package com.sinonc.iot.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.api.vo.ProStaticVo;
import com.sinonc.iot.domain.InsectImg;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.service.DeviceDataService;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IDeviceMonitorService;
import com.sinonc.iot.service.IDeviceTypeService;
import com.sinonc.iot.utils.OKHttp;
import com.sinonc.iot.utils.OKHttpResponse;
import com.sinonc.iot.vo.QueryDataVo;
import com.sinonc.iot.vo.RealData;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/1/24 15:41
 */

@Api()
@ApiOperation("物联网大屏接口")
@RestController
@RequestMapping("/iot/api/screen")
public class ApiScreenController {

    @Autowired
    private DeviceDataService deviceDataService;

    @Autowired
    private IDeviceInfoService deviceInfoService;

    @Autowired
    private IDeviceTypeService deviceTypeService;

    @Autowired
    private RemoteBaseFarmService baseFarmService;

    @Autowired
    IDeviceMonitorService deviceMonitorService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;




    @ApiOperation("大屏物联网折线图数据接口")
    @PostMapping("getScreenIot")
    public AjaxResult getData(@RequestBody QueryDataVo queryDataVo) {
        return AjaxResult.success(deviceDataService.getDeviceData(queryDataVo));
    }

    @ApiOperation("根据设备id查询实时数据")
    @GetMapping("getDeviceDataByDeviceId/{deviceId}")
    public AjaxResult getDeviceDataByDeviceId(@PathVariable("deviceId") String deviceId) {
        List<RealData> list = deviceDataService.getRealData(deviceId);
        return AjaxResult.success(list);
    }

    @ApiOperation("根据基地ID获取设备列表")
    @GetMapping("getDeviceList/{farmId}")
    public AjaxResult getDeviceList(@PathVariable("farmId") Long farmId) {
        BaseFarm baseFarm = baseFarmService.getFarmInfo(farmId);
        String id = baseFarm.getDeviceIds();
        String[] ids = Convert.toStrArray(id);
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (String deviceId : ids) {
            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(deviceId);
            if (deviceInfo != null) {
                DeviceType deviceTypeById = deviceTypeService.selectDeviceTypeById(Long.valueOf(deviceInfo.getDeviceTypeId()));
                deviceInfo.setDeviceTypeDesc(deviceTypeById.getRemark());
                deviceInfos.add(deviceInfo);
            }
        }
        return AjaxResult.success(deviceInfos);
    }

    @ApiOperation("获取基地列表")
    @PostMapping("/getBaseList")
    public AjaxResult getBaseList(@RequestBody BaseFarm baseFarm) {
        List<BaseFarm> baseFarmList = baseFarmService.list(baseFarm).getData();
        PageInfo<BaseFarm> pageInfo = new PageInfo<>(baseFarmList);
        AjaxResult success = AjaxResult.success(pageInfo.getList());
        success.put("hasNextPage", pageInfo.isHasNextPage());
        return success;
    }


    @ApiOperation("物联网汇总数据")
    @GetMapping("getGather")
    public AjaxResult getGather() {
        Integer deviceCount = deviceInfoService.getCount();
        List list = deviceMonitorService.selectDeviceMonitorList(new DeviceMonitor());
        Integer monitorCount = 0;
        if (list == null || list.size() <= 0) {
            monitorCount = 0;
        } else {
            //monitorCount = list.size();
        }
        //Long areaCount=baseFarmService.getAreaCount();
        BaseFarm baseFarm = new BaseFarm();
        baseFarm.setHasIot(1L);
        R<List<BaseFarm>> farmR = baseFarmService.list(baseFarm);
        List<BaseFarm> farmList = farmR.getData();
        Integer areaCount = 0;
        Integer baseCount = 0;
        if (farmList == null || farmList.size() <= 0) {
            areaCount = 0;
            baseCount = 0;
        } else {
            baseCount = farmList.size();
            for (int i = 0; i < farmList.size(); i++) {
                BaseFarm tempBaseFarm = farmList.get(i);
                String monitorIds=tempBaseFarm.getMonitorIds();
                String[] monitoridsArray=monitorIds.split(",");
                monitorCount=monitorCount+monitoridsArray.length;
                areaCount = areaCount + tempBaseFarm.getBaseArea();
            }
        }
        AjaxResult resultMap = new AjaxResult();
        resultMap.put("deviceCount", deviceCount);
        resultMap.put("monitorCount", monitorCount);
        resultMap.put("areaCount", areaCount);
        resultMap.put("baseCount", baseCount);
        //监控+设备
        resultMap.put("allCount", deviceCount + monitorCount);
        return resultMap;
    }


