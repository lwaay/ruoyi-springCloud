package com.sinonc.base.service;

import java.util.List;
import java.util.Map;

/**
 * 施肥方案
 * 
 * @author sinonc
 * @date 2019-11-17
 */
public interface PlantAPIService {

	/**
	 * 根据基地编码查询果园施肥方案
	 * @param forchardId
	 * @return
	 */
	public Map<String,Object> findFertilizerSchema(Long forchardId);

	public Map<String,Object> selectCurrentInsectInfo();

	public Map<String,Object> selectInsectByCode(String insectCode);

	public Map<String,Object> selectInsectByName(String insectName);

	public Map<String,Object> selectInsectByMonth(Integer month);

	public List<Map<String,Object>> listFertilizerSkill();

	public Map<String,Object> selectFertilizerSkillById(Long skillId);

	public Map<String,Object> selectFertilizerSkillByEleName(String eleName);
}
