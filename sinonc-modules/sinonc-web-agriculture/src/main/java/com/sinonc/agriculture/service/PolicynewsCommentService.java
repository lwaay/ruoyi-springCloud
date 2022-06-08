package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.PolicynewsComment;

/**
 * 政策新闻评论回复Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface PolicynewsCommentService
{
    /**
     * 查询政策新闻评论回复
     * 
     * @param replyId 政策新闻评论回复ID
     * @return 政策新闻评论回复
     */
    public PolicynewsComment getPolicynewsCommentById(Long replyId);

    /**
     * 查询政策新闻评论回复列表
     * 
     * @param policynewsComment 政策新闻评论回复
     * @return 政策新闻评论回复集合
     */
    public List<PolicynewsComment> getPolicynewsCommentList(PolicynewsComment policynewsComment);

    /**
     * 新增政策新闻评论回复
     * 
     * @param policynewsComment 政策新闻评论回复
     * @return 结果
     */
    public int addPolicynewsComment(PolicynewsComment policynewsComment);

    /**
     * 修改政策新闻评论回复
     * 
     * @param policynewsComment 政策新闻评论回复
     * @return 结果
     */
    public int updatePolicynewsComment(PolicynewsComment policynewsComment);

    /**
     * 批量删除政策新闻评论回复
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicynewsCommentByIds(String ids);

    /**
     * 删除政策新闻评论回复信息
     * 
     * @param replyId 政策新闻评论回复ID
     * @return 结果
     */
    public int deletePolicynewsCommentById(Long replyId);
}
