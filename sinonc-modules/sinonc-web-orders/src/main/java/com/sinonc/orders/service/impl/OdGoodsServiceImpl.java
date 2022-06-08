package com.sinonc.orders.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.BookGoodsDto;
import com.sinonc.orders.dto.OdGoodsDto;
import com.sinonc.orders.enums.GoodsEnum;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.*;
import com.sinonc.orders.service.GoodsSpecsService;
import com.sinonc.orders.vo.OdGoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sinonc.orders.service.IOdGoodsService;

/**
 * 商品Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-01
 */
@Service
public class OdGoodsServiceImpl implements IOdGoodsService {
    @Autowired
    private OdGoodsMapper odGoodsMapper;
    @Autowired
    private SpecsMapper specsMapper;
    @Autowired
    private GoodsSpecsService goodsSpecsService;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private BookGoodsService bookGoodsService;
    @Autowired
    private AuctionService auctionService;

    /**
     * 查询商品
     *
     * @param goodsId 商品ID
     * @return 商品
     */
    @Override
    public OdGoods selectOdGoodsById(Long goodsId) {
        return odGoodsMapper.selectOdGoodsById(goodsId);
    }

    /**
     * 查询商品列表
     *
     * @param odGoods 商品
     * @return 商品
     */
    @Override
    public List<OdGoods> selectOdGoodsList(OdGoods odGoods) {
        List<OdGoods> odGoodsList=odGoodsMapper.selectOdGoodsList(odGoods);
        return odGoodsList;
    }

    /**
     * 新增商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    @Override
    public int insertOdGoods(OdGoods odGoods) {
        odGoods.setCreateTime(DateUtils.getNowDate());
        return odGoodsMapper.insertOdGoods(odGoods);
    }

    /**
     * 修改商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    @Override
    public int updateOdGoods(OdGoods odGoods) {
        odGoods.setUpdateTime(DateUtils.getNowDate());
        return odGoodsMapper.updateOdGoods(odGoods);
    }

    /**
     * 批量删除商品
     *
     * @param goodsIds 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteOdGoodsByIds(Long[] goodsIds) {
        return odGoodsMapper.deleteOdGoodsByIds(goodsIds);
    }

    /**
     * 删除商品信息
     *
     * @param goodsId 商品ID
     * @return 结果
     */
    @Override
    public int deleteOdGoodsById(Long goodsId) {
        return odGoodsMapper.deleteOdGoodsById(goodsId);
    }

    @Override
    public int addOdGoodsVo(OdGoodsVo odGoodsVo) throws Exception {
        List<Specs> specsList=createSpecsList(odGoodsVo);
        int specsRows = specsMapper.batchInsertSpecs(specsList);

        //将规格id转换为字符串，存入goods中
        StringBuilder specsIdSb = new StringBuilder();
        for (Specs specs : specsList) {
            specsIdSb.append(",").append(specs.getSpecsId());
        }
        String specsIds = specsIdSb.toString().replaceFirst(",", "");

        //插入直供商品
        OdGoods odGoods=new OdGoods();
        BeanUtils.copyProperties(odGoodsVo,odGoods);
        odGoods.setSpecsIds(specsIds);
        //直供商品
        odGoods.setType(1);
        odGoods.setCreateTime(DateUtils.getNowDate());
        odGoods.setUpdateTime(DateUtils.getNowDate());
        odGoodsMapper.insertOdGoods(odGoods);

        //添加商品和规格关联关系
        List<GoodsSpecs> goodsSpecsList = new LinkedList<>();

        for (Specs specs : specsList) {
            GoodsSpecs goodsSpecs = new GoodsSpecs();
            goodsSpecs.setGoodsIdP(odGoods.getGoodsId());
            goodsSpecs.setSpecsIdP(specs.getSpecsId());
            goodsSpecs.setCreateTime(DateUtils.getNowDate());
            goodsSpecsList.add(goodsSpecs);
        }
        goodsSpecsService.batchAddGoodsSpecs(goodsSpecsList);

        return specsRows;
    }

