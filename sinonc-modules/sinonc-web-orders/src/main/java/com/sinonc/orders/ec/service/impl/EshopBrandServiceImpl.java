package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.EshopBrand;
import com.sinonc.orders.ec.mapper.EshopBrandMapper;
import com.sinonc.orders.ec.service.IEshopBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大屏自定义品牌Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-31
 */
@Service
public class EshopBrandServiceImpl implements IEshopBrandService {
    @Autowired
    private EshopBrandMapper eshopBrandMapper;

    /**
     * 查询大屏自定义品牌
     *
     * @param brandId 大屏自定义品牌ID
     * @return 大屏自定义品牌
     */
    @Override
    public EshopBrand selectEshopBrandById(Long brandId) {
        return eshopBrandMapper.selectEshopBrandById(brandId);
    }

    /**
     * 查询大屏自定义品牌列表
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 大屏自定义品牌
     */
    @Override
    public List<EshopBrand> selectEshopBrandList(EshopBrand eshopBrand) {
        return eshopBrandMapper.selectEshopBrandList(eshopBrand);
    }

    /**
     * 新增大屏自定义品牌
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 结果
     */
    @Override
    public int insertEshopBrand(EshopBrand eshopBrand) {
        eshopBrand.setCreateTime(DateUtils.getNowDate());
        return eshopBrandMapper.insertEshopBrand(eshopBrand);
    }

    /**
     * 修改大屏自定义品牌
     *
     * @param eshopBrand 大屏自定义品牌
     * @return 结果
     */
    @Override
    public int updateEshopBrand(EshopBrand eshopBrand) {
        return eshopBrandMapper.updateEshopBrand(eshopBrand);
    }

    /**
     * 批量删除大屏自定义品牌
     *
     * @param brandIds 需要删除的大屏自定义品牌ID
     * @return 结果
     */
    @Override
    public int deleteEshopBrandByIds(Long[] brandIds) {
        return eshopBrandMapper.deleteEshopBrandByIds(brandIds);
    }

    /**
     * 删除大屏自定义品牌信息
     *
     * @param brandId 大屏自定义品牌ID
     * @return 结果
     */
    @Override
    public int deleteEshopBrandById(Long brandId) {
        return eshopBrandMapper.deleteEshopBrandById(brandId);
    }

    /**
     * 查询默认品牌
     * @return
     */
    @Override
    public List<EshopBrand> selectListDefault(){
        return eshopBrandMapper.selectListDefault();
    }
}
