package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 省市县数据 数据层
 *
 * @author sinonc
 * @date 2019-12-04
 */
@Mapper
public interface CityMapper {
	/**
     *
     * 查询一级地区
     * @return 省市县数据信息
     */
	public List<City> selectProvice();

	/**
	 *
	 * 查询二级地区
	 * @param pid
	 * @return 省市县数据信息
	 */
	public  List<City> selectCity(int pid);


}
