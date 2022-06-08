package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.constants.MemberInfoConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.mapper.SectionDictMapper;
import com.sinonc.agriculture.service.*;
import com.sinonc.agriculture.vo.ExpertInfoModifyVo;
import com.sinonc.agriculture.vo.ExpertInfoVo;
import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

/**
 * 专家信息Api接口
 */
@Api(tags = "专家信息Api接口")
@RestController
@RequestMapping("api/agriculture/expertinfo")
@Slf4j
public class ApiExpertInfoController extends BaseController {

    @Autowired
    private ExpertInfoService expertInfoService;
    @Autowired
    private ConcernInfoService concernInfoService;
    @Autowired
    private CropDictService cropDictService;
    @Autowired
    private MemberDictService memberDictService;
    @Autowired
    private AnswerOptionService answerOptionService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private GrowExperService growExperService;
    @Autowired
    private ExpertInfoModifyService expertInfoModifyService;
    @Resource
    private SectionDictMapper sectionDictMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RemoteAreaCodeService areaCodeService;


    /**
     * 我关注的专家列表
     *
     * @param
     * @return
     */
    @ApiOperation("我关注的专家列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query")
    })
    @GetMapping("selectexpertinfo")
    public AjaxResult selectExpertInfo() {
        List<Map<String, Object>> list = new ArrayList<>();
        //根据会员id查询关注的专家
        List<ConcernInfo> ConcernInfoList = concernInfoService.getConcernInfoByMemberIdByExpertInfo(tokenService.getLoginUser().getUserid());
        if (ConcernInfoList != null && ConcernInfoList.size() > 0) {
            for (ConcernInfo concernInfo : ConcernInfoList) {
                //根据专家id查询专家
                Map<String, Object> map = expertInfoService.selectExpertInfoMemberId(concernInfo.getTargetId());
                if(Optional.ofNullable(map).isPresent()){
                    list.add(map);
                }
            }
        }

        if (list.size() > 0) {
            for (Map<String, Object> map : list) {
                String str = map.get("expert_id").toString(); //获取关注目标id
                //根据专家id查询专家关注表查询关注数
                List<Map<String, Object>> concernInfolist = concernInfoService.selectConcernInfoByIdforMerber(Long.parseLong(str));
                if (concernInfolist != null && concernInfolist.size() > 0) {
                    map.put("fansCount", concernInfolist.size());
                }
                //查询擅长作物，和领域
                if (map.get("crop_code") != null) {
                    String[] cropCodelist = map.get("crop_code").toString().split(",");
                    List<String> cropDictName = new ArrayList<>();
                    for (String cropCodeid : cropCodelist) {
                        if (!StringUtils.isEmpty(cropCodeid)) {
                            CropDict cropDict = cropDictService.selectCropDictById(Long.parseLong(cropCodeid));//查询作物
                            if (cropDict != null) {
                                cropDictName.add(cropDict.getCropName());
                            }
                        }
                    }
                    map.put("cropCodeName", cropDictName);
                }
                if (map.get("region_code") != null) {
                    String[] regionCodeList = map.get("region_code").toString().split(",");
                    List<String> regionCodName = new ArrayList<>();
                    for (String regionCodeid : regionCodeList) {
                        if (!StringUtils.isEmpty(regionCodeid)) {
                            CropDict regionCode = cropDictService.selectCropDictById(Long.parseLong(regionCodeid));//查询作物
                            if (regionCode != null) {
                                regionCodName.add(regionCode.getCropName());
                            }
                        }
                    }
                    map.put("regionCodName", regionCodName);
                }
            }
        }
        return AjaxResult.success(list);
    }


    /**
     * 根据专家id查询专家详情
     *
     * @param expertInfoId
     * @return
     */
    @ApiOperation("根据专家id查询专家详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expertInfoId", value = "专家ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "会员ID，0;//查询全部动态,", required = true, paramType = "query")
    })
    @GetMapping("selectexpretinfobyid")
    public AjaxResult selectExpretInfoById(Long expertInfoId, Long memberId) throws Exception {
        Map<String, Object> result = new HashMap<>();
        expertInfoService.selectExpretInfoDetailById(result, expertInfoId, memberId);
        return AjaxResult.success(result);
    }

