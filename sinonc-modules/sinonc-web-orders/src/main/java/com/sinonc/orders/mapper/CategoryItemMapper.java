package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.CategoryItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评级详细项目 数据层
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Mapper
public interface CategoryItemMapper {
	/**
     * 查询评级详细项目信息
     *
     * @param itemId 评级详细项目ID
     * @return 评级详细项目信息
     */
	public CategoryItem selectCategoryItemById(Long itemId);

	/**
     * 查询评级详细项目列表
     *
     * @param categoryItem 评级详细项目信息
     * @return 评级详细项目集合
     */
	public List<CategoryItem> selectCategoryItemList(CategoryItem categoryItem);

	/**
     * 新增评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	public int insertCategoryItem(CategoryItem categoryItem);

	/**
     * 修改评级详细项目
     *
     * @param categoryItem 评级详细项目信息
     * @return 结果
     */
	public int updateCategoryItem(CategoryItem categoryItem);

	/**
     * 删除评级详细项目
     *
     * @param itemId 评级详细项目ID
     * @return 结果
     */
	public int deleteCategoryItemById(Long itemId);

	/**
     * 批量删除评级详细项目
     *
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCategoryItemByIds(String[] itemIds);

}
