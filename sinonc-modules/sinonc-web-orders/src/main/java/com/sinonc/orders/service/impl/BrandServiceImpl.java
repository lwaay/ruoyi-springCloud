package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.Brand;
import com.sinonc.orders.mapper.BrandMapper;
import com.sinonc.orders.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service("brand")
public class BrandServiceImpl implements BrandService {
	@Autowired
	private BrandMapper brandMapper;

	/**
     * 查询商品品牌信息
	 *
	 * @param brandId 商品品牌ID
     * @return 商品品牌信息
     */
    @Override
	public Brand getBrandById(Long brandId) {
	    return brandMapper.selectBrandById(brandId);
	}

	/**
     * 查询商品品牌列表
	 *
	 * @param brand 商品品牌信息
     * @return 商品品牌集合
     */
	@Override
	public List<Brand> listBrand(Brand brand) {
	    return brandMapper.selectBrandList(brand);
	}

    /**
     * 新增商品品牌
	 *
	 * @param brand 商品品牌信息
     * @return 结果
     */
	@Override
	public int addBrand(Brand brand) {
	    return brandMapper.insertBrand(brand);
	}

	/**
     * 修改商品品牌
	 *
	 * @param brand 商品品牌信息
     * @return 结果
     */
	@Override
	public int updateBrand(Brand brand) {
	    return brandMapper.updateBrand(brand);
	}

	/**
     * 删除商品品牌对象
	 *
	 * @return 结果
     */
	@Override
	public int deleteBrandByIds(String ids) {
		return brandMapper.deleteBrandByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询品牌下拉显示
	 * @return
	 */
	@Override
	public List<Brand> listSelectBrand() {
		return brandMapper.listSelectBrand();
	}

}
