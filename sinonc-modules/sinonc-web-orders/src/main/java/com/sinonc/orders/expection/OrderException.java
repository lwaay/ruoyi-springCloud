package com.sinonc.orders.expection;

/**
 * 订单异常
 */
public class OrderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String message;

    public OrderException(String message) {
        this.message = message;
    }

    public OrderException(String orderNo, String message) {
        this.message = "订单[" + orderNo + "]异常：" + message;
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
