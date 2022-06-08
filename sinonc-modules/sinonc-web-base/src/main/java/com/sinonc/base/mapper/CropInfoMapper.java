package com.sinonc.base.mapper;

import com.sinonc.base.domain.CropInfo;

import java.util.List;

/**
 * 脐橙信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface CropInfoMapper {
    /**
     * 查询脐橙信息
     *
     * @param id 脐橙信息ID
     * @return 脐橙信息
     */
    public CropInfo selectCropInfoById(Long id);

    /**
     * 查询脐橙信息列表
     *
     * @param cropInfo 脐橙信息
     * @return 脐橙信息集合
     */
    public List<CropInfo> selectCropInfoList(CropInfo cropInfo);

    /**
     * 新增脐橙信息
     *
     * @param cropInfo 脐橙信息
     * @return 结果
     */
    public int insertCropInfo(CropInfo cropInfo);

    /**
     * 修改脐橙信息
     *
     * @param cropInfo 脐橙信息
     * @return 结果
     */
    public int updateCropInfo(CropInfo cropInfo);

    /**
     * 删除脐橙信息
     *
     * @param id 脐橙信息ID
     * @return 结果
     */
    public int deleteCropInfoById(Long id);

    /**
     * 批量删除脐橙信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCropInfoByIds(Long[] ids);
}
