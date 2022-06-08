package com.sinonc.origins.service;

import com.sinonc.origins.domain.ProductLike;

import java.util.List;

/**
 * 朔源商品点赞Service接口
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IProductLikeService {
    /**
     * 查询朔源商品点赞
     *
     * @param productIdP 朔源商品点赞ID
     * @return 朔源商品点赞
     */
    public ProductLike selectProductLikeById(Long productIdP);

    /**
     * 查询朔源商品点赞列表
     *
     * @param productLike 朔源商品点赞
     * @return 朔源商品点赞集合
     */
    public List<ProductLike> selectProductLikeList(ProductLike productLike);

    /**
     * 新增朔源商品点赞
     *
     * @param productLike 朔源商品点赞
     * @return 结果
     */
    public int insertProductLike(ProductLike productLike);

    /**
     * 修改朔源商品点赞
     *
     * @param productLike 朔源商品点赞
     * @return 结果
     */
    public int updateProductLike(ProductLike productLike);

    /**
     * 批量删除朔源商品点赞
     *
     * @param productIdPs 需要删除的朔源商品点赞ID
     * @return 结果
     */
    public int deleteProductLikeByIds(Long[] productIdPs);

    /**
     * 删除朔源商品点赞信息
     *
     * @param productIdP 朔源商品点赞ID
     * @return 结果
     */
    public int deleteProductLikeById(Long productIdP);
}
