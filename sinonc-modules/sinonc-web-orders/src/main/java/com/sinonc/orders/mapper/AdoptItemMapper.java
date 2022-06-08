package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AdoptItem;
import com.sinonc.orders.vo.AdoptItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 认养服务明细 数据层
 *
 * @author sinonc
 * @date 2019-08-09
 */
@Mapper
public interface AdoptItemMapper {
	/**
     * 查询认养服务明细信息
     *
     * @param adoptItemId 认养服务明细ID
     * @return 认养服务明细信息
     */
	public AdoptItem selectAdoptItemById(Long adoptItemId);

	/**
     * 查询认养服务明细列表
     *
     * @param adoptItem 认养服务明细信息
     * @return 认养服务明细集合
     */
	public List<AdoptItem> selectAdoptItemList(AdoptItem adoptItem);

	/**
     * 新增认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
	public int insertAdoptItem(AdoptItem adoptItem);

	/**
     * 修改认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
	public int updateAdoptItem(AdoptItem adoptItem);

	/**
     * 删除认养服务明细
     *
     * @param adoptItemId 认养服务明细ID
     * @return 结果
     */
	public int deleteAdoptItemById(Integer adoptItemId);

	/**
     * 批量删除认养服务明细
     *
     * @param adoptItemIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptItemByIds(String[] adoptItemIds);

    /**
     * 查询认养服务视图明细列表
     * @param adoptItemVo 认养服务视图对象
     * @return 结果
     */
    List<AdoptItemVo> selectAdoptItemVoList(AdoptItemVo adoptItemVo);
}
