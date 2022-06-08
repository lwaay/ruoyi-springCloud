package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertDynamicComment;

import java.util.List;

/**
 * 专家动态评论Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ExpertDynamicCommentMapper {
    /**
     * 查询专家动态评论
     *
     * @param commentId 专家动态评论ID
     * @return 专家动态评论
     */
    public ExpertDynamicComment selectExpertDynamicCommentById(Long commentId);

    /**
     * 查询专家动态评论列表
     *
     * @param expertDynamicComment 专家动态评论
     * @return 专家动态评论集合
     */
    public List<ExpertDynamicComment> selectExpertDynamicCommentList(ExpertDynamicComment expertDynamicComment);

    /**
     * 新增专家动态评论
     *
     * @param expertDynamicComment 专家动态评论
     * @return 结果
     */
    public int insertExpertDynamicComment(ExpertDynamicComment expertDynamicComment);

    /**
     * 修改专家动态评论
     *
     * @param expertDynamicComment 专家动态评论
     * @return 结果
     */
    public int updateExpertDynamicComment(ExpertDynamicComment expertDynamicComment);

    /**
     * 删除专家动态评论
     *
     * @param commentId 专家动态评论ID
     * @return 结果
     */
    public int deleteExpertDynamicCommentById(Long commentId);

    /**
     * 批量删除专家动态评论
     *
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertDynamicCommentByIds(String[] commentIds);
}
