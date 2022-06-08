package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertDynamicLike;

import java.util.List;

/**
 * 专家动态点赞Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ExpertDynamicLikeMapper {
    /**
     * 查询专家动态点赞
     *
     * @param likeId 专家动态点赞ID
     * @return 专家动态点赞
     */
    public ExpertDynamicLike selectExpertDynamicLikeById(Long likeId);

    /**
     * 查询专家动态点赞列表
     *
     * @param expertDynamicLike 专家动态点赞
     * @return 专家动态点赞集合
     */
    public List<ExpertDynamicLike> selectExpertDynamicLikeList(ExpertDynamicLike expertDynamicLike);

    /**
     * 新增专家动态点赞
     *
     * @param expertDynamicLike 专家动态点赞
     * @return 结果
     */
    public int insertExpertDynamicLike(ExpertDynamicLike expertDynamicLike);

    /**
     * 修改专家动态点赞
     *
     * @param expertDynamicLike 专家动态点赞
     * @return 结果
     */
    public int updateExpertDynamicLike(ExpertDynamicLike expertDynamicLike);

    /**
     * 删除专家动态点赞
     *
     * @param likeId 专家动态点赞ID
     * @return 结果
     */
    public int deleteExpertDynamicLikeById(Long likeId);

    /**
     * 批量删除专家动态点赞
     *
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertDynamicLikeByIds(String[] likeIds);

    /**
     * @param memberId
     * @param dynamicIds
     * @return
     */
    List<ExpertDynamicLike> selectByMemberIdAndDynamicIds(Long memberId, List<Long> dynamicIds);

    /**
     * 查询会员是否已经点过赞了
     *
     * @param memberId  会员ID
     * @param dynamicId 动态ID
     * @return
     */
    ExpertDynamicLike selectByMemberIdAndDynamicId(Long memberId, Long dynamicId);

    /**
     * 根据 dynamicId
     *
     * @param dynamicId
     * @return
     */
    int deleteByDynamicId(Long dynamicId);
}
