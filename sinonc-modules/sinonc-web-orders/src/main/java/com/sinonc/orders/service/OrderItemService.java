package com.sinonc.orders.service;

import com.sinonc.orders.domain.OrderItem;

import java.util.List;

/**
 * 订单项目 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface OrderItemService {

	/**
     * 查询订单项目信息
     *
     * @param orderItemId 订单项目ID
     * @return 订单项目信息
     */
	public OrderItem getOrderItemById(Long orderItemId);

	/**
     * 查询订单项目列表
     *
     * @param orderItem 订单项目信息
     * @return 订单项目集合
     */
	public List<OrderItem> listOrderItem(OrderItem orderItem);

	/**
     * 新增订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	public int addOrderItem(OrderItem orderItem);

	/**
     * 修改订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	public int updateOrderItem(OrderItem orderItem);

	/**
     * 删除订单项目信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderItemByIds(String ids);

	/**
	 * 根据商品id查询订单商品关联表
	 * @param goodsId
	 * @return
	 */
    OrderItem selectOrderItemByGoodsId(Long goodsId);

	/**
	 * 根据订单id查询订单商品关联表
	 * @param orderId
	 * @return
	 */
	OrderItem selectOrderItemByOrderId(Long orderId);
}
