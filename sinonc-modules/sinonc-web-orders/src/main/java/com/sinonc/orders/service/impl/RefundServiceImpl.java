package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.payment.alipay.AliPayService;
import com.sinonc.common.payment.wechat.WechatPayService;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.Refund;
import com.sinonc.orders.domain.RefundLog;
import com.sinonc.orders.domain.TradeInfo;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.mapper.RefundLogMapper;
import com.sinonc.orders.mapper.RefundMapper;
import com.sinonc.orders.mapper.TradeInfoMapper;
import com.sinonc.orders.service.RefundService;
import com.sinonc.orders.utils.OrderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 退款申请 服务层实现
 *
 * @author sinonc
 * @date 2019-11-11
 */

@Slf4j
@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private RefundLogMapper refundLogMapper;

    @Autowired
    private TradeInfoMapper tradeInfoMapper;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;

    /**
     * 查询退款申请信息
     *
     * @param rfId 退款申请ID
     * @return 退款申请信息
     */
    @Override
    public Refund getRefundById(Long rfId) {
        return refundMapper.selectRefundById(rfId);
    }

    /**
     * 查询退款申请列表
     *
     * @param refund 退款申请信息
     * @return 退款申请集合
     */
    @Override
    public List<Refund> listRefund(Refund refund) {
        return refundMapper.selectRefundList(refund);
    }

    /**
     * 新增退款申请
     *
     * @param refund 退款申请信息
     * @return 结果
     */
    @Override
    @Transactional
    public int addRefund(Refund refund) {

        Refund rf = refundMapper.selectRefundByOrderNo(refund.getOrderNo());

        if (rf == null) {
            refundMapper.insertRefund(refund);
        } else {
            rf.setRfStatus(0);
            rf.setUpdateTime(new Date());
            refundMapper.updateRefund(rf);
        }

        RefundLog refundLog = new RefundLog();
        refundLog.setCreateTime(refund.getCreateTime());
        refundLog.setRfId(refund.getRfId());
        refundLog.setContent("买家发起退款");

        refundLogMapper.insertRefundLog(refundLog);

        Order order = new Order();
        order.setOrderId(refund.getOrderId());
        order.setRefundStatus(1);

        return orderMapper.updateOrder(order);
    }

    /**
     * 修改退款申请
     *
     * @param refund 退款申请信息
     * @return 结果
     */
    @Override
    public int updateRefund(Refund refund) {
        return refundMapper.updateRefund(refund);
    }

    /**
     * 删除退款申请对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRefundByIds(String ids) {
        return refundMapper.deleteRefundByIds(Convert.toStrArray(ids));
    }


    @Override
    @Transactional
    public void refund(String orderNo, BigDecimal refundAmount, String reason, String confirmBy) throws Exception {

        //查询付款时的交易信息
        TradeInfo payTradeInfo = tradeInfoMapper.selectPayTradeByOrderNo(orderNo);

        if (payTradeInfo == null) {
            throw new Exception("订单支付信息不存在，请核对后重试");
        }

        //查询退款申请记录
        Refund refund = refundMapper.selectRefundByOrderNo(orderNo);

        Date date = new Date();

        //如果是买家发起退款，则会存在退款申请，如果是卖家或者系统发起退款，可能会不存退款申请，
        // 若不存在则创建一个退款申请，并直接同意
        if (refund == null) {

            refund = new Refund();

            refund.setCreateTime(date);
            refund.setMemberId(payTradeInfo.getMemberId());
            refund.setOrderId(payTradeInfo.getOrderIdP());
            refund.setConfirmTime(date);
            refund.setUpdateTime(date);
            refund.setOrderAmount(payTradeInfo.getTotalAmount());
            refund.setOrderNo(payTradeInfo.getOrderNo());
            refund.setRfAmount(refundAmount);
            refund.setRfReason("卖家发起退款");
            refund.setRfDescribe(reason);
            refund.setRfStatus(1);
            refund.setShopId(payTradeInfo.getShopIdP());
            refund.setTradeId(payTradeInfo.getTradeId());
            refund.setConfirmBy(confirmBy);
            refund.setRefundNo("TK" + OrderUtil.createOrderNo());

            //插入退款记录
            refundMapper.insertRefund(refund);

            RefundLog refundLog = new RefundLog();
            refundLog.setRfId(refund.getRfId());
            refundLog.setContent("卖家主动发起并同意退款请求");
            refundLog.setCreateTime(date);

            //插入退款日志
            refundLogMapper.insertRefundLog(refundLog);

        } else {

            refund.setConfirmTime(date);
            refund.setRfStatus(1);
            refund.setUpdateTime(date);
            refund.setRefundNo("TK" + OrderUtil.createOrderNo());

            refundMapper.updateRefund(refund);

            RefundLog refundLog = new RefundLog();
            refundLog.setRfId(refund.getRfId());
            refundLog.setContent("卖家同意退款请求");
            refundLog.setCreateTime(date);

            //插入退款日志
            refundLogMapper.insertRefundLog(refundLog);
        }

        Integer payType = payTradeInfo.getPayType();

        if (refundAmount.compareTo(payTradeInfo.getTotalAmount()) != 0) {
            throw new RuntimeException("退款金额与订单金额不一致");
        }

        if (payType == 1) {
            //调用支付宝退款
            aliPayService.tradeRefund(orderNo, refund.getRefundNo(), refundAmount, reason, payTradeInfo.getShopIdP().toString());
        } else {
            //调用微信退款
            wechatPayService.tradeRefund(orderNo, refund.getRefundNo(), payTradeInfo.getTotalAmount(), refundAmount, reason, payTradeInfo.getShopIdP().toString());
        }

    }

    @Override
    public int operate(Long rfId, String content, BigDecimal amount, Integer rfStatus, String confirmBy) throws Exception {

        Date date = new Date();

        Refund refund = refundMapper.selectRefundById(rfId);

        if (refund == null) {
            throw new Exception("退款申请不存在");
        }

        if (refund.getRfStatus() == 1) {
            throw new Exception("已同意订单不可重复提交");
        }

        //卖家拒绝
        if (rfStatus == 2) {

            refund.setRfAmount(amount);
            refund.setRfStatus(rfStatus);
            refund.setConfirmTime(date);
            refund.setConfirmBy(confirmBy);
            refund.setUpdateTime(date);

            //更新退款申请
            refundMapper.updateRefund(refund);

            //更新订单状态
            Order order = new Order();
            order.setOrderId(refund.getOrderId());
            order.setRefundStatus(3);
            orderMapper.updateOrder(order);

            //添加退款日志
            RefundLog refundLog = new RefundLog();
            refundLog.setRfId(refund.getRfId());
            refundLog.setContent("卖家拒绝退款：" + content);
            refundLog.setCreateTime(date);

            return refundLogMapper.insertRefundLog(refundLog);

            //同意退款
        } else if (rfStatus == 1) {
            RefundService refundService = (RefundService) AopContext.currentProxy();
            refundService.refund(refund.getOrderNo(), amount, content, confirmBy);
        }

        return 0;
    }

}
