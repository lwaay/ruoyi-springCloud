package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.FareTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 运费模板 数据层
 *
 * @author sinonc
 * @date 2019-11-27
 */
@Mapper
public interface FareTemplateMapper {
	/**
     * 查询运费模板信息
     *
     * @param fareId 运费模板ID
     * @return 运费模板信息
     */
	public FareTemplate selectFareTemplateById(Long fareId);
	/**
	 * 查询运费模板信息
	 *
	 * @param shopId 运费模板ID
	 * @return 运费模板信息
	 */
	public FareTemplate selectFareTemplateByShopId(Long shopId);

	/**
     * 查询运费模板列表
     *
     * @param fareTemplate 运费模板信息
     * @return 运费模板集合
     */
	public List<FareTemplate> selectFareTemplateList(FareTemplate fareTemplate);

	/**
     * 新增运费模板
     *
     * @param fareTemplate 运费模板信息
     * @return 结果
     */
	public int insertFareTemplate(FareTemplate fareTemplate);

	/**
     * 修改运费模板
     *
     * @param fareTemplate 运费模板信息
     * @return 结果
     */
	public int updateFareTemplate(FareTemplate fareTemplate);

	/**
     * 删除运费模板
     *
     * @param fareId 运费模板ID
     * @return 结果
     */
	public int deleteFareTemplateById(Long fareId);

	/**
     * 批量删除运费模板
     *
     * @param fareIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteFareTemplateByIds(String[] fareIds);

}
