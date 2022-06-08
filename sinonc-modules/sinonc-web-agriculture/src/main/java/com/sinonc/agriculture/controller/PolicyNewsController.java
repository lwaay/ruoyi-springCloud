package com.sinonc.agriculture.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.agriculture.service.PolicyNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 政策新闻Controller
 *
 * @author ruoyi
 * @date 2022-04-13
 */
@RestController
@RequestMapping("/news")
public class PolicyNewsController extends BaseController {
    @Autowired
    private PolicyNewsService policyNewsService;

    /**
     * 查询政策新闻列表
     */
    @PreAuthorize(hasPermi = "agriculture:news:list")
    @GetMapping("/list")
    public TableDataInfo list(PolicyNews policyNews) {
        startPage();
        List<PolicyNews> list = policyNewsService.selectPolicyNewsList(policyNews);
        return getDataTable(list);
    }

    /**
     * 导出政策新闻列表
     */
    @PreAuthorize(hasPermi = "agriculture:news:export")
    @Log(title = "政策新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PolicyNews policyNews) throws IOException {
        List<PolicyNews> list = policyNewsService.selectPolicyNewsList(policyNews);
        ExcelUtil<PolicyNews> util = new ExcelUtil<PolicyNews>(PolicyNews.class);
        util.exportExcel(response, list, "news");
    }

    /**
     * 获取政策新闻详细信息
     */
    @PreAuthorize(hasPermi = "agriculture:news:query")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable("newsId") Long newsId) {
        return AjaxResult.success(policyNewsService.selectPolicyNewsDtoById(newsId));
    }

    /**
     * 新增政策新闻
     */
    @PreAuthorize(hasPermi = "agriculture:news:add")
    @Log(title = "政策新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PolicyNews policyNews) {
        return toAjax(policyNewsService.insertPolicyNews(policyNews));
    }

    /**
     * 修改政策新闻
     */
    @PreAuthorize(hasPermi = "agriculture:news:edit")
    @Log(title = "政策新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PolicyNews policyNews) {
        return toAjax(policyNewsService.updatePolicyNews(policyNews));
    }

    /**
     * 删除政策新闻
     */
    @PreAuthorize(hasPermi = "agriculture:news:remove")
    @Log(title = "政策新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds) {
        return toAjax(policyNewsService.deletePolicyNewsByIds(newsIds));
    }
}
