package com.sinonc.system.controller.api;

import com.sinonc.common.core.domain.R;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.service.ISysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/24 16:13
 */
@RestController
@RequestMapping("api/dict")
@Slf4j
public class ApiDictDataController {

    @Autowired
    private ISysDictTypeService dictTypeService;

    @GetMapping(value = "/type/{dictType}")
    public R<List<SysDictData>> dictType(@PathVariable String dictType){
        return R.ok(dictTypeService.selectDictDataByType(dictType));
    }
}
