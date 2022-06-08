package com.sinonc.orders.controller.api;


import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.TradeInfo;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.mapper.TradeInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页数据接口
 */
@Api(tags = "APP首页基础数据接口")
@RestController
@RequestMapping("api/trade")
public class ApiBaseController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TradeInfoMapper tradeInfoMapper;


    /**
     * 获取首页基础信息
     * @return 结果
     */
    @ApiOperation("查询首页交易、认养、农场数量")
    @GetMapping("baseInfo")
    public AjaxResult getBaseInfo() {

        Order adopt = new Order();
        adopt.setOrderType(0);
        adopt.setTradeStatus(1);
        adopt.setOrderType(0);

        int adoptCount = orderMapper.selectCount(adopt);

        TradeInfo tradeInfo = new TradeInfo();
        String amount = tradeInfoMapper.selectTotalAmount(tradeInfo);

//        int farmsCount=farminfoMapper.selectFarmInfoCount();

        Map<String,String> data=new HashMap<>();

        data.put("adoptCount", adoptCount+"");
        data.put("amount", amount);
//        data.put("farmsCount", farmsCount+"");

        return AjaxResult.success(data);
    }


}
