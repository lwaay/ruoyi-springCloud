package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.OdSpecsSpecsvalue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规格_规格值关联 数据层
 *
 * @author sinonc
 * @date 2019-08-07
 */
@Mapper
public interface OdSpecsSpecsvalueMapper {
	/**
     * 查询规格_规格值关联信息
     *
     * @param specsValueId 规格_规格值关联ID
     * @return 规格_规格值关联信息
     */
	public OdSpecsSpecsvalue selectOdSpecsSpecsvalueById(Long specsValueId);

	/**
     * 查询规格_规格值关联列表
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 规格_规格值关联集合
     */
	public List<OdSpecsSpecsvalue> selectOdSpecsSpecsvalueList(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 新增规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	public int insertOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 修改规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	public int updateOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 删除规格_规格值关联
     *
     * @param specsValueId 规格_规格值关联ID
     * @return 结果
     */
	public int deleteOdSpecsSpecsvalueById(Long specsValueId);

	/**
     * 批量删除规格_规格值关联
     *
     * @param specsValueIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOdSpecsSpecsvalueByIds(Long[] specsValueIds);

	/**
	 * 根据规格id查询该规格下的所有规格值
	 * @param specsId
	 * @return
	 */
    List<OdSpecsSpecsvalue> selectSpecsSpecsValueBySpecsId(Long specsId);

	/**
	 * 删除规格-规格属性关联表中specsValueIdP为specsValueIdP的记录，
	 * @param specsValueIdP
	 * @return
	 */
	public int deleteOdSpecsSpecsvalueBySpecsValueIdP(Long specsValueIdP);
}
