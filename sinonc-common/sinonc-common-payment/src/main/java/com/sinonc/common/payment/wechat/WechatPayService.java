package com.sinonc.common.payment.wechat;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.AES256Util;
import com.sinonc.common.core.utils.SpringUtils;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;

import com.sinonc.common.payment.configuraion.properties.WechatPayProperties;
import com.sinonc.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.rmi.Remote;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 微信支付处理
 */
@Slf4j
@Service
public class WechatPayService {

    @Autowired
    private WechatPayProperties properties;

    @Autowired
    private WXPayRequest wxPayRequest;

    @Autowired
    private RedisService stringRedisTemplate;

    //微信小程序相关配置
    @Value("${wxxcx.appId}")
    private String appId;

    @Value("${wxxcx.appSecret}")
    private String appSecret;

    @Value("${wxxcx.grantType}")
    private String grantType;

    private List<PayObserver> payObservers = new LinkedList<>();

    private List<RefundObserver> refundObservers = new LinkedList<>();


    /**
     * 初始化时，获取所有实现了WechatPayObserver接口的Bean并进行注册
     */
    @PostConstruct
    public void init() {
        Map<String, PayObserver> beans = SpringUtils.getBeansOfType(PayObserver.class);
        Set<String> keys = beans.keySet();
        for (String key : keys) {
            log.error("keyName:{}", key);
            payObservers.add(beans.get(key));
        }

        Map<String, RefundObserver> refunds = SpringUtils.getBeansOfType(RefundObserver.class);
        Set<String> rfKeys = refunds.keySet();
        for (String rfKey : rfKeys) {
            refundObservers.add(refunds.get(rfKey));
        }
    }

