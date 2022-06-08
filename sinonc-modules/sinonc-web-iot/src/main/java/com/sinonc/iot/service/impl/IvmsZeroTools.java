package com.sinonc.iot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.iot.domain.IvmsZeroCamera;
import com.sinonc.iot.domain.IvmsZeroPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxinlong
 * @date 2021/3/30  18:36
 */
public class IvmsZeroTools {

    public static final String APPKEY = "ab597210";
    public static final String SECRET = "829392d20f694404a5f5e4d6ee59b58a";
    private static final String OPENAPI_IP_PORT_HTTP = "http://218.201.61.90:8091";
    private static final String ITF_ADDRESS_GET_DEFAULT_USER_UUID = "/openapi/service/base/user/getDefaultUserUuid";
    private static final String GET_ENCODERDEVICESEX = "/openapi/service/vss/res/getEncoderDevicesEx";
    private static final String GET_NETZONES = "/openapi/service/base/netZone/getNetZones";
    private static final String GET_PREVIEWPARAMBYCAMERAUUID = "/openapi/service/vss/preview/getPreviewParamByCameraUuid";
    private static final String GET_CAMERAS = "/openapi/service/vss/res/getCameras";


    /**
     * 获取操作用户UUID
     *
     * @param appkey
     * @param secret
     * @return
     * @throws Exception
     */
    public static String getDefaultUserUuid(String appkey, String secret) throws Exception {
        String url = OPENAPI_IP_PORT_HTTP + ITF_ADDRESS_GET_DEFAULT_USER_UUID;
        Map<String, Object> map = new HashMap<String, Object>();
        //设置APPKEY
        map.put("appkey", appkey);
        //设置时间参数
        map.put("time", System.currentTimeMillis());
        String params = JSON.toJSONString(map);
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, secret), params);
        JSONObject obj = JSON.parseObject(data);
        String opuseruuid = obj.getString("data");
        return opuseruuid;
    }

    /**
     * 分页获取摄像头的播放地址列表
     *
     * @param opUserUuid
     * @param appkey
     * @param secret
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public static IvmsZeroPage getCameras(String opUserUuid, String appkey, String secret, Integer pageNo, Integer pageSize, String defaultCameaName) throws Exception {
        String url = OPENAPI_IP_PORT_HTTP + GET_CAMERAS;
        Map<String, Object> map = new HashMap<String, Object>();
        //设置APPKEY
        map.put("appkey", appkey);
        //设置时间参数
        map.put("time", System.currentTimeMillis());
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("opUserUuid", opUserUuid);
        String params = JSON.toJSONString(map);
        String data = HttpClientSSLUtils.doPost(url + "?token=" + Digests.buildToken(url + "?" + params, null, secret), params);
        JSONObject obj = JSON.parseObject(data);
        JSONObject dataJson = obj.getJSONObject("data");

        IvmsZeroPage ivmsZeroPage = new IvmsZeroPage();
        String errorCode = obj.getString("errorCode");
        ivmsZeroPage.setErrorCode(errorCode);

        String total = dataJson.getString("total");
        ivmsZeroPage.setTotal(total);

        JSONArray listJSONArray = dataJson.getJSONArray("list");

        List rsList = new ArrayList<>();
        ivmsZeroPage.setIvmsZeroCameraList(rsList);
        for (int i = 0; i < listJSONArray.size(); i++) {
            JSONObject camera = listJSONArray.getJSONObject(i);
            IvmsZeroCamera ivmsZeroCamera = buildIvmsZeroCamera(camera, defaultCameaName);
            rsList.add(ivmsZeroCamera);
        }

        return ivmsZeroPage;
    }

    /**
     * 构造摄像头监控对象
     *
     * @param camera
     * @return
     */
    public static IvmsZeroCamera buildIvmsZeroCamera(JSONObject camera, String defaultCameaName) {
        IvmsZeroCamera ivmsZeroCamera = new IvmsZeroCamera();

        String cameraName = camera.getString("cameraName");
        ivmsZeroCamera.setCameraName(cameraName);

        //非默认
        ivmsZeroCamera.setDefaultStatus("0");
        if (StringUtils.isNotEmpty(defaultCameaName)) {
            if (defaultCameaName.equals(cameraName)) {
                //设置为默认
                ivmsZeroCamera.setDefaultStatus("1");
            }
        }

        String cameraType = camera.getString("cameraType");
        ivmsZeroCamera.setCameraType(cameraType);

        String cameraUuid = camera.getString("cameraUuid");
        ivmsZeroCamera.setCameraUuid(cameraUuid);

        String onLineStatus = camera.getString("onLineStatus");
        ivmsZeroCamera.setOnLineStatus(onLineStatus);

        String playUrl = "/mag/hls/" + cameraUuid + "/0/live.m3u8";
        ivmsZeroCamera.setPlayUrl(playUrl);
        return ivmsZeroCamera;
    }

}
