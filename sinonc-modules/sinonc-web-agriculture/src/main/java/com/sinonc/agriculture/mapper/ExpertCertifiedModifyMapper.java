package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertCertifiedModify;

import java.util.List;

/**
 * 专家资格证明暂存Mapper接口
 *
 * @author ruoyi
 * @date 2020-04-03
 */
public interface ExpertCertifiedModifyMapper {
    /**
     * 查询专家资格证明暂存
     *
     * @param certifiedmodId 专家资格证明暂存ID
     * @return 专家资格证明暂存
     */
    public ExpertCertifiedModify selectExpertCertifiedModifyById(Long certifiedmodId);

    /**
     * 查询专家资格证明暂存列表
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 专家资格证明暂存集合
     */
    public List<ExpertCertifiedModify> selectExpertCertifiedModifyList(ExpertCertifiedModify expertCertifiedModify);

    /**
     * 新增专家资格证明暂存
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 结果
     */
    public int insertExpertCertifiedModify(ExpertCertifiedModify expertCertifiedModify);

    /**
     * 修改专家资格证明暂存
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 结果
     */
    public int updateExpertCertifiedModify(ExpertCertifiedModify expertCertifiedModify);

    /**
     * 删除专家资格证明暂存
     *
     * @param certifiedmodId 专家资格证明暂存ID
     * @return 结果
     */
    public int deleteExpertCertifiedModifyById(Long certifiedmodId);

    /**
     * 批量删除专家资格证明暂存
     *
     * @param certifiedmodIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertCertifiedModifyByIds(String[] certifiedmodIds);

    public void deleteExpertCertifiedModifyByExpertCertifiedId(Long expertmodId);
}
