package com.sinonc.iot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.oss.service.UploadUtil;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.domain.IvmsZeroPage;
import com.sinonc.iot.mapper.DeviceMonitorMapper;
import com.sinonc.iot.service.IDeviceMonitorService;
import com.sinonc.iot.utils.OKHttp;
import com.sinonc.iot.utils.OKHttpResponse;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 监控设备Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
@Slf4j
public class DeviceMonitorServiceImpl implements IDeviceMonitorService {
    @Autowired
    private DeviceMonitorMapper deviceMonitorMapper;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UploadUtil uploadService;

    private final static String MASS_SAFE_TRACE_CAMERA_NAME = "mass_safe_trace_camera_name";

    /**
     * 查询监控设备
     *
     * @param id 监控设备ID
     * @return 监控设备
     */
    @Override
    public DeviceMonitor selectDeviceMonitorById(Long id) {
        return deviceMonitorMapper.selectDeviceMonitorById(id);
    }

    /**
     * 查询监控设备列表
     *
     * @param deviceMonitor 监控设备
     * @return 监控设备
     */
    @Override
    public List<DeviceMonitor> selectDeviceMonitorList(DeviceMonitor deviceMonitor) {
        return deviceMonitorMapper.selectDeviceMonitorList(deviceMonitor);
    }

    /**
     * 新增监控设备
     *
     * @param deviceMonitor 监控设备
     * @return 结果
     */
    @Override
    public int insertDeviceMonitor(DeviceMonitor deviceMonitor) {
        deviceMonitor.setCreateTime(DateUtils.getNowDate());
        return deviceMonitorMapper.insertDeviceMonitor(deviceMonitor);
    }

    /**
     * 修改监控设备
     *
     * @param deviceMonitor 监控设备
     * @return 结果
     */
    @Override
    public int updateDeviceMonitor(DeviceMonitor deviceMonitor) {
        return deviceMonitorMapper.updateDeviceMonitor(deviceMonitor);
    }

    /**
     * 批量删除监控设备
     *
     * @param ids 需要删除的监控设备ID
     * @return 结果
     */
    @Override
    public int deleteDeviceMonitorByIds(Long[] ids) {
        return deviceMonitorMapper.deleteDeviceMonitorByIds(ids);
    }

    /**
     * 删除监控设备信息
     *
     * @param id 监控设备ID
     * @return 结果
     */
    @Override
    public int deleteDeviceMonitorById(Long id) {
        return deviceMonitorMapper.deleteDeviceMonitorById(id);
    }

    @Override
    public List<DeviceMonitor> getMonitorByIds(String[] ids) {
        return deviceMonitorMapper.getMonitorByIds(ids);
    }

    @Override
    public int getMonitorCount() {
        return deviceMonitorMapper.getMonitorCount();
    }

    @Override
    public IvmsZeroPage getIvmsZeroCameraList(Integer pageNo, Integer pageSize) throws Exception {
        R<String> defaultCamea = remoteUserService.getConfigKey(MASS_SAFE_TRACE_CAMERA_NAME);

        String defaultCameaName = defaultCamea.getData();

        String opuseruuid = IvmsZeroTools.getDefaultUserUuid(IvmsZeroTools.APPKEY, IvmsZeroTools.SECRET);
        IvmsZeroPage ivmsZeroPage = IvmsZeroTools.getCameras(opuseruuid, IvmsZeroTools.APPKEY, IvmsZeroTools.SECRET, pageNo, pageSize, defaultCameaName);
        return ivmsZeroPage;
    }

