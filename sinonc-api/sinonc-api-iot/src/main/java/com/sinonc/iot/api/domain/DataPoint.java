package com.sinonc.iot.api.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 数据端点
 */
public class DataPoint implements Serializable {
	public String metric;//数据名称
	public String timestamp;//时间戳
	public String value;//数据值
	public Map<String, String> tags;//标记名称/标记值对的映射。至少要提供一对。  查询时的参数

	@Override
	public String toString() {
		return "DataPoint [metric=" + metric + ", timestamp=" + timestamp + ", value=" + value + ", tags=" + tags + "]";
	}

}
