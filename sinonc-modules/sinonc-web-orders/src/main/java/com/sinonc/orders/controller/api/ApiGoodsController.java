package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.OdMemberAttention;
import com.sinonc.orders.event.event.VisitEvent;
import com.sinonc.orders.event.payload.VisitRecord;
import com.sinonc.orders.mapper.GoodsDetailVoMapper;
import com.sinonc.orders.mapper.SpecsDetailVoMapper;
import com.sinonc.orders.service.*;
import com.sinonc.orders.vo.GoodsDetailVo;
import com.sinonc.orders.vo.SpecsDetailVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Api商品功能模块
 */
@Api
@RestController
@RequestMapping("api/goods/")
public class ApiGoodsController extends BaseController {


    @Autowired
    private GoodsDetailVoMapper goodsDetailVoMapper;

    @Autowired
    private SpecsDetailVoMapper specsDetailVoMapper;

    @Autowired
    private IOdMemberAttentionService odMemberAttentionService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AuctionService auctionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ApiOperation(value = "根据类型查询全部商品")
    @ApiImplicitParam(name = "goodstype", value = "商品类型", dataType = "Integer", required = false, paramType = "query")
    @ApiResponses({@ApiResponse(code = 200, message = " {\n" +
            "            \"sale_able\": 0,\n" +
            "            \"image\": \"http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/b50f7e8d-068a-41f7-ab09-4a942b062288.jpg,http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/37e0981f-7807-4a19-83e2-dd77ae2e2b27.jpg,\",\n" +
            "            \"specs_id\": 3,\n" +
            "            \"goods_id\": 7,\n" +
            "            \"discount\": \"0.8\",\n" +
            "            \"remark\": \"cc\",\n" +
            "            \"video\": \"3\",\n" +
            "            \"type\": 0,\n" +
            "            \"content\": \"<p>你好啊大家好11</p>\",\n" +
            "            \"brand_id\": 1,\n" +
            "            \"shop_id\": 1,\n" +
            "            \"category_id\": 1,\n" +
            "            \"final_price\": 88,\n" +
            "            \"name\": \"春秋脐橙\",\n" +
            "            \"stock\": 100,\n" +
            "            \"cost_price\": 99\n" +
            "        }"), @ApiResponse(code = 500, message = "{\n" +
            "\"msg\": \"无效Token\",\n" +
            "\"code\": 500\n" +
            "}")})
    @GetMapping(value = "selectgoodsbytype")
    public AjaxResult selectGoods(@RequestParam(required = false) Integer goodstype, String name) {

        startPage();
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setType(goodstype);
        goodsDetailVo.setName(name);
        List<Map<String, Object>> data = goodsDetailVoMapper.selectGoodsBriefInfo(goodsDetailVo);

        //竞拍商品
        if (goodstype != null && goodstype == 3) {
            for (Map<String, Object> map : data) {
                //根据商品id查询竞拍活动状态
                Auction auction = auctionService.selectAuctionForGoodsId(Long.parseLong(map.get("goodsId").toString()));
                if(auction!=null){
                    map.put("auctionIsEnd", auction.getIsEnd());
                    map.put("auctionPrice", auction.getAuctionNowprice());
                }else{
                    AjaxResult.error("竞拍商品已被删除，请联系管理员!");
                }
            }
        }
        Collections.reverse(data);
        return AjaxResult.success(data);
    }


    @ApiOperation(value = "根据类型查询全部商品")
    @ApiImplicitParam(name = "goodstype", value = "商品类型", dataType = "Integer", required = false, paramType = "query")
    @ApiResponses({@ApiResponse(code = 200, message = " {\n" +
            "            \"sale_able\": 0,\n" +
            "            \"image\": \"http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/b50f7e8d-068a-41f7-ab09-4a942b062288.jpg,http://sinonc-develop-test.oss-cn-hangzhou.aliyuncs.com/37e0981f-7807-4a19-83e2-dd77ae2e2b27.jpg,\",\n" +
            "            \"specs_id\": 3,\n" +
            "            \"goods_id\": 7,\n" +
            "            \"discount\": \"0.8\",\n" +
            "            \"remark\": \"cc\",\n" +
            "            \"video\": \"3\",\n" +
            "            \"type\": 0,\n" +
            "            \"content\": \"<p>你好啊大家好11</p>\",\n" +
            "            \"brand_id\": 1,\n" +
            "            \"shop_id\": 1,\n" +
            "            \"category_id\": 1,\n" +
            "            \"final_price\": 88,\n" +
            "            \"name\": \"春秋脐橙\",\n" +
            "            \"stock\": 100,\n" +
            "            \"cost_price\": 99\n" +
            "        }"), @ApiResponse(code = 500, message = "{\n" +
            "\"msg\": \"无效Token\",\n" +
            "\"code\": 500\n" +
            "}")})
    @GetMapping(value = "selectgoodsbytypeIsRecommend")
    public AjaxResult selectgoodsbytypeIsRecommend(@RequestParam(required = false) Integer goodstype) {

        startPage();
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setType(goodstype);
        List<Map<String, Object>> data = goodsDetailVoMapper.selectGoodsBriefInfoIsRecommend(goodsDetailVo);

        //竞拍商品
        if (goodstype != null && goodstype == 3) {
            for (Map<String, Object> map : data) {
                //根据商品id查询竞拍活动状态
                Auction auction = auctionService.selectAuctionForGoodsId(Long.parseLong(map.get("goodsId").toString()));
                map.put("auctionIsEnd", auction.getIsEnd());
                map.put("auctionPrice", auction.getAuctionNowprice());
            }
        }

        return AjaxResult.success(data);
    }


