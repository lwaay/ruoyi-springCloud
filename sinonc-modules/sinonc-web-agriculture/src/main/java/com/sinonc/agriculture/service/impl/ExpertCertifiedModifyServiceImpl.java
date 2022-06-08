package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.domain.ExpertCertifiedModify;
import com.sinonc.agriculture.mapper.ExpertCertifiedModifyMapper;
import com.sinonc.agriculture.service.ExpertCertifiedModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专家资格证明暂存Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-03
 */
@Service
public class ExpertCertifiedModifyServiceImpl implements ExpertCertifiedModifyService {
    @Autowired
    private ExpertCertifiedModifyMapper expertCertifiedModifyMapper;

    /**
     * 查询专家资格证明暂存
     *
     * @param certifiedmodId 专家资格证明暂存ID
     * @return 专家资格证明暂存
     */
    @Override
    public ExpertCertifiedModify getExpertCertifiedModifyById(Long certifiedmodId) {
        return expertCertifiedModifyMapper.selectExpertCertifiedModifyById(certifiedmodId);
    }

    /**
     * 查询专家资格证明暂存列表
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 专家资格证明暂存
     */
    @Override
    public List<ExpertCertifiedModify> getExpertCertifiedModifyList(ExpertCertifiedModify expertCertifiedModify) {
        return expertCertifiedModifyMapper.selectExpertCertifiedModifyList(expertCertifiedModify);
    }

    /**
     * 新增专家资格证明暂存
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 结果
     */
    @Override
    public int addExpertCertifiedModify(ExpertCertifiedModify expertCertifiedModify) {
        expertCertifiedModify.setCreateTime(DateUtils.getNowDate());
        return expertCertifiedModifyMapper.insertExpertCertifiedModify(expertCertifiedModify);
    }

    /**
     * 修改专家资格证明暂存
     *
     * @param expertCertifiedModify 专家资格证明暂存
     * @return 结果
     */
    @Override
    public int updateExpertCertifiedModify(ExpertCertifiedModify expertCertifiedModify) {
        expertCertifiedModify.setUpdateTime(DateUtils.getNowDate());
        return expertCertifiedModifyMapper.updateExpertCertifiedModify(expertCertifiedModify);
    }

    /**
     * 删除专家资格证明暂存对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpertCertifiedModifyByIds(String ids) {
        return expertCertifiedModifyMapper.deleteExpertCertifiedModifyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家资格证明暂存信息
     *
     * @param certifiedmodId 专家资格证明暂存ID
     * @return 结果
     */
    @Override
    public int deleteExpertCertifiedModifyById(Long certifiedmodId) {
        return expertCertifiedModifyMapper.deleteExpertCertifiedModifyById(certifiedmodId);
    }
}
