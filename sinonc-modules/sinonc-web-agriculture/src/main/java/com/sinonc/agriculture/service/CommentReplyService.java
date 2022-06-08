package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.CommentReply;

/**
 * 评论回复Service接口
 * 
 * @author ruoyi
 * @date 2020-03-17
 */
public interface CommentReplyService
{
    /**
     * 查询评论回复
     * 
     * @param replyId 评论回复ID
     * @return 评论回复
     */
    public CommentReply getCommentReplyById(Long replyId);

    /**
     * 查询评论回复列表
     * 
     * @param commentReply 评论回复
     * @return 评论回复集合
     */
    public List<CommentReply> getCommentReplyList(CommentReply commentReply);

    /**
     * 新增评论回复
     * 
     * @param commentReply 评论回复
     * @return 结果
     */
    public int addCommentReply(CommentReply commentReply);

    /**
     * 修改评论回复
     * 
     * @param commentReply 评论回复
     * @return 结果
     */
    public int updateCommentReply(CommentReply commentReply);

    /**
     * 批量删除评论回复
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentReplyByIds(String ids);

    /**
     * 删除评论回复信息
     * 
     * @param replyId 评论回复ID
     * @return 结果
     */
    public int deleteCommentReplyById(Long replyId);
}