    @ApiOperation("监控控制")
    @GetMapping("control")
    public AjaxResult control(String devSn, int direction, String channel) {
        String url = "https://open.ys7.com/api/lapp/device/ptz/start";
        Map<String, String> querys = new HashMap<>();
        //鉴权token
//        querys.put("accessToken", getToken());
        querys.put("accessToken", deviceMonitorService.getTokenByDevSn(devSn, Integer.valueOf(channel)));
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
        querys.put("accessToken", deviceMonitorService.getTokenByDevSn(devSn, Integer.valueOf(channel)));
        querys.put("deviceSerial", devSn);
        querys.put("channelNo", channel);
        querys.put("direction", direction);
        OKHttpResponse response = OKHttp.instance().post(url, null, new HashMap<>(), querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        return "200".equals(jsonObject.getString("code"));

    }

    @ApiOperation("根据基地id获取监控列表")
    @GetMapping("getMonitorListByBaseId/{farmId}")
    public AjaxResult getMonitorListByBaseId(@PathVariable("farmId") Long farmId) {
        BaseFarm baseFarm = baseFarmService.getFarmInfo(farmId);
        String id = baseFarm.getMonitorIds();
        Long[] ids = Convert.toLongArray(id);
        List<DeviceMonitor> deviceInfos = new ArrayList<>();
        for (Long deviceId : ids) {
            DeviceMonitor monitor = deviceMonitorService.selectDeviceMonitorById(deviceId);
            //使用ezOpen播放
            if (!Optional.ofNullable(monitor).isPresent()) {
                continue;
            }
            String token = deviceMonitorService.getTokenByDevSn(monitor.getDevSn(), monitor.getDevChannel());

            monitor.setToken(token);
            //屏蔽appkey,Secret
            monitor.setAppKey("*");
            monitor.setAppSecret("*");

            String hlsUrl = getHLSUrl(token, monitor);
            monitor.setHttpUrl(hlsUrl);
            monitor.setToken(token);
            deviceInfos.add(monitor);

        }
        return AjaxResult.success(deviceInfos);
    }


    @ApiOperation("根据摄像头ID获取摄像头")
    @GetMapping("getMonitorListByDeviceId/{deviceId}")
    public AjaxResult getMonitorListByDeviceId(@PathVariable("deviceId") Long deviceId) {
        DeviceMonitor monitor = deviceMonitorService.selectDeviceMonitorById(deviceId);
        monitor.setEzopen("ezopen://open.ys7.com/" + monitor.getDevSn() + "/" + monitor.getDevChannel() + ".hd.live");
        String token = deviceMonitorService.getTokenByDevSn(monitor.getDevSn(), monitor.getDevChannel());
        monitor.setToken(token);
        //屏蔽appkey,Secret
        monitor.setAppKey("*");
        monitor.setAppSecret("*");
        return AjaxResult.success(monitor);
    }

    @ApiOperation("根据摄像头序列和通道获取摄像头")
    @GetMapping("getMonitorListByDevSn")
    public AjaxResult getMonitorListByDevSn(DeviceMonitor monitor) {
        monitor.setEzopen("ezopen://open.ys7.com/" + monitor.getDevSn() + "/" + monitor.getDevChannel() + ".hd.live");
        String token=monitor.getToken();
        if(StringUtils.isEmpty(token)){
            token = deviceMonitorService.getTokenByDevSn(monitor.getDevSn(), monitor.getDevChannel());
        }

        monitor.setToken(token);
        //屏蔽appkey,Secret
        monitor.setAppKey("*");
        monitor.setAppSecret("*");
        return AjaxResult.success(monitor);
    }


    private String getHLSUrl(String accessToken, DeviceMonitor monitor) {
        String deviceSerial = monitor.getDevSn();
        String channelNo = String.valueOf(monitor.getDevChannel());
        //7天过期
        Long secondLong = 3600 * 24 * 70L;
        //键值
        String redisHlsUrlKey = deviceSerial + channelNo;

//        if (Boolean.TRUE.equals(redisTemplate.hasKey(redisHlsUrlKey))) {
//            return redisTemplate.opsForValue().get(redisHlsUrlKey);
//        }
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/v2/live/address/get";
        querys.put("accessToken", accessToken);
        querys.put("deviceSerial", monitor.getDevSn());
        querys.put("channelNo", String.valueOf(monitor.getDevChannel()));
        querys.put("expireTime", String.valueOf(secondLong));
        querys.put("protocol", "2");
        querys.put("quality", "2");

        Map<String, String> headers = new HashMap<>();

        OKHttpResponse response = OKHttp.instance().post(url, null, headers, querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        String code = jsonObject.getString("code");
        if (!"200".equals(code)) {
            return "";
        }
        JSONObject data = jsonObject.getJSONObject("data");
        redisTemplate.opsForValue().set(redisHlsUrlKey, data.getString("url"), secondLong - 3600, TimeUnit.SECONDS);
        return data.getString("url");
    }

    @ApiOperation("获取全部最新的虫情图片")
    @GetMapping("getAllInsetImg")
    public AjaxResult getAllInsetImg(Long entityId) {
        String[] ids = deviceInfoService.getUserAllDeviceIds(entityId);

        List<InsectImg> insectImgList = new ArrayList<>();
        for (String id : ids) {
            InsectImg insectImg = deviceDataService.getImg(id);
            if (insectImg != null) {
                insectImgList.add(insectImg);
            }
        }

        return AjaxResult.success(insectImgList);
    }

    @ApiOperation("获取最新的虫情图片")
    @GetMapping("getInsetImg/{deviceId}")
    public AjaxResult getImg(@PathVariable("deviceId") String deviceId) {
        InsectImg insectImg = deviceDataService.getImg(deviceId);
        if (insectImg == null) {
            return AjaxResult.error("该设备无虫情图片");
        }
        return AjaxResult.success(insectImg);
    }
}
