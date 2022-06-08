package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.SectionDictService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "主题板块API接口")
@RestController
@RequestMapping("api/agriculture/sectionDict")
public class ApiSectionDictController extends BaseController {

    @Autowired
    private SectionDictService sectionDictService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("获取版块列表")
    @GetMapping("list")
    public AjaxResult getSectionDictList() {
        return AjaxResult.success(sectionDictService.selectSectionDictList(new SectionDict()));
    }


    /**
     * 查询版块分页列表
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("查询版块分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query")
    })
    @RequestMapping(value = "/selectSectionDictList")
    public AjaxResult selectSectionDictList(Integer pageNum,Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }

        PageHelper.startPage(pageNum, pageSize);
        Long memberId = tokenService.getLoginUser().getUserid();
        PageInfo pageInfo = new PageInfo<>(sectionDictService.selectAllSectionDictList(memberId));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    @ApiOperation("添加关注版块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "sectionId", value = "主题ID", required = true, paramType = "query")
    })
    @RequestMapping("concernSection/{sectionId}")
    public AjaxResult concernSection(@PathVariable Long sectionId) {

        ConcernInfo concernInfo = new ConcernInfo();

        concernInfo.setCreateTime(new Date());
        concernInfo.setMemberId(tokenService.getLoginUser().getUserid());
        concernInfo.setTargetId(sectionId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_SECTION);

        return toAjax(concernInfoService.insertConcernInfo(concernInfo, concernInfo.getMemberId()));
    }

    @ApiOperation("取消关注版块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "sectionId", value = "主题ID", required = true, paramType = "query")
    })
    @RequestMapping("concernSection/cancel/{sectionId}")
    public AjaxResult cancelConcern(@PathVariable Long sectionId) {

        ConcernInfo concernInfo = new ConcernInfo();

        concernInfo.setMemberId(tokenService.getLoginUser().getUserid());
        concernInfo.setTargetId(sectionId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_SECTION);

        return toAjax(concernInfoService.deleteConcernInfo(concernInfo));
    }


}
