package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.KPesticideNorm;

/**
 * 病虫害施药标准Service接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IKPesticideNormService {
    /**
     * 查询病虫害施药标准
     *
     * @param id 病虫害施药标准ID
     * @return 病虫害施药标准
     */
    public KPesticideNorm selectKPesticideNormById(Long id);

    /**
     * 查询病虫害施药标准列表
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 病虫害施药标准集合
     */
    public List<KPesticideNorm> selectKPesticideNormList(KPesticideNorm kPesticideNorm);

    /**
     * 新增病虫害施药标准
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 结果
     */
    public int insertKPesticideNorm(KPesticideNorm kPesticideNorm);

    /**
     * 修改病虫害施药标准
     *
     * @param kPesticideNorm 病虫害施药标准
     * @return 结果
     */
    public int updateKPesticideNorm(KPesticideNorm kPesticideNorm);

    /**
     * 批量删除病虫害施药标准
     *
     * @param ids 需要删除的病虫害施药标准ID
     * @return 结果
     */
    public int deleteKPesticideNormByIds(Long[] ids);

    /**
     * 删除病虫害施药标准信息
     *
     * @param id 病虫害施药标准ID
     * @return 结果
     */
    public int deleteKPesticideNormById(Long id);
}
