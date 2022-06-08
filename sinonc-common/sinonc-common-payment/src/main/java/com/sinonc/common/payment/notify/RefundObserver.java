package com.sinonc.common.payment.notify;

import org.springframework.scheduling.annotation.Async;

/**
 * 退款通知
 */
public interface RefundObserver {

    @Async
    public void refundNotify(RefundMessage message) throws Exception;

}
