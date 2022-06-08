package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.mapper.SpecsMapper;
import com.sinonc.orders.service.SpecsService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 规格 服务层实现
 *
 * @author sinonc
 * @date 2019-07-26
 */
@Service("specs")
public class SpecsServiceImpl implements SpecsService {

	@Autowired
	private SpecsMapper specsMapper;

	/**
     * 查询规格信息
     *
     * @param specsId 规格ID
     * @return 规格信息
     */
    @Override
	public Specs getSpecsById(Long specsId) {
	    return specsMapper.selectSpecsById(specsId);
	}

	/**
	 * 根据规格ID查询规格信息
	 * @param specsIds 规格Ids
	 * @return
	 */
	@Override
	public List<Specs> getSpecsByIds(String specsIds) {
		return specsMapper.selectSpecsByIds(specsIds.split(","));
	}

	/**
     * 查询规格列表
     *
     * @param specs 规格信息
     * @return 规格集合
     */
	@Override
	public List<Specs> listSpecs(Specs specs) {
	    return specsMapper.selectSpecsList(specs);
	}

    /**
     * 新增规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	@Override
	public int addSpecs(Specs specs) {
		//获取用户姓名
		String userName = SecurityUtils.getUsername();
		specs.setCreateBy(userName);
		specs.setCreateTime(new Date());
	    return specsMapper.insertSpecs(specs);
	}

	/**
     * 修改规格
     *
     * @param specs 规格信息
     * @return 结果
     */
	@Override
	public int updateSpecs(Specs specs) {
		String userName = SecurityUtils.getUsername();
		specs.setUpdateBy(userName);
		specs.setUpdateTime(new Date());
	    return specsMapper.updateSpecs(specs);
	}

	/**
     * 删除规格对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSpecsByIds(String ids) {
		return specsMapper.deleteSpecsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询店铺商品规格下拉列表
	 * @return
	 */
	@Override
	public List<Specs> listSelectSpecs(Long shopId) {
		SpecsService specsService= (SpecsService) AopContext.currentProxy();
		Specs specs = new Specs();
		specs.setShopId(shopId);
		return specsService.listSpecs(specs);
	}

	@Override
	public List<Specs> getGoodsSpecsByIds(String[] ids) {
		return specsMapper.selectSpecsByIds(ids);
	}
}
