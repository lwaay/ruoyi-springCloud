package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.domain.MemberNotice;
import com.sinonc.agriculture.mapper.MemberNoticeMapper;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api
@RestController
@RequestMapping("api/agriculture/notice")
public class ApiMemberNoticeController extends BaseController {

    @Autowired
    private MemberNoticeMapper noticeMapper;

    @Autowired
    private TokenService tokenService;


    @ApiOperation("获取通知列表")
    @GetMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head"),
            @ApiImplicitParam(name = "pageNum", value = "pageNum", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query")
    })
    public AjaxResult getNotices() {

        startPage();
        MemberNotice memberNotice = new MemberNotice();
        memberNotice.setMemberId(tokenService.getLoginUser().getUserid());

        PageHelper.orderBy("create_time desc");
        List<MemberNotice> noticeList = noticeMapper.selectNoticeList(memberNotice);

        PageInfo<MemberNotice> pageInfo = new PageInfo<>(noticeList);

        AjaxResult success = AjaxResult.success(pageInfo.getList());
        success.put("hasNextPage", pageInfo.isHasNextPage());

        return success;
    }

    @ApiOperation("更新为已读信息")
    @PostMapping("read/{noticeId}")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult readNotice(@PathVariable Long noticeId) {

        MemberNotice memberNotice = new MemberNotice();

        memberNotice.setNoticeId(noticeId);
        memberNotice.setMemberId(tokenService.getLoginUser().getUserid());
        memberNotice.setUpdateTime(new Date());
        memberNotice.setReadFlag(1);

        return toAjax(noticeMapper.updateByPrimaryKeySelective(memberNotice));
    }

    @PostMapping("delete/{noticeId}")
    @ApiOperation("删除通知")
    @ApiImplicitParam(name = "token", value = "token", required = true, paramType = "head")
    public AjaxResult delete(@PathVariable Long noticeId) {
        return toAjax(noticeMapper.deleteByPrimaryKey(noticeId));
    }

}
