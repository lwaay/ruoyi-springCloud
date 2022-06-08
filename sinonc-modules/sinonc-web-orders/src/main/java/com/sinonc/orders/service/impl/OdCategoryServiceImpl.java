package com.sinonc.orders.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdCategoryMapper;
import com.sinonc.orders.domain.OdCategory;
import com.sinonc.orders.service.OdCategoryService;

/**
 * 商品分类Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-06
 */
@Service
public class OdCategoryServiceImpl implements OdCategoryService {
    @Autowired
    private OdCategoryMapper odCategoryMapper;

    /**
     * 查询商品分类
     *
     * @param categoryId 商品分类ID
     * @return 商品分类
     */
    @Override
    public OdCategory selectOdCategoryById(Long categoryId) {
        return odCategoryMapper.selectOdCategoryById(categoryId);
    }

    /**
     * 查询商品分类列表
     *
     * @param odCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<OdCategory> selectOdCategoryList(OdCategory odCategory) {
        return odCategoryMapper.selectOdCategoryList(odCategory);
    }

    /**
     * 新增商品分类
     *
     * @param odCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertOdCategory(OdCategory odCategory) {
        odCategory.setCreateTime(DateUtils.getNowDate());
        return odCategoryMapper.insertOdCategory(odCategory);
    }

    /**
     * 修改商品分类
     *
     * @param odCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateOdCategory(OdCategory odCategory) {
        odCategory.setUpdateTime(DateUtils.getNowDate());
        return odCategoryMapper.updateOdCategory(odCategory);
    }

    /**
     * 批量删除商品分类
     *
     * @param categoryIds 需要删除的商品分类ID
     * @return 结果
     */
    @Override
    public int deleteOdCategoryByIds(Long[] categoryIds) {
        return odCategoryMapper.deleteOdCategoryByIds(categoryIds);
    }

    /**
     * 删除商品分类信息
     *
     * @param categoryId 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteOdCategoryById(Long categoryId) {
        return odCategoryMapper.deleteOdCategoryById(categoryId);
    }
}
