package com.sinonc.orders.delaytask;

import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.Auctionmember;
import com.sinonc.orders.domain.BookGoods;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.mapper.AuctionMapper;
import com.sinonc.orders.mapper.AuctionmemberMapper;
import com.sinonc.orders.mapper.BookGoodsMapper;
import com.sinonc.orders.service.AuctionBondService;
import com.sinonc.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 过期任务执行器
 */
@Slf4j
@Service
public class ExpiredTaskExecutorService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookGoodsMapper bookGoodsMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private AuctionmemberMapper auctionmemberMapper;

    @Autowired
    private DelayTaskService delayTaskService;
    @Autowired
    private AuctionBondService auctionBondService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 执行过期任务
     *
     * @param delayTask 延时任务
     */
    @Async
    public void executor(DelayTask delayTask) {

        Date date = new Date();

        try {

            Integer taskType = delayTask.getTaskType();

            if (TaskTypeConstants.WAIT_PAY_TASK.equals(taskType)) {

                //取消未支付的订单
                Order body = (Order) delayTask.getBody();
                if (body != null) {

                    Order order = orderService.getOrderById(body.getOrderId());

                    if (order != null) {
                        //判断订单状态，如果是未支付，则取消订单
                        if (order.getOrderStatus().equals("0") && order.getTradeStatus().equals(0)) {

                            orderService.cancelOrder(order);

                            log.info("订单:{} 支付超时，已取消", order.getOrderNo());
                        }
                    }
                }

            } else if (TaskTypeConstants.AUTO_CONFIRM_TASK.equals(taskType)) {
                //自动收货
                Order body = (Order) delayTask.getBody();
                if (body != null) {
                    Order order = orderService.getOrderById(body.getOrderId());

                    if (order != null && order.getAutoConfirmTime() != null) {

                        //判断是否已经延长了自动货时间
                        if (order.getAutoConfirmTime().getTime() > System.currentTimeMillis()) {
                            //如果已经延长了收货时间，则将消息重新放入队列中
                            DelayTask instance = DelayTask.getInstance(delayTask.getTaskType(), order.getAutoConfirmTime().getTime(), date.getTime(), order);
                            delayTaskService.addTask(instance);
                            return;
                        }

                        //判断订单状态
                        if (order.getOrderStatus() == OrderConstant.STATUS_WAITING_FOR_CONFIRMATION) {
                            orderService.confirmOrder(order);
                            log.info("订单:{} 收货超时，已自动收货", order.getOrderNo());
                        }
                    }
                }
            } else if (TaskTypeConstants.BOOK_START_TASK.equals(taskType)) {

                //开启预订活动
                BookGoods body = (BookGoods) delayTask.getBody();
                if (body != null) {
                    BookGoods bookGoods = bookGoodsMapper.selectBookGoodsById(body.getBgId());

                    if (bookGoods != null) {

                        //判断是否修改过开始时间
                        if (bookGoods.getStartTime().getTime() > date.getTime()) {
                            //如果修改过，则将消息重新放入消息队列中
                            DelayTask instance = DelayTask.getInstance(delayTask.getTaskType(), bookGoods.getStartTime().getTime(), date.getTime(), bookGoods);
                            delayTaskService.addTask(instance);
                            return;
                        }

                        bookGoods.setStatus(1);
                        bookGoodsMapper.updateBookGoods(bookGoods);
                        log.info("预订活动 {} 开始", bookGoods.getBgId());
                    }
                }

            } else if (TaskTypeConstants.BOOK_END_TASK.equals(taskType)) {

                BookGoods body = (BookGoods) delayTask.getBody();
                if (body != null) {
                    BookGoods bookGoods = bookGoodsMapper.selectBookGoodsById(body.getBgId());

                    if (bookGoods != null) {

                        //判断是否修改过结束时间
                        if (bookGoods.getEndTime().getTime() > date.getTime()) {
                            //如果修改过，则将消息重新放入消息队列中
                            DelayTask instance = DelayTask.getInstance(delayTask.getTaskType(), bookGoods.getEndTime().getTime(), date.getTime(), bookGoods);
                            delayTaskService.addTask(instance);
                            return;
                        }

                        bookGoods.setStatus(2);
                        bookGoodsMapper.updateBookGoods(bookGoods);
                        log.info("预订活动 {} 结束", bookGoods.getBgId());
                    }
                }

            } else if (TaskTypeConstants.AUCTION_START_TASK.equals(taskType) && null != stringRedisTemplate) {

                Auction auction = (Auction) delayTask.getBody();
                if (auction != null) {
                    //活动开始redis插入标识
                    stringRedisTemplate.opsForValue().set("auctionStart" + auction.getAuctionId(), "start");

                    Auction au = auctionMapper.selectAuctionById(auction.getAuctionId());

                    if (au != null) {

                        if (au.getAuctionBegintime().getTime() > date.getTime()) {
                            DelayTask instance = DelayTask.getInstance(delayTask.getTaskType(), au.getAuctionBegintime().getTime(), date.getTime(), au);
                            delayTaskService.addTask(instance);
                            return;
                        }

                        auction.setIsEnd(1); //竞拍活动状态设为已开始
                        auctionMapper.updateAuction(auction);
                        log.info("竞拍活动 {} 开始", auction.getAuctionId());
                    }
                }
            } else if (TaskTypeConstants.AUCTION_END_TASK.equals(taskType) && null != stringRedisTemplate) {
                Auction auction = (Auction) delayTask.getBody();
                if (auction != null) {
                    //活动结束删除redis标识
                    stringRedisTemplate.delete("auctionStart" + auction.getAuctionId());

                    Auction au = auctionMapper.selectAuctionById(auction.getAuctionId());

                    if (au != null) {

                        if (au.getAuctionEndtime().getTime() > date.getTime()) {
                            DelayTask instance = DelayTask.getInstance(delayTask.getTaskType(), au.getAuctionEndtime().getTime(), date.getTime(), au);
                            delayTaskService.addTask(instance);
                            return;
                        }

                        au.setIsEnd(2); //活动结束吧竞拍活动状态设为已结束

                        //竞拍结束查询最终获得者，把价钱更新到活动表中
                        Auctionmember auctionmember = auctionmemberMapper.listAuctionMerberMax(au.getGoodsId());

                        if (auctionmember != null) {
                            au.setAuctionNowprice(auctionmember.getNowPrice());
                            auctionMapper.updateAuction(au);

                            //拍卖结束标识最终获得者
                            auctionBondService.updateAuctionBoonForWin(auctionmember.getMemberId(), auctionmember.getGoodsId());
                            //活动结束，退款
                            auctionBondService.auctionTK(auction.getAuctionId());

                        } else {
                            auctionMapper.updateAuction(au);
                        }
                        log.info("竞拍活动 {} 结束", au.getAuctionId());
                    }
                }
            }


        } catch (Exception e) {
            log.error("延迟任务执行异常：", e);
        }

    }


}
