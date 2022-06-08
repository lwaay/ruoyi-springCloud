package com.sinonc.orders.service.impl;

import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.common.TradeCodeConstant;
import com.sinonc.orders.domain.Account;
import com.sinonc.orders.domain.AccountFlow;
import com.sinonc.orders.domain.BookOrder;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.dto.SettlementDto;
import com.sinonc.orders.mapper.AccountFlowMapper;
import com.sinonc.orders.mapper.AccountMapper;
import com.sinonc.orders.mapper.BookOrderMapper;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Autowired
    private BookOrderMapper bookOrderMapper;

    /**
     * 直购订单结算（定时任务调用）
     *
     * @return 结算账户数
     */
    @Override
    public int tradeOrderSettlement() {

        int i = 0;

        //查询所有已经收货的订单
        Order order = new Order();
        order.setOrderType(OrderConstant.TYPE_TRADE);
        order.setOrderStatus(OrderConstant.STATUS_TRADE_SUCCESSFUL);
        order.setSettlement(OrderConstant.UN_SETTLEMENT);

        List<SettlementDto<Order>> orders = orderMapper.selectSettlementDto(order);
        //根据店铺对订单进行分组
        Map<Long, List<SettlementDto<Order>>> orderMap = new LinkedHashMap<>();

        for (SettlementDto<Order> od : orders) {
            List<SettlementDto<Order>> orderList = orderMap.computeIfAbsent(od.getTarget().getShopIdP(), k -> new LinkedList<>());
            orderList.add(od);
        }

        //获取当前类的代理对象
        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        //进行订单结算
        for (Long shopId : orderMap.keySet()) {
            try {
                i += currentProxy.settlement(shopId, orderMap.get(shopId));
            } catch (Exception e) {
                log.error("\n订单结算异常:\n", e);
            }
        }

        return i;
    }

    /**
     * 预订订单结算（定时任务调用）
     *
     * @return 结果
     */
    @Override
    public int bookOrderSettlement() {

        int i = 0;
        //查询所有已经收货的预订订单
        Order params = new Order();
        params.setOrderType(OrderConstant.TYPE_BOOK);
        params.setOrderStatus(OrderConstant.STATUS_TRADE_SUCCESSFUL);
        params.setSettlement(OrderConstant.UN_SETTLEMENT);

        List<SettlementDto<Order>> orders = orderMapper.selectSettlementDto(params);

        //根据店铺对订单进行分组
        Map<Long, List<SettlementDto<Order>>> orderMap = new LinkedHashMap<>();

        for (SettlementDto<Order> order : orders) {
            List<SettlementDto<Order>> orderList = orderMap.computeIfAbsent(order.getTarget().getShopIdP(), f -> new LinkedList<>());
            orderList.add(order);
        }

        //获取当前类的代理对象
        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        //进行订单结算
        for (Long shopId : orderMap.keySet()) {
            try {
                i += currentProxy.bookSettlement(shopId, orderMap.get(shopId));
            } catch (Exception e) {
                log.error("\n订单结算异常:\n", e);
            }
        }

        return i;
    }

    /**
     * 定金结算（定时任务调用）
     *
     * @return
     */
    @Override
    public int bookEarnestSettlement() {

        int i = 0;

        BookOrder params = new BookOrder();
        params.setSettlement(0);
        params.setTradeStatus(BookOrder.TRADE_STATUS_PAY_EARNEST);

        List<SettlementDto<BookOrder>> bookOrders = bookOrderMapper.selectSettlementDto(params);

        //根据店铺对定金进行分组
        Map<Long, List<SettlementDto<BookOrder>>> orderMap = new LinkedHashMap<>();

        for (SettlementDto<BookOrder> bookOrder : bookOrders) {
            List<SettlementDto<BookOrder>> bookOrderList = orderMap.computeIfAbsent(bookOrder.getTarget().getShopId(), f -> new LinkedList<>());
            bookOrderList.add(bookOrder);
        }

        //获取当前类的代理对象
        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        //进行定金结算
        for (Long shopId : orderMap.keySet()) {
            try {
                i += currentProxy.earnestSettlement(shopId, orderMap.get(shopId));
            } catch (Exception e) {
                log.error("\n定金结算异常:\n", e);
            }
        }

        return i;
    }

    /**
     * 认养订单结算（定时任务调用）
     *
     * @return
     */
    @Override
    public int adoptOrderSettlement(Integer settlement) {

        int i = 0;

        Order params = new Order();
        params.setOrderType(OrderConstant.TYPE_ADOPT);
        params.setTradeStatus(Order.TRADE_STATUS_PAYED);

        if (settlement == 2) {
            params.setOrderStatus(OrderConstant.STATUS_TRADE_SUCCESSFUL);
        }

        params.setSettlement(settlement);

        List<SettlementDto<Order>> orderList = orderMapper.selectSettlementDto(params);
        Map<Long, List<SettlementDto<Order>>> adoptMap = new HashMap<>();

        //分组处理
        for (SettlementDto<Order> order : orderList) {
            List<SettlementDto<Order>> orders = adoptMap.computeIfAbsent(order.getTarget().getShopIdP(), f -> new LinkedList<>());
            orders.add(order);
        }

        //获取当前类的代理对象
        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        for (Long shopId : adoptMap.keySet()) {
            try {
                i += currentProxy.adoptSettlement(shopId, adoptMap.get(shopId));
            } catch (Exception e) {
                log.error("\n认养订单结算异常:\n", e);
            }
        }

        return i;
    }

    /**
     * 认养订单结算操作
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int adoptSettlement(Long shopId, List<SettlementDto<Order>> orderList) {

        Date date = new Date();

        BigDecimal totalAmount = new BigDecimal("0.00");

        for (SettlementDto<Order> order : orderList) {

            //判断订单状态，确定结算比例
            if (order.getTarget().getSettlement().equals(0)) {

                //结算30%
                BigDecimal settlementAmount = order.getReceiptAmount().multiply(new BigDecimal("0.3")).setScale(2, BigDecimal.ROUND_FLOOR);
                totalAmount = totalAmount.add(settlementAmount);

                Order update = new Order();
                update.setOrderId(order.getTarget().getOrderId());
                update.setSettlement(2);
                update.setSettlementAmount(settlementAmount);
                update.setSettlementTime(date);

                orderMapper.updateOrder(update);

            } else if (order.getTarget().getSettlement().equals(2)) {

                //结算剩余70%
                BigDecimal settlementAmount = order.getReceiptAmount().subtract(order.getTarget().getSettlementAmount());
                totalAmount = totalAmount.add(settlementAmount);

                Order update = new Order();
                update.setOrderId(order.getTarget().getOrderId());
                update.setSettlement(1);
                update.setSettlementAmount(order.getReceiptAmount());
                update.setSettlementTime(date);

                orderMapper.updateOrder(update);
            } else {
                throw new RuntimeException("订单【" + order.getTarget().getOrderNo() + "】重复结算");
            }

        }

        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        return currentProxy.updateAccount(shopId, totalAmount, "认养订单结算");
    }

    /**
     * 定金结算操作
     * 采用新建事物，防止悲观锁被重复占用
     *
     * @param shopId     店铺id
     * @param bookOrders 预订定金单
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int earnestSettlement(Long shopId, List<SettlementDto<BookOrder>> bookOrders) {

        BigDecimal totalAmount = new BigDecimal("0.00");

        for (SettlementDto<BookOrder> bookOrder : bookOrders) {

            //计算50%定金
            BigDecimal settlementAmount = bookOrder.getReceiptAmount().multiply(new BigDecimal("0.5")).setScale(2, BigDecimal.ROUND_FLOOR);

            BookOrder update = new BookOrder();
            update.setBoId(bookOrder.getTarget().getBoId());
            update.setSettlement(2);
            update.setSettlementAmount(settlementAmount);
            update.setSettlementTime(new Date());

            totalAmount = totalAmount.add(settlementAmount);

            bookOrderMapper.updateBookOrder(update);
        }

        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();
        return currentProxy.updateAccount(shopId, totalAmount, "定金结算");

    }


    /**
     * 直购订单结算操作
     * <p>
     * 更新账户金额，采用独立事务，防止悲观锁，被长时间占用
     *
     * @param shopId    店铺ID
     * @param orderList 订单列表
     * @return 影响的行数
     */
    @Transactional
    public int settlement(Long shopId, List<SettlementDto<Order>> orderList) {

        BigDecimal amount = new BigDecimal("0.00");

        //计算结算的总金额
        for (SettlementDto<Order> od : orderList) {
            amount = amount.add(od.getReceiptAmount());

            Order update = new Order();
            update.setOrderId(od.getTarget().getOrderId());
            update.setSettlement(1);
            update.setSettlementAmount(od.getReceiptAmount());
            update.setSettlementTime(new Date());

            //更新订单结算状态
            orderMapper.updateOrder(update);
        }

        //获取当前类的代理对象
        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        //更新账户金额
        return currentProxy.updateAccount(shopId, amount, "订单结算");
    }

    /**
     * 更新账户金额操作
     *
     * @param shopId 店铺id
     * @param amount 金额
     * @return 影响数
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateAccount(Long shopId, BigDecimal amount, String remark) {

        Date date = new Date();

        //查询账户信息
        Account account = accountMapper.selectAccountByShopIdForUpdate(shopId);


        //转出流水
        AccountFlow outFlow = new AccountFlow();
        outFlow.setAcctIdP(account.getAcctId());
        outFlow.setOpType(AccountFlow.OP_TYPE_OUT);
        outFlow.setOpAmount(amount);
        outFlow.setPrevAmount(account.getAcctAmount());
        outFlow.setOutAmount(amount);
        outFlow.setTradeCode(TradeCodeConstant.ACCOUNT_SETTLE_OUT);
        outFlow.setRemark(remark + ",冻结金额转出");
        outFlow.setShopIdP(account.getShopIdP());
        outFlow.setTradeTime(date);
        outFlow.setOrderNo("-");

        accountFlowMapper.insertAccountFlow(outFlow);

        //转入流水
        AccountFlow inFlow = new AccountFlow();
        inFlow.setAcctIdP(account.getAcctId());
        inFlow.setOpType(AccountFlow.OP_TYPE_IN);
        inFlow.setOpAmount(amount);
        inFlow.setPrevAmount(account.getAcctAmount().subtract(amount));
        inFlow.setInAmount(amount);
        inFlow.setTradeCode(TradeCodeConstant.ACCOUNT_SETTLE_IN);
        inFlow.setRemark(remark + ",可用金额转入");
        inFlow.setShopIdP(account.getShopIdP());
        inFlow.setTradeTime(date);
        inFlow.setOrderNo("-");

        accountFlowMapper.insertAccountFlow(inFlow);

        //更新账户
        BigDecimal acctLock = account.getAcctLock().subtract(amount);
        BigDecimal acctUsable = account.getAcctUsable().add(amount);
        account.setAcctLock(acctLock);
        account.setAcctUsable(acctUsable);
        account.setUpdateTime(date);

        return accountMapper.updateAccount(account);
    }

    /**
     * 预订单确认收货后结算操作
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int bookSettlement(Long shopId, List<SettlementDto<Order>> orderList) {

        Date date = new Date();

        //结算的订单总金额
        BigDecimal orderTotal = new BigDecimal("0.00");
        //结算的定金总金额
        BigDecimal earnestTotal = new BigDecimal("0.00");

        //订单id列表
        ArrayList<Long> ids = new ArrayList<>(orderList.size());

        for (SettlementDto<Order> order : orderList) {
            ids.add(order.getTarget().getOrderId());
            orderTotal = orderTotal.add(order.getReceiptAmount());
        }

        //查询需要进行结算的订单的对应定金信息
        List<SettlementDto<BookOrder>> bookOrders = bookOrderMapper.selectSettlementByOrderIds(ids);

        //计算总结算定金
        for (SettlementDto<BookOrder> bookOrder : bookOrders) {

            earnestTotal = earnestTotal.add(bookOrder.getReceiptAmount().subtract(bookOrder.getTarget().getSettlementAmount()));

            BookOrder update = new BookOrder();
            update.setBoId(bookOrder.getTarget().getBoId());
            update.setSettlement(1);
            update.setSettlementTime(date);
            update.setSettlementAmount(bookOrder.getTarget().getEarnestPrice());

            //更新定金结算状态
            bookOrderMapper.updateBookOrder(update);
        }

        SettlementServiceImpl currentProxy = (SettlementServiceImpl) AopContext.currentProxy();

        //将订单状态更改为已结算
        orderMapper.updateSettlement(1, ids);

        //更新账户金额
        return currentProxy.updateAccount(shopId, earnestTotal.add(orderTotal), "预订订单结算");
    }

}
