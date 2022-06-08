package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.KFertilizerSkill;

/**
 * 施肥技术Service接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface IKFertilizerSkillService {
    /**
     * 查询施肥技术
     *
     * @param id 施肥技术ID
     * @return 施肥技术
     */
    public KFertilizerSkill selectKFertilizerSkillById(Long id);

    /**
     * 查询施肥技术列表
     *
     * @param kFertilizerSkill 施肥技术
     * @return 施肥技术集合
     */
    public List<KFertilizerSkill> selectKFertilizerSkillList(KFertilizerSkill kFertilizerSkill);

    /**
     * 新增施肥技术
     *
     * @param kFertilizerSkill 施肥技术
     * @return 结果
     */
    public int insertKFertilizerSkill(KFertilizerSkill kFertilizerSkill);

    /**
     * 修改施肥技术
     *
     * @param kFertilizerSkill 施肥技术
     * @return 结果
     */
    public int updateKFertilizerSkill(KFertilizerSkill kFertilizerSkill);

    /**
     * 批量删除施肥技术
     *
     * @param ids 需要删除的施肥技术ID
     * @return 结果
     */
    public int deleteKFertilizerSkillByIds(Long[] ids);

    /**
     * 删除施肥技术信息
     *
     * @param id 施肥技术ID
     * @return 结果
     */
    public int deleteKFertilizerSkillById(Long id);
}
