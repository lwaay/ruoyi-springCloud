package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.Qnygnb;
import com.sinonc.orders.mapper.QnygnbMapper;
import com.sinonc.orders.service.QnygnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轻农业信丰脐橙果农版用户关联 服务层实现
 *
 * @author sinonc
 * @date 2019-12-11
 */
@Service
public class QnygnbServiceImpl implements QnygnbService {
	@Autowired
	private QnygnbMapper qnygnbMapper;

	/**
     * 查询轻农业信丰脐橙果农版用户关联信息
     *
     * @param qnyId 轻农业信丰脐橙果农版用户关联ID
     * @return 轻农业信丰脐橙果农版用户关联信息
     */
    @Override
	public Qnygnb getQnygnbById(Long qnyId) {
	    return qnygnbMapper.selectQnygnbById(qnyId);
	}

	/**
     * 查询轻农业信丰脐橙果农版用户关联列表
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 轻农业信丰脐橙果农版用户关联集合
     */
	@Override
	public List<Qnygnb> listQnygnb(Qnygnb qnygnb) {
	    return qnygnbMapper.selectQnygnbList(qnygnb);
	}

    /**
     * 新增轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	@Override
	public int addQnygnb(Qnygnb qnygnb) {
	    return qnygnbMapper.insertQnygnb(qnygnb);
	}

	/**
     * 修改轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	@Override
	public int updateQnygnb(Qnygnb qnygnb) {
	    return qnygnbMapper.updateQnygnb(qnygnb);
	}

	/**
     * 删除轻农业信丰脐橙果农版用户关联对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteQnygnbByIds(String ids) {
		return qnygnbMapper.deleteQnygnbByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据shopId查询轻农业本店铺的问题
	 * @param shopId
	 * @return
	 */
	@Override
	public List<Qnygnb> selectQnyGnbListByShopId(Long shopId) {
		return qnygnbMapper.selectQnyGnbListByShopId(shopId);
	}

	/**
	 * 根据shopId查询轻农业本店铺的问题根据年月查问题
	 * @param shopId
	 * @param year
	 * @param month
	 * @return
	 */
	@Override
	public List<Qnygnb> selectQnyGnbListByShopIdForYearAndMonth(Long shopId, String year, String month) {
		return qnygnbMapper.selectQnyGnbListByShopIdForYearAndMonth(shopId,year,month);
	}

}
