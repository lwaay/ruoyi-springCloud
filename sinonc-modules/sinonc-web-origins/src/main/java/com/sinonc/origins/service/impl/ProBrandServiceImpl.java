package com.sinonc.origins.service.impl;

import com.sinonc.origins.domain.ProBrand;
import com.sinonc.origins.mapper.ProBrandMapper;
import com.sinonc.origins.service.IProBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品品牌Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-23
 */
@Service
public class ProBrandServiceImpl implements IProBrandService {
    @Autowired
    private ProBrandMapper proBrandMapper;

    /**
     * 查询商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌
     */
    @Override
    public ProBrand selectProBrandById(Long brandId) {
        return proBrandMapper.selectProBrandById(brandId);
    }

    /**
     * 查询商品品牌列表
     *
     * @param proBrand 商品品牌
     * @return 商品品牌
     */
    @Override
    public List<ProBrand> selectProBrandList(ProBrand proBrand) {
        return proBrandMapper.selectProBrandList(proBrand);
    }

    /**
     * 新增商品品牌
     *
     * @param proBrand 商品品牌
     * @return 结果
     */
    @Override
    public int insertProBrand(ProBrand proBrand) {
        proBrand.setCreateTime(new Date());
        return proBrandMapper.insertProBrand(proBrand);
    }

    /**
     * 修改商品品牌
     *
     * @param proBrand 商品品牌
     * @return 结果
     */
    @Override
    public int updateProBrand(ProBrand proBrand) {
        proBrand.setUpdateTime(new Date());
        return proBrandMapper.updateProBrand(proBrand);
    }

    /**
     * 批量删除商品品牌
     *
     * @param brandIds 需要删除的商品品牌ID
     * @return 结果
     */
    @Override
    public int deleteProBrandByIds(Long[] brandIds) {
        return proBrandMapper.deleteProBrandByIds(brandIds);
    }

    /**
     * 删除商品品牌信息
     *
     * @param brandId 商品品牌ID
     * @return 结果
     */
    @Override
    public int deleteProBrandById(Long brandId) {
        return proBrandMapper.deleteProBrandById(brandId);
    }
}
