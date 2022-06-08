package com.sinonc.orders.service.impl;

import java.math.BigDecimal;
import java.util.List;

import cn.hutool.core.lang.Assert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.vo.GoodsDetailVo;
import com.sinonc.orders.vo.SpecsDetailVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.service.IOdMemberAttentionService;

/**
 * 用户收藏商品Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-31
 */
@Service
public class OdMemberAttentionServiceImpl implements IOdMemberAttentionService {
    @Autowired
    private OdMemberAttentionMapper odMemberAttentionMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AdoptItemMapper adoptItemMapper;

    @Autowired
    private GoodsDetailVoMapper goodsDetailVoMapper;

    /**
     * 查询用户收藏商品
     *
     * @param id 用户收藏商品ID
     * @return 用户收藏商品
     */
    @Override
    public OdMemberAttention selectOdMemberAttentionById(Long id) {
        return odMemberAttentionMapper.selectOdMemberAttentionById(id);
    }

    @Override
    public int selectOdMemberAttentionCountByUserId(Long userId) {
        return odMemberAttentionMapper.getCountByUserId(userId);
    }

    /**
     * 查询用户收藏商品列表
     *
     * @param odMemberAttention 用户收藏商品
     * @return 用户收藏商品
     */
    @Override
    public List<OdMemberAttention> selectOdMemberAttentionList(OdMemberAttention odMemberAttention) {
        return odMemberAttentionMapper.selectOdMemberAttentionList(odMemberAttention);
    }

    /**
     * 新增用户收藏商品
     *
     * @param odMemberAttention 用户收藏商品
     * @return 结果
     */
    @Override
    public int insertOdMemberAttention(OdMemberAttention odMemberAttention) {
        if(odMemberAttention.getAttentionType() == null || odMemberAttention.getTargetId() == null){
            return 0;
        }
        List<OdMemberAttention> odMemberAttentions = odMemberAttentionMapper.selectOdMemberAttentionList(odMemberAttention);
        if(odMemberAttentions.size() != 0 && odMemberAttentions.get(0).getStatus() == 1){
            OdMemberAttention odMemberAttentionTmp = odMemberAttentions.get(0);
            odMemberAttentionTmp.setStatus(0);
            odMemberAttentionTmp.setUpdateTime(DateUtils.getNowDate());
            return odMemberAttentionMapper.updateOdMemberAttention(odMemberAttentionTmp);
        }else if(odMemberAttentions.size() != 0 && odMemberAttentions.get(0).getStatus() == 0){
            return 1;
        }
        odMemberAttention.setStatus(0);
        odMemberAttention.setCreateTime(DateUtils.getNowDate());
        return odMemberAttentionMapper.insertOdMemberAttention(odMemberAttention);
    }

    /**
     * 修改用户收藏商品
     *
     * @param odMemberAttention 用户收藏商品
     * @return 结果
     */
    @Override
    public int updateOdMemberAttention(OdMemberAttention odMemberAttention) {
        odMemberAttention.setUpdateTime(DateUtils.getNowDate());
        return odMemberAttentionMapper.updateOdMemberAttention(odMemberAttention);
    }

    /**
     * 批量删除用户收藏商品
     *
     * @param ids 需要删除的用户收藏商品ID
     * @return 结果
     */
    @Override
    public int deleteOdMemberAttentionByIds(Long[] ids) {
        return odMemberAttentionMapper.deleteOdMemberAttentionByIds(ids);
    }

    /**
     * 删除用户收藏商品信息
     *
     * @param id 用户收藏商品ID
     * @return 结果
     */
    @Override
    public int deleteOdMemberAttentionById(Long id) {
        return odMemberAttentionMapper.deleteOdMemberAttentionById(id);
    }

    @Override
    public int deleteOdMemberAttentionByAnyway(OdMemberAttention odMemberAttention) {
        List<OdMemberAttention> odMemberAttentions = odMemberAttentionMapper.selectOdMemberAttentionList(odMemberAttention);
        if(odMemberAttentions.size() != 0 && odMemberAttentions.get(0).getStatus() == 0){
            OdMemberAttention odMemberAttentionTmp = odMemberAttentions.get(0);
            odMemberAttentionTmp.setStatus(1);
            odMemberAttentionTmp.setUpdateTime(DateUtils.getNowDate());
            return odMemberAttentionMapper.updateOdMemberAttention(odMemberAttentionTmp);
        }
        if(odMemberAttentions.size() != 0 && odMemberAttentions.get(0).getStatus() == 1){
            return 1;
        }
//        OdMemberAttention attention = odMemberAttentionMapper.selectOdMemberAttentionById(odMemberAttention.getId());
//        if(attention != null && attention.getStatus() != 1){
//            attention.setStatus(1);
//            attention.setUpdateTime(DateUtils.getNowDate());
//            return odMemberAttentionMapper.updateOdMemberAttention(attention);
//        }
        return 0;
    }

    @Override
    public List<MemberFollowDescription> selectOdMemberFollowDescriptionList(OdMemberAttention odMemberAttention) {
        List<MemberFollowDescription> memberAttentionList = odMemberAttentionMapper.selectOdMemberAttentionDescriptionList(odMemberAttention);
        memberAttentionList.forEach(obj -> {
            switch (obj.getAttentionType()){
                case 3:{
                    Auction auction = auctionMapper.selectAuctionById(obj.getTargetId());
                    obj.setAuctionEndDate(auction.getAuctionEndtime());
                    Goods goods = goodsMapper.selectGoodsById(auction.getGoodsId());
                    if(ObjectUtils.isNotEmpty(goods)){
                        obj.setTitle(goods.getName());
                        obj.setImage(goods.getImage());
                        getPrice(obj, auction.getGoodsId());
                    }else{
                        obj.setTitle("失效商品");
                    }
                    break;
                }
                case 0:
                case 1:
                case 2: {
                    Goods goods = goodsMapper.selectGoodsById(obj.getTargetId());
                    if(ObjectUtils.isNotEmpty(goods)){
                        obj.setTitle(goods.getName());
                        obj.setImage(goods.getImage());
                        getPrice(obj, obj.getTargetId());
                    }else{
                        obj.setTitle("失效商品");
                    }
                    break;
                }
                default:
                    break;
            }
        });
        return memberAttentionList;
    }

    private void getPrice(MemberFollowDescription obj, Long goodId){
        GoodsDetailVo goodsDetailVo = goodsDetailVoMapper.selectByGoodsId(goodId);
        if(!org.springframework.util.ObjectUtils.isEmpty(goodsDetailVo)){
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
            obj.setPrice(goodsDetailVo.getPrice());
        }else{
            obj.setPrice("商品已失效");
        }
    }
}
