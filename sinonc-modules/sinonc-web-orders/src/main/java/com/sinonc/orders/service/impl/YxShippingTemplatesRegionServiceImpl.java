package com.sinonc.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.YxShippingTemplatesRegionMapper;
import com.sinonc.orders.domain.YxShippingTemplatesRegion;
import com.sinonc.orders.service.IYxShippingTemplatesRegionService;

/**
 * 模板区域表Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class YxShippingTemplatesRegionServiceImpl implements IYxShippingTemplatesRegionService {
    @Autowired
    private YxShippingTemplatesRegionMapper yxShippingTemplatesRegionMapper;

    /**
     * 查询模板区域表
     *
     * @param id 模板区域表ID
     * @return 模板区域表
     */
    @Override
    public YxShippingTemplatesRegion selectYxShippingTemplatesRegionById(Long id) {
        return yxShippingTemplatesRegionMapper.selectYxShippingTemplatesRegionById(id);
    }

    /**
     * 查询模板区域表列表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 模板区域表
     */
    @Override
    public List<YxShippingTemplatesRegion> selectYxShippingTemplatesRegionList(YxShippingTemplatesRegion yxShippingTemplatesRegion) {
        return yxShippingTemplatesRegionMapper.selectYxShippingTemplatesRegionList(yxShippingTemplatesRegion);
    }

    /**
     * 新增模板区域表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 结果
     */
    @Override
    public int insertYxShippingTemplatesRegion(YxShippingTemplatesRegion yxShippingTemplatesRegion) {
        return yxShippingTemplatesRegionMapper.insertYxShippingTemplatesRegion(yxShippingTemplatesRegion);
    }

    /**
     * 修改模板区域表
     *
     * @param yxShippingTemplatesRegion 模板区域表
     * @return 结果
     */
    @Override
    public int updateYxShippingTemplatesRegion(YxShippingTemplatesRegion yxShippingTemplatesRegion) {
        return yxShippingTemplatesRegionMapper.updateYxShippingTemplatesRegion(yxShippingTemplatesRegion);
    }

    /**
     * 批量删除模板区域表
     *
     * @param ids 需要删除的模板区域表ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesRegionByIds(Long[] ids) {
        return yxShippingTemplatesRegionMapper.deleteYxShippingTemplatesRegionByIds(ids);
    }

    /**
     * 删除模板区域表信息
     *
     * @param id 模板区域表ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesRegionById(Long id) {
        return yxShippingTemplatesRegionMapper.deleteYxShippingTemplatesRegionById(id);
    }
}
