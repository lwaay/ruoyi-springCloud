package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.FarmGrade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 基地评级 数据层
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Mapper
public interface FarmGradeMapper {
	/**
     * 查询基地评级信息
     *
     * @param gradeId 基地评级ID
     * @return 基地评级信息
     */
	public FarmGrade selectFarmGradeById(Long gradeId);

	/**
     * 查询基地评级列表
     *
     * @param farmGrade 基地评级信息
     * @return 基地评级集合
     */
	public List<FarmGrade> selectFarmGradeList(FarmGrade farmGrade);

	/**
     * 新增基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	public int insertFarmGrade(FarmGrade farmGrade);

	/**
     * 修改基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	public int updateFarmGrade(FarmGrade farmGrade);

	/**
     * 删除基地评级
     *
     * @param gradeId 基地评级ID
     * @return 结果
     */
	public int deleteFarmGradeById(Long gradeId);

	/**
     * 批量删除基地评级
     *
     * @param gradeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteFarmGradeByIds(String[] gradeIds);

}
