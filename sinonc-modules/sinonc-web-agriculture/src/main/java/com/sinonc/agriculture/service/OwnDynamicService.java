package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.OwnDynamic;
import com.sinonc.agriculture.dto.OwnDynamicDto;

import java.util.List;

/**
 * 我的动态Service接口
 *
 * @author ruoyi
 * @date 2020-03-24
 */
public interface OwnDynamicService {
    /**
     * 查询我的动态
     *
     * @param owndynaId 我的动态ID
     * @return 我的动态
     */
    public OwnDynamic getOwnDynamicById(Long owndynaId);

    /**
     * 查询我的动态列表
     *
     * @param ownDynamic 我的动态
     * @return 我的动态集合
     */
    public List<OwnDynamic> getOwnDynamicList(OwnDynamic ownDynamic);

    /**
     * 新增我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    public int addOwnDynamic(OwnDynamic ownDynamic);

    /**
     * 修改我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    public int updateOwnDynamic(OwnDynamic ownDynamic);

    /**
     * 批量删除我的动态
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOwnDynamicByIds(String ids);

    /**
     * 删除我的动态信息
     *
     * @param owndynaId 我的动态ID
     * @return 结果
     */
    public int deleteOwnDynamicById(Long owndynaId);

    /**
     * 新增问题动态
     *
     * @param agriQuestion
     * @return
     */
    public int addOwnQuestionDynamic(AgriQuestion agriQuestion, Long targetId, Integer type, Long memberId);

    /**
     * @param ownDynamic
     * @return
     */
    public List<OwnDynamicDto> getOwnDynamicListForDto(OwnDynamic ownDynamic);
}
