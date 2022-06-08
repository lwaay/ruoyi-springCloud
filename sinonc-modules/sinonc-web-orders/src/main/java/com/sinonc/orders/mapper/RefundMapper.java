package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Refund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 退款申请 数据层
 *
 * @author sinonc
 * @date 2019-11-11
 */
@Mapper
public interface RefundMapper {
	/**
     * 查询退款申请信息
     *
     * @param rfId 退款申请ID
     * @return 退款申请信息
     */
	public Refund selectRefundById(Long rfId);

	/**
     * 查询退款申请列表
     *
     * @param refund 退款申请信息
     * @return 退款申请集合
     */
	public List<Refund> selectRefundList(Refund refund);

	/**
     * 新增退款申请
     *
     * @param refund 退款申请信息
     * @return 结果
     */
	public int insertRefund(Refund refund);

	/**
     * 修改退款申请
     *
     * @param refund 退款申请信息
     * @return 结果
     */
	public int updateRefund(Refund refund);

	/**
     * 删除退款申请
     *
     * @param rfId 退款申请ID
     * @return 结果
     */
	public int deleteRefundById(Long rfId);

	/**
     * 批量删除退款申请
     *
     * @param rfIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteRefundByIds(String[] rfIds);


    Refund selectRefundByOrderNo(String orderNo);
}
