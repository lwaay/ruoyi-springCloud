package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.OdCategory;

/**
 * 商品分类Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-06
 */
public interface OdCategoryMapper {
    /**
     * 查询商品分类
     *
     * @param categoryId 商品分类ID
     * @return 商品分类
     */
    public OdCategory selectOdCategoryById(Long categoryId);

    /**
     * 查询商品分类列表
     *
     * @param odCategory 商品分类
     * @return 商品分类集合
     */
    public List<OdCategory> selectOdCategoryList(OdCategory odCategory);

    /**
     * 新增商品分类
     *
     * @param odCategory 商品分类
     * @return 结果
     */
    public int insertOdCategory(OdCategory odCategory);

    /**
     * 修改商品分类
     *
     * @param odCategory 商品分类
     * @return 结果
     */
    public int updateOdCategory(OdCategory odCategory);

    /**
     * 删除商品分类
     *
     * @param categoryId 商品分类ID
     * @return 结果
     */
    public int deleteOdCategoryById(Long categoryId);

    /**
     * 批量删除商品分类
     *
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdCategoryByIds(Long[] categoryIds);
}
