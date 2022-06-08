package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.OdSpecsValue;

/**
 * 规格属性Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-25
 */
public interface OdSpecsValueMapper {
    /**
     * 查询规格属性
     *
     * @param specsValueId 规格属性ID
     * @return 规格属性
     */
    public OdSpecsValue selectOdSpecsValueById(Long specsValueId);

    /**
     * 查询规格属性列表
     *
     * @param odSpecsValue 规格属性
     * @return 规格属性集合
     */
    public List<OdSpecsValue> selectOdSpecsValueList(OdSpecsValue odSpecsValue);

    /**
     * 新增规格属性
     *
     * @param odSpecsValue 规格属性
     * @return 结果
     */
    public int insertOdSpecsValue(OdSpecsValue odSpecsValue);

    /**
     * 修改规格属性
     *
     * @param odSpecsValue 规格属性
     * @return 结果
     */
    public int updateOdSpecsValue(OdSpecsValue odSpecsValue);

    /**
     * 删除规格属性
     *
     * @param specsValueId 规格属性ID
     * @return 结果
     */
    public int deleteOdSpecsValueById(Long specsValueId);

    /**
     * 批量删除规格属性
     *
     * @param specsValueIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdSpecsValueByIds(Long[] specsValueIds);
}
