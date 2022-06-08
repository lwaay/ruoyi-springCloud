package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.domain.ExpertCertified;
import com.sinonc.agriculture.mapper.ExpertCertifiedMapper;
import com.sinonc.agriculture.service.ExpertCertifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专家资格证明Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-03
 */
@Service
public class ExpertCertifiedServiceImpl implements ExpertCertifiedService {
    @Autowired
    private ExpertCertifiedMapper expertCertifiedMapper;

    /**
     * 查询专家资格证明
     *
     * @param certifiedId 专家资格证明ID
     * @return 专家资格证明
     */
    @Override
    public ExpertCertified getExpertCertifiedById(Long certifiedId) {
        return expertCertifiedMapper.selectExpertCertifiedById(certifiedId);
    }

    /**
     * 查询专家资格证明列表
     *
     * @param expertCertified 专家资格证明
     * @return 专家资格证明
     */
    @Override
    public List<ExpertCertified> getExpertCertifiedList(ExpertCertified expertCertified) {
        return expertCertifiedMapper.selectExpertCertifiedList(expertCertified);
    }

    /**
     * 新增专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    @Override
    public int addExpertCertified(ExpertCertified expertCertified) {
        expertCertified.setCreateTime(DateUtils.getNowDate());
        return expertCertifiedMapper.insertExpertCertified(expertCertified);
    }

    /**
     * 修改专家资格证明
     *
     * @param expertCertified 专家资格证明
     * @return 结果
     */
    @Override
    public int updateExpertCertified(ExpertCertified expertCertified) {
        expertCertified.setUpdateTime(DateUtils.getNowDate());
        return expertCertifiedMapper.updateExpertCertified(expertCertified);
    }

    /**
     * 删除专家资格证明对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpertCertifiedByIds(String ids) {
        return expertCertifiedMapper.deleteExpertCertifiedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家资格证明信息
     *
     * @param certifiedId 专家资格证明ID
     * @return 结果
     */
    @Override
    public int deleteExpertCertifiedById(Long certifiedId) {
        return expertCertifiedMapper.deleteExpertCertifiedById(certifiedId);
    }
}
