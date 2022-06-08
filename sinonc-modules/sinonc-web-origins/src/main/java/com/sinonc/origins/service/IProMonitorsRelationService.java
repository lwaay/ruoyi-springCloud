package com.sinonc.origins.service;

import com.sinonc.origins.domain.ProMonitorsRelation;

import java.util.List;

/**
 * 产品摄像头关联关系Service接口
 *
 * @author ruoyi
 * @date 2020-10-22
 */
public interface IProMonitorsRelationService {
    /**
     * 查询产品摄像头关联关系
     *
     * @param proMonitorsId 产品摄像头关联关系ID
     * @return 产品摄像头关联关系
     */
    public ProMonitorsRelation selectProMonitorsRelationById(Long proMonitorsId);

    /**
     * 查询产品摄像头关联关系列表
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 产品摄像头关联关系集合
     */
    public List<ProMonitorsRelation> selectProMonitorsRelationList(ProMonitorsRelation proMonitorsRelation);

    /**
     * 新增产品摄像头关联关系
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 结果
     */
    public int insertProMonitorsRelation(ProMonitorsRelation proMonitorsRelation);

    /**
     * 修改产品摄像头关联关系
     *
     * @param proMonitorsRelation 产品摄像头关联关系
     * @return 结果
     */
    public int updateProMonitorsRelation(ProMonitorsRelation proMonitorsRelation);

    /**
     * 批量删除产品摄像头关联关系
     *
     * @param proMonitorsIds 需要删除的产品摄像头关联关系ID
     * @return 结果
     */
    public int deleteProMonitorsRelationByIds(Long[] proMonitorsIds);

    /**
     * 删除产品摄像头关联关系信息
     *
     * @param proMonitorsId 产品摄像头关联关系ID
     * @return 结果
     */
    public int deleteProMonitorsRelationById(Long proMonitorsId);
}
