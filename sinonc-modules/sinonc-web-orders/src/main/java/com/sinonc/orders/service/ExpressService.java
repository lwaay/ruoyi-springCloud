package com.sinonc.orders.service;

import com.sinonc.orders.domain.Express;

import java.util.List;

/**
 * 物流 服务层
 * 
 * @author sinonc
 * @date 2019-07-25
 */
public interface ExpressService {

	/**
     * 查询物流信息
     * 
     * @param expressId 物流ID
     * @return 物流信息
     */
	public Express getExpressById(Long expressId);
	
	/**
     * 查询物流列表
     * 
     * @param express 物流信息
     * @return 物流集合
     */
	public List<Express> listExpress(Express express);
	
	/**
     * 新增物流
     * 
     * @param express 物流信息
     * @return 结果
     */
	public int addExpress(Express express);
	
	/**
     * 修改物流
     * 
     * @param express 物流信息
     * @return 结果
     */
	public int updateExpress(Express express);
		
	/**
     * 删除物流信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExpressByIds(String ids);

	/**
	 * 查询物流详情信息
	 * @param expressNo 物流单号
	 * @return 结果
	 */
	public Object getExpressDetail(String expressNo);

	/**
	 * 查询物流单号和名称
	 * @param orderId 订单ID
	 * @return 结果
	 */
	public Express getExpressByOrderId(Long orderId);
}
