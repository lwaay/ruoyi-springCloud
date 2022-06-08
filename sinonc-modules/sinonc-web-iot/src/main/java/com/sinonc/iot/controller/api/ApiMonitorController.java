package com.sinonc.iot.controller.api;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDomain;
import com.sinonc.common.core.web.page.TableSupport;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.domain.DeviceCapture;
import com.sinonc.iot.domain.IvmsZeroPage;
import com.sinonc.iot.service.IDeviceCaptureService;
import com.sinonc.iot.service.IDeviceMonitorService;
import com.sinonc.iot.utils.OKHttp;
import com.sinonc.iot.utils.OKHttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yehuiwang
 * @date: 2020/6/12 16:23
 * @description:
 */
@Api(tags = "摄像头接口")
@RestController
@RequestMapping("/iot/api/monitor")
public class ApiMonitorController extends BaseController {

    @Value("${ys.appKey}")
    private String appKey;

    @Value("${ys.appSecret}")
    private String secret;
    @Autowired
    IDeviceMonitorService deviceMonitorService;
    @Autowired
    private IDeviceCaptureService deviceCaptureService;
    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;

    @ApiOperation("监控控制")
    @GetMapping("control")
    public AjaxResult control(String devSn, int direction, String channel) {
        String url = "https://open.ys7.com/api/lapp/device/ptz/start";
        Map<String, String> querys = new HashMap<>();
        //鉴权token
//        querys.put("accessToken", getToken());
        querys.put("accessToken", getDeviceMonitorAccessToken(devSn, channel));
        //设备序列号
        querys.put("deviceSerial", devSn);
        //通道号
        querys.put("channelNo", channel);
        //方向，具体值请查看官网
        querys.put("direction", String.valueOf(direction));
        //转动速度
        querys.put("speed", "1");
        OKHttpResponse response = OKHttp.instance().post(url, null, new HashMap<>(), querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        if ("200".equals(jsonObject.getString("code"))) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stop(String.valueOf(direction), devSn, channel)) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error("操作失败");
            }

        } else {
            return AjaxResult.error(jsonObject.getString("msg"));
        }
    }

    public boolean stop(String direction, String devSn, String channel) {
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/device/ptz/stop";
        querys.put("accessToken", getDeviceMonitorAccessToken(devSn, channel));
        querys.put("deviceSerial", devSn);
        querys.put("channelNo", channel);
        querys.put("direction", direction);
        OKHttpResponse response = OKHttp.instance().post(url, null, new HashMap<>(), querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        return "200".equals(jsonObject.getString("code"));

    }

    @GetMapping("getMonitorDetail")
    @ApiOperation("根据id获取摄像头详细信息")
    public AjaxResult getMonitorDetail(Long id) {
        DeviceMonitor deviceMonitor = deviceMonitorService.selectDeviceMonitorById(id);
        return AjaxResult.success("success", deviceMonitor);
    }

    @GetMapping("getMonitorCout")
    @ApiOperation("获取摄像头总数")
    public AjaxResult getMonitorCount() {
        List<DeviceMonitor> deviceMonitor = deviceMonitorService.selectDeviceMonitorList(new DeviceMonitor());
        return AjaxResult.success("success", deviceMonitor.size());
    }

    @GetMapping("getMonitorList")
    @ApiOperation("获取摄像头列表")
    public AjaxResult getMonitorList() {
        List<DeviceMonitor> deviceMonitor = deviceMonitorService.selectDeviceMonitorList(new DeviceMonitor());
        return AjaxResult.success("success", deviceMonitor);
    }



    @GetMapping("getMonitor/{farmId}")
    @ApiOperation("根据基地id获取摄像头列表")
    public AjaxResult getMonitor(@PathVariable("farmId") Long farmId) {
        BaseFarm baseFarm = remoteBaseFarmService.getFarmInfo(farmId);
        String monitorIds = baseFarm.getMonitorIds();
        if (monitorIds.isEmpty()) {
            return AjaxResult.success("该基地无摄像头");
        }
        String[] ids = monitorIds.split(",");
        List<DeviceMonitor> deviceMonitorList = deviceMonitorService.getMonitorByIds(ids);

        getDeviceMonitorListToken(deviceMonitorList);
        return AjaxResult.success("success", deviceMonitorList);
    }

    @GetMapping("getMonitorListByFarm/{farmId}")
    @ApiOperation("根据基地id获取摄像头列表")
    public R<List<DeviceMonitor>> getMonitorListByFarm(@PathVariable("farmId") Long farmId) {
        BaseFarm baseFarm = remoteBaseFarmService.getFarmInfo(farmId);
        String monitorIds = baseFarm.getMonitorIds();
        if (monitorIds.isEmpty()) {
            return R.fail("该基地无摄像头");
        }
        String[] ids = monitorIds.split(",");
        List<DeviceMonitor> deviceMonitorList = deviceMonitorService.getMonitorByIds(ids);

        getDeviceMonitorListToken(deviceMonitorList);
        return R.ok(deviceMonitorList);
    }

    public String getToken() {
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/token/get";
        querys.put("appKey", appKey);
        querys.put("appSecret", secret);
        OKHttpResponse response = OKHttp.instance().post(url, null, null, querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        JSONObject data = jsonObject.getJSONObject("data");
        return data.getString("accessToken");
    }

    /**
     * pageNo 当前页码
     * pageSize 每页数据记录数
     */
    @GetMapping("getIvmsZeroCameraList")
    @ApiOperation("获取监控中心摄像头列表")
    public AjaxResult getIvmsZeroCameraList(Integer pageNo, Integer pageSize) {
        try {
            IvmsZeroPage ivmsZeroPage = deviceMonitorService.getIvmsZeroCameraList(pageNo, pageSize);
            return AjaxResult.success("success", ivmsZeroPage);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }


    @GetMapping("/getEsYsMonitor")
    @ApiOperation("获取萤石商居摄像头列表")
    public AjaxResult getEsYsMonitor() {
        DeviceMonitor deviceMonitor = new DeviceMonitor();
        deviceMonitor.setIsEsys("1");
        List<DeviceMonitor> deviceMonitorList = deviceMonitorService.selectDeviceMonitorList(deviceMonitor);
        getDeviceMonitorListToken(deviceMonitorList);
        return AjaxResult.success("success", deviceMonitorList);

    }

    @GetMapping("getMonitorCapture")
    @ApiOperation("根据id获取摄像头抓拍图片")
    public AjaxResult getMonitorCapture(DeviceCapture capture) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        PageHelper.startPage(StringUtils.isNotNull(pageNum)? pageNum:1, StringUtils.isNotNull(pageSize)? pageSize:5,"create_time desc");
        List<DeviceCapture> deviceCaptureList = deviceCaptureService.selectDeviceCaptureList(capture);
        return AjaxResult.success("success", deviceCaptureList);
    }

    private void getDeviceMonitorListToken(List<DeviceMonitor> deviceMonitorList) {
        String systemToken = getToken();
        Map<String, String> tempTokenMap = new HashMap<>();
        for (int i = 0; i < deviceMonitorList.size(); i++) {
            DeviceMonitor tempDeviceMonitor = deviceMonitorList.get(i);
            String appKey = tempDeviceMonitor.getAppKey();
            String appSecret = tempDeviceMonitor.getAppSecret();
            if (StringUtils.isEmpty(appKey)) {
                tempDeviceMonitor.setToken(systemToken);
            } else {
                String token = tempTokenMap.get(appKey);
                if (StringUtils.isEmpty(token)) {
                    token = getToken(appKey, appSecret);
                    tempTokenMap.put(appKey, token);
                }
                tempDeviceMonitor.setToken(token);
            }
        }
    }


    private String getDeviceMonitorAccessToken(String devSn, String devChannel) {
        DeviceMonitor paraDeviceMonitor = new DeviceMonitor();
        paraDeviceMonitor.setDevSn(devSn);
        paraDeviceMonitor.setDevChannel(Integer.parseInt(devChannel));
        List<DeviceMonitor> deviceMonitorList = deviceMonitorService.selectDeviceMonitorList(paraDeviceMonitor);
        if (deviceMonitorList != null && deviceMonitorList.size() > 0) {
            DeviceMonitor deviceMonitor = deviceMonitorList.get(0);
            String appKey = deviceMonitor.getAppKey();
            String appSecret = deviceMonitor.getAppSecret();
            if (StringUtils.isEmpty(appKey)) {
                String systemToken = getToken();
                return systemToken;
            } else {
                String token = getToken(appKey, appSecret);
                return token;
            }
        } else {
            return "";
        }
    }

    public String getToken(String tempKey, String tempSecret) {
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/token/get";
        querys.put("appKey", tempKey);
        querys.put("appSecret", tempSecret);
        OKHttpResponse response = OKHttp.instance().post(url, null, null, querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        JSONObject data = jsonObject.getJSONObject("data");
        return data.getString("accessToken");
    }

}
