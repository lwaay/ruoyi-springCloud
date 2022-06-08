package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.orders.domain.OdMemberVisit;
import com.sinonc.orders.domain.OdMemberVisitDescription;
import com.sinonc.orders.service.IOdMemberVisitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 17:05
 */
@Api(tags = "我的足迹接口")
@RequestMapping("/api/good/visit")
@RestController
public class ApiGoodVisitController extends BaseController {

    @Autowired
    private IOdMemberVisitService visitService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("用户足迹列表接口")
    @GetMapping("/list")
    public AjaxResult list(OdMemberVisit memberVisit){
        if(StringUtils.isNotNull(memberVisit.getPageNum()) && StringUtils.isNotNull(memberVisit.getPageSize())){
            PageHelper.startPage(memberVisit.getPageNum(), memberVisit.getPageSize(), "visit_time desc");
        }else{
            PageHelper.orderBy("visit_time desc");
        }
        memberVisit.setUserId(tokenService.getLoginUser().getWxUserConsume().getId());
        List<OdMemberVisitDescription> memberVisitList = visitService.selectOdMemberVisitDescriptionList(memberVisit);
        Map<String, Object> ret = new HashMap<>(3);
        ret.put("rows", memberVisitList);
        ret.put("total",new PageInfo(memberVisitList).getTotal());
        ret.put("hasNext",new PageInfo(memberVisitList).isHasNextPage());
        return AjaxResult.success(ret);
    }

    @ApiOperation("用户足迹删除接口")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(visitService.deleteOdMemberVisitByIds(ids));
    }
}
