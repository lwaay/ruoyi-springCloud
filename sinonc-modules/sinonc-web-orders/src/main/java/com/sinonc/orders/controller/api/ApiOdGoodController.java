package com.sinonc.orders.controller.api;


import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.domain.OdMemberAttention;
import com.sinonc.orders.dto.BookGoodsDto;
import com.sinonc.orders.dto.OdGoodsDto;
import com.sinonc.orders.event.event.VisitEvent;
import com.sinonc.orders.event.payload.VisitRecord;
import com.sinonc.orders.mapper.OrderItemMapper;
import com.sinonc.orders.service.IOdGoodsService;
import com.sinonc.orders.service.IOdMemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 直供商品相关接口
 */
@Api(tags = "直供商品相关接口")
@RestController
@RequestMapping("api/odgood")
public class ApiOdGoodController {

    @Autowired
    private IOdGoodsService odGoodsService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private IOdMemberAttentionService odMemberAttentionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 查询直供商品列表
     * @return
     */
    @ApiOperation("查询直供商品列表")
    @GetMapping("getOdGoodList")
    public AjaxResult getOdGoodList(OdGoods odGoods) {
        odGoods.setType(1);
        odGoods.setSaleAble(0);
        List<OdGoods>   odGoodList=odGoodsService.selectOdGoodsList(odGoods);
        List<OdGoodsDto> odGoodsDtoList=new ArrayList<>();

        for (int i = 0; i < odGoodList.size(); i++) {
            OdGoods tempOdGoods=odGoodList.get(i);
            OdGoodsDto odGoodsDto=new OdGoodsDto();
            BeanUtils.copyProperties(tempOdGoods,odGoodsDto);
            Long count=orderItemMapper.countGoodsSale(tempOdGoods.getGoodsId());
            odGoodsDto.setSalesVolume(count);
            odGoodsDtoList.add(odGoodsDto);
        }

        return AjaxResult.success(odGoodsDtoList);
    }

    /**
     * 查询商品详情
     * @return
     */
    @ApiOperation("查询商品详情")
    @GetMapping("getOdGoodDetail")
    public AjaxResult getOdGoodDetail(Long goodsId) {
        OdGoodsDto odGoodsDto=odGoodsService.selectOdGoodsDetailById(goodsId);
        // 添加用户足迹
        if(tokenService.getLoginUser() != null){
            VisitRecord record = VisitRecord.builder().userId(tokenService.getLoginUser().getWxUserConsume().getId()).targetId(goodsId).visitDate(new Date()).build();
            applicationEventPublisher.publishEvent(new VisitEvent(this, record));
            // 当前用户是否收藏
            OdMemberAttention query = new OdMemberAttention();
            query.setUserId(tokenService.getLoginUser().getWxUserConsume().getId());
            query.setTargetId(goodsId);
            query.setStatus(0);
            odGoodsDto.setIsAttention(odMemberAttentionService.selectOdMemberAttentionList(query).size()>0 ? "0":"1");
        }
        return AjaxResult.success(odGoodsDto);
    }


}
