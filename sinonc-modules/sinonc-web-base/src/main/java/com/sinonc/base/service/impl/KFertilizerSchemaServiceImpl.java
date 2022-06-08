package com.sinonc.base.service.impl;

import java.util.List;
                                                                                                        import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KFertilizerSchemaMapper;
import com.sinonc.base.domain.KFertilizerSchema;
import com.sinonc.base.service.IKFertilizerSchemaService;

/**
 * 施肥方案标准Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KFertilizerSchemaServiceImpl implements IKFertilizerSchemaService {
    @Autowired
    private KFertilizerSchemaMapper kFertilizerSchemaMapper;

    /**
     * 查询施肥方案标准
     *
     * @param id 施肥方案标准ID
     * @return 施肥方案标准
     */
    @Override
    public KFertilizerSchema selectKFertilizerSchemaById(Long id) {
        return kFertilizerSchemaMapper.selectKFertilizerSchemaById(id);
    }

    /**
     * 查询施肥方案标准列表
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 施肥方案标准
     */
    @Override
    public List<KFertilizerSchema> selectKFertilizerSchemaList(KFertilizerSchema kFertilizerSchema) {
        return kFertilizerSchemaMapper.selectKFertilizerSchemaList(kFertilizerSchema);
    }

    /**
     * 新增施肥方案标准
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 结果
     */
    @Override
    public int insertKFertilizerSchema(KFertilizerSchema kFertilizerSchema) {
                                                                                                                                                                                kFertilizerSchema.setCreateTime(DateUtils.getNowDate());
                                                return kFertilizerSchemaMapper.insertKFertilizerSchema(kFertilizerSchema);
    }

    /**
     * 修改施肥方案标准
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 结果
     */
    @Override
    public int updateKFertilizerSchema(KFertilizerSchema kFertilizerSchema) {
                                                                                                                                                                                                    return kFertilizerSchemaMapper.updateKFertilizerSchema(kFertilizerSchema);
    }

    /**
     * 批量删除施肥方案标准
     *
     * @param ids 需要删除的施肥方案标准ID
     * @return 结果
     */
    @Override
    public int deleteKFertilizerSchemaByIds(Long[] ids) {
        return kFertilizerSchemaMapper.deleteKFertilizerSchemaByIds(ids);
    }

    /**
     * 删除施肥方案标准信息
     *
     * @param id 施肥方案标准ID
     * @return 结果
     */
    @Override
    public int deleteKFertilizerSchemaById(Long id) {
        return kFertilizerSchemaMapper.deleteKFertilizerSchemaById(id);
    }
}
