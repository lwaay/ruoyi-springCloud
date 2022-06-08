package com.sinonc.orders.expection;

import com.sinonc.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 订单异常处理
 */
@ControllerAdvice
@Slf4j
public class OrderExceptionHandler {

    /**
     * 捕获订单异常
     * @param e
     * @return
     */
    @ExceptionHandler(OrderException.class)
    public AjaxResult handleOrderException(OrderException e) {
        log.error(e.getMessage(),e);
        return AjaxResult.error(e.getMessage());
    }

}
