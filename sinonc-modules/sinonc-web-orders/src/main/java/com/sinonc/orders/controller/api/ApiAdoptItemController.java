package com.sinonc.orders.controller.api;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.AdoptItem;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "认养服务项目Api")
@RestController
@RequestMapping("/api/adopt")
public class ApiAdoptItemController extends BaseController {

    @Autowired
    private AdoptItemService adoptItemService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private IOdGoodsService odGoodsService;

    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;


    @ApiOperation("根据订单ID查询认养服务项目列表")
    @GetMapping("/items")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "int", paramType = "query")
    })
    public AjaxResult listByOrderId(Long orderId) {

        if (orderId == null) {
            return AjaxResult.error("orderId不能为空");
        }

        AdoptItem adoptItem = new AdoptItem();
        adoptItem.setDelFlag(0);
        adoptItem.setOrderIdP(orderId);
        Long memberId = SecurityUtils.getUserId();
        adoptItem.setMemberIdP(memberId);
        List<AdoptItem> adoptItems = adoptItemService.listAdoptItem(adoptItem);
        Map<String,Object> map = new HashMap<>();
        map.put("adoptItems",adoptItems);
        return AjaxResult.success(map);
    }


    @ApiOperation("查询用户所有认养服务项目,并根据套餐进行分组显示")
    @GetMapping("/all")
    @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header")
    @SuppressWarnings("unchecked")
    public AjaxResult listAll(@RequestParam(required = false) Long orderId) {

        AdoptItem adoptItem = new AdoptItem();
        adoptItem.setDelFlag(0);
        Long memberId = SecurityUtils.getUserId();
        adoptItem.setMemberIdP(memberId);
        adoptItem.setOrderIdP(orderId);
        List<AdoptItem> adoptItems = adoptItemService.listAdoptItem(adoptItem);

        Map<Long, Map<String, Object>> result = new LinkedHashMap<>();

        for (AdoptItem item : adoptItems) {

            Map<String, Object> dataMap;

            if (result.containsKey(item.getOrderIdP())) {
                dataMap = result.get(item.getOrderIdP());
                List<AdoptItem> items = (List<AdoptItem>) dataMap.get("items");
                items.add(item);
            } else {
                dataMap = new LinkedHashMap<>();

                List<AdoptItem> items = new LinkedList<>();
                items.add(item);

                dataMap.put("orderId", item.getOrderIdP());

                OrderItem orderItem = orderItemService.selectOrderItemByOrderId(item.getOrderIdP());
                if (Optional.ofNullable(orderItem).isPresent()){
                    OdGoods odGoods = odGoodsService.selectOdGoodsById(orderItem.getGoodsIdP());
                    if (Optional.ofNullable(odGoods).isPresent()){
                        dataMap.put("goodsImg", odGoods.getImage().split(",")[0]);
                        BaseFarm baseFarm = remoteBaseFarmService.getFarmInfo(odGoods.getFarmId());
                        if (Optional.ofNullable(baseFarm).isPresent()){
                            dataMap.put("farmName",baseFarm.getFarmName());
                        }
                    }
                }
                dataMap.put("goodsName", item.getGoodsName());
                dataMap.put("specsName", item.getSpecsName());
                dataMap.put("startTime", item.getStartTime());
                dataMap.put("endTime", item.getEndTime());
                dataMap.put("items", items);
                result.put(item.getOrderIdP(), dataMap);
            }
        }

        List<Map<String, Object>> data = new LinkedList<>();
        Set<Long> keys = result.keySet();
        for (Long key : keys) {
            Map<String, Object> map = result.get(key);
            data.add(map);
        }

        return AjaxResult.success(data);
    }
}
