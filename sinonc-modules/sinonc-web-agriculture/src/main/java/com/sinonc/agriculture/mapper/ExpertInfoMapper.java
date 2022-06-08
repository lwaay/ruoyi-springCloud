package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.dto.ExperInfoDto;
import com.sinonc.agriculture.vo.ExpertInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 专家消息Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-05
 */
public interface ExpertInfoMapper
{
    /**
     * 查询专家消息
     *
     * @param expertId 专家消息ID
     * @return 专家消息
     */
    public ExpertInfo selectExpertInfoById(Long expertId);

    public ExpertInfo selectExpertInfoByUserId(Long userId);

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

    /**
     * 删除专家消息
     *
     * @param expertId 专家消息ID
     * @return 结果
     */
    public int deleteExpertInfoById(Long expertId);

    /**
     * 批量删除专家消息
     *
     * @param expertIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertInfoByIds(String[] expertIds);

    /**
     * 查询专家列表返回list<String,Map>
     * @param expertInfo
     * @return
     */
    List<Map<String, Object>> selectExpertInfoListMap(ExpertInfo expertInfo);

    /**
     * 根据专家id查询专家
     *
     * @param expertId
     * @return
     */
    Map<String, Object> selectExpertInfoMemberId(Long expertId);

    List<ExpertInfo> selectExpertInfoListNotInConcern();

    /**
     * 查询所有专家信息
     *
     * @return
     */
    List<ExperInfoDto> selectAllExpertInfoList();


    List<ExperInfoDto> selectExpertInfoNoPageList(ExpertInfo expertInfo);

    /**
     * 根据memberId查询专家ID
     *
     * @param memberId 会员ID
     * @return 结果
     */
    ExpertInfo selectExpertInfoByMemberId(Long memberId);

    /**
     * 根据作物过滤查询专家
     * @param expertInfoVo
     * @return
     */
    List<ExperInfoDto> selectExpertInfoNoPageListFilter(ExpertInfoVo expertInfoVo);

    /**
     * 查询专家数量
     * @return
     */
    int selectExpertCount();
}
