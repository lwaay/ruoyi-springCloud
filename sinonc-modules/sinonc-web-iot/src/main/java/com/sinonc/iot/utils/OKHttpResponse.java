package com.sinonc.iot.utils;

public class OKHttpResponse {

	/**
	 * http返回码
	 */
	private int httpReturnCode;
	/**
	 * http返回的内容
	 */
	private String content;

	/**
	 * 请求的cookie
	 */
	private String cookie;

	public OKHttpResponse(int httpReturnCode, String content, String cookie) {
		this.httpReturnCode = httpReturnCode;
		this.content = content;
		this.cookie = cookie;
	}

	public int getHttpReturnCode() {
		return httpReturnCode;
	}

	public String getContent() {
		return content;
	}

	public String getCookIe() {
		return cookie;
	}

	@Override
	public String toString() {
		return "OKHttpResponse [httpReturnCode=" + httpReturnCode + ", content=" + content + ", cookie=" + cookie + "]";
	}

}
