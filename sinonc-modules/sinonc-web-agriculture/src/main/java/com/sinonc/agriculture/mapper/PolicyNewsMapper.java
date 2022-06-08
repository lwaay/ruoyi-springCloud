package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.dto.PolicyNewsDto;
import com.sinonc.exchange.vo.PolicyNewsVo;

import java.util.List;

/**
 * 政策新闻Mapper接口
 *
 * @author zhang.xl
 * @date 2020-03-05
 */
public interface PolicyNewsMapper
{
    /**
     * 查询政策新闻
     *
     * @param newsId 政策新闻ID
     * @return 政策新闻
     */
    public PolicyNews selectPolicyNewsById(Long newsId);

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
     * 删除政策新闻
     *
     * @param newsId 政策新闻ID
     * @return 结果
     */
    public int deletePolicyNewsById(Long newsId);

    /**
     * 批量删除政策新闻
     *
     * @param newsIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyNewsByIds(Long[] newsIds);


    public PolicyNews selectPolicyNewsByIdForUpdate(Long newsId);

    public List<PolicyNews> selectPolicyNewsListByPolicyNewsVo(PolicyNewsVo policyNewsVo);

    /**
     * 根据ID查询政策新闻
     * @param newsId
     * @return
     */
    public PolicyNewsDto selectPolicyNewsDtoById(Long newsId);
}