//    /**
//     * 只查果技员的信息
//     *
//     * @param expertId 专家id
//     * @return 果技员
//     */
//    @ApiOperation("根据果技员id查询详情")
//    @ApiImplicitParam(name = "expertId", value = "专家", required = true)
//    @GetMapping("/{expertId}")
//    public AjaxResult selectExpertInfoByIdAndType(@PathVariable(value = "expertId") long expertId) {
//        return AjaxResult.success(expertInfoService.selectExpertInfoByIdAndType(expertId));
//    }

    /**
     * 只查果技员的信息
     *
     * @return 果技员
     */
    @ApiOperation("根据果技员id查询详情")
    @ApiImplicitParam(name = "expertId", value = "专家", required = true)
    @GetMapping("detail")
    public AjaxResult selectExpertInfoByIdAndType() {
        ExpertInfo expertInfo = expertInfoService.selectExpertInfoByUserId(tokenService.getLoginUser().getUserid());
        ExpertInfoWithAreaName expertInfoP = new ExpertInfoWithAreaName();
        BeanUtils.copyProperties(expertInfo, expertInfoP);
        expertInfoP.setAreaName(areaCodeService.changeAddressName(expertInfo.getAreaCode()).getData());
        return AjaxResult.success(expertInfoP);
    }

    /**
     * 申请专家认证
     *
     * @param expertInfo
     * @return
     */
    @ApiOperation("申请专家认证")
    @PostMapping("applyexpret")
    public AjaxResult applyExpret(@RequestBody ExpertInfoVo expertInfo) {
        try {
            Long memberId = tokenService.getLoginUser().getUserid();
            boolean rs = expertInfoService.isApplyExpret(memberId);
            if (rs) {
                return toAjax(rs);
            }

            expertInfo.setMemberId(memberId);


            if (StringUtils.isEmpty(expertInfo.getRealName())) {
                return AjaxResult.error("姓名不能为空");
            }
            if (StringUtils.isEmpty(expertInfo.getAreaCode())) {
                return AjaxResult.error("区域不能为空");
            }
            if (StringUtils.isEmpty(expertInfo.getCropCode())) {
                return AjaxResult.error("擅长作物不能为空");
            }
            if (StringUtils.isEmpty(expertInfo.getRegionCode())) {
                return AjaxResult.error("擅长领域不能为空");
            }
            if (StringUtils.isEmpty(expertInfo.getIdenCard())) {
                return AjaxResult.error("身份证号码不能为空");
            }
            int row = expertInfoService.insertExpertInfo(expertInfo);
            return toAjax(row);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return AjaxResult.error(e.getMessage(), e);
        }

    }

    @ApiOperation("获取专家认证状态")
    @GetMapping("auditStatus")
    public R<Map<String, Object>> expertStatus(Long memberId){
        ExpertInfo expertInfo = expertInfoService.selectExpertInfoByUserId(memberId);
        String status = "1";
        if(expertInfo == null){
            status = "1";
            Map<String, Object> map = new HashMap<>();
            map.put("status", status);
            return R.ok(map);
        }
        switch (expertInfo.getVerifyStatus()){
            case 0:status = "2";break;
            case 1:status = "3";break;
            case 2:status = "4";break;
            default:status = "1";break;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        return R.ok(map);
    }


    /**
     * 修改专家资料
     *
     * @param expertInfoModifyVo
     * @return
     */
    @ApiOperation("修改专家资料")
    @PostMapping("modifyExpretInfo")
    public AjaxResult modifyExpretInfo(@RequestBody ExpertInfoModifyVo expertInfoModifyVo) {
        Long memberId = tokenService.getLoginUser().getUserid();
        if (expertInfoModifyVo.getExpertIdP() == null) {
            return AjaxResult.error("专家ID不能为空。", false);
        }
        expertInfoModifyVo.setMemberId(memberId);
        try {
            expertInfoModifyService.modifyExpertInfoModify(expertInfoModifyVo);
            return AjaxResult.success("提交资料修改成功。", true);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage(), false);
        }
    }


    @ApiOperation("查询是否已经申请专家")
    @ApiImplicitParam(name = "token", value = "token", paramType = "head", required = true)
    @GetMapping("isApplyExpret")
    public AjaxResult isApplyExpret() {
        Long memberId = tokenService.getLoginUser().getUserid();
        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setMemberId(memberId);
        List<ExpertInfo> list = expertInfoService.selectExpertInfoList(expertInfo);
        AjaxResult success = AjaxResult.success();
        if (list != null && list.size() > 0) {
            //已注册
            ExpertInfo expertInfoRs = list.get(0);
            success.put("data", true);
            success.put("VerifyStatus", String.valueOf(expertInfoRs.getVerifyStatus()));
        } else {
            //未注册
            success.put("data", false);
            success.put("VerifyStatus", "");
        }

        return success;
    }

    /**
     * 查询专家分页列表
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("查询专家分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query")
    })
    @GetMapping("selectExpertInfoList")
    public AjaxResult selectExpertInfoList(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }

        if (pageSize == null) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        ExpertInfo expertInfo = new ExpertInfo();
        if(tokenService.getLoginUser() != null){
            expertInfo.setMemberId(tokenService.getLoginUser().getUserid());
        }
        PageInfo pageInfo = new PageInfo<>(expertInfoService.selectAllExpertInfoList(expertInfo));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo);
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    /**
     * 查询专家无分页列表
     *
     * @param cropCode
     * @return
     */
    @ApiOperation("查询专家无分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "cropCode", value = "作物ID，可以不传", paramType = "query"),
            @ApiImplicitParam(name = "sunCropId", value = "作物ID，可以不传", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "realName", value = "姓名", paramType = "query")
    })
    @GetMapping("queryExpertInfoNoPageList")
    public AjaxResult queryExpertInfoNoPageList(String realName,String cropCode,Long sunCropId,Integer pageNum, Integer pageSize) {

        ExpertInfoVo expertInfoVo = new ExpertInfoVo();
        expertInfoVo.setCropCode(cropCode);
        expertInfoVo.setRoleType(MemberInfoConstants.ROLE_EXPERT);
        expertInfoVo.setRealName(realName);

        Long parentCropId=null;

        if(sunCropId!=null){
            CropDict cropDict=cropDictService.selectCropDictById(sunCropId);
            if(cropDict!=null){
                parentCropId=cropDict.getParentId();
                expertInfoVo.setParentCropId(parentCropId);
            }else {
                return AjaxResult.error("无cropId为:"+sunCropId+"的作物信息");
            }
        }
        if(pageNum!=null&&pageSize!=null){

            PageHelper.startPage(pageNum, pageSize);
            PageInfo pageInfo = new PageInfo<>(expertInfoService.selectExpertInfoNoPageListFilter(expertInfoVo));

            AjaxResult success = AjaxResult.success();
            success.put("data", pageInfo.getList());
            success.put("totalPage", pageInfo.getPages());
            success.put("hasNext", pageInfo.isHasNextPage());
            success.put("hasPre", pageInfo.isHasPreviousPage());

            return success;

        }else {
            List list = expertInfoService.selectExpertInfoNoPageListFilter(expertInfoVo);

            AjaxResult success = AjaxResult.success();
            success.put("data", list);

            return success;
        }


    }


    /**
     * 添加专家关注
     *
     * @param expertInfoId
     * @return
     */
    @ApiOperation("添加专家关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "expertInfoId", value = "专家ID", required = true, paramType = "query")
    })
    @GetMapping("addOwnExpertInfo")
    public AjaxResult addOwnExpertInfo(Long expertInfoId) {
        Long memberId = tokenService.getLoginUser().getWxUser().getId();
        if (expertInfoId == null) {
            return AjaxResult.error("expertInfoId不能为空。");
        }
        if (memberId == null) {
            return AjaxResult.error("memberId不能为空。");
        }
        try {
            String rs = concernInfoService.addOwmConcernInfo(expertInfoId, memberId);
            return AjaxResult.success(rs);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 取消专家关注
     *
     * @param expertInfoId
     * @return
     */
    @ApiOperation("取消专家关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "expertInfoId", value = "专家ID", required = true, paramType = "query")
    })
    @GetMapping("cancelOwnExpertInfo")
    public AjaxResult cancelOwnExpertInfo(Long expertInfoId) {
        Long memberId = tokenService.getLoginUser().getWxUser().getId();
        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setMemberId(memberId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
        concernInfo.setTargetId(expertInfoId);
        int row = concernInfoService.cancelOwnExpertInfo(concernInfo);

        return AjaxResult.success(row);
    }


    /**
     * @param memberId
     * @return
     * @throws ParseException
     */
    @ApiOperation("根据会员ID查询会员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query")
    })
    @GetMapping("getMemberInfoById")
    public AjaxResult getMemberInfoById(Long memberId) {

        Map<String, Object> result = new HashMap<>();


        if (memberId == null || memberId == 0) {
            memberId = tokenService.getLoginUser().getUserid();
        }

        //对应的会员
        MemberInfo memberInfo = memberInfoService.getMemberInfoById(memberId);
        if (memberInfo != null && memberInfo.getHeadImg() == null) {
            memberInfo.setHeadImg("");
        }
        result.put("memberInfo", memberInfo);

        GrowExper growExper = new GrowExper();
        growExper.setMemberIdP(memberId);
        List growExperList = growExperService.getGrowExperList(growExper);
        result.put("growExperList", growExperList);

        //关注主题
        ConcernInfo concernInfo = new ConcernInfo();
        concernInfo.setMemberId(memberId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_SECTION);
        List<ConcernInfo> sectionList = concernInfoService.selectConcernInfoList(concernInfo);
        result.put("sectionSize", sectionList.size());

        //关注农友数量
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
        List<ConcernInfo> memberList = concernInfoService.selectConcernInfoList(concernInfo);
        result.put("memberSize", memberList.size());

        //粉丝数量
        ConcernInfo concernInfoFans = new ConcernInfo();
        concernInfoFans.setTargetId(memberId);
        concernInfoFans.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
        List<ConcernInfo> fansList = concernInfoService.selectConcernInfoList(concernInfo);
        result.put("fansList", fansList.size());

        //回答被点赞数量
        AnswerOption option = new AnswerOption();
        option.setMemberId(memberId);
        List<AnswerOption> answerOptionList = answerOptionService.selectAnswerOptionPraise(option);
        result.put("praiseSize", answerOptionList.size());


        //浏览用户数
        result.put("browse", 0);

        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setMemberId(memberId);
        List<ExpertInfo> expertInfoList = expertInfoService.selectExpertInfoList(expertInfo);
        if (expertInfoList != null && expertInfoList.size() > 0) {
            ExpertInfo tempExpertInfo = expertInfoList.get(0);
            result.put("expertInfo", tempExpertInfo);
            //查询擅长主题名称
            queryRegionCodName(result, tempExpertInfo.getRegionCode());
            //查询擅长作物名称
            queryCropCodeName(result, tempExpertInfo.getCropCode());
        } else {
            result.put("expertInfo", new ExpertInfo());
        }

        MemberDict background = memberDictService.getDictByMemberIdAndType(memberId, MemberDictConstants.HOME_PAGE_BACKGROUND_IMG);

        if (background != null) {
            result.put("backgroundImg", background.getDictValue());
        } else {
            result.put("backgroundImg", "http://sinonc-xfqc-pro.oss-cn-hangzhou.aliyuncs.com/a4683863-be9f-4e6a-acdc-b502221c632d.jpg");
        }

        return AjaxResult.success(result);
    }

    @ApiOperation("专家数量")
    @GetMapping("count")
    public AjaxResult count(){
        return AjaxResult.success(expertInfoService.selectExpertCount());
    }

    private void queryCropCodeName(Map<String, Object> result, String cropCode) {
        if (cropCode != null) {
            String[] cropCodeList = cropCode.split(",");
            List<String> cropCodeNameList = new ArrayList<>();
            for (String cropCodeid : cropCodeList) {
                if (!StringUtils.isEmpty(cropCodeid)) {
                    CropDict cropDict = cropDictService.selectCropDictById(Long.valueOf(cropCodeid));
                    cropCodeNameList.add(cropDict.getCropName());
                }
            }
            result.put("cropCodeName", cropCodeNameList);
        } else {
            result.put("cropCodeName", new ArrayList());
        }
    }


    private void queryRegionCodName(Map<String, Object> result, String regionCode) {
        if (regionCode != null) {
            String[] regionCodeList = regionCode.split(",");
            List<String> regionCodName = new ArrayList<>();
            for (String regionCodeid : regionCodeList) {
                if (!StringUtils.isEmpty(regionCodeid)) {
                    SectionDict sectionDict = sectionDictMapper.selectSectionDictById(Long.valueOf(regionCodeid));
                    if (null != sectionDict) {
                        regionCodName.add(sectionDict.getSectionName());
                    }

                }
            }
            result.put("regionCodName", regionCodName);
        } else {
            result.put("regionCodName", new ArrayList());
        }
    }


