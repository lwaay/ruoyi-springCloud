package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertCertified;

import java.util.List;

/**
 * 专家资格证明Mapper接口
 *
 * @author ruoyi
 * @date 2020-04-03
 */
public interface ExpertCertifiedMapper {
    /**
     * 查询专家资格证明
     *
     * @param certifiedId 专家资格证明ID
     * @return 专家资格证明
     */
    public ExpertCertified selectExpertCertifiedById(Long certifiedId);

    /**
     * 查询专家资格证明列表
     *
     * @param expertCertified 专家资格证明
     * @return 专家资格证明集合
     */
    public List<ExpertCertified> selectExpertCertifiedList(ExpertCertified expertCertified);

    /**
     * 新增专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    public int insertExpertCertified(ExpertCertified expertCertified);

    /**
     * 修改专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    public int updateExpertCertified(ExpertCertified expertCertified);

    /**
     * 删除专家资格证明
     *
     * @param certifiedId 专家资格证明ID
     * @return 结果
     */
    public int deleteExpertCertifiedById(Long certifiedId);

    /**
     * 批量删除专家资格证明
     *
     * @param certifiedIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertCertifiedByIds(String[] certifiedIds);

    /**
     * 根据专家ID删除资格证明
     *
     * @param expertId
     */
    public void deleteExpertCertifiedByExpertInfoId(Long expertId);
}
