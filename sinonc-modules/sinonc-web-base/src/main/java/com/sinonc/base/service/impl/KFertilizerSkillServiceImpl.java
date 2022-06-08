package com.sinonc.base.service.impl;

import java.util.List;
                                                                                            import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KFertilizerSkillMapper;
import com.sinonc.base.domain.KFertilizerSkill;
import com.sinonc.base.service.IKFertilizerSkillService;

/**
 * 施肥技术Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KFertilizerSkillServiceImpl implements IKFertilizerSkillService {
    @Autowired
    private KFertilizerSkillMapper kFertilizerSkillMapper;

    /**
     * 查询施肥技术
     *
     * @param id 施肥技术ID
     * @return 施肥技术
     */
    @Override
    public KFertilizerSkill selectKFertilizerSkillById(Long id) {
        return kFertilizerSkillMapper.selectKFertilizerSkillById(id);
    }

    /**
     * 查询施肥技术列表
     *
     * @param kFertilizerSkill 施肥技术
     * @return 施肥技术
     */
    @Override
    public List<KFertilizerSkill> selectKFertilizerSkillList(KFertilizerSkill kFertilizerSkill) {
        return kFertilizerSkillMapper.selectKFertilizerSkillList(kFertilizerSkill);
    }

    /**
     * 新增施肥技术
     *
     * @param kFertilizerSkill 施肥技术
     * @return 结果
     */
    @Override
    public int insertKFertilizerSkill(KFertilizerSkill kFertilizerSkill) {
                                                                                                                                                            kFertilizerSkill.setCreateTime(DateUtils.getNowDate());
                                                return kFertilizerSkillMapper.insertKFertilizerSkill(kFertilizerSkill);
    }

    /**
     * 修改施肥技术
     *
     * @param kFertilizerSkill 施肥技术
     * @return 结果
     */
    @Override
    public int updateKFertilizerSkill(KFertilizerSkill kFertilizerSkill) {
                                                                                                                                                                                return kFertilizerSkillMapper.updateKFertilizerSkill(kFertilizerSkill);
    }

    /**
     * 批量删除施肥技术
     *
     * @param ids 需要删除的施肥技术ID
     * @return 结果
     */
    @Override
    public int deleteKFertilizerSkillByIds(Long[] ids) {
        return kFertilizerSkillMapper.deleteKFertilizerSkillByIds(ids);
    }

    /**
     * 删除施肥技术信息
     *
     * @param id 施肥技术ID
     * @return 结果
     */
    @Override
    public int deleteKFertilizerSkillById(Long id) {
        return kFertilizerSkillMapper.deleteKFertilizerSkillById(id);
    }
}
