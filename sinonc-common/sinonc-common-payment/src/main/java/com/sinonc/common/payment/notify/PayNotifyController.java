package com.sinonc.common.payment.notify;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.payment.alipay.AliPayService;
import com.sinonc.common.payment.wechat.WechatPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 处理支付宝和微信异步通知
 */
@RestController
@RequestMapping("pay")
@Slf4j
public class PayNotifyController extends BaseController {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;

    @PostConstruct
    private void init(){
        log.info("----------处理支付宝和微信异步通知启动----------");
    }

    /**
     * 接受支付宝支付异步通知
     *
     * @param params 支付宝请求参数
     * @return "success"
     */
    @PostMapping("notify/alipay")
    public String alipayNotify(@RequestParam Map<String, String> params) {
        try {
            //通知订阅者进行业务处理
            aliPayService.aliPayNotify(params);
        } catch (Exception e) {
            log.error("支付宝通知异常：", e);
        }
        return "success";
    }


    /**
     * 接受微信支付异步消息
     *
     * @param params 异步消息
     * @return 结果
     */
    @RequestMapping(value = "notify/wechat", produces = MediaType.APPLICATION_XML_VALUE)
    public String wechatNotify(@RequestBody String params) {
        try {
            log.info("----------------微信支付回调");
            log.info(params);
            wechatPayService.payNotify(params);
        } catch (Exception e) {
            log.error("微信支付通知异常：", e);
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    /**
     * 微信退款通知
     *
     * @param params 通知参数
     * @return
     */
    @RequestMapping(value = "notify/wechat/refund", produces = MediaType.APPLICATION_XML_VALUE)
    public String wechatRefundNotify(@RequestBody String params) throws Exception {
        try {
            wechatPayService.refundNotify(params);
        } catch (Exception e) {
            log.error("微信退款通知异常：", e);
        }
        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
