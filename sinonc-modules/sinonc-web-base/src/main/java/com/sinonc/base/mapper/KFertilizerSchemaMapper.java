package com.sinonc.base.mapper;

import java.util.List;

import com.sinonc.base.domain.KFertilizerSchema;

/**
 * 施肥方案标准Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface KFertilizerSchemaMapper {
    /**
     * 查询施肥方案标准
     *
     * @param id 施肥方案标准ID
     * @return 施肥方案标准
     */
    public KFertilizerSchema selectKFertilizerSchemaById(Long id);

    /**
     * 查询施肥方案标准列表
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 施肥方案标准集合
     */
    public List<KFertilizerSchema> selectKFertilizerSchemaList(KFertilizerSchema kFertilizerSchema);

    /**
     * 新增施肥方案标准
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 结果
     */
    public int insertKFertilizerSchema(KFertilizerSchema kFertilizerSchema);

    /**
     * 修改施肥方案标准
     *
     * @param kFertilizerSchema 施肥方案标准
     * @return 结果
     */
    public int updateKFertilizerSchema(KFertilizerSchema kFertilizerSchema);

    /**
     * 删除施肥方案标准
     *
     * @param id 施肥方案标准ID
     * @return 结果
     */
    public int deleteKFertilizerSchemaById(Long id);

    /**
     * 批量删除施肥方案标准
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKFertilizerSchemaByIds(Long[] ids);
}
