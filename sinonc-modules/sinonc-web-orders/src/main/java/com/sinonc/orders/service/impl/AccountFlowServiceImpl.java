package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;
import com.sinonc.orders.common.TradeCodeConstant;
import com.sinonc.orders.domain.Account;
import com.sinonc.orders.domain.AccountFlow;
import com.sinonc.orders.mapper.AccountFlowMapper;
import com.sinonc.orders.mapper.AccountMapper;
import com.sinonc.orders.service.AccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 账户流水 服务层实现
 *
 * @author sinonc
 * @date 2019-10-29
 */
@Service
public class AccountFlowServiceImpl implements AccountFlowService, PayObserver, RefundObserver {

    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 查询账户流水信息
     *
     * @param acctFlowId 账户流水ID
     * @return 账户流水信息
     */
    @Override
    public AccountFlow getAccountFlowById(Integer acctFlowId) {
        return accountFlowMapper.selectAccountFlowById(acctFlowId);
    }

    /**
     * 查询账户流水列表
     *
     * @param accountFlow 账户流水信息
     * @return 账户流水集合
     */
    @Override
    public List<AccountFlow> listAccountFlow(AccountFlow accountFlow) {
        return accountFlowMapper.selectAccountFlowList(accountFlow);
    }

    /**
     * 新增账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
    @Override
    public int addAccountFlow(AccountFlow accountFlow) {
        return accountFlowMapper.insertAccountFlow(accountFlow);
    }

    /**
     * 修改账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
    @Override
    public int updateAccountFlow(AccountFlow accountFlow) {
        return accountFlowMapper.updateAccountFlow(accountFlow);
    }

    /**
     * 删除账户流水对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccountFlowByIds(String ids) {
        return accountFlowMapper.deleteAccountFlowByIds(Convert.toStrArray(ids));
    }


    /**
     * 支付通知
     *
     * @param message 通知消息体
     * @throws Exception 异常
     */
    @Override
    @Transactional
    public void payNotify(PayMessage message) throws Exception {

        Date date = new Date();

        BigDecimal zero = new BigDecimal("0.00");
        //交易金额
        BigDecimal totalAmount = new BigDecimal(message.getAmount());
        //实际收入（减扣平台交易费）
        BigDecimal receiptAmount = new BigDecimal(message.getReceiptAmount());
        //从回传订单中获取店铺ID
        Long shopId = Long.valueOf(message.getParams());
        //获取订单号
        String orderNo = message.getOrderNo();

        //获取店铺账户信息
        Account account = accountMapper.selectAccountByShopIdForUpdate(shopId);

        //收入流水
        AccountFlow inFlow = new AccountFlow();

        inFlow.setShopIdP(shopId);
        inFlow.setAcctIdP(account.getAcctId());
        inFlow.setInAmount(totalAmount);
        inFlow.setOpAmount(totalAmount);
        inFlow.setOutAmount(zero);
        inFlow.setOpType(0);
        inFlow.setOrderNo(orderNo);
        inFlow.setPrevAmount(account.getAcctAmount());
        inFlow.setRemark(StringUtils.format("订单：{} 收入", orderNo));
        inFlow.setTradeTime(date);
        inFlow.setTradeCode(TradeCodeConstant.ORDER_IN);

        //判断是否为预订单
        if (orderNo.contains("WK")) {
            inFlow.setRemark(StringUtils.format("订单：{} 尾款收入", orderNo.substring(2)));
        } else if (orderNo.contains("DJ")) {
            inFlow.setRemark(StringUtils.format("订单：{} 定金收入", orderNo.substring(2)));
        }

        //判断是否为竞拍押金
        if (orderNo.contains("JP")) {
            inFlow.setRemark(StringUtils.format("订单：{} 竞拍押金收入", orderNo));
        }

        //获取平台交易费
        BigDecimal free = new BigDecimal(message.getTradeFee());

        //写入收入流水
        accountFlowMapper.insertAccountFlow(inFlow);


        //判断是否需要将交易费写入账户流水
        if (free.compareTo(zero) != 0) {

            //支出流水
            AccountFlow outFlow = new AccountFlow();
            outFlow.setOutAmount(free);
            outFlow.setOpAmount(free);
            outFlow.setOrderNo(orderNo);

            if (message.getPayType() == 0) {
                outFlow.setRemark(StringUtils.format("订单：{} 微信交易手续费", orderNo));
            } else if (message.getPayType() == 1) {
                outFlow.setRemark(StringUtils.format("订单：{} 支付宝交易手续费", orderNo));
            }

            outFlow.setInAmount(zero);
            outFlow.setAcctIdP(account.getAcctId());
            outFlow.setOpType(1);
            outFlow.setTradeTime(date);
            outFlow.setShopIdP(shopId);
            outFlow.setPrevAmount(account.getAcctAmount().add(inFlow.getInAmount()));
            outFlow.setTradeCode(TradeCodeConstant.TRADE_FEE_OUT);

            //写入支出流水
            accountFlowMapper.insertAccountFlow(outFlow);
        }


        //更新店铺账户
        account.setAcctAmount(account.getAcctAmount().add(receiptAmount));
        account.setAcctLock(account.getAcctLock().add(receiptAmount));
        account.setIncomeAmount(account.getIncomeAmount().add(totalAmount));
        account.setOutAmount(account.getOutAmount().add(free));
        account.setUpdateTime(date);

        accountMapper.updateAccount(account);
    }

