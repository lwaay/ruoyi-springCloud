package com.sinonc.common.payment.wechat;


import lombok.Data;

/**
 * 创建微信交易订单信息实体类
 */
@Data
public class WechatPayTradeModel {

    /**
     * 商品描述交易字段格式根据不同的应用场景按照以下格式：
     *
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */
    private String body;

    /**
     * 商户系统内部订单号
     */
    private String out_trade_no;

    /**
     * 订单总金额，单位为分
     */
    private String total_fee;

    /**
     * 自定义附加数据
     */
    private String attach;

    /**
     * 用户身份标识
     */
    private String openId;
}
