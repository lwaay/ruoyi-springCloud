package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.mapper.OrderItemMapper;
import com.sinonc.orders.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单项目 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
     * 查询订单项目信息
     *
     * @param orderItemId 订单项目ID
     * @return 订单项目信息
     */
    @Override
	public OrderItem getOrderItemById(Long orderItemId) {
	    return orderItemMapper.selectOrderItemById(orderItemId);
	}

	/**
     * 查询订单项目列表
     *
     * @param orderItem 订单项目信息
     * @return 订单项目集合
     */
	@Override
	public List<OrderItem> listOrderItem(OrderItem orderItem) {
	    return orderItemMapper.selectOrderItemList(orderItem);
	}

    /**
     * 新增订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	@Override
	public int addOrderItem(OrderItem orderItem) {
	    return orderItemMapper.insertOrderItem(orderItem);
	}

	/**
     * 修改订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	@Override
	public int updateOrderItem(OrderItem orderItem) {
	    return orderItemMapper.updateOrderItem(orderItem);
	}

	/**
     * 删除订单项目对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderItemByIds(String ids) {
		return orderItemMapper.deleteOrderItemByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据商品id查询订单商品关联表
	 * @param goodsId
	 * @return
	 */
    @Override
    public OrderItem selectOrderItemByGoodsId(Long goodsId) {
    	Long memberId = SecurityUtils.getUserId();
        return orderItemMapper.selectOrderItemByGoodsId(goodsId,memberId);
    }
	/**
	 * 根据订单id查询订单商品关联表
	 * @param orderId
	 * @return
	 */
    @Override
    public OrderItem selectOrderItemByOrderId(Long orderId) {
        return orderItemMapper.selectOrderItemByOrderId(orderId);
    }

}
