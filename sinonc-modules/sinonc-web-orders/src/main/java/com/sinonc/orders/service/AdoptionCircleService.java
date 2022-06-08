package com.sinonc.orders.service;

import com.sinonc.orders.domain.AdoptionCircle;

import java.util.List;
import java.util.Map;

/**
 * 朋友圈 服务层
 *
 * @author sinonc
 * @date 2019-08-14
 */
public interface AdoptionCircleService {

	/**
     * 查询朋友圈信息
     *
     * @param adoptionId 朋友圈ID
     * @return 朋友圈信息
     */
	public AdoptionCircle getAdoptionCircleById(Long adoptionId);

	/**
     * 查询朋友圈列表
     *
     * @param adoptionCircle 朋友圈信息
     * @return 朋友圈集合
     */
	public List<AdoptionCircle> listAdoptionCircle(AdoptionCircle adoptionCircle);

	/**
     * 新增朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	public int addAdoptionCircle(AdoptionCircle adoptionCircle);

	/**
     * 修改朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	public int updateAdoptionCircle(AdoptionCircle adoptionCircle);

	/**
	 * 修改朋友圈(限定id)
	 *
	 * @param adoptionCircle 朋友圈信息
	 * @return 结果
	 */
	public int updateAdoptionCircleWithMemberId(AdoptionCircle adoptionCircle);

	/**
     * 删除朋友圈信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptionCircleByIds(String ids);

	/**
	 * Api查询朋友圈信息
	 * @return
	 */
    List<Map<String, Object>> selectAdoptionCircleListForApi();

	/**
	 * Api查询朋友圈信息
	 * @return
	 */
	List<Map<String, Object>> selectAdoptionCircleListForApiTwo();

	/**
	 * 新增朋友圈(后台)
	 *
	 * @param adoptionCircle 朋友圈
	 * @return 结果
	 */
	public int insertAdoptionCircle(AdoptionCircle adoptionCircle);

}
