package com.sinonc.origins.service.impl;

import com.sinonc.origins.api.domain.ProVisit;
import com.sinonc.origins.mapper.ProVisitMapper;
import com.sinonc.origins.service.IProVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 朔源访问Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ProVisitServiceImpl implements IProVisitService {
    @Autowired
    private ProVisitMapper proVisitMapper;

    /**
     * 查询朔源访问
     *
     * @param visitId 朔源访问ID
     * @return 朔源访问
     */
    @Override
    public ProVisit selectProVisitById(Long visitId) {
        return proVisitMapper.selectProVisitById(visitId);
    }

    /**
     * 查询朔源访问列表
     *
     * @param proVisit 朔源访问
     * @return 朔源访问
     */
    @Override
    public List<ProVisit> selectProVisitList(ProVisit proVisit) {
        return proVisitMapper.selectProVisitList(proVisit);
    }

    /**
     * 新增朔源访问
     *
     * @param proVisit 朔源访问
     * @return 结果
     */
    @Override
    public int insertProVisit(ProVisit proVisit) {
                                                                                                                                        return proVisitMapper.insertProVisit(proVisit);
    }

    /**
     * 修改朔源访问
     *
     * @param proVisit 朔源访问
     * @return 结果
     */
    @Override
    public int updateProVisit(ProVisit proVisit) {
                                                                                                                                        return proVisitMapper.updateProVisit(proVisit);
    }

    /**
     * 批量删除朔源访问
     *
     * @param visitIds 需要删除的朔源访问ID
     * @return 结果
     */
    @Override
    public int deleteProVisitByIds(Long[] visitIds) {
        return proVisitMapper.deleteProVisitByIds(visitIds);
    }

    /**
     * 删除朔源访问信息
     *
     * @param visitId 朔源访问ID
     * @return 结果
     */
    @Override
    public int deleteProVisitById(Long visitId) {
        return proVisitMapper.deleteProVisitById(visitId);
    }

    @Override
    public Long getVisitCount(Long productId){
        return proVisitMapper.getVisitCount(productId);
    }

    @Override
    public Long getVisitCountByCode(String baseArea) {
        return proVisitMapper.selectVisitCountByCode(baseArea);
    }

}
