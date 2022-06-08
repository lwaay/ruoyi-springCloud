package com.sinonc.agriculture.controller.api;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.dto.*;
import com.sinonc.agriculture.mapper.MemberDictMapper;
import com.sinonc.agriculture.service.*;
import com.sinonc.agriculture.vo.AgriQuestionVo;
import com.sinonc.base.api.RemoteCorpDictService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.*;
import jdk.nashorn.api.scripting.ScriptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "农业问题接口")
@RestController
@RequestMapping("/agriculture/api/agriQuestion")
@Slf4j
public class ApiAgriQuestionController extends BaseController {

    @Autowired
    private AgriQuestionService questionService;

    @Autowired
    private QuestionNotifyService notifyService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private AgriQuestionDtoService questionDtoService;

    @Autowired
    private MemberDictService memberDictService;

    @Autowired
    private OwnDynamicService ownDynamicService;

    @Autowired
    private RemoteCorpDictService cropDictService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @Autowired
    private QuestionAnswerDtoService answerDtoService;

    @Autowired
    private MemberDictMapper memberDictMapper;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;


    @ApiOperation("添加问题")
    @PostMapping("add")
    public AjaxResult addQuestion(@RequestBody @Validated AgriQuestionVo agriQuestion, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        //获取会员ID
        Long memberId = tokenService.getLoginUser().getUserid();
        agriQuestion.setMemberId(memberId);

        int rows = questionService.addAgriQuestion(agriQuestion, memberId);

        //判断是否有邀请的会员
        if (!StringUtils.isEmpty(agriQuestion.getExpertIds())) {

            String[] strLong = agriQuestion.getExpertIds().split(",");
            Long[] longs = new Long[strLong.length];

            for (int i = 0; i < strLong.length; i++) {
                ExpertInfo expertInfo = expertInfoService.selectExpertInfoById(Long.parseLong(strLong[i]));
                longs[i] = expertInfo.getMemberId();

                ConcernInfo concernInfo = new ConcernInfo();
                concernInfo.setMemberId(expertInfo.getMemberId());
                concernInfo.setTargetId(agriQuestion.getQuestionId());
                concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_QUESTION);

                int ii = concernInfoService.insertConcernInfo(concernInfo, expertInfo.getMemberId());

                if (ii > 0) {
                    addMemberDictCount(memberId, MemberDictConstants.TYPE_QUESTION_CONCERN_TOTAL);
                }
            }

            //发送邀请回答通知
            notifyService.invitationToAnswer(agriQuestion, longs);
        }

        //更新用户字典
        if (rows > 0) {
            addMemberDictCount(memberId, MemberDictConstants.TYPE_ADD_QUESTION_TOTAL);
        }

