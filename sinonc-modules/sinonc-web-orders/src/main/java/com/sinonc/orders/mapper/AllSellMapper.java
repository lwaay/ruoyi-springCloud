package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AllSell;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 信丰脐橙销售 数据层
 *
 * @author sinonc
 * @date 2019-11-14
 */
@Mapper
public interface AllSellMapper {
	/**
     * 查询信丰脐橙销售信息
     *
     * @param id 信丰脐橙销售ID
     * @return 信丰脐橙销售信息
     */
	public AllSell selectAllSellById(Long id);

	/**
     * 查询信丰脐橙销售列表
     *
     * @param allSell 信丰脐橙销售信息
     * @return 信丰脐橙销售集合
     */
	public List<AllSell> selectAllSellList(AllSell allSell);

	/**
     * 新增信丰脐橙销售
     *
     * @param allSells 信丰脐橙销售信息
     * @return 结果
     */
	public int insertAllSell(@Param("list") List<AllSell> allSells);

	/**
     * 修改信丰脐橙销售
     *
     * @param allSell 信丰脐橙销售信息
     * @return 结果
     */
	public int updateAllSell(AllSell allSell);

	/**
     * 删除信丰脐橙销售
     *
     * @param id 信丰脐橙销售ID
     * @return 结果
     */
	public int deleteAllSellById(Long id);

	/**
     * 批量删除信丰脐橙销售
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAllSellByIds(String[] ids);

}
