package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageInfo;
import com.mchange.v1.db.sql.ConnectionUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.domain.ShopVisit;
import com.sinonc.orders.dto.ShopDto;
import com.sinonc.orders.mapper.AttentionMapper;
import com.sinonc.orders.mapper.GoodsDetailVoMapper;
import com.sinonc.orders.mapper.ShopVisitMapper;
import com.sinonc.orders.service.*;
import com.sinonc.orders.vo.GoodsDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * 店铺接口
 */
@Api(tags = "店铺接口API接口")
@RestController
@RequestMapping("api/shop")
public class ApiShopController extends BaseController {

    @Autowired
    private IShopService shopService;
    @Autowired
    private IOdGoodsService odGoodsService;
    @Autowired
    private AttentionMapper attentionMapper;
    @Autowired
    private ShopVisitService shopVisitService;
    @Autowired
    private ShopVisitMapper shopVisitMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private GoodsDetailVoMapper goodsDetailVoMapper;

    @Autowired
    private AuctionService auctionService;


    /**
     * 查询店铺列表
     *
     * @return
     */
    @ApiOperation("查询店铺列表")
    @GetMapping("getShopList")
    public AjaxResult getShopList(Shop shop) {
        startPage();
        PageInfo pageInfo = new PageInfo<>(shopService.selectShopList(shop));
        List<Shop> shopList = pageInfo.getList();

        List<ShopDto> shopDtoList = new ArrayList<>();
        for (int i = 0; i < shopList.size(); i++) {
            Shop tempShop = shopList.get(i);
            ShopDto shopDto = new ShopDto();
            shopDtoList.add(shopDto);
            BeanUtils.copyProperties(tempShop, shopDto);

            //直购商品
            List<OdGoods> odGoodsList = selectOdGoodsByShopId(tempShop.getShopId(),1);
            shopDto.setOdGoodsList(odGoodsList);
            //关注
            Integer attenCount = attentionMapper.queryAttention(String.valueOf(tempShop.getShopId()));
            shopDto.setAttenQua(Long.valueOf(attenCount));
            //访问量
            ShopVisit shopVisit = new ShopVisit();
            shopVisit.setShopIdP(tempShop.getShopId());
            long visitNumber = shopVisitMapper.sumShopVisitList(shopVisit);
            shopDto.setVisitQua(visitNumber);

            //商品数
            OdGoods odGoods=new OdGoods();
            odGoods.setShopId(tempShop.getShopId());
            odGoods.setSaleAble(0);
            List<OdGoods>  odTempGoodsList=odGoodsService.selectOdGoodsList(odGoods);
            if(odTempGoodsList!=null&&odTempGoodsList.size()>0){
                shopDto.setOdGoodCount(odTempGoodsList.size());
            }else {
                shopDto.setOdGoodCount(0);
            }

        }

        AjaxResult success = AjaxResult.success();
        success.put("data", shopDtoList);
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    /**
     * 查询店铺详情
     *
     * @return
     */
    @ApiOperation("查询店铺详情")
    @GetMapping("getShopDetail")
    public AjaxResult getShopDetail(Long shopId) {
        Shop shop = shopService.selectShopById(shopId);
        ShopDto shopDto = new ShopDto();
        BeanUtils.copyProperties(shop, shopDto);

        ShopVisit tempShopVisit=new ShopVisit();
        tempShopVisit.setShopIdP(shopId);
        shopVisitService.addShopVisit(tempShopVisit);

        //认养商品
        List<OdGoods> ryOdGoodsList = selectOdGoodsByShopId(shop.getShopId(),0);
        selectPrice(ryOdGoodsList,0);
        shopDto.setRyOdGoodsList(ryOdGoodsList);

        //直供商品
        List<OdGoods> odGoodsList = selectOdGoodsByShopId(shop.getShopId(),1);
        shopDto.setOdGoodsList(odGoodsList);

        //预定商品
        List<OdGoods> ydOdGoodsList = selectOdGoodsByShopId(shop.getShopId(),2);
        selectPrice(ydOdGoodsList,2);
        shopDto.setYdOdGoodsList(ydOdGoodsList);

        //热销
        List<OdGoods> rxOdGoodsList = selectRxOdGoodsByShopId(shop.getShopId());
        shopDto.setRxOdGoodsList(rxOdGoodsList);

        //关注
        Integer attenCount = attentionMapper.queryAttention(String.valueOf(shop.getShopId()));
        shopDto.setAttenQua(Long.valueOf(attenCount));
        //访问量
        ShopVisit shopVisit = new ShopVisit();
        shopVisit.setShopIdP(shop.getShopId());
        Long visitNumber = shopVisitMapper.sumShopVisitList(shopVisit);
        shopDto.setVisitQua(visitNumber);

        if(tokenService.getLoginUser() != null){
            Map<String, Long> query = new HashMap<>(2);
            query.put("shopId", shopId);
            query.put("memberId", tokenService.getLoginUser().getWxUserConsume().getId());
            shopDto.setIsAttention(attentionMapper.selectAttention(query).size()>0 ? "0":"1");
        }

        AjaxResult success = AjaxResult.success(shopDto);
        return success;
    }


    /**
     * 查询会员的所有订单列表信息
     *
     * @return
     */
    @ApiOperation(value = "增加或更新店铺访问信息")
    @ApiImplicitParam(value = "shopVisit", name = "店铺访问信息", paramType = "query", required = true)
    @PostMapping(value = "addShopVisit")
    public AjaxResult addShopVisit(ShopVisit shopVisit) {
        int rows = shopVisitService.addShopVisit(shopVisit);
        return AjaxResult.success(rows);
    }

    private List<OdGoods> selectOdGoodsByShopId(Long shopId,Integer type) {
        OdGoods odGoods = new OdGoods();
        odGoods.setShopId(shopId);
        odGoods.setType(type);
        odGoods.setLimitsize(6);
        odGoods.setSaleAble(0);
        List<OdGoods> odGoodsList = odGoodsService.selectOdGoodsListLimit(odGoods);
        return odGoodsList;
    }


    private void selectPrice(List<OdGoods> odGoodsList,Integer type){
        logger.info("商品类型："+type);
        for (int i = 0; i < odGoodsList.size(); i++) {
            OdGoods odGoods=odGoodsList.get(i);
            GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
            goodsDetailVo.setType(type);
            goodsDetailVo.setName(odGoods.getName());
            List<Map<String, Object>> data = goodsDetailVoMapper.selectGoodsBriefInfo(goodsDetailVo);
            if(CollectionUtils.isEmpty(data)){
                continue;
            }else {
                Map<String, Object> map=data.get(0);
                if(CollectionUtils.isEmpty(map)){
                    continue;
                }else {
                    if(type == 2){
                        Integer status= (Integer) map.get("status");
                        if(status!=null&&status==2){
                            //结束的不展示
                            continue;
                        }
                    }
                    odGoods.setCostPrice(new BigDecimal(map.get("price").toString()));
                    odGoods.setFinalPrice(new BigDecimal(map.get("price").toString()));
                }
            }

        }


    }


    private List<OdGoods> selectRxOdGoodsByShopId(Long shopId) {
        OdGoods odGoods = new OdGoods();
        odGoods.setShopId(shopId);
        odGoods.setLimitsize(6);
        odGoods.setSaleAble(0);
        List<OdGoods> odGoodsList = odGoodsService.selectRxOdGoodsByShopId(odGoods);
        return odGoodsList;
    }

    /**
     * 我的主体内的店铺(暂不分)
     * @return
     */
    @GetMapping("/myShop")
    public AjaxResult myShop(){

        Shop query = new Shop();

        List<Shop> list = shopService.selectShopList(query);
        return AjaxResult.success(list);
    }
}
