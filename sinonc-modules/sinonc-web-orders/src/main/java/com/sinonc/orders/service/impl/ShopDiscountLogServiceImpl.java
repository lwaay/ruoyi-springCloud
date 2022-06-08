package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.ShopDiscountLog;
import com.sinonc.orders.mapper.ShopDiscountLogMapper;
import com.sinonc.orders.service.ShopDiscountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家改价记录 服务层实现
 *
 * @author sinonc
 * @date 2019-11-20
 */
@Service
public class ShopDiscountLogServiceImpl implements ShopDiscountLogService {
	@Autowired
	private ShopDiscountLogMapper shopDiscountLogMapper;

	/**
     * 查询商家改价记录信息
     *
     * @param logId 商家改价记录ID
     * @return 商家改价记录信息
     */
    @Override
	public ShopDiscountLog getShopDiscountLogById(Long logId) {
	    return shopDiscountLogMapper.selectShopDiscountLogById(logId);
	}

	/**
     * 查询商家改价记录列表
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 商家改价记录集合
     */
	@Override
	public List<ShopDiscountLog> listShopDiscountLog(ShopDiscountLog shopDiscountLog) {
	    return shopDiscountLogMapper.selectShopDiscountLogList(shopDiscountLog);
	}

    /**
     * 新增商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	@Override
	public int addShopDiscountLog(ShopDiscountLog shopDiscountLog) {
	    return shopDiscountLogMapper.insertShopDiscountLog(shopDiscountLog);
	}

	/**
     * 修改商家改价记录
     *
     * @param shopDiscountLog 商家改价记录信息
     * @return 结果
     */
	@Override
	public int updateShopDiscountLog(ShopDiscountLog shopDiscountLog) {
	    return shopDiscountLogMapper.updateShopDiscountLog(shopDiscountLog);
	}

	/**
     * 删除商家改价记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShopDiscountLogByIds(String ids) {
		return shopDiscountLogMapper.deleteShopDiscountLogByIds(Convert.toStrArray(ids));
	}

}
