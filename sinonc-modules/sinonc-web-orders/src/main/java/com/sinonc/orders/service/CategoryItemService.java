package com.sinonc.orders.service;

import com.sinonc.orders.domain.CategoryItem;

import java.util.List;

/**
 * 评级详细项目 服务层
 *
 * @author sinonc
 * @date 2019-11-23
 */
public interface CategoryItemService {

	/**
     * 查询评级详细项目信息
     *
     * @param itemId 评级详细项目ID
     * @return 评级详细项目信息
     */
	public CategoryItem getCategoryItemById(Long itemId);

	/**
     * 查询评级详细项目列表
     *
     * @param categoryItem 评级详细项目信息
     * @return 评级详细项目集合
     */
	public List<CategoryItem> listCategoryItem(CategoryItem categoryItem);

	/**
     * 新增评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	public int addCategoryItem(CategoryItem categoryItem);

	/**
     * 修改评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	public int updateCategoryItem(CategoryItem categoryItem);

	/**
     * 删除评级详细项目信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCategoryItemByIds(String ids);

}
