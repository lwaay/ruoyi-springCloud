package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.api.domain.FruiterInfo;

/**
 * 果树信息Service接口
 *
 * @author ruoyi
 * @date 2022-02-21
 */
public interface IFruiterInfoService {
    /**
     * 查询果树信息
     *
     * @param fruId 果树信息ID
     * @return 果树信息
     */
    public FruiterInfo selectFruiterInfoById(Long fruId);

    /**
     * 查询果树信息列表
     *
     * @param fruiterInfo 果树信息
     * @return 果树信息集合
     */
    public List<FruiterInfo> selectFruiterInfoList(FruiterInfo fruiterInfo);

    /**
     * 新增果树信息
     *
     * @param fruiterInfo 果树信息
     * @return 结果
     */
    public int insertFruiterInfo(FruiterInfo fruiterInfo);

    /**
     * 修改果树信息
     *
     * @param fruiterInfo 果树信息
     * @return 结果
     */
    public int updateFruiterInfo(FruiterInfo fruiterInfo);

    /**
     * 批量删除果树信息
     *
     * @param fruIds 需要删除的果树信息ID
     * @return 结果
     */
    public int deleteFruiterInfoByIds(Long[] fruIds);

    /**
     * 删除果树信息信息
     *
     * @param fruId 果树信息ID
     * @return 结果
     */
    public int deleteFruiterInfoById(Long fruId);
}
