package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.NewsTypeConstants;
import com.sinonc.agriculture.constants.SysConstants;
import com.sinonc.agriculture.domain.OdAdvertisement;
import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.domain.PolicynewsComment;
import com.sinonc.agriculture.dto.PolicyNewsDto;
import com.sinonc.agriculture.service.OdAdvertisementService;
import com.sinonc.agriculture.service.PolicyNewsService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 政策新闻API接口
 */
@Api(tags = "政策新闻API接口")
@RestController
@RequestMapping("api/agriculture")
public class ApiPolicyNewsController extends BaseController {

    @Autowired
    private PolicyNewsService policyNewsService;

    @Autowired
    private OdAdvertisementService odAdvertisementService;

    @Autowired
    private TokenService tokenService;

    @ApiOperation("获取新闻列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "newsType", value = "新闻类型", paramType = "query"),
            @ApiImplicitParam(name = "sysName", value = "系统名称", paramType = "query")
    })
    @GetMapping(value = "/selectPolicyNews")
    public AjaxResult selectPolicyNews(Integer pageNum, Integer pageSize, String newsType, String sysName) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("issue_time desc");
        PolicyNews policyNews = new PolicyNews();
        if (sysName == null) {
            //轻农业系统
            policyNews.setSysName(SysConstants.SYS_NAME);
        } else {
            policyNews.setSysName(sysName);
        }
        if (newsType == null) {
            policyNews.setNewsType(NewsTypeConstants.ZCTZ);
        } else {
            policyNews.setNewsType(newsType);
        }
        PageInfo pageInfo = new PageInfo<>(policyNewsService.selectPolicyNewsList(policyNews));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    /**
     * 根据新闻ID查询新闻详情
     *
     * @param newsId
     * @return
     */
    @ApiOperation("根据新闻ID查询新闻详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/getPolicyNews")
    public AjaxResult getPolicyNews(Long newsId) {
        if (newsId == null) {
            return AjaxResult.error("newsId不能为空。");
        }
        //用户查看文章时阅读数+1
        policyNewsService.summPolicyNewsReadCount(newsId);
        PolicyNewsDto policyNewsDto = policyNewsService.selectPolicyNewsDtoById(newsId);
        policyNewsDto.setCreateTime(policyNewsDto.getIssueTime());
        return AjaxResult.success(policyNewsDto);
    }

    /**
     * 增加新闻阅读数
     *
     * @param newsId
     * @return
     */
    @ApiOperation("增加新闻阅读数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsId", value = "新闻ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/summPolicyNewsReadCount")
    public AjaxResult summPolicyNewsReadCount(Long newsId) {
        PolicyNews policyNews = policyNewsService.summPolicyNewsReadCount(newsId);
        return AjaxResult.success(policyNews);
    }

    @ApiOperation("获取轮播图")
    @GetMapping(value = "/selectAdvertisementList")
    public AjaxResult selectAdvertisementList() {
        OdAdvertisement odAdvertisement = new OdAdvertisement();
        List<OdAdvertisement> advertisementList = odAdvertisementService.getOdAdvertisementList(odAdvertisement);

        AjaxResult success = AjaxResult.success();
        success.put("data", advertisementList);
        return success;
    }



    /**
     * 政策新闻点赞
     *
     * @param newsId
     * @return
     */
    @ApiOperation("政策新闻点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "newsId", value = "政策新闻ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/addPolicynewsLike")
    public AjaxResult addPolicynewsLike(Long newsId) {

        if (newsId == null) {
            return AjaxResult.error("newsId不能为空");
        }

        Long memberId = tokenService.getLoginUser().getUserid();

        int rows = policyNewsService.addPolicyNewsLike(newsId, memberId);

        if (rows == -1) {
            return AjaxResult.error("一个人对同一个政策新闻只能点赞一次");
        } else {
            return AjaxResult.success(rows);
        }
    }




    @ApiOperation("添加政策新闻评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "policynewsIdP", value = "政策新闻ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "replyContent", value = "评论内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "replyMemberIdP", value = "上级评论ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/addPolicynewsComment")
    public AjaxResult addPolicynewsComment(PolicynewsComment policynewsComment) {
        Long memberId = tokenService.getLoginUser().getUserid();
        policynewsComment.setMemberIdP(memberId);

        int rows = policyNewsService.addPolicynewsComment(policynewsComment);

        return AjaxResult.success(rows);
    }


}
