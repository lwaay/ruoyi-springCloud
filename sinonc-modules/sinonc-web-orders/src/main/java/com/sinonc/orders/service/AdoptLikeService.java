package com.sinonc.orders.service;

import com.sinonc.orders.domain.AdoptLike;

import java.util.List;

/**
 * 认养点赞 服务层
 *
 * @author sinonc
 * @date 2019-10-06
 */
public interface AdoptLikeService {

	/**
     * 查询认养点赞信息
     *
     * @param likeId 认养点赞ID
     * @return 认养点赞信息
     */
	public AdoptLike getAdoptLikeById(Long likeId);

	/**
     * 查询认养点赞列表
     *
     * @param adoptLike 认养点赞信息
     * @return 认养点赞集合
     */
	public List<AdoptLike> listAdoptLike(AdoptLike adoptLike);

	/**
     * 新增认养点赞
     *
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	public int addAdoptLike(AdoptLike adoptLike);

	/**
     * 修改认养点赞
     *
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	public int updateAdoptLike(AdoptLike adoptLike);

	/**
     * 删除认养点赞信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptLikeByIds(String ids);

	/**
	 * 删除认养点赞
	 *
	 * @param likeId 认养点赞ID
	 * @return 结果
	 */
	public int deleteAdoptLikeById(Long likeId);

	public int deleteAdoptLike(AdoptLike adoptLike);

}
