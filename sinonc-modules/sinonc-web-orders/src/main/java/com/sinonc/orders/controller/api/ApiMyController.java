package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.OdMemberVisit;
import com.sinonc.orders.service.AttentionService;
import com.sinonc.orders.service.IOdMemberAttentionService;
import com.sinonc.orders.service.IOdMemberVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/4/15 16:13
 */
@RestController
@RequestMapping("/api/my")
public class ApiMyController {

    @Autowired
    private IOdMemberAttentionService odMemberAttentionService;

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private IOdMemberVisitService visitService;

    @Autowired
    private TokenService tokenService;

    // 我的数据
    @GetMapping("data")
    public AjaxResult my(){
        Long memberId = tokenService.getLoginUser().getWxUserConsume().getId();
        // 收藏商品数
        int attentionCount = odMemberAttentionService.selectOdMemberAttentionCountByUserId(memberId);
        List<Shop> shops = attentionService.selectLikeListByMemberId(memberId);
        // 店铺关注id
        int shopAttentionCount = shops.size();
        OdMemberVisit visitquery= new OdMemberVisit();
        visitquery.setUserId(memberId);
        List<OdMemberVisit> odMemberVisits = visitService.selectOdMemberVisitList(visitquery);
        // 足迹
        int visitCount = odMemberVisits.size();
        Map<String, Object> display = new HashMap<>(3);
        display.put("attentionCount", attentionCount);
        display.put("shopAttentionCount", shopAttentionCount);
        display.put("visitCount", visitCount);
        return AjaxResult.success(display);
    }
}
