package com.sinonc.orders.service;

import com.sinonc.orders.domain.AccountTask;
import com.sinonc.orders.domain.AccountTaskStatisticData;

import java.util.List;
import java.util.Map;

/**
 * 账户提现 服务层
 *
 * @author sinonc
 * @date 2019-11-15
 */
public interface AccountTaskService {

	/**
     * 查询账户提现信息
     *
     * @param acctTaskId 账户提现ID
     * @return 账户提现信息
     */
	public AccountTask getAccountTaskById(Long acctTaskId);

	/**
     * 查询账户提现列表
     *
     * @param accountTask 账户提现信息
     * @return 账户提现集合
     */
	public List<AccountTask> listAccountTask(AccountTask accountTask);

	/**
     * 新增账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	public int addAccountTask(AccountTask accountTask);

	/**
     * 修改账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	public int updateAccountTask(AccountTask accountTask);

	/**
     * 删除账户提现信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountTaskByIds(String ids);

	public List<AccountTask> queryAccountTaskList(AccountTask accountTask);


	public AccountTaskStatisticData queryAccountTaskStatistic(Long userId, Long shopId);
}
