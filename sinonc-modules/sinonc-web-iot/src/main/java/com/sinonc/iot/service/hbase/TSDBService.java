package com.sinonc.iot.service.hbase;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.iot.api.domain.DataPoint;
import com.sinonc.iot.api.vo.DeviceMetrics;
import com.sinonc.iot.util.DateUtil;
import com.sinonc.iot.util.OKHttp;
import com.sinonc.iot.util.OKHttpResponse;
import com.sinonc.iot.vo.QueryDataVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;


@Service
public class TSDBService {

    @Value("${tsdb.host}")
    public String tsdbHost;

    public static Map<String, String> header = new HashMap<>(2);

    static {
        header.put("Content-Type", "application/json");
        header.put("X_REQUEST_WITH", "XMHHttpRequest");
    }

    private static final Logger logger = LoggerFactory.getLogger(TSDBService.class.getName());
    private final static int SYNC_TIMEOUT_MS = 60 * 1000;

    public boolean put(List<DataPoint> dataPoints) {
        if (dataPoints == null || dataPoints.size() == 0) {
            return false;
        }
        String putUrl = tsdbHost + "/api/put?sync_timeout=" + SYNC_TIMEOUT_MS;
//		http://114.55.250.211:4242/
//		String putUrl = "http://111.75.255.48:8060/tsdb/api/put?sync_timeout=60000";
        logger.info(putUrl);
        String dataPointsJson = JSON.toJSONString(dataPoints);
        logger.info(dataPointsJson);
        OKHttpResponse response = OKHttp.instance().post(putUrl, dataPointsJson, header, null);
        int statusCode = response.getHttpReturnCode();
        logger.info("response statusCode:" + statusCode);
        if (statusCode == 200 || statusCode == 204) {
            logger.info("put OK data:" + dataPointsJson);

            return true;
        } else {
            logger.info("put ERROR");

            return false;
        }

    }

    /**
     * @param start         开始时间
     * @param end           结束时间
     * @param spotIdMetrics 查询参数
     * @param aggregator    聚合函数 例如sum avg
     * @param groupBy       分组
     * @return
     */
    public Map<String, Object> query(Integer start, Integer end, DeviceMetrics spotIdMetrics, String aggregator,
                                     Boolean groupBy, String downsample) {
        // check params
        if (groupBy == null) {
            groupBy = true;
        }
        if (StringUtils.isEmpty(aggregator)) {
            aggregator = "avg";
        }
        String putUrl = tsdbHost + "/api/query";
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, List<String>> map = spotIdMetrics.getMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            List<String> list = map.get(spotIdMetrics.deviceId);
            for (String metric : list) {
                Map<String, Object> params = new HashMap<String, Object>();
                List<Map<String, Object>> queries = new ArrayList<Map<String, Object>>();
                params.put("start", start);
                params.put("end", end);
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("metric", metric);
                queryParam.put("aggregator", aggregator);
                queryParam.put("downsample", downsample);
                // build filter
                List<Map<String, Object>> filters = new ArrayList<Map<String, Object>>();
                Map<String, Object> spotfilter = new HashMap<String, Object>();
                spotfilter.put("type", "literal_or");
                spotfilter.put("tagk", "deviceId");
                spotfilter.put("filter", entry.getKey().toString());
                spotfilter.put("groupBy", groupBy);
                filters.add(spotfilter);

                queryParam.put("filters", filters);
                queries.add(queryParam);
                params.put("queries", queries);
                String postBody = JSON.toJSONString(params);
                OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
                int statusCode = response.getHttpReturnCode();

                if (statusCode == 200) {
                    JSONArray array = JSONArray.parseArray(response.getContent());
                    if (array.size() > 0) {
                        JSONObject jsonObject = array.getJSONObject(0);
                        data.put(entry.getKey().toString() + ":" + metric, jsonObject.getString("dps"));
                    }
                } else {
                    continue;
                }
            }
        }

