package com.sinonc.base.service;

import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.vo.AreaCodeTreeSelect;

import java.util.List;

/**
 * 行政区域Service接口
 *
 * @author ruoyi
 * @date 2020-09-23
 */
public interface IAreaCodeService {
    /**
     * 查询行政区域
     *
     * @param code 行政区域ID
     * @return 行政区域
     */
    public AreaCode selectAreaCodeById(Long code);

    /**
     * 查询行政区域列表
     *
     * @param areaCode 行政区域
     * @return 行政区域集合
     */
    public List<AreaCode> selectAreaCodeList(AreaCode areaCode);

    /**
     * 新增行政区域
     *
     * @param areaCode 行政区域
     * @return 结果
     */
    public int insertAreaCode(AreaCode areaCode);

    /**
     * 修改行政区域
     *
     * @param areaCode 行政区域
     * @return 结果
     */
    public int updateAreaCode(AreaCode areaCode);

    /**
     * 批量删除行政区域
     *
     * @param codes 需要删除的行政区域ID
     * @return 结果
     */
    public int deleteAreaCodeByIds(Long[] codes);

    /**
     * 删除行政区域信息
     *
     * @param code 行政区域ID
     * @return 结果
     */
    public int deleteAreaCodeById(Long code);


    /**
     * 根据父行政区域代码，获取子行政区域list
     *
     * @param parentCode
     * @return
     */
    public List<AreaCode> listAreaCodeByParentCode(Long parentCode);

    List<AreaCodeTreeSelect> buildDeptTreeSelect(List<AreaCode> areaCodeList);


    List<AreaCode> selectAreaCodeListLike(AreaCode areaCode);

    /**
     * 根据地址Code获取对应的名称
     *
     * @param addressCode
     * @return
     */
    String changeAddressName(String addressCode);

    /**
     * 根据level获取行政区划
     */
    List<AreaCode> parentBaseArea(Integer level,String code);
}
