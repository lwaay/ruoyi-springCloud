package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.OdBrand;

/**
 * 商品品牌Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-25
 */
public interface OdBrandMapper {
    /**
     * 查询商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌
     */
    public OdBrand selectOdBrandById(Long brandId);

    /**
     * 查询商品品牌列表
     *
     * @param odBrand 商品品牌
     * @return 商品品牌集合
     */
    public List<OdBrand> selectOdBrandList(OdBrand odBrand);

    /**
     * 新增商品品牌
     *
     * @param odBrand 商品品牌
     * @return 结果
     */
    public int insertOdBrand(OdBrand odBrand);

    /**
     * 修改商品品牌
     *
     * @param odBrand 商品品牌
     * @return 结果
     */
    public int updateOdBrand(OdBrand odBrand);

    /**
     * 删除商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 结果
     */
    public int deleteOdBrandById(Long brandId);

    /**
     * 批量删除商品品牌
     *
     * @param brandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdBrandByIds(Long[] brandIds);
}
