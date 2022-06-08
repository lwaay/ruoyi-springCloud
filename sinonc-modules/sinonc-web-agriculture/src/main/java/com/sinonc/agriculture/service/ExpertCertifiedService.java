package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ExpertCertified;

import java.util.List;

/**
 * 专家资格证明Service接口
 *
 * @author ruoyi
 * @date 2020-04-03
 */
public interface ExpertCertifiedService {
    /**
     * 查询专家资格证明
     *
     * @param certifiedId 专家资格证明ID
     * @return 专家资格证明
     */
    public ExpertCertified getExpertCertifiedById(Long certifiedId);

    /**
     * 查询专家资格证明列表
     *
     * @param expertCertified 专家资格证明
     * @return 专家资格证明集合
     */
    public List<ExpertCertified> getExpertCertifiedList(ExpertCertified expertCertified);

    /**
     * 新增专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    public int addExpertCertified(ExpertCertified expertCertified);

    /**
     * 修改专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    public int updateExpertCertified(ExpertCertified expertCertified);

    /**
     * 批量删除专家资格证明
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertCertifiedByIds(String ids);

    /**
     * 删除专家资格证明信息
     *
     * @param certifiedId 专家资格证明ID
     * @return 结果
     */
    public int deleteExpertCertifiedById(Long certifiedId);
}
