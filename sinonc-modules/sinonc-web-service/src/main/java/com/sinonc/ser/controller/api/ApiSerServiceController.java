package com.sinonc.ser.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.service.domain.SerService;
import com.sinonc.ser.service.ISerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社会化服务Controller
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@RestController
@RequestMapping("/api/service")
public class ApiSerServiceController {

    @Autowired
    private ISerServiceService serServiceService;

    /**
     * 查询社会化服务列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SerService serService) {
        PageHelper.startPage(serService.getPageNum() !=null?serService.getPageNum():1, serService.getPageSize()!=null?serService.getPageSize():10);
        List<SerService> list = serServiceService.selectSerServiceList(serService);
        PageInfo<SerService> page = PageInfo.of(list);
        Map<String,Object> res = new HashMap<>();
        res.put("list",page.getList());
        res.put("hasNext",page.isHasNextPage());
        return AjaxResult.success(res);
    }

    /**
     * 获取服务详情
     */
    @GetMapping("/detail/{serviceId}")
    public AjaxResult detail(@PathVariable("serviceId")Long serviceId){
        SerService serService = serServiceService.selectSerServiceById(serviceId);
        return AjaxResult.success(serService);
    }

    /**
     * 新增社会化服务
     */
    @PostMapping("/put")
    public AjaxResult put(@RequestBody SerService serService){
        return serServiceService.insertSerService(serService)>0?AjaxResult.success():AjaxResult.error();
    }
}
