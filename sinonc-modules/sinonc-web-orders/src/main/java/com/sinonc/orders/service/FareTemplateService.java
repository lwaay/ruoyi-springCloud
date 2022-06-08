package com.sinonc.orders.service;

import com.sinonc.orders.domain.FareTemplate;
import com.sinonc.orders.dto.FareDto;

import java.util.List;

/**
 * 运费模板 服务层
 * 
 * @author sinonc
 * @date 2019-11-27
 */
public interface FareTemplateService {

	/**
     * 查询运费模板信息
     * 
     * @param fareId 运费模板ID
     * @return 运费模板信息
     */
	public FareTemplate getFareTemplateById(Long fareId);

	/**
	 * 查询运费模板信息
	 *
	 * @param shopId 运费模板ID
	 * @return 运费模板信息
	 */
	public FareTemplate getFareTemplateByShopId(Long shopId);
	
	/**
     * 查询运费模板列表
     * 
     * @param fareTemplate 运费模板信息
     * @return 运费模板集合
     */
	public List<FareTemplate> listFareTemplate(FareTemplate fareTemplate);
	
	/**
     * 新增运费模板
     * 
     * @param fareDto 运费模板信息
     * @return 结果
     */
	public int addFareTemplate(FareDto fareDto);
	
	/**
     * 修改运费模板
     * 
     * @param fareDto 运费模板信息
     * @return 结果
     */
	public int updateFareTemplate(FareDto fareDto);
		
	/**
     * 删除运费模板信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFareTemplateByIds(String ids);
	
}
