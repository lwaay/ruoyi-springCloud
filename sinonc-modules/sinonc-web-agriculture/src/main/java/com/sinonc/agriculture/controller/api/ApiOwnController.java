//package com.sinonc.agriculture.controller.api;
//
//import com.sinonc.agriculture.constants.ConcernInfoConstants;
//import com.sinonc.agriculture.constants.MemberDictConstants;
//import com.sinonc.agriculture.domain.*;
//import com.sinonc.agriculture.service.*;
//import com.sinonc.agriculture.vo.MemberBackgroundVo;
//import com.sinonc.agriculture.vo.MemberInfoVo;
//import com.sinonc.base.api.domain.CropDict;
//import com.sinonc.common.core.utils.StringUtils;
//import com.sinonc.common.core.web.controller.BaseController;
//import com.sinonc.common.core.web.domain.AjaxResult;
//import com.sinonc.common.security.service.TokenService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.*;
//
///**
// * 我的
// */
//@Api(tags = "我的页面的接口")
//@RestController
//@RequestMapping(value = "api/agriculture/own")
//@Slf4j
//public class ApiOwnController extends BaseController {
//
//    @Autowired
//    private ConcernInfoService concernInfoService;
//    @Autowired
//    private ExpertInfoService expertInfoService;
//    @Autowired
//    private CropDictService cropDictService;
//    @Autowired
//    private MemberInfoService memberInfoService;
//    @Autowired
//    private AppSuggestService appSuggestService;
//    @Autowired
//    private SectionDictService sectionDictService;
//
//    @Autowired
//    private MemberDictService memberDictService;
//
//    @Autowired
//    private TokenService tokenService;
//
//    /**
//     * 我的关注,专家，作物，农友等
//     *
//     * @return
//     */
//    @ApiOperation("我的关注,专家，作物，农友等")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
//            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query")
//    })
//    @GetMapping(value = "/selectownconcern")
//    public AjaxResult selectOwnConcern(Long memberId) {
//        try{
//
//            Long memberIdTwo = tokenService.getLoginUser().getUserid();
//            List<ConcernInfo> list = concernInfoService.selectOwnConcernByMemberId(memberIdTwo); //查询会员关注
//            Map<String, Object> result = new HashMap<>();
//            List<Map<String, Object>> dataList = new ArrayList<>();
//            List<CropDict> cropDictList = new ArrayList<>();
//            List<MemberInfo> MemberInfoList = new ArrayList<>();
//            List<SectionDict> sectionDictList = new ArrayList<>();
//            if (list != null && list.size() > 0) {
//
//                for (ConcernInfo concernInfo : list) {
//                    if (concernInfo.getTargetType() == ConcernInfoConstants.CONCERN_INFO_EXPERT) { //关注专家
//                        Map<String, Object> expertInfo = expertInfoService.selectExpertInfoMemberId(concernInfo.getTargetId());
//                        if (expertInfo == null) {
//                            log.error("concernInfo.getTargetId():"+concernInfo.getTargetId());
//                            continue;
//                        }
//
//                        MemberInfo memberInfo = memberInfoService.getMemberInfoById(Long.parseLong(expertInfo.get("member_id").toString()));
//                        if(memberInfo==null){
//                            log.error("expertInfo.get(\"member_id\"):"+expertInfo.get("member_id"));
//                            continue;
//                        }
//                        expertInfo.put("headImg", memberInfo.getHeadImg());
//
//                        //查询专家粉丝
//                        List<Map<String, Object>> concernInfolist = concernInfoService.selectConcernInfoByIdforMerber(Long.parseLong(expertInfo.get("expert_id").toString()));
//                        if (concernInfolist != null) {
//                            expertInfo.put("fansCount", concernInfolist.size());
//                        } else {
//                            expertInfo.put("fansCount", 0);
//                        }
//
//                        //查询擅长作物，和领域
//                        if (expertInfo.get("crop_code") != null) {
//                            String[] cropCodelist = expertInfo.get("crop_code").toString().split(","); //作物
//                            List<String> cropDictName = new ArrayList<>();
//                            for (String cropCodeid : cropCodelist) {
//                                if (!StringUtils.isEmpty(cropCodeid)) {
//                                    CropDict cropDict = cropDictService.selectCropDictById(Long.parseLong(cropCodeid));//查询作物
//                                    if (cropDict != null) {
//                                        cropDictName.add(cropDict.getCropName());
//                                    }
//                                }
//                            }
//                            expertInfo.put("cropCodeName", cropDictName);
//                        } else {
//                            expertInfo.put("cropCodeName", new ArrayList());
//                        }
//
//                        if (expertInfo.get("region_code") != null) {
//                            String[] regionCodeList = expertInfo.get("region_code").toString().split(","); //领域
//                            List<String> regionCodName = new ArrayList<>();
//                            for (String regionCodeid : regionCodeList) {
//                                if (!StringUtils.isEmpty(regionCodeid)) {
//                                    CropDict regionCode = cropDictService.selectCropDictById(Long.parseLong(regionCodeid));//查询作物
//                                    if (regionCode != null) {
//                                        regionCodName.add(regionCode.getCropName());
//                                    }
//                                }
//                            }
//                            expertInfo.put("regionCodName", regionCodName);
//                        } else {
//                            expertInfo.put("regionCodName", new ArrayList());
//                        }
//
//                        dataList.add(expertInfo);
//                    }
//                    if (concernInfo.getTargetType() == ConcernInfoConstants.CONCERN_INFO_FARMER) { //关注农友
//                        MemberInfo memberInfo = memberInfoService.getMemberInfoById(concernInfo.getTargetId());
//                        if (memberInfo != null) {
//                            MemberInfoList.add(memberInfo);
//                        }
//                    }
//                    if (concernInfo.getTargetType() == ConcernInfoConstants.CONCERN_INFO_CROP) { //关注作物
//                        CropDict cropdict = cropDictService.selectCropDictById(concernInfo.getTargetId());
//                        if (cropdict != null) {
//                            cropDictList.add(cropdict);
//                        }
//                    }
//                    if (concernInfo.getTargetType() == ConcernInfoConstants.CONCERN_INFO_SECTION) { //关注板块
//                        SectionDict sectionDict = sectionDictService.selectSectionDictById(concernInfo.getTargetId());
//                        if (sectionDict != null) {
//                            sectionDictList.add(sectionDict);
//                        }
//                    }
//                }
//            }
//
//            result.put("expertInfoList", dataList);
//            result.put("cropDictList", cropDictList);
//            result.put("MemberInfoList", MemberInfoList);
//            result.put("sectionDictList", sectionDictList);
//
//            return AjaxResult.success(result);
//        }catch (Exception e){
//            log.error(e.getMessage(),e);
//            return AjaxResult.error(e.getMessage());
//        }
//
//    }
//
//
//    /**
//     * 修改个人信息
//     *
//     * @return
//     */
//    @ApiOperation("修改个人信息")
//    @PostMapping(value = "/updateMemberInfo")
//    public AjaxResult updateMemberInfo(@RequestBody MemberInfoVo memberInfo) {
//        if (StringUtils.isEmpty(memberInfo.getHeadImg())) {
//            return AjaxResult.error("头像不能为空");
//        }
//        int row = memberInfoService.updateMemberInfoAndGrowExper(memberInfo);
//        return toAjax(row);
//    }
//
//
//    /**
//     * 添加意见反馈
//     *
//     * @return
//     */
//    @ApiOperation("添加意见反馈")
//    @GetMapping(value = "/addAppSuggest")
//    public AjaxResult addAppSuggest(@RequestBody @Validated AppSuggest AppSuggest, BindingResult result) {
//        if (result.hasErrors()) {
//            return AjaxResult.error(result.getFieldError().getDefaultMessage());
//        }
//        return toAjax(appSuggestService.addAppSuggest(AppSuggest));
//    }
//
//    @ApiOperation("更新首页背景图")
//    @PostMapping("updateBackgroundImg")
//    @ApiImplicitParam(name = "token", value = "token", paramType = "header")
//    public AjaxResult updateBackgroundImg(@RequestBody @Valid MemberBackgroundVo backgroundVo, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return AjaxResult.error(result.getFieldError().getDefaultMessage());
//        }
//
//        MemberDict dict = memberDictService.getDictByMemberIdAndType(tokenService.getLoginUser().getUserid(), MemberDictConstants.HOME_PAGE_BACKGROUND_IMG);
//
//        if (dict != null) {
//            dict.setDictValue(backgroundVo.getImg());
//            dict.setUpdateTime(new Date());
//            memberDictService.updateMemberDict(dict);
//        } else {
//            dict = new MemberDict();
//            dict.setMemberId(tokenService.getLoginUser().getUserid());
//            dict.setDictType(MemberDictConstants.HOME_PAGE_BACKGROUND_IMG);
//            dict.setDictValue(backgroundVo.getImg());
//            dict.setCreateTime(new Date());
//            memberDictService.addMemberDict(dict);
//        }
//
//        return AjaxResult.success();
//    }
//
//}
