package com.sinonc.common.payment.alipay;

/**
 * 支付宝支付异常
 */
public class AlipayException extends RuntimeException {

    private String message;
    private String errCode;

    public AlipayException() {
    }

    public AlipayException(String message) {
        super(message);
        this.message = message;
    }

    public AlipayException(String errCode, String message) {
        super(message);
        this.message = message;
        this.errCode = errCode;
    }


    @Override
    public String getMessage() {
        return "错误码：" + this.errCode + ",错误信息：" + this.message;
    }
}
