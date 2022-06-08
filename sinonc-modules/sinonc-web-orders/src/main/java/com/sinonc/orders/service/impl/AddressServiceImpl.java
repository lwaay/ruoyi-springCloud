package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.Address;
import com.sinonc.orders.mapper.AddressMapper;
import com.sinonc.orders.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 收货地址 服务层实现
 *
 * @author sinonc
 * @date 2019-07-26
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询收货地址信息
     *
     * @param addressId 收货地址ID
     * @return 收货地址信息
     */
    @Override
    public Address getAddressById(Long addressId) {
        return addressMapper.selectAddressById(addressId);
    }

    /**
     * 查询收货地址列表
     *
     * @param address 收货地址信息
     * @return 收货地址集合
     */
    @Override
    public List<Address> listAddress(Address address) {
        return addressMapper.selectAddressList(address);
    }

    /**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
    @Override
    public int addAddress(Address address) {
        Address query = new Address();
        query.setMemberIdP(address.getMemberIdP());
        List<Address> old = addressMapper.selectAddressList(query);
        if (CollectionUtils.isEmpty(old)){
            return addressMapper.insertAddress(address);
        }
        long isDefault = old.stream().filter(item->item.getDefaults() == 1).count();
        if (isDefault > 0 && address.getDefaults()==1){
            throw new BusinessException("请勿设置多个默认地址");
        }
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
    @Override
    public int updateAddress(Address address) {
        Address query = new Address();
        query.setMemberIdP(address.getMemberIdP());
        List<Address> old = addressMapper.selectAddressList(query);
        if (CollectionUtils.isEmpty(old)){
            return addressMapper.updateAddress(address);
        }
        long isDefault = old.stream().filter(item->item.getDefaults() == 1 && !item.getAddressId().equals(address.getAddressId())).count();
        if (isDefault > 0 && address.getDefaults()==1){
            throw new BusinessException("请勿设置多个默认地址");
        }
        return addressMapper.updateAddress(address);
    }

    /**
     * 删除收货地址对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAddressByIds(String ids) {
        return addressMapper.deleteAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 获取用户默认地址
     */
    @Override
    public Address getDefaults(Long userId){
        return addressMapper.getDefaults(userId);
    }

}
