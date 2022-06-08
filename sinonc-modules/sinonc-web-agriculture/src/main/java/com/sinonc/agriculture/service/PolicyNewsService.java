package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.domain.PolicynewsComment;
import com.sinonc.agriculture.dto.PolicyNewsDto;
import com.sinonc.exchange.vo.PolicyNewsVo;

import java.util.List;

/**
 * 政策新闻Service接口
 *
 * @author zhang.xl
 * @date 2020-03-05
 */
public interface PolicyNewsService
{
    /**
     * 查询政策新闻
     *
     * @param newsId 政策新闻ID
     * @return 政策新闻
     */
    public PolicyNewsDto selectPolicyNewsDtoById(Long newsId);

    /**
     * 查询政策新闻列表
     *
     * @param policyNews 政策新闻
     * @return 政策新闻集合
     */
    public List<PolicyNews> selectPolicyNewsList(PolicyNews policyNews);

    /**
     * 新增政策新闻
     *
     * @param policyNews 政策新闻
     * @return 结果
     */
    public int insertPolicyNews(PolicyNews policyNews);

    /**
     * 修改政策新闻
     *
     * @param policyNews 政策新闻
     * @return 结果
     */
    public int updatePolicyNews(PolicyNews policyNews);

    /**
     * 批量删除政策新闻
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyNewsByIds(Long[] ids);

    /**
     * 删除政策新闻信息
     *
     * @param newsId 政策新闻ID
     * @return 结果
     */
    public int deletePolicyNewsById(Long newsId);

    /**
     * 累加政策新闻阅读量
     *
     * @param newsId
     * @return
     */
    PolicyNews summPolicyNewsReadCount(Long newsId);

    /**
     * 查询政策新闻列表
     *
     * @param policyNewsVo 政策新闻
     * @return 政策新闻集合
     */
    public List<PolicyNews> selectPolicyNewsListByPolicyNewsVo(PolicyNewsVo policyNewsVo);


    /**
     * 新增政策新闻点赞
     * @param newsId
     * @param memberId
     * @return
     */
    public int addPolicyNewsLike(Long newsId, Long memberId);

    /**
     * 新增政策新闻评论
     * @param policynewsComment
     * @return
     */
    public int addPolicynewsComment(PolicynewsComment policynewsComment);
}
