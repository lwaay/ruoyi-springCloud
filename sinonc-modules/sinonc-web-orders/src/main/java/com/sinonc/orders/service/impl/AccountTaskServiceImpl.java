package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.common.TradeCodeConstant;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.AccountTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 账户提现 服务层实现
 *
 * @author sinonc
 * @date 2019-11-15
 */
@Service
public class AccountTaskServiceImpl implements AccountTaskService {
	@Resource
	private AccountTaskMapper accountTaskMapper;

	@Resource
	private AccountMapper accountMapper;
	@Resource
	private AccountFlowMapper accountFlowMapper;
	@Resource
	private ShopMapper shopMapper;
	@Resource
	private  OrderMapper orderMapper;

	/**
     * 查询账户提现信息
     *
     * @param acctTaskId 账户提现ID
     * @return 账户提现信息
     */
    @Override
	public AccountTask getAccountTaskById(Long acctTaskId) {
	    return accountTaskMapper.selectAccountTaskById(acctTaskId);
	}

	/**
     * 查询账户提现列表
     *
     * @param accountTask 账户提现信息
     * @return 账户提现集合
     */
	@Override
	public List<AccountTask> listAccountTask(AccountTask accountTask) {

	    return accountTaskMapper.selectAccountTaskList(accountTask);
	}

