package com.sinonc.base.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.FruiterInfoMapper;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.base.service.IFruiterInfoService;

/**
 * 果树信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-21
 */
@Service
public class FruiterInfoServiceImpl implements IFruiterInfoService {
    @Autowired
    private FruiterInfoMapper fruiterInfoMapper;

    /**
     * 查询果树信息
     *
     * @param fruId 果树信息ID
     * @return 果树信息
     */
    @Override
    public FruiterInfo selectFruiterInfoById(Long fruId) {
        return fruiterInfoMapper.selectFruiterInfoById(fruId);
    }

    /**
     * 查询果树信息列表
     *
     * @param fruiterInfo 果树信息
     * @return 果树信息
     */
    @Override
    public List<FruiterInfo> selectFruiterInfoList(FruiterInfo fruiterInfo) {
        return fruiterInfoMapper.selectFruiterInfoList(fruiterInfo);
    }

    /**
     * 新增果树信息
     *
     * @param fruiterInfo 果树信息
     * @return 结果
     */
    @Override
    public int insertFruiterInfo(FruiterInfo fruiterInfo) {
        fruiterInfo.setCreateTime(DateUtils.getNowDate());
        return fruiterInfoMapper.insertFruiterInfo(fruiterInfo);
    }

    /**
     * 修改果树信息
     *
     * @param fruiterInfo 果树信息
     * @return 结果
     */
    @Override
    public int updateFruiterInfo(FruiterInfo fruiterInfo) {
        fruiterInfo.setUpdateTime(DateUtils.getNowDate());
        return fruiterInfoMapper.updateFruiterInfo(fruiterInfo);
    }

    /**
     * 批量删除果树信息
     *
     * @param fruIds 需要删除的果树信息ID
     * @return 结果
     */
    @Override
    public int deleteFruiterInfoByIds(Long[] fruIds) {
        return fruiterInfoMapper.deleteFruiterInfoByIds(fruIds);
    }

    /**
     * 删除果树信息信息
     *
     * @param fruId 果树信息ID
     * @return 结果
     */
    @Override
    public int deleteFruiterInfoById(Long fruId) {
        return fruiterInfoMapper.deleteFruiterInfoById(fruId);
    }
}
