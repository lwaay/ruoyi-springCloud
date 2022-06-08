package com.sinonc.orders.service;

import com.sinonc.orders.domain.Specs;

import java.util.List;

/**
 * 规格 服务层
 *
 * @author sinonc
 * @date 2019-07-26
 */
public interface SpecsService {

	/**
     * 查询规格信息
     *
     * @param specsId 规格ID
     * @return 规格信息
     */
	public Specs getSpecsById(Long specsId);

	/**
	 * 查询规格信息
	 * @param specsIds 规格Ids
	 * @return 结果
	 */
	public List<Specs> getSpecsByIds(String specsIds);

	/**
     * 查询规格列表
     *
     * @param specs 规格信息
     * @return 规格集合
     */
	public List<Specs> listSpecs(Specs specs);


	/**
     * 新增规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	public int addSpecs(Specs specs);

	/**
     * 修改规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	public int updateSpecs(Specs specs);

	/**
     * 删除规格信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSpecsByIds(String ids);

	/**
	 * 查询商品规格下拉列表
	 * @return
	 */
	public List<Specs> listSelectSpecs(Long shopId);

	public List<Specs> getGoodsSpecsByIds(String[] ids);

}
