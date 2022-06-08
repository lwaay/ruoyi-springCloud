package com.sinonc.orders.service;

import com.sinonc.orders.domain.ExpPrice;

import java.util.List;

/**
 * 物流价格模版 服务层
 *
 * @author sinonc
 * @date 2019-11-26
 */
public interface ExpPriceService {

	/**
     * 查询物流价格模版信息
     *
     * @param expPriceId 物流价格模版ID
     * @return 物流价格模版信息
     */
	public ExpPrice getExpPriceById(Long expPriceId);

	/**
     * 查询物流价格模版列表
     *
     * @param expPrice 物流价格模版信息
     * @return 物流价格模版集合
     */
	public List<ExpPrice> listExpPrice(ExpPrice expPrice);

	/**
     * 新增物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	public int addExpPrice(ExpPrice expPrice);

	/**
     * 修改物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	public int updateExpPrice(ExpPrice expPrice);

	/**
     * 删除物流价格模版信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExpPriceByIds(String ids);

	/**
	 * 根据店铺id获取可发货省份
	 * @param shopId
	 * @return
	 */
	public String getprovincesByShopId(Long shopId);

}
