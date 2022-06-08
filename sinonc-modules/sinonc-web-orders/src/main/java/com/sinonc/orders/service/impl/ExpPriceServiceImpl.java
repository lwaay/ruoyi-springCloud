package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.ExpPrice;
import com.sinonc.orders.mapper.ExpPriceMapper;
import com.sinonc.orders.service.ExpPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流价格模版 服务层实现
 *
 * @author sinonc
 * @date 2019-11-26
 */
@Service
public class ExpPriceServiceImpl implements ExpPriceService {
	@Autowired
	private ExpPriceMapper expPriceMapper;

	/**
     * 查询物流价格模版信息
     *
     * @param expPriceId 物流价格模版ID
     * @return 物流价格模版信息
     */
    @Override
	public ExpPrice getExpPriceById(Long expPriceId) {
	    return expPriceMapper.selectExpPriceById(expPriceId);
	}

	/**
     * 查询物流价格模版列表
     *
     * @param expPrice 物流价格模版信息
     * @return 物流价格模版集合
     */
	@Override
	public List<ExpPrice> listExpPrice(ExpPrice expPrice) {
	    return expPriceMapper.selectExpPriceList(expPrice);
	}

    /**
     * 新增物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	@Override
	public int addExpPrice(ExpPrice expPrice) {
	    return expPriceMapper.insertExpPrice(expPrice);
	}

	/**
     * 修改物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	@Override
	public int updateExpPrice(ExpPrice expPrice) {
	    return expPriceMapper.updateExpPrice(expPrice);
	}

	/**
     * 删除物流价格模版对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExpPriceByIds(String ids) {
		return expPriceMapper.deleteExpPriceByIds(Convert.toStrArray(ids));
	}

	@Override
	public String getprovincesByShopId(Long shopId) {
		ExpPrice expPrice=new ExpPrice();
		expPrice.setShopIdP(shopId.intValue());

		List<ExpPrice> expPriceList=expPriceMapper.selectExpPriceList(expPrice);
		String expPriceString="";

		if (expPriceList!=null&&expPriceList.size()>0){
			for (int i = 0; i < expPriceList.size(); i++) {
				ExpPrice tempExpPrice=expPriceList.get(i);
				String province=tempExpPrice.getProvince();
				if(expPriceString.compareTo("")==0){
					expPriceString=province;
				}else {
					expPriceString=expPriceString+","+province;
				}
			}
		}
		return expPriceString;
	}

}