        return toAjax(rows);
    }

    @ApiOperation("获取问题列表")
    @GetMapping({"list/{ask}", "list"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query"),
            @ApiImplicitParam(name = "orderByColumn", value = "orderByColumn", paramType = "query"),
            @ApiImplicitParam(name = "isAsc", value = "isAsc", paramType = "query", defaultValue = "desc"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query"),
            @ApiImplicitParam(name = "cropId", value = "作物类型", paramType = "query"),
            @ApiImplicitParam(name = "sunCropId", value = "子类作物类型", paramType = "query")
    })
    public AjaxResult questionList(@PathVariable(required = false) String ask,String title,Long cropId,Long sunCropId, Long sectionId) {


        log.info("ask:"+ask+"-title:"+title);
        log.info("loginUserId:"+!ObjectUtils.isEmpty(SecurityUtils.getUserId()));
        Long parentId=null;
        Long[] children = {};
        if(sunCropId!=null){
            //如果传入子类，则查出父类值
            System.out.println(JSON.toJSONString(cropDictService.getInfo(sunCropId)));
            CropDict cropDict= cropDictService.getInfo(sunCropId).getData();
            if(cropDict!=null){
                parentId=cropDict.getParentId();
                System.out.println(JSON.toJSONString(cropDictService.getchildId(parentId)));
                children = cropDictService.getchildId(parentId).getData();
                cropId=null;
            }else {
               return AjaxResult.error("无cropId为:"+sunCropId+"的作物信息");
            }
        }

        startPage();
        PageHelper.orderBy("create_time desc");

        List<AgriQuestionSimpleDto> agriQuestionSimpleDtos = questionDtoService.getAgriQuestionSimpleDtoList(!ObjectUtils.isEmpty(SecurityUtils.getUserId()), StringUtils.isEmpty(ask)?title:ask,cropId,children,sectionId);
        if (agriQuestionSimpleDtos.size() > 0){
            agriQuestionSimpleDtos.forEach( entity -> {
                //是否@专家
//                ConcernInfo concernInfo = concernInfoService.getByMemberIdAndTypeAndTargetId(tokenService.getLoginUser().getUserid(), String.valueOf(ConcernInfoConstants.CONCERN_INFO_QUESTION), entity.getQuestionId());
//                if (Optional.ofNullable(concernInfo).isPresent()){
//                    entity.set
//                }

                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setQuestionId(entity.getQuestionId());
                List<QuestionAnswerDto> questionAnswerList = answerDtoService.getQuestionAnswerDtoList(questionAnswer, !ObjectUtils.isEmpty(tokenService.getLoginUser()));
                if (questionAnswerList.size() > 0){
//                    entity.setAnswers(questionAnswerList);
                    entity.setAnswer(questionAnswerList.get(0));
                }
            });
        }

        log.info("agriQuestionSimpleDtos:"+agriQuestionSimpleDtos.size());
        PageInfo<AgriQuestionSimpleDto> pageInfo = new PageInfo<>(agriQuestionSimpleDtos);

        AjaxResult success = AjaxResult.success(pageInfo.getList());
        success.put("hasNextPage", pageInfo.isHasNextPage());
        success.put("total", pageInfo.getTotal());

        return success;

    }

    @ApiOperation("添加关注问题")
    @GetMapping("concern/{questionId}")
    public AjaxResult concern(@PathVariable Long questionId) {

        Long memberId = tokenService.getLoginUser().getUserid();

        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setMemberId(memberId);
        concernInfo.setTargetId(questionId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_QUESTION);

        int i = concernInfoService.insertConcernInfo(concernInfo, memberId);

        if (i > 0) {
            addMemberDictCount(memberId, MemberDictConstants.TYPE_QUESTION_CONCERN_TOTAL);
        }

        return toAjax(i);
    }

    @ApiOperation("取消关注")
    @GetMapping("concern/cancel/{questionId}")
    public AjaxResult cancelConcern(@PathVariable Long questionId) {

        ConcernInfo concernInfo = concernInfoService.getByMemberIdAndTypeAndTargetId(tokenService.getLoginUser().getUserid(), String.valueOf(ConcernInfoConstants.CONCERN_INFO_QUESTION), questionId);

        if (concernInfo == null) {
            return AjaxResult.error("参数错误");
        }

        return toAjax(concernInfoService.deleteConcernInfoById(concernInfo.getConcernId()));
    }


    @ApiOperation("问题详情接口")
    @GetMapping("detail/{questionId}")
    public AjaxResult detail(@PathVariable Long questionId) {
        LoginUser loginUser = tokenService.getLoginUser();
        AgriQuestionDetailDto agriQuestionDetailDto=questionDtoService.getAgriQuestionDetailDto(!ObjectUtils.isEmpty(tokenService.getLoginUser()), questionId);
        if(agriQuestionDetailDto!=null){
            return AjaxResult.success(agriQuestionDetailDto);
        }else {
            return AjaxResult.error("该问题已删除。");
        }

    }


    /**
     * 字典值 +1 操作
     *
     * @param memberId 会员ID
     * @param dictType 字典类型
     */
    private void addMemberDictCount(Long memberId, String dictType) {
        executor.execute(new TimerTask() {
            @Override
            public void run() {

                MemberDict memberDict = memberDictService.getDictByMemberIdAndType(memberId, dictType);

                if (memberDict == null) {

                    memberDict = new MemberDict();
                    memberDict.setDictType(dictType);
                    memberDict.setMemberId(memberId);
                    memberDict.setDictValue("1");
                    memberDict.setCreateTime(new Date());

                    memberDictService.addMemberDict(memberDict);

                } else {

                    memberDict.setDictValue(String.valueOf(Integer.parseInt(memberDict.getDictValue()) + 1));
                    memberDict.setUpdateTime(new Date());
                    memberDictService.updateMemberDict(memberDict);
                }

            }
        });
    }

    /**
     * 字典值-1操作
     *
     * @param memberId 会员ID
     * @param dictType 字典类型
     */
    private void subtractDictCount(Long memberId, String dictType) {

        executor.execute(new TimerTask() {
            @Override
            public void run() {

                MemberDict memberDict = memberDictService.getDictByMemberIdAndType(memberId, dictType);

                if (memberDict != null) {

                    memberDict.setDictValue(String.valueOf(Integer.parseInt(memberDict.getDictValue()) - 1));
                    memberDict.setUpdateTime(new Date());
                    memberDictService.updateMemberDict(memberDict);
                }

            }
        });
    }


    /**
     * 查询问题列表
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("查询我的问题列表")
    @GetMapping(value = "/selectQuestionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "questionAnswerConstants", value = "查询问题类型", required = true, paramType = "query")
    })
    public AjaxResult selectQuestionList(Integer pageNum, Integer pageSize, int questionAnswerConstants) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 5;
        }

        Long memberId = SecurityUtils.getUserId();
        PageHelper.startPage(pageNum, pageSize,"create_time desc");

        //如果值为0则查询自己的信息
        if (memberId == 0) {
            memberId = tokenService.getLoginUser().getUserid();
        }


        PageInfo pageInfo = null;
        //我提的问题
        if (questionAnswerConstants == ListQuestionAnswerConstants.OwnQuestion) {
            AgriQuestion agriQuestion = new AgriQuestion();
            agriQuestion.setMemberId(memberId);
            List<AgriQuestionSimpleDto> agriQuestionSimpleDtos = questionDtoService.getAgriQuestionList(!ObjectUtils.isEmpty(tokenService.getLoginUser()), agriQuestion);
            pageInfo = new PageInfo<>(agriQuestionSimpleDtos);

        }

        //我回答的问题
        if (questionAnswerConstants == ListQuestionAnswerConstants.OwnAnswerQuestion) {
            AgriQuestion agriQuestion = new AgriQuestion();
            agriQuestion.setMemberId(memberId);
            pageInfo = new PageInfo<>(questionDtoService.selectAgriQuestionListByAnswer(!ObjectUtils.isEmpty(tokenService.getLoginUser()), agriQuestion));
        }

        //我关注的问题
        if (questionAnswerConstants == ListQuestionAnswerConstants.OwnConcern) {
            AgriQuestion agriQuestion = new AgriQuestion();
            agriQuestion.setMemberId(memberId);
            List<AgriQuestionSimpleDto> questions = questionDtoService.selectAgriQuestionListByConcernInfo(!ObjectUtils.isEmpty(tokenService.getLoginUser()), agriQuestion);
//            for(AgriQuestionSimpleDto questionSimpleDto : questions){
//
//                QuestionAnswer questionAnswer = new QuestionAnswer();
//                questionAnswer.setQuestionId(questionSimpleDto.getQuestionId());
//                List<QuestionAnswerDto> questionAnswerList = answerDtoService.getQuestionAnswerDtoList(questionAnswer, !ObjectUtils.isEmpty(tokenService.getLoginUser()));
//                questionSimpleDto.setAnswers(questionAnswerList);
//            }
            pageInfo = new PageInfo<>(questions);

        }


        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    private List<HistoryDto> groupByExpert(List<AgriQuestionSimpleDto> questions){
        Set<ExpertInfo> expertInfoSet = new HashSet<>();
        List<AgriQuestionSimpleDto> questionSimpleDtoList = new ArrayList<>();
        List<AgriQuestionSimpleDto> questionSimpleDtoNotAnswer = new ArrayList<>();
        questions.forEach(entity -> {
            if(Optional.ofNullable(entity.getAnswer()).isPresent() && Optional.ofNullable(entity.getAnswer().getExpertInfo()).isPresent()){
                expertInfoSet.add(entity.getAnswer().getExpertInfo());
                questionSimpleDtoList.add(entity);
            }else {
                questionSimpleDtoNotAnswer.add(entity);

            }
        });
        //没有专家的分为一类
        HistoryDto historyDto = new HistoryDto();
        historyDto.setQuestionSimpleDtoList(questionSimpleDtoNotAnswer);
        //有专家回复分为一类
        List<HistoryDto> historyDtoList = changeList(expertInfoSet,questionSimpleDtoList);
        historyDtoList.add(historyDto);
        return historyDtoList;
    }

    private List<HistoryDto> changeList(Set<ExpertInfo> expertInfoSet, List<AgriQuestionSimpleDto> questions){
        List<HistoryDto> historyDtoList = new ArrayList<>();
        List<AgriQuestionSimpleDto> questionSimpleDtoList = new ArrayList<>();
        Iterator iterator = expertInfoSet.iterator();
        while (iterator.hasNext()){
            HistoryDto historyDto = new HistoryDto();
            ExpertInfo expertInfo = (ExpertInfo) iterator.next();

            //查询专家回复率
            MemberDict memberDictQuestion = memberDictMapper.selectDictByMemberIdAndType(expertInfo.getMemberId(), MemberDictConstants.TYPE_QUESTION_CONCERN_TOTAL);
            MemberDict memberDictAnswer = memberDictMapper.selectDictByMemberIdAndType(expertInfo.getMemberId(), MemberDictConstants.TYPE_ANSWER_TOTAL);
            int questionCount = 0;
            int answerCount = 0;
            if (Optional.ofNullable(memberDictQuestion).isPresent()){
                questionCount = Integer.parseInt(memberDictQuestion.getDictValue());
            }
            if (Optional.ofNullable(memberDictAnswer).isPresent()){
                questionCount = Integer.parseInt(memberDictAnswer.getDictValue());
            }
            expertInfo.setQuestionCount(questionCount);
            expertInfo.setAnswerCount(answerCount);

            historyDto.setExpertInfo(expertInfo);
            questions.forEach(entity -> {
                if(entity.getAnswer().getExpertInfo().equals(expertInfo)){
                    questionSimpleDtoList.add(entity);
                }
            });
            historyDto.setQuestionSimpleDtoList(questionSimpleDtoList);
            historyDtoList.add(historyDto);
        }

        return historyDtoList;
    }

    /**
     * 查询问题列表
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("查询我的动态列表")
    @GetMapping(value = "/selectOwnDynamicList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "questionAnswerConstants", value = "查询问题类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query")
    })
    public AjaxResult selectOwnDynamicList(Integer pageNum, Integer pageSize, int questionAnswerConstants, Long memberId) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);

        //如果值为0则查询自己的信息
        if (memberId == 0) {
            memberId = tokenService.getLoginUser().getUserid();
        }

        OwnDynamic ownDynamic = new OwnDynamic();
        ownDynamic.setMemberIdP(memberId);
        ownDynamic.setType(questionAnswerConstants);
        PageInfo pageInfo = new PageInfo<>(ownDynamicService.getOwnDynamicListForDto(ownDynamic));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());
        success.put("total", pageInfo.getTotal());

        return success;
    }

    @ApiOperation("查询我的问题板块列表")
    @GetMapping(value = "/agriQuestionSectionList")
    public AjaxResult agriQuestionSectionList() {
        Long memberId = tokenService.getLoginUser().getUserid();
        List<AgriQuestionSectionDto> list = questionDtoService.selectAgriQuestionSectionList(memberId);
        return AjaxResult.success(list);
    }

    @ApiOperation("根据我关注的板块推荐问题列表")
    @GetMapping(value = "/pushAgriQuestion")
    public AjaxResult pushAgriQuestionBySection() {
        Long memberId = tokenService.getLoginUser().getUserid();
        List<AgriQuestionSectionDto> sectionDtos = questionDtoService.selectAgriQuestionSectionList(memberId);
        if(CollectionUtils.isEmpty(sectionDtos)){
            return AjaxResult.success(sectionDtos);
        }
        List<Long> sectionIds = sectionDtos.stream()
                .map(e -> e.getSectionId()).collect(Collectors.toList());
        List<AgriQuestionDto> list = questionDtoService.pushAgriQuestionBySection(sectionIds);
        return AjaxResult.success(list);
    }
}