    private List<Specs> createSpecsList(OdGoodsVo odGoodsVo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Specs> specsList = new ArrayList<>();
        //规格属性
        String specsJson=odGoodsVo.getSpecsJson();

        List<HashMap> specsValues = mapper.readValue(specsJson,new TypeReference<List<HashMap>>() { });
        for (int i = 0; i <specsValues.size() ; i++) {
            HashMap spacsValueMap=specsValues.get(i);
            Specs specs=changMapToSpecs(spacsValueMap,odGoodsVo);
            specs.setCreateTime(DateUtils.getNowDate());
            specs.setUpdateTime(DateUtils.getNowDate());
            specsList.add(specs);
        }
        return specsList;
    }

    private Specs changMapToSpecs(Map map, OdGoodsVo odGoodsVo){
        Specs specs=new Specs();
        specs.setShopId(odGoodsVo.getShopId());

        specs.setSpecsName((String) map.get("specsName"));
        specs.setSpecsPrice(new BigDecimal(String.valueOf(map.get("specsPrice"))));
        specs.setUnit((String)map.get("unit"));
        specs.setPerWeight(new BigDecimal(String.valueOf(map.get("perWeight"))));
        specs.setStock(Integer.valueOf(String.valueOf(map.get("stock"))));
        return specs;
    }

    @Override
    public OdGoodsVo selectOdGoodsVoById(Long goodsId) throws Exception {
        OdGoods odGoods=odGoodsMapper.selectOdGoodsById(goodsId);
        OdGoodsVo odGoodsVo=new OdGoodsVo();
        BeanUtils.copyProperties(odGoods,odGoodsVo);
        String specsValuesJson=createSpecsJson(odGoods);
        odGoodsVo.setSpecsJson(specsValuesJson);
        return odGoodsVo;
    }

    @Override
    public int updateOdGoodsVo(OdGoodsVo odGoodsVo) throws Exception {
        Long goodsId=odGoodsVo.getGoodsId();
        OdGoods odGoods=odGoodsMapper.selectOdGoodsById(goodsId);
        BeanUtils.copyProperties(odGoodsVo,odGoods);

        //处理商品规格
        List<Specs> specsList=new ArrayList<>();
        handleSpecs(odGoodsVo,odGoods,specsList);

        StringBuilder specsIdsSb = new StringBuilder();
        for (Specs specs : specsList) {
            specsIdsSb.append(",").append(specs.getSpecsId());
        }

        //更新商品信息
        odGoods.setSpecsIds(specsIdsSb.toString().replaceFirst(",", ""));
        odGoods.setUpdateTime(DateUtils.getNowDate());

        int goodsRows = odGoodsMapper.updateOdGoods(odGoods);

        //验证添加数量
        if (goodsRows == 0 ) {
            throw new BusinessException("修改商品失败");
        }else {
            return goodsRows;
        }
    }

