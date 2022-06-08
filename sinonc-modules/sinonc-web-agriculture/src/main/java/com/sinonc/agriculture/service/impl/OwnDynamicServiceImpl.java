package com.sinonc.agriculture.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.sinonc.agriculture.domain.*;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.dto.OwnDynamicDto;
import com.sinonc.agriculture.mapper.AgriQuestionMapper;
import com.sinonc.agriculture.mapper.GrowTechMapper;
import com.sinonc.agriculture.mapper.GrowtechLikeMapper;
import com.sinonc.agriculture.mapper.OwnDynamicMapper;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.CropDictService;
import com.sinonc.agriculture.service.OwnDynamicService;
import com.sinonc.agriculture.service.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 我的动态Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-24
 */
@Service
public class OwnDynamicServiceImpl implements OwnDynamicService {
    @Autowired
    private OwnDynamicMapper ownDynamicMapper;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private CropDictService cropDictService;

    @Autowired
    private AgriQuestionMapper agriQuestionMapper;

    @Autowired
    private GrowTechMapper growTechMapper;

    @Autowired
    private GrowtechLikeMapper growtechLikeMapper;

    /**
     * 查询我的动态
     *
     * @param owndynaId 我的动态ID
     * @return 我的动态
     */
    @Override
    public OwnDynamic getOwnDynamicById(Long owndynaId) {
        return ownDynamicMapper.selectOwnDynamicById(owndynaId);
    }

    /**
     * @param ownDynamic 我的动态
     * @return
     */
    @Override
    public List<OwnDynamic> getOwnDynamicList(OwnDynamic ownDynamic) {
        return ownDynamicMapper.selectOwnDynamicList(ownDynamic);
    }

    /**
     * 查询我的动态列表
     *
     * @param ownDynamic 我的动态
     * @return 我的动态
     */
    @Override
    public List<OwnDynamicDto> getOwnDynamicListForDto(OwnDynamic ownDynamic) {
        List<OwnDynamicDto> ownDynamicList = ownDynamicMapper.selectOwnDynamicListForDto(ownDynamic);

        Map<String, OwnDynamicDto> growIdMap = new HashMap<String, OwnDynamicDto>();

        for (int i = 0; i < ownDynamicList.size(); i++) {
            OwnDynamicDto ownDynamicDto = ownDynamicList.get(i);
            int type = ownDynamicDto.getType();
            if ((type == ListQuestionAnswerConstants.OwnQuestion) || (type == ListQuestionAnswerConstants.OwnConcern) || (type == ListQuestionAnswerConstants.OwnAnswerQuestion)) {
                //查询评论(答案)数
                String content = ownDynamicDto.getDynaContent();
                Map contentMap = (Map) JSONUtils.parse(content);
                Integer questionId = (Integer) contentMap.get("questionId");
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionId(Long.valueOf(questionId));

                AgriQuestion agriQuestion = agriQuestionMapper.selectAgriQuestionById(Long.valueOf(questionId));
                ownDynamicDto.setAgriQuestion(agriQuestion);
                if(agriQuestion==null){
                    continue;
                }

                List<QuestionAnswer> questionAnswerList = questionAnswerService.getQuestionAnswerList(questionAnswer);
                if (questionAnswerList != null && questionAnswerList.size() > 0) {
                    ownDynamicDto.setAnswerCount(questionAnswerList.size());
                    ownDynamicDto.setFirstQuestionAnswer(questionAnswerList.get(0));
                }

                //是否关注
                ConcernInfo concernInfo = concernInfoService.getByMemberIdAndTypeAndTargetId(ownDynamic.getMemberIdP(), String.valueOf(ConcernInfoConstants.CONCERN_INFO_QUESTION), Long.valueOf(questionId));
                if (concernInfo == null) {
                    ownDynamicDto.setConcern(false);
                } else {
                    ownDynamicDto.setConcern(true);
                }

                //关注人列表
                List<String> concernMemberList = concernInfoService.selectConcernMemberInfo(Long.valueOf(questionId), ConcernInfoConstants.CONCERN_INFO_QUESTION);
                ownDynamicDto.setConcernMemberList(concernMemberList);

                //查询作物
                Long cropId = agriQuestion.getCropId();
                CropDict cropDict = cropDictService.selectCropDictById(cropId);
                if (cropDict != null) {
                    ownDynamicDto.setCropName(cropDict.getCropName());
                }

            }

            //我评论的种养殖技术列表
            if (type == ListQuestionAnswerConstants.OwnGrowTechMomment) {
                String content = ownDynamicDto.getDynaContent();
                Map contentMap = (Map) JSONUtils.parse(content);
                Integer growId = (Integer) contentMap.get("growId");

                GrowTech growTech = growTechMapper.selectGrowTechById(Long.valueOf(growId));
                ownDynamicDto.setGrowTech(growTech);

                GrowtechLike growtechLike = new GrowtechLike();
                growtechLike.setGrowtechIdP(Long.valueOf(growId));
                growtechLike.setMemberIdP(ownDynamic.getMemberIdP());
                List growtechLikeList = growtechLikeMapper.selectGrowtechLikeList(growtechLike);
                //是否点赞，征用Concern属性
                if (growtechLikeList != null && growtechLikeList.size() > 0) {
                    ownDynamicDto.setConcern(true);
                } else {
                    ownDynamicDto.setConcern(false);
                }
            }
        }
        List tempList = new ArrayList();
        clearRepetition(ownDynamicList, tempList);
        return tempList;
    }

