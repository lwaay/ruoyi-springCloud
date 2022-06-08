package com.sinonc.iot.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.iot.service.IApiIotConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 设备Controller
 *
 * @author ruoyi
 * @date 2020-09-25
 */
@RestController
@RequestMapping("/iotApiConfig")
public class IotApiConfigController extends BaseController {

    @Autowired
    private IApiIotConfigService apiIotConfigService;

    @GetMapping("/selectTable")
    public R<List<Map<String,String>>>  selectTable(){
        List<Map<String,String>> tables = apiIotConfigService.getTables();
        if (CollectionUtils.isEmpty(tables)){
            return R.fail();
        }
        return R.ok(tables);
    }

    @GetMapping("/selectColumn/{tableNames}")
    public R<List<Map<String,String>>>  selectColumn(@PathVariable("tableNames") String tableNames){
        List<Map<String,String>> columns = apiIotConfigService.getColumn(tableNames);
        if (CollectionUtils.isEmpty(columns)){
            return R.fail();
        }
        return R.ok(columns);
    }

    @PostMapping("/queryList")
    public R<List<Map<String,String>>> queryList(@RequestBody Map<String,String> params){
        List<Map<String,String>> columns = apiIotConfigService.queryList(params);
        if (CollectionUtils.isEmpty(columns)){
            return R.fail();
        }
        return R.ok(columns);
    }
}
