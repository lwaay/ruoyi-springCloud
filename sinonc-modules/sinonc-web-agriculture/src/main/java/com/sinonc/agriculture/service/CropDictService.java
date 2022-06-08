package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.vo.CropChildVo;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.web.domain.Ztree;

/**
 * 作物字典Service接口
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public interface CropDictService
{
    /**
     * 查询作物字典
     *
     * @param cropId 作物字典ID
     * @return 作物字典
     */
    public CropDict selectCropDictById(Long cropId);

    /**
     * 查询作物字典树列表
     *
     * @return 所有作物字典信息
     */
    public List<Ztree> selectCropDictTree();

    /**
     * 根据作物ID查询该作物信息和其子类信息
     * @param cropDictId
     * @return
     */
    List<CropChildVo> getCropChildList(Long cropDictId);

    /**
     * 查询作物列表
     * @return
     */
    List<CropDict> selectAllCropDictList(Long memberId);
}
