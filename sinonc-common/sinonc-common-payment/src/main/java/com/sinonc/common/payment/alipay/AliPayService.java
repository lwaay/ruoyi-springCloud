package com.sinonc.common.payment.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.SpringUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;
import com.sinonc.common.payment.configuraion.properties.AliPayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AliPayService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AliPayProperties properties;

    private List<PayObserver> payObservers = new LinkedList<>();

    private List<RefundObserver> refundObservers = new LinkedList<>();

    /**
     * 初始化时，获取所有实现了PayObserver接口的Bean并进行注册
     */
    @PostConstruct
    public void init() {

        Map<String, PayObserver> beans = SpringUtils.getBeansOfType(PayObserver.class);
        Set<String> keys = beans.keySet();
        for (String key : keys) {
            payObservers.add(beans.get(key));
        }

        Map<String, RefundObserver> refunds = SpringUtils.getBeansOfType(RefundObserver.class);
        Set<String> rfKeys = refunds.keySet();
        for (String rfKey : rfKeys) {
            refundObservers.add(refunds.get(rfKey));
        }
    }

    /**
     * 创建支付宝APP支付响应数据,用于前端APP调用支付宝客户端sdk进行支付
     *
     * @param model 支付宝app支付业务模型 示例：https://docs.open.alipay.com/54/106370/
     * @return
     */
    public String createAppPayResponse(AlipayTradeAppPayModel model) throws AlipayApiException {

        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setReturnUrl(properties.getReturnUrl());
        request.setNotifyUrl(properties.getNotifyUrl());

        return alipayClient.sdkExecute(request).getBody();
    }

    /**
     * 支付宝统一退款服务
     *
     * @param orderNo 订单号
     * @param price   金额
     * @return 结果
     */
    public AlipayTradeRefundResponse tradeRefund(@NotEmpty String orderNo, String refundNo, BigDecimal price, @NotEmpty String refundReason, String params) throws Exception {


        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setRefundAmount(price.toString());
        model.setOutTradeNo(orderNo);
        model.setRefundReason(refundReason);
        model.setOutRequestNo(refundNo);

        AlipayTradeRefundRequest refundRequest = new AlipayTradeRefundRequest();
        refundRequest.setBizModel(model);

        AlipayTradeRefundResponse refundResponse = alipayClient.execute(refundRequest);

        //判断业务状态
        if (!refundResponse.isSuccess()) {
            throw new Exception(StringUtils.format("退款异常：{} {}", refundResponse.getSubCode(), refundResponse.getSubMsg()));
        }


        RefundMessage message = new RefundMessage();

        message.setOutTradeNo(refundResponse.getTradeNo());
        message.setPayType(1);
        message.setRefundReason(refundReason);
        message.setTradeResult(refundResponse.getMsg());
        message.setOrderNo(refundResponse.getOutTradeNo());
        //退款金额
        message.setRefundAmount(refundResponse.getRefundFee());
        //实际退款金额
        message.setReceiptAmount(refundResponse.getRefundFee());
        //平台退还的手续费
        message.setRefundFee("0.00");
        message.setParams(params);

        this.refundNotify(message);

        return refundResponse;
    }

    /**
     * 支付宝参数签名验证
     *
     * @param params 支付宝请求参数
     * @return
     * @throws AlipayApiException
     */
    public boolean signature(Map<String, String> params) throws AlipayApiException {
        params.remove("sign_type");
        return AlipaySignature.rsaCheckV2(params, properties.getPublicKey(), properties.getCharset(), properties.getSignType());
    }

    /**
     * 向订阅者发送支付宝异步通知消息
     *
     * @param params 支付宝提交的异步请求参数 详见：https://docs.open.alipay.com/204/105301/
     */
    public void aliPayNotify(Map<String, String> params) throws Exception {
        log.info("支付宝异步通知：{}", params);

        boolean signature = this.signature(params);
        //验证通知签名
        if (!signature) {
            log.info("签名错误:{}", params);
            throw new BusinessException("签名错误");
        }

        //判断是否为重复通知
        String notifyId = stringRedisTemplate.opsForValue().get("ALIPAY_NOTIFY_" + params.get("notify_id"));

        if (notifyId != null) {
            log.info("回调ID重复:{}", notifyId);
            return;
        }

        stringRedisTemplate.opsForValue().set("ALIPAY_NOTIFY_" + params.get("notify_id"), params.toString(), 25, TimeUnit.HOURS);

        PayMessage payMessage = new PayMessage();

        //商户订单号
        payMessage.setOrderNo(params.get("out_trade_no"));
        //支付宝交易号
        payMessage.setOutTrade(params.get("trade_no"));
        //交易状态
        payMessage.setTradeResult(params.get("trade_status"));
        //卖家支付宝账号的PID
        payMessage.setSellerId(params.get("seller_id"));
        payMessage.setPayType(1);
        //公共回传参数，如果请求时传递了该参数，则返回给商家时会在异步通知中原样传回该参数。本参数必须进行UrlEncode之后才可传入。
        payMessage.setParams(params.get("passback_params"));
        //本次交易支付的订金金额，单位为人民币（元）
        payMessage.setAmount(params.get("total_amount"));

        BigDecimal amount = new BigDecimal(payMessage.getAmount());

        //判断是否为付款交易
        if (!"TRADE_SUCCESS".equals(payMessage.getTradeResult())) {
            log.info("非付款交易：{}", params);
            return;
        }

        //计算平台交易费
        BigDecimal tradeFee = amount.multiply(new BigDecimal(properties.getTradeRate())).setScale(2, RoundingMode.HALF_UP);
        payMessage.setTradeFee(tradeFee.toString());

        //计算实收款 （减去平台手续费）
        BigDecimal receiptAmount = amount.subtract(tradeFee);
        payMessage.setReceiptAmount(receiptAmount.toString());


        payMessage.setBuyerId(params.get("buyer_id"));
        payMessage.setAppId("app_id");


        for (PayObserver observer : payObservers) {

            try {
                observer.payNotify(payMessage);
            } catch (Exception e) {
                log.error("订阅者接受消息异常：\n" + e.getMessage());
            }

        }
    }

    /**
     * 向订阅者发送支付宝退款通知
     */
    private void refundNotify(RefundMessage refundMessage) {

        for (RefundObserver refundObserver : refundObservers) {
            try {
                refundObserver.refundNotify(refundMessage);
            } catch (Exception e) {
                log.error("订阅者退款通知异常：", e);
            }
        }
    }

}
