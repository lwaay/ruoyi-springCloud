package com.sinonc.iot.api.format;


import com.sinonc.iot.api.domain.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

;

/**
 * 数据格式化及存入tsdb
 */
public class DataFormat {

    /**
     * 配置数据端点
     *
     * @param metric
     * @param value
     * @param tags
     * @return
     */
    public DataPoint getDataPoint(String time, String metric, String value, Map<String, String> tags) {
        DataPoint dataPoint = new DataPoint();
        dataPoint.metric = metric;
        dataPoint.value = value;
        dataPoint.timestamp = time;
        dataPoint.tags = new HashMap<>();
        Iterator it = tags.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();
            dataPoint.tags.put(entry.getKey().toString(), entry.getValue().toString());
        }

        return dataPoint;
    }




    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s){
        String res = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;

    }
}
