package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.OwnDynamic;
import com.sinonc.agriculture.dto.OwnDynamicDto;

import java.util.List;

/**
 * 我的动态Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-24
 */
public interface OwnDynamicMapper {
    /**
     * 查询我的动态
     *
     * @param owndynaId 我的动态ID
     * @return 我的动态
     */
    public OwnDynamic selectOwnDynamicById(Long owndynaId);

    /**
     * 查询我的动态列表
     *
     * @param ownDynamic 我的动态
     * @return 我的动态集合
     */
    public List<OwnDynamic> selectOwnDynamicList(OwnDynamic ownDynamic);

    /**
     * 新增我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    public int insertOwnDynamic(OwnDynamic ownDynamic);

    /**
     * 修改我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    public int updateOwnDynamic(OwnDynamic ownDynamic);

    /**
     * 删除我的动态
     *
     * @param owndynaId 我的动态ID
     * @return 结果
     */
    public int deleteOwnDynamicById(Long owndynaId);

    /**
     * 批量删除我的动态
     *
     * @param owndynaIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOwnDynamicByIds(String[] owndynaIds);

    /**
     * 查询我的动态列表
     *
     * @param ownDynamic 我的动态
     * @return 我的动态集合
     */
    public List<OwnDynamicDto> selectOwnDynamicListForDto(OwnDynamic ownDynamic);
}
