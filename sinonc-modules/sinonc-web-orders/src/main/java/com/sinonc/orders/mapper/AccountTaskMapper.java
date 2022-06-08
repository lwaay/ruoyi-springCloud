package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AccountTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 账户提现 数据层
 *
 * @author sinonc
 * @date 2019-11-15
 */
@Mapper
public interface AccountTaskMapper {
	/**
     * 查询账户提现信息
     *
     * @param acctTaskId 账户提现ID
     * @return 账户提现信息
     */
	public AccountTask selectAccountTaskById(Long acctTaskId);

	/**
     * 查询账户提现列表
     *
     * @param accountTask 账户提现信息
     * @return 账户提现集合
     */
	public List<AccountTask> selectAccountTaskList(AccountTask accountTask);

	/**
     * 新增账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	public int insertAccountTask(AccountTask accountTask);

	/**
     * 修改账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	public int updateAccountTask(AccountTask accountTask);

	/**
     * 删除账户提现
     *
     * @param acctTaskId 账户提现ID
     * @return 结果
     */
	public int deleteAccountTaskById(Long acctTaskId);

	/**
     * 批量删除账户提现
     *
     * @param acctTaskIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountTaskByIds(String[] acctTaskIds);

    public List<AccountTask> queryAccountTaskList(AccountTask accountTask);
}
