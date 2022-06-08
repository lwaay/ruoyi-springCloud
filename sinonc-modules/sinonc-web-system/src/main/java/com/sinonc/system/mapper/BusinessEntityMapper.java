package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.api.domain.BusinessEntity;

/**
 * 农业经营主体基础信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public interface BusinessEntityMapper {
    /**
     * 查询农业经营主体基础信息
     *
     * @param entityId 农业经营主体基础信息ID
     * @return 农业经营主体基础信息
     */
    public BusinessEntity selectBusinessEntityById(Long entityId);

    /**
     * 查询农业经营主体基础信息列表
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 农业经营主体基础信息集合
     */
    public List<BusinessEntity> selectBusinessEntityList(BusinessEntity businessEntity);

    /**
     * 新增农业经营主体基础信息
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 结果
     */
    public int insertBusinessEntity(BusinessEntity businessEntity);

    /**
     * 修改农业经营主体基础信息
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 结果
     */
    public int updateBusinessEntity(BusinessEntity businessEntity);

    /**
     * 删除农业经营主体基础信息
     *
     * @param entityId 农业经营主体基础信息ID
     * @return 结果
     */
    public int deleteBusinessEntityById(Long entityId);

    /**
     * 批量删除农业经营主体基础信息
     *
     * @param entityIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessEntityByIds(Long[] entityIds);
}