    @Override
    public boolean syncEsYs() {
        try {
            List<DeviceMonitor> deviceMonitorsList = getDeviceMonitors();
            for (int i = 0; i < deviceMonitorsList.size(); i++) {
                DeviceMonitor deviceMonitor = deviceMonitorsList.get(i);
                DeviceMonitor para = new DeviceMonitor();
                para.setDevSn(deviceMonitor.getDevSn());
                para.setDevChannel(deviceMonitor.getDevChannel());
                List<DeviceMonitor> list = deviceMonitorMapper.selectDeviceMonitorList(para);
                if (list != null && list.size() > 0) {
                    DeviceMonitor tempDeviceMonitor = list.get(0);
                    Long id = tempDeviceMonitor.getId();
                    deviceMonitor.setId(id);
                    deviceMonitor.setName(null);
                    deviceMonitorMapper.updateDeviceMonitor(deviceMonitor);
                } else {
                    deviceMonitorMapper.insertDeviceMonitor(deviceMonitor);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private List<DeviceMonitor> getDeviceMonitors() throws Exception {
        String putUrl = "https://esopen.ys7.com/api/resource/open-app/surveillance/camera/list/page";
        String token = getToken();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("accessToken", token);
        String postBody = JSON.toJSONString(params);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
        int statusCode = response.getHttpReturnCode();

        if (statusCode == 200) {
            JSONObject rsJSONObject = JSONObject.parseObject(response.getContent());
            JSONArray dataJSONArray = rsJSONObject.getJSONArray("data");

            ArrayList<DeviceMonitor> rsArrayList = new ArrayList<>();

            if (dataJSONArray.size() > 0) {
                for (int i = 0; i < dataJSONArray.size(); i++) {
                    DeviceMonitor deviceMonitor = changeToDeviceMonitor(dataJSONArray.getJSONObject(i));
                    deviceMonitor.setIsEsys("1");
                    rsArrayList.add(deviceMonitor);
                }
            }
            return rsArrayList;
        } else {
            return new ArrayList<>();
        }
    }

    private DeviceMonitor changeToDeviceMonitor(JSONObject jsONObject) {
        DeviceMonitor deviceMonitor = new DeviceMonitor();
        String deviceSerial = jsONObject.getString("deviceSerial");
        String channelNo = jsONObject.getString("channelNo");
        String channelName = jsONObject.getString("channelName");
        deviceMonitor.setDevSn(deviceSerial);
        deviceMonitor.setDevChannel(Integer.parseInt(channelNo));
        deviceMonitor.setName(channelName);

        deviceMonitor.setFactory("海康");
        deviceMonitor.setType("sphere");
        deviceMonitor.setEzopen("ezopen://open.ys7.com/" + deviceSerial + "/" + channelNo + ".hd.live");
        deviceMonitor.setAppKey("ed87e19d76ef4bd2ad46166d980af092");
        deviceMonitor.setAppSecret("9e8ba1244cfc4efbbcae77ecbaa605cf");

        return deviceMonitor;
    }

    private String getToken() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        String putUrl = "https://esopen.ys7.com/api/user/open-app/auth/gettoken";
        params.put("appKey", "ed87e19d76ef4bd2ad46166d980af092");
        params.put("appSecret", "9e8ba1244cfc4efbbcae77ecbaa605cf");
        String postBody = JSON.toJSONString(params);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
        int statusCode = response.getHttpReturnCode();

        if (statusCode == 200) {
            JSONObject jSONObject = JSONObject.parseObject(response.getContent());
            String token = jSONObject.getJSONObject("data").getString("accessToken");
            return token;
        }
        {
            throw new Exception("获取token失败。" + response);
        }
    }

    /**
     * 根据设备号获取token
     * @Param devSn 设备devSn
     */
    @Override
    public String getTokenByDevSn(String devSn,Integer channel){
        if (StringUtils.isEmpty(devSn)){
            return "";
        }
        DeviceMonitor monitor = this.getMonitorByDevSnChannel(devSn,channel);
        if (monitor == null){
            return "";
        }
        String appKey = monitor.getAppKey();
        String secret= monitor.getAppSecret();
        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(secret)){
            return "";
        }
        if (Boolean.TRUE.equals(redisTemplate.hasKey(appKey))) {
            return redisTemplate.opsForValue().get(appKey);
        }
        Map<String, String> querys = new HashMap<>();
        String url = "https://open.ys7.com/api/lapp/token/get";
        querys.put("appKey", appKey);
        querys.put("appSecret", secret);
        com.sinonc.iot.util.OKHttpResponse response = com.sinonc.iot.util.OKHttp.instance().post(url, null, null, querys);
        JSONObject jsonObject = JSONObject.parseObject(response.getContent());
        JSONObject data = jsonObject.getJSONObject("data");
        redisTemplate.opsForValue().set(appKey, data.getString("accessToken"), 5L, TimeUnit.MINUTES);
        return data.getString("accessToken");
    }

    /**
     * 根据设备号、通道号获取设备信息
     * @Param devSn 设备号
     * @Param channel 通道号
     * @return
     */
    public DeviceMonitor getMonitorByDevSnChannel(String devSn,Integer channel){
        return deviceMonitorMapper.getMonitorByDevSnChannel(devSn,channel);
    }

    /**
     * 抓拍图片
     */
    @Override
    public String capture(String devSn, Integer channel){
        if (StringUtils.isBlank(devSn) || channel==null){
            return "";
        }
        String token = this.getTokenByDevSn(devSn,channel);
        Map<String, String> querys = new HashMap<>();
        //鉴权token
        querys.put("accessToken", token);
        //设备序列号
        querys.put("deviceSerial", devSn);
        //通道号
        querys.put("channelNo", channel+"");

        String url = "https://open.ys7.com/api/lapp/device/capture";

        try{
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpMethod method = HttpMethod.POST;
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            URIBuilder builder = new URIBuilder(url);
            builder.addParameter("accessToken",token);
            builder.addParameter("deviceSerial",devSn);
            builder.addParameter("channelNo",channel+"");
            //将请求头部和参数合成一个请求
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);
            ResponseEntity<JSONObject> response = client.exchange(builder.build().toString(),method,requestEntity,JSONObject.class);
            //log.info(response.getBody().toString());
            JSONObject res = response.getBody();
            if (!CollectionUtils.isEmpty(res) &&"200".equals(res.getString("code"))){
                JSONObject data = res.getJSONObject("data");
                String imgUrl = data.getString("picUrl");
                if (StringUtils.isBlank(imgUrl)){
                    return "";
                }
                //new一个URL对象
                URL imgUrls = new URL(imgUrl);
                //打开链接
                HttpURLConnection conn = (HttpURLConnection)imgUrls.openConnection();
                //设置请求方式为"GET"
                conn.setRequestMethod("GET");
                //超时响应时间为5秒
                conn.setConnectTimeout(5 * 1000);
                //通过输入流获取图片数据
                InputStream inStream = conn.getInputStream();
                String realUrl = uploadService.upload(inStream, ContentType.IMAGE_JPEG,".jpg");
                if (StringUtils.isNotBlank(realUrl)){
                    return realUrl;
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return "";
    }

}
