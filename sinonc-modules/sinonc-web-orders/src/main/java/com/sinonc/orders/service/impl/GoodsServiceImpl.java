package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.GoodsDto;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.AuctionService;
import com.sinonc.orders.service.BookGoodsService;
import com.sinonc.orders.service.GoodsService;
import com.sinonc.orders.service.GoodsSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service("goods")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsSpecsMapper goodsSpecsMapper;

    @Resource
    private SpecsMapper specsMapper;

    @Autowired
    private GoodsSpecsService goodsSpecsService;

    @Autowired
    private BookGoodsService bookGoodsService;

    @Autowired
    private AuctionService auctionService;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    ShopMapper shopMapper;


    /**
     * 查询商品信息
     *
     * @param goodsId 商品ID
     * @return 商品信息
     */
    @Override
    public Goods getGoodsById(Long goodsId) {
        return goodsMapper.selectGoodsById(goodsId);
    }

    /**
     * 查询商品列表
     *
     * @param goods 商品信息
     * @return 商品集合
     */
    @Override
    public List<Goods> listGoods(Goods goods) {
        List<Goods> goodsTo = goodsMapper.selectGoodsList(goods);
        if (goodsTo.size()>0){
            for (Goods goods1 : goodsTo) {
                Brand brand = brandMapper.selectBrandById(goods1.getBrandId());
                if (brand!=null){

                }
            }
        }
        return goodsTo;
    }

    /**
     * 根据商品类型查询商品列表
     *
     * @param type 商品类型
     * @return 商品列表
     */
    public List<Goods> listGoodsByType(Integer type) {
        Goods goods = new Goods();
        goods.setType(type);
        return goodsMapper.selectGoodsList(goods);
    }

    /**
     * 新增普通商品
     *
     * @param goodsDto 商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int addGoods(GoodsDto goodsDto) {

        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        //添加规格
        List<Specs> specsList = goodsDto.getSpecsList();

        //判断是否有足够的可销售重量
        String specsIdsString =goodsDto.getGoods().getSpecsIds();
        boolean judgeRs=judgeSaleAllWeightAndStock(goodsDto.getGoods().getShopId(),goodsDto.getSpecsList(),specsIdsString);
        if (!judgeRs){
            throw new BusinessException("新增商品失败,可销售重量不够。");
        }
        for (Specs specs : specsList) {
            specs.setCreateTime(date);
            specs.setCreateBy(loginName);
            specs.setUpdateTime(date);
            specs.setUpdateBy(loginName);
        }

        int specsRows = specsMapper.batchInsertSpecs(specsList);

        //保存规格ID
        StringBuilder specsIdSb = new StringBuilder();

        //将规格id转换为字符串，存入goods中
        for (Specs specs : specsList) {

            //验证定金
            if (specs.getEarnest() != null && specs.getEarnest().compareTo(specs.getSpecsPrice()) >= 0) {
                throw new BusinessException("定金金额不能大于或等于商品金额");
            }

            specsIdSb.append(",").append(specs.getSpecsId());
        }

        String specsIds = specsIdSb.toString().replaceFirst(",", "");

        //添加商品
        Goods goods = goodsDto.getGoods();

        goods.setSpecsIds(specsIds);
        goods.setCreateTime(date);
        goods.setCreateBy(loginName);

        //拆分脐橙类型和树龄
        String orangeTypeAndAge = goods.getOrangeTypeAndAge();
        if (orangeTypeAndAge != null) {
            String[] orangeTypes = orangeTypeAndAge.split("-");
            String orangeType = orangeTypes[0];
            String treeAge = orangeTypes[1];
            goods.setOrangeType(orangeType);
            goods.setTreeAge(BigDecimal.valueOf(Long.parseLong(treeAge)));
        }

        //添加商品o
        int goodsRows = goodsMapper.insertGoods(goods);

        //添加商品和规格关联关系
        List<GoodsSpecs> goodsSpecsList = new LinkedList<>();

        for (Specs specs : specsList) {
            GoodsSpecs goodsSpecs = new GoodsSpecs();
            goodsSpecs.setGoodsIdP(goods.getGoodsId());
            goodsSpecs.setSpecsIdP(specs.getSpecsId());
            goodsSpecs.setCreateTime(date);
            goodsSpecs.setCreateBy(loginName);
            goodsSpecsList.add(goodsSpecs);
        }

        int goodsSpecsRows = goodsSpecsService.batchAddGoodsSpecs(goodsSpecsList);


        if (specsRows != specsList.size() || goodsRows == 0 || goodsSpecsRows != specsList.size()) {
            throw new BusinessException("商品添加失败");
        }

        //为预订商品增加预订操作
        if (goods.getType() == 2) {
            BookGoods bookGoods = goodsDto.getBook();
            bookGoods.setShopId(goods.getShopId());
            bookGoods.setGoodsId(goods.getGoodsId());
            int i = bookGoodsService.saveBookGoods(bookGoods);
            if (i == 0) {
                throw new BusinessException("添加预订操作失败");
            }
        }

        //为竞拍商品增加竞拍活动
        if (goods.getType() == 3) {

            Auction auction = goodsDto.getAuction();
            auction.setGoodsId(goods.getGoodsId());

            for (Specs specs : specsList) {
                auction.setAuctionStartingprice(specs.getSpecsPrice());
            }

            int i = auctionService.addAuction(auction);

            if (i == 0) {
                throw new BusinessException("添加竞拍操作失败");
            }
        }


        return goodsRows * specsRows;
    }


    /**
     * 修改商品
     *
     * @param goodsDto 后台传入的商品信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateGoods(GoodsDto goodsDto) {

        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        //判断是否有足够的可销售重量
        String specsIdsString =goodsDto.getGoods().getSpecsIds();
        boolean judgeRs=judgeSaleAllWeightAndStock(goodsDto.getGoods().getShopId(),goodsDto.getSpecsList(),specsIdsString);
        if (!judgeRs){
            throw new BusinessException("修改商品失败,可销售重量不够。");
        }

        //处理商品规格
        List<Specs> specsList=new ArrayList<>();
        handleSpecs(goodsDto,specsList);

        StringBuilder specsIdsSb = new StringBuilder();
        for (Specs specs : specsList) {
            specsIdsSb.append(",").append(specs.getSpecsId());
        }

        //更新商品信息
        Goods goods = goodsDto.getGoods();
        goods.setSpecsIds(specsIdsSb.toString().replaceFirst(",", ""));
        goods.setUpdateTime(date);
        goods.setUpdateBy(loginName);

        //拆分脐橙类型和树龄
        String orangeTypeAndAge = goods.getOrangeTypeAndAge();
        if (orangeTypeAndAge != null) {
            String[] orangeTypes = orangeTypeAndAge.split("-");
            String orangeType = orangeTypes[0];
            String treeAge = orangeTypes[1];
            goods.setOrangeType(orangeType);
            goods.setTreeAge(BigDecimal.valueOf(Long.valueOf(treeAge)));
        }

        int goodsRows = goodsMapper.updateGoods(goods);

        //验证添加数量
        if (goodsRows == 0 ) {
            throw new BusinessException("修改商品失败");
        }

        //预订商品修改
        if (goods.getType() == 2) {
            BookGoods bookGoods = goodsDto.getBook();
            bookGoods.setShopId(goods.getShopId());
            bookGoods.setGoodsId(goods.getGoodsId());
            int i = bookGoodsService.saveBookGoods(bookGoods);
            if (i == 0) {
                throw new BusinessException("修改商品失败");
            }
        }

        //竞拍，
        if (goods.getType() == 3) {
            Auction auction = goodsDto.getAuction();
            auction.setGoodsId(goods.getGoodsId());
            for (Specs specs : specsList) {
                auction.setAuctionStartingprice(new BigDecimal(specs.getSpecsPrice().toString()));
            }
            auctionService.addAuction(auction);
        }

        return goodsRows;
    }


    /**
     * 处理规格逻辑删除
     * @param goodsDto
     * @param specsList
     */
    private void handleSpecs(GoodsDto goodsDto,List<Specs> specsList){
        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        Goods goods=goodsDto.getGoods();
        String tempSpecsIds="";
        List<Specs> tempSpecsList=goodsDto.getSpecsList();
        for (int i = 0; i <tempSpecsList.size() ; i++) {
            Specs tempSpecs=tempSpecsList.get(i);
            Long specsId=tempSpecs.getSpecsId();
            if (specsId==null||specsId==0){
                //新增
                tempSpecs.setDelFlag("0");//正常
                tempSpecs.setCreateTime(date);
                tempSpecs.setCreateBy(loginName);
                specsMapper.insertSpecs(tempSpecs);
                GoodsSpecs goodsSpecs = new GoodsSpecs();
                goodsSpecs.setGoodsIdP(goods.getGoodsId());
                goodsSpecs.setSpecsIdP(tempSpecs.getSpecsId());
                goodsSpecs.setCreateTime(date);
                goodsSpecs.setCreateBy(loginName);
                goodsSpecsMapper.insertGoodsSpecs(goodsSpecs);
                specsList.add(tempSpecs);
            }else{
                //修改
                tempSpecs.setDelFlag("0");//正常
                tempSpecs.setUpdateTime(date);
                tempSpecs.setUpdateBy(loginName);
                specsMapper.updateSpecs(tempSpecs);
                tempSpecsIds=tempSpecsIds+","+tempSpecs.getSpecsId();
                specsList.add(tempSpecs);
            }
        }


        String oldSpecsIds=goodsDto.getGoods().getSpecsIds();
        String [] oldSpecsArr=oldSpecsIds.split(",");
        for (int i = 0; i < oldSpecsArr.length; i++) {
            String tempOldSpecsId=oldSpecsArr[i];
            if (tempSpecsIds.indexOf(tempOldSpecsId)==-1){
                Specs specs=specsMapper.selectSpecsById(Long.valueOf(tempOldSpecsId));
                //如果有订单，则不允许删除对应的规格
                OrderItem orderItem=orderItemMapper.selectOrderItemGoodsIdAndSpecsId(goods.getGoodsId(),specs.getSpecsId());
                if (orderItem!=null){
                    throw new BusinessException("修改商品失败,不允许删除有订单的规格。SpecsId:"+specs.getSpecsId());
                }
                specs.setDelFlag("1");//已删除
                specs.setStock(0);//逻辑删除库存归0
                //删除商品规格关联关系
                GoodsSpecs goodsSpecs=goodsSpecsMapper.selectGoodsIdAndSpecsId(goods.getGoodsId(),specs.getSpecsId());
                goodsSpecsMapper.deleteGoodsSpecsById(goodsSpecs.getGoodsSpecId());
                specsMapper.updateSpecs(specs);
            }
        }
    }



    /**
     * 删除商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteGoodsByIds(String ids) {

        StringBuilder specsIds = new StringBuilder();

        StringBuilder bookGoodsIds = new StringBuilder();

        List<Goods> goods = goodsMapper.selectGoodsByIds(Convert.toStrArray(ids));

        for (Goods good : goods) {

            specsIds.append(",").append(good.getSpecsIds());

            //如果是预订商品则需要需要将商品的预订活动一并删除
            if (good.getType() == 2) {
                bookGoodsIds.append(",").append(good.getGoodsId());
            }
        }

        //删除规格
        specsMapper.deleteSpecsByIds(Convert.toStrArray(specsIds.toString().replaceFirst(",", "")));

        //删除关联关系
        goodsSpecsMapper.deleteGoodsSpecsByGoodsIds(Convert.toStrArray(ids));

        //删除预订活动
        String[] goodsIds = Convert.toStrArray(bookGoodsIds.toString().replaceFirst(",", ""));

        if (goodsIds.length > 0) {
            bookGoodsService.deleteBookGoodsByGoodsIds(goodsIds);
        }

        //删除商品
        return goodsMapper.deleteGoodsByIds(Convert.toStrArray(ids));
    }

    /**
     * 获取预订商品的销售情况
     *
     * @param goodsId 商品ID
     * @return 结果
     */
    @Override
    public List<Map<String, String>> getBookGoodsSellInfo(Long goodsId) {

        List<Map<String, String>> mapList = goodsMapper.selectBookGoodsSellInfo(goodsId);

        for (Map<String, String> map : mapList) {
            String address = map.get("address");
            String receiver = map.get("receiver");
            map.put("receiver", receiver.charAt(0) + "**");
            map.put("address", address.substring(0, address.indexOf("市") + 1));
        }

        return mapList;
    }

    @Override
    public boolean judgeSaleAllWeightAndStock(Long shopId,List<Specs> changeSpecsList ,String inSpecsIds) {
        Shop shop=shopMapper.selectShopById(shopId);
        if(shop==null){
            throw new BusinessException("店铺不存在：shopId:"+shopId);
        }
        if(inSpecsIds==null)
        {
            inSpecsIds="";
        }
        //总可销售重量
        //BigDecimal saleAllWeight=shop.getSaleAllWeight();
        //TODO 库存与销量的问题
        BigDecimal saleAllWeight=new BigDecimal("1000000000");
        if(saleAllWeight==null){
            throw new BusinessException("店铺可销量为空：shopId:"+shopId);
        }

        //订单明细总重量
        BigDecimal orderItemWeight=new BigDecimal("0.00");
        List<OrderItem> orderItemList=orderItemMapper.queryShopOrderItem(shopId);
        if(orderItemList!=null){
            for (int i = 0; i < orderItemList.size(); i++) {
                OrderItem orderItem=orderItemList.get(i);
                Integer goodsCount=orderItem.getGoodsCount();
                BigDecimal perWeight=orderItem.getPerWeight();
                if (perWeight!=null&&goodsCount!=null){
                    orderItemWeight=orderItemWeight.add(perWeight.multiply(new BigDecimal(goodsCount)));
                }
            }
        }

        //规格总重量
        Specs specs=new Specs();
        specs.setShopId(shopId);
        List<Specs> specsList=specsMapper.selectSpecsList(specs);
        BigDecimal specsWeight=new BigDecimal("0.00");
        if (specsList!=null){
            for (int i = 0; i < specsList.size(); i++) {
                Specs tempSpecs=specsList.get(i);
                String specsString=String.valueOf(tempSpecs.getSpecsId());
                if(inSpecsIds.indexOf(specsString)==-1){
                    BigDecimal perWeight=tempSpecs.getPerWeight();
                    Integer stock=tempSpecs.getStock();
                    if(perWeight!=null){
                        specsWeight=specsWeight.add(perWeight.multiply(new BigDecimal(stock)));
                    }
                }else {
                    //去除原有的规格
                    continue;
                }
            }
        }

        //变化的规格的总重量
        if(changeSpecsList!=null){
            for (int i = 0; i < changeSpecsList.size(); i++) {
                Specs tempSpecs=changeSpecsList.get(i);
                BigDecimal perWeight=tempSpecs.getPerWeight();
                Integer stock=tempSpecs.getStock();
                if(perWeight!=null){
                    specsWeight=specsWeight.add(perWeight.multiply(new BigDecimal(stock)));
                }
            }
        }

        if(saleAllWeight.compareTo(specsWeight.add(orderItemWeight))==1){
            //总可销售重量  > 规格总重量加上订单总重量
            return true;
        }else {
            return false;
        }
    }


    @Override
    public BigDecimal nabSaleAllWeight(Long shopId) {
        return nabSaleAllWeightPri(shopId);
    }

    @Override
    public BigDecimal nabOrderItemWeight(Long shopId) {
        return nabOrderItemWeightPri(shopId);
    }

    @Override
    public BigDecimal nabSpecsWeight(Long shopId, List<Specs> changeSpecsList, String inSpecsIds) {
        return nabSpecsWeightPri(shopId,changeSpecsList,inSpecsIds);
    }

    /**
     * 统计商品销售量
     * @param goodsId
     * @param orderType 订单类型：0，认养订单，1，交易订单,2,预订订单  3,竞拍订单
     * @return
     */
    @Override
    public Integer countGoods(Long goodsId,int orderType){
        return goodsMapper.countGoods(goodsId, orderType);
    }

    @Override
    public Integer goodsCount(){
        return goodsMapper.goodsCount();
    }

    private BigDecimal nabSaleAllWeightPri(Long shopId){
        Shop shop=shopMapper.selectShopById(shopId);
        if(shop==null){
            throw new BusinessException("店铺不存在：shopId:"+shopId);
        }
        //总可销售重量
        //BigDecimal saleAllWeight=shop.getSaleAllWeight();
        //TODO 库存与销量的问题
        BigDecimal saleAllWeight=new BigDecimal("100000000");
        if(saleAllWeight==null){
            throw new BusinessException("店铺可销量为空：shopId:"+shopId);
        }
        return saleAllWeight;
    }

    private BigDecimal nabOrderItemWeightPri(Long shopId){
        //订单明细总重量
        BigDecimal orderItemWeight=new BigDecimal("0.00");
        List<OrderItem> orderItemList=orderItemMapper.queryShopOrderItem(shopId);
        if(orderItemList!=null){
            for (int i = 0; i < orderItemList.size(); i++) {
                OrderItem orderItem=orderItemList.get(i);
                Integer goodsCount=orderItem.getGoodsCount();
                BigDecimal perWeight=orderItem.getPerWeight();
                if (perWeight!=null&&goodsCount!=null){
                    orderItemWeight=orderItemWeight.add(perWeight.multiply(new BigDecimal(goodsCount)));
                }
            }
        }
        return orderItemWeight;
    }

    private BigDecimal nabSpecsWeightPri(Long shopId, List<Specs> changeSpecsList, String inSpecsIds){
        //规格总重量
        Specs specs=new Specs();
        specs.setShopId(shopId);
        List<Specs> specsList=specsMapper.selectSpecsList(specs);
        BigDecimal specsWeight=new BigDecimal("0.00");
        if (specsList!=null){
            for (int i = 0; i < specsList.size(); i++) {
                Specs tempSpecs=specsList.get(i);
                String specsString=String.valueOf(tempSpecs.getSpecsId());
                if(inSpecsIds.indexOf(specsString)==-1){
                    BigDecimal perWeight=tempSpecs.getPerWeight();
                    Integer stock=tempSpecs.getStock();
                    if(perWeight!=null){
                        specsWeight=specsWeight.add(perWeight.multiply(new BigDecimal(stock)));
                    }
                }else {
                    //去除原有的规格
                    continue;
                }
            }
        }

        //变化的规格的总重量
        if(changeSpecsList!=null){
            for (int i = 0; i < changeSpecsList.size(); i++) {
                Specs tempSpecs=changeSpecsList.get(i);
                BigDecimal perWeight=tempSpecs.getPerWeight();
                Integer stock=tempSpecs.getStock();
                if(perWeight!=null){
                    specsWeight=specsWeight.add(perWeight.multiply(new BigDecimal(stock)));
                }
            }
        }
        return specsWeight;
    }

}
