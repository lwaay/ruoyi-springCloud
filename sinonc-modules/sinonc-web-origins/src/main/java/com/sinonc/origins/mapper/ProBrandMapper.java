package com.sinonc.origins.mapper;

import com.sinonc.origins.domain.ProBrand;

import java.util.List;

/**
 * 商品品牌Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-23
 */
public interface ProBrandMapper {
    /**
     * 查询商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌
     */
    public ProBrand selectProBrandById(Long brandId);

    /**
     * 查询商品品牌列表
     *
     * @param proBrand 商品品牌
     * @return 商品品牌集合
     */
    public List<ProBrand> selectProBrandList(ProBrand proBrand);

    /**
     * 新增商品品牌
     *
     * @param proBrand 商品品牌
     * @return 结果
     */
    public int insertProBrand(ProBrand proBrand);

    /**
     * 修改商品品牌
     *
     * @param proBrand 商品品牌
     * @return 结果
     */
    public int updateProBrand(ProBrand proBrand);

    /**
     * 删除商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 结果
     */
    public int deleteProBrandById(Long brandId);

    /**
     * 批量删除商品品牌
     *
     * @param brandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProBrandByIds(Long[] brandIds);
}
