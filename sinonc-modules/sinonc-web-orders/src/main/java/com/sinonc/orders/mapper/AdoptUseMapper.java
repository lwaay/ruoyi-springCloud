package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AdoptUse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 服务使用记录 数据层
 *
 * @author sinonc
 * @date 2019-08-14
 */
@Mapper
public interface AdoptUseMapper {
	/**
     * 查询服务使用记录信息
     *
     * @param useId 服务使用记录ID
     * @return 服务使用记录信息
     */
	public AdoptUse selectAdoptUseById(Integer useId);

	/**
     * 查询服务使用记录列表
     *
     * @param adoptUse 服务使用记录信息
     * @return 服务使用记录集合
     */
	public List<AdoptUse> selectAdoptUseList(AdoptUse adoptUse);

	/**
     * 新增服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	public int insertAdoptUse(AdoptUse adoptUse);

	/**
     * 修改服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	public int updateAdoptUse(AdoptUse adoptUse);

	/**
     * 删除服务使用记录
     *
     * @param useId 服务使用记录ID
     * @return 结果
     */
	public int deleteAdoptUseById(Integer useId);

	/**
     * 批量删除服务使用记录
     *
     * @param useIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptUseByIds(String[] useIds);

}
