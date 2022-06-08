package com.sinonc.orders.service;

import com.sinonc.orders.domain.City;

import java.util.List;

/**
 * 省市县数据 服务层
 * 
 * @author sinonc
 * @date 2019-12-04
 */
public interface CityService {

	/**
	 * 获取一级城市
	 * @return
	 */
	public List<City> getProvice();

	/**
	 * 获取二级三级城市
	 * @return
	 */
	public List<City> getCity(int pid);
	
}
