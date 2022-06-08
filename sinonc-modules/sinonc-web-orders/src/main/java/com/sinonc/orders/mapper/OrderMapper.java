package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Order;
import com.sinonc.orders.dto.AccountDto;
import com.sinonc.orders.dto.OrderCountDto;
import com.sinonc.orders.dto.OrderDto;
import com.sinonc.orders.dto.SettlementDto;
import com.sinonc.orders.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface OrderMapper {
    /**
     * 查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单信息
     */
    public Order selectOrderById(Long orderId);

    /**
     * 查询订单信息并上锁
     *
     * @param orderId
     * @return
     */
    public Order selectOrderByIdForUpdate(Long orderId);

    /**
     * 根据订单编号查询订单信息
     *
     * @param no 订单编号
     * @return 订单信息
     */
    public Order selectOrderByNo(String no);

    /**
     * 查询订单列表
     *
     * @param order 订单信息
     * @return 订单集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 查询订单结算数据对象
     * @param order 订单
     * @return
     */
    public List<SettlementDto<Order>> selectSettlementDto(Order order);

    /**
     * 新增订单
     *
     * @param order 订单信息
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单
     *
     * @param order 订单信息
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 乐观锁机制更新订单状态
     *
     * @param order 订单信息
     * @return
     */
    public int updateOrderOptimistic(@Param("order") Order order, @Param("lock") Order lock);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteOrderById(Long orderId);

    /**
     * 批量删除订单
     *
     * @param orderIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderByIds(String[] orderIds);


    List<OrderVO> selectOrderVoList(Order order);


    List<OrderVO> listDataScopeOrderVo(OrderDto order);


    List<OrderVO> selectOrderDtoList(OrderDto order);

    /**
     * 根据条件查询数量
     *
     * @param order 订单对象
     * @return 结果
     */
    public int selectCount(Order order);

    /**
     * 根据orderId查询订单Vo
     *
     * @param orderId
     * @return
     */
    public OrderVO selectOrderVoById(Long orderId);

    Map<String, Object> selectOrderStatusCount(Order order);

    /**
     * 查询所有订单成交金额
     *
     * @param shopId
     * @return
     */
    public Double selectShopCount(Long shopId);

    /**
     * 查询所有该店铺订单成交金额当日 如果传入时间就查询当天，不传人则查询全部
     *
     * @param accountDto
     * @return
     */
    public Double selectShopCountByDate(AccountDto accountDto);

    /**
     * 查询当天访问量
     *
     * @param shopId
     * @return
     */
    public Long selectVisitor(Long shopId);

    /**
     * 查询订单总数
     *
     * @return
     */
    public Long countOrders();

    /**
     * 获取用户订单今日收入总金额
     *
     * @param order
     * @return
     */
    public double selectTodayOrdersPayment(OrderDto order);

    /**
     * 查询订单统计
     *
     * @param orderDto
     * @return
     */
    public Long getOrdersCount(OrderDto orderDto);


    /**
     * 根据订单类型统计订单数量
     *
     * @param accountDto
     * @return
     */
    public Long selectCountByType(AccountDto accountDto);

	/**
	 * 查询近一月的订单统计
	 * @return
	 */
	public List<OrderCountDto> selectOrderCountByDay(AccountDto accountDto);
	/**
	 * 查询近半年的订单统计
	 * @return
	 */
	public List<OrderCountDto> selectOrderCountByMonth(AccountDto accountDto);
	/**
	 * 查询近一年的订单统计
	 * @return
	 */
	public List<OrderCountDto> selectOrderCountByYear(AccountDto accountDto);

    /**
     * 更新直购订单结算状态
     * @param i 结算状态
     * @param orderIds 订单id集合
     * @return
     */
    public int updateSettlement(@Param("settlement") int i,@Param("list") List<Long> orderIds);
}
