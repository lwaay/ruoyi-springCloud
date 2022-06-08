package com.sinonc.origins.mapper;

import com.sinonc.origins.domain.ProductLike;

import java.util.List;

/**
 * 朔源商品点赞Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public interface ProductLikeMapper {
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
     * 删除朔源商品点赞
     *
     * @param productIdP 朔源商品点赞ID
     * @return 结果
     */
    public int deleteProductLikeById(Long productIdP);

    /**
     * 批量删除朔源商品点赞
     *
     * @param productIdPs 需要删除的数据ID
     * @return 结果
     */
    public int deleteProductLikeByIds(Long[] productIdPs);
}
