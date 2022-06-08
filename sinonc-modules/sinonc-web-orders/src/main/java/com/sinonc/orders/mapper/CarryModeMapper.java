package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.CarryMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运送方式 数据层
 *
 * @author sinonc
 * @date 2019-11-27
 */
@Mapper
public interface CarryModeMapper {
	/**
     * 查询运送方式信息
     *
     * @param carrymodelId 运送方式ID
     * @return 运送方式信息
     */
	public CarryMode selectCarryModeById(Long carrymodelId);

	/**
     * 查询运送方式列表
     *
     * @param carryMode 运送方式信息
     * @return 运送方式集合
     */
	public List<CarryMode> selectCarryModeList(CarryMode carryMode);

	/**
     * 新增运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	public int insertCarryMode(CarryMode carryMode);

	/**
     * 修改运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	public int updateCarryMode(CarryMode carryMode);

	/**
     * 删除运送方式
     *
     * @param carrymodelId 运送方式ID
     * @return 结果
     */
	public int deleteCarryModeById(Long carrymodelId);

	/**
     * 批量删除运送方式
     *
     * @param carrymodelIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarryModeByIds(String[] carrymodelIds);

	/**
	 * 根据类型查询运费模默认运费或自定义运费
	 * @param isDefault
	 * @return
	 */
    List<CarryMode> selectCarryModelForIsDefault(@Param("fareId") Long fareId,@Param("isDefault") int isDefault);


	/**
	 * 删除该模板下的运费方式
	 * @param fareId
	 */
	void deleteCarryModeByFareId(@Param("fareId") Long fareId);

}
