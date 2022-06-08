package com.sinonc.orders.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdSpecsMapper;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.service.IOdSpecsService;

/**
 * 规格Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@Service
public class OdSpecsServiceImpl implements IOdSpecsService {
    @Autowired
    private OdSpecsMapper odSpecsMapper;

    /**
     * 查询规格
     *
     * @param specsId 规格ID
     * @return 规格
     */
    @Override
    public Specs selectOdSpecsById(Long specsId) {
        return odSpecsMapper.selectOdSpecsById(specsId);
    }

    /**
     * 查询规格列表
     *
     * @param odSpecs 规格
     * @return 规格
     */
    @Override
    public List<Specs> selectOdSpecsList(Specs odSpecs) {
        return odSpecsMapper.selectOdSpecsList(odSpecs);
    }

    /**
     * 新增规格
     *
     * @param odSpecs 规格
     * @return 结果
     */
    @Override
    public int insertOdSpecs(Specs odSpecs) {
        odSpecs.setCreateTime(DateUtils.getNowDate());
        return odSpecsMapper.insertOdSpecs(odSpecs);
    }

    /**
     * 修改规格
     *
     * @param odSpecs 规格
     * @return 结果
     */
    @Override
    public int updateOdSpecs(Specs odSpecs) {
        odSpecs.setUpdateTime(DateUtils.getNowDate());
        return odSpecsMapper.updateOdSpecs(odSpecs);
    }

    /**
     * 批量删除规格
     *
     * @param specsIds 需要删除的规格ID
     * @return 结果
     */
    @Override
    public int deleteOdSpecsByIds(Long[] specsIds) {
        return odSpecsMapper.deleteOdSpecsByIds(specsIds);
    }

    /**
     * 删除规格信息
     *
     * @param specsId 规格ID
     * @return 结果
     */
    @Override
    public int deleteOdSpecsById(Long specsId) {
        return odSpecsMapper.deleteOdSpecsById(specsId);
    }
}
