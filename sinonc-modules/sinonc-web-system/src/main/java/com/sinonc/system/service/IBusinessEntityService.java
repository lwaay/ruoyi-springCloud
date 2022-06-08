package com.sinonc.system.service;

import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.dto.BusinessEntityDto;
import com.sinonc.system.vo.BusinessEntityVo;

import java.util.List;

/**
 * 农业经营主体基础信息Service接口
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public interface IBusinessEntityService {
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
     * 批量删除农业经营主体基础信息
     *
     * @param entityIds 需要删除的农业经营主体基础信息ID
     * @return 结果
     */
    public int deleteBusinessEntityByIds(Long[] entityIds);

    /**
     * 删除农业经营主体基础信息信息
     *
     * @param entityId 农业经营主体基础信息ID
     * @return 结果
     */
    public int deleteBusinessEntityById(Long entityId);

    /**
     * 新增经营主体，及主体类型对应的资料
     * @param businessEntityVo
     * @return
     */
    int addBusinessEntityVo(BusinessEntityVo businessEntityVo);

    /**
     * 根据id获取经营主体及其主体类型
     *
     * @param memberId
     * @return
     */
    BusinessEntityDto getBusinessEntityDtoById(Long memberId, String applyType);

    /**
     * 查询当前会员认证状态列表
     *
     * @param memberId
     * @return
     */
    List<BusinessEntityDto> getAllBusinessEntityByMemberId(Long memberId);

    /**
     * 根据会员ID获取经营主体相关信息
     *
     * @param memberId
     * @return
     */
    BusinessEntityDto getBusinessEntityDtoBymemberId(Long memberId);
}
