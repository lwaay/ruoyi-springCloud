package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.YxShippingTemplatesRegion;

/**
 * 模板区域表Service接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface IYxShippingTemplatesRegionService {
    /**
     * 查询模板区域表
     *
     * @param id 模板区域表ID
     * @return 模板区域表
     */
    public YxShippingTemplatesRegion selectYxShippingTemplatesRegionById(Long id);

    /**
     * 查询模板区域表列表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 模板区域表集合
     */
    public List<YxShippingTemplatesRegion> selectYxShippingTemplatesRegionList(YxShippingTemplatesRegion yxShippingTemplatesRegion);

    /**
     * 新增模板区域表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 结果
     */
    public int insertYxShippingTemplatesRegion(YxShippingTemplatesRegion yxShippingTemplatesRegion);

    /**
     * 修改模板区域表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 结果
     */
    public int updateYxShippingTemplatesRegion(YxShippingTemplatesRegion yxShippingTemplatesRegion);

    /**
     * 批量删除模板区域表
     *
     * @param ids 需要删除的模板区域表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesRegionByIds(Long[] ids);

    /**
     * 删除模板区域表信息
     *
     * @param id 模板区域表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesRegionById(Long id);
}
