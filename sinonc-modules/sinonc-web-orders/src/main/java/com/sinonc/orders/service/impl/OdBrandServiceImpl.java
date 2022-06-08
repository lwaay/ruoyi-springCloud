package com.sinonc.orders.service.impl;

import java.util.List;
                                                                                import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdBrandMapper;
import com.sinonc.orders.domain.OdBrand;
import com.sinonc.orders.service.IOdBrandService;

/**
 * 商品品牌Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@Service
public class OdBrandServiceImpl implements IOdBrandService {
    @Autowired
    private OdBrandMapper odBrandMapper;

    /**
     * 查询商品品牌
     *
     * @param brandId 商品品牌ID
     * @return 商品品牌
     */
    @Override
    public OdBrand selectOdBrandById(Long brandId) {
        return odBrandMapper.selectOdBrandById(brandId);
    }

    /**
     * 查询商品品牌列表
     *
     * @param odBrand 商品品牌
     * @return 商品品牌
     */
    @Override
    public List<OdBrand> selectOdBrandList(OdBrand odBrand) {
        return odBrandMapper.selectOdBrandList(odBrand);
    }

    /**
     * 新增商品品牌
     *
     * @param odBrand 商品品牌
     * @return 结果
     */
    @Override
    public int insertOdBrand(OdBrand odBrand) {
                                                                                                                                        odBrand.setCreateTime(DateUtils.getNowDate());
                                                return odBrandMapper.insertOdBrand(odBrand);
    }

    /**
     * 修改商品品牌
     *
     * @param odBrand 商品品牌
     * @return 结果
     */
    @Override
    public int updateOdBrand(OdBrand odBrand) {
                                                                                                                                                            odBrand.setUpdateTime(DateUtils.getNowDate());
                            return odBrandMapper.updateOdBrand(odBrand);
    }

    /**
     * 批量删除商品品牌
     *
     * @param brandIds 需要删除的商品品牌ID
     * @return 结果
     */
    @Override
    public int deleteOdBrandByIds(Long[] brandIds) {
        return odBrandMapper.deleteOdBrandByIds(brandIds);
    }

    /**
     * 删除商品品牌信息
     *
     * @param brandId 商品品牌ID
     * @return 结果
     */
    @Override
    public int deleteOdBrandById(Long brandId) {
        return odBrandMapper.deleteOdBrandById(brandId);
    }
}
