package com.sinonc.orders.service;

import com.sinonc.orders.domain.GradeCategory;

import java.util.List;

/**
 * 评级类别 服务层
 *
 * @author sinonc
 * @date 2019-11-23
 */
public interface GradeCategoryService {

	/**
     * 查询评级类别信息
     *
     * @param categoryId 评级类别ID
     * @return 评级类别信息
     */
	public GradeCategory getGradeCategoryById(Long categoryId);

	/**
     * 查询评级类别列表
     *
     * @param gradeCategory 评级类别信息
     * @return 评级类别集合
     */
	public List<GradeCategory> listGradeCategory(GradeCategory gradeCategory);

	/**
     * 新增评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	public int addGradeCategory(GradeCategory gradeCategory);

	/**
     * 修改评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	public int updateGradeCategory(GradeCategory gradeCategory);

	/**
     * 删除评级类别信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGradeCategoryByIds(String ids);

}
