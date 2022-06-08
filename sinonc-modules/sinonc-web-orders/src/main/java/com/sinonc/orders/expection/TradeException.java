package com.sinonc.orders.expection;

public class TradeException extends RuntimeException {

    private String orderNo;

    private String message;

    public TradeException(String orderNo, String message) {
        super(message);
        this.orderNo = orderNo;
        this.message = message;
    }

    public TradeException() {
    }

    public TradeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getOrderNo() {
        return this.orderNo;
    }
}
