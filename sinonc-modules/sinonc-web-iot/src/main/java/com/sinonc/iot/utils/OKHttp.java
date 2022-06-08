package com.sinonc.iot.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.Request.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OKHttp {

	private static final Logger logger = LoggerFactory.getLogger(OKHttp.class);

	private static final OKHttp singleton = new OKHttp();

	private static OkHttpClient client;

	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final MediaType TEXT = MediaType.parse("text/text; charset=utf-8");
	private OKHttp() {
		client = new OkHttpClient();
	}

	public static OKHttp instance() {
		return singleton;
	}

	public OKHttpResponse post(String url, String data) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		return post(url, data, headers, null);
	}

	public OKHttpResponse post(String url, String data, Map<String, String> headers, Map<String, String> querys) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		if (null == data || "".equals(data)) {
			data = new JSONObject().toJSONString();
		}

		RequestBody body = RequestBody.create(JSON, data);
		Builder requestBuilder = new Builder();
		// 构造头部
		if (headers != null && headers.isEmpty() == false) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				requestBuilder.addHeader(header.getKey(), header.getValue());
			}
		}
		// 构造请求参数
		if (querys != null && querys.isEmpty() == false) {
			for (Map.Entry<String, String> query : querys.entrySet()) {
				urlBuilder.addQueryParameter(query.getKey(), query.getValue().toString());
			}
		}


		Request request = requestBuilder.url(urlBuilder.build()).post(body).build();
		OKHttpResponse okHttpResponse = null;
		try {
			client.newBuilder().connectTimeout(3, TimeUnit.SECONDS);
			Response response = client.newCall(request).execute();
			Headers response_headers = response.headers();
			String Cookie = response_headers.get("Set-Cookie");
			int returnCode = response.code();
			String content = response.body().string();
			okHttpResponse = new OKHttpResponse(returnCode, content, Cookie);
		} catch (Exception e) {
			logger.error("okhttp execute failed.", e);
		}
		return okHttpResponse;
	}

	public OKHttpResponse get(String url, Map<String, String> headers, Map<String, String> querys) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		Builder requestBuilder = new Builder();
		// 构造头部
		if (headers != null && headers.isEmpty() == false) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				requestBuilder.addHeader(header.getKey(), header.getValue());
			}
		}
		// 构造请求参数
		if (querys != null && querys.isEmpty() == false) {
			for (Map.Entry<String, String> query : querys.entrySet()) {
				urlBuilder.addQueryParameter(query.getKey(), query.getValue().toString());
			}
		}

		Request request = requestBuilder.url(urlBuilder.build()).build();

		logger.info("The request url is : " + request.url());
		OKHttpResponse okHttpResponse = null;
		try {
			client.newBuilder().connectTimeout(3, TimeUnit.SECONDS);
			Response response = client.newCall(request).execute();
			int returnCode = response.code();
			String content = response.body().string();
			Headers response_headers = response.headers();
			String Cookie = response_headers.get("Set-Cookie");
			okHttpResponse = new OKHttpResponse(returnCode, content, Cookie);
		} catch (Exception e) {
			logger.error("okhttp execute failed.", e);
		}
		return okHttpResponse;
	}


}
