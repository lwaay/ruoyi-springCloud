package com.sinonc.orders.service;

import com.sinonc.orders.domain.Order;
import com.sinonc.orders.dto.AccountDto;
import com.sinonc.orders.dto.OrderDto;
import com.sinonc.orders.vo.FareVo;
import com.sinonc.orders.vo.GoodsVo;
import com.sinonc.orders.vo.OrderVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface OrderService {

    /**
     * 查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    public Order getOrderById(Long orderId);

    /**
     * 根据订单ID查询订单VO
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public OrderVO getOrderVoById(Long orderId);

    /**
     * 查询会员订单vo列表
     *
     * @param order 会员ID
     * @return 订单Vo列表
     */
    public List<OrderVO> listOrderVO(Order order);

    /**
     * 查询会员订单vo列表
     *
     * @param order 会员ID
     * @return 订单Vo列表
     */
    public List<OrderVO> listDataScopeOrderVO(OrderDto order);

    /**
     * 查询会员订单dto列表
     *
     * @param order 会员ID
     * @return 订单Vo列表
     */
    public List<OrderVO> listOrderDto(OrderDto order);

    /**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
    public List<Order> listOrder(Order order);

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
    public int addOrder(Order order);

    /**
     * 通过API接口创建订单
     *
     * @param orderVO 订单视图对象
     * @return 结果
     */
    public Order createOrder(OrderVO<GoodsVo> orderVO);


    /**
     * 根据订单ID取消订单
     * @param order 订单
     * @return
     */
    public int cancelOrder(Order order);

    /**
     * 确认收货
     * @param order 订单
     * @return 结果
     */
    public int confirmOrder(Order order);
    /**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderByIds(String ids);


    /**
     * 获取订单总数
     * @param order
     * @return
     */
    public Map<String,Object> getOrderStatusCount(Order order);


    /**
     * 修改商家优惠金额
     * @param orderId 订单ID
     * @param amount 优惠金额
     * @return
     */
    int shopCustomDiscount(Long orderId, String amount);

    /**
     * 获取登录用户订单今日收入总金额
     * @param orderDto
     * @return
     */
    double getTodayOrdersPayment(OrderDto orderDto) ;

    /**
     * 统计订单数量
     * @param orderDto
     * @return
     */
    Long getOrderCount(OrderDto orderDto);


    /**
     * 查询近期订单金额
     * accountDto
     * @return
     */
    public Map getOrderCountByDate(AccountDto accountDto);

    /**
     * 根据订单计算运费
     * @param fareVo
     * @return
     */
    public BigDecimal getFare(FareVo fareVo);

    /**
     * 根据订单号查询订单信息
     * @param orderNo
     * @return
     */
    public Order getOrderByNo(String orderNo);
}
