package com.sinonc.agriculture.service;

import java.util.List;

import com.sinonc.agriculture.domain.CommentReply;
import com.sinonc.agriculture.domain.GrowtechComment;

/**
 * 养殖技术评论Service接口
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
public interface GrowtechCommentService
{
    /**
     * 查询养殖技术评论
     *
     * @param commentId 养殖技术评论ID
     * @return 养殖技术评论
     */
    public GrowtechComment selectGrowtechCommentById(Long commentId);

    /**
     * 查询养殖技术评论列表
     *
     * @param growtechComment 养殖技术评论
     * @return 养殖技术评论集合
     */
    public List<GrowtechComment> selectGrowtechCommentList(GrowtechComment growtechComment);

    /**
     * 新增养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    public int insertGrowtechComment(GrowtechComment growtechComment);

    /**
     * 修改养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    public int updateGrowtechComment(GrowtechComment growtechComment);

    /**
     * 批量删除养殖技术评论
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowtechCommentByIds(String ids);

    /**
     * 删除养殖技术评论信息
     *
     * @param commentId 养殖技术评论ID
     * @return 结果
     */
    public int deleteGrowtechCommentById(Long commentId);

    /**
     * 新增评论
     * @param growtechIdP
     * @param memberId
     * @param replyId
     * @param content
     * @return
     */
    public int addGrowtechComment(Long growtechIdP, Long memberId, Long replyId, String content);

    public int addCommentReply(CommentReply commentReply);
}
