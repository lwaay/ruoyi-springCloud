package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ConcernInfo;

import java.util.List;
import java.util.Map;

/**
 * 会员关注信息Service接口
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public interface ConcernInfoService
{
    /**
     * 查询会员关注信息
     *
     * @param concernId 会员关注信息ID
     * @return 会员关注信息
     */
    public ConcernInfo selectConcernInfoById(Long concernId);

    /**
     * 查询会员关注信息列表
     *
     * @param concernInfo 会员关注信息
     * @return 会员关注信息集合
     */
    public List<ConcernInfo> selectConcernInfoList(ConcernInfo concernInfo);

    /**
     * 新增会员关注信息
     *
     * @param concernInfo 会员关注信息
     * @return 结果
     */
    public int insertConcernInfo(ConcernInfo concernInfo, Long memberId);

    /**
     * 修改会员关注信息
     *
     * @param concernInfo 会员关注信息
     * @return 结果
     */
    public int updateConcernInfo(ConcernInfo concernInfo);

    /**
     * 批量删除会员关注信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConcernInfoByIds(String ids);

    /**
     * 删除会员关注信息信息
     *
     * @param concernId 会员关注信息ID
     * @return 结果
     */
    public int deleteConcernInfoById(Long concernId);

    /**
     * 根据专家id查询专家关注表查询关注数
     * @param expertId
     * @return
     */
    List<Map<String,Object>> selectConcernInfoByIdforMerber(Long expertId);

    /**
     * 我的关注
     * @param memberId
     * @return
     */
    List<ConcernInfo> selectOwnConcernByMemberId(Long memberId);

    /**
     * 根据会员id查询会员关注的专家
     *
     * @param memberId
     * @return
     */
    List<ConcernInfo> getConcernInfoByMemberIdByExpertInfo(Long memberId);


    List<ConcernInfo> getByMemberIdAndType(Long memberId, Integer type);


    int batchAdd(List<ConcernInfo> concernInfoList);

    int deleteConcernInfo(ConcernInfo concernInfo);

    /**
     * 添加专家关注
     *
     * @param expertInfoId
     * @param memberId
     * @return
     */
    String addOwmConcernInfo(Long expertInfoId, Long memberId) throws Exception;

    /**
     * 取消关注专家
     *
     * @param concernInfo
     * @return
     */
    int cancelOwnExpertInfo(ConcernInfo concernInfo);

    /**
     * 添加农友关注
     *
     * @param memberInfoId
     * @param memberId
     * @return
     */
    String addOwnMemberInfo(Long memberInfoId, Long memberId) throws Exception;

    /**
     * 取消农友关注
     *
     * @param memberInfoId
     * @param memberId
     * @return
     */
    int cancelOwnMemberInfo(Long memberInfoId, Long memberId);

    /**
     * @param memberId
     * @param type
     * @param questionId
     * @return
     */
    ConcernInfo getByMemberIdAndTypeAndTargetId(Long memberId, String type, Long questionId);

    /**
     * 查询关注人列表
     *
     * @param targetId
     * @param targetType
     * @return
     */
    List<String> selectConcernMemberInfo(Long targetId, Integer targetType);
}
