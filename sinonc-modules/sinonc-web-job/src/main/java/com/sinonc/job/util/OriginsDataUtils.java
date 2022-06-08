package com.sinonc.job.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.base.api.domain.OriginsCrop;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取溯源数据
 * @Param url  请求地址
 * @Param params 请求参数
 *        params.sign 权限签名
 *        params.rows 页大小
 *        params.page 页码
 * @Param clazz  返回实体类
 *  @author lqqu
 */
@Slf4j
public class OriginsDataUtils {

    public static JSONObject getOriginsData(String url, Map<String,String> params) throws Exception{

        if (StringUtils.isEmpty(url) || CollectionUtils.isEmpty(params)){
             throw new BusinessException("请求参数错误,请确认参数后查询数据");
         }
         if (StringUtils.isEmpty(params.get("sign"))){
             throw new BusinessException("获取签名失败,请确认签名后查询数据");
         }
        HttpUrl.Builder urlBuilder =HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("sign", params.get("sign"));
        urlBuilder.addQueryParameter("page",StringUtils.isEmpty(params.get("page"))?"1":params.get("page"));
        urlBuilder.addQueryParameter("rows",StringUtils.isEmpty(params.get("rows"))?"1":params.get("rows"));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
                .build();
        OkHttpClient httpClient = builder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body == null){
                throw new BusinessException("获取第三方平台数据失败. "+ DateUtils.dateTimeNow());
            }
            String data = body.string();
            if (StringUtils.isEmpty(data)){
                throw new BusinessException("获取第三方平台数据失败. "+ DateUtils.dateTimeNow());
            }
            JSONObject object = JSON.parseObject(data);
            if (object == null || object.isEmpty()){
                throw new BusinessException("解析第三方平台数据失败. "+ DateUtils.dateTimeNow());
            }
            if (!"0".equals(object.get("status"))){
                throw new BusinessException(object.get("message")+"  "+ DateUtils.dateTimeNow());
            }
            return object;
        }catch (BusinessException be){
            throw be;
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
    }
}
