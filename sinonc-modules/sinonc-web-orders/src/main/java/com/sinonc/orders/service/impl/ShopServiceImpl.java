package com.sinonc.orders.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.Account;
import com.sinonc.orders.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.ShopMapper;
import com.sinonc.orders.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 店铺信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-30
 */
@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 主体id
     * @param entityId
     * @return
     */
    @Override
    public List<Shop> selectShopByEntity(Long entityId){
        return shopMapper.selectShopByEntity(entityId);
    }

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询店铺信息
     *
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    @Override
    public Shop selectShopById(Long shopId) {
        return shopMapper.selectShopById(shopId);
    }

    /**
     * 查询店铺信息列表
     *
     * @param shop 店铺信息
     * @return 店铺信息
     */
    @Override
    public List<Shop> selectShopList(Shop shop) {
        return shopMapper.selectShopList(shop);
    }

    /**
     * 新增店铺信息
     *
     * @param shop 店铺信息
     * @return 结果
     */
    @Override
    public int insertShop(Shop shop) {
        shop.setCreateTime(DateUtils.getNowDate());
        int i = shopMapper.insertShop(shop);
        if(i == 1){
            //插入对应的商户账户
            Account account = new Account();
            //店铺ID
            account.setShopIdP(shop.getShopId());
            //店铺名称
            account.setShopName(shop.getShopName());
            //账户总金额
            account.setAcctAmount(new BigDecimal("0.00"));
            //可用金额
            account.setAcctUsable(new BigDecimal("0.00"));
            //冻结金额
            account.setAcctLock(new BigDecimal("0.00"));
            //累计收入
            account.setIncomeAmount(new BigDecimal("0.00"));
            //累计支出
            account.setOutAmount(new BigDecimal("0.00"));
            account.setUpdateTime(new Date());
            accountMapper.insertAccount(account);
        }
        return i;
    }

    /**
     * 修改店铺信息
     *
     * @param shop 店铺信息
     * @return 结果
     */
    @Override
    public int updateShop(Shop shop) {
        shop.setUpdateTime(DateUtils.getNowDate());
        return shopMapper.updateShop(shop);
    }

    /**
     * 批量删除店铺信息
     *
     * @param shopIds 需要删除的店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteShopByIds(Long[] shopIds) {
        return shopMapper.deleteShopByIds(shopIds);
    }

    /**
     * 删除店铺信息信息
     *
     * @param shopId 店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteShopById(Long shopId) {
        return shopMapper.deleteShopById(shopId);
    }
}
