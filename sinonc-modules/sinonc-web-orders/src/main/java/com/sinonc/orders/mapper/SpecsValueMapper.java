package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.SpecsValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规格属性 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface SpecsValueMapper {
	/**
     * 查询规格属性信息
     *
     * @param specsValueId 规格属性ID
     * @return 规格属性信息
     */
	public SpecsValue selectSpecsValueById(Long specsValueId);

	/**
     * 查询规格属性列表
     *
     * @param specsValue 规格属性信息
     * @return 规格属性集合
     */
	public List<SpecsValue> selectSpecsValueList(SpecsValue specsValue);

	/**
     * 新增规格属性
     *
     * @param specsValue 规格属性信息
     * @return 结果
     */
	public int insertSpecsValue(SpecsValue specsValue);

	/**
     * 修改规格属性
     *
     * @param specsValue 规格属性信息
     * @return 结果
     */
	public int updateSpecsValue(SpecsValue specsValue);

	/**
     * 删除规格属性
     *
     * @param specsValueId 规格属性ID
     * @return 结果
     */
	public int deleteSpecsValueById(Long specsValueId);

	/**
     * 批量删除规格属性
     *
     * @param specsValueIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSpecsValueByIds(String[] specsValueIds);

}
