package com.sinonc.base.mapper;

import com.sinonc.base.api.domain.AreaCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行政区域Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-23
 */
public interface AreaCodeMapper {
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


    public List<AreaCode> selectAreaCodeListLike(AreaCode areaCode);

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
     * 删除行政区域
     *
     * @param code 行政区域ID
     * @return 结果
     */
    public int deleteAreaCodeById(Long code);

    /**
     * 批量删除行政区域
     *
     * @param codes 需要删除的数据ID
     * @return 结果
     */
    public int deleteAreaCodeByIds(Long[] codes);

    /**
     * 根据子类code,level获取父类信息
     */
    public AreaCode selectParentByCodeLevel(@Param("code") String code,@Param("level") Integer level);
}
