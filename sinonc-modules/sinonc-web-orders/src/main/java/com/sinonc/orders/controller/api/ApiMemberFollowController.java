package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.constant.HttpStatus;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.orders.domain.MemberFollowDescription;
import com.sinonc.orders.domain.OdMemberAttention;
import com.sinonc.orders.event.event.VisitEvent;
import com.sinonc.orders.event.payload.VisitRecord;
import com.sinonc.orders.service.IOdMemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 10:06
 */
@Api(tags = {"我的收藏接口", "商品收藏"})
@RequestMapping("/api/follow")
@RestController
public class ApiMemberFollowController extends BaseController {
    @Autowired
    private IOdMemberAttentionService odMemberAttentionService;

    @Autowired
    private TokenService tokenService;


    @ApiOperation("用户添加关注接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attentionType", value = "关注目标类型", required = true, dataType = "number", paramType = "body"),
            @ApiImplicitParam(name = "targetId", value = "关注目标ID", required = true, dataType = "number", paramType = "body")
    })
    @PutMapping
    public AjaxResult add(@RequestBody OdMemberAttention odMemberAttention){
        Long userId = tokenService.getLoginUser().getWxUserConsume().getId();
        odMemberAttention.setUserId(userId);
        return toAjax(odMemberAttentionService.insertOdMemberAttention(odMemberAttention));
    }

    @ApiOperation("用户取消关注接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "关注ID", required = false, dataType = "number", paramType = "body"),
            @ApiImplicitParam(name = "attentionType", value = "关注目标类型", required = false, dataType = "number", paramType = "body"),
            @ApiImplicitParam(name = "targetId", value = "关注目标ID", required = false, dataType = "number", paramType = "body")
    })
    @DeleteMapping
    public AjaxResult del(@RequestBody OdMemberAttention odMemberAttention){
        Long userId = tokenService.getLoginUser().getWxUserConsume().getId();
        odMemberAttention.setUserId(userId);
        return toAjax(odMemberAttentionService.deleteOdMemberAttentionByAnyway(odMemberAttention));
    }


    @ApiOperation("用户关注列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attentionType", value = "关注目标类型", required = false, dataType = "number", paramType = "body"),
    })
    @GetMapping("/list")
    public AjaxResult query(OdMemberAttention odMemberAttention){
        Long userId = tokenService.getLoginUser().getWxUserConsume().getId();
        odMemberAttention.setUserId(userId);
        odMemberAttention.setStatus(0);
        if(StringUtils.isNotNull(odMemberAttention.getPageNum()) && StringUtils.isNotNull(odMemberAttention.getPageSize())){
            PageHelper.startPage(odMemberAttention.getPageNum(), odMemberAttention.getPageSize(), "create_time desc");
        }else{
            PageHelper.orderBy("create_time desc");
        }
        List<MemberFollowDescription> memberAttentionList = odMemberAttentionService.selectOdMemberFollowDescriptionList(odMemberAttention);
        Map<String, Object> ret = new HashMap<>(3);
        ret.put("rows", memberAttentionList);
        ret.put("total",new PageInfo(memberAttentionList).getTotal());
        ret.put("hasNext",new PageInfo(memberAttentionList).isHasNextPage());
        return AjaxResult.success(ret);
    }
}
