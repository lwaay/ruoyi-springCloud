package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.TradeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 支付 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
@Slf4j
public class TradeInfoServiceImpl implements TradeInfoService, PayObserver, RefundObserver {

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookOrderMapper bookOrderMapper;

    @Autowired
    private AuctionBondMapper auctionBondMapper;

    @Autowired
    private RefundMapper refundMapper;

    /**
     * 查询支付信息
     *
     * @param tradeId 支付ID
     * @return 支付信息
     */
    @Override
    public TradeInfo getTradeInfoById(Long tradeId) {
        return tradeInfoMapper.selectTradeInfoById(tradeId);
    }

    /**
     * 查询支付列表
     *
     * @param tradeInfo 支付信息
     * @return 支付集合
     */
    @Override
    public List<TradeInfo> listTradeInfo(TradeInfo tradeInfo) {
        return tradeInfoMapper.selectTradeInfoList(tradeInfo);
    }

    /**
     * 新增支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
    @Override
    public int addTradeInfo(TradeInfo tradeInfo) {
        return tradeInfoMapper.insertTradeInfo(tradeInfo);
    }

    /**
     * 修改支付
     *
     * @param tradeInfo 支付信息
     * @return 结果
     */
    @Override
    public int updateTradeInfo(TradeInfo tradeInfo) {
        return tradeInfoMapper.updateTradeInfo(tradeInfo);
    }

