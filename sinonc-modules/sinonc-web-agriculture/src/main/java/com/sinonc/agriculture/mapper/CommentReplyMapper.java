package com.sinonc.agriculture.mapper;

import java.util.List;
import java.util.Map;

import com.sinonc.agriculture.domain.CommentReply;

/**
 * 评论回复Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-17
 */
public interface CommentReplyMapper
{
    /**
     * 查询评论回复
     *
     * @param replyId 评论回复ID
     * @return 评论回复
     */
    public CommentReply selectCommentReplyById(Long replyId);

    /**
     * 查询评论回复列表
     *
     * @param commentReply 评论回复
     * @return 评论回复集合
     */
    public List<CommentReply> selectCommentReplyList(CommentReply commentReply);

    /**
     * 新增评论回复
     *
     * @param commentReply 评论回复
     * @return 结果
     */
    public int insertCommentReply(CommentReply commentReply);

    /**
     * 修改评论回复
     *
     * @param commentReply 评论回复
     * @return 结果
     */
    public int updateCommentReply(CommentReply commentReply);

    /**
     * 删除评论回复
     *
     * @param replyId 评论回复ID
     * @return 结果
     */
    public int deleteCommentReplyById(Long replyId);

    /**
     * 批量删除评论回复
     *
     * @param replyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentReplyByIds(String[] replyIds);


    public List<Map<String, Object>> selectCommentReplyListForMap(Long replyMemberIdP);

}
