package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.RefundLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 退款申请日志 数据层
 *
 * @author sinonc
 * @date 2019-11-11
 */
@Mapper
public interface RefundLogMapper {
	/**
     * 查询退款申请日志信息
     *
     * @param logId 退款申请日志ID
     * @return 退款申请日志信息
     */
	public RefundLog selectRefundLogById(Long logId);

	/**
     * 查询退款申请日志列表
     *
     * @param refundLog 退款申请日志信息
     * @return 退款申请日志集合
     */
	public List<RefundLog> selectRefundLogList(RefundLog refundLog);

	/**
     * 新增退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	public int insertRefundLog(RefundLog refundLog);

	/**
     * 修改退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	public int updateRefundLog(RefundLog refundLog);

	/**
     * 删除退款申请日志
     *
     * @param logId 退款申请日志ID
     * @return 结果
     */
	public int deleteRefundLogById(Long logId);

	/**
     * 批量删除退款申请日志
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteRefundLogByIds(String[] logIds);

}
