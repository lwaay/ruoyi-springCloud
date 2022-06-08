package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.dto.ExperInfoDto;
import com.sinonc.agriculture.vo.ExpertInfoVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 专家消息Service接口
 *
 * @author ruoyi
 * @date 2020-03-05
 */
public interface ExpertInfoService
{
    /**
     * 查询专家消息
     *
     * @param expertId 专家消息ID
     * @return 专家消息
     */
    public ExpertInfo selectExpertInfoById(Long expertId);

    /**
     * 查询专家消息列表
     *
     * @param expertInfo 专家消息
     * @return 专家消息集合
     */
    public List<ExpertInfo> selectExpertInfoList(ExpertInfo expertInfo);

    /**
     * 新增专家消息
     *
     * @param expertInfo 专家消息
     * @return 结果
     */
    public int insertExpertInfo(ExpertInfo expertInfo);

    /**
     * 修改专家消息
     *
     * @param expertInfo 专家消息
     * @return 结果
     */
    public int updateExpertInfo(ExpertInfo expertInfo);

    public int auditExpertInfo(ExpertInfo expertInfo);

    /**
     * 批量删除专家消息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertInfoByIds(String ids);

    /**
     * 删除专家消息信息
     *
     * @param expertId 专家消息ID
     * @return 结果
     */
    public int deleteExpertInfoById(Long expertId);

    /**
     * 查询专家列表返回list<String,Map>
     * @param expertInfo
     * @return
     */
    List<Map<String, Object>> selectExpertInfoListMap(ExpertInfo expertInfo);

    /**
     * 根据专家id查询专家
     * @param expertId
     * @return
     */
    Map<String, Object> selectExpertInfoMemberId(Long expertId);


    ExpertInfo selectExpertInfoByUserId(Long userId);
    /**
     *
     * @param expertInfo
     * @return
     */
    public List<ExpertInfo> selectExpertInfoListNotInConcern(ExpertInfo expertInfo);

    /**
     * @param expertInfo
     * @return
     */
    public List<ExpertInfo> selectAllExpertInfoList(ExpertInfo expertInfo);

//    /**
//     * 查询所有的农友信息
//     *
//     * @param memberInfo
//     * @return
//     */
//    public List<MemberInfo> selectAllMemberInfoList(Long memberId);

    /**
     * 判断是否已经申请过
     *
     * @return
     */
    public boolean isApplyExpret(Long memberId);


    /**
     * 根据会员ID，专家ID查找专家详情
     *
     * @param result
     * @param expertInfoId
     * @param memberId
     * @throws ParseException
     * @throws Exception
     */
    public void selectExpretInfoDetailById(Map<String, Object> result, Long expertInfoId, Long memberId) throws ParseException, Exception;

    /**
     * 根据擅长作物查询专家
     *
     * @param expertInfo
     * @return
     */
    public List<ExpertInfo> selectExpertInfoNoPageList(ExpertInfo expertInfo);

    /**
     * 根据作物过滤专家
     * @return
     */
    public List<ExperInfoDto> selectExpertInfoNoPageListFilter(ExpertInfoVo expertInfoVo);

//    public List<ExperInfoDto> queryExpertInfoForIndexList(ExpertInfo expertInfo);

    /**
     * 查询果机员
     * @param expertId
     * @return
     */
    ExpertInfoVo selectExpertInfoByIdAndType(long expertId);

    /**
     * 查看要审核的专家详情
     * @param expertId
     * @return
     */
    ExpertInfo selectAuditExpertInfoById(Long expertId);

    /**
     * 查询专家数量
     * @return
     */
    int selectExpertCount();
}