        return data;
    }

    /**
     * @param start         开始时间
     * @param end           结束时间
     * @param spotIdMetrics 查询参数
     * @param aggregator    聚合函数 例如sum avg
     * @param groupBy       分组
     * @return
     */
    public Map<String, Object> query(String start, String end, DeviceMetrics spotIdMetrics, String aggregator,
                                     Boolean groupBy, String downsample) {
        // check params
        if (groupBy == null) {
            groupBy = true;
        }
        if (StringUtils.isEmpty(aggregator)) {
            aggregator = "avg";
        }
        String putUrl = tsdbHost + "/api/query";
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, List<String>> map = spotIdMetrics.getMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            List<String> list = map.get(spotIdMetrics.deviceId);
            for (String metric : list) {
                Map<String, Object> params = new HashMap<String, Object>();
                List<Map<String, Object>> queries = new ArrayList<Map<String, Object>>();
                params.put("start", start);
                if (!end.isEmpty()) {
                    params.put("end", end);
                }
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("metric", metric);
                queryParam.put("aggregator", aggregator);
                queryParam.put("downsample", downsample);
                // build filter
                List<Map<String, Object>> filters = new ArrayList<Map<String, Object>>();
                Map<String, Object> spotfilter = new HashMap<String, Object>();
                spotfilter.put("type", "literal_or");

                spotfilter.put("tagk", "deviceId");
                spotfilter.put("filter", entry.getKey().toString());
                spotfilter.put("groupBy", groupBy);
                filters.add(spotfilter);

                queryParam.put("filters", filters);
                queries.add(queryParam);
                params.put("queries", queries);
                String postBody = JSON.toJSONString(params);
                OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
                int statusCode = response.getHttpReturnCode();

                if (statusCode == 200) {
                    JSONArray array = JSONArray.parseArray(response.getContent());
                    if (array.size() > 0) {
                        JSONObject jsonObject = array.getJSONObject(0);
                        data.put(entry.getKey().toString() + ":" + metric, jsonObject.getString("dps"));
                    }
                } else {
                    continue;
                }

            }
        }


        return getSortData(data);
    }

    /**
     * @param dataVo 查询参数
     * @return
     */
    public Map<String, Object> query(QueryDataVo dataVo) {
        System.out.println(dataVo.toString());
        String aggregator = dataVo.getAggregator();
        String downsample = dataVo.getDownsample();
        String metrics = dataVo.getMetric();
        String start = dataVo.getStartTime();
        String end = dataVo.getEndTime();
        if (StringUtils.isEmpty(dataVo.getAggregator())) {
            aggregator = "avg";
        }
        String putUrl = tsdbHost + "/api/query";
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        if (!StringUtils.isEmpty(end)) {
            params.put("end", end);
        }
        //OpenTsdb子查询集合
        List<Map<String, Object>> queries = new ArrayList<Map<String, Object>>();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("metric", metrics);
        queryParam.put("aggregator", aggregator);
        queryParam.put("downsample", downsample);
        // 子查询中过滤器集合
        List<Map<String, Object>> filters = new ArrayList<Map<String, Object>>();
        Map<String, String> querys = dataVo.getParams();
        for (Map.Entry<String, String> stringStringEntry : querys.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "literal_or");
            map.put("tagk", ((Map.Entry) stringStringEntry).getKey().toString());
            map.put("filter", ((Map.Entry) stringStringEntry).getValue().toString());
            map.put("groupBy", true);
            filters.add(map);
        }

        queryParam.put("filters", filters);
        queries.add(queryParam);
        params.put("queries", queries);
        String postBody = JSON.toJSONString(params);
        OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
        int statusCode = response.getHttpReturnCode();

        if (statusCode == 200) {
            JSONArray array = JSONArray.parseArray(response.getContent());
            if (array.size() > 0) {
                JSONObject jsonObject = array.getJSONObject(0);
                data.put("result", jsonObject.getString("dps"));
            }
        }


        return getSortData(data);
    }

    public boolean delete(String start, String end, String metric) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("metric", metric);
