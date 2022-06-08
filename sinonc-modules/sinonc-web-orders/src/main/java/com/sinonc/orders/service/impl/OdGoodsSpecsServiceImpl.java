package com.sinonc.orders.service.impl;

import java.util.List;
                                                                    import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdGoodsSpecsMapper;
import com.sinonc.orders.domain.OdGoodsSpecs;
import com.sinonc.orders.service.IOdGoodsSpecsService;

/**
 * 商品规格Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class OdGoodsSpecsServiceImpl implements IOdGoodsSpecsService {
    @Autowired
    private OdGoodsSpecsMapper odGoodsSpecsMapper;

    /**
     * 查询商品规格
     *
     * @param goodsSpecId 商品规格ID
     * @return 商品规格
     */
    @Override
    public OdGoodsSpecs selectOdGoodsSpecsById(Long goodsSpecId) {
        return odGoodsSpecsMapper.selectOdGoodsSpecsById(goodsSpecId);
    }

    /**
     * 查询商品规格列表
     *
     * @param odGoodsSpecs 商品规格
     * @return 商品规格
     */
    @Override
    public List<OdGoodsSpecs> selectOdGoodsSpecsList(OdGoodsSpecs odGoodsSpecs) {
        return odGoodsSpecsMapper.selectOdGoodsSpecsList(odGoodsSpecs);
    }

    /**
     * 新增商品规格
     *
     * @param odGoodsSpecs 商品规格
     * @return 结果
     */
    @Override
    public int insertOdGoodsSpecs(OdGoodsSpecs odGoodsSpecs) {
                                                                                                                    odGoodsSpecs.setCreateTime(DateUtils.getNowDate());
                                                return odGoodsSpecsMapper.insertOdGoodsSpecs(odGoodsSpecs);
    }

    /**
     * 修改商品规格
     *
     * @param odGoodsSpecs 商品规格
     * @return 结果
     */
    @Override
    public int updateOdGoodsSpecs(OdGoodsSpecs odGoodsSpecs) {
                                                                                                                                        odGoodsSpecs.setUpdateTime(DateUtils.getNowDate());
                            return odGoodsSpecsMapper.updateOdGoodsSpecs(odGoodsSpecs);
    }

    /**
     * 批量删除商品规格
     *
     * @param goodsSpecIds 需要删除的商品规格ID
     * @return 结果
     */
    @Override
    public int deleteOdGoodsSpecsByIds(Long[] goodsSpecIds) {
        return odGoodsSpecsMapper.deleteOdGoodsSpecsByIds(goodsSpecIds);
    }

    /**
     * 删除商品规格信息
     *
     * @param goodsSpecId 商品规格ID
     * @return 结果
     */
    @Override
    public int deleteOdGoodsSpecsById(Long goodsSpecId) {
        return odGoodsSpecsMapper.deleteOdGoodsSpecsById(goodsSpecId);
    }
}
