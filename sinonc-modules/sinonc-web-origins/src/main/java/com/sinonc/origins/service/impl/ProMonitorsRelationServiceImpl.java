package com.sinonc.origins.service.impl;

import com.sinonc.origins.domain.ProMonitorsRelation;
import com.sinonc.origins.mapper.ProMonitorsRelationMapper;
import com.sinonc.origins.service.IProMonitorsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品摄像头关联关系Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-22
 */
@Service
public class ProMonitorsRelationServiceImpl implements IProMonitorsRelationService {
    @Autowired
    private ProMonitorsRelationMapper proMonitorsRelationMapper;

    /**
     * 查询产品摄像头关联关系
     *
     * @param proMonitorsId 产品摄像头关联关系ID
     * @return 产品摄像头关联关系
     */
    @Override
    public ProMonitorsRelation selectProMonitorsRelationById(Long proMonitorsId) {
        return proMonitorsRelationMapper.selectProMonitorsRelationById(proMonitorsId);
    }

    /**
     * 查询产品摄像头关联关系列表
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 产品摄像头关联关系
     */
    @Override
    public List<ProMonitorsRelation> selectProMonitorsRelationList(ProMonitorsRelation proMonitorsRelation) {
        return proMonitorsRelationMapper.selectProMonitorsRelationList(proMonitorsRelation);
    }

    /**
     * 新增产品摄像头关联关系
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 结果
     */
    @Override
    public int insertProMonitorsRelation(ProMonitorsRelation proMonitorsRelation) {
        return proMonitorsRelationMapper.insertProMonitorsRelation(proMonitorsRelation);
    }

    /**
     * 修改产品摄像头关联关系
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 结果
     */
    @Override
    public int updateProMonitorsRelation(ProMonitorsRelation proMonitorsRelation) {
        return proMonitorsRelationMapper.updateProMonitorsRelation(proMonitorsRelation);
    }

    /**
     * 批量删除产品摄像头关联关系
     *
     * @param proMonitorsIds 需要删除的产品摄像头关联关系ID
     * @return 结果
     */
    @Override
    public int deleteProMonitorsRelationByIds(Long[] proMonitorsIds) {
        return proMonitorsRelationMapper.deleteProMonitorsRelationByIds(proMonitorsIds);
    }

    /**
     * 删除产品摄像头关联关系信息
     *
     * @param proMonitorsId 产品摄像头关联关系ID
     * @return 结果
     */
    @Override
    public int deleteProMonitorsRelationById(Long proMonitorsId) {
        return proMonitorsRelationMapper.deleteProMonitorsRelationById(proMonitorsId);
    }
}
