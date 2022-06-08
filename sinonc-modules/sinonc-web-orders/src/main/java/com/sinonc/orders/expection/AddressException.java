package com.sinonc.orders.expection;

/**
 * 收货地址异常
 */
public class AddressException extends RuntimeException {

    public AddressException() {
    }

    public AddressException(String message) {
        super(message);
    }

    public AddressException(String message, Throwable cause) {
        super(message, cause);
    }

}