    /**
     * 退款通知
     *
     * @param message 通知
     * @throws Exception
     */
    @Override
    public void refundNotify(RefundMessage message) throws Exception {

        BigDecimal zero = new BigDecimal("0.00");
        BigDecimal refundAmount = new BigDecimal(message.getRefundAmount());
        BigDecimal receiptAmount = new BigDecimal(message.getReceiptAmount());
        BigDecimal refundFee = zero;

        Date date = new Date();

        Long shopId = Long.valueOf(message.getParams());

        //查询账户余额
        Account account = accountMapper.selectAccountByShopIdForUpdate(shopId);

        //支出流水
        AccountFlow outFlow = new AccountFlow();

        outFlow.setShopIdP(shopId);
        outFlow.setInAmount(zero);
        outFlow.setAcctIdP(account.getAcctId());
        outFlow.setOpAmount(refundAmount);
        outFlow.setOutAmount(refundAmount);
        outFlow.setOpType(AccountFlow.OP_TYPE_OUT);
        outFlow.setOrderNo(message.getOrderNo());
        outFlow.setPrevAmount(account.getAcctAmount());
        outFlow.setRemark(StringUtils.format("订单：{} 退款", message.getOrderNo()));
        outFlow.setTradeTime(date);
        outFlow.setTradeCode(TradeCodeConstant.ORDER_OUT);

        //写入支出流水
        accountFlowMapper.insertAccountFlow(outFlow);

        //微信退款，需退还交易手续费
        if (message.getPayType() == 0 && !message.getRefundFee().equals("0.00")) {

            refundFee = new BigDecimal(message.getRefundFee());

            AccountFlow inFlow = new AccountFlow();

            inFlow.setShopIdP(shopId);
            inFlow.setInAmount(refundFee);
            inFlow.setAcctIdP(account.getAcctId());
            inFlow.setOpAmount(refundFee);
            inFlow.setOutAmount(zero);
            inFlow.setOpType(AccountFlow.OP_TYPE_IN);
            inFlow.setOrderNo(message.getOrderNo());
            inFlow.setPrevAmount(account.getAcctAmount().subtract(refundAmount));
            inFlow.setRemark(StringUtils.format("订单：{} 退款 微信手续费退还", message.getOrderNo()));
            inFlow.setTradeTime(date);
            inFlow.setTradeCode(TradeCodeConstant.TRADE_FEE_IN);

            accountFlowMapper.insertAccountFlow(inFlow);
        }


        //退款更新账户
        account.setAcctAmount(account.getAcctAmount().subtract(receiptAmount));
        account.setAcctLock(account.getAcctLock().subtract(receiptAmount));
        account.setIncomeAmount(account.getIncomeAmount().add(refundFee));
        account.setOutAmount(account.getOutAmount().add(refundAmount));
        account.setUpdateTime(date);

        accountMapper.updateAccount(account);
    }
}
