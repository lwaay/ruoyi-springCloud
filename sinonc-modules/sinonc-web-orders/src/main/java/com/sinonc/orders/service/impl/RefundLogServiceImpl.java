package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.RefundLog;
import com.sinonc.orders.mapper.RefundLogMapper;
import com.sinonc.orders.service.RefundLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退款申请日志 服务层实现
 *
 * @author sinonc
 * @date 2019-11-11
 */
@Service
public class RefundLogServiceImpl implements RefundLogService {
	@Autowired
	private RefundLogMapper refundLogMapper;

	/**
     * 查询退款申请日志信息
     *
     * @param logId 退款申请日志ID
     * @return 退款申请日志信息
     */
    @Override
	public RefundLog getRefundLogById(Long logId) {
	    return refundLogMapper.selectRefundLogById(logId);
	}

	/**
     * 查询退款申请日志列表
     *
     * @param refundLog 退款申请日志信息
     * @return 退款申请日志集合
     */
	@Override
	public List<RefundLog> listRefundLog(RefundLog refundLog) {
	    return refundLogMapper.selectRefundLogList(refundLog);
	}

    /**
     * 新增退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	@Override
	public int addRefundLog(RefundLog refundLog) {
	    return refundLogMapper.insertRefundLog(refundLog);
	}

	/**
     * 修改退款申请日志
     *
     * @param refundLog 退款申请日志信息
     * @return 结果
     */
	@Override
	public int updateRefundLog(RefundLog refundLog) {
	    return refundLogMapper.updateRefundLog(refundLog);
	}

	/**
     * 删除退款申请日志对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteRefundLogByIds(String ids) {
		return refundLogMapper.deleteRefundLogByIds(Convert.toStrArray(ids));
	}

}
