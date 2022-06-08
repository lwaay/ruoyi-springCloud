package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.OdMemberVisit;
import com.sinonc.orders.domain.OdMemberVisitDescription;

/**
 * 访问记录Service接口
 *
 * @author ruoyi
 * @date 2022-03-31
 */
public interface IOdMemberVisitService {
    /**
     * 查询访问记录
     *
     * @param id 访问记录ID
     * @return 访问记录
     */
    public OdMemberVisit selectOdMemberVisitById(Long id);

    /**
     * 查询访问记录列表
     *
     * @param odMemberVisit 访问记录
     * @return 访问记录集合
     */
    public List<OdMemberVisit> selectOdMemberVisitList(OdMemberVisit odMemberVisit);

    public List<OdMemberVisitDescription> selectOdMemberVisitDescriptionList(OdMemberVisit odMemberVisit);

    /**
     * 新增访问记录
     *
     * @param odMemberVisit 访问记录
     * @return 结果
     */
    public int insertOdMemberVisit(OdMemberVisit odMemberVisit);

    /**
     * 修改访问记录
     *
     * @param odMemberVisit 访问记录
     * @return 结果
     */
    public int updateOdMemberVisit(OdMemberVisit odMemberVisit);

    /**
     * 批量删除访问记录
     *
     * @param ids 需要删除的访问记录ID
     * @return 结果
     */
    public int deleteOdMemberVisitByIds(Long[] ids);

    /**
     * 删除访问记录信息
     *
     * @param id 访问记录ID
     * @return 结果
     */
    public int deleteOdMemberVisitById(Long id);
}
