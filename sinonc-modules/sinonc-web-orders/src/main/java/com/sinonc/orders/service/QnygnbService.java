package com.sinonc.orders.service;

import com.sinonc.orders.domain.Qnygnb;

import java.util.List;

/**
 * 轻农业信丰脐橙果农版用户关联 服务层
 *
 * @author sinonc
 * @date 2019-12-11
 */
public interface QnygnbService {

	/**
     * 查询轻农业信丰脐橙果农版用户关联信息
     *
     * @param qnyId 轻农业信丰脐橙果农版用户关联ID
     * @return 轻农业信丰脐橙果农版用户关联信息
     */
	public Qnygnb getQnygnbById(Long qnyId);

	/**
     * 查询轻农业信丰脐橙果农版用户关联列表
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 轻农业信丰脐橙果农版用户关联集合
     */
	public List<Qnygnb> listQnygnb(Qnygnb qnygnb);

	/**
     * 新增轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	public int addQnygnb(Qnygnb qnygnb);

	/**
     * 修改轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	public int updateQnygnb(Qnygnb qnygnb);

	/**
     * 删除轻农业信丰脐橙果农版用户关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteQnygnbByIds(String ids);

	/**
	 * 根据shopId查询轻农业本店铺的问题
	 * @param shopId
	 * @return
	 */
	List<Qnygnb> selectQnyGnbListByShopId(Long shopId);

	/**
	 * 根据shopId查询轻农业本店铺的问题根据年月查问题
	 * @param shopId
	 * @param year
	 * @param month
	 * @return
	 */
	List<Qnygnb> selectQnyGnbListByShopIdForYearAndMonth(Long shopId, String year, String month);
}
