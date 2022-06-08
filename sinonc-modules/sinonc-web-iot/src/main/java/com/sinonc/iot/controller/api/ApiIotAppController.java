package com.sinonc.iot.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.constant.AppInfoConfing;
import com.sinonc.iot.dto.InsectImgDto;
import com.sinonc.iot.service.DeviceDataService;
import com.sinonc.iot.service.IInsectImgService;
import com.sinonc.iot.util.WarnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author: yehuiwang
 * @date: 2020/12/10 10:59
 * @description:
 */
//@Api(tags = "app物联网接口")
@RestController
@RequestMapping("api/iot/app")
public class ApiIotAppController {

    @Autowired
    private IInsectImgService imgService;
    @Autowired
    private DeviceDataService deviceDataService;

    @Autowired
    private RestTemplate restTemplateTo;

    @Autowired
    private AppInfoConfing appInfo;


    //    @ApiOperation("分页查询虫情图片")
    @PostMapping("insectImg")
    public TableDataInfo getImg(@RequestBody InsectImgDto insectImgDto) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        Map<String, Object> map = getImg(insectImgDto.getDeviceId(),insectImgDto.getPageNum(),insectImgDto.getPageSize(), restTemplateTo);
        List templist = (List) map.get("data");
        Integer total = (Integer) map.get("total");
        rspData.setRows(templist);
        rspData.setTotal(total);
        return rspData;
    }

   /* @GetMapping("queryImg")
    public AjaxResult queryImg(String deviceId) {
        Map<String, Object> resMap = getImg(deviceId, restTemplateTo);
        List templist = (List) resMap.get("data");
        AjaxResult success = AjaxResult.success(templist);
        return success;
    }*/

    @GetMapping("/insectReportByDevice")
    public AjaxResult insectReport(String deviceId, String start, String end){
        Map<String,String> param = new HashMap<>();
        param.put("deviceId",deviceId);
        param.put("start",start);
        param.put("end",end);
        return WarnUtils.insectReport(appInfo.getAppId(),appInfo.getAppSecret(),appInfo.getIotHost(),param);
    }

    @GetMapping("/insectTimeReportByType")
    public AjaxResult insectReport(String deviceId,String type){
        Map<String,String> param = new HashMap<>();
        param.put("deviceId",deviceId);
        param.put("type",type);
        return WarnUtils.insectTimeReportByType(appInfo.getAppId(),appInfo.getAppSecret(),appInfo.getIotHost(),param);
    }

    @GetMapping("/insectTotalReport")
    public AjaxResult insectTotalReport(String deviceId){
        Map<String,String> param = new HashMap<>();
        param.put("deviceId",deviceId);
        return WarnUtils.insectTotalReport(appInfo.getAppId(),appInfo.getAppSecret(),appInfo.getIotHost(),param);
    }


    /**
     * 获取设备数据统计
     * @param startDate
     * @param endDate
     * @param deviceId
     * @return
     */
    @GetMapping("/getProdEnviStatisticsDto")
    public AjaxResult getProdEnviStatisticsDto(String startDate, String endDate,String deviceId) {
        Date startDateConvert = DateUtils.parseDate(startDate);
        Date endDateConvert = DateUtils.parseDate(endDate);
        ProdEnviStatisticsDto prodEnviStatisticsDto=new ProdEnviStatisticsDto();
        //ProdEnviStatisticsDto prodEnviStatisticsDto= deviceDataService.getProdEnviStatisticsDto(startDateConvert,endDateConvert,deviceId);
        AjaxResult success = AjaxResult.success(prodEnviStatisticsDto);
        return success;
    }


    private Map<String, Object> getImg(String deviceId,Integer pageNum,Integer pageSize, RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appId", appInfo.getAppId());
        headers.set("secret", appInfo.getAppSecret());
        Map<String, String> sizeMap = new HashMap<>();

        sizeMap.put("pageNum", String.valueOf(pageNum));
        sizeMap.put("pageSize", String.valueOf(pageSize));
        if (StringUtils.isNotEmpty(deviceId)) {
            sizeMap.put("deviceId", deviceId);
        }

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(sizeMap, headers);
        ResponseEntity<String> exchage = restTemplate.exchange("http://zxyniot.sinonc.cn/api/iot/app/insectImg", HttpMethod.POST, httpEntity, String.class);

        JSONObject res = JSONObject.parseObject(exchage.getBody());

        Map resMap = new HashMap();
        resMap.put("total", res.getIntValue("total"));

        JSONArray jsONArray = res.getJSONArray("data");

        List dataList = new ArrayList();

        for (int i = 0; i < jsONArray.size(); i++) {
            JSONObject jsONObject = jsONArray.getJSONObject(i);
            Map tempMap = new HashMap();
            tempMap.put("createTime", jsONObject.getString("createTime"));
            tempMap.put("id", jsONObject.getString("id"));
            tempMap.put("deviceId", jsONObject.getString("deviceId"));
            tempMap.put("imgUrl", jsONObject.getString("imgUrl"));
            tempMap.put("resultJson", jsONObject.getString("resultJson"));
            tempMap.put("resultImgUrl", jsONObject.getString("resultImgUrl"));
            dataList.add(tempMap);
        }

        resMap.put("data", dataList);

        return resMap;
    }


}
