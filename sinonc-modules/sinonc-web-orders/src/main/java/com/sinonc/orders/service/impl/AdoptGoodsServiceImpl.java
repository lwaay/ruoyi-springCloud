package com.sinonc.orders.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.AdoptGoodsService;
import com.sinonc.orders.service.GoodsService;
import com.sinonc.orders.service.GoodsSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 认养商品 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
@Transactional
public class AdoptGoodsServiceImpl implements AdoptGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    @Autowired
    private GoodsSpecsService goodsSpecsService;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private SpecsValueMapper specsValueMapper;

    @Autowired
    private OdSpecsSpecsvalueMapper odSpecsSpecsvalueMapper;
    @Autowired
    private GoodsService goodsService;


    @Override
	@Transactional
    public int addAdoptGoods(Goods goods) throws IOException {
        Date date = new Date();
        String loginName = SecurityUtils.getUsername();


        //规格
        Specs specs=new Specs();
        specs.setCreateTime(date);
        specs.setCreateBy(loginName);
        specs.setShopId(goods.getShopId());
        specs.setStock(goods.getStock());
        specs.setSpecsName(goods.getSpecsName());
        specs.setSpecsPrice(goods.getSpecsPrice());
        specs.setPerWeight(goods.getPerWeight());

        List specsList=new ArrayList();
        specsList.add(specs);

        //判断是否有可销售重量
        boolean judgeRs=goodsService.judgeSaleAllWeightAndStock(goods.getShopId(),specsList,"");
        if (!judgeRs){
            throw new BusinessException("新增商品失败,可销售重量不够。");
        }

        int specsRows=specsMapper.insertSpecs(specs);

        //商品
        goods.setCreateTime(date);
        goods.setCreateBy(loginName);
        goods.setType(0);//认养商品
        String orangeTypeAndAge=goods.getOrangeTypeAndAge();
        //拆分脐橙类型和树龄
        if(orangeTypeAndAge!=null){
            String [] orangeTypes=orangeTypeAndAge.split("-");
            String orangeType=orangeTypes[0];
            String treeAge=orangeTypes[1];
            goods.setOrangeType(orangeType);
            goods.setTreeAge(BigDecimal.valueOf(Long.valueOf(treeAge)));
        }


        goods.setSpecsIds(String.valueOf(specs.getSpecsId()));
        int rows = goodsMapper.insertGoods(goods);

        //商品规格关联表
        GoodsSpecs goodsSpecs=new GoodsSpecs();
        goodsSpecs.setCreateTime(date);
        goodsSpecs.setCreateBy(loginName);
        goodsSpecs.setGoodsIdP(goods.getGoodsId());
        goodsSpecs.setSpecsIdP(specs.getSpecsId());
        goodsSpecsMapper.insertGoodsSpecs(goodsSpecs);

        //规格属性
        String specsJson=goods.getSpecsJson();
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap> specsValues = mapper.readValue(specsJson,new TypeReference<List<HashMap>>() { });
        for (int i = 0; i <specsValues.size() ; i++) {
            HashMap spacsValueMap=specsValues.get(i);
            SpecsValue specsValue=changMapToSpecsValue(spacsValueMap,goods);
            specsValue.setSpecsId(specs.getSpecsId());
            specsValue.setCreateTime(date);
            specsValue.setCreateBy(loginName);
            int specsValueRows=specsValueMapper.insertSpecsValue(specsValue);

            //规格属性和规格的关联表
            OdSpecsSpecsvalue odSpecsSpecsvalue=new OdSpecsSpecsvalue();
            odSpecsSpecsvalue.setCreateTime(date);
            odSpecsSpecsvalue.setCreateBy(loginName);
            odSpecsSpecsvalue.setSpecsIdP(specs.getSpecsId());
            odSpecsSpecsvalue.setSpecsValueIdp(specsValue.getSpecsValueId());
            odSpecsSpecsvalue.setNumber(specsValue.getGuiNumbers());

            odSpecsSpecsvalueMapper.insertOdSpecsSpecsvalue(odSpecsSpecsvalue);
        }
        return rows;
    }


    /**
     * map转换为规格属性
     * @param map
     * @param goods
     * @return
     */
    private SpecsValue changMapToSpecsValue(Map map,Goods goods){
        SpecsValue specsValue=new SpecsValue();
        specsValue.setShopId(goods.getShopId());
        specsValue.setUnit((String)map.get("unit"));
        specsValue.setSpecsValue((String)map.get("specsValue"));
        specsValue.setNumber(Integer.valueOf(map.get("number").toString()));
        specsValue.setType(Integer.valueOf(map.get("type").toString()));
        specsValue.setGuiNumbers(Integer.valueOf(map.get("guiNumbers").toString()));
        return specsValue;
    }


    @Override
    public Specs selectSpecsByGoodId(Long goodsId) {
        GoodsSpecs goodsSpecs=goodsSpecsMapper.selectGoodsSpecsByGoodsId(goodsId);
        Long specsIdp=goodsSpecs.getSpecsIdP();
        Specs specs=specsMapper.selectSpecsById(specsIdp);
        return specs;
    }

    @Override
    public String selectSpecsValueListBySpecsId(Long specsId) throws Exception{
        List<OdSpecsSpecsvalue> specsSpecsvalueList=odSpecsSpecsvalueMapper.selectSpecsSpecsValueBySpecsId(specsId);

        List<SpecsValue> rsList=new ArrayList<SpecsValue>();
        for (int i = 0; i < specsSpecsvalueList.size(); i++) {
            OdSpecsSpecsvalue odSpecsSpecsvalue=specsSpecsvalueList.get(i);
            Long specsValueById=odSpecsSpecsvalue.getSpecsValueIdp();
            SpecsValue specsValue=specsValueMapper.selectSpecsValueById(specsValueById);
            specsValue.setGuiNumbers(odSpecsSpecsvalue.getNumber());
            rsList.add(specsValue);
        }
        ObjectMapper mapper = new ObjectMapper();
        String specsValuesJson = mapper.writeValueAsString(rsList);
        return specsValuesJson;
    }

    @Override
    @Transactional
    public int updateAdoptGoods(Goods goods) throws Exception{
        Date date = new Date();
        String loginName = SecurityUtils.getUsername();

        //更新商品
        goods.setUpdateTime(date);
        goods.setUpdateBy(loginName);
        goods.setType(0);//认养商品

        //拆分脐橙类型和树龄
        String orangeTypeAndAge=goods.getOrangeTypeAndAge();
        if(orangeTypeAndAge!=null){
            String [] orangeTypes=orangeTypeAndAge.split("-");
            String orangeType=orangeTypes[0];
            String treeAge=orangeTypes[1];
            goods.setOrangeType(orangeType);
            goods.setTreeAge(BigDecimal.valueOf(Long.valueOf(treeAge)));
        }

        int rows=goodsMapper.updateGoods(goods);

        //更新规格
        Specs specs=specsMapper.selectSpecsById(Long.parseLong(goods.getSpecsIds()));
        specs.setUpdateTime(date);
        specs.setUpdateBy(loginName);
        specs.setShopId(goods.getShopId());
        specs.setStock(goods.getStock());
        specs.setSpecsName(goods.getSpecsName());
        specs.setSpecsPrice(goods.getSpecsPrice());
        specs.setSpecsId(Long.parseLong(goods.getSpecsIds()));
        specs.setPerWeight(goods.getPerWeight());

        List specsList=new ArrayList();
        specsList.add(specs);
        //判断是否有可销售重量
        boolean judgeRs=goodsService.judgeSaleAllWeightAndStock(goods.getShopId(),specsList,goods.getSpecsIds());
        if (!judgeRs){
            throw new BusinessException("修改商品失败,可销售重量不够。");
        }
        specsMapper.updateSpecs(specs);

        //更新规格属性
        String specsJson=goods.getSpecsJson();
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap> specsValues = mapper.readValue(specsJson,new TypeReference<List<HashMap>>() { });
        String haveSpecsValueIds="";
        for (int i = 0; i <specsValues.size() ; i++) {
            HashMap spacsValueMap=specsValues.get(i);
            SpecsValue specsValue=changMapToSpecsValue(spacsValueMap,goods);
            specsValue.setSpecsId(specs.getSpecsId());
            specsValue.setCreateTime(date);
            specsValue.setCreateBy(loginName);
            if(spacsValueMap.get("specsValueId")!=null&&spacsValueMap.get("specsValueId")!=""){
                Integer specsValueId=(Integer)spacsValueMap.get("specsValueId");
                specsValue.setSpecsValueId(Long.parseLong(String.valueOf(specsValueId)));
                haveSpecsValueIds=haveSpecsValueIds+","+specsValueId;
                specsValueMapper.updateSpecsValue(specsValue);
            }else {
                int specsValueRows=specsValueMapper.insertSpecsValue(specsValue);

                //规格属性和规格的关联表
                OdSpecsSpecsvalue odSpecsSpecsvalue=new OdSpecsSpecsvalue();
                odSpecsSpecsvalue.setCreateTime(date);
                odSpecsSpecsvalue.setCreateBy(loginName);
                odSpecsSpecsvalue.setSpecsIdP(specs.getSpecsId());
                odSpecsSpecsvalue.setSpecsValueIdp(specsValue.getSpecsValueId());
                odSpecsSpecsvalue.setNumber(specsValue.getGuiNumbers());

                odSpecsSpecsvalueMapper.insertOdSpecsSpecsvalue(odSpecsSpecsvalue);
            }

        }
        //删除页面已经删掉的规格属性
        String specsValueids=goods.getSpecsValueids();
        String[] specsValueidArr=specsValueids.split(",");
        for (int i = 0; i <specsValueidArr.length ; i++) {
            String tempSpecsValueId=specsValueidArr[i];
            if(haveSpecsValueIds.indexOf(tempSpecsValueId)==-1){
                specsValueMapper.deleteSpecsValueById(Long.parseLong(tempSpecsValueId));
                odSpecsSpecsvalueMapper.deleteOdSpecsSpecsvalueBySpecsValueIdP(Long.parseLong(tempSpecsValueId));
            }
        }
        return rows;
    }

    @Override
    public String selectSpecsValueIds(Long specsId) {
        List<OdSpecsSpecsvalue> specsSpecsvalueList=odSpecsSpecsvalueMapper.selectSpecsSpecsValueBySpecsId(specsId);
        String specsValues="";
        for (int i = 0; i < specsSpecsvalueList.size(); i++) {
            OdSpecsSpecsvalue odSpecsSpecsvalue=specsSpecsvalueList.get(i);
            Long specsValueById=odSpecsSpecsvalue.getSpecsValueIdp();
            SpecsValue specsValue=specsValueMapper.selectSpecsValueById(specsValueById);
            if(specsValues.compareTo("")==0){
                specsValues=String.valueOf(specsValue.getSpecsValueId());
            }else {
                specsValues=specsValues+","+specsValue.getSpecsValueId();
            }
        }
        return specsValues;
    }

    @Override
    @Transactional
    public int deleteAdoptGoodsByIds(String ids) {
        String[] idArr=ids.split(",");
        int row=0;
        for (int i = 0; i <idArr.length ; i++) {
            Long goodId=Long.parseLong(idArr[i]);
            GoodsSpecs goodsSpecs=goodsSpecsMapper.selectGoodsSpecsByGoodsId(goodId);
            Long specsId=goodsSpecs.getSpecsIdP();
            List<OdSpecsSpecsvalue> odSpecsSpecsvalueList=odSpecsSpecsvalueMapper.selectSpecsSpecsValueBySpecsId(specsId);

            //删除规格属性及对应的关联关系表记录
            for (int j = 0; j < odSpecsSpecsvalueList.size(); j++) {
                OdSpecsSpecsvalue odSpecsSpecsvalue=odSpecsSpecsvalueList.get(j);
                row=row+odSpecsSpecsvalueMapper.deleteOdSpecsSpecsvalueById(odSpecsSpecsvalue.getSpecsValueId());
                row=row+specsValueMapper.deleteSpecsValueById(odSpecsSpecsvalue.getSpecsValueIdp());
            }
            //删除商品与规格关联关系表对应的记录
            row=row+goodsSpecsMapper.deleteGoodsSpecsById(goodsSpecs.getGoodsSpecId());
            //删除规格
            row=row+specsMapper.deleteSpecsById(goodsSpecs.getSpecsIdP());
            //删除商品
            row=row+goodsMapper.deleteGoodsById(goodId);
        }

        return row;
    }


}
