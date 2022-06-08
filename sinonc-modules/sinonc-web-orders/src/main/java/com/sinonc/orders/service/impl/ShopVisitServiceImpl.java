package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.ShopVisit;
import com.sinonc.orders.mapper.ShopVisitMapper;
import com.sinonc.orders.service.ShopVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 店铺访问量 服务层实现
 *
 * @author sinonc
 * @date 2019-11-19
 */
@Service
public class ShopVisitServiceImpl implements ShopVisitService {
	@Autowired
	private ShopVisitMapper shopVisitMapper;

	/**
     * 查询店铺访问量信息
     *
     * @param visitId 店铺访问量ID
     * @return 店铺访问量信息
     */
    @Override
	public ShopVisit getShopVisitById(Long visitId) {
	    return shopVisitMapper.selectShopVisitById(visitId);
	}

	/**
     * 查询店铺访问量列表
     *
     * @param shopVisit 店铺访问量信息
     * @return 店铺访问量集合
     */
	@Override
	public List<ShopVisit> listShopVisit(ShopVisit shopVisit) {
	    return shopVisitMapper.selectShopVisitList(shopVisit);
	}

    /**
     * 新增店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	@Override
	public int addShopVisit(ShopVisit shopVisit) {

		ShopVisit shopVisitPara=new ShopVisit();
		shopVisitPara.setVisitDate(new Date());
		shopVisitPara.setShopIdP(shopVisit.getShopIdP());

		List<ShopVisit> shopVisitList=shopVisitMapper.selectShopVisitList(shopVisitPara);
		if(shopVisitList!=null&&shopVisitList.size()!=0){
			ShopVisit tempShopVisit=shopVisitList.get(0);
			tempShopVisit.setVisitNumber(tempShopVisit.getVisitNumber()+1);
			if(shopVisit.getMemberIdP()!=null){
				tempShopVisit.setMemberIdP(shopVisit.getMemberIdP());
			}
			return shopVisitMapper.updateShopVisit(tempShopVisit);
		}else {
			shopVisit.setVisitDate(new Date());
			shopVisit.setVisitNumber(Long.valueOf(1));
			return shopVisitMapper.insertShopVisit(shopVisit);
		}
	}

	/**
     * 修改店铺访问量
     *
     * @param shopVisit 店铺访问量信息
     * @return 结果
     */
	@Override
	public int updateShopVisit(ShopVisit shopVisit) {
	    return shopVisitMapper.updateShopVisit(shopVisit);
	}

	/**
     * 删除店铺访问量对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShopVisitByIds(String ids) {
		return shopVisitMapper.deleteShopVisitByIds(Convert.toStrArray(ids));
	}


	@Override
	public Long getShopVisit(ShopVisit shopVisit) {
		return shopVisitMapper.selectTodayVisit(shopVisit);
	}

}
