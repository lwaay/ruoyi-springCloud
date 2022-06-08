package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AdoptLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 认养点赞 数据层
 *
 * @author sinonc
 * @date 2019-10-06
 */
@Mapper
public interface AdoptLikeMapper {
	/**
     * 查询认养点赞信息
     *
     * @param likeId 认养点赞ID
     * @return 认养点赞信息
     */
	public AdoptLike selectAdoptLikeById(Long likeId);

	/**
     * 查询认养点赞列表
     *
     * @param adoptLike 认养点赞信息
     * @return 认养点赞集合
     */
	public List<AdoptLike> selectAdoptLikeList(AdoptLike adoptLike);


	public List<AdoptLike> selectAdoptLikeList1(AdoptLike adoptLike);

	public List<AdoptLike> listAdoptLike(AdoptLike adoptLike);

	/**
     * 新增认养点赞
     *
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	public int insertAdoptLike(AdoptLike adoptLike);

	/**
     * 修改认养点赞
     *
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	public int updateAdoptLike(AdoptLike adoptLike);

	/**
     * 删除认养点赞
     *
     * @param likeId 认养点赞ID
     * @return 结果
     */
	public int deleteAdoptLikeById(Long likeId);

	/**
     * 批量删除认养点赞
     *
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptLikeByIds(String[] likeIds);


	public int deleteAdoptLike(AdoptLike adoptLike);

}
