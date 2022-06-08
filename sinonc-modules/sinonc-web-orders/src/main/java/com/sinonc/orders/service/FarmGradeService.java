package com.sinonc.orders.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinonc.orders.domain.FarmGrade;
import com.sinonc.orders.domain.GradeCategory;

import java.util.List;

/**
 * 基地评级 服务层
 *
 * @author sinonc
 * @date 2019-11-23
 */
public interface FarmGradeService {

	/**
     * 查询基地评级信息
     *
     * @param gradeId 基地评级ID
     * @return 基地评级信息
     */
	public FarmGrade getFarmGradeById(Long gradeId) throws JsonProcessingException;

	/**
     * 查询基地评级列表
     *
     * @param farmGrade 基地评级信息
     * @return 基地评级集合
     */
	public List<FarmGrade> listFarmGrade(FarmGrade farmGrade);

	/**
     * 新增基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	public int addFarmGrade(FarmGrade farmGrade) throws Exception;

	/**
     * 修改基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	public int updateFarmGrade(FarmGrade farmGrade) throws Exception;

	/**
     * 删除基地评级信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFarmGradeByIds(String ids);

	/**
	 * 根据基地ID查询评级类别
	 * @param farmId
	 * @return
	 */
    FarmGrade selectGradeCategoryByFarmId(String farmId);

	/**
	 *
	 * @param farmId
	 * @param
	 * @return
	 */
	GradeCategory selectCategoryItemByFarmId(String farmId,String categoryName);
}
