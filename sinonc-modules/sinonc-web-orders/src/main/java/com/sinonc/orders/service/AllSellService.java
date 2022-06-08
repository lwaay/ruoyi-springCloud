package com.sinonc.orders.service;

import com.sinonc.orders.domain.AllSell;

import java.util.List;

/**
 * 信丰脐橙销售 服务层
 *
 * @author sinonc
 * @date 2019-11-14
 */
public interface AllSellService {

	/**
     * 查询信丰脐橙销售信息
     *
     * @param id 信丰脐橙销售ID
     * @return 信丰脐橙销售信息
     */
	public AllSell getAllSellById(Long id);

	/**
     * 查询信丰脐橙销售列表
     *
     * @param allSell 信丰脐橙销售信息
     * @return 信丰脐橙销售集合
     */
	public List<AllSell> listAllSell(AllSell allSell);

	/**
     * 新增信丰脐橙销售
     *
     * @param allSell 信丰脐橙销售信息
     * @return 结果
     */
	public int addAllSell(List<AllSell> allSell);

	/**
     * 修改信丰脐橙销售
     *
     * @param allSell 信丰脐橙销售信息
     * @return 结果
     */
	public int updateAllSell(AllSell allSell);

	/**
     * 删除信丰脐橙销售信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAllSellByIds(String ids);

}
