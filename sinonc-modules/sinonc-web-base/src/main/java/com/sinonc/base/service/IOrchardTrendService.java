package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.OrchardTrend;

/**
 * 果园动态Service接口
 *
 * @author ruoyi
 * @date 2022-04-27
 */
public interface IOrchardTrendService {
    /**
     * 查询果园动态
     *
     * @param id 果园动态ID
     * @return 果园动态
     */
    public OrchardTrend selectOrchardTrendById(Long id);

    /**
     * 查询果园动态列表
     *
     * @param orchardTrend 果园动态
     * @return 果园动态集合
     */
    public List<OrchardTrend> selectOrchardTrendList(OrchardTrend orchardTrend);

    /**
     * 新增果园动态
     *
     * @param orchardTrend 果园动态
     * @return 结果
     */
    public int insertOrchardTrend(OrchardTrend orchardTrend);

    /**
     * 修改果园动态
     *
     * @param orchardTrend 果园动态
     * @return 结果
     */
    public int updateOrchardTrend(OrchardTrend orchardTrend);

    /**
     * 批量删除果园动态
     *
     * @param ids 需要删除的果园动态ID
     * @return 结果
     */
    public int deleteOrchardTrendByIds(Long[] ids);

    /**
     * 删除果园动态信息
     *
     * @param id 果园动态ID
     * @return 结果
     */
    public int deleteOrchardTrendById(Long id);
}
