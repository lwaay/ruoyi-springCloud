package com.sinonc.orders.service;

import com.sinonc.orders.domain.Refund;

import java.math.BigDecimal;
import java.util.List;

/**
 * 退款申请 服务层
 * 
 * @author sinonc
 * @date 2019-11-11
 */
public interface RefundService {

	/**
     * 查询退款申请信息
     * 
     * @param rfId 退款申请ID
     * @return 退款申请信息
     */
	public Refund getRefundById(Long rfId);
	
	/**
     * 查询退款申请列表
     * 
     * @param refund 退款申请信息
     * @return 退款申请集合
     */
	public List<Refund> listRefund(Refund refund);
	
	/**
     * 新增退款申请
     * 
     * @param refund 退款申请信息
     * @return 结果
     */
	public int addRefund(Refund refund);
	
	/**
     * 修改退款申请
     * 
     * @param refund 退款申请信息
     * @return 结果
     */
	public int updateRefund(Refund refund);
		
	/**
     * 删除退款申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteRefundByIds(String ids);


	/**
	 * 订单退款
	 * @param orderNo
	 * @param amount
	 * @param reason
	 * @return
	 */
	public void refund(String orderNo, BigDecimal amount, String reason,String confirmBy) throws Exception;

	/**
	 * 退款申请单操作
	 * @param rfId 退款单号
	 * @param content 操作内容
	 * @param amount 退款金额
	 * @param rfStatus 操作结果
	 * @param confirmBy 操作员
	 * @return 影响行数
	 */
	int operate(Long rfId, String content, BigDecimal amount, Integer rfStatus, String confirmBy) throws Exception;
}
