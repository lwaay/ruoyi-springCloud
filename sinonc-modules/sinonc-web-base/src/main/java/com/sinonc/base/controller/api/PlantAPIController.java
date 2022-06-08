package com.sinonc.base.controller.api;


import com.sinonc.base.service.PlantAPIService;
import com.sinonc.base.service.impl.BaseFarmServiceImpl;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 药剂 信息操作处理
 *
 * @author sinonc
 * @date 2019-11-17
 */
@RestController
@RequestMapping("/api/seller/plant")
public class PlantAPIController extends BaseController {

	@Autowired
	private PlantAPIService plantAPIService;
	@Autowired
	private BaseFarmServiceImpl baseFarmService;

	/**
	 * 查询施肥方案
	 * @param farmId
	 * @return
	 */
	@GetMapping(value = "selectfertilizerinfo")
	public AjaxResult selectFertilizerInfo(Long farmId){
		if(farmId == null || farmId == 0){
			return AjaxResult.error(406,"果园id不能为空!");
		}
		Map<String,Object> data = plantAPIService.findFertilizerSchema(farmId);
		return AjaxResult.success(data);
	}

	/**
	 * 查询当前时间段流行的病虫害方案
	 * @return
	 */
	@GetMapping(value = "selectcurrentinsect")
	public AjaxResult selectCurrentInsect(){
		Map<String,Object> data = plantAPIService.selectCurrentInsectInfo();
		return AjaxResult.success(data);
	}

	/**
	 * 根据病虫害编码查询病虫害防治方案
	 * @param insectCode
	 * @return
	 */
	@GetMapping(value = "selectinsectbycode")
	public AjaxResult selectInsectByCode(String insectCode){
		if(StringUtils.isEmpty(insectCode)){
			return AjaxResult.error(406,"病虫害编码不能为空!");
		}
		Map<String,Object> data = plantAPIService.selectInsectByCode(insectCode);
		return AjaxResult.success(data);
	}

	/**
	 * 根据病虫害名称查询病虫害防治方案
	 * @param insectName
	 * @return
	 */
	@GetMapping(value = "selectinsectbyname")
	public AjaxResult selectInsectByName(String insectName){
		if(StringUtils.isEmpty(insectName)){
			return AjaxResult.error(406,"病虫害名称不能为空!");
		}
		Map<String,Object> data = plantAPIService.selectInsectByName(insectName);
		return AjaxResult.success(data);
	}

	/**
	 * 根据月份查询流行病虫害信息
	 * @param month
	 * @return
	 */
	@GetMapping(value = "selectperiodbymonth")
	public AjaxResult selectPeriodByMonth(Integer month){
		if(month == null){
			return AjaxResult.error(406,"月份不能为空!");
		}
		Map<String,Object> data = plantAPIService.selectInsectByMonth(month);
		return AjaxResult.success(data);
	}

	/**
	 * 查询施肥技术列表
	 * @return
	 */
	@GetMapping(value = "listfertilizerskill")
	public AjaxResult listFertilizerSkill(){
		List<Map<String,Object>> data = plantAPIService.listFertilizerSkill();
		return AjaxResult.success(data);
	}

	/**
	 * 根据施肥ID查询施肥技术详情
	 * @return
	 */
	@GetMapping(value = "listfertilizerskillbyid")
	public AjaxResult listFertilizerSkillById(Long skillId){
		if(skillId == null){
			return AjaxResult.error("ID不能为空！");
		}
		Map<String,Object> data = plantAPIService.selectFertilizerSkillById(skillId);
		return AjaxResult.success(data);
	}

	/**
	 * 根据元素名称模糊查询施肥技术详情
	 * @param eleName
	 * @return
	 */
	@GetMapping(value = "listfertilizerskillbyelename")
	public AjaxResult listFertilizerSkillByEleName(String eleName){
		if(StringUtils.isEmpty(eleName)){
			return AjaxResult.error("元素名称不能为空！");
		}
		Map<String,Object> data = plantAPIService.selectFertilizerSkillByEleName(eleName);
		return AjaxResult.success(data);
	}
}
