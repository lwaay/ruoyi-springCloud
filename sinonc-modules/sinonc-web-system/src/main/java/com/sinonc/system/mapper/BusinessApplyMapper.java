package com.sinonc.system.mapper;

import com.sinonc.system.domain.BusinessApply;

import java.util.List;

/**
 * 主体类型申请Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-01
 */
public interface BusinessApplyMapper {
    /**
     * 查询主体类型申请
     *
     * @param applyId 主体类型申请ID
     * @return 主体类型申请
     */
    public BusinessApply selectBusinessApplyById(Long applyId);

    /**
     * 查询主体类型申请列表
     *
     * @param businessApply 主体类型申请
     * @return 主体类型申请集合
     */
    public List<BusinessApply> selectBusinessApplyList(BusinessApply businessApply);

    /**
     * 新增主体类型申请
     *
     * @param businessApply 主体类型申请
     * @return 结果
     */
    public int insertBusinessApply(BusinessApply businessApply);

    /**
     * 修改主体类型申请
     *
     * @param businessApply 主体类型申请
     * @return 结果
     */
    public int updateBusinessApply(BusinessApply businessApply);

    /**
     * 删除主体类型申请
     *
     * @param applyId 主体类型申请ID
     * @return 结果
     */
    public int deleteBusinessApplyById(Long applyId);

    /**
     * 批量删除主体类型申请
     *
     * @param applyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessApplyByIds(Long[] applyIds);
}
