package com.sinonc.orders.controller.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.payment.alipay.AliPayService;
import com.sinonc.common.payment.wechat.WechatAppResp;
import com.sinonc.common.payment.wechat.WechatPayService;
import com.sinonc.common.payment.wechat.WechatPayTradeModel;
import com.sinonc.common.payment.wechat.WechatXcxResp;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.domain.BookOrder;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.expection.TradeException;
import com.sinonc.orders.service.BookOrderService;
import com.sinonc.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/pay")
@Slf4j
public class ApiPayController {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookOrderService bookOrderService;

    @Autowired
    private TokenService tokenService;

    /**
     * 创建支付宝APP支付参数
     *
     * @param orderId 订单id
     * @return 支付宝支付信息
     */
    @PostMapping("alipay/create")
    public String createAliPay(Long orderId) throws AlipayApiException {

        Order order = orderService.getOrderById(orderId);

        //判断订单是否已经被支付了
        if (order.getTradeStatus() == 1) {
            throw new TradeException(order.getOrderNo(), "订单已支付,请勿重复支付！");
        }

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo(order.getOrderNo());
        model.setTotalAmount(order.getActualPayment().toString());
        model.setSubject("订单：" + order.getOrderNo());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(order.getShopIdP().toString());

        //判断是否是预订单,并增加预订流程
        if (order.getOrderType() == OrderConstant.TYPE_BOOK) {
            BookOrder bookOrder = bookOrderService.getBookOrderByOrderId(order.getOrderId());

            if (bookOrder == null) {
                throw new BaseException("订单状态异常");
            }

            Integer tradeStatus = bookOrder.getTradeStatus();

            //定金未支付，则支付定金
            if (tradeStatus == 0) {
                model.setOutTradeNo(bookOrder.getEarnestNo());
                model.setTotalAmount(bookOrder.getEarnestPrice().toString());
                model.setSubject(StringUtils.format("订单：{} 定金", order.getOrderNo()));
                //定金已支付，则支付尾款
            } else if (tradeStatus == 1) {
                model.setOutTradeNo(bookOrder.getRestNo());
                model.setTotalAmount(bookOrder.getRestPrice().toString());
                model.setSubject(StringUtils.format("订单：{} 尾款", order.getOrderNo()));
            }
        }

        return aliPayService.createAppPayResponse(model);
    }


    /**
     * 创建微信APP支付请求参数
     *
     * @param orderId 订单ID
     * @return 微信支付信息
     */
    @PostMapping(value = "wechat/create/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WechatAppResp createWechatPay(@PathVariable("orderId") Long orderId) throws Exception {

        Order order = orderService.getOrderById(orderId);

        //判断订单是否已经被支付了
        if (order.getTradeStatus() == 1) {
            throw new TradeException(order.getOrderNo(), "订单已支付");
        }

        WechatPayTradeModel model = new WechatPayTradeModel();
        model.setBody("订单：" + order.getOrderNo());
        model.setOut_trade_no(order.getOrderNo());
        //将金额转换为分
        model.setTotal_fee(String.valueOf(order.getActualPayment().multiply(new BigDecimal("100")).intValue()));
        model.setAttach(order.getShopIdP().toString());

        //判断是否是预订单,并增加预订流程
        if (order.getOrderType() == OrderConstant.TYPE_BOOK) {
            BookOrder bookOrder = bookOrderService.getBookOrderByOrderId(order.getOrderId());

            if (bookOrder == null) {
                throw new BaseException("订单状态异常");
            }

            Integer tradeStatus = bookOrder.getTradeStatus();

            //定金未支付，则支付定金
            if (tradeStatus == 0) {
                model.setOut_trade_no(bookOrder.getEarnestNo());
                model.setTotal_fee(String.valueOf(bookOrder.getEarnestPrice().multiply(new BigDecimal("100")).intValue()));
                model.setBody(StringUtils.format("订单：{} 定金", order.getOrderNo()));
                //定金已支付，则支付尾款
            } else if (tradeStatus == 1) {
                model.setOut_trade_no(bookOrder.getRestNo());
                model.setTotal_fee(String.valueOf(bookOrder.getRestPrice().multiply(new BigDecimal("100")).intValue()));
                model.setBody(StringUtils.format("订单：{} 尾款", order.getOrderNo()));
            }
        }

        return wechatPayService.createPayResponse(model);
    }

    /**
     * 创建微信小程序支付响应
     *
     * @param orderId 订单Id
     * @return 小程序响应参数
     * @throws Exception
     */
    @PostMapping(value = "wechat/xcx/create/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public WechatXcxResp createWechatXcxPay(@PathVariable("orderId") Long orderId) throws Exception {

        Order order = orderService.getOrderById(orderId);

        //判断订单是否已经被支付了
        if (order.getTradeStatus() == 1) {
            throw new TradeException(order.getOrderNo(), "订单已支付");
        }

        WechatPayTradeModel model = new WechatPayTradeModel();
        model.setBody("订单：" + order.getOrderNo());
        model.setOut_trade_no(order.getOrderNo());
        //将金额转换为分
        model.setTotal_fee(String.valueOf(order.getActualPayment().multiply(new BigDecimal("100")).intValue()));
        model.setAttach(order.getShopIdP().toString());
        model.setOpenId(tokenService.getLoginUser().getWxUserConsume().getOpenid());
        
        //判断是否是预订单,并增加预订流程
        if (order.getOrderType() == OrderConstant.TYPE_BOOK) {
            BookOrder bookOrder = bookOrderService.getBookOrderByOrderId(order.getOrderId());

            if (bookOrder == null) {
                throw new BaseException("订单状态异常");
            }

            Integer tradeStatus = bookOrder.getTradeStatus();

            //定金未支付，则支付定金
            if (tradeStatus == 0) {
                model.setOut_trade_no(bookOrder.getEarnestNo());
                model.setTotal_fee(String.valueOf(bookOrder.getEarnestPrice().multiply(new BigDecimal("100")).intValue()));
                model.setBody(StringUtils.format("订单：{} 定金", order.getOrderNo()));
                //定金已支付，则支付尾款
            } else if (tradeStatus == 1) {
                model.setOut_trade_no(bookOrder.getRestNo());
                model.setTotal_fee(String.valueOf(bookOrder.getRestPrice().multiply(new BigDecimal("100")).intValue()));
                model.setBody(StringUtils.format("订单：{} 尾款", order.getOrderNo()));
            }
        }

        return wechatPayService.createXcxPayResponse(model);
    }
}
