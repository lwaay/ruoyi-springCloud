package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.SysConstants;
import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.domain.ExpertDynamicComment;
import com.sinonc.agriculture.dto.ExpertDynamicDto;
import com.sinonc.agriculture.service.ExpertDynamicDtoService;
import com.sinonc.agriculture.service.ExpertDynamicService;
import com.sinonc.agriculture.service.ExpertInfoService;
import com.sinonc.agriculture.vo.DynamicTypeVo;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api
@RequestMapping("api/agriculture/expertDynamic")
@RestController
public class ApiExpertDynamicController extends BaseController {

    @Autowired
    private ExpertDynamicService expertDynamicService;

    @Autowired
    private ExpertDynamicDtoService expertDynamicDtoService;

    @Autowired
    private ExpertInfoService expertInfoService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("add")
    @ApiOperation("添加专家动态")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    public AjaxResult addDynamic(@RequestBody @Valid ExpertDynamic expertDynamic, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        LoginUser loginUser = tokenService.getLoginUser();

        if (ObjectUtils.isEmpty(loginUser) || !isExpert(loginUser.getUserid())) {
            return AjaxResult.error("无发表权限");
        }

        Long memberId = loginUser.getUserid();
        expertDynamic.setMemberId(memberId);

        return toAjax(expertDynamicService.addExpertDynamic(expertDynamic));
    }

    @PostMapping("list")
    @ApiOperation("获取专家动态列表")
    @ApiImplicitParam(name = "token", value = "token(可选)")
    public AjaxResult listDynamic(@RequestBody DynamicTypeVo dynamicTypeVo) {

        ExpertDynamic expertDynamic = new ExpertDynamic();

        expertDynamic.setSectionId(dynamicTypeVo.getSectionId());
        expertDynamic.setCropId(dynamicTypeVo.getCropId());
        expertDynamic.setMemberId(dynamicTypeVo.getMemberId());
        expertDynamic.setTitle(dynamicTypeVo.getTitle());

        startPage();
        List<ExpertDynamicDto> expertDynamicDtos = expertDynamicDtoService.listExpertDynamicDto(!ObjectUtils.isEmpty(tokenService.getLoginUser()), expertDynamic);

        PageInfo<ExpertDynamicDto> page = new PageInfo<>(expertDynamicDtos);

        AjaxResult success = AjaxResult.success(page.getList());
        success.put("hasNextPage", page.isHasNextPage());

        return success;
    }

    @ApiOperation("获取专家动态详情")
    @GetMapping("detail/{dynamicId}")
    @ApiImplicitParam(name = "token", value = "token(可选)")
    public AjaxResult getDynamicDetail(@PathVariable Long dynamicId) {
        return AjaxResult.success(expertDynamicDtoService.getExpertDynamicDetailDto(!ObjectUtils.isEmpty(tokenService.getLoginUser()), dynamicId));
    }

    @PostMapping("addLike/{dynamicId}")
    @ApiOperation("专家动态点赞")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    public AjaxResult addLike(@PathVariable Long dynamicId) {
        return toAjax(expertDynamicService.addLike(tokenService.getLoginUser().getUserid(), dynamicId));
    }

    @ApiOperation("添加动态评论")
    @PostMapping("addComment")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    public AjaxResult addComment(@RequestBody @Valid ExpertDynamicComment comment, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        if (StringUtils.isEmpty(comment.getSysName())) {
            comment.setSysName(SysConstants.SYS_NAME);
        }

        comment.setMemberId(tokenService.getLoginUser().getUserid());
        comment.setNikeName(tokenService.getLoginUser().getUsername());

        return toAjax(expertDynamicService.addComment(comment));
    }


    @ApiOperation("更新专家动态")
    @PostMapping("update")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    public AjaxResult updateDynamic(@RequestBody ExpertDynamic expertDynamic, BindingResult result) {

        if (result.hasErrors()) {
            return AjaxResult.error(result.getFieldError().getDefaultMessage());
        }

        if (expertDynamic.getDynamicId() == null) {
            return AjaxResult.error("dynamicId不能为空");
        }

        ExpertDynamic expertDynamicById = expertDynamicService.getExpertDynamicById(expertDynamic.getDynamicId());

        //验证身份
        if (!expertDynamicById.getMemberId().equals(tokenService.getLoginUser().getUserid())) {
            return AjaxResult.error("无操作权限");
        }

        return toAjax(expertDynamicService.updateExpertDynamic(expertDynamic));
    }

    @PostMapping("delete/{dynamicId}")
    @ApiOperation("删除动态")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    public AjaxResult deleteDynamic(@PathVariable Long dynamicId) {

        ExpertDynamic expertDynamicById = expertDynamicService.getExpertDynamicById(dynamicId);

        //验证身份
        if (!expertDynamicById.getMemberId().equals(tokenService.getLoginUser().getUserid())) {
            return AjaxResult.error("无操作权限");
        }

        return toAjax(expertDynamicService.deleteExpertDynamicById(dynamicId));
    }

    boolean isExpert(Long userId){
        Map<String, Object> stringObjectMap = expertInfoService.selectExpertInfoMemberId(userId);
        if(stringObjectMap == null || stringObjectMap.size() == 0){
            return false;
        }
        String status = stringObjectMap.get("verify_status").toString();
        if("1".equals(status)){
            return true;
        }else{
            return false;
        }
    }
}
