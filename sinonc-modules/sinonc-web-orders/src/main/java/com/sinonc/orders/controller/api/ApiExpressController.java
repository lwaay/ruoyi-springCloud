package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.orders.domain.Express;
import com.sinonc.orders.service.ExpressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "物流信息接口")
@RequestMapping({"api/express","api/seller/express"})
@RestController
public class ApiExpressController {

    @Autowired
    private ExpressService expressService;

    @ApiOperation("根据订单号查询物流单号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "orderId", name = "订单号", paramType = "query", required = true),
            @ApiImplicitParam(value = "token", name = "token", paramType = "header", required = true)
    })
    @GetMapping("get")
    public AjaxResult getExpByOrderId(Long orderId) {
        Express express = expressService.getExpressByOrderId(orderId);
        return AjaxResult.success(express);
    }

    @ApiOperation("根据物流单号查询物流信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "orderId", name = "订单号", paramType = "query", required = true),
            @ApiImplicitParam(value = "token", name = "token", paramType = "header", required = true)
    })
    @GetMapping("detail")
    public AjaxResult getExpDetail(String expressNo) {
        return AjaxResult.success(expressService.getExpressDetail(expressNo));
    }

}
