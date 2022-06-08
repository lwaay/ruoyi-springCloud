package com.sinonc.base.mapper;

import com.sinonc.base.api.domain.BaseFarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 基地信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public interface BaseFarmMapper {
    /**
     * 查询基地信息
     *
     * @param farmId 基地信息ID
     * @return 基地信息
     */
    public BaseFarm selectBaseFarmById(Long farmId);

    /**
     * 查询基地信息列表
     *
     * @param baseFarm 基地信息
     * @return 基地信息集合
     */
    public List<BaseFarm> selectBaseFarmList(BaseFarm baseFarm);

    /**
     * 新增基地信息
     *
     * @param baseFarm 基地信息
     * @return 结果
     */
    public int insertBaseFarm(BaseFarm baseFarm);

    /**
     * 修改基地信息
     *
     * @param baseFarm 基地信息
     * @return 结果
     */
    public int updateBaseFarm(BaseFarm baseFarm);

    /**
     * 删除基地信息
     *
     * @param farmId 基地信息ID
     * @return 结果
     */
    public int deleteBaseFarmById(Long farmId);

    /**
     * 批量删除基地信息
     *
     * @param farmIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseFarmByIds(Long[] farmIds);

    List<String> getFarmNameList();

    /**
     * 查询基地总数
     *
     * @return
     */
    Integer getFarmCount();

    /**
     * 查询基地总面积
     *
     * @return
     */
    Double getFarmAreaCount();

    /**
     * 根据区县编码获取最大的基地编码+1
     *
     * @param areaCode
     * @return
     */
    Long getMaxAreaCode(@Param("areaCode") String areaCode);

    /**
     * @param baseFarm
     * @return
     */
    List<BaseFarm> selectBaseFarmListByEntityId(BaseFarm baseFarm);
}
