package com.sinonc.orders.service;

import com.sinonc.orders.domain.RefundLog;

import java.util.List;

/**
 * 退款申请日志 服务层
 *
 * @author sinonc
 * @date 2019-11-11
 */
public interface RefundLogService {

	/**
     * 查询退款申请日志信息
     *
     * @param logId 退款申请日志ID
     * @return 退款申请日志信息
     */
	public RefundLog getRefundLogById(Long logId);

	/**
     * 查询退款申请日志列表
     *
     * @param refundLog 退款申请日志信息
     * @return 退款申请日志集合
     */
	public List<RefundLog> listRefundLog(RefundLog refundLog);

	/**
     * 新增退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	public int addRefundLog(RefundLog refundLog);

	/**
     * 修改退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	public int updateRefundLog(RefundLog refundLog);

	/**
     * 删除退款申请日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRefundLogByIds(String ids);

}
