package com.sinonc.openapi.controller.api;

import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.RemoteIotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/10 15:10
 */
@RestController
@RequestMapping("/v1/iot")
public class ApiIotController {

    @Autowired
    private RemoteIotService iotSerivce;

    @GetMapping("monitor")
    public AjaxResult getMonitorList(Long farmId){
        return iotSerivce.getMonitorList(farmId);
    }
}
