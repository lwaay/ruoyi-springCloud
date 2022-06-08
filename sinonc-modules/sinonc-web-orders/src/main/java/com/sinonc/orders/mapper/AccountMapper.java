package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Account;
import com.sinonc.orders.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商户账户 数据层
 *
 * @author sinonc
 * @date 2019-10-29
 */
@Mapper
public interface AccountMapper {
    /**
     * 查询商户账户信息
     *
     * @param acctId 商户账户ID
     * @return 商户账户信息
     */
    public Account selectAccountById(Long acctId);

    /**
     * 查询商户账户列表
     *
     * @param account 商户账户信息
     * @return 商户账户集合
     */
    public List<Account> selectAccountList(Account account);

    /**
     * 新增商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
    public int insertAccount(Account account);

    /**
     * 修改商户账户
     *
     * @param account 商户账户信息
     * @return 结果
     */
    public int updateAccount(Account account);

    /**
     * 删除商户账户
     *
     * @param acctId 商户账户ID
     * @return 结果
     */
    public int deleteAccountById(Long acctId);

    /**
     * 批量删除商户账户
     *
     * @param acctIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccountByIds(String[] acctIds);


    /**
     * 根据shopId查询账户
     *
     * @param shopId shopId
     * @return 结果
     */
    public Account selectAccountByShopId(Long shopId);


    /**
     * 根据shopId 查询账户并增加更新锁
     * @param shopId shopId
     * @return 结果
     */
    public Account selectAccountByShopIdForUpdate(Long shopId);

    /**
     * 查询累计收入总额
     * @return
     */
    public Double countAccount();

    /**
     * 查询累计收入额
     * @param accountDto
     * @return
     */
    public Double selectAccountByShopIdAndDate(AccountDto accountDto);
}
