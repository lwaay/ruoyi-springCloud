package com.sinonc.iot.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.domain.ProIrrigationLog;
import com.sinonc.iot.service.IProIrrigationLogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/21 17:21
 */
@Api(tags = "水肥信息接口")
@RestController
@RequestMapping("api/iot/irrigation")
@Slf4j
public class ApiIrrigationController extends BaseController {

    @Autowired
    private IProIrrigationLogService irrigationLogService;

    @GetMapping("list")
    public AjaxResult getIrrigationRecord(){
        PageHelper.startPage(1, 10, "create_time desc");
        return AjaxResult.success(irrigationLogService.selectProIrrigationLogList(new ProIrrigationLog()));
    }

    @GetMapping("analysis")
    public AjaxResult getAnalysisData(){
        return AjaxResult.success(irrigationLogService.getLogGroupByMonth());
    }
}
