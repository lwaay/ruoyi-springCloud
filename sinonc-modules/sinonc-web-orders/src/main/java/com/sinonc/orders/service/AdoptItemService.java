package com.sinonc.orders.service;

import com.sinonc.orders.domain.AdoptItem;
import com.sinonc.orders.vo.AdoptItemVo;

import java.util.List;

/**
 * 认养服务明细 服务层
 *
 * @author sinonc
 * @date 2019-08-09
 */
public interface AdoptItemService {

	/**
     * 查询认养服务明细信息
     *
     * @param adoptItemId 认养服务明细ID
     * @return 认养服务明细信息
     */
	public AdoptItem getAdoptItemById(Long adoptItemId);

	/**
     * 查询认养服务明细列表
     *
     * @param adoptItem 认养服务明细信息
     * @return 认养服务明细集合
     */
	public List<AdoptItem> listAdoptItem(AdoptItem adoptItem);

	/**
	 * 查询认养服务视图明细列表
	 * @param adoptItemVo 认养服务视图对象明细信息
	 * @return 认养服务视图明细
	 */
	public List<AdoptItemVo> listAdoptItemVo(AdoptItemVo adoptItemVo);


	/**
	 * 服务项目使用
	 * @param adoptItem 服务项目
	 * @return 结果
	 */
	public int AdoptItemUse(AdoptItem adoptItem);

	/**
     * 新增认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
	public int addAdoptItem(AdoptItem adoptItem);

	/**
     * 修改认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
	public int updateAdoptItem(AdoptItem adoptItem);

	/**
     * 删除认养服务明细信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptItemByIds(String ids);
}
