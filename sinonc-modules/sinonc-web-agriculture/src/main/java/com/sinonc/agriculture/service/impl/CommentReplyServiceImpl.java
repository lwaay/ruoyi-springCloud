package com.sinonc.agriculture.service.impl;

import java.util.List;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.CommentReplyMapper;
import com.sinonc.agriculture.domain.CommentReply;
import com.sinonc.agriculture.service.CommentReplyService;
import com.sinonc.common.core.text.Convert;

/**
 * 评论回复Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-17
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService
{
    @Autowired
    private CommentReplyMapper commentReplyMapper;

    /**
     * 查询评论回复
     *
     * @param replyId 评论回复ID
     * @return 评论回复
     */
    @Override
    public CommentReply getCommentReplyById(Long replyId)
    {
        return commentReplyMapper.selectCommentReplyById(replyId);
    }

    /**
     * 查询评论回复列表
     *
     * @param commentReply 评论回复
     * @return 评论回复
     */
    @Override
    public List<CommentReply> getCommentReplyList(CommentReply commentReply)
    {
        return commentReplyMapper.selectCommentReplyList(commentReply);
    }

    /**
     * 新增评论回复
     *
     * @param commentReply 评论回复
     * @return 结果
     */
    @Override
    public int addCommentReply(CommentReply commentReply)
    {
        commentReply.setCreateTime(DateUtils.getNowDate());
        return commentReplyMapper.insertCommentReply(commentReply);
    }

    /**
     * 修改评论回复
     *
     * @param commentReply 评论回复
     * @return 结果
     */
    @Override
    public int updateCommentReply(CommentReply commentReply)
    {
        return commentReplyMapper.updateCommentReply(commentReply);
    }

    /**
     * 删除评论回复对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommentReplyByIds(String ids)
    {
        return commentReplyMapper.deleteCommentReplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评论回复信息
     *
     * @param replyId 评论回复ID
     * @return 结果
     */
    @Override
    public int deleteCommentReplyById(Long replyId)
    {
        return commentReplyMapper.deleteCommentReplyById(replyId);
    }
}
