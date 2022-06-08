package com.sinonc.orders.service;

import com.sinonc.orders.domain.AccountFlow;

import java.util.List;

/**
 * 账户流水 服务层
 *
 * @author sinonc
 * @date 2019-10-29
 */
public interface AccountFlowService {

	/**
     * 查询账户流水信息
     *
     * @param acctFlowId 账户流水ID
     * @return 账户流水信息
     */
	public AccountFlow getAccountFlowById(Integer acctFlowId);

	/**
     * 查询账户流水列表
     *
     * @param accountFlow 账户流水信息
     * @return 账户流水集合
     */
	public List<AccountFlow> listAccountFlow(AccountFlow accountFlow);

	/**
     * 新增账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
	public int addAccountFlow(AccountFlow accountFlow);

	/**
     * 修改账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
	public int updateAccountFlow(AccountFlow accountFlow);

	/**
     * 删除账户流水信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountFlowByIds(String ids);

}
