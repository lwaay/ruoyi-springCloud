package com.sinonc.base.service.impl;

import com.sinonc.base.domain.CropInfo;
import com.sinonc.base.mapper.CropInfoMapper;
import com.sinonc.base.service.ICropInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 脐橙信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class CropInfoServiceImpl implements ICropInfoService {
    @Autowired
    private CropInfoMapper cropInfoMapper;

    /**
     * 查询脐橙信息
     *
     * @param id 脐橙信息ID
     * @return 脐橙信息
     */
    @Override
    public CropInfo selectCropInfoById(Long id) {
        return cropInfoMapper.selectCropInfoById(id);
    }

    /**
     * 查询脐橙信息列表
     *
     * @param cropInfo 脐橙信息
     * @return 脐橙信息
     */
    @Override
    public List<CropInfo> selectCropInfoList(CropInfo cropInfo) {
        return cropInfoMapper.selectCropInfoList(cropInfo);
    }

    /**
     * 新增脐橙信息
     *
     * @param cropInfo 脐橙信息
     * @return 结果
     */
    @Override
    public int insertCropInfo(CropInfo cropInfo) {
        return cropInfoMapper.insertCropInfo(cropInfo);
    }

    /**
     * 修改脐橙信息
     *
     * @param cropInfo 脐橙信息
     * @return 结果
     */
    @Override
    public int updateCropInfo(CropInfo cropInfo) {
        return cropInfoMapper.updateCropInfo(cropInfo);
    }

    /**
     * 批量删除脐橙信息
     *
     * @param ids 需要删除的脐橙信息ID
     * @return 结果
     */
    @Override
    public int deleteCropInfoByIds(Long[] ids) {
        return cropInfoMapper.deleteCropInfoByIds(ids);
    }

    /**
     * 删除脐橙信息信息
     *
     * @param id 脐橙信息ID
     * @return 结果
     */
    @Override
    public int deleteCropInfoById(Long id) {
        return cropInfoMapper.deleteCropInfoById(id);
    }
}
