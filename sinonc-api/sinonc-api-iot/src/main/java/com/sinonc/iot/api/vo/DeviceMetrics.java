package com.sinonc.iot.api.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备要素
 */
public class DeviceMetrics {

	public String deviceId;//设备id

	private Map<String,List<String>> map;//key:设备id value要素List

	public Map<String, List<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<String>> map) {
		this.map = map;
	}
	public List<String> metrics= new ArrayList<String>();
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<String> metrics) {
		this.metrics = metrics;
	}

}
