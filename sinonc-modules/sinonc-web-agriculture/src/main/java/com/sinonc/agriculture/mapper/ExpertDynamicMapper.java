package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertDynamic;

import java.util.List;

/**
 * 专家动态Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-31
 */
public interface ExpertDynamicMapper {
    /**
     * 查询专家动态
     *
     * @param dynamicId 专家动态ID
     * @return 专家动态
     */
    public ExpertDynamic selectExpertDynamicById(Long dynamicId);

    /**
     * 查询专家动态列表
     *
     * @param expertDynamic 专家动态
     * @return 专家动态集合
     */
    public List<ExpertDynamic> selectExpertDynamicList(ExpertDynamic expertDynamic);

    /**
     * 新增专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    public int insertExpertDynamic(ExpertDynamic expertDynamic);

    /**
     * 修改专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    public int updateExpertDynamic(ExpertDynamic expertDynamic);

    /**
     * 删除专家动态
     *
     * @param dynamicId 专家动态ID
     * @return 结果
     */
    public int deleteExpertDynamicById(Long dynamicId);

    /**
     * 批量删除专家动态
     *
     * @param dynamicIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertDynamicByIds(String[] dynamicIds);

    /**
     * 增加点赞总数
     *
     * @return
     */
    int addCount(String columnName, Long dynamicId);

}
