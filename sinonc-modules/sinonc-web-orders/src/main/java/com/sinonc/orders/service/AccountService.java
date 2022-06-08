package com.sinonc.orders.service;

import com.sinonc.orders.domain.Account;

import java.util.List;

/**
 * 商户账户 服务层
 *
 * @author sinonc
 * @date 2019-10-29
 */
public interface AccountService {

	/**
     * 查询商户账户信息
     *
     * @param acctId 商户账户ID
     * @return 商户账户信息
     */
	public Account getAccountById(Long acctId);

	/**
	 * 根据店铺ID查询店铺账目
	 * @param shopId 店铺ID
	 * @return 结果
	 */
	public Account getAccountByShopId(Long shopId);

	/**
	 * 根据当前登陆用户获取账户信息
	 * @return 结果
	 */
	public Account getAccountByCurrentUser();

	/**
     * 查询商户账户列表
     *
     * @param account 商户账户信息
     * @return 商户账户集合
     */
	public List<Account> listAccount(Account account);

	/**
     * 新增商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
	public int addAccount(Account account);

	/**
     * 修改商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
	public int updateAccount(Account account);

	/**
     * 删除商户账户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAccountByIds(String ids);



}
