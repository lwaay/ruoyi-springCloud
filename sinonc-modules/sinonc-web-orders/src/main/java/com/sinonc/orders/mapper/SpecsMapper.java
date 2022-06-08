package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Specs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格 数据层
 *
 * @author sinonc
 * @date 2019-07-26
 */
@Mapper
public interface SpecsMapper {
	/**
     * 查询规格信息
     *
     * @param specsId 规格ID
     * @return 规格信息
     */
	public Specs selectSpecsById(Long specsId);

	/**
	 * 根据运费模板ID查询规格
	 * @param fareId
	 * @return
	 */
	public List<Specs> selectSpecsByFareId(Long fareId);

	/**
	 * 查询规格信息
	 *
	 * @param specsId 规格ID
	 * @return 规格信息
	 */
	public Specs selectSpecsByIdForUpdate(Long specsId);

	/**
     * 查询规格列表
     *
     * @param specs 规格信息
     * @return 规格集合
     */
	public List<Specs> selectSpecsList(Specs specs);

	/**
     * 新增规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	public int insertSpecs(Specs specs);

	/**
	 * 批量新增规格
	 * @param specsList 规格列表
	 * @return 结果
	 */
	public int batchInsertSpecs(List<Specs> specsList);

	/**
     * 修改规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	public int updateSpecs(Specs specs);

	public int updateSpecsClearByFareId(@Param(value="fareId") Long fareId );

	/**
     * 删除规格
     *
     * @param specsId 规格ID
     * @return 结果
     */
	public int deleteSpecsById(Long specsId);

	/**
     * 批量删除规格
     *
     * @param specsIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSpecsByIds(String[] specsIds);

	/**
	 * 查询商品规格下拉列表
	 * @return
	 */
	public List<Specs> listSelectSpecs();

	/**
	 * 通过ids查询商品规格
	 * @param specsIds 规格id数组
	 * @return
	 */
    public List<Specs> selectSpecsByIds(String[] specsIds);
}
