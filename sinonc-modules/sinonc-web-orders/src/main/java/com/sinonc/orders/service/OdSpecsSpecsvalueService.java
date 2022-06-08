package com.sinonc.orders.service;

import com.sinonc.orders.domain.OdSpecsSpecsvalue;

import java.util.List;

/**
 * 规格_规格值关联 服务层
 *
 * @author sinonc
 * @date 2019-08-07
 */
public interface OdSpecsSpecsvalueService {

	/**
     * 查询规格_规格值关联信息
     *
     * @param specsValueId 规格_规格值关联ID
     * @return 规格_规格值关联信息
     */
	public OdSpecsSpecsvalue getOdSpecsSpecsvalueById(Long specsValueId);

	/**
     * 查询规格_规格值关联列表
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 规格_规格值关联集合
     */
	public List<OdSpecsSpecsvalue> listOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 新增规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	public int addOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 修改规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	public int updateOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue);

	/**
     * 删除规格_规格值关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOdSpecsSpecsvalueByIds(Long[] specsvalueids);

	/**
	 * 根据规格id查询该规格下的所有规格值
	 * @param specsId
	 * @return
	 */
    List<OdSpecsSpecsvalue> selectSpecsSpecsValueBySpecsId(Long specsId);
}
