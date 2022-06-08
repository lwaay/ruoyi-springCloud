package com.sinonc.orders.ec.controller;


import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.orders.ec.domain.EshopProductConfig;
import com.sinonc.orders.ec.service.IEshopProductConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务配置Controller
 *
 * @author ruoyi
 * @date 2021-04-10
 */
@RestController
@RequestMapping("/ec/config")
public class EshopProductConfigController extends BaseController {
    @Autowired
    private IEshopProductConfigService eshopProductConfigService;

    /**
     * 获取定时任务配置详细信息
     */
    @GetMapping(value = "/getConfig")
    public AjaxResult getConfig() {
        return AjaxResult.success(eshopProductConfigService.getEshopProductConfig());
    }

    /**
     * 修改定时任务配置
     */
    @Log(title = "定时任务配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopProductConfig eshopProductConfig) {
        return toAjax(eshopProductConfigService.updateEshopProductConfig(eshopProductConfig));
    }

}
