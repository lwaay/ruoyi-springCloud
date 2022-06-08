package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertInfoModify;

import java.util.List;

/**
 * 专家信息暂存Mapper接口
 *
 * @author ruoyi
 * @date 2020-04-07
 */
public interface ExpertInfoModifyMapper {
    /**
     * 查询专家信息暂存
     *
     * @param expertmodId 专家信息暂存ID
     * @return 专家信息暂存
     */
    public ExpertInfoModify selectExpertInfoModifyById(Long expertmodId);

    /**
     * 查询专家信息暂存列表
     *
     * @param expertInfoModify 专家信息暂存
     * @return 专家信息暂存集合
     */
    public List<ExpertInfoModify> selectExpertInfoModifyList(ExpertInfoModify expertInfoModify);

    /**
     * 新增专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    public int insertExpertInfoModify(ExpertInfoModify expertInfoModify);

    /**
     * 修改专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    public int updateExpertInfoModify(ExpertInfoModify expertInfoModify);

    /**
     * 删除专家信息暂存
     *
     * @param expertmodId 专家信息暂存ID
     * @return 结果
     */
    public int deleteExpertInfoModifyById(Long expertmodId);

    /**
     * 批量删除专家信息暂存
     *
     * @param expertmodIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertInfoModifyByIds(String[] expertmodIds);
}
