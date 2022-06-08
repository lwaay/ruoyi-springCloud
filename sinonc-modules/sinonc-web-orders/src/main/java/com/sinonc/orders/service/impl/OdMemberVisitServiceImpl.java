package com.sinonc.orders.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.OdMemberVisitDescription;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.mapper.GoodsDetailVoMapper;
import com.sinonc.orders.mapper.GoodsMapper;
import com.sinonc.orders.service.GoodsSpecsService;
import com.sinonc.orders.service.SpecsService;
import com.sinonc.orders.vo.GoodsDetailVo;
import com.sinonc.orders.vo.SpecsDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdMemberVisitMapper;
import com.sinonc.orders.domain.OdMemberVisit;
import com.sinonc.orders.service.IOdMemberVisitService;

/**
 * 访问记录Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-31
 */
@Service
public class OdMemberVisitServiceImpl implements IOdMemberVisitService {
    @Autowired
    private OdMemberVisitMapper odMemberVisitMapper;

    @Autowired
    private GoodsDetailVoMapper goodsDetailVoMapper;

    @Autowired
    private SpecsService specsService;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询访问记录
     *
     * @param id 访问记录ID
     * @return 访问记录
     */
    @Override
    public OdMemberVisit selectOdMemberVisitById(Long id) {
        return odMemberVisitMapper.selectOdMemberVisitById(id);
    }

    /**
     * 查询访问记录列表
     *
     * @param odMemberVisit 访问记录
     * @return 访问记录
     */
    @Override
    public List<OdMemberVisit> selectOdMemberVisitList(OdMemberVisit odMemberVisit) {
        return odMemberVisitMapper.selectOdMemberVisitList(odMemberVisit);
    }

    @Override
    public List<OdMemberVisitDescription> selectOdMemberVisitDescriptionList(OdMemberVisit odMemberVisit) {
        List<OdMemberVisitDescription> memberVisitDescriptionList = odMemberVisitMapper.selectOdMemberVisitDescriptionList(odMemberVisit);
        memberVisitDescriptionList.forEach(obj ->{
            Goods goods = goodsMapper.selectGoodsById(obj.getTargetId());
            if(goods != null){
                obj.setImage(goods.getImage());
                obj.setTitle(goods.getName());
                String[] specIds = goods.getSpecsIds().split(",", -1);
                String price = "";
                List<Specs> goodsSpecsByIds = specsService.getGoodsSpecsByIds(specIds);
                //所有规格中的最低价和最高价
                BigDecimal minPrice = new BigDecimal(0);
                BigDecimal maxPrice = new BigDecimal(0);

                for(Specs specsDetailVo : goodsSpecsByIds){

                    BigDecimal specsPrice = specsDetailVo.getSpecsPrice();

                    if (minPrice.equals(new BigDecimal(0)) || minPrice.compareTo(specsPrice) >= 0) {
                        minPrice = specsPrice;
                    }

                    if (maxPrice.equals(new BigDecimal(0)) || maxPrice.compareTo(specsPrice) <= 0) {
                        maxPrice = specsPrice;
                    }
                }
                if (maxPrice.compareTo(minPrice) == 0) {
                    price = maxPrice.toString();
                } else {
                    price = minPrice + "-" + maxPrice;
                }
                obj.setPrice(price);
            }
        });
        return memberVisitDescriptionList;
    }

    /**
     * 新增访问记录
     *
     * @param odMemberVisit 访问记录
     * @return 结果
     */
    @Override
    public int insertOdMemberVisit(OdMemberVisit odMemberVisit) {
        return odMemberVisitMapper.insertOdMemberVisit(odMemberVisit);
    }

    /**
     * 修改访问记录
     *
     * @param odMemberVisit 访问记录
     * @return 结果
     */
    @Override
    public int updateOdMemberVisit(OdMemberVisit odMemberVisit) {
        return odMemberVisitMapper.updateOdMemberVisit(odMemberVisit);
    }

    /**
     * 批量删除访问记录
     *
     * @param ids 需要删除的访问记录ID
     * @return 结果
     */
    @Override
    public int deleteOdMemberVisitByIds(Long[] ids) {
        return odMemberVisitMapper.deleteOdMemberVisitByIds(ids);
    }

    /**
     * 删除访问记录信息
     *
     * @param id 访问记录ID
     * @return 结果
     */
    @Override
    public int deleteOdMemberVisitById(Long id) {
        return odMemberVisitMapper.deleteOdMemberVisitById(id);
    }
}
