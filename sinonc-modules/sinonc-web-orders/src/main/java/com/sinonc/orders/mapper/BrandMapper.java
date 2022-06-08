package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品品牌 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface BrandMapper {
	/**
     * 查询商品品牌信息
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌信息
     */
	public Brand selectBrandById(Long brandId);

	/**
     * 查询商品品牌列表
     *
     * @param brand 商品品牌信息
     * @return 商品品牌集合
     */
	public List<Brand> selectBrandList(Brand brand);

	/**
     * 新增商品品牌
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
	public int insertBrand(Brand brand);

	/**
     * 修改商品品牌
     *
     * @param brand 商品品牌信息
     * @return 结果
     */
	public int updateBrand(Brand brand);

	/**
     * 删除商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 结果
     */
	public int deleteBrandById(Long brandId);

	/**
     * 批量删除商品品牌
     *
     * @param brandIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBrandByIds(String[] brandIds);

	/**
	 * 查询品牌下拉框显示
	 * @return
	 */
	List<Brand> listSelectBrand();
}
