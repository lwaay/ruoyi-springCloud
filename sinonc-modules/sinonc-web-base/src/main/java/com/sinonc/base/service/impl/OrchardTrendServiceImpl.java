package com.sinonc.base.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.OrchardTrendMapper;
import com.sinonc.base.domain.OrchardTrend;
import com.sinonc.base.service.IOrchardTrendService;

/**
 * 果园动态Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-27
 */
@Service
public class OrchardTrendServiceImpl implements IOrchardTrendService {
    @Autowired
    private OrchardTrendMapper orchardTrendMapper;

    /**
     * 查询果园动态
     *
     * @param id 果园动态ID
     * @return 果园动态
     */
    @Override
    public OrchardTrend selectOrchardTrendById(Long id) {
        return orchardTrendMapper.selectOrchardTrendById(id);
    }

    /**
     * 查询果园动态列表
     *
     * @param orchardTrend 果园动态
     * @return 果园动态
     */
    @Override
    public List<OrchardTrend> selectOrchardTrendList(OrchardTrend orchardTrend) {
        return orchardTrendMapper.selectOrchardTrendList(orchardTrend);
    }

    /**
     * 新增果园动态
     *
     * @param orchardTrend 果园动态
     * @return 结果
     */
    @Override
    public int insertOrchardTrend(OrchardTrend orchardTrend) {
        orchardTrend.setCreateTime(DateUtils.getNowDate());
        return orchardTrendMapper.insertOrchardTrend(orchardTrend);
    }

    /**
     * 修改果园动态
     *
     * @param orchardTrend 果园动态
     * @return 结果
     */
    @Override
    public int updateOrchardTrend(OrchardTrend orchardTrend) {
        orchardTrend.setUpdateTime(DateUtils.getNowDate());
        return orchardTrendMapper.updateOrchardTrend(orchardTrend);
    }

    /**
     * 批量删除果园动态
     *
     * @param ids 需要删除的果园动态ID
     * @return 结果
     */
    @Override
    public int deleteOrchardTrendByIds(Long[] ids) {
        return orchardTrendMapper.deleteOrchardTrendByIds(ids);
    }

    /**
     * 删除果园动态信息
     *
     * @param id 果园动态ID
     * @return 结果
     */
    @Override
    public int deleteOrchardTrendById(Long id) {
        return orchardTrendMapper.deleteOrchardTrendById(id);
    }
}
