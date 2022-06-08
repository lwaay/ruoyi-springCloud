package com.sinonc.orders.service;

import com.sinonc.orders.domain.Address;

import java.util.List;

/**
 * 收货地址 服务层
 *
 * @author sinonc
 * @date 2019-07-26
 */
public interface AddressService {

	/**
     * 查询收货地址信息
     *
     * @param addressId 收货地址ID
     * @return 收货地址信息
     */
	public Address getAddressById(Long addressId);

	/**
     * 查询收货地址列表
     *
     * @param address 收货地址信息
     * @return 收货地址集合
     */
	public List<Address> listAddress(Address address);

	/**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
	public int addAddress(Address address);

	/**
     * 修改收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
	public int updateAddress(Address address);

	/**
     * 删除收货地址信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAddressByIds(String ids);

	/**
	 * 获取用户默认地址
	 */
	public Address getDefaults(Long userId);

}
