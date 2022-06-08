package com.sinonc.base.mapper;

import java.util.List;

import com.sinonc.base.domain.KPesticide;

/**
 * 药剂Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface KPesticideMapper {
    /**
     * 查询药剂
     *
     * @param id 药剂ID
     * @return 药剂
     */
    public KPesticide selectKPesticideById(Long id);

    /**
     * 查询药剂列表
     *
     * @param kPesticide 药剂
     * @return 药剂集合
     */
    public List<KPesticide> selectKPesticideList(KPesticide kPesticide);

    /**
     * 新增药剂
     *
     * @param kPesticide 药剂
     * @return 结果
     */
    public int insertKPesticide(KPesticide kPesticide);

    /**
     * 修改药剂
     *
     * @param kPesticide 药剂
     * @return 结果
     */
    public int updateKPesticide(KPesticide kPesticide);

    /**
     * 删除药剂
     *
     * @param id 药剂ID
     * @return 结果
     */
    public int deleteKPesticideById(Long id);

    /**
     * 批量删除药剂
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKPesticideByIds(Long[] ids);
}