//    /**
//     * 查询农友分页列表
//     *
//     * @param pageNum
//     * @return
//     */
//    @ApiOperation("查询农友分页列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
//            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
//            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query")
//    })
//    @GetMapping(value = "/selectMemberInfoList")
//    public AjaxResult selectMemberInfoList(Integer pageNum, Integer pageSize) {
//        if (pageNum == null) {
//            pageNum = 1;
//        }
//
//        if (pageSize == null) {
//            pageSize = 5;
//        }
//
//        PageHelper.startPage(pageNum, pageSize);
//        MemberInfo memberInfo = new MemberInfo();
//        Long memberId = tokenService.getLoginUser().getUserid();
//        PageInfo pageInfo = new PageInfo<>(expertInfoService.selectAllMemberInfoList(Long memberId));
//
//        AjaxResult success = AjaxResult.success();
//        success.put("data", pageInfo.getList());
//        success.put("totalPage", pageInfo.getPages());
//        success.put("hasNext", pageInfo.isHasNextPage());
//        success.put("hasPre", pageInfo.isHasPreviousPage());
//
//        return success;
//    }

//    /**
//     * 添加农友关注
//     *
//     * @param memberInfoId
//     * @return
//     */
//    @ApiOperation("添加农友关注")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
//            @ApiImplicitParam(name = "memberInfoId", value = "会员ID", required = true, paramType = "query")
//    })
//    @GetMapping(value = "/addOwnMemberInfo")
//    public AjaxResult addOwnMemberInfo(Long memberInfoId) {
//        if (memberInfoId == null) {
//            return AjaxResult.error("会员ID不能为空");
//        }
//        Long memberId = tokenService.getLoginUser().getUserid();
//        try {
//            String msg = concernInfoService.addOwnMemberInfo(memberInfoId, memberId);
//            return AjaxResult.success(msg);
//        } catch (Exception e) {
//            return AjaxResult.error(e.getMessage());
//        }
//    }



}
