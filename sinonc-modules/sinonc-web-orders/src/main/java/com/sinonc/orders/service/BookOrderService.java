package com.sinonc.orders.service;

import com.sinonc.orders.domain.BookOrder;

import java.util.List;

/**
 * 预订单 服务层
 *
 * @author sinonc
 * @date 2019-11-01
 */
public interface BookOrderService {

	/**
     * 查询预订单信息
     *
     * @param boId 预订单ID
     * @return 预订单信息
     */
	public BookOrder getBookOrderById(Long boId);

	/**
     * 查询预订单列表
     *
     * @param bookOrder 预订单信息
     * @return 预订单集合
     */
	public List<BookOrder> listBookOrder(BookOrder bookOrder);

	/**
     * 新增预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	public int addBookOrder(BookOrder bookOrder);

	/**
     * 修改预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	public int updateBookOrder(BookOrder bookOrder);

	/**
     * 删除预订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBookOrderByIds(String ids);

	/**
	 * 根据订单ID获取预订单信息
	 * @param orderId 订单ID
	 * @return 结果
	 */
	public BookOrder getBookOrderByOrderId(Long orderId);
}
