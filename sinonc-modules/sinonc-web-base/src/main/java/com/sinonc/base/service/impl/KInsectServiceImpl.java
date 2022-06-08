package com.sinonc.base.service.impl;

import java.util.List;
                                                                                                        import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.KInsectMapper;
import com.sinonc.base.domain.KInsect;
import com.sinonc.base.service.IKInsectService;

/**
 * 病虫害Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Service
public class KInsectServiceImpl implements IKInsectService {
    @Autowired
    private KInsectMapper kInsectMapper;

    /**
     * 查询病虫害
     *
     * @param id 病虫害ID
     * @return 病虫害
     */
    @Override
    public KInsect selectKInsectById(Long id) {
        return kInsectMapper.selectKInsectById(id);
    }

    /**
     * 查询病虫害列表
     *
     * @param kInsect 病虫害
     * @return 病虫害
     */
    @Override
    public List<KInsect> selectKInsectList(KInsect kInsect) {
        return kInsectMapper.selectKInsectList(kInsect);
    }

    /**
     * 新增病虫害
     *
     * @param kInsect 病虫害
     * @return 结果
     */
    @Override
    public int insertKInsect(KInsect kInsect) {
                                                                                                                                                                                kInsect.setCreateTime(DateUtils.getNowDate());
                                                return kInsectMapper.insertKInsect(kInsect);
    }

    /**
     * 修改病虫害
     *
     * @param kInsect 病虫害
     * @return 结果
     */
    @Override
    public int updateKInsect(KInsect kInsect) {
                                                                                                                                                                                                    return kInsectMapper.updateKInsect(kInsect);
    }

    /**
     * 批量删除病虫害
     *
     * @param ids 需要删除的病虫害ID
     * @return 结果
     */
    @Override
    public int deleteKInsectByIds(Long[] ids) {
        return kInsectMapper.deleteKInsectByIds(ids);
    }

    /**
     * 删除病虫害信息
     *
     * @param id 病虫害ID
     * @return 结果
     */
    @Override
    public int deleteKInsectById(Long id) {
        return kInsectMapper.deleteKInsectById(id);
    }
}
