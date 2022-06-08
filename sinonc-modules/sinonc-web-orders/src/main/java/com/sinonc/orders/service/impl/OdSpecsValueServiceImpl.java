package com.sinonc.orders.service.impl;

import java.util.List;
                                                                    import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdSpecsValueMapper;
import com.sinonc.orders.domain.OdSpecsValue;
import com.sinonc.orders.service.IOdSpecsValueService;

/**
 * 规格属性Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-25
 */
@Service
public class OdSpecsValueServiceImpl implements IOdSpecsValueService {
    @Autowired
    private OdSpecsValueMapper odSpecsValueMapper;

    /**
     * 查询规格属性
     *
     * @param specsValueId 规格属性ID
     * @return 规格属性
     */
    @Override
    public OdSpecsValue selectOdSpecsValueById(Long specsValueId) {
        return odSpecsValueMapper.selectOdSpecsValueById(specsValueId);
    }

    /**
     * 查询规格属性列表
     *
     * @param odSpecsValue 规格属性
     * @return 规格属性
     */
    @Override
    public List<OdSpecsValue> selectOdSpecsValueList(OdSpecsValue odSpecsValue) {
        return odSpecsValueMapper.selectOdSpecsValueList(odSpecsValue);
    }

    /**
     * 新增规格属性
     *
     * @param odSpecsValue 规格属性
     * @return 结果
     */
    @Override
    public int insertOdSpecsValue(OdSpecsValue odSpecsValue) {
                                                                                                                    odSpecsValue.setCreateTime(DateUtils.getNowDate());
                                                                                                                                return odSpecsValueMapper.insertOdSpecsValue(odSpecsValue);
    }

    /**
     * 修改规格属性
     *
     * @param odSpecsValue 规格属性
     * @return 结果
     */
    @Override
    public int updateOdSpecsValue(OdSpecsValue odSpecsValue) {
                                                                                                                                        odSpecsValue.setUpdateTime(DateUtils.getNowDate());
                                                                                                            return odSpecsValueMapper.updateOdSpecsValue(odSpecsValue);
    }

    /**
     * 批量删除规格属性
     *
     * @param specsValueIds 需要删除的规格属性ID
     * @return 结果
     */
    @Override
    public int deleteOdSpecsValueByIds(Long[] specsValueIds) {
        return odSpecsValueMapper.deleteOdSpecsValueByIds(specsValueIds);
    }

    /**
     * 删除规格属性信息
     *
     * @param specsValueId 规格属性ID
     * @return 结果
     */
    @Override
    public int deleteOdSpecsValueById(Long specsValueId) {
        return odSpecsValueMapper.deleteOdSpecsValueById(specsValueId);
    }
}
