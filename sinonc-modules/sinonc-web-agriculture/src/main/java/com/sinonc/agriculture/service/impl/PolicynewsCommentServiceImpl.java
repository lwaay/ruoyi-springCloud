package com.sinonc.agriculture.service.impl;

import java.util.List;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.PolicynewsCommentMapper;
import com.sinonc.agriculture.domain.PolicynewsComment;
import com.sinonc.agriculture.service.PolicynewsCommentService;
import com.sinonc.common.core.text.Convert;

/**
 * 政策新闻评论回复Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class PolicynewsCommentServiceImpl implements PolicynewsCommentService
{
    @Autowired
    private PolicynewsCommentMapper policynewsCommentMapper;

    /**
     * 查询政策新闻评论回复
     *
     * @param replyId 政策新闻评论回复ID
     * @return 政策新闻评论回复
     */
    @Override
    public PolicynewsComment getPolicynewsCommentById(Long replyId)
    {
        return policynewsCommentMapper.selectPolicynewsCommentById(replyId);
    }

    /**
     * 查询政策新闻评论回复列表
     *
     * @param policynewsComment 政策新闻评论回复
     * @return 政策新闻评论回复
     */
    @Override
    public List<PolicynewsComment> getPolicynewsCommentList(PolicynewsComment policynewsComment)
    {
        return policynewsCommentMapper.selectPolicynewsCommentList(policynewsComment);
    }

    /**
     * 新增政策新闻评论回复
     *
     * @param policynewsComment 政策新闻评论回复
     * @return 结果
     */
    @Override
    public int addPolicynewsComment(PolicynewsComment policynewsComment)
    {
        policynewsComment.setCreateTime(DateUtils.getNowDate());
        return policynewsCommentMapper.insertPolicynewsComment(policynewsComment);
    }

    /**
     * 修改政策新闻评论回复
     *
     * @param policynewsComment 政策新闻评论回复
     * @return 结果
     */
    @Override
    public int updatePolicynewsComment(PolicynewsComment policynewsComment)
    {
        return policynewsCommentMapper.updatePolicynewsComment(policynewsComment);
    }

    /**
     * 删除政策新闻评论回复对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePolicynewsCommentByIds(String ids)
    {
        return policynewsCommentMapper.deletePolicynewsCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除政策新闻评论回复信息
     *
     * @param replyId 政策新闻评论回复ID
     * @return 结果
     */
    @Override
    public int deletePolicynewsCommentById(Long replyId)
    {
        return policynewsCommentMapper.deletePolicynewsCommentById(replyId);
    }
}
