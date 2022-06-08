package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.AccpetScore;

/**
 * 专家评分Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-15
 */
public interface AccpetScoreMapper 
{
    /**
     * 查询专家评分
     * 
     * @param id 专家评分ID
     * @return 专家评分
     */
    public AccpetScore selectAccpetScoreById(Long id);

    /**
     * 查询专家评分列表
     * 
     * @param accpetScore 专家评分
     * @return 专家评分集合
     */
    public List<AccpetScore> selectAccpetScoreList(AccpetScore accpetScore);

    /**
     * 新增专家评分
     * 
     * @param accpetScore 专家评分
     * @return 结果
     */
    public int insertAccpetScore(AccpetScore accpetScore);

    /**
     * 修改专家评分
     * 
     * @param accpetScore 专家评分
     * @return 结果
     */
    public int updateAccpetScore(AccpetScore accpetScore);

    /**
     * 删除专家评分
     * 
     * @param id 专家评分ID
     * @return 结果
     */
    public int deleteAccpetScoreById(Long id);

    /**
     * 批量删除专家评分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccpetScoreByIds(String[] ids);
}
