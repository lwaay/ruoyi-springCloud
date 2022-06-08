package com.sinonc.base.service;

import com.sinonc.base.api.domain.BaseFarm;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 基地信息Service接口
 *
 * @author ruoyi
 * @date 2020-09-25
 */
public interface IBaseFarmService {
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
     * 批量删除基地信息
     *
     * @param farmIds 需要删除的基地信息ID
     * @return 结果
     */
    public int deleteBaseFarmByIds(Long[] farmIds);

    /**
     * 删除基地信息信息
     *
     * @param farmId 基地信息ID
     * @return 结果
     */
    public int deleteBaseFarmById(Long farmId);

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

    List<String> getFarmNameList();

    /**
     * 根据会员ID查询基地列表
     *
     * @param memberId
     * @return
     */
    List<BaseFarm> getFarmListByMemberId(Long memberId);

    /**
     * 根据系统用户ID查询基地列表
     *
     * @return
     */
    List<BaseFarm> getFarmListBySys();

    /**
     * 新增基地
     *
     * @param baseFarm
     * @return
     */
    int addBaseFarm(BaseFarm baseFarm);
}
