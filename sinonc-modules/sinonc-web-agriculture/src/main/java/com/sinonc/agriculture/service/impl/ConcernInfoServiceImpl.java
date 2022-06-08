package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.OwnDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会员关注信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@Service
public class ConcernInfoServiceImpl implements ConcernInfoService {

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private AgriQuestionMapper agriQuestionMapper;

    @Autowired
    private OwnDynamicService ownDynamicService;

    /**
     * 查询会员关注信息
     *
     * @param concernId 会员关注信息ID
     * @return 会员关注信息
     */
    @Override
    public ConcernInfo selectConcernInfoById(Long concernId) {
        return concernInfoMapper.selectConcernInfoById(concernId);
    }

    /**
     * 查询会员关注信息列表
     *
     * @param concernInfo 会员关注信息
     * @return 会员关注信息
     */
    @Override
    public List<ConcernInfo> selectConcernInfoList(ConcernInfo concernInfo) {
        return concernInfoMapper.selectConcernInfoList(concernInfo);
    }

    /**
     * 新增会员关注信息
     *
     * @param concernInfo 会员关注信息
     * @return 结果
     */
    @Override
    public int insertConcernInfo(ConcernInfo concernInfo, Long memberId) {

        List<ConcernInfo> concernInfos = concernInfoMapper.selectConcernInfoList(concernInfo);

        if (concernInfos != null && concernInfos.size() > 0) {
            throw new RuntimeException("请勿重复关注");
        }

        Date nowDate = DateUtils.getNowDate();

        concernInfo.setCreateTime(nowDate);
        concernInfo.setCreateBy("member");
        concernInfo.setUpdateBy("member");
        concernInfo.setUpdateTime(nowDate);

        int row = concernInfoMapper.insertConcernInfo(concernInfo);

        AgriQuestion agriQuestion = agriQuestionMapper.selectAgriQuestionById(concernInfo.getTargetId());

        row = row + ownDynamicService.addOwnQuestionDynamic(agriQuestion, concernInfo.getConcernId(), ListQuestionAnswerConstants.OwnConcern, memberId);
        return row;
    }

    /**
     * 修改会员关注信息
     *
     * @param concernInfo 会员关注信息
     * @return 结果
     */
    @Override
    public int updateConcernInfo(ConcernInfo concernInfo) {
        return concernInfoMapper.updateConcernInfo(concernInfo);
    }

    /**
     * 删除会员关注信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConcernInfoByIds(String ids) {
        return concernInfoMapper.deleteConcernInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员关注信息信息
     *
     * @param concernId 会员关注信息ID
     * @return 结果
     */
    @Override
    public int deleteConcernInfoById(Long concernId) {
        return concernInfoMapper.deleteConcernInfoById(concernId);
    }

    /**
     * 根据专家id查询专家关注表查询关注数
     *
     * @param expertId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectConcernInfoByIdforMerber(Long expertId) {
        return concernInfoMapper.selectConcernInfoByIdforMerber(expertId);
    }

    /**
     * 我的关注
     *
     * @param memberId
     * @return
     */
    @Override
    public List<ConcernInfo> selectOwnConcernByMemberId(Long memberId) {
        return concernInfoMapper.selectOwnConcernByMemberId(memberId);
    }

    /**
     * 根据会员id查询会员关注的专家
     *
     * @param memberId
     * @return
     */
    @Override
    public List<ConcernInfo> getConcernInfoByMemberIdByExpertInfo(Long memberId) {
        return concernInfoMapper.getConcernInfoByMemberIdByExpertInfo(memberId);
    }

    @Override
    public List<ConcernInfo> getByMemberIdAndType(Long memberId, Integer type) {
        return concernInfoMapper.selectByMemberIdAndType(memberId, type);
    }


    /**
     * 批量插入
     *
     * @param concernInfoList
     * @return
     */
    @Override
    public int batchAdd(List<ConcernInfo> concernInfoList) {
        return concernInfoMapper.batchInsert(concernInfoList);
    }

    @Override
    @Transactional
    public int deleteConcernInfo(ConcernInfo concernInfo) {
        return concernInfoMapper.deleteConcernInfo(concernInfo);
    }

    @Override
    @Transactional
    public String addOwmConcernInfo(Long expertInfoId, Long memberId) throws Exception {

        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setTargetId(expertInfoId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
        concernInfo.setMemberId(memberId);
        concernInfo.setCreateTime(new Date());

        ConcernInfo paraConcernInfo = new ConcernInfo();
        paraConcernInfo.setTargetId(expertInfoId);
        paraConcernInfo.setMemberId(memberId);
        paraConcernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
        List list = concernInfoMapper.selectConcernInfoList(paraConcernInfo);

        if (list != null && list.size() > 0) {
            throw new Exception("此专家已经关注。");
        }
        int row = concernInfoMapper.insertConcernInfo(concernInfo);

        if (row == 1) {
            return "关注成功。";
        } else {
            throw new Exception("关注失败。");
        }
    }

    @Override
    public int cancelOwnExpertInfo(ConcernInfo concernInfo) {
        concernInfo.setCreateTime(new Date());
        return concernInfoMapper.deleteConcernInfo(concernInfo);
    }

    @Override
    public String addOwnMemberInfo(Long memberInfoId, Long memberId) throws Exception {
        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setTargetId(memberInfoId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
        concernInfo.setMemberId(memberId);
        concernInfo.setCreateTime(new Date());

        ConcernInfo paraConcernInfo = new ConcernInfo();
        paraConcernInfo.setTargetId(memberInfoId);
        paraConcernInfo.setMemberId(memberId);
        paraConcernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
        List list = concernInfoMapper.selectConcernInfoList(paraConcernInfo);

        if (list != null && list.size() > 0) {
            throw new Exception("此农友已经关注。");
        }

        int row = concernInfoMapper.insertConcernInfo(concernInfo);
        if (row >= 0) {
            return "关注成功";
        } else {
            throw new Exception("关注失败。");
        }
    }

    @Override
    public int cancelOwnMemberInfo(Long memberInfoId, Long memberId) {
        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setMemberId(memberId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
        concernInfo.setTargetId(memberInfoId);
        return concernInfoMapper.deleteConcernInfo(concernInfo);
    }

    @Override
    public ConcernInfo getByMemberIdAndTypeAndTargetId(Long memberId, String type, Long questionId) {
        return concernInfoMapper.selectByMemberIdAndTypeAndTargetId(memberId, type, questionId);
    }

    @Override
    public List<String> selectConcernMemberInfo(Long targetId, Integer targetType) {
        return concernInfoMapper.selectConcernMemberInfo(targetId, targetType);
    }

}
