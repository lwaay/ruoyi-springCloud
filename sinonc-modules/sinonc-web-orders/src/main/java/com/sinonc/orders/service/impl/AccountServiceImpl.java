package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Account;
import com.sinonc.orders.domain.UserRelation;
import com.sinonc.orders.mapper.AccountMapper;
import com.sinonc.orders.mapper.UserRelationMapper;
import com.sinonc.orders.service.AccountService;
import com.sinonc.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户账户 服务层实现
 *
 * @author sinonc
 * @date 2019-10-29
 */
@Service("account")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UserRelationMapper userRelationMapper;

	/**
     * 查询商户账户信息
     *
     * @param acctId 商户账户ID
     * @return 商户账户信息
     */
    @Override
	public Account getAccountById(Long acctId) {
	    return accountMapper.selectAccountById(acctId);
	}

	/**
	 * 根据店铺ID查询店铺账目
	 * @param shopId 店铺ID
	 * @return 结果
	 */
	@Override
	public Account getAccountByShopId(Long shopId) {
		return accountMapper.selectAccountByShopId(shopId);
	}

	/**
	 * 根据当前登陆用户获取账户信息
	 * @return 结果
	 */
	@Override
	public Account getAccountByCurrentUser() {
		Long userId = SecurityUtils.getUserId();
		SysUser sysUser = new SysUser();
		UserRelation userRelation = userRelationMapper.selectByDeptId(sysUser.getDeptId());
		Long shopId = userRelation.getShopId();
		return this.getAccountByShopId(shopId);
	}

	/**
     * 查询商户账户列表
     *
     * @param account 商户账户信息
     * @return 商户账户集合
     */
	@Override
	public List<Account> listAccount(Account account) {
	    return accountMapper.selectAccountList(account);
	}

    /**
     * 新增商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
	@Override
	public int addAccount(Account account) {
	    return accountMapper.insertAccount(account);
	}

	/**
     * 修改商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
	@Override
	public int updateAccount(Account account) {
	    return accountMapper.updateAccount(account);
	}

	/**
     * 删除商户账户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAccountByIds(String ids) {
		return accountMapper.deleteAccountByIds(Convert.toStrArray(ids));
	}

}
