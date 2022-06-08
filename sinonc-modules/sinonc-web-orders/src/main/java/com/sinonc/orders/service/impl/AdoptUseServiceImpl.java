package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.AdoptUse;
import com.sinonc.orders.mapper.AdoptUseMapper;
import com.sinonc.orders.service.AdoptUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务使用记录 服务层实现
 *
 * @author sinonc
 * @date 2019-08-14
 */
@Service
public class AdoptUseServiceImpl implements AdoptUseService {
	@Autowired
	private AdoptUseMapper adoptUseMapper;

	/**
     * 查询服务使用记录信息
     *
     * @param useId 服务使用记录ID
     * @return 服务使用记录信息
     */
    @Override
	public AdoptUse getAdoptUseById(Integer useId) {
	    return adoptUseMapper.selectAdoptUseById(useId);
	}

	/**
     * 查询服务使用记录列表
     *
     * @param adoptUse 服务使用记录信息
     * @return 服务使用记录集合
     */
	@Override
	public List<AdoptUse> listAdoptUse(AdoptUse adoptUse) {
	    return adoptUseMapper.selectAdoptUseList(adoptUse);
	}

    /**
     * 新增服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	@Override
	public int addAdoptUse(AdoptUse adoptUse) {
	    return adoptUseMapper.insertAdoptUse(adoptUse);
	}

	/**
     * 修改服务使用记录
     *
     * @param adoptUse 服务使用记录信息
     * @return 结果
     */
	@Override
	public int updateAdoptUse(AdoptUse adoptUse) {
	    return adoptUseMapper.updateAdoptUse(adoptUse);
	}

	/**
     * 删除服务使用记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdoptUseByIds(String ids) {
		return adoptUseMapper.deleteAdoptUseByIds(Convert.toStrArray(ids));
	}

}
