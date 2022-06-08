package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.PolicynewsLike;

/**
 * 政策信息点赞Service接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface PolicynewsLikeService
{
    /**
     * 查询政策信息点赞
     * 
     * @param likeId 政策信息点赞ID
     * @return 政策信息点赞
     */
    public PolicynewsLike getPolicynewsLikeById(Long likeId);

    /**
     * 查询政策信息点赞列表
     * 
     * @param policynewsLike 政策信息点赞
     * @return 政策信息点赞集合
     */
    public List<PolicynewsLike> getPolicynewsLikeList(PolicynewsLike policynewsLike);

    /**
     * 新增政策信息点赞
     * 
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    public int addPolicynewsLike(PolicynewsLike policynewsLike);

    /**
     * 修改政策信息点赞
     * 
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    public int updatePolicynewsLike(PolicynewsLike policynewsLike);

    /**
     * 批量删除政策信息点赞
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicynewsLikeByIds(String ids);

    /**
     * 删除政策信息点赞信息
     * 
     * @param likeId 政策信息点赞ID
     * @return 结果
     */
    public int deletePolicynewsLikeById(Long likeId);
}
