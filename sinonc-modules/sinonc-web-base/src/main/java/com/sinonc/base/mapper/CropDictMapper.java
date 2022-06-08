package com.sinonc.base.mapper;

import com.sinonc.base.api.domain.CropDict;

import java.util.List;

/**
 * 作物字典Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface CropDictMapper {
    /**
     * 查询作物字典
     *
     * @param cropId 作物字典ID
     * @return 作物字典
     */
    public CropDict selectCropDictById(Long cropId);

    /**
     * 查询作物字典列表
     *
     * @param cropDict 作物字典
     * @return 作物字典集合
     */
    public List<CropDict> selectCropDictList(CropDict cropDict);

    /**
     * 新增作物字典
     *
     * @param cropDict 作物字典
     * @return 结果
     */
    public int insertCropDict(CropDict cropDict);

    /**
     * 修改作物字典
     *
     * @param cropDict 作物字典
     * @return 结果
     */
    public int updateCropDict(CropDict cropDict);

    /**
     * 删除作物字典
     *
     * @param cropId 作物字典ID
     * @return 结果
     */
    public int deleteCropDictById(Long cropId);

    /**
     * 批量删除作物字典
     *
     * @param cropIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCropDictByIds(Long[] cropIds);
}
