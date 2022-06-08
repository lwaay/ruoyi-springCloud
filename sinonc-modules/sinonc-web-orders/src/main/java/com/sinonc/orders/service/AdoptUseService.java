package com.sinonc.orders.service;

import com.sinonc.orders.domain.AdoptUse;

import java.util.List;

/**
 * 服务使用记录 服务层
 *
 * @author sinonc
 * @date 2019-08-14
 */
public interface AdoptUseService {

	/**
     * 查询服务使用记录信息
     *
     * @param useId 服务使用记录ID
     * @return 服务使用记录信息
     */
	public AdoptUse getAdoptUseById(Integer useId);

	/**
     * 查询服务使用记录列表
     *
     * @param adoptUse 服务使用记录信息
     * @return 服务使用记录集合
     */
	public List<AdoptUse> listAdoptUse(AdoptUse adoptUse);

	/**
     * 新增服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	public int addAdoptUse(AdoptUse adoptUse);

	/**
     * 修改服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	public int updateAdoptUse(AdoptUse adoptUse);

	/**
     * 删除服务使用记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptUseByIds(String ids);

}
