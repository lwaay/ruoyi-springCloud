package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.GrowExper;

import java.util.List;

/**
 * 会员种植经验Service接口
 *
 * @author ruoyi
 * @date 2020-04-01
 */
public interface GrowExperService {
    /**
     * 查询会员种植经验
     *
     * @param growexperId 会员种植经验ID
     * @return 会员种植经验
     */
    public GrowExper getGrowExperById(Long growexperId);

    /**
     * 查询会员种植经验列表
     *
     * @param growExper 会员种植经验
     * @return 会员种植经验集合
     */
    public List<GrowExper> getGrowExperList(GrowExper growExper);

    /**
     * 新增会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    public int addGrowExper(GrowExper growExper);

    /**
     * 修改会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    public int updateGrowExper(GrowExper growExper);

    /**
     * 批量删除会员种植经验
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowExperByIds(String ids);

    /**
     * 删除会员种植经验信息
     *
     * @param growexperId 会员种植经验ID
     * @return 结果
     */
    public int deleteGrowExperById(Long growexperId);
}
