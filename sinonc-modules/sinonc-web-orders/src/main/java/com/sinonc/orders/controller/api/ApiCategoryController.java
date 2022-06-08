package com.sinonc.orders.controller.api;


import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.orders.domain.OdCategory;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.TradeInfo;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.mapper.TradeInfoMapper;
import com.sinonc.orders.service.OdCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类相关接口
 */
@Api(tags = "商品分类相关接口")
@RestController
@RequestMapping("api/category")
public class ApiCategoryController {

    @Autowired
    private OdCategoryService odCategoryService;


    /**
     * 查询商品分类列表
     * @return
     */
    @ApiOperation("查询商品分类列表")
    @GetMapping("getOdCategoryList")
    public AjaxResult getOdCategoryList(OdCategory odCategory) {
        List<OdCategory>  odCategoryList=odCategoryService.selectOdCategoryList(odCategory);
        return AjaxResult.success(odCategoryList);
    }


}
