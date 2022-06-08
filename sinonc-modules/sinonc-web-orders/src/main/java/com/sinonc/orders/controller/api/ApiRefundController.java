package com.sinonc.orders.controller.api;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.Refund;
import com.sinonc.orders.service.OrderService;
import com.sinonc.orders.service.RefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@Api(tags = "订单退款接口")
@RequestMapping("api/refund")
@RestController
public class ApiRefundController {

    @Autowired
    private RefundService refundService;
    @Autowired
    private OrderService orderService;

    @ApiOperation("创建退款申请")
    @PostMapping("create")
    public AjaxResult create(@Valid Refund refund, BindingResult result) {

        //验证参数是否为空
        if (result.hasErrors()) {
            return AjaxResult.error(406, result.getFieldError().getDefaultMessage());
        }

        Order order = orderService.getOrderById(refund.getOrderId());

        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        Long memberId = SecurityUtils.getUserId();

        if (!order.getMemberIdP().equals(memberId)) {
            throw new BusinessException("订单信息与所属用户不符");
        }

        if (order.getActualPayment().compareTo(refund.getRfAmount()) < 0) {
            throw new BusinessException("退款金额不能大于订单总金额");
        }


        refund.setOrderNo(order.getOrderNo());
        refund.setMemberId(memberId);
        refund.setCreateTime(new Date());
        refund.setShopId(order.getShopIdP());
        refund.setOrderAmount(order.getActualPayment());

        return AjaxResult.success(refundService.addRefund(refund));
    }
}

