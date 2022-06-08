package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.BookOrder;
import com.sinonc.orders.mapper.BookOrderMapper;
import com.sinonc.orders.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预订单 服务层实现
 *
 * @author sinonc
 * @date 2019-11-01
 */
@Service
public class BookOrderServiceImpl implements BookOrderService {
	@Autowired
	private BookOrderMapper bookOrderMapper;

	/**
     * 查询预订单信息
     *
     * @param boId 预订单ID
     * @return 预订单信息
     */
    @Override
	public BookOrder getBookOrderById(Long boId) {
	    return bookOrderMapper.selectBookOrderById(boId);
	}

	/**
     * 查询预订单列表
     *
     * @param bookOrder 预订单信息
     * @return 预订单集合
     */
	@Override
	public List<BookOrder> listBookOrder(BookOrder bookOrder) {
	    return bookOrderMapper.selectBookOrderList(bookOrder);
	}

    /**
     * 新增预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	@Override
	public int addBookOrder(BookOrder bookOrder) {
	    return bookOrderMapper.insertBookOrder(bookOrder);
	}

	/**
     * 修改预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	@Override
	public int updateBookOrder(BookOrder bookOrder) {
	    return bookOrderMapper.updateBookOrder(bookOrder);
	}

	/**
     * 删除预订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBookOrderByIds(String ids) {
		return bookOrderMapper.deleteBookOrderByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据订单ID查询预订单ID
	 * @param orderId 订单ID
	 * @return 结果
	 */
	@Override
	public BookOrder getBookOrderByOrderId(Long orderId) {
		return bookOrderMapper.selectByOrderId(orderId);
	}

}
