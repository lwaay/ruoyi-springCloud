package com.sinonc.base.service.impl;

import java.util.List;
                                                                                                        import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KInsectPeriodMapper;
import com.sinonc.base.domain.KInsectPeriod;
import com.sinonc.base.service.IKInsectPeriodService;

/**
 * 病虫害出现周期Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KInsectPeriodServiceImpl implements IKInsectPeriodService {
    @Autowired
    private KInsectPeriodMapper kInsectPeriodMapper;

    /**
     * 查询病虫害出现周期
     *
     * @param id 病虫害出现周期ID
     * @return 病虫害出现周期
     */
    @Override
    public KInsectPeriod selectKInsectPeriodById(Long id) {
        return kInsectPeriodMapper.selectKInsectPeriodById(id);
    }

    /**
     * 查询病虫害出现周期列表
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 病虫害出现周期
     */
    @Override
    public List<KInsectPeriod> selectKInsectPeriodList(KInsectPeriod kInsectPeriod) {
        return kInsectPeriodMapper.selectKInsectPeriodList(kInsectPeriod);
    }

    /**
     * 新增病虫害出现周期
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 结果
     */
    @Override
    public int insertKInsectPeriod(KInsectPeriod kInsectPeriod) {
                                                                                                                                                                                kInsectPeriod.setCreateTime(DateUtils.getNowDate());
                                                return kInsectPeriodMapper.insertKInsectPeriod(kInsectPeriod);
    }

    /**
     * 修改病虫害出现周期
     *
     * @param kInsectPeriod 病虫害出现周期
     * @return 结果
     */
    @Override
    public int updateKInsectPeriod(KInsectPeriod kInsectPeriod) {
                                                                                                                                                                                                    return kInsectPeriodMapper.updateKInsectPeriod(kInsectPeriod);
    }

    /**
     * 批量删除病虫害出现周期
     *
     * @param ids 需要删除的病虫害出现周期ID
     * @return 结果
     */
    @Override
    public int deleteKInsectPeriodByIds(Long[] ids) {
        return kInsectPeriodMapper.deleteKInsectPeriodByIds(ids);
    }

    /**
     * 删除病虫害出现周期信息
     *
     * @param id 病虫害出现周期ID
     * @return 结果
     */
    @Override
    public int deleteKInsectPeriodById(Long id) {
        return kInsectPeriodMapper.deleteKInsectPeriodById(id);
    }
}
