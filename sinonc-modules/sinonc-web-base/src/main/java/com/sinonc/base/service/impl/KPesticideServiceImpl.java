package com.sinonc.base.service.impl;

import java.util.List;
                                                                                            import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KPesticideMapper;
import com.sinonc.base.domain.KPesticide;
import com.sinonc.base.service.IKPesticideService;

/**
 * 药剂Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KPesticideServiceImpl implements IKPesticideService {
    @Autowired
    private KPesticideMapper kPesticideMapper;

    /**
     * 查询药剂
     *
     * @param id 药剂ID
     * @return 药剂
     */
    @Override
    public KPesticide selectKPesticideById(Long id) {
        return kPesticideMapper.selectKPesticideById(id);
    }

    /**
     * 查询药剂列表
     *
     * @param kPesticide 药剂
     * @return 药剂
     */
    @Override
    public List<KPesticide> selectKPesticideList(KPesticide kPesticide) {
        return kPesticideMapper.selectKPesticideList(kPesticide);
    }

    /**
     * 新增药剂
     *
     * @param kPesticide 药剂
     * @return 结果
     */
    @Override
    public int insertKPesticide(KPesticide kPesticide) {
                                                                                                                                                            kPesticide.setCreateTime(DateUtils.getNowDate());
                                                return kPesticideMapper.insertKPesticide(kPesticide);
    }

    /**
     * 修改药剂
     *
     * @param kPesticide 药剂
     * @return 结果
     */
    @Override
    public int updateKPesticide(KPesticide kPesticide) {
                                                                                                                                                                                return kPesticideMapper.updateKPesticide(kPesticide);
    }

    /**
     * 批量删除药剂
     *
     * @param ids 需要删除的药剂ID
     * @return 结果
     */
    @Override
    public int deleteKPesticideByIds(Long[] ids) {
        return kPesticideMapper.deleteKPesticideByIds(ids);
    }

    /**
     * 删除药剂信息
     *
     * @param id 药剂ID
     * @return 结果
     */
    @Override
    public int deleteKPesticideById(Long id) {
        return kPesticideMapper.deleteKPesticideById(id);
    }
}
