package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.KInsectPeriod;

/**
 * 病虫害出现周期Service接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IKInsectPeriodService {
    /**
     * 查询病虫害出现周期
     *
     * @param id 病虫害出现周期ID
     * @return 病虫害出现周期
     */
    public KInsectPeriod selectKInsectPeriodById(Long id);

    /**
     * 查询病虫害出现周期列表
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 病虫害出现周期集合
     */
    public List<KInsectPeriod> selectKInsectPeriodList(KInsectPeriod kInsectPeriod);

    /**
     * 新增病虫害出现周期
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 结果
     */
    public int insertKInsectPeriod(KInsectPeriod kInsectPeriod);

    /**
     * 修改病虫害出现周期
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 结果
     */
    public int updateKInsectPeriod(KInsectPeriod kInsectPeriod);

    /**
     * 批量删除病虫害出现周期
     *
     * @param ids 需要删除的病虫害出现周期ID
     * @return 结果
     */
    public int deleteKInsectPeriodByIds(Long[] ids);

    /**
     * 删除病虫害出现周期信息
     *
     * @param id 病虫害出现周期ID
     * @return 结果
     */
    public int deleteKInsectPeriodById(Long id);
}