    @ApiOperation(value = "根据商品id查询商品详情")
    @ApiImplicitParam(name = "goodsId", value = "商品Id", dataType = "Long", required = true, paramType = "query")
    @GetMapping(value = "selectgoodsdetailbyid")
    public AjaxResult selectGoodsDetail(Long goodsId) {
        if (goodsId == null) {
            return AjaxResult.error("goodsId不能为空");
        }
        GoodsDetailVo goodsDetailVo = goodsDetailVoMapper.selectByGoodsId(goodsId);

        if (goodsDetailVo == null) {
            return AjaxResult.error("该商品已下架");
        }

        List<SpecsDetailVo> specsList = goodsDetailVo.getSpecsList();

        //所有规格中的最低价和最高价
        BigDecimal minPrice = null;
        BigDecimal maxPrice = null;

        //设置商品价格区间，用于前端显示
        for (SpecsDetailVo specsDetailVo : specsList) {

            BigDecimal specsPrice = specsDetailVo.getSpecsPrice();

            if (minPrice == null || minPrice.compareTo(specsPrice) >= 0) {
                minPrice = specsPrice;
            }

            if (maxPrice == null || maxPrice.compareTo(specsPrice) <= 0) {
                maxPrice = specsPrice;
            }
        }

        if (maxPrice.compareTo(minPrice) == 0) {
            goodsDetailVo.setPrice(maxPrice.toString());
        } else {
            goodsDetailVo.setPrice(minPrice + "-" + maxPrice);
        }

        // 添加用户足迹
        if(SecurityUtils.getUserId() != null){
            VisitRecord record = VisitRecord.builder().userId(SecurityUtils.getUserId()).targetId(goodsId).visitDate(new Date()).build();
            applicationEventPublisher.publishEvent(new VisitEvent(this, record));
            // 当前用户是否收藏
            OdMemberAttention query = new OdMemberAttention();
            query.setUserId(SecurityUtils.getUserId());
            query.setTargetId(goodsId);
            query.setStatus(0);
            goodsDetailVo.setIsAttention(odMemberAttentionService.selectOdMemberAttentionList(query).size()>0 ? "0":"1");
        }

        Integer saleCount = goodsService.countGoods(goodsId,0);
        //查询销量
        goodsDetailVo.setSaleCount(saleCount==null?0:saleCount);
        goodsDetailVo.setImgList(goodsDetailVo.getImage().split("[,，]"));
        //店铺信息
        goodsDetailVo.setShop(shopService.selectShopById(goodsDetailVo.getShopId()));
        return AjaxResult.success(goodsDetailVo);
    }

    @ApiOperation(value = "根据规格ID查询商品规格详情")
    @ApiImplicitParam(name = "specsId", value = "规格Id", dataType = "Long", required = true, paramType = "query")
    @GetMapping("getSpecsById")
    public AjaxResult selectGoodsSpecsDetail(Long specsId) {
        if (specsId == null) {
            return AjaxResult.error("specsId不能为空");
        }
        return AjaxResult.success(specsDetailVoMapper.selectById(specsId));
    }

    /**
     * 获取果园所有商品
     *
     * @param farmId
     * @return
     */
    @ApiOperation("根据农场ID获取所有预订商品列表")
    @ApiImplicitParam(name = "farmId", value = "基地农场ID")
    @GetMapping("/getGoodsByFarmId")
    public AjaxResult goodsList(Long farmId) {
        if (farmId == null) {
            return AjaxResult.error("farmId不能为空");
        }
        Shop shop = shopService.selectShopById(farmId);
        if (shop == null) {
            return AjaxResult.success("农场店铺不存在");
        }

        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setShopId(shop.getShopId());

        startPage();
        List<Map<String, Object>> maps = goodsDetailVoMapper.selectGoodsBriefInfo(goodsDetailVo);

        return AjaxResult.success(maps);
    }

    @ApiOperation("根据商品ID获取预订商品的销售情况")
    @ApiImplicitParam(name = "goodsId", value = "商品ID")
    @GetMapping("/bookSellInfo")
    public AjaxResult getBookGoodsSellInfo(Long goodsId) {
        if (goodsId == null) {
            return AjaxResult.error("goodsId不能为空");
        }
        startPage();
        List<Map<String, String>> map = goodsService.getBookGoodsSellInfo(goodsId);

        PageInfo<Map<String, String>> pageInfo = new PageInfo<>(map);
        AjaxResult success = AjaxResult.success();

        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    @GetMapping("count")
    public AjaxResult goodsCount(){
        return AjaxResult.success(goodsService.goodsCount());
    }

}
