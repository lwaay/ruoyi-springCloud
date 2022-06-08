package com.sinonc.orders.service.impl;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedissonService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.common.CouponConstant;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.common.OrderItemConstant;
import com.sinonc.orders.delaytask.DelayTask;
import com.sinonc.orders.delaytask.DelayTaskServiceImpl;
import com.sinonc.orders.delaytask.TaskTypeConstants;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.AccountDto;
import com.sinonc.orders.dto.OrderCountDto;
import com.sinonc.orders.dto.OrderDto;
import com.sinonc.orders.expection.OrderException;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.*;
import com.sinonc.orders.vo.FareVo;
import com.sinonc.orders.vo.GoodsVo;
import com.sinonc.orders.vo.OrderVO;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.sinonc.orders.utils.OrderUtil.createOrderNo;

/**
 * 订单 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderMapper orderMapper;
    private final GoodsMapper goodsMapper;
    private final GoodsSpecsMapper goodsSpecsMapper;
    private final SpecsMapper specsMapper;
    private final AddressMapper addressMapper;
    private final OrderItemMapper orderItemMapper;
    private final CouponMapper couponMapper;
    private final CouponMemberMapper couponMemberMapper;
    private final BookOrderMapper bookOrderMapper;
    private final ShopDiscountLogMapper shopDiscountLogMapper;
    private final ExpPriceMapper expPriceMapper;
    private final FareTemplateMapper fareTemplateMapper;
    private final CarryModeMapper carryModeMapper;
//    private final RefundService refundService;

    private final DelayTaskServiceImpl delayTaskService;
    private final AuctionmemberService auctionmemberService;
    private final RefundService refundService;
    private final ExpressService expressService;
    private final TradeInfoService tradeInfoService;

    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private IYxShippingTemplatesService templatesService;

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private OdCartMapper cartMapper;

    @Autowired
    private IShopService shopService;


    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper, GoodsSpecsMapper goodsSpecsMapper, GoodsMapper goodsMapper, AddressMapper addressMapper, OrderItemMapper orderItemMapper, CouponMapper couponMapper, CouponMemberMapper couponMemberMapper, SpecsMapper specsMapper, BookOrderMapper bookOrderMapper, ShopDiscountLogMapper shopDiscountLogMapper, DelayTaskServiceImpl delayTaskService, AuctionmemberService auctionmemberService, ExpPriceMapper expPriceMapper, RefundService refundService, FareTemplateMapper fareTemplateMapper, CarryModeMapper carryModeMapper,
                            ExpressService expressService,TradeInfoService tradeInfoService) {
        this.orderMapper = orderMapper;
        this.goodsMapper = goodsMapper;
        this.addressMapper = addressMapper;
        this.orderItemMapper = orderItemMapper;
        this.couponMapper = couponMapper;
        this.couponMemberMapper = couponMemberMapper;
        this.specsMapper = specsMapper;
        this.bookOrderMapper = bookOrderMapper;
        this.shopDiscountLogMapper = shopDiscountLogMapper;
        this.delayTaskService = delayTaskService;
        this.auctionmemberService = auctionmemberService;
        this.expPriceMapper = expPriceMapper;
        this.refundService = refundService;
        this.fareTemplateMapper = fareTemplateMapper;
        this.carryModeMapper = carryModeMapper;
        this.goodsSpecsMapper = goodsSpecsMapper;
        this.expressService = expressService;
        this.tradeInfoService = tradeInfoService;
    }

    /**
     * 查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    @Override
    public Order getOrderById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    /**
     * 根据订单ID查询订单VO
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public OrderVO getOrderVoById(Long orderId) {
        OrderVO vo = orderMapper.selectOrderVoById(orderId);
        if (!Optional.ofNullable(vo).isPresent()){
            return vo;
        }
        vo.setExpress(expressService.getExpressById(vo.getExpressIdP()));
        vo.setTradeInfo(tradeInfoService.getTradeInfoById(vo.getTradeIdP()));
        vo.setAddr(addressMapper.selectAddressById(vo.getAddrIdP()));
        R<WxUser> r = wxUserService.getUserById(vo.getMemberIdP());
        if (r.getCode() == 200){
            vo.setBuyer(r.getData());
        }

        Shop shop = shopService.selectShopById(vo.getShopIdP());
        vo.setShopHead(shop.getShopLogo());

        return vo;
    }

    /**
     * @param order 订单信息
     * @return orderVo 列表
     */
    @Override
    public List<OrderVO> listOrderVO(Order order) {
        //如果查已完成订单,带上待评价订单
        if(OrderConstant.STATUS_TRADE_SUCCESSFUL.equals(order.getOrderStatus()) ){
            order.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_EVALUATION + "," + OrderConstant.STATUS_TRADE_SUCCESSFUL);
        }
        List<OrderVO> list = orderMapper.selectOrderVoList(order);
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        list.forEach(item->{
            Shop shop = shopService.selectShopById(item.getShopIdP());
            if (!Optional.ofNullable(shop).isPresent()){
                return;
            }
            item.setShopHead(shop.getShopLogo());
        });
        return list;
    }


    /**
     * 查询会员订单vo列表
     *
     * @param order 会员ID
     * @return 订单Vo列表
     */
    @Override
    public List<OrderVO> listDataScopeOrderVO(OrderDto order){
        return orderMapper.listDataScopeOrderVo(order);
    }

    @Override
    public List<OrderVO> listOrderDto(OrderDto order) {
        return orderMapper.selectOrderDtoList(order);
    }

    /**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
    @Override
    public List<Order> listOrder(Order order) {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int addOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    /**
     * 通过API接口创建订单
     *
     * @param orderVO 订单视图对象
     * @return 结果
     */
    @Transactional
    @Override
    public Order createOrder(OrderVO<GoodsVo> orderVO) {

        //定金
        BigDecimal earnestPrice = new BigDecimal("0.00");

        Date date = new Date();
        //验证地址信息是否合法
        Address address = addressMapper.selectAddressById(orderVO.getAddressId());
        if (address == null || !address.getMemberIdP().equals(orderVO.getMemberId())) {
            throw new OrderException("收货地址信息错误或者不存在");
        }
        StringBuilder strAddress = new StringBuilder();
        strAddress.append(address.getProvince()).append(address.getCity()).append(address.getCounty()).append(address.getAddr());
        //创建订单信息
        Order order = new Order();
        order.setOrderNo(createOrderNo());
        order.setAddress(address.getMergerName()+address.getAddr());
        order.setAddrIdP(address.getAddressId());
        order.setCouponIdP(orderVO.getCouponId());
        order.setCreateTime(date);
        order.setOrderType(orderVO.getType());

        order.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_PAYMENT);
        order.setMemberIdP(orderVO.getMemberId());
        order.setPhone(address.getPhone());
        order.setReceiver(address.getName());
        order.setShopIdP(orderVO.getShopId());
        //计算订单运费
        //order.setExpressPrice(orderVO.getExpressPrice());

        //先将部分数据插入数据库获取主键ID
        int orderRows = orderMapper.insertOrder(order);

        if (orderRows == 0) {
            throw new OrderException("生成订单信息主键异常");
        }

        //获取订单中的商品信息
        ArrayList<GoodsVo> goodsVos = orderVO.getGoodsVos();
        List<OrderItem> orderItems = new ArrayList<>();


        for (GoodsVo goodsVo : goodsVos) {

            Goods goods = goodsMapper.selectGoodsById(goodsVo.getGoodsId());

            if (goods == null) {
                throw new OrderException("商品ID[" + goodsVo.getGoodsId() + "]不存在");
            }

            Specs specs = specsMapper.selectSpecsByIdForUpdate(goodsVo.getSpecsId());
            //预订商品，计算定金
            if (goods.getType() == 2) {
                earnestPrice = earnestPrice.add(specs.getEarnest());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setCreateTime(date);
            orderItem.setGoodsIdP(goodsVo.getGoodsId());
            orderItem.setGoodsCount(goodsVo.getCount());
            String goodsImg = goods.getImage();

            if (goodsImg != null) {
                goodsImg = goodsImg.split(",")[0];
            }

            orderItem.setGoodsImg(goodsImg);
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsOrderStatus(OrderItemConstant.STATUS_NORMAL);
            orderItem.setGoodsSpecsIdP(specs.getSpecsId());
            orderItem.setGoodsSpecsName(specs.getSpecsName());
            orderItem.setGoodsType(goods.getType());
            if(order.getOrderType() == 3){
                Auctionmember auctionmember = auctionmemberService.listAuctionMerberMax(goods.getGoodsId());
                if(auctionmember != null){
                    orderItem.setGoodsPrice(auctionmember.getNowPrice());
                }else{
                    orderItem.setGoodsPrice(new BigDecimal(9999999));
                }
            }else{
                orderItem.setGoodsPrice(specs.getSpecsPrice());
            }
            orderItem.setGoodsTotalPrice(specs.getSpecsPrice().multiply(new BigDecimal(goodsVo.getCount())));
            orderItem.setMemberIdP(orderVO.getMemberId());
            orderItem.setShopIdP(goods.getShopId());
            orderItem.setOrderIdP(order.getOrderId());
            orderItem.setGoodsShopDiscountPrice(new BigDecimal(0));
            orderItem.setGoodsPlatformDiscountPrice(new BigDecimal(0));
            orderItems.add(orderItem);
        }

        //计算商品总价
        calculateOrderTotalPrice(order, orderItems);
        BigDecimal expressPrice = templatesService.handlePostage(orderItems,address);
        //将物流费用更新到订单实付金额中
        order.setActualPayment(order.getActualPayment().add(expressPrice));
        //将物流费用更新到订单金额中
        BigDecimal tt_order_amount = order.getOrderTotalPrice().add(expressPrice);
        order.setOrderTotalPrice(tt_order_amount);

        //如果提交参数中存在优惠券ID，则验证优惠券是否合法
        if (orderVO.getCouponId() != null && orderVO.getCouponId() != 0) {
            //获取优惠券信息
            Coupon coupon = couponMapper.selectCouponById(orderVO.getCouponId());
            if (coupon == null) {
                throw new OrderException("未找到关联的优惠卷信息,创建订单失败");
            }
            long startTime = coupon.getStartTime().getTime();
            long endTime = coupon.getInvalidTime().getTime();
            long currentTime = System.currentTimeMillis();
            //验证时间
            if (startTime > currentTime || currentTime > endTime) {
                throw new OrderException("优惠卷已过期,创建订单失败");
            }
            //判断优惠券是否属于该用户
            CouponMember query = new CouponMember();
            query.setCouponIdP(orderVO.getCouponId());
            query.setMemberIdP(orderVO.getMemberId());
            query.setCouponStatus(CouponConstant.STATUS_UNUSE);
            List<CouponMember> couponMembers = couponMemberMapper.selectCouponMemberList(query);
            if (couponMembers == null || couponMembers.size() == 0) {
                throw new OrderException("用户未领取优惠卷或优惠卷已过期，创建订单失败");
            }
            //验证金额
            if (coupon.getMinimumPrice().compareTo(order.getOrderTotalPrice())!=-1){
                throw new OrderException("订单金额不满足优惠卷最小使用金额，创建订单失败");
            }
            if (coupon.getCouponAmount().compareTo(order.getOrderTotalPrice())!=-1){
                throw new OrderException("订单金额小于优惠金额，创建订单失败");
            }
            //验证优惠卷类型
            switch (coupon.getCouponType()){
                //店铺通用卷
                case 0:
                    if (!order.getShopIdP().equals(coupon.getShopIdP())){
                        throw new OrderException("店铺通用卷无法使用于非指定店铺,创建订单失败");
                    }
                    break;
                //指定商品卷
                case 1:
                    String goodsList = coupon.getGoodsList();
                    if (StringUtils.isBlank(goodsList)){
                        throw new OrderException("优惠卷指定商品信息有误,创建订单失败");
                    }
                    long hasGoods = Arrays.stream(goodsList.split(",")).mapToLong(goodsId->
                            orderItems.stream().filter(fil -> goodsId.equals(fil.getGoodsIdP() + "")).count()
                    ).sum();
                    if (hasGoods < 1){
                        throw new OrderException("指定商品卷无法适用于非指定商品,创建订单失败");
                    }
                    break;
                //平台通用卷
                case 2:break;
                //未知卷，报错
                default:throw new OrderException("优惠卷类型信息错误,创建订单失败");
            }

            //修改优惠券状态
            query.setCouponStatus(CouponConstant.STATUS_USED);
            query.setUseTime(new Date());
            int updateCouponMemberRows = couponMemberMapper.updateCouponMember(query);
            if (updateCouponMemberRows == 0) {
                throw new OrderException("更新优惠券使用信息异常,创建订单失败");
            }

            //将优惠卷中的优惠金额更新至订单中
            BigDecimal preferential_amount = order.getOrderTotalPrice().subtract(coupon.getCouponAmount());
            order.setOrderTotalPrice(preferential_amount);
            order.setShopTotalDiscountPrice(coupon.getCouponAmount());
        }

        //将计算的总价更新至订单信息中
        int orderUpdateRows = orderMapper.updateOrder(order);
        if (orderUpdateRows == 0) {
            throw new OrderException("更新商品总价信息异常");
        }

        //更新商品总数
        int orderItemRows = 0;

        //将订单商品中信息写入数据表
        for (OrderItem orderItem : orderItems) {
            orderItemRows += orderItemMapper.insertOrderItem(orderItem);
            Specs specs = specsMapper.selectSpecsByIdForUpdate(orderItem.getGoodsSpecsIdP());
            if (specs == null) {
                throw new OrderException("规格ID[" + orderItem.getGoodsSpecsIdP() + "]不存在");
            }
            //判断购买数量是否与规格要求数量一致
            if ((specs.getMaxCount() < orderItem.getGoodsCount() || specs.getMinCount() > orderItem.getGoodsCount()) && specs.getMaxCount() != 0) {
                throw new OrderException("购买数量与规格要求不一致");
            }
            //检查库存与规格是否一直)
            if (specs.getStock() < orderItem.getGoodsCount()) {
                throw new OrderException("库存不足，创建订单失败");
            }
            RLock rlock = redissonService.getRLock("sale_goods_lock:"+orderItem.getGoodsIdP());
            try{
                boolean res = rlock.tryLock(60,10, TimeUnit.SECONDS);
                if (res){
                    //减库存操作
                    specs.setStock(specs.getStock() - orderItem.getGoodsCount());
                    specsMapper.updateSpecs(specs);
                }else {
                    throw new OrderException("无法扣减商品库存，创建订单失败");
                }
            }catch (Exception ex){
                throw new OrderException(ex.getMessage(),ex);
            }finally {
                rlock.unlock();
            }
        }

        //清理购物车
        orderItems.forEach(item->{
            cartMapper.payCartByGoodsId(item.getGoodsSpecsIdP(),item.getMemberIdP());
        });

        //判断数据库更新数量是否和提交商品数一致
        if (orderItemRows != orderItems.size()) {
            throw new OrderException("创建订单商品详情异常");
        }

        //判断是否是预订单
        if (order.getOrderType() == OrderConstant.TYPE_BOOK) {

            //创建定金和尾款相关信息
            BookOrder bookOrder = new BookOrder();
            bookOrder.setShopId(order.getShopIdP());
            bookOrder.setEarnestPrice(earnestPrice);
            bookOrder.setOrderId(order.getOrderId());
            bookOrder.setEarnestNo("DJ" + order.getOrderNo());
            bookOrder.setRestNo("WK" + order.getOrderNo());
            bookOrder.setTradeStatus(0);
            bookOrder.setRestPrice(order.getActualPayment().subtract(earnestPrice));
            bookOrder.setCreateTime(date);
            bookOrderMapper.insertBookOrder(bookOrder);

        }

        //将订单添加至延时队列,超时未支付，取消订单
        DelayTask orderDelayTask = DelayTask.getInstance(TaskTypeConstants.WAIT_PAY_TASK, System.currentTimeMillis() + (1000L * 60 * 30), System.currentTimeMillis(), order);
        delayTaskService.addTask(orderDelayTask);

        return order;
    }


    /**
     * 取消订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public int cancelOrder(Order order) {

        OrderVO orderVO = orderMapper.selectOrderVoById(order.getOrderId());

        if (orderVO == null) {
            throw new BusinessException("订单不存在，请重试");
        }

        if (!OrderConstant.STATUS_WAITING_FOR_PAYMENT.equals(orderVO.getOrderStatus())) {
            throw new BusinessException("不可取消已付款订单");
        }

        //修改订单状态
        order.setOrderStatus(OrderConstant.STATUS_TRADE_CLOSED);
        Date endTime = new Date();
        order.setFinishTime(endTime);

        //恢复库存
        ArrayList<OrderItem> goodsVos = orderVO.getGoodsVos();

        for (OrderItem orderItem : goodsVos) {
            Long specsId = orderItem.getGoodsSpecsIdP();

            Specs specs = specsMapper.selectSpecsById(specsId);

            if (specs != null) {
                RLock rlock = redissonService.getRLock("sale_goods_lock:"+orderItem.getGoodsIdP());
                try{
                    boolean res = rlock.tryLock(60,10, TimeUnit.SECONDS);
                    if (res){
                        //减库存操作
                        specs.setStock(specs.getStock() + orderItem.getGoodsCount());
                        specs.setUpdateTime(endTime);
                        specsMapper.updateSpecs(specs);
                    }else {
                        throw new OrderException("商品库存修改失败，取消订单失败");
                    }
                }catch (Exception ex){
                    throw new OrderException(ex.getMessage(),ex);
                }finally {
                    rlock.unlock();
                }
            }

        }

        Order update = new Order();
        update.setOrderId(orderVO.getOrderId());
        update.setOrderStatus(OrderConstant.STATUS_TRADE_CLOSED);

        //乐观锁条件
        Order lock = new Order();
        lock.setOrderStatus(orderVO.getOrderStatus());

        //乐观锁机制更新订单状态,防止极端环境订单被异常取消
        int i = orderMapper.updateOrderOptimistic(update, lock);

        if (i == 0) {
            throw new RuntimeException("取消订单失败，原因：订单状态已被修改");
        }

        //预订订单还需更新预订信息表
        if (orderVO.getOrderType() == 2) {
            BookOrder bookOrder = new BookOrder();
            bookOrder.setOrderId(orderVO.getOrderId());
            bookOrder.setTradeStatus(3);
            bookOrderMapper.updateBookOrder(bookOrder);
        }

//        //判断订单是否需要退款
//        if (orderVO.getTradeStatus() == 1 || orderVO.getTradeStatus() == 2) {
//            try {
//                refundService.refund(orderVO.getOrderNo(), orderVO.getActualPayment(), "取消订单退款");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

        return i;
    }


    /**
     * 确认收货
     *
     * @return
     */
    @Override
    public int confirmOrder(Order order) {

        Order order1 = orderMapper.selectOrderById(order.getOrderId());

        if (order1 == null || !OrderConstant.STATUS_WAITING_FOR_CONFIRMATION.equals(order1.getOrderStatus())) {
            throw new BusinessException("订单状态异常");
        }

        order1.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_EVALUATION);
        order1.setOrderId(order.getOrderId());
        order1.setMemberIdP(order.getMemberIdP());

        return orderMapper.updateOrder(order1);
    }

    /**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids) {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询各个状态的订单数量
     *
     * @param order 订单
     * @return
     */
    @Override
    public Map<String, Object> getOrderStatusCount(Order order) {
        return orderMapper.selectOrderStatusCount(order);
    }


    /**
     * 商家自定义优惠金额
     *
     * @param orderId 订单ID
     * @param amount  优惠金额
     * @return
     */
    @Override
    @Transactional
    public int shopCustomDiscount(Long orderId, String amount) {

        Order order = orderMapper.selectOrderByIdForUpdate(orderId);

        Long shopId = SecurityUtils.getUserId();

        //订单身份验证和状态验证
        if (order == null || !order.getShopIdP().equals(shopId) || (!OrderConstant.STATUS_WAITING_FOR_PAYMENT.equals(order.getOrderStatus()))) {
            throw new BusinessException("订单信息不正确");
        }

        order.setActualPayment(order.getActualPayment().add(order.getShopCustomDiscountPrice()));

        BigDecimal discountAmount = new BigDecimal(amount);

        BigDecimal updateActPayment = order.getActualPayment().subtract(discountAmount);

        if (updateActPayment.compareTo(new BigDecimal("0.00")) <= 0) {
            throw new BusinessException("优惠后订单总金额不能为0.00");
        }

        //如果是预订订单，则需要更新尾款信息
        if (order.getOrderType() == OrderConstant.TYPE_BOOK) {
            BookOrder bookOrder = bookOrderMapper.selectByOrderId(orderId);
            bookOrder.setRestPrice(updateActPayment);
            bookOrderMapper.updateBookOrder(bookOrder);
        }

        Date date = new Date();

        //添加修改记录
        ShopDiscountLog shopDiscountLog = new ShopDiscountLog();

        shopDiscountLog.setCreateTime(date);
        shopDiscountLog.setCreateBy(SecurityUtils.getUserId()+"");
        shopDiscountLog.setDiscountPrice(discountAmount);
        shopDiscountLog.setOriginalPrice(order.getActualPayment());
        shopDiscountLog.setPresentPrice(updateActPayment);
        shopDiscountLog.setMemberId(order.getMemberIdP());
        shopDiscountLog.setOrderNo(order.getOrderNo());
        shopDiscountLog.setOrderId(order.getOrderId());
        shopDiscountLog.setShopId(order.getShopIdP());

        shopDiscountLogMapper.insertShopDiscountLog(shopDiscountLog);

        //更新订单价格
        order.setShopCustomDiscountPrice(discountAmount);
        order.setActualPayment(updateActPayment);
        order.setUpdateTime(date);

        return orderMapper.updateOrder(order);
    }


    @Override
    public double getTodayOrdersPayment(OrderDto order) {
        return orderMapper.selectTodayOrdersPayment(order);
    }

    @Override
    public Long getOrderCount(OrderDto orderDto) {
        return orderMapper.getOrdersCount(orderDto);
    }


    @Override
    public Map getOrderCountByDate(AccountDto accountDto) {
        //查询近一个月
        if (accountDto.getMark() == 1) {
            List<OrderCountDto> orderCountDtos = orderMapper.selectOrderCountByDay(accountDto);
            return dataProcessing(orderCountDtos);
        }
        //查询近半年
        if (accountDto.getMark() == 2) {
            List<OrderCountDto> orderCountDtos = orderMapper.selectOrderCountByMonth(accountDto);
            return dataProcessing(orderCountDtos);
        }
        //查询近一年
        if (accountDto.getMark() == 3) {
            List<OrderCountDto> orderCountDtos = orderMapper.selectOrderCountByYear(accountDto);
            return dataProcessing(orderCountDtos);
        }
        return null;
    }


    @Override
    public BigDecimal getFare(FareVo fareVo) {
         BigDecimal fare = templatesService.handlePostage(fareVo.getItems(),fareVo.getAddress());
         return fare;
    }


    @Override
    public Order getOrderByNo(String orderNo) {
        return orderMapper.selectOrderByNo(orderNo);
    }

    /**
     * 计算商品折扣后的总价
     *
     * @param orderItems 订单商品列表
     * @return 商品总价
     */
    private void calculateOrderTotalPrice(Order order, List<OrderItem> orderItems) {

        //商品折扣前总价
        BigDecimal goodsPrice = new BigDecimal("0");
        //商品折扣后总价
        BigDecimal actualPayment = new BigDecimal("0");
        //订单折扣前总价
        BigDecimal totalAmount = new BigDecimal("0");


        for (OrderItem orderItem : orderItems) {
            //商品单价*商品数量（优惠前）
            goodsPrice = goodsPrice.add(orderItem.getGoodsPrice().multiply(new BigDecimal(orderItem.getGoodsCount())));
            //订单总价（优惠前）
            totalAmount = totalAmount.add(goodsPrice);
            //实际支付金额（减去店铺和平台优惠）
            actualPayment = actualPayment.add(orderItem.getGoodsPrice().multiply(new BigDecimal(orderItem.getGoodsCount())).subtract(orderItem.getGoodsShopDiscountPrice()).subtract(orderItem.getGoodsPlatformDiscountPrice()));
        }

        if (order.getOrderType() == 3) { //竞拍商品
            Auctionmember auctionmember = auctionmemberService.listAuctionMerberMax(orderItems.get(0).getGoodsIdP());
            if (auctionmember != null) {
                order.setGoodsTotalPrice(auctionmember.getNowPrice());
                order.setOrderTotalPrice(auctionmember.getNowPrice());
                //设置优惠后用户需要支付的实际款项
                order.setActualPayment(auctionmember.getNowPrice());
            }
        } else {
            order.setGoodsTotalPrice(goodsPrice);
            order.setOrderTotalPrice(totalAmount);
            //设置优惠后用户需要支付的实际款项+

            order.setActualPayment(actualPayment);
        }


    }

    /**
     * 数据处理
     *
     * @param list
     * @return
     */
    Map dataProcessing(List<OrderCountDto> list) {
        List listDate = new ArrayList();
        List listCount = new ArrayList();
        for (OrderCountDto orderCountDto : list) {
            listDate.add(orderCountDto.getDateFomat());
            listCount.add(orderCountDto.getOrderCount());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("listDate", listDate);
        map.put("listCount", listCount);
        return map;

    }
}


