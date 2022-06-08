package com.sinonc.origins.mapper;

import com.sinonc.origins.api.domain.ProVisit;

import java.util.List;

/**
 * 朔源访问Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public interface ProVisitMapper {
    /**
     * 查询朔源访问
     *
     * @param visitId 朔源访问ID
     * @return 朔源访问
     */
    public ProVisit selectProVisitById(Long visitId);

    /**
     * 查询朔源访问列表
     *
     * @param proVisit 朔源访问
     * @return 朔源访问集合
     */
    public List<ProVisit> selectProVisitList(ProVisit proVisit);

    /**
     * 新增朔源访问
     *
     * @param proVisit 朔源访问
     * @return 结果
     */
    public int insertProVisit(ProVisit proVisit);

    /**
     * 修改朔源访问
     *
     * @param proVisit 朔源访问
     * @return 结果
     */
    public int updateProVisit(ProVisit proVisit);

    /**
     * 删除朔源访问
     *
     * @param visitId 朔源访问ID
     * @return 结果
     */
    public int deleteProVisitById(Long visitId);

    /**
     * 批量删除朔源访问
     *
     * @param visitIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProVisitByIds(Long[] visitIds);

    Long getVisitCount(Long productId);

    /**
     * 溯源访问数量
     * @return
     */
    Long getAllVisitCount();

    /**查询访问总数
     * @param baseArea
     * @return
     */
    public Long selectVisitCountByCode(String baseArea);
}
