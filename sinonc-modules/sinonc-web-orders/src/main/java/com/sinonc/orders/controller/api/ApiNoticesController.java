package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.OdNotice;
import com.sinonc.orders.service.IOdNoticeService;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

/**
 * 通知消息接口Controller
 * @author Administrator
 */
@Api(tags = "系统公告")
@RestController
@RequestMapping("api/orders/notices")
public class ApiNoticesController extends BaseController {

    @Autowired
    private IOdNoticeService noticeService;

    private final static String NOTICE_PACKAGE_KEY = "baise:notice:";

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询所属的新闻通知列表
     * @return
     */
    @ApiOperation(value = "获取系统公告列表")
    @RequestMapping(value = "list")
    public PageDataInfo list(@RequestBody OdNotice notice) {
        Integer pageNum = notice.getPageNum() == null || notice.getPageNum()<1?1:notice.getPageNum();
        Integer pageSize = notice.getPageSize() == null || notice.getPageSize()<1?10:notice.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<OdNotice> list = noticeService.selectNoticeList(notice);
        return getPageTable(list);
    }

    @ApiOperation(value = "获取未读公告数")
    @GetMapping(value = "countNotRead")
    public AjaxResult selectNoticeByNotRead(){
        LoginUser loginUser = tokenService.getLoginUser();
        if (!Optional.ofNullable(loginUser).isPresent()){
            return AjaxResult.error("用户未登录");
        }
        return AjaxResult.success(noticeService.selectNoticeByNotRead(loginUser.getUserid()));
    }


    /**
     * 根据通知ID查询详情信息
     *
     * @param noticeId
     * @return
     */
    @ApiOperation(value = "获取系统公告明细")
    @GetMapping(value = "/get/{noticeId}")
    public AjaxResult get(@PathVariable Long noticeId) {
        if (noticeId == null || noticeId == 0) {
            return AjaxResult.error("通知ID字符串不能为空");
        }
        OdNotice notice =noticeService.selectNoticeById(noticeId);
        if (!Optional.ofNullable(notice).isPresent()){
            return AjaxResult.error("查询通知信息失败");
        }
        redisService.redisTemplate.opsForSet().add(NOTICE_PACKAGE_KEY+noticeId,SecurityUtils.getUserId()+"");
        return AjaxResult.success(notice);
    }

    /**
     * 修改通知公告
     */
    @ApiOperation(value = "系统公告已读状态")
    @PostMapping("/updateNotice")
    public AjaxResult edit(@RequestBody OdNotice notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

}
