package com.sinonc.orders.service.impl;

import com.sinonc.orders.domain.City;
import com.sinonc.orders.mapper.CityMapper;
import com.sinonc.orders.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省市县数据 服务层实现
 *
 * @author sinonc
 * @date 2019-12-04
 */
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;


	@Override
	public List<City> getProvice() {
		return cityMapper.selectProvice();
	}

	@Override
	public List<City> getCity(int pid) {
		return cityMapper.selectCity(pid);
	}
}
