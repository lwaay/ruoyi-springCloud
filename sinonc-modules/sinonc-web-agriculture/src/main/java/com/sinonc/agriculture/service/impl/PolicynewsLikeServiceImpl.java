package com.sinonc.agriculture.service.impl;

import java.util.List;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.PolicynewsLikeMapper;
import com.sinonc.agriculture.domain.PolicynewsLike;
import com.sinonc.agriculture.service.PolicynewsLikeService;
import com.sinonc.common.core.text.Convert;

/**
 * 政策信息点赞Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
public class PolicynewsLikeServiceImpl implements PolicynewsLikeService
{
    @Autowired
    private PolicynewsLikeMapper policynewsLikeMapper;

    /**
     * 查询政策信息点赞
     *
     * @param likeId 政策信息点赞ID
     * @return 政策信息点赞
     */
    @Override
    public PolicynewsLike getPolicynewsLikeById(Long likeId)
    {
        return policynewsLikeMapper.selectPolicynewsLikeById(likeId);
    }

    /**
     * 查询政策信息点赞列表
     *
     * @param policynewsLike 政策信息点赞
     * @return 政策信息点赞
     */
    @Override
    public List<PolicynewsLike> getPolicynewsLikeList(PolicynewsLike policynewsLike)
    {
        return policynewsLikeMapper.selectPolicynewsLikeList(policynewsLike);
    }

    /**
     * 新增政策信息点赞
     *
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    @Override
    public int addPolicynewsLike(PolicynewsLike policynewsLike)
    {
        policynewsLike.setCreateTime(DateUtils.getNowDate());
        return policynewsLikeMapper.insertPolicynewsLike(policynewsLike);
    }

    /**
     * 修改政策信息点赞
     *
     * @param policynewsLike 政策信息点赞
     * @return 结果
     */
    @Override
    public int updatePolicynewsLike(PolicynewsLike policynewsLike)
    {
        policynewsLike.setUpdateTime(DateUtils.getNowDate());
        return policynewsLikeMapper.updatePolicynewsLike(policynewsLike);
    }

    /**
     * 删除政策信息点赞对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePolicynewsLikeByIds(String ids)
    {
        return policynewsLikeMapper.deletePolicynewsLikeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除政策信息点赞信息
     *
     * @param likeId 政策信息点赞ID
     * @return 结果
     */
    @Override
    public int deletePolicynewsLikeById(Long likeId)
    {
        return policynewsLikeMapper.deletePolicynewsLikeById(likeId);
    }
}
