package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.BookOrder;
import com.sinonc.orders.dto.SettlementDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 预订单 数据层
 *
 * @author sinonc
 * @date 2019-11-01
 */
@Mapper
public interface BookOrderMapper {
	/**
     * 查询预订单信息
     *
     * @param boId 预订单ID
     * @return 预订单信息
     */
	public BookOrder selectBookOrderById(Long boId);

	/**
     * 查询预订单列表
     *
     * @param bookOrder 预订单信息
     * @return 预订单集合
     */
	public List<BookOrder> selectBookOrderList(BookOrder bookOrder);

	/**
     * 新增预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	public int insertBookOrder(BookOrder bookOrder);

	/**
     * 修改预订单
     *
     * @param bookOrder 预订单信息
     * @return 结果
     */
	public int updateBookOrder(BookOrder bookOrder);

	/**
     * 删除预订单
     *
     * @param boId 预订单ID
     * @return 结果
     */
	public int deleteBookOrderById(Long boId);

	/**
     * 批量删除预订单
     *
     * @param boIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBookOrderByIds(String[] boIds);

	/**
	 * 根据订单id查询预订的信息
	 * @param orderId
	 * @return
	 */
    public BookOrder selectByOrderId(Long orderId);

	/**
	 * 根据订单ids查询订单信息
	 * @param ids 订单ids
	 * @return
	 */
	List<BookOrder> selectBookOrderByOrderIds(ArrayList<Long> ids);

	/**
	 * 根据订单ids查询定金单
	 * @param ids 订单ids
	 * @return
	 */
	List<SettlementDto<BookOrder>> selectSettlementByOrderIds(ArrayList<Long> ids);

	/**
	 * 查询定金单结算DTO对象
	 * @param params
	 * @return
	 */
	List<SettlementDto<BookOrder>> selectSettlementDto(BookOrder params);
}