    /**
     * 添加预订商品
     * @param goodsDto
     * @return
     */
    @Transactional
    @Override
    public int addBookGoods(BookGoodsDto goodsDto){

        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        //添加规格
        List<Specs> specsList = goodsDto.getSpecsList();

        for (Specs specs : specsList) {
            specs.setCreateTime(date);
            specs.setCreateBy(loginName);
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
        OdGoods goods = goodsDto.getOdGoods();
        goods.setSpecsIds(specsIds);
        goods.setCreateTime(date);
        goods.setCreateBy(loginName);

        //添加商品o
        int goodsRows = odGoodsMapper.insertOdGoods(goods);

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

        if(GoodsEnum.TYPE_3.getValue().equals(goods.getType())){
            //添加预订商品
            BookGoods bookGoods = goodsDto.getBookGoods();
            bookGoods.setGoodsId(goods.getGoodsId());
            bookGoods.setStatus(0);
            bookGoodsService.saveBookGoods(bookGoods);
        }

        if(GoodsEnum.TYPE_4.getValue().equals(goods.getType())){
            //添加竞拍商品
            Auction auction = goodsDto.getAuction();
            auction.setGoodsId(goods.getGoodsId());
            auctionService.addAuction(auction);
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
    public int updateBookGoods(BookGoodsDto goodsDto) {

        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        //处理商品规格
        List<Specs> specsList=new ArrayList<>();
        handleBookSpecs(goodsDto,specsList);

        StringBuilder specsIdsSb = new StringBuilder();
        for (Specs specs : specsList) {
            specsIdsSb.append(",").append(specs.getSpecsId());
        }

        //更新商品信息
        OdGoods goods = goodsDto.getOdGoods();

        if(GoodsEnum.TYPE_3.getValue().equals(goods.getType())){
            //预订商品修改
            BookGoods bookGoods = goodsDto.getBookGoods();
            bookGoods.setShopId(goods.getShopId());
            bookGoods.setGoodsId(goods.getGoodsId());
            bookGoodsService.updateBookGoods(bookGoods);
        }

        if(GoodsEnum.TYPE_4.getValue().equals(goods.getType())){
            //修改竞拍商品
            Auction auction = goodsDto.getAuction();
            auction.setGoodsId(goods.getGoodsId());
            auctionService.updateAuction(auction);
        }

        goods.setSpecsIds(specsIdsSb.toString().replaceFirst(",", ""));
        goods.setUpdateTime(date);
        goods.setUpdateBy(loginName);

        int goodsRows = odGoodsMapper.updateOdGoods(goods);

        //验证添加数量
        if (goodsRows == 0 ) {
            throw new BusinessException("修改商品失败");
        }

        return goodsRows;
    }

    /**
     * 查询预订商品及规格列表
     * @param goodsId
     * @return
     */
    @Override
    public BookGoodsDto selectOdGoodsDtoById(Long goodsId){
        OdGoods odGoods = odGoodsMapper.selectOdGoodsById(goodsId);

        BookGoodsDto bookGoodsDto = new BookGoodsDto();
        List<Specs> specsList = specsMapper.selectSpecsByIds(odGoods.getSpecsIds().split(","));
        bookGoodsDto.setOdGoods(odGoods);
        bookGoodsDto.setSpecsList(specsList);
        if(GoodsEnum.TYPE_3.getValue().equals(odGoods.getType())){
            BookGoods bookGoods = bookGoodsService.getByGoodsId(goodsId);
            bookGoodsDto.setBookGoods(bookGoods);
        }
        if(GoodsEnum.TYPE_4.getValue().equals(odGoods.getType())){
            Auction auction = auctionService.selectAuctionForGoodsId(goodsId);
            bookGoodsDto.setAuction(auction);
        }
        return bookGoodsDto;
    }

    @Override
    public OdGoodsDto selectOdGoodsDetailById(Long goodsId) {
        OdGoods odGoods=odGoodsMapper.selectOdGoodsById(goodsId);
        OdGoodsDto odGoodsDto=new OdGoodsDto();
        BeanUtils.copyProperties(odGoods,odGoodsDto);

        //销量
        Long count=orderItemMapper.countGoodsSale(odGoods.getGoodsId());
        odGoodsDto.setSalesVolume(count);

        //规格
        List<Specs> specsList=getSpecsList(odGoods);
        odGoodsDto.setSpecsList(specsList);

        //店铺
        Shop shop=shopMapper.selectShopById(odGoods.getShopId());
        odGoodsDto.setShop(shop);

        return odGoodsDto;
    }

    @Override
    public List<OdGoods> selectOdGoodsListLimit(OdGoods odGoods) {
        List<OdGoods>  odGoodsList=odGoodsMapper.selectOdGoodsListLimit(odGoods);
        return odGoodsList;
    }

    @Override
    public List<OdGoods> selectRxOdGoodsByShopId(OdGoods odGoods) {
        List<OdGoods>  rxOdGoodsList=odGoodsMapper.selectRxOdGoodsByShopId(odGoods);
        return rxOdGoodsList;
    }


    private List<Specs>  getSpecsList(OdGoods odGoods){
        String specsIds=odGoods.getSpecsIds();
        String [] specsArray=specsIds.split(",");
        List<Specs> specsList=new ArrayList<>();
        for (int i = 0; i < specsArray.length; i++) {
            Specs specs=specsMapper.selectSpecsById(Long.valueOf(specsArray[i]));
            specsList.add(specs);
        }
        return specsList;
    }

    /**
     * 处理规格逻辑删除
     * @param specsList
     */
    private void handleSpecs(OdGoodsVo odGoodsVo, OdGoods odGoods,List<Specs> specsList) throws Exception {
        String tempSpecsIds="";
        List<Specs> tempSpecsList=createSpecsList(odGoodsVo);
        for (int i = 0; i <tempSpecsList.size() ; i++) {
            Specs tempSpecs=tempSpecsList.get(i);
            Long specsId=tempSpecs.getSpecsId();
            if (specsId==null||specsId==0){
                //新增
                tempSpecs.setDelFlag("0");//正常
                tempSpecs.setCreateTime(DateUtils.getNowDate());
                specsMapper.insertSpecs(tempSpecs);
                GoodsSpecs goodsSpecs = new GoodsSpecs();
                goodsSpecs.setGoodsIdP(odGoods.getGoodsId());
                goodsSpecs.setSpecsIdP(tempSpecs.getSpecsId());
                goodsSpecs.setCreateTime(DateUtils.getNowDate());
                goodsSpecsMapper.insertGoodsSpecs(goodsSpecs);
                specsList.add(tempSpecs);
            }else{
                //修改
                tempSpecs.setDelFlag("0");//正常
                tempSpecs.setUpdateTime(DateUtils.getNowDate());
                specsMapper.updateSpecs(tempSpecs);
                tempSpecsIds=tempSpecsIds+","+tempSpecs.getSpecsId();
                specsList.add(tempSpecs);
            }
        }


        String oldSpecsIds=odGoods.getSpecsIds();
        String [] oldSpecsArr=oldSpecsIds.split(",");
        for (int i = 0; i < oldSpecsArr.length; i++) {
            String tempOldSpecsId=oldSpecsArr[i];
            if (tempSpecsIds.indexOf(tempOldSpecsId)==-1){
                Specs specs=specsMapper.selectSpecsById(Long.valueOf(tempOldSpecsId));
                //如果有订单，则不允许删除对应的规格
                //先取消这条规则
                //OrderItem orderItem=orderItemMapper.selectOrderItemGoodsIdAndSpecsId(odGoods.getGoodsId(),specs.getSpecsId());
                //if (orderItem!=null){
                //    throw new BusinessException("修改商品失败,不允许删除有订单的规格。SpecsId:"+specs.getSpecsId());
                //}
                specs.setDelFlag("1");//已删除
                specs.setStock(0);//逻辑删除库存归0
                //删除商品规格关联关系
                GoodsSpecs goodsSpecs=goodsSpecsMapper.selectGoodsIdAndSpecsId(odGoods.getGoodsId(),specs.getSpecsId());
                goodsSpecsMapper.deleteGoodsSpecsById(goodsSpecs.getGoodsSpecId());
                specsMapper.updateSpecs(specs);
            }
        }
    }

    /**
     * 处理规格逻辑删除
     * @param goodsDto
     * @param specsList
     */
    private void handleBookSpecs(BookGoodsDto goodsDto, List<Specs> specsList){
        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        OdGoods goods=goodsDto.getOdGoods();
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


        String oldSpecsIds=goods.getSpecsIds();
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


    public String createSpecsJson(OdGoods odGoods) throws Exception{
        String specsIds=odGoods.getSpecsIds();
        String [] specsIdArray=specsIds.split(",");

        List<Specs> rsList=new ArrayList<>();
        for (int i = 0; i < specsIdArray.length; i++) {
            String specsId=specsIdArray[i];
            Specs specs=specsMapper.selectSpecsById(Long.valueOf(specsId));
            rsList.add(specs);
        }
        ObjectMapper mapper = new ObjectMapper();
        String specsValuesJson = mapper.writeValueAsString(rsList);
        return specsValuesJson;
    }
}