    /**
     * 去重
     *
     * @param ownDynamicList
     * @param tempList
     */
    private void clearRepetition(List<OwnDynamicDto> ownDynamicList, List tempList) {
        Map<String, OwnDynamicDto> tempMap = new HashMap<String, OwnDynamicDto>();

        for (int i = 0; i < ownDynamicList.size(); i++) {
            OwnDynamicDto ownDynamicDto = ownDynamicList.get(i);
            Integer integer = ownDynamicDto.getType();
            if (integer == ListQuestionAnswerConstants.OwnGrowTechMomment) {
                String key = ownDynamicDto.getTargetId()+ ownDynamicDto.getType() + String.valueOf(ownDynamicDto.getMemberIdP());
                OwnDynamicDto mapOwnDynamicDto = tempMap.get(key);
                if (mapOwnDynamicDto == null) {
                    //不存在
                    tempMap.put(key, ownDynamicDto);
                    tempList.add(ownDynamicDto);
                }
            } else {
                String key = ownDynamicDto.getTargetId() + ownDynamicDto.getType() + String.valueOf(ownDynamicDto.getMemberIdP());
                OwnDynamicDto mapOwnDynamicDto = tempMap.get(key);
                if (mapOwnDynamicDto == null) {
                    //不存在
                    tempMap.put(key, ownDynamicDto);
                    tempList.add(ownDynamicDto);
                }
            }
        }
    }

    /**
     * 新增我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    @Override
    public int addOwnDynamic(OwnDynamic ownDynamic) {
        ownDynamic.setCreateTime(DateUtils.getNowDate());
        return ownDynamicMapper.insertOwnDynamic(ownDynamic);
    }

    /**
     * 修改我的动态
     *
     * @param ownDynamic 我的动态
     * @return 结果
     */
    @Override
    public int updateOwnDynamic(OwnDynamic ownDynamic) {
        ownDynamic.setUpdateTime(DateUtils.getNowDate());
        return ownDynamicMapper.updateOwnDynamic(ownDynamic);
    }

    /**
     * 删除我的动态对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOwnDynamicByIds(String ids) {
        return ownDynamicMapper.deleteOwnDynamicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除我的动态信息
     *
     * @param owndynaId 我的动态ID
     * @return 结果
     */
    @Override
    public int deleteOwnDynamicById(Long owndynaId) {
        return ownDynamicMapper.deleteOwnDynamicById(owndynaId);
    }

    @Override
    public int addOwnQuestionDynamic(AgriQuestion agriQuestion, Long targetId, Integer type, Long memberId) {

        Map<String, Object> content = new HashMap<>();
        content.put("questionId", agriQuestion.getQuestionId());
        content.put("memberId", agriQuestion.getMemberId());
        content.put("cropId", agriQuestion.getCropId());
        content.put("sectionId", agriQuestion.getSectionId());
        content.put("ask", agriQuestion.getAsk());
        content.put("img", agriQuestion.getImg());
        content.put("areaCode", agriQuestion.getAreaCode());
        content.put("areaName", agriQuestion.getAreaName());
        content.put("readCount", agriQuestion.getReadCount());
        content.put("answerCount", agriQuestion.getAnswerCount());
        content.put("status", agriQuestion.getStatus());
        content.put("createTime", agriQuestion.getCreateTime());
        content.put("updateTime", agriQuestion.getUpdateTime());
        content.put("delFlag", agriQuestion.getDelFlag());
        content.put("sysName", agriQuestion.getSysName());
        content.put("describe", agriQuestion.getDescribe());
        content.put("lastAnswerTime", agriQuestion.getLastAnswerTime());

        //插入动态表
        OwnDynamic ownDynamic = new OwnDynamic();
        ownDynamic.setMemberIdP(memberId);
        ownDynamic.setTargetId(targetId);
        ownDynamic.setType(type);

        String agriQuestionContent = JSONUtils.toJSONString(content);
        ownDynamic.setDynaContent(agriQuestionContent);

        ownDynamic.setCreateTime(DateUtils.getNowDate());
        return ownDynamicMapper.insertOwnDynamic(ownDynamic);
    }
}
