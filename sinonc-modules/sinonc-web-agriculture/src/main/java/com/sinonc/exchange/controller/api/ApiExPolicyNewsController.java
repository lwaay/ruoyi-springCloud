package com.sinonc.exchange.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.service.PolicyNewsService;
import com.sinonc.exchange.utils.SysSignUtil;
import com.sinonc.exchange.vo.PolicyNewsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "外部系统新闻接口")
@RequestMapping("api/agriculture/exchange/policynews")
@RestController
@Slf4j
public class ApiExPolicyNewsController extends BaseController {

    @Autowired
    private PolicyNewsService policyNewsService;

    @ApiOperation("查询新闻列表")
    @PostMapping(value = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableDataInfo list(@RequestBody PolicyNewsVo policyNewsVo) {

        //验证签名
        String sign = SysSignUtil.getSign(policyNewsVo);

        if (!sign.equals(policyNewsVo.getSign())) {
            TableDataInfo rspData = new TableDataInfo();
            return rspData;
        }

        PageHelper.startPage(policyNewsVo.getPageNum(), policyNewsVo.getPageSize());
        List<PolicyNews> list = policyNewsService.selectPolicyNewsListByPolicyNewsVo(policyNewsVo);
        return getDataTable(list);
    }

    @ApiOperation("新增新闻信息")
    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult addSave(@RequestBody PolicyNews policyNews) {
        //验证签名
        String sign = SysSignUtil.getSign(policyNews);

        if (!sign.equals(policyNews.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        return toAjax(policyNewsService.insertPolicyNews(policyNews));
    }

    @ApiOperation("根据新闻ID查询新闻信息")
    @PostMapping(value = "getPolicyNewsById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult getPolicyNewsById(@RequestBody PolicyNews policyNewsPara) {
        //验证签名
        log.info("policyNewsId:" + policyNewsPara.getNewsId());
        String sign = SysSignUtil.getSign(policyNewsPara);

        if (!sign.equals(policyNewsPara.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        PolicyNews policyNews = policyNewsService.selectPolicyNewsDtoById(policyNewsPara.getNewsId());

        AjaxResult success = AjaxResult.success();
        success.put("policyNews", policyNews);
        return success;
    }


    @ApiOperation("更新新闻信息")
    @PostMapping(value = "edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult editSave(@RequestBody PolicyNews policyNews) {
        //验证签名
        String sign = SysSignUtil.getSign(policyNews);

        if (!sign.equals(policyNews.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        return toAjax(policyNewsService.updatePolicyNews(policyNews));
    }

    @ApiOperation("删除新闻信息")
    @PostMapping(value = "remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult remove(@RequestBody PolicyNews policyNewsPara) {
        //验证签名
        log.info("policyNewsId:" + policyNewsPara.getNewsId());
        Long newsId = policyNewsPara.getNewsId();

        String sign = SysSignUtil.getSign(policyNewsPara);

        if (!sign.equals(policyNewsPara.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        policyNewsService.deletePolicyNewsById(newsId);

        return toAjax(policyNewsService.deletePolicyNewsById(newsId));

    }


}
