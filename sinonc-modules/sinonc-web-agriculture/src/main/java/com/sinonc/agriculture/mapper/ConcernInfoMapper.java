package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ConcernInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会员关注信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public interface ConcernInfoMapper {
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
    public int insertConcernInfo(ConcernInfo concernInfo);

    /**
     * 修改会员关注信息
     *
     * @param concernInfo 会员关注信息
     * @return 结果
     */
    public int updateConcernInfo(ConcernInfo concernInfo);

    /**
     * 删除会员关注信息
     *
     * @param concernId 会员关注信息ID
     * @return 结果
     */
    public int deleteConcernInfoById(Long concernId);

    /**
     * 批量删除会员关注信息
     *
     * @param concernIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteConcernInfoByIds(String[] concernIds);

    /**
     * 根据专家id查询专家关注表查询关注数
     *
     * @param expertId
     * @return
     */
    List<Map<String, Object>> selectConcernInfoByIdforMerber(@Param("expertId") Long expertId);

    /**
     * 我的关注
     *
     * @param memberId
     * @return
     */
    List<ConcernInfo> selectOwnConcernByMemberId(Long memberId);


    ConcernInfo selectMemberConcernInfoByTargetId(Long memberId, Integer targetType, Long targetId);


    List<ConcernInfo> selectConcernInfoByTargetIds(Long memberId, Integer targetType, List<Long> ids);

    /**
     * 根据会员id查询会员关注的专家
     *
     * @param memberId
     * @return
     */
    List<ConcernInfo> getConcernInfoByMemberIdByExpertInfo(Long memberId);

    /**
     * 批量插入
     *
     * @param concernInfoList
     * @return
     */
    int batchInsert(List<ConcernInfo> concernInfoList);

    /**
     * 查询会员所属的类别
     *
     * @param memberId 会员ID
     * @param type     类型
     * @return
     */
    List<ConcernInfo> selectByMemberIdAndType(Long memberId, Integer type);

    /**
     * 根据targetId和targetType查询关注
     *
     * @param targetType 目标类型
     * @param targetId   目标ID
     * @return 关注集合
     */
    List<ConcernInfo> selectByTypeAndTargetId(Integer targetType, Long targetId);

    /**
     * 删除关注
     *
     * @param concernInfo
     * @return
     */
    int deleteConcernInfo(ConcernInfo concernInfo);


    /**
     * 根据会员ID、类型、目标表ID查询
     *
     * @param memberId
     * @param type
     * @param questionId
     * @return
     */
    ConcernInfo selectByMemberIdAndTypeAndTargetId(Long memberId, String type, Long questionId);

    /**
     * @param targetId
     * @param targetType
     * @return
     */
    List<String> selectConcernMemberInfo(Long targetId, Integer targetType);
}
