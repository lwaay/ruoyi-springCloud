package com.sinonc.orders.service;

import com.sinonc.orders.domain.Brand;

import java.util.List;

/**
 * 商品品牌 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface BrandService {

	/**
     * 查询商品品牌信息
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌信息
     */
	public Brand getBrandById(Long brandId);

	/**
     * 查询商品品牌列表
     *
     * @param brand 商品品牌信息
     * @return 商品品牌集合
     */
	public List<Brand> listBrand(Brand brand);

	/**
     * 新增商品品牌
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
	public int addBrand(Brand brand);

	/**
     * 修改商品品牌
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
	public int updateBrand(Brand brand);

	/**
     * 删除商品品牌信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBrandByIds(String ids);

	/**
	 * 查询商品品牌下拉框显示
	 * @return
	 */
	public List<Brand> listSelectBrand();
}
