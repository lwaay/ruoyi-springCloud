package com.sinonc.base.controller.api;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.base.domain.OrchardTrend;
import com.sinonc.base.service.IBaseFarmService;
import com.sinonc.base.service.IFruiterInfoService;
import com.sinonc.base.service.IOrchardTrendService;
import com.sinonc.base.vo.GradeRateVo;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.iot.api.RemoteIotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yehuiwang
 * @date: 2020/6/11 10:36
 * @description:
 */
@Api(tags = "基地(合作社)")
@RestController
@RequestMapping("api/base/farm/")
public class ApiBaseFarmController extends BaseController {

    @Autowired
    private RemoteIotService remoteIotService;
    @Autowired
    private IBaseFarmService baseFarmService;
    @Autowired
    private IFruiterInfoService fruiterInfoService;
    @Autowired
    private IOrchardTrendService orchardTrendService;

    @ApiOperation("查询基地列表数据")
    @PostMapping("getFarmList")
    public AjaxResult getFarmList(@RequestBody BaseFarm baseFarm) {
        PageHelper.startPage(baseFarm.getPageNum(), baseFarm.getPageSize());
        PageInfo pageInfo = new PageInfo<>(baseFarmService.selectBaseFarmList(baseFarm));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    @ApiOperation("查询基地列表数据")
    @GetMapping("getAllFarmList")
    public AjaxResult getAllFarmList() {
        return AjaxResult.success(baseFarmService.selectBaseFarmList(new BaseFarm()));
    }

    @ApiOperation("根据服务版会员ID查询基地列表数据")
    @GetMapping("getFarmListByMemberId")
    public AjaxResult getFarmListByMemberId() {
        Long userId = SecurityUtils.getUserId();
        List<BaseFarm> list = baseFarmService.getFarmListByMemberId(userId);
        return AjaxResult.success("success", list);
    }

    @ApiOperation("根据后台用户查询基地列表数据")
    @GetMapping("getFarmListBySys")
    public AjaxResult getFarmListBySys() {
        List<BaseFarm> list = baseFarmService.getFarmListBySys();
        return AjaxResult.success("success", list);
    }

    @ApiOperation("新增基地")
    @PostMapping("addBaseFarm")
    public AjaxResult addBaseFarm(@RequestBody BaseFarm baseFarm) {
        int rs = baseFarmService.addBaseFarm(baseFarm);
        return AjaxResult.success("success", rs);
    }

    @ApiOperation("更新评分")
    @PostMapping("updateBaseFarmGradeRate")
    public AjaxResult updateBaseFarmGradeRate(@RequestBody GradeRateVo gradeRateVo) {
        BaseFarm baseFarm=new BaseFarm();
        baseFarm.setFarmId(gradeRateVo.getFarmId());
        String gradeRate = JSON.toJSONString(gradeRateVo);
        baseFarm.setGradeRate(gradeRate);
        int rs = baseFarmService.updateBaseFarm(baseFarm);
        return AjaxResult.success("success", rs);
    }


    @ApiOperation("查询基地相关数据(基地总个数、基地总面积、传感器总数、摄像头总数)")
    @GetMapping("getFarmData")
    public AjaxResult getFarmData() {
        Map<String, Object> map = new HashMap<>();
        Integer farmCount = baseFarmService.getFarmCount();
        Double farmAreaCount = baseFarmService.getFarmAreaCount();
        Integer deviceCount = remoteIotService.getDeviceCount();

        Integer monitorCount = remoteIotService.getMonitorCount();
        map.put("farmCount", farmCount);
        map.put("farmAreaCount", farmAreaCount);
        map.put("deviceCount", deviceCount);
        map.put("monitorCount", monitorCount);
        return AjaxResult.success("success", map);
    }

    @ApiOperation("根据果园ID(基地)查询果园详情(基地)")
    @GetMapping("getFarmById")
    public AjaxResult getFarmById(Long farmId) {
        BaseFarm baseFarm = baseFarmService.selectBaseFarmById(farmId);
        return AjaxResult.success("success", baseFarm);
    }


    @ApiOperation("根据果树ID查询果树信息")
    @GetMapping("getFruiterInfoById")
    public AjaxResult getFruiterInfoById(Long fruId) {
        FruiterInfo fruiterInfo = fruiterInfoService.selectFruiterInfoById(fruId);
        return AjaxResult.success("success", fruiterInfo);
    }

    @ApiOperation("添加果园动态信息")
    @PutMapping("trend")
    public AjaxResult addOrchardTrend(@Validated @RequestBody OrchardTrend orchardTrend){
        Long  userId=SecurityUtils.getUserId();
        orchardTrend.setCreateBy(String.valueOf(userId));
        return AjaxResult.success(orchardTrendService.insertOrchardTrend(orchardTrend));
    }

    @ApiOperation("获取果园动态信息")
    @GetMapping("trend/{id}")
    public AjaxResult trendDetail(@PathVariable("id") Long id){
        return AjaxResult.success(orchardTrendService.selectOrchardTrendById(id));
    }

    @ApiOperation("根据果园id查询果园动态信息")
    @GetMapping("trend")
    public AjaxResult trendList(OrchardTrend orchardTrend){
        startPage();
        PageHelper.orderBy("create_time desc");
        return AjaxResult.success(orchardTrendService.selectOrchardTrendList(orchardTrend));
    }

    @ApiOperation("根据果园id查询果园动态信息Ver2,带total")
    @GetMapping("trendV2")
    public TableDataInfo trendListV2(OrchardTrend orchardTrend){
        startPage();
        PageHelper.orderBy("create_time desc");
        return getDataTable(orchardTrendService.selectOrchardTrendList(orchardTrend));
    }

}
