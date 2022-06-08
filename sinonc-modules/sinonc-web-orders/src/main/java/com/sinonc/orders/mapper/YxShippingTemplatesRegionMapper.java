package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.YxShippingTemplatesRegion;
import org.apache.ibatis.annotations.Param;

/**
 * 模板区域表Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface YxShippingTemplatesRegionMapper {
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
     * 删除模板区域表
     *
     * @param id 模板区域表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesRegionById(Long id);

    /**
     * 批量删除模板区域表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesRegionByIds(Long[] ids);

    /**
     * 根据模板id删除区域
     */
    public int deleteRegionByTempId(Integer tempId);

    /**
     * 根据模板Id，城市Id获取模板区域列表
     */
    public List<YxShippingTemplatesRegion> listRegionByTempAndCity(@Param("temps") List<Integer> temps,@Param("citys") List<String> citys);
}
