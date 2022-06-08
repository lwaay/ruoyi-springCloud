package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址 数据层
 *
 * @author sinonc
 * @date 2019-07-26
 */
@Mapper
public interface AddressMapper {
	/**
     * 查询收货地址信息
     *
     * @param addressId 收货地址ID
     * @return 收货地址信息
     */
	public Address selectAddressById(Long addressId);

	/**
     * 查询收货地址列表
     *
     * @param address 收货地址信息
     * @return 收货地址集合
     */
	public List<Address> selectAddressList(Address address);

	/**
     * 新增收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
	public int insertAddress(Address address);

	/**
     * 修改收货地址
     *
     * @param address 收货地址信息
     * @return 结果
     */
	public int updateAddress(Address address);

	/**
     * 删除收货地址
     *
     * @param addressId 收货地址ID
     * @return 结果
     */
	public int deleteAddressById(Long addressId);

	/**
     * 批量删除收货地址
     *
     * @param addressIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAddressByIds(String[] addressIds);


	/**
	 * 获取用户默认地址
	 */
	public Address getDefaults(@Param("memberId") Long userId);
}
