package com.sinonc.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 药剂 数据层
 *
 * @author sinonc
 * @date 2019-11-17
 */
@Mapper
public interface PlantAPIMapper {

	/**
	 * 根据基地编码统计基地果树
	 * @param forchardId
	 * @return
	 */
	public Map<String,Object> listFarmGrowInfoBy(@Param("forchardId")Long forchardId);

	/**
	 * 根据基地编码查询基地果树果龄种植情况
	 * @param forchardId
	 * @return
	 */
	public List<Map<String,String>> listFarmGrowInfo(@Param("forchardId")Long forchardId);

	/**
	 * 根据果树成长阶段查询施肥方案
	 * @param ageCode
	 * @return
	 */
	public Map<String,String> selectFertilizerByageCode(@Param("ageCode")String ageCode);

	/**
	 * 查询当前月份流行的病虫害信息
	 * @param month
	 * @return
	 */
	public Map<String,String> selectCurrentInsectInfo(@Param("month")int month);

	/**
	 * 根据病虫害编码查询病虫害信息
	 * @param insectCode
	 * @return
	 */
	public Map<String,Object> selectInsectByCode(@Param("insectCode")String insectCode);

	/**
	 * 根据病虫害名称模糊查询病虫害信息
	 * @param insectName
	 * @return
	 */
	public Map<String,Object> selectInsectByName(@Param("insectName")String insectName);

	/**
	 * 根据病虫害编码查询病虫害常用药剂
	 * @param insectCode
	 * @return
	 */
	public List<Map<String,String>> listInsectPesticide(@Param("insectCode")String insectCode);

	/**
	 * 查询施肥技术列表
	 * @return
	 */
	public List<Map<String,Object>> listFertilizerSkill();

	/**
	 * 根据施肥技术ID查询施肥详情
	 * @param skillId
	 * @return
	 */
	public Map<String,Object> selectFertilizerSkillById(@Param("skillId")Long skillId);

	/**
	 * 根据元素名称模糊查询施肥技术详情
	 * @param eleName
	 * @return
	 */
	public Map<String,Object> selectFertilizerSkillByEleName(@Param("eleName")String eleName);
}
