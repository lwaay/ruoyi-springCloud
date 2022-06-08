package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.CarryMode;
import com.sinonc.orders.mapper.CarryModeMapper;
import com.sinonc.orders.service.CarryModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运送方式 服务层实现
 *
 * @author sinonc
 * @date 2019-11-27
 */
@Service
public class CarryModeServiceImpl implements CarryModeService {
	@Autowired
	private CarryModeMapper carryModeMapper;

	/**
     * 查询运送方式信息
     *
     * @param carrymodelId 运送方式ID
     * @return 运送方式信息
     */
    @Override
	public CarryMode getCarryModeById(Long carrymodelId) {
	    return carryModeMapper.selectCarryModeById(carrymodelId);
	}

	/**
     * 查询运送方式列表
     *
     * @param carryMode 运送方式信息
     * @return 运送方式集合
     */
	@Override
	public List<CarryMode> listCarryMode(CarryMode carryMode) {
	    return carryModeMapper.selectCarryModeList(carryMode);
	}

    /**
     * 新增运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	@Override
	public int addCarryMode(CarryMode carryMode) {
	    return carryModeMapper.insertCarryMode(carryMode);
	}

	/**
     * 修改运送方式
     *
     * @param carryMode 运送方式信息
     * @return 结果
     */
	@Override
	public int updateCarryMode(CarryMode carryMode) {
	    return carryModeMapper.updateCarryMode(carryMode);
	}

	/**
     * 删除运送方式对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarryModeByIds(String ids) {
		return carryModeMapper.deleteCarryModeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据类型查询运费模默认运费或自定义运费
	 * @param isDefault
	 * @return
	 */
	@Override
	public List<CarryMode> selectCarryModelForIsDefault(Long fareId,int isDefault) {
		return carryModeMapper.selectCarryModelForIsDefault(fareId,isDefault);
	}

}