    /**
     * 服务后台生成微信预支付交易单，用于前端App调用
     * 仅限APP支付使用
     *
     * @param model 微信支付交易订单参数模型
     * @return 订单交易参数xml字符串
     * @throws JsonProcessingException 转换xml异常
     */
    @SuppressWarnings("unchecked")
    public WechatAppResp createPayResponse(WechatPayTradeModel model) throws Exception {

        //构建支付请求参数
        WechatXcxPayRequestModel requestModel = new WechatXcxPayRequestModel();

        String nonceStr = RandomStringUtils.random(15, "abcdefgABCDEFG123456789");

        requestModel.setAppid(properties.getAppid());
        requestModel.setMch_id(properties.getMchId());
        requestModel.setNonce_str(nonceStr);
        requestModel.setBody(properties.getName() + "-" + model.getBody());
        requestModel.setNotify_url(properties.getNotifyUrl());
        InetAddress addr = InetAddress.getLocalHost();
        requestModel.setSpbill_create_ip(addr.getHostAddress());
        requestModel.setOut_trade_no(model.getOut_trade_no());
        requestModel.setTotal_fee(model.getTotal_fee());
        requestModel.setAttach(model.getAttach());
        requestModel.setTrade_type("JSAPI");
        requestModel.setOpenid(model.getOpenId());

        //将参数进行字典排序
        Map<String, String> describe = BeanUtils.describe(requestModel);
        requestModel.setSign(getSign(describe));


        //调用统一下单获取的prepay_id
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<WechatPayRequestModel> httpEntity = new HttpEntity<>(requestModel, headers);
        RestTemplate restTemplate = new RestTemplate();
        String respXml = restTemplate.postForObject(properties.getUrl(), httpEntity, String.class);

        respXml = new String(respXml.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        XmlMapper xmlMapper = new XmlMapper();
        WechatPayResponseModel wechatPayResponseModel = xmlMapper.readValue(respXml, WechatPayResponseModel.class);

        //判断请求状态
        if (!"SUCCESS".equals(wechatPayResponseModel.getReturnCode())) {
            throw new BusinessException(wechatPayResponseModel.getReturnMsg());
        }

        if (!"SUCCESS".equals(wechatPayResponseModel.getResultCode())) {
            throw new BusinessException(wechatPayResponseModel.getErrCodeDes());
        }

        WechatAppResp wechatAppResp = new WechatAppResp();

        wechatAppResp.setAppid(properties.getAppid());
        wechatAppResp.setNoncestr(RandomStringUtils.random(15, "abcdefgABCDEFG123456789"));
        wechatAppResp.setPrepayid(wechatPayResponseModel.getPrepayId());
        wechatAppResp.setPartnerid(properties.getMchId());
        wechatAppResp.setTimestamp(new Date().getTime());

        wechatAppResp.setSign(getSign(BeanUtils.describe(wechatAppResp)));
        return wechatAppResp;
    }

    /**
     * 小程序支付
     *
     * @param model
     * @return
     */
    public WechatXcxResp createXcxPayResponse(WechatPayTradeModel model) throws Exception {

        String nonceStr = RandomStringUtils.random(15, "abcdefgABCDEFG123456789");

        WechatXcxPayRequestModel requestModel = new WechatXcxPayRequestModel();
        requestModel.setOpenid(model.getOpenId());
        requestModel.setAppid(appId);
        requestModel.setMch_id(properties.getMchId());
        requestModel.setNonce_str(nonceStr);
        requestModel.setBody(properties.getName() + "-" + model.getBody());
        requestModel.setNotify_url(properties.getNotifyUrl());
        InetAddress addr = InetAddress.getLocalHost();
        requestModel.setSpbill_create_ip(addr.getHostAddress());
        requestModel.setOut_trade_no(model.getOut_trade_no());
        requestModel.setTotal_fee(model.getTotal_fee());
        requestModel.setAttach(model.getAttach());
        requestModel.setTrade_type("JSAPI");
        log.info("--------支付参数{}", JSON.toJSONString(requestModel));
        //将参数进行字典排序
        Map<String, String> describe = BeanUtils.describe(requestModel);
        requestModel.setSign(getSign(describe));


        //调用统一下单获取的prepay_id
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<WechatPayRequestModel> httpEntity = new HttpEntity<>(requestModel, headers);
        RestTemplate restTemplate = new RestTemplate();
        String respXml = restTemplate.postForObject(properties.getUrl(), httpEntity, String.class);

        respXml = new String(respXml.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        XmlMapper xmlMapper = new XmlMapper();
        WechatPayResponseModel wechatPayResponseModel = xmlMapper.readValue(respXml, WechatPayResponseModel.class);

        //判断请求状态
        if (!"SUCCESS".equals(wechatPayResponseModel.getReturnCode())) {
            throw new BusinessException(wechatPayResponseModel.getReturnMsg());
        }

        if (!"SUCCESS".equals(wechatPayResponseModel.getResultCode())) {
            throw new BusinessException(wechatPayResponseModel.getErrCodeDes());
        }

        //用于小程序发起微信支付
        WechatXcxResp resp = new WechatXcxResp();

        resp.setAppId(appId);
        resp.setNonceStr(nonceStr);
        resp.setTimeStamp(new Date().getTime());
        resp.setPackage("prepay_id=" + wechatPayResponseModel.getPrepayId());
        resp.setPaySign(getSign(BeanUtils.describe(resp)));

        return resp;
    }

    /**
     * 微信统一退款接口
     *
     * @param orderNo      订单号
     * @param totalFee     订单总金额
     * @param refundAmount 退款金额
     * @param refundReason 退款原因
     * @return 微信同步响应数据
     * @throws Exception 异常
     */
    public WechatRefundResponseModel tradeRefund(@NotEmpty String orderNo, String refundNo, @NotNull BigDecimal totalFee, @NotNull BigDecimal refundAmount, @NotEmpty String refundReason, String attach) throws Exception {

        WechatRefundRequestModel refundRequestModel = new WechatRefundRequestModel();

        refundRequestModel.setAppid(properties.getAppid());
        refundRequestModel.setMch_id(properties.getMchId());
        refundRequestModel.setNonce_str(RandomStringUtils.random(15, "abcdefgABCDEFG123456789"));
        refundRequestModel.setOut_trade_no(orderNo);
        //生成退款单号
        refundRequestModel.setOut_refund_no(refundNo);
        refundRequestModel.setNotify_url(properties.getNotifyUrl() + "/refund");
        refundRequestModel.setRefund_desc(refundReason);
        refundRequestModel.setRefund_fee(String.valueOf(refundAmount.multiply(new BigDecimal("100.00")).intValue()));
        refundRequestModel.setTotal_fee(String.valueOf(totalFee.multiply(new BigDecimal("100.00")).intValue()));

        //将参数进行字典排序
        Map<String, String> describe = BeanUtils.describe(refundRequestModel);
        refundRequestModel.setSign(getSign(describe));

        XmlMapper xmlMapper = new XmlMapper();
        String xmlStr = xmlMapper.writeValueAsString(refundRequestModel);
        String respXML = wxPayRequest.requestOnce(properties.getRefundUrl(), xmlStr, true);

        WechatRefundResponseModel responseModel = xmlMapper.readValue(respXML, WechatRefundResponseModel.class);

        //判断请求状态 返回状态码
        if (!"SUCCESS".equals(responseModel.getReturnCode())) {
            throw new Exception(responseModel.getReturnMsg());
        }

        //判断业务状态
        if (!"SUCCESS".equals(responseModel.getResultCode())) {
            throw new Exception("微信退款异常：\n订单号：" + orderNo + "\n错误码：" + responseModel.getErrCode() + "\n错误信息：" + responseModel.getErrCodeDes());
        }

        RefundMessage message = new RefundMessage();

        message.setOutTradeNo(responseModel.getRefundId());
        message.setPayType(0);
        message.setRefundReason(refundReason);
        message.setTradeResult(responseModel.getResultCode());
        message.setOrderNo(responseModel.getOutTradeNo());

        //退款金额
        message.setRefundAmount(refundAmount.toString());

        //退款金额转换为元
        BigDecimal refundFee = refundAmount.multiply(new BigDecimal(properties.getTradeRate())).setScale(2, RoundingMode.HALF_UP);

        //实际退款金额
        message.setReceiptAmount(refundAmount.subtract(refundFee).toString());
        //平台退还的手续费
        message.setRefundFee(refundFee.toString());
        message.setParams(attach);

        //退款通知
        for (RefundObserver refundObserver : refundObservers) {
            try {
                refundObserver.refundNotify(message);
            } catch (Exception e) {
                log.error("通知退款订阅者异常：", e);
            }
        }

        return responseModel;
    }

    /**
     * 验证签名
     *
     * @param params
     * @return
     */
    private String getSign(Map<String, String> params) {

        //移除class sign 字段
        params.remove("class");
        params.remove("sign");
        //小程序需要移除paySign字段
        params.remove("paySign");


        TreeMap<String, String> map = new TreeMap<>(Comparator.naturalOrder());

        map.putAll(params);


        //进行字符串URL格式拼接
        StringBuilder sb = new StringBuilder();

        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            sb.append("&").append(key).append("=").append(map.get(key));
        }

        sb.append("&key=").append(properties.getKey());

        String stringSignTemp = sb.toString().replaceFirst("&", "");

        String bytes = DigestUtils.md5Hex(stringSignTemp.getBytes(StandardCharsets.UTF_8));

        return bytes.toUpperCase();
    }


    /**
     * 发送支付消息至订阅者
     *
     * @param params 回调通知参数
     */
    public void payNotify(String params) throws Exception {


        XmlMapper xmlMapper = new XmlMapper();
        JsonNode xmlTree = xmlMapper.readTree(params);
        Iterator<String> iterator = xmlTree.fieldNames();
        Map<String, String> paramsMap = new HashMap<>();

        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = xmlTree.get(key).textValue();
            paramsMap.put(key, value);
        }

        //验证回传签名
        String sign = getSign(paramsMap);
        if (!sign.equals(xmlTree.get("sign").asText())) {
            throw new BusinessException("签名错误");
        }

        //判断是否为重复通知
        String transactionId = (String) stringRedisTemplate.redisTemplate.opsForValue().get("WECHATPAY_NOTIFY_" + paramsMap.get("transaction_id"));

        if (transactionId != null) {
            return;
        }

        stringRedisTemplate.redisTemplate.opsForValue().set("WECHATPAY_NOTIFY_" + paramsMap.get("transaction_id"), params, 25, TimeUnit.HOURS);


        PayMessage message = new PayMessage();

        //将分转换为元
        BigDecimal totalFree = new BigDecimal(xmlTree.get("total_fee").asText()).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        message.setAmount(totalFree.toString());
        message.setAppId(xmlTree.get("appid").asText());
        message.setBuyerId(xmlTree.get("openid").asText());
        message.setOrderNo(xmlTree.get("out_trade_no").asText());
        message.setOutTrade(xmlTree.get("transaction_id").asText());
        //设置支付类型为微信
        message.setPayType(0);

        //计算平台交易手续费(保留两位小数，四舍五入)
        BigDecimal tradeFree = totalFree.multiply(new BigDecimal(properties.getTradeRate())).setScale(2, RoundingMode.HALF_UP);

        message.setTradeFee(tradeFree.toString());

        //计算实收款
        BigDecimal receiptAmount = totalFree.subtract(tradeFree);
        message.setReceiptAmount(receiptAmount.toString());

        message.setSellerId(xmlTree.get("mch_id").asText());
        message.setTradeResult(xmlTree.get("result_code").asText());
        message.setParams(xmlTree.get("attach").asText());
        message.setTradeType(PayMessage.TYPE_PAY);
        log.error("订单：{}，开始回调处理",message.getOrderNo());
        for (PayObserver observer : payObservers) {
            try {
                log.error("bean:{}",observer.getClass().getName());
                observer.payNotify(message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

    }

    /**
     * 微信退款通知
     *
     * @param params 通知内容
     */
    public void refundNotify(String params) throws Exception {

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode xmlTree = xmlMapper.readTree(params);

        String reqInfo = xmlTree.get("req_info").textValue();

        if (reqInfo == null) {
            return;
        }

        //Base64 解码 获取加密字符串A
        byte[] decode = Base64.getDecoder().decode(reqInfo);
        String keyMd5 = DigestUtils.md5Hex(properties.getKey()).toLowerCase();
        reqInfo = AES256Util.decryptData(decode, keyMd5.getBytes());

        //获取通知内容,并转换为支付通知
        JsonNode reqInfoNode = xmlMapper.readTree(reqInfo);

        PayMessage payMessage = new PayMessage();
        payMessage.setTradeType(PayMessage.TYPE_REFUND);
        payMessage.setPayType(0);

        //将分转换为元
        BigDecimal totalFree = new BigDecimal(reqInfoNode.get("total_fee").asText()).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        //设置退款金额
        payMessage.setAmount(totalFree.toString());
        payMessage.setReceiptAmount(totalFree.toString());
        payMessage.setOrderNo(reqInfoNode.get("out_trade_no").asText());
        payMessage.setTradeFee("0.00");
        payMessage.setTradeResult(reqInfoNode.get("refund_status").asText());
        payMessage.setSellerId(xmlTree.get("mch_id").asText());
        payMessage.setAppId(xmlTree.get("appid").asText());
        payMessage.setOutTrade(reqInfoNode.get("refund_id").asText());

        //判断是否为重复通知
        String transactionId = (String) stringRedisTemplate.redisTemplate.opsForValue().get("WECHAT_REFUND_NOTIFY_" + reqInfoNode.get("refund_id"));

        if (transactionId != null) {
            return;
        }

        stringRedisTemplate.redisTemplate.opsForValue().set("WECHAT_REFUND_NOTIFY_" + reqInfoNode.get("refund_id"), params, 25, TimeUnit.HOURS);


    }
}
