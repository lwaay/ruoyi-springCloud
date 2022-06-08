package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.OdSpecsSpecsvalue;
import com.sinonc.orders.mapper.OdSpecsSpecsvalueMapper;
import com.sinonc.orders.service.OdSpecsSpecsvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格_规格值关联 服务层实现
 *
 * @author sinonc
 * @date 2019-08-07
 */
@Service
public class OdSpecsSpecsvalueServiceImpl implements OdSpecsSpecsvalueService {
	@Autowired
	private OdSpecsSpecsvalueMapper odSpecsSpecsvalueMapper;

	/**
     * 查询规格_规格值关联信息
     *
     * @param specsValueId 规格_规格值关联ID
     * @return 规格_规格值关联信息
     */
    @Override
	public OdSpecsSpecsvalue getOdSpecsSpecsvalueById(Long specsValueId) {
	    return odSpecsSpecsvalueMapper.selectOdSpecsSpecsvalueById(specsValueId);
	}

	/**
     * 查询规格_规格值关联列表
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 规格_规格值关联集合
     */
	@Override
	public List<OdSpecsSpecsvalue> listOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue) {
	    return odSpecsSpecsvalueMapper.selectOdSpecsSpecsvalueList(odSpecsSpecsvalue);
	}

    /**
     * 新增规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	@Override
	public int addOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue) {
	    return odSpecsSpecsvalueMapper.insertOdSpecsSpecsvalue(odSpecsSpecsvalue);
	}

	/**
     * 修改规格_规格值关联
     *
     * @param odSpecsSpecsvalue 规格_规格值关联信息
     * @return 结果
     */
	@Override
	public int updateOdSpecsSpecsvalue(OdSpecsSpecsvalue odSpecsSpecsvalue) {
	    return odSpecsSpecsvalueMapper.updateOdSpecsSpecsvalue(odSpecsSpecsvalue);
	}

	/**
     * 删除规格_规格值关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOdSpecsSpecsvalueByIds(Long[] specsvalueids) {
		return odSpecsSpecsvalueMapper.deleteOdSpecsSpecsvalueByIds(specsvalueids);
	}

	/**
	 * 根据规格id查询该规格下的所有规格值
	 * @param specsId
	 * @return
	 */
    @Override
    public List<OdSpecsSpecsvalue> selectSpecsSpecsValueBySpecsId(Long specsId) {
        return odSpecsSpecsvalueMapper.selectSpecsSpecsValueBySpecsId(specsId);
    }

}
