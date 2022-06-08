package com.sinonc.origins.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.origins.domain.ProductLike;
import com.sinonc.origins.mapper.ProductLikeMapper;
import com.sinonc.origins.service.IProductLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 朔源商品点赞Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ProductLikeServiceImpl implements IProductLikeService {
    @Autowired
    private ProductLikeMapper productLikeMapper;

    /**
     * 查询朔源商品点赞
     *
     * @param productIdP 朔源商品点赞ID
     * @return 朔源商品点赞
     */
    @Override
    public ProductLike selectProductLikeById(Long productIdP) {
        return productLikeMapper.selectProductLikeById(productIdP);
    }

    /**
     * 查询朔源商品点赞列表
     *
     * @param productLike 朔源商品点赞
     * @return 朔源商品点赞
     */
    @Override
    public List<ProductLike> selectProductLikeList(ProductLike productLike) {
        return productLikeMapper.selectProductLikeList(productLike);
    }

    /**
     * 新增朔源商品点赞
     *
     * @param productLike 朔源商品点赞
     * @return 结果
     */
    @Override
    public int insertProductLike(ProductLike productLike) {
                                                                                                productLike.setCreateTime(DateUtils.getNowDate());
                            return productLikeMapper.insertProductLike(productLike);
    }

    /**
     * 修改朔源商品点赞
     *
     * @param productLike 朔源商品点赞
     * @return 结果
     */
    @Override
    public int updateProductLike(ProductLike productLike) {
                                                                                                return productLikeMapper.updateProductLike(productLike);
    }

    /**
     * 批量删除朔源商品点赞
     *
     * @param productIdPs 需要删除的朔源商品点赞ID
     * @return 结果
     */
    @Override
    public int deleteProductLikeByIds(Long[] productIdPs) {
        return productLikeMapper.deleteProductLikeByIds(productIdPs);
    }

    /**
     * 删除朔源商品点赞信息
     *
     * @param productIdP 朔源商品点赞ID
     * @return 结果
     */
    @Override
    public int deleteProductLikeById(Long productIdP) {
        return productLikeMapper.deleteProductLikeById(productIdP);
    }
}