    /**
     * 新增账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	@Override
	@Transactional
	public int addAccountTask(AccountTask accountTask) {
			Long userId = SecurityUtils.getUserId();
			accountTask.setUserIdP(userId);
			accountTask.setTaskFlag(1);//提交审核中
			//accountTask.setTaskTime(new Date());//日期为当前日期
			int count = 0;
		    //提现申请发起成功扣减账户金额 ，写入流水
			//根据shopid查询店铺账户
			Account account = accountMapper.selectAccountByShopIdForUpdate(accountTask.getShopIdP());
			if(account.getAcctAmount().compareTo(accountTask.getTaskAmount()) == -1){ //账户总金额小于要提现金额 提现申请失败
				return 0;
			}
			if(account.getAcctUsable().compareTo(accountTask.getTaskAmount()) == -1){ //账户可用金额小于要提现金额，提现申请失败
				return 0;
			}
			//账户总金额减要提现金额
			account.setAcctAmount(new BigDecimal(account.getAcctAmount().toString()).subtract(new BigDecimal(accountTask.getTaskAmount().toString())));
			//账户可用金额减去要提现金额
			account.setAcctUsable(new BigDecimal(account.getAcctUsable().toString()).subtract(new BigDecimal(accountTask.getTaskAmount().toString())));
			count += accountMapper.updateAccount(account);
			//写入提现流水
			AccountFlow accountllow = new AccountFlow();
			accountllow.setTradeTime(new Date());
			accountllow.setAcctIdP(account.getAcctId()); //账户id
			accountllow.setShopIdP(account.getShopIdP()); //店铺id
			accountllow.setOpAmount(accountTask.getTaskAmount()); //本次操作金额
			accountllow.setOutAmount(accountTask.getTaskAmount()); //本次支出金额
			accountllow.setOrderNo("-");
			accountllow.setOpType(AccountFlow.OP_TYPE_ASKFOR); //操作类型 2 提现
			accountllow.setTradeCode(TradeCodeConstant.ACCOUNT_ASK_FOR);
			accountllow.setRemark("账户:"+account.getAcctId() +"提现");
			count += accountFlowMapper.insertAccountFlow(accountllow);

			//新增提现申请
			count += accountTaskMapper.insertAccountTask(accountTask);
	    	return count;
	}

	/**
     * 修改账户提现
     *
     * @param accountTask 账户提现信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateAccountTask(AccountTask accountTask) {
		Date date = new Date();
		String loginName = SecurityUtils.getUsername();
		accountTask.setAuditTime(date);
		accountTask.setAuditBy(loginName);
		int count = 0;
		if(accountTask.getTaskFlag() == null){
			accountTask.setTaskFlag(2); //人工复核通过，执行转账操作，把状态改为通过
		}
		if(accountTask.getTaskFlag() == 4){ //初审通过，写入流水
			//根据shopid查询店铺账户
			Account account = accountMapper.selectAccountByShopIdForUpdate(accountTask.getShopIdP());
			//写入流水
			AccountFlow accountllow = new AccountFlow();
			accountllow.setTradeTime(new Date());
			accountllow.setAcctIdP(account.getAcctId()); //账户id
			accountllow.setShopIdP(account.getShopIdP()); //店铺id
			accountllow.setOpAmount(new BigDecimal(0)); //本次操作金额 ，不操作金额操作日志
			accountllow.setOutAmount(new BigDecimal(0)); //本次支出金额，不操作金额操作日志
			accountllow.setOrderNo("-");
			accountllow.setTradeCode(TradeCodeConstant.ACCOUNT_WITHDRAW);
			accountllow.setOpType(AccountFlow.OP_TYPE_ASKFOR); //操作类型 2 提现
			accountllow.setRemark("账户:"+account.getAcctId() +"提现初审通过");
			count += accountFlowMapper.insertAccountFlow(accountllow);
		}
		if(accountTask.getTaskFlag() == 2){ //复核通过，写入流水
			//根据shopid查询店铺账户
			Account account = accountMapper.selectAccountByShopIdForUpdate(accountTask.getShopIdP());

           // account.setAcctAmount((new BigDecimal(account.getAcctAmount().toString()).subtract(new BigDecimal(accountTask.getTaskAmount().toString()))));//账户总金额减提现金额

            BigDecimal outAmount=new BigDecimal(account.getOutAmount().toString());

            BigDecimal taskAmount=new BigDecimal(accountTask.getTaskAmount().toString());

            BigDecimal add=outAmount.add(taskAmount);

            account.setOutAmount(add); //提现成功累加账户支出

            count += accountMapper.updateAccount(account);
			//写入流水
			AccountFlow accountllow = new AccountFlow();
			accountllow.setTradeTime(new Date());
			accountllow.setAcctIdP(account.getAcctId()); //账户id
			accountllow.setShopIdP(account.getShopIdP()); //店铺id
			accountllow.setOpAmount(new BigDecimal(0)); //本次操作金额，不操作金额操作日志
			accountllow.setOutAmount(new BigDecimal(0)); //本次支出金额，不操作金额操作日志
			accountllow.setOrderNo("-");
			accountllow.setTradeCode(TradeCodeConstant.ACCOUNT_WITHDRAW);
			accountllow.setOpType(AccountFlow.OP_TYPE_ASKFOR); //操作类型 2 提现
			accountllow.setRemark("账户:"+account.getAcctId() +"提现复核通过");
			count += accountFlowMapper.insertAccountFlow(accountllow);
		}
		if(accountTask.getTaskFlag() == 3){ //审核驳回
			// 审核驳回退回账户扣的钱，写上流水
			//根据shopid查询店铺账户
			Account account = accountMapper.selectAccountByShopIdForUpdate(accountTask.getShopIdP());
			//商家账户提现驳回往账户增加回去总金额、可用金额
			account.setAcctAmount(account.getAcctAmount().add(accountTask.getTaskAmount()));
			account.setAcctUsable(account.getAcctUsable().add(accountTask.getTaskAmount()));
			count += accountMapper.updateAccount(account);
			//写入流水
			AccountFlow accountllow = new AccountFlow();
			accountllow.setTradeTime(new Date());
			accountllow.setAcctIdP(account.getAcctId()); //账户id
			accountllow.setShopIdP(account.getShopIdP()); //店铺id
			accountllow.setOpAmount(accountTask.getTaskAmount()); //本次操作金额
			accountllow.setOutAmount(accountTask.getTaskAmount()); //本次支出金额
			accountllow.setOrderNo("-");
			accountllow.setTradeCode(TradeCodeConstant.ACCOUNT_ROLLBACK);
			accountllow.setOpType(AccountFlow.OP_TYPE_ASKFOR); //操作类型 2 提现
			accountllow.setRemark("账户:"+account.getAcctId() +"提现驳回");
			count += accountFlowMapper.insertAccountFlow(accountllow);
		}
		//修改提现申请
		count += accountTaskMapper.updateAccountTask(accountTask);

		return count;
	}

	/**
     * 删除账户提现对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAccountTaskByIds(String ids) {
		return accountTaskMapper.deleteAccountTaskByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<AccountTask> queryAccountTaskList(AccountTask accountTask) {
		List<AccountTask> accountTaskList=accountTaskMapper.queryAccountTaskList(accountTask);
		return accountTaskList;
	}

	@Override
	public AccountTaskStatisticData queryAccountTaskStatistic(Long userId, Long shopId) {
		AccountTaskStatisticData statisticData = new AccountTaskStatisticData();
		AccountTask accountTask=new AccountTask();
		accountTask.setUserIdP(userId);
		List<AccountTask> accountTaskList=accountTaskMapper.queryAccountTaskList(accountTask);
		statisticData.setCrashOutCount(accountTaskList.size());
        //当日统计订单金额
		Double ttPayAomount = orderMapper.selectShopCount(shopId);
        //昨日统计访问量
        Long yesterdayNumber=0L;
		Long number = orderMapper.selectVisitor(shopId);
		if (number!=null){
			yesterdayNumber=number;
		}
		statisticData.setPayAccountNow(ttPayAomount);
		statisticData.setYesterdayVisitCount(yesterdayNumber);
		accountTask.setTaskFlag(2);//审核通过
		List<AccountTask> accountTaskListTwo=accountTaskMapper.queryAccountTaskList(accountTask);
		statisticData.setCrashOutSuccessCount(accountTaskListTwo.size());
		BigDecimal cgtxzje=new BigDecimal(0);
		for (int i = 0; i < accountTaskListTwo.size(); i++) {
			AccountTask tempAccountTask=accountTaskListTwo.get(i);
			BigDecimal tempBigDecimal=tempAccountTask.getTaskAmount();
			cgtxzje=cgtxzje.add(tempBigDecimal);
		}
		statisticData.setCrashOutSuccessAmount(cgtxzje);

		Account account=accountMapper.selectAccountByShopId(shopId);
		if (account!=null){
			statisticData.setAvailAmount(account.getAcctUsable());
			statisticData.setTotalAmount(account.getAcctAmount());
		}else {
			statisticData.setAvailAmount(new BigDecimal(0));
			statisticData.setTotalAmount(new BigDecimal(0));
		}


		Shop shop = shopMapper.selectShopById(shopId);
//		//shop.setMobile(shop.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
//		//粉丝数
//		String attention = shopMapper.selectShopAttention(shopId);
//		if (attention==null||attention.equals("")){
//			shop.setAttention(0);
//		}
//		else {
//			String[] split = attention.split(",");
//			shop.setAttention(split.length);
//		}
		statisticData.setShop(shop);

		return statisticData;
	}

}
