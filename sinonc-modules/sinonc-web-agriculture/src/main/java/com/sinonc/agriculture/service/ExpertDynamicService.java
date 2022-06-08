package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.domain.ExpertDynamicComment;

import java.util.List;

/**
 * 专家动态Service接口
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ExpertDynamicService {
    /**
     * 查询专家动态
     *
     * @param dynamicId 专家动态ID
     * @return 专家动态
     */
    public ExpertDynamic getExpertDynamicById(Long dynamicId);

    /**
     * 查询专家动态列表
     *
     * @param expertDynamic 专家动态
     * @return 专家动态集合
     */
    public List<ExpertDynamic> getExpertDynamicList(ExpertDynamic expertDynamic);

    /**
     * 新增专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    public int addExpertDynamic(ExpertDynamic expertDynamic);

    /**
     * 添加点赞
     *
     * @param memberId  会员ID
     * @param dynamicId 动态ID
     * @return
     */
    int addLike(Long memberId, Long dynamicId);

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    int addComment(ExpertDynamicComment comment);

    /**
     * 修改专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    public int updateExpertDynamic(ExpertDynamic expertDynamic);

    /**
     * 批量删除专家动态
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertDynamicByIds(String ids);

    /**
     * 删除专家动态信息
     *
     * @param dynamicId 专家动态ID
     * @return 结果
     */
    public int deleteExpertDynamicById(Long dynamicId);
}
