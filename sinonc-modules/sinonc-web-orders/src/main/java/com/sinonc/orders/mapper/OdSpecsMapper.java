package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.Specs;

/**
 * 规格Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-25
 */
public interface OdSpecsMapper {
    /**
     * 查询规格
     *
     * @param specsId 规格ID
     * @return 规格
     */
    public Specs selectOdSpecsById(Long specsId);

    /**
     * 查询规格列表
     *
     * @param odSpecs 规格
     * @return 规格集合
     */
    public List<Specs> selectOdSpecsList(Specs odSpecs);

    /**
     * 新增规格
     *
     * @param odSpecs 规格
     * @return 结果
     */
    public int insertOdSpecs(Specs odSpecs);

    /**
     * 修改规格
     *
     * @param odSpecs 规格
     * @return 结果
     */
    public int updateOdSpecs(Specs odSpecs);

    /**
     * 删除规格
     *
     * @param specsId 规格ID
     * @return 结果
     */
    public int deleteOdSpecsById(Long specsId);

    /**
     * 批量删除规格
     *
     * @param specsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdSpecsByIds(Long[] specsIds);
}
