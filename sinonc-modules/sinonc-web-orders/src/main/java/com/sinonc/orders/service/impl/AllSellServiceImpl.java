package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.domain.AllSell;
import com.sinonc.orders.mapper.AllSellMapper;
import com.sinonc.orders.service.AllSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信丰脐橙销售 服务层实现
 *
 * @author sinonc
 * @date 2019-11-14
 */
@Service
public class AllSellServiceImpl implements AllSellService {
	@Autowired
	private AllSellMapper allSellMapper;
	/**
     * 查询信丰脐橙销售信息
     *
     * @param id 信丰脐橙销售ID
     * @return 信丰脐橙销售信息
     */
    @Override
	public AllSell getAllSellById(Long id) {
	    return allSellMapper.selectAllSellById(id);
	}

	/**
     * 查询信丰脐橙销售列表
     *
     * @param allSell 信丰脐橙销售信息
     * @return 信丰脐橙销售集合
     */
	@Override
	public List<AllSell> listAllSell(AllSell allSell) {
		List<AllSell> allSells = allSellMapper.selectAllSellList(allSell);
		for (AllSell sell : allSells) {
			if(sell.getFarmId()==0){
				sell.setFirmName("");
			}
		}
		return allSells;
	}

    /**
     * 新增信丰脐橙销售
     *
     * @param allSells 信丰脐橙销售信息
     * @return 结果
     */
	@Override
	public int addAllSell(List<AllSell> allSells) {

		for (AllSell allSell : allSells) {
			allSell.setMoney(allSell.getPrice()*allSell.getAmount());
			allSell.setCreateTime(DateUtils.getNowDate());
		}
	    return allSellMapper.insertAllSell(allSells);
	}

	/**
     * 修改信丰脐橙销售
     *
     * @param allSell 信丰脐橙销售信息
     * @return 结果
     */
	@Override
	public int updateAllSell(AllSell allSell) {
		//计算金额
		allSell.setMoney(allSell.getPrice()*allSell.getAmount());
	    return allSellMapper.updateAllSell(allSell);
	}

	/**
     * 删除信丰脐橙销售对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAllSellByIds(String ids) {
		return allSellMapper.deleteAllSellByIds(Convert.toStrArray(ids));
	}

}
