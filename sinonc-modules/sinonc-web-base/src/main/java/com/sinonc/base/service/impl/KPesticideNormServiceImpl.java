package com.sinonc.base.service.impl;

import java.util.List;
                                                                                            import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KPesticideNormMapper;
import com.sinonc.base.domain.KPesticideNorm;
import com.sinonc.base.service.IKPesticideNormService;

/**
 * 病虫害施药标准Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KPesticideNormServiceImpl implements IKPesticideNormService {
    @Autowired
    private KPesticideNormMapper kPesticideNormMapper;

    /**
     * 查询病虫害施药标准
     *
     * @param id 病虫害施药标准ID
     * @return 病虫害施药标准
     */
    @Override
    public KPesticideNorm selectKPesticideNormById(Long id) {
        return kPesticideNormMapper.selectKPesticideNormById(id);
    }

    /**
     * 查询病虫害施药标准列表
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 病虫害施药标准
     */
    @Override
    public List<KPesticideNorm> selectKPesticideNormList(KPesticideNorm kPesticideNorm) {
        return kPesticideNormMapper.selectKPesticideNormList(kPesticideNorm);
    }

    /**
     * 新增病虫害施药标准
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 结果
     */
    @Override
    public int insertKPesticideNorm(KPesticideNorm kPesticideNorm) {
                                                                                                                                                            kPesticideNorm.setCreateTime(DateUtils.getNowDate());
                                                return kPesticideNormMapper.insertKPesticideNorm(kPesticideNorm);
    }

    /**
     * 修改病虫害施药标准
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 结果
     */
    @Override
    public int updateKPesticideNorm(KPesticideNorm kPesticideNorm) {
                                                                                                                                                                                return kPesticideNormMapper.updateKPesticideNorm(kPesticideNorm);
    }

    /**
     * 批量删除病虫害施药标准
     *
     * @param ids 需要删除的病虫害施药标准ID
     * @return 结果
     */
    @Override
    public int deleteKPesticideNormByIds(Long[] ids) {
        return kPesticideNormMapper.deleteKPesticideNormByIds(ids);
    }

    /**
     * 删除病虫害施药标准信息
     *
     * @param id 病虫害施药标准ID
     * @return 结果
     */
    @Override
    public int deleteKPesticideNormById(Long id) {
        return kPesticideNormMapper.deleteKPesticideNormById(id);
    }
}
