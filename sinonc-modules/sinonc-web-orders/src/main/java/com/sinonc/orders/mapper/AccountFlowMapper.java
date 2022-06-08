package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AccountFlow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 账户流水 数据层
 *
 * @author sinonc
 * @date 2019-10-29
 */
@Mapper
public interface AccountFlowMapper {
	/**
     * 查询账户流水信息
     *
     * @param acctFlowId 账户流水ID
     * @return 账户流水信息
     */
	public AccountFlow selectAccountFlowById(Integer acctFlowId);

	/**
     * 查询账户流水列表
     *
     * @param accountFlow 账户流水信息
     * @return 账户流水集合
     */
	public List<AccountFlow> selectAccountFlowList(AccountFlow accountFlow);

	/**
     * 新增账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
	public int insertAccountFlow(AccountFlow accountFlow);

	/**
     * 修改账户流水
     *
     * @param accountFlow 账户流水信息
     * @return 结果
     */
	public int updateAccountFlow(AccountFlow accountFlow);

	/**
     * 删除账户流水
     *
     * @param acctFlowId 账户流水ID
     * @return 结果
     */
	public int deleteAccountFlowById(Integer acctFlowId);

	/**
     * 批量删除账户流水
     *
     * @param acctFlowIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountFlowByIds(String[] acctFlowIds);

}
