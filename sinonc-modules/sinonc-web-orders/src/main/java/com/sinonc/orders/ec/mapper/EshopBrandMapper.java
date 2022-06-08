package com.sinonc.orders.ec.mapper;

import com.sinonc.orders.ec.domain.EshopBrand;

import java.util.List;

/**
 * 大屏自定义品牌Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-31
 */
public interface EshopBrandMapper {
    /**
     * 查询大屏自定义品牌
     *
     * @param brandId 大屏自定义品牌ID
     * @return 大屏自定义品牌
     */
    public EshopBrand selectEshopBrandById(Long brandId);

    /**
     * 查询大屏自定义品牌列表
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 大屏自定义品牌集合
     */
    public List<EshopBrand> selectEshopBrandList(EshopBrand eshopBrand);

    /**
     * 新增大屏自定义品牌
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 结果
     */
    public int insertEshopBrand(EshopBrand eshopBrand);

    /**
     * 修改大屏自定义品牌
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 结果
     */
    public int updateEshopBrand(EshopBrand eshopBrand);

    /**
     * 删除大屏自定义品牌
     *
     * @param brandId 大屏自定义品牌ID
     * @return 结果
     */
    public int deleteEshopBrandById(Long brandId);

    /**
     * 批量删除大屏自定义品牌
     *
     * @param brandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopBrandByIds(Long[] brandIds);

    /**
     * 根据品牌id集查询品牌
     * @param brandIds
     * @return
     */
    public List<String> selectEshopBrandByIds(Long[] brandIds);

    /**
     * 查询默认品牌
     * @return
     */
    List<EshopBrand> selectListDefault();
}