//		 queryParam.put("start","1589244293");
//		 queryParam.put("end","1589334321");
        queryParam.put("start", start);
        queryParam.put("end", end);
        String putUrl = tsdbHost + "/api/delete_data";
        OKHttpResponse response = OKHttp.instance().post(putUrl, queryParam.toString(), header, null);
        System.out.println(response.getContent());
        return true;

    }

    /**
     * 将时间排序并输出数据
     *
     * @param data
     * @return
     */
    public Map<String, Object> getSortData(Map<String, Object> data) {
        JSONObject jsonObject = new JSONObject();
        for (String key : data.keySet()) {
            Object value = data.get(key);
            String datas = (String) value;
            jsonObject.put(key, datas);
//            System.out.println(jsonObject);
        }
        //取出data数据
        Map<String, Object> map = jsonObject.getInnerMap();
        Iterator iterator = map.entrySet().iterator();

        JSONObject valueJson = new JSONObject();
        //取出qps数据
        Map<String, Object> value = new HashMap<>();
        //排序时间列表
        List<String> timeList = new ArrayList<>();
        //不排序时间列表
        List<String> timeList2 = new ArrayList<>();
        //最终数据
        Map<String, Object> resultMap = new HashMap<>();
        //值
        List<List<String>> lists = new ArrayList<>();
        //时间
        List<List<String>> lists2 = new ArrayList<>();

        while (iterator.hasNext()) {
            List<String> time = new ArrayList<>();
            List<String> values = new ArrayList<>();
            Map.Entry entry = (Map.Entry) iterator.next();
            valueJson = (JSONObject) JSONObject.parse(entry.getValue().toString());
            //取出qps数据
            value = valueJson.getInnerMap();
            Iterator valueIter = value.entrySet().iterator();

            while (valueIter.hasNext()) {
                Map.Entry it = (Map.Entry) valueIter.next();
                //取出时间
                timeList.add(formatDate(Integer.parseInt(it.getKey().toString())));
                timeList2.add(it.getKey().toString());
            }
            //排序时间
            Collections.sort(timeList);
            Collections.sort(timeList2);
            for (int i = 0; i < timeList2.size(); i++) {
                //取出时间相对应的数据
                BigDecimal values2 = (BigDecimal) value.get(timeList2.get(i));
                //将对应的值存入list //取两位小数
                values.add(String.format("%.2f", values2));
                //将对应的时间存入list
                time.add(timeList.get(i));
            }

            lists.add(values);

            lists2.add(time);
            resultMap.put("time", lists2);
            resultMap.put("values", lists);
        }
        return resultMap;
    }

    /**
     * 将时间戳转换成正常的时间
     *
     * @param time
     * @return str 截取成 时:分 模式
     * @throws
     * @Title: formatDate
     * @author WXH
     */
    public String formatDate(Integer time) {
        Date date = new Date(time * 1000L);
        return DateUtil.parseDate(date);
    }


    public Map<String, Object> executeQuery(QueryDataVo dataVo) {
        String aggregator = dataVo.getAggregator();
        String downsample = dataVo.getDownsample();
        String metrics = dataVo.getMetric();
        String start = dataVo.getStartTime();
        String end = dataVo.getEndTime();
        if (StringUtils.isEmpty(dataVo.getAggregator())) {
            aggregator = "avg";
        }
        String putUrl = tsdbHost + "/api/query";
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("start", start);
        if (!StringUtils.isEmpty(end)) {
            params.put("end", end);
        }
        //OpenTsdb子查询集合
        List<Map<String, Object>> queries = new ArrayList<Map<String, Object>>();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("metric", metrics);
        queryParam.put("aggregator", aggregator);
        queryParam.put("downsample", downsample);
        // 子查询中过滤器集合
        List<Map<String, Object>> filters = new ArrayList<Map<String, Object>>();
        Map<String,String> querys=dataVo.getParams();
        Iterator iterator = querys.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Map<String,Object> map=new HashMap<>();
            map.put("type","literal_or");
            map.put("tagk",entry.getKey().toString());
            map.put("filter",entry.getValue().toString());
            map.put("groupBy",true);
            filters.add(map);
        }

        queryParam.put("filters", filters);
        queries.add(queryParam);
        params.put("queries", queries);
        String postBody = JSON.toJSONString(params);
        OKHttpResponse response = OKHttp.instance().post(putUrl, postBody, header, null);
        int statusCode = response.getHttpReturnCode();

        if (statusCode == 200) {
            JSONArray array = JSONArray.parseArray(response.getContent());
            if (array.size() > 0) {
                JSONObject jsonObject = array.getJSONObject(0);
                data.put("result", jsonObject.getString("dps"));
            }
        }
        return data;
    }

}
