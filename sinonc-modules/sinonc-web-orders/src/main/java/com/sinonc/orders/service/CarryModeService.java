package com.sinonc.orders.service;

import com.sinonc.orders.domain.CarryMode;

import java.util.List;

/**
 * 运送方式 服务层
 *
 * @author sinonc
 * @date 2019-11-27
 */
public interface CarryModeService {

	/**
     * 查询运送方式信息
     *
     * @param carrymodelId 运送方式ID
     * @return 运送方式信息
     */
	public CarryMode getCarryModeById(Long carrymodelId);

	/**
     * 查询运送方式列表
     *
     * @param carryMode 运送方式信息
     * @return 运送方式集合
     */
	public List<CarryMode> listCarryMode(CarryMode carryMode);

	/**
     * 新增运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	public int addCarryMode(CarryMode carryMode);

	/**
     * 修改运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	public int updateCarryMode(CarryMode carryMode);

	/**
     * 删除运送方式信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarryModeByIds(String ids);

	/**
	 *根据类型查询运费模默认运费或自定义运费
	 * @param isDefault
	 * @return
	 */
    List<CarryMode> selectCarryModelForIsDefault(Long fareId,int isDefault);
}
