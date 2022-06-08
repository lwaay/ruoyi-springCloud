package com.sinonc.openapi.controller.api;

import com.sinonc.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/5/10 15:43
 */
@RestController
@RequestMapping("/v1/health")
public class ApiHeartBeatController {

    /**
     * 心跳
     * @return
     */
    @RequestMapping("check")
    public AjaxResult check(){
        return AjaxResult.success(new Date());
    }
}
