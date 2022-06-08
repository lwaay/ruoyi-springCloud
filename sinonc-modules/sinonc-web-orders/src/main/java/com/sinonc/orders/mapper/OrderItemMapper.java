package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单项目 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface OrderItemMapper {
	/**
     * 查询订单项目信息
     *
     * @param orderItemId 订单项目ID
     * @return 订单项目信息
     */
	public OrderItem selectOrderItemById(Long orderItemId);
	/**
	 * 查询订单项目信息
	 *
	 * @param orderId 订单项目ID
	 * @return 订单项目信息
	 */
	public OrderItem selectOrderItemByOrderId(Long orderId);

	/**
	 * 根据订单id查询订单明细
	 */
	public List<OrderItem> getOrderItemByOrderId(Long orderId);


	/**
     * 查询订单项目列表
     *
     * @param orderItem 订单项目信息
     * @return 订单项目集合
     */
	public List<OrderItem> selectOrderItemList(OrderItem orderItem);

	/**
     * 新增订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	public int insertOrderItem(OrderItem orderItem);

	/**
     * 修改订单项目
     *
     * @param orderItem 订单项目信息
     * @return 结果
     */
	public int updateOrderItem(OrderItem orderItem);

	/**
     * 删除订单项目
     *
     * @param orderItemId 订单项目ID
     * @return 结果
     */
	public int deleteOrderItemById(Long orderItemId);

	/**
     * 批量删除订单项目
     *
     * @param orderItemIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderItemByIds(String[] orderItemIds);

	public OrderItem selectOrderItemGoodsIdAndSpecsId(@Param("goodsIdP")Long goodsIdP, @Param("specsIdP")Long specsIdP);

	/**
	 * 根据shopId查询对应的订单明细列表
	 * @param shopId
	 * @return
	 */
	public List<OrderItem> queryShopOrderItem(Long shopId);

	/**
	 * 根据商品id查询订单商品关联表
	 * @param goodsId
	 * @param memberId
	 * @return
	 */
    OrderItem selectOrderItemByGoodsId(@Param("goodsId") Long goodsId, @Param("memberId")Long memberId);

	/**
	 * 统计商品销量
	 */
	Long countGoodsSale(@Param("goodsId") Long goodsId);
}
