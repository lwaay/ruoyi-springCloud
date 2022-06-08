package com.sinonc.iot.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.iot.domain.ProIrrigationLog;
import com.sinonc.iot.service.IProIrrigationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/23 10:15
 */
@RestController
@RequestMapping("irrigation")
public class IrrigationController extends BaseController {

    @Autowired
    private IProIrrigationLogService irrigationLogService;

    /**
     * 查询灌溉记录
     * @param irrigationLog
     * @return
     */
    @PreAuthorize(hasPermi = "iot:irrigation:list")
    @GetMapping("list")
    public TableDataInfo list(ProIrrigationLog irrigationLog){
        startPage();
        List<ProIrrigationLog> irrigationLogList = irrigationLogService.selectProIrrigationLogList(irrigationLog);
        return getDataTable(irrigationLogList);
    }

    /**
     * 导出灌溉记录列表
     */
    @PreAuthorize(hasPermi = "iot:irrigation:export")
    @Log(title = "灌溉记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProIrrigationLog irrigationLog) throws IOException {
        List<ProIrrigationLog> irrigationLogList = irrigationLogService.selectProIrrigationLogList(irrigationLog);
        ExcelUtil<ProIrrigationLog> util = new ExcelUtil<ProIrrigationLog>(ProIrrigationLog.class);
        util.exportExcel(response, irrigationLogList, "irrigationRecord");
    }
}
