package com.sinonc.common.payment.notify;

import org.springframework.scheduling.annotation.Async;

/**
 * 支付异步通知
 */
public interface PayObserver {
    @Async
    public void payNotify(PayMessage message) throws Exception;
}
