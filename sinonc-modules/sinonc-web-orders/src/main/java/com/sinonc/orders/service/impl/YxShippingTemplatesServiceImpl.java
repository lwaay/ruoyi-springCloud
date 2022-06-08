package com.sinonc.orders.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.IdUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.*;
import com.sinonc.orders.enums.ShippingTempEnum;
import com.sinonc.orders.mapper.YxShippingTemplatesFreeMapper;
import com.sinonc.orders.mapper.YxShippingTemplatesRegionMapper;
import com.sinonc.orders.service.GoodsService;
import com.sinonc.orders.service.SpecsService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.YxShippingTemplatesMapper;
import com.sinonc.orders.service.IYxShippingTemplatesService;
import org.springframework.transaction.annotation.Transactional;
import rx.Completable;

/**
 * 运费模板Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class YxShippingTemplatesServiceImpl implements IYxShippingTemplatesService {
    @Autowired
    private YxShippingTemplatesMapper yxShippingTemplatesMapper;
    @Autowired
    private YxShippingTemplatesFreeMapper freeMapper;
    @Autowired
    private YxShippingTemplatesRegionMapper regionMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SpecsService specsService;


    /**
     * 查询运费模板
     *
     * @param id 运费模板ID
     * @return 运费模板
     */
    @Override
    public YxShippingTemplates selectYxShippingTemplatesById(Integer id) {
        return yxShippingTemplatesMapper.selectYxShippingTemplatesById(id);
    }

    /**
     * 查询运费模板列表
     *
     * @param yxShippingTemplates 运费模板
     * @return 运费模板
     */
    @Override
    public List<YxShippingTemplates> selectYxShippingTemplatesList(YxShippingTemplates yxShippingTemplates) {
        return yxShippingTemplatesMapper.selectYxShippingTemplatesList(yxShippingTemplates);
    }

    /**
     * 新增运费模板
     *
     * @param yxShippingTemplates 运费模板
     * @return 结果
     */
    @Override
    public int insertYxShippingTemplates(YxShippingTemplates yxShippingTemplates) {
        return yxShippingTemplatesMapper.insertYxShippingTemplates(yxShippingTemplates);
    }

    /**
     * 修改运费模板
     *
     * @param yxShippingTemplates 运费模板
     * @return 结果
     */
    @Override
    public int updateYxShippingTemplates(YxShippingTemplates yxShippingTemplates) {
        return yxShippingTemplatesMapper.updateYxShippingTemplates(yxShippingTemplates);
    }

    /**
     * 修改运费模板
     *
     * @param shippingTemplatesDto 运费模板
     * @return 结果
     */
    @Transactional
    @Override
    public int saveYxShippingTemplates(ShippingTemplatesDto shippingTemplatesDto){
         int res = 0;
         if (!Optional.ofNullable(shippingTemplatesDto).isPresent()){
             return res;
         }
        YxShippingTemplates shippingTemplates = new YxShippingTemplates();
        BeanUtils.copyProperties(shippingTemplatesDto,shippingTemplates);
        if (SecurityUtils.getEntity() !=null && SecurityUtils.getEntity()>0){
            shippingTemplates.setEntityId(SecurityUtils.getEntity());
        }
        shippingTemplates.setRegionInfo(JSON.toJSONString(shippingTemplatesDto.getRegionInfo()));
        shippingTemplates.setAppointInfo(JSON.toJSONString(shippingTemplatesDto.getAppointInfo()));
         if (shippingTemplatesDto.getId() == null ){
             res = yxShippingTemplatesMapper.insertYxShippingTemplates(shippingTemplates);
             if (res<1){
                 throw new BusinessException("添加运费模板失败");
             }
         }else {
             res = yxShippingTemplatesMapper.updateYxShippingTemplates(shippingTemplates);
             if (res<1){
                 throw new BusinessException("添加运费模板失败");
             }
         }
         this.saveFreeReigion(shippingTemplatesDto,shippingTemplates.getId());
         this.saveRegion(shippingTemplatesDto,shippingTemplates.getId());
         return res;
    }

    /**
     * 批量删除运费模板
     *
     * @param ids 需要删除的运费模板ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesByIds(Integer[] ids) {
        int res = 0;
        if (ids == null || ids.length<1){
            return res;

        }
        res = Arrays.stream(ids).mapToInt(item->{
           int del =  this.deleteYxShippingTemplatesById(item);
           freeMapper.deleteFreeByTempId(item);
           regionMapper.deleteRegionByTempId(item);
           return del;
        }).sum();
        return res;
    }

    /**
     * 删除运费模板信息
     *
     * @param id 运费模板ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesById(Integer id) {
        return yxShippingTemplatesMapper.deleteYxShippingTemplatesById(id);
    }

    /**
     * 保存包邮区域
     * @param yxShippingTemplates ShippingTemplatesDto
     * @param tempId 模板id
     */
    private void saveFreeReigion(ShippingTemplatesDto yxShippingTemplates,Integer tempId){

        if(yxShippingTemplates.getAppointInfo() == null
                || yxShippingTemplates.getAppointInfo().isEmpty()){
            return;
        }
        freeMapper.deleteFreeByTempId(tempId);
        List<YxShippingTemplatesFree> shippingTemplatesFrees = new ArrayList<>();
        List<AppointInfoDto> appointInfo = yxShippingTemplates.getAppointInfo();
        for (AppointInfoDto appointInfoDto : appointInfo){
            String uni = IdUtils.simpleUUID();
            if(appointInfoDto.getPlace() != null && !appointInfoDto.getPlace().isEmpty()){
                for (RegionDto regionDto : appointInfoDto.getPlace()){
                    if(regionDto.getChildren() != null && !regionDto.getChildren().isEmpty()){
                        for (RegionChildrenDto childrenDto : regionDto.getChildren()){
                            YxShippingTemplatesFree shippingTemplatesFree = YxShippingTemplatesFree.builder()
                                    .tempId(tempId)
                                    .number(new BigDecimal(appointInfoDto.getA_num()))
                                    .price(new BigDecimal(appointInfoDto.getA_price()))
                                    .type(yxShippingTemplates.getType())
                                    .uniqid(uni)
                                    .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                    .cityId(Integer.valueOf(childrenDto.getCity_id()))
                                    .build();
                            shippingTemplatesFrees.add(shippingTemplatesFree);
                        }
                    }
                }
            }
        }
        if(shippingTemplatesFrees.isEmpty()) {
            throw new BusinessException("请添加包邮区域");
        }
        shippingTemplatesFrees.forEach(item->freeMapper.insertYxShippingTemplatesFree(item));
    }

    /**
     * 保存模板设置的区域价格
     * @param yxShippingTemplates ShippingTemplatesDTO
     * @param tempId 运费模板id
     */
    private void saveRegion(ShippingTemplatesDto yxShippingTemplates,Integer tempId){
        regionMapper.deleteRegionByTempId(tempId);
        List<YxShippingTemplatesRegion> shippingTemplatesRegions = new ArrayList<>();
        List<RegionInfoDto> regionInfo = yxShippingTemplates.getRegionInfo();
        for (RegionInfoDto regionInfoDto : regionInfo){
            String uni = IdUtils.simpleUUID();
            if(regionInfoDto.getRegion() != null && !regionInfoDto.getRegion().isEmpty()){
                for (RegionDto regionDto : regionInfoDto.getRegion()){
                    if(regionDto.getChildren() != null && !regionDto.getChildren().isEmpty()){
                        for (RegionChildrenDto childrenDtp : regionDto.getChildren()){
                            YxShippingTemplatesRegion shippingTemplatesRegion = YxShippingTemplatesRegion.builder()
                                    .tempId(tempId)
                                    .first(new BigDecimal(regionInfoDto.getFirst()))
                                    .firstPrice(new BigDecimal(regionInfoDto.getPrice()))
                                    .continues(new BigDecimal(regionInfoDto.get_continue()))
                                    .continuePrice(new BigDecimal(regionInfoDto.getContinue_price()))
                                    .type(yxShippingTemplates.getType())
                                    .uniqid(uni)
                                    .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                    .cityId(Integer.valueOf(childrenDtp.getCity_id()))
                                    .build();
                            shippingTemplatesRegions.add(shippingTemplatesRegion);
                        }
                    }else{
                        YxShippingTemplatesRegion shippingTemplatesRegion = YxShippingTemplatesRegion.builder()
                                .tempId(tempId)
                                .first(new BigDecimal(regionInfoDto.getFirst()))
                                .firstPrice(new BigDecimal(regionInfoDto.getPrice()))
                                .continues(new BigDecimal(regionInfoDto.get_continue()))
                                .continuePrice(new BigDecimal(regionInfoDto.getContinue_price()))
                                .type(yxShippingTemplates.getType())
                                .uniqid(uni)
                                .provinceId(Integer.valueOf(regionDto.getCity_id()))
                                .build();
                        shippingTemplatesRegions.add(shippingTemplatesRegion);
                    }
                }
            }
        }

        if(shippingTemplatesRegions.isEmpty()) {
            throw new BusinessException("请添加区域");
        }
        shippingTemplatesRegions.forEach(item->regionMapper.insertYxShippingTemplatesRegion(item));
    }

    /**
     * 根据运费模板算法返回邮费
     * @param userAddress 地址
     * @return double
     */
    @Override
    public BigDecimal handlePostage(List<OrderItem> items, Address userAddress) {
        BigDecimal storePostage = BigDecimal.ZERO;
        if (userAddress != null) {
            if (userAddress.getCity() == null) {
                return storePostage;
            }
            //城市包括默认
            String cityId = userAddress.getCity();
            List<String> citys = new ArrayList<>();
            citys.add(cityId);
            citys.add("0");

            List<Goods> storeProductVOList = items.stream().map(item->goodsService.getGoodsById(item.getGoodsIdP())).collect(Collectors.toList());
            List<Integer> tempIdS = storeProductVOList
                    .stream()
                    .map(Goods::getFareIdP)
                    .distinct()
                    .collect(Collectors.toList());


            //获取商品用到的运费模板
            List<YxShippingTemplates> shippingTemplatesList = tempIdS.stream().map(tempId->yxShippingTemplatesMapper.selectYxShippingTemplatesById(tempId)).collect(Collectors.toList());
            //获取运费模板区域列表按照城市排序
            List<YxShippingTemplatesRegion> shippingTemplatesRegionList = regionMapper.listRegionByTempAndCity(tempIdS,citys);
            //提取运费模板类型
            Map<Integer, Integer> shippingTemplatesMap = ObjectUtils.isNotEmpty(shippingTemplatesList)?shippingTemplatesList
                    .stream()
                    .collect(Collectors.toMap(YxShippingTemplates::getId,
                            YxShippingTemplates::getType)):new HashMap<>();
            //提取运费模板有相同值覆盖
            Map<Integer, YxShippingTemplatesRegion> shippingTemplatesRegionMap =
                    shippingTemplatesRegionList.stream()
                            .collect(Collectors.toMap(YxShippingTemplatesRegion::getTempId,
                                    YxShippingTemplatesRegion -> YxShippingTemplatesRegion,
                                    (key1, key2) -> key2));


            Map<Integer, TemplateDto> templateDTOMap = new HashMap<>();
            for (OrderItem item : items) {
                Goods goods = goodsService.getGoodsById(item.getGoodsIdP());
                Specs specs = specsService.getSpecsById(item.getGoodsSpecsIdP());
                if (!Optional.ofNullable(goods).isPresent() || !Optional.ofNullable(specs).isPresent()){
                    throw new BusinessException("获取商品信息失败,无法计算运费");
                }
                Integer tempId = goods.getFareIdP();

                //处理拼团等营销商品没有设置运费模板
                if (tempId == null) {
                    return storePostage;
                }

                //根据模板类型获取相应的数量
                double num = 0d;
                num = item.getGoodsCount().doubleValue();
                if (ShippingTempEnum.TYPE_1.getValue().equals(shippingTemplatesMap.get(tempId))) {
                    num = item.getGoodsCount().doubleValue();
                } else if (ShippingTempEnum.TYPE_2.getValue().equals(shippingTemplatesMap.get(tempId))) {
                    num = NumberUtil.mul(item.getGoodsCount().doubleValue(),
                            specs.getPerWeight().doubleValue());
                }

                YxShippingTemplatesRegion shippingTemplatesRegion = shippingTemplatesRegionMap.get(tempId);
                BigDecimal price = specs.getSpecsPrice();
                if (!templateDTOMap.containsKey(tempId)) {
                    TemplateDto templateDTO = TemplateDto.builder()
                            .number(num)
                            .price(price)
                            .first(shippingTemplatesRegion.getFirst().doubleValue())
                            .firstPrice(shippingTemplatesRegion.getFirstPrice())
                            ._continue(shippingTemplatesRegion.getContinues().doubleValue())
                            .continuePrice(shippingTemplatesRegion.getContinuePrice())
                            .tempId(tempId)
                            .cityId(Integer.parseInt(cityId))
                            .build();
                    templateDTOMap.put(tempId, templateDTO);
                } else {
                    TemplateDto templateDTO = templateDTOMap.get(tempId);
                    templateDTO.setNumber(templateDTO.getNumber() + num);
                    templateDTO.setPrice(NumberUtil.add(templateDTO.getPrice().doubleValue(), price));
                }


            }

            //处理包邮情况
            for (Map.Entry<Integer, TemplateDto> entry : templateDTOMap.entrySet()) {
                Integer mapKey = entry.getKey();
                TemplateDto mapValue = entry.getValue();

                Long count = freeMapper.countFree(mapValue.getTempId(),mapValue.getCityId(),mapValue.getNumber(),mapValue.getPrice());
                //满足包邮条件剔除
                if (count > 0L) {
                    templateDTOMap.remove(mapKey);
                }
            }

            //处理区域邮费
            boolean isFirst = true; //用来是否多个产品的标识 false表示数量大于1
            for (TemplateDto templateDTO : templateDTOMap.values()) {
                if (isFirst) {//首件
                    //只满足首件
                    if (Double.compare(templateDTO.getNumber(), templateDTO.getFirst()) <= 0) {
                        storePostage = NumberUtil.round(NumberUtil.add(storePostage,
                                templateDTO.getFirstPrice()), 2);
                    } else {
                        BigDecimal fristPrice = NumberUtil.add(storePostage, templateDTO.getFirstPrice());

                        if (templateDTO.get_continue() <= 0) {
                            storePostage = fristPrice;
                        } else {
                            //续件平均值且向上取整数
                            double average = Math.ceil(NumberUtil.div(NumberUtil.sub(templateDTO.getNumber(),
                                    templateDTO.getFirst()),
                                    templateDTO.get_continue().doubleValue()));
                            //最终邮费
                            storePostage = NumberUtil.add(fristPrice, NumberUtil.mul(average,
                                    templateDTO.getContinuePrice()));
                        }

                    }

                    isFirst = false;
                } else {
                    //多件直接在以前的基数继续续建
                    if (templateDTO.get_continue() > 0) {
                        //续件平均值且向上取整数
                        double average = Math.ceil(
                                NumberUtil.div(
                                        templateDTO.getNumber(),
                                        templateDTO.get_continue()
                                )
                        );
                        //最终邮费
                        storePostage = NumberUtil.add(storePostage.doubleValue(), NumberUtil.mul(average,
                                templateDTO.getContinuePrice()));
                    }
                }
            }
        }


        return storePostage;
    }
}
