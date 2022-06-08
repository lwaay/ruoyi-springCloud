package com.sinonc.iot.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.config.property.IotConfig;
import com.sinonc.iot.domain.IotWarn;
import com.sinonc.iot.dto.IotWarnDto;
import com.sinonc.iot.service.IotWarnService;
import com.sinonc.iot.util.WarnUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author zhangxinlong
 * @date 2021/4/10  14:20
 */
@Api(tags = "大屏、APP物联网预警信息接口")
@RestController
@RequestMapping("/iot/api/warn/iotwarn")
public class ApiIotWarnController extends BaseController {

    @Autowired
    private IotWarnService iotWarnService;

    @Autowired
    private IotConfig appInfo;

    /**
     * @return
     */
    @ApiOperation("获取物联网设备告警信息")
    @GetMapping("/getDeviceWarnInfo")
    public AjaxResult getDeviceWarnInfo() {
        List<Map> deviceInfoList = WarnUtils.getDeviceInfoList(appInfo.getAppKey(), appInfo.getAppSecret(),appInfo.getHost());
        startPage();
        IotWarn iotWarn = new IotWarn();
        List<IotWarn> list = iotWarnService.listIotWarm(iotWarn);
        PageInfo<IotWarn> pageInfo = new PageInfo<>(list);
        if (CollectionUtils.isEmpty(list)){
            return AjaxResult.success(list);
        }
        list.forEach(item->{
           item.setDeviceName((String) deviceInfoList.stream().filter(info->item.getDeviceId().equals(info.get("deviceId"))).findFirst().map(devising->devising.get("deviceName")).orElse(""));
        });
        AjaxResult success = AjaxResult.success();
        success.put("data", list);
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    @ApiOperation("添加处理信息")
    @GetMapping("addHandleInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "预警记录id", name = "id", paramType = "query", required = true),
            @ApiImplicitParam(value = "处理信息", name = "handleRecord", paramType = "query", required = true),
    })
    public AjaxResult addHandleInfo(String id, String handleRecord) {
        IotWarn iotWarn = new IotWarn();
        iotWarn.setWarnId(Long.valueOf(id));
        iotWarn.setHandleRecord(handleRecord);
        iotWarn.setIsHandle(1);
        iotWarnService.updateIotWarn(iotWarn);
        return AjaxResult.success("添加成功");
    }

    @ApiOperation("根据id获取预警明细")
    @GetMapping("/getWarnInfoById/{warnId}")
    public AjaxResult getWarnInfoById(@PathVariable("warnId")Long wardId){
       if (wardId == null || wardId<1L){
           return AjaxResult.error("请提交预警id后查询信息");
       }
        IotWarnDto res = iotWarnService.getIotWarnDetailById(wardId);
       return Optional.ofNullable(res).isPresent()?AjaxResult.success(res):AjaxResult.error("查询预警数据失败");
    }

    @ApiOperation("预警数量")
    @GetMapping("count")
    public AjaxResult warnCount(){
        return AjaxResult.success(iotWarnService.warnCount());
    }
}
