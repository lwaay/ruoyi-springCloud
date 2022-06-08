package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.GradeCategory;
import com.sinonc.orders.mapper.GradeCategoryMapper;
import com.sinonc.orders.service.GradeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评级类别 服务层实现
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Service
public class GradeCategoryServiceImpl implements GradeCategoryService {
	@Autowired
	private GradeCategoryMapper gradeCategoryMapper;

	/**
     * 查询评级类别信息
     *
     * @param categoryId 评级类别ID
     * @return 评级类别信息
     */
    @Override
	public GradeCategory getGradeCategoryById(Long categoryId) {
	    return gradeCategoryMapper.selectGradeCategoryById(categoryId);
	}

	/**
     * 查询评级类别列表
     *
     * @param gradeCategory 评级类别信息
     * @return 评级类别集合
     */
	@Override
	public List<GradeCategory> listGradeCategory(GradeCategory gradeCategory) {
	    return gradeCategoryMapper.selectGradeCategoryList(gradeCategory);
	}

    /**
     * 新增评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	@Override
	public int addGradeCategory(GradeCategory gradeCategory) {
	    return gradeCategoryMapper.insertGradeCategory(gradeCategory);
	}

	/**
     * 修改评级类别
     *
     * @param gradeCategory 评级类别信息
     * @return 结果
     */
	@Override
	public int updateGradeCategory(GradeCategory gradeCategory) {
	    return gradeCategoryMapper.updateGradeCategory(gradeCategory);
	}

	/**
     * 删除评级类别对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGradeCategoryByIds(String ids) {
		return gradeCategoryMapper.deleteGradeCategoryByIds(Convert.toStrArray(ids));
	}

}
