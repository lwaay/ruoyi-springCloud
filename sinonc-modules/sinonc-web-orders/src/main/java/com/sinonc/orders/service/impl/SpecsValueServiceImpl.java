package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.SpecsValue;
import com.sinonc.orders.mapper.SpecsValueMapper;
import com.sinonc.orders.service.SpecsValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 规格属性 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class SpecsValueServiceImpl implements SpecsValueService {
	@Autowired
	private SpecsValueMapper specsValueMapper;

	/**
     * 查询规格属性信息
     *
     * @param specsValueId 规格属性ID
     * @return 规格属性信息
     */
    @Override
	public SpecsValue getSpecsValueById(Long specsValueId) {
	    return specsValueMapper.selectSpecsValueById(specsValueId);
	}

	/**
     * 查询规格属性列表
     *
     * @param specsValue 规格属性信息
     * @return 规格属性集合
     */
	@Override
	public List<SpecsValue> listSpecsValue(SpecsValue specsValue) {
	    return specsValueMapper.selectSpecsValueList(specsValue);
	}

    /**
     * 新增规格属性
     *
     * @param specsValue 规格属性信息
     * @return 结果
     */
	@Override
	public int addSpecsValue(SpecsValue specsValue) {
		String userName = SecurityUtils.getUsername();
		specsValue.setCreateBy(userName);
		specsValue.setCreateTime(new Date());
	    return specsValueMapper.insertSpecsValue(specsValue);
	}

	/**
     * 修改规格属性
     *
     * @param specsValue 规格属性信息
     * @return 结果
     */
	@Override
	public int updateSpecsValue(SpecsValue specsValue) {
		specsValue.setUpdateTime(new Date());
	    return specsValueMapper.updateSpecsValue(specsValue);
	}

	/**
     * 删除规格属性对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSpecsValueByIds(String ids) {
		return specsValueMapper.deleteSpecsValueByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询规格值列表
	 * @return
	 */
    @Override
    public List<SpecsValue> findSpecsValueList(SpecsValue specsValue) {
        return specsValueMapper.selectSpecsValueList(specsValue);
    }

}
