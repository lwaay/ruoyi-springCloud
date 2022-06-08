package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.CategoryItem;
import com.sinonc.orders.mapper.CategoryItemMapper;
import com.sinonc.orders.service.CategoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评级详细项目 服务层实现
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Service
public class CategoryItemServiceImpl implements CategoryItemService {
	@Autowired
	private CategoryItemMapper categoryItemMapper;

	/**
     * 查询评级详细项目信息
     *
     * @param itemId 评级详细项目ID
     * @return 评级详细项目信息
     */
    @Override
	public CategoryItem getCategoryItemById(Long itemId) {
	    return categoryItemMapper.selectCategoryItemById(itemId);
	}

	/**
     * 查询评级详细项目列表
     *
     * @param categoryItem 评级详细项目信息
     * @return 评级详细项目集合
     */
	@Override
	public List<CategoryItem> listCategoryItem(CategoryItem categoryItem) {
	    return categoryItemMapper.selectCategoryItemList(categoryItem);
	}

    /**
     * 新增评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	@Override
	public int addCategoryItem(CategoryItem categoryItem) {
	    return categoryItemMapper.insertCategoryItem(categoryItem);
	}

	/**
     * 修改评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	@Override
	public int updateCategoryItem(CategoryItem categoryItem) {
	    return categoryItemMapper.updateCategoryItem(categoryItem);
	}

	/**
     * 删除评级详细项目对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCategoryItemByIds(String ids) {
		return categoryItemMapper.deleteCategoryItemByIds(Convert.toStrArray(ids));
	}

}
