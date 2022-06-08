package com.sinonc.iot.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.dto.InsectImgDto;
import com.sun.javafx.fxml.builder.URLBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2021/4/9  11:15
 */
@Slf4j
public class WarnUtils {

    public final static List<Map> getDeviceInfoList(String appId, String appSecret, String iotHost) {
        final String uri = iotHost + "/api/iot/third/deviceList";
        AjaxResult bodyAjaxResult = getBodyAjaxResult(appId, appSecret, uri);
        List data = (List) bodyAjaxResult.get("data");
        return data;
    }

    public final static AjaxResult getBodyAjaxResult(String appId, String appSecret, String uri) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("appId", appId);
        headers.add("secret", appSecret);
        try{
            ResponseEntity<AjaxResult> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    AjaxResult.class);
            AjaxResult bodyAjaxResult = response.getBody();
            return bodyAjaxResult;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static AjaxResult insectReport(String appId, String appSecret, String iotHost,Map<String,String> params){
        String url = iotHost + "/api/iot/app/insectReportByDevice" ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("appId", appId);
        headers.add("secret", appSecret);
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(params)){
                params.forEach((k,v)->{
                    builder.addParameter(k,v);
                });
            }
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AjaxResult> response = restTemplate.exchange(
                    builder.build(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    AjaxResult.class);
            AjaxResult bodyAjaxResult = response.getBody();
            return bodyAjaxResult;
        }catch (Exception e){
            log.error(e.getMessage(),e);

        }
        return AjaxResult.error();
    }

    public static AjaxResult insectTimeReportByType(String appId, String appSecret, String iotHost,Map<String,String> params){
        String url = iotHost + "/api/iot/app/insectTimeReportByType" ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("appId", appId);
        headers.add("secret", appSecret);
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(params)){
                params.forEach((k,v)->{
                    builder.addParameter(k,v);
                });
            }
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AjaxResult> response = restTemplate.exchange(
                    builder.build(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    AjaxResult.class);
            AjaxResult bodyAjaxResult = response.getBody();
            return bodyAjaxResult;
        }catch (Exception e){
            log.error(e.getMessage(),e);

        }
        return AjaxResult.error();
    }

    public static AjaxResult insectTotalReport(String appId, String appSecret, String iotHost,Map<String,String> params){
        String url = iotHost + "/api/iot/app/insectTotalReport" ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("appId", appId);
        headers.add("secret", appSecret);
        try {
            URIBuilder builder = new URIBuilder(url);
            if (!CollectionUtils.isEmpty(params)){
                params.forEach((k,v)->{
                    builder.addParameter(k,v);
                });
            }
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AjaxResult> response = restTemplate.exchange(
                    builder.build(),
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    AjaxResult.class);
            AjaxResult bodyAjaxResult = response.getBody();
            return bodyAjaxResult;
        }catch (Exception e){
            log.error(e.getMessage(),e);

        }
        return AjaxResult.error();
    }

}
