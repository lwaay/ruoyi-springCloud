package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.orders.ec.service.IApiEcConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 行政区域Controller
 *
 * @author ruoyi
 * @date 2020-09-23
 */
@RestController
@RequestMapping("/ec/ecApiConfig")
public class ApiEcConfigController extends BaseController {

    @Autowired
    private IApiEcConfigService ecConfigService;

    @GetMapping("/selectTable")
    public R<List<Map<String, String>>> selectTable(){
        List<Map<String, String>> tables = ecConfigService.getTables();
        if (CollectionUtils.isEmpty(tables)){
            return R.fail();
        }
        return R.ok(tables);
    }

    @GetMapping("/selectColumn/{tableNames}")
    public R<List<Map<String, String>>> selectColumn(@PathVariable("tableNames") String tableNames){
        List<Map<String, String>> columns = ecConfigService.getColumn(tableNames);
        if (CollectionUtils.isEmpty(columns)){
            return R.fail();
        }
        return R.ok(columns);
    }

    @PostMapping("/queryList")
    public R<List<Map<String, String>>> queryList(@RequestBody Map<String, String> params){
        List<Map<String, String>> columns = ecConfigService.queryList(params);
        if (CollectionUtils.isEmpty(columns)){
            return R.fail();
        }
        return R.ok(columns);
    }
}
