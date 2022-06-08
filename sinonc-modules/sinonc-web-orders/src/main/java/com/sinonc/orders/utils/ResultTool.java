package com.sinonc.orders.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ResultTool {

	public static final String result_code = "resultCode";

	public static final String message = "msg";

	public static final String data = "data";
	
	public static final String ROWS = "rows";

	public static final Integer status_code_ok = 200;

	public static final Integer status_code_param_error = 406;

	public static final Integer status_code_conflict = 409;

	public static final Integer status_code_notfound = 404;

	public static final Integer status_code_sign_error = 403;

	public static final Integer status_code_sys_error = 500;

	/**
	 * 状态正常
	 * @param result
	 */
	public static void resultCodeOk(Map<String, Object> result) {
		result.put(result_code, status_code_ok);
	}
	
	/**
	 * 参数错误
	 * @param result
	 */
	public static void paramsError406(Map<String, Object> result) {
		result.put(result_code, status_code_param_error);
		result.put(message, "参数错误");
	}

	/**
	 * 参数错误
	 * @param result
	 * @param errorMsg
	 */
	public static void paramsError406(Map<String, Object> result, String errorMsg) {
		result.put(result_code, status_code_param_error);
		if (!StringUtils.isEmpty(errorMsg)) {
			result.put(message, errorMsg);
		}
	}

	/**
	 * 业务冲突
	 * @param result
	 * @param errorMsg
	 */
	public static void conflict409(Map<String, Object> result, String errorMsg) {
		result.put(result_code, status_code_conflict);
		if (!StringUtils.isEmpty(errorMsg)) {
			result.put(message, errorMsg);
		}
	}
	
	/**
	 * 签名错误
	 * @param result
	 */
	public static void signError403(Map<String, Object> result) {
		result.put(result_code, status_code_sign_error);
		result.put(message, "签名错误");
	}

	/**
	 * 签名错误
	 * @param result
	 * @param errorMsg
	 */
	public static void signError403(Map<String, Object> result, String errorMsg) {
		result.put(result_code, status_code_sign_error);
		if (!StringUtils.isEmpty(errorMsg)) {
			result.put(message, errorMsg);
		}
	}
	
	/**
	 * 服务器错误
	 * @param result
	 */
	public static void sysError500(Map<String, Object> result) {
		result.put(result_code, status_code_sys_error);
		result.put(message, "服务器错误");
	}

	/**
	 * 服务器错误
	 * @param result
	 * @param errorMsg
	 */
	public static void sysError500(Map<String, Object> result, String errorMsg) {
		result.put(result_code, status_code_sys_error);
		if (!StringUtils.isEmpty(errorMsg)) {
			result.put(message, errorMsg);
		}
	}

	/**
	 * 资源不存在
	 * @param result
	 * @param errorMsg
	 */
	public static void notFound404(Map<String, Object> result, String errorMsg) {
		result.put(result_code, status_code_notfound);
		if (!StringUtils.isEmpty(errorMsg)) {
			result.put(message, errorMsg);
		}
	}
	
	/**
	 * 操作成功
	 * @Title: operateSuccess
	 * @param object 返回数据
	 * @param msg 返回消息
	 * @return
	 */
	public static Map<String,Object> operateSuccess(Object object,String msg){
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put(result_code, status_code_ok);
		resultMap.put(message, msg);
		resultMap.put(data, object);
		return resultMap;
	}
	
}