    /**
     * 删除支付对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTradeInfoByIds(String ids) {
        return tradeInfoMapper.deleteTradeInfoByIds(Convert.toStrArray(ids));
    }


    @Override
    public void payNotify(PayMessage message) throws Exception {

        boolean isBookOrder = false;

        String orderNo;

        //交易平台传入本系统内部订单号
        String outTradeNo = message.getOrderNo();
        //交易平台交易流水号
        String tradeNo = message.getOutTrade();
        String tradeStatus = message.getTradeResult();
        String totalAmount = message.getAmount();
        String receiptAmount = message.getReceiptAmount();

        //判断是否为预订单
        if (outTradeNo.contains("DJ") || outTradeNo.contains("WK")) {
            orderNo = outTradeNo.substring(2);
            isBookOrder = true;
        } else {
            orderNo = outTradeNo;
        }

        BigDecimal total = new BigDecimal(totalAmount);
        Date date = new Date();

        //判断是否是竞拍押金
        if (outTradeNo.contains("JP")) {

            AuctionBond auctionBond = auctionBondMapper.selectByAuctionOrderNo(orderNo);

            TradeInfo tradeInfo = new TradeInfo();
            tradeInfo.setMemberId(auctionBond.getMemberId());
            tradeInfo.setCreateTime(date);
            tradeInfo.setOrderIdType(1);
            tradeInfo.setOrderIdP(auctionBond.getAuctionbondId());
            tradeInfo.setOrderNo(outTradeNo);
            tradeInfo.setRetrunMsg(tradeStatus);
            tradeInfo.setPayStatus(1);
            tradeInfo.setTradeType(0);
            tradeInfo.setPayType(message.getPayType());
            tradeInfo.setTotalAmount(total);
            tradeInfo.setReceiptAmount(new BigDecimal(receiptAmount));
            tradeInfo.setShopIdP(auctionBond.getShopId());
            tradeInfo.setTradeNo(tradeNo);


            try {
                //插入支付信息
                tradeInfoMapper.insertTradeInfo(tradeInfo);
            } catch (Exception e) {
                log.error("添加支付支付记录异常：\n" + e.getMessage());
            }

        } else {

            Order order = orderMapper.selectOrderByNo(orderNo);

            TradeInfo tradeInfo = new TradeInfo();
            tradeInfo.setCreateTime(date);
            tradeInfo.setOrderIdP(order.getOrderId());
            tradeInfo.setOrderNo(outTradeNo);
            tradeInfo.setOrderIdType(0);
            tradeInfo.setMemberId(order.getMemberIdP());
            tradeInfo.setRetrunMsg(tradeStatus);
            tradeInfo.setPayStatus(1);
            tradeInfo.setTradeType(0);
            tradeInfo.setPayType(message.getPayType());
            tradeInfo.setTotalAmount(total);
            tradeInfo.setReceiptAmount(new BigDecimal(receiptAmount));
            tradeInfo.setShopIdP(order.getShopIdP());
            tradeInfo.setTradeNo(tradeNo);


            try {
                //插入支付信息
                tradeInfoMapper.insertTradeInfo(tradeInfo);
            } catch (Exception e) {
                log.error("添加支付支付记录异常：\n" + e.getMessage());
            }


            order.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_DELIVERY);
            order.setTradeStatus(1);
            order.setTradeTime(date);
            order.setTradeIdP(tradeInfo.getTradeId());
            order.setUpdateTime(date);

            //判断是否为预订单,预订单则需要更新预订单状态
            if (isBookOrder) {

                BookOrder bookOrder = bookOrderMapper.selectByOrderId(order.getOrderId());

                //如果是定金，则修改订单状态为已付定金
                if (outTradeNo.contains("DJ")) {
                        bookOrder.setEarnestTradeId(tradeInfo.getTradeId());
                        bookOrder.setEarnestTradeTime(date);
                        bookOrder.setTradeStatus(1);
                        order.setTradeStatus(2);
                        order.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_PAYMENT);

                    } else {
                        bookOrder.setRestTradeId(tradeInfo.getTradeId());
                        bookOrder.setRestTradeTime(date);
                        bookOrder.setTradeStatus(2);
                }

                //更新预订单状态
                bookOrderMapper.updateBookOrder(bookOrder);
            }

            try {
                //更新订单状态
                orderMapper.updateOrder(order);
            } catch (Exception e) {
                log.error("更新订单" + order.getOrderNo() + "支付状态异常：\n" + e.getMessage());
            }
        }
    }

    @Override
    public void refundNotify(RefundMessage message) throws Exception {

        String orderNo = message.getOrderNo();
        Date date = new Date();

        //创建交易信息
        TradeInfo tradeInfo = new TradeInfo();

        tradeInfo.setCreateTime(date);
        tradeInfo.setOrderNo(orderNo);
        tradeInfo.setOrderIdType(0);
        tradeInfo.setRetrunMsg(message.getTradeResult());
        tradeInfo.setPayStatus(1);
        tradeInfo.setTradeType(1);
        tradeInfo.setOrderIdType(0);

        //支付方式。0，微信、1，支付宝
        tradeInfo.setPayType(message.getPayType());
        tradeInfo.setTotalAmount(new BigDecimal(message.getRefundAmount()));
        tradeInfo.setReceiptAmount(new BigDecimal(message.getReceiptAmount()));
        tradeInfo.setTradeNo(message.getOutTradeNo());

        //预订单退款操作
        if (orderNo.contains("DJ") || orderNo.contains("WK")) {

            Order order = orderMapper.selectOrderByNo(orderNo.substring(2));

            tradeInfo.setOrderIdP(order.getOrderId());
            tradeInfo.setMemberId(order.getMemberIdP());
            tradeInfo.setShopIdP(order.getShopIdP());

            BookOrder bookOrder = bookOrderMapper.selectByOrderId(order.getOrderId());

            //更新预订单表
            if (orderNo.contains("DJ")) {
                //预订定金退款操作
                bookOrder.setTradeStatus(BookOrder.TRADE_STATUS_REFUND_EARNEST);
                order.setTradeStatus(Order.TRADE_STATUS_REFUND_ALL);
                order.setRefundStatus(2);
                //修改订单状态为关闭订单
                order.setOrderStatus(OrderConstant.STATUS_TRADE_CLOSED);
            } else {
                //判断尾款退款是否为全额退款
                if (bookOrder.getRestPrice().compareTo(new BigDecimal(message.getRefundAmount())) > 0) {
                    //部分退款
                    bookOrder.setTradeStatus(BookOrder.TRADE_STATUS_REFUND_REST_PART);
                    order.setTradeStatus(Order.TRADE_STATUS_REFUND_PART);
                    order.setOrderStatus(OrderConstant.STATUS_REFUND_SUCCESSFUL);
                    order.setRefundStatus(2);
                } else {
                    //全部退款
                    bookOrder.setTradeStatus(BookOrder.TRADE_STATUS_REFUND_REST_ALL);
                    order.setTradeStatus(Order.TRADE_STATUS_REFUND_ALL);
                    order.setOrderStatus(OrderConstant.STATUS_TRADE_CLOSED);
                    order.setRefundStatus(2);
                }
            }

            //更新预订单状态
            bookOrderMapper.updateBookOrder(bookOrder);

            order.setRefundStatus(2);
            //更新订单状态
            orderMapper.updateOrder(order);


        } else if (orderNo.contains("JP")) {//竞拍押金退款操作

            AuctionBond auctionBond = auctionBondMapper.selectByAuctionOrderNo(orderNo);

            tradeInfo.setOrderIdType(1);
            tradeInfo.setOrderIdP(auctionBond.getOrderId());
            tradeInfo.setMemberId(auctionBond.getMemberId());
            tradeInfo.setShopIdP(auctionBond.getShopId());

            auctionBond.setRefundNo(message.getOutTradeNo());
            auctionBond.setRefundPrice(new BigDecimal(message.getRefundAmount()));
            auctionBond.setRefundTime(date);
            auctionBond.setPaymentStatus(2);

            auctionBondMapper.updateAuctionBond(auctionBond);


        } else { //普通订单和预订单退款

            Order order = orderMapper.selectOrderByNo(message.getOrderNo());

            tradeInfo.setOrderIdP(order.getOrderId());
            tradeInfo.setMemberId(order.getMemberIdP());
            tradeInfo.setShopIdP(order.getShopIdP());


            //判断是否为全额退款
            if (order.getActualPayment().compareTo(new BigDecimal(message.getRefundAmount())) == 0) {
                //全额退款，关闭订单
                order.setOrderStatus(OrderConstant.STATUS_TRADE_CLOSED);
                order.setTradeStatus(Order.TRADE_STATUS_REFUND_ALL);
            } else {
                //非全额退款，订单成交
                order.setOrderStatus(OrderConstant.STATUS_TRADE_SUCCESSFUL);
                order.setTradeStatus(Order.TRADE_STATUS_REFUND_PART);
            }

            //更新订单退款状态为同意
            order.setRefundStatus(2);

            //更新订单状态
            orderMapper.updateOrder(order);
        }

        //写入退款交易信息
        tradeInfoMapper.insertTradeInfo(tradeInfo);

        //更新退款申请信息
        Refund refund = refundMapper.selectRefundByOrderNo(message.getOrderNo());
        refund.setTradeId(tradeInfo.getTradeId());

        refundMapper.updateRefund(refund);

    }

}
