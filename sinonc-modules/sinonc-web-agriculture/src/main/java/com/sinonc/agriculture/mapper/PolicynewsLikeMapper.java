package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.PolicynewsLike;

/**
 * 政策信息点赞Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-18
 */
public interface PolicynewsLikeMapper 
{
    /**
     * 查询政策信息点赞
     * 
     * @param likeId 政策信息点赞ID
     * @return 政策信息点赞
     */
    public PolicynewsLike selectPolicynewsLikeById(Long likeId);

    /**
     * 查询政策信息点赞列表
     * 
     * @param policynewsLike 政策信息点赞
     * @return 政策信息点赞集合
     */
    public List<PolicynewsLike> selectPolicynewsLikeList(PolicynewsLike policynewsLike);

    /**
     * 新增政策信息点赞
     * 
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    public int insertPolicynewsLike(PolicynewsLike policynewsLike);

    /**
     * 修改政策信息点赞
     * 
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    public int updatePolicynewsLike(PolicynewsLike policynewsLike);

    /**
     * 删除政策信息点赞
     * 
     * @param likeId 政策信息点赞ID
     * @return 结果
     */
    public int deletePolicynewsLikeById(Long likeId);

    /**
     * 批量删除政策信息点赞
     * 
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicynewsLikeByIds(String[] likeIds);
}
