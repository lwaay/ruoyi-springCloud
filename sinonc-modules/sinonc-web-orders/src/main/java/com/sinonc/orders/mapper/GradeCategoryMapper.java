package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.GradeCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评级类别 数据层
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Mapper
public interface GradeCategoryMapper {
	/**
     * 查询评级类别信息
     *
     * @param categoryId 评级类别ID
     * @return 评级类别信息
     */
	public GradeCategory selectGradeCategoryById(Long categoryId);

	/**
     * 查询评级类别列表
     *
     * @param gradeCategory 评级类别信息
     * @return 评级类别集合
     */
	public List<GradeCategory> selectGradeCategoryList(GradeCategory gradeCategory);

	/**
     * 新增评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	public int insertGradeCategory(GradeCategory gradeCategory);

	/**
     * 修改评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	public int updateGradeCategory(GradeCategory gradeCategory);

	/**
     * 删除评级类别
     *
     * @param categoryId 评级类别ID
     * @return 结果
     */
	public int deleteGradeCategoryById(Long categoryId);

	/**
     * 批量删除评级类别
     *
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteGradeCategoryByIds(String[] categoryIds);

}
