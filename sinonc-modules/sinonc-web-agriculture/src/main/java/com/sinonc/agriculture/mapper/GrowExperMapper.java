package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.GrowExper;

import java.util.List;

/**
 * 会员种植经验Mapper接口
 *
 * @author ruoyi
 * @date 2020-04-01
 */
public interface GrowExperMapper {
    /**
     * 查询会员种植经验
     *
     * @param growexperId 会员种植经验ID
     * @return 会员种植经验
     */
    public GrowExper selectGrowExperById(Long growexperId);

    /**
     * 查询会员种植经验列表
     *
     * @param growExper 会员种植经验
     * @return 会员种植经验集合
     */
    public List<GrowExper> selectGrowExperList(GrowExper growExper);

    /**
     * 新增会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    public int insertGrowExper(GrowExper growExper);

    /**
     * 修改会员种植经验
     *
     * @param growExper 会员种植经验
     * @return 结果
     */
    public int updateGrowExper(GrowExper growExper);

    /**
     * 删除会员种植经验
     *
     * @param growexperId 会员种植经验ID
     * @return 结果
     */
    public int deleteGrowExperById(Long growexperId);

    /**
     * 批量删除会员种植经验
     *
     * @param growexperIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowExperByIds(String[] growexperIds);

    /**
     * 根据会员ID删除种植信息
     *
     * @param memberId
     * @return
     */
    public int deleteGrowExperByMemberId(Long memberId);
}
