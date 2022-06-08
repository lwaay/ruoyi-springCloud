package com.sinonc.openapi.controller.api;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/10 11:14
 */
@RestController
@RequestMapping("/v1/base")
public class ApiBaseFarmController {

    @Autowired
    private RemoteBaseFarmService baseFarmService;

    @GetMapping("list")
    public AjaxResult getFarmInfoList(){
        List<BaseFarm> baseFarmList = baseFarmService.getFarmList();
        return AjaxResult.success(baseFarmList);
    }
}
