package com.sinonc.report.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.jmreport.common.constant.CommonConstant;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.config.client.JmReportTokenClient;
import org.jeecg.modules.jmreport.desreport.entity.JmReportMap;
import org.jeecg.modules.jmreport.desreport.service.IJmReportMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({"/jimu/map"})
public class MapController {
    private static final Logger a = LoggerFactory.getLogger(MapController.class);

    @Autowired
    private IJmReportMapService jmReportMapService;

    @Autowired
    private JmReportTokenClient jimuTokenClient;

    @GetMapping({"/mapList"})
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestParam(name = "label", required = false) String paramString1, @RequestParam(name = "name", required = false) String paramString2, @RequestParam(name = "current", defaultValue = "1") Integer paramInteger1, @RequestParam(name = "size", defaultValue = "10") Integer paramInteger2) {
        String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
        Page page = new Page(paramInteger1.intValue(), paramInteger2.intValue());
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        JmReportMap jmReportMap = new JmReportMap();
        jmReportMap.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
        jmReportMap.setCreateBy(str);
        if (StringUtils.isNotBlank(paramString1))
            jmReportMap.setLabel(paramString1);
        if (StringUtils.isNotBlank(paramString2))
            jmReportMap.setName(paramString2);
        lambdaQueryWrapper.setEntity(jmReportMap);
        IPage iPage = this.jmReportMapService.page((IPage) page, (Wrapper) lambdaQueryWrapper);
        return Result.OK(iPage);
    }

    @PostMapping({"/addMapData"})
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportMap paramJmReportMap) {
        try {
            String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
            paramJmReportMap.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
            paramJmReportMap.setCreateBy(str);
            paramJmReportMap.setCreateTime(new Date());
            paramJmReportMap.setUpdateTime(new Date());
            this.jmReportMapService.saveMapSource(paramJmReportMap);
        } catch (Exception exception) {
            return Result.error(403, "");
        }
        return Result.OK("", Boolean.valueOf(true));
    }

    @PostMapping({"/delMapSource"})
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportMap paramJmReportMap) {
        this.jmReportMapService.removeById(paramJmReportMap.getId());
        return Result.OK("", Boolean.valueOf(true));
    }

    @PostMapping({"/queryMapByCode"})
    public Result<?> c(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportMap paramJmReportMap) {
        String str1 = this.jimuTokenClient.getUsername(paramHttpServletRequest);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        paramJmReportMap.setDelFlag(CommonConstant.DEL_FLAG_0.toString());
        String str2 = paramJmReportMap.getReportId();
        if (StringUtils.isBlank(str2))
            paramJmReportMap.setCreateBy(str1);
        lambdaQueryWrapper.setEntity(paramJmReportMap);
        JmReportMap jmReportMap = (JmReportMap) this.jmReportMapService.getOne((Wrapper) lambdaQueryWrapper);
        return Result.OK(jmReportMap);
    }
}
