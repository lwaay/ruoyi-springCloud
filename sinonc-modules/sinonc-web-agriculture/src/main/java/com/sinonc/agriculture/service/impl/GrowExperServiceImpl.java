package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.domain.GrowExper;
import com.sinonc.agriculture.mapper.GrowExperMapper;
import com.sinonc.agriculture.service.GrowExperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员种植经验Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-01
 */
@Service
public class GrowExperServiceImpl implements GrowExperService {
    @Autowired
    private GrowExperMapper growExperMapper;

    /**
     * 查询会员种植经验
     *
     * @param growexperId 会员种植经验ID
     * @return 会员种植经验
     */
    @Override
    public GrowExper getGrowExperById(Long growexperId) {
        return growExperMapper.selectGrowExperById(growexperId);
    }

    /**
     * 查询会员种植经验列表
     *
     * @param growExper 会员种植经验
     * @return 会员种植经验
     */
    @Override
    public List<GrowExper> getGrowExperList(GrowExper growExper) {
        return growExperMapper.selectGrowExperList(growExper);
    }

    /**
     * 新增会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    @Override
    public int addGrowExper(GrowExper growExper) {
        growExper.setCreateTime(DateUtils.getNowDate());
        return growExperMapper.insertGrowExper(growExper);
    }

    /**
     * 修改会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    @Override
    public int updateGrowExper(GrowExper growExper) {
        growExper.setUpdateTime(DateUtils.getNowDate());
        return growExperMapper.updateGrowExper(growExper);
    }

    /**
     * 删除会员种植经验对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGrowExperByIds(String ids) {
        return growExperMapper.deleteGrowExperByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员种植经验信息
     *
     * @param growexperId 会员种植经验ID
     * @return 结果
     */
    @Override
    public int deleteGrowExperById(Long growexperId) {
        return growExperMapper.deleteGrowExperById(growexperId);
    }
}
