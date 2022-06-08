package com.sinonc.ser.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.core.utils.StringUtils;
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
import com.sinonc.service.domain.SerService;
import com.sinonc.ser.service.ISerServiceService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 社会化服务Controller
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/service")
public class SerServiceController extends BaseController {
    @Autowired
    private ISerServiceService serServiceService;

    /**
     * 查询社会化服务列表
     */
    @PreAuthorize(hasPermi = "system:service:list")
    @GetMapping("/list")

    public TableDataInfo list(SerService serService) {
        startPage();
        List<SerService> list = serServiceService.selectSerServiceList(serService);
        return getDataTable(list);
    }

    /**
     * 导出社会化服务列表
     */
    @PreAuthorize(hasPermi = "system:service:export")
    @Log(title = "社会化服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SerService serService) throws IOException {
        List<SerService> list = serServiceService.selectSerServiceList(serService);
        ExcelUtil<SerService> util = new ExcelUtil<SerService>(SerService.class);
        util.exportExcel(response, list, "service");
    }

    /**
     * 获取社会化服务详细信息
     */
    @PreAuthorize(hasPermi = "system:service:query")
    @GetMapping(value = "/{serviceId}")
    public AjaxResult getInfo(@PathVariable("serviceId") Long serviceId) {
        return AjaxResult.success(serServiceService.selectSerServiceById(serviceId));
    }

    /**
     * 新增社会化服务
     */
    @PreAuthorize(hasPermi = "system:service:add")
    @Log(title = "社会化服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SerService serService) {
        serService.setDetail(StringUtils.coverText(serService.getDetail()));
        return toAjax(serServiceService.insertSerService(serService));
    }

    /**
     * 修改社会化服务
     */
    @PreAuthorize(hasPermi = "system:service:edit")
    @Log(title = "社会化服务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SerService serService) {
        serService.setDetail(StringUtils.coverText(serService.getDetail()));
        return toAjax(serServiceService.updateSerService(serService));
    }

    /**
     * 删除社会化服务
     */
    @PreAuthorize(hasPermi = "system:service:remove")
    @Log(title = "社会化服务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serviceIds}")
    public AjaxResult remove(@PathVariable Long[] serviceIds) {
        return toAjax(serServiceService.deleteSerServiceByIds(serviceIds));
    }
}
