package com.sinonc.agriculture.vo;


import com.sinonc.common.core.web.domain.AjaxResult;

/**
 * 自定义返回结果,对AjaxResult进行增强
 */
public class R extends AjaxResult {

    /**
     * 支出自定义错误代码
     * @param code 错误代码
     * @param msg msg
     * @return 结果
     */
    public static AjaxResult error(Integer code, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", code);
        ajaxResult.put("msg", msg);
        return ajaxResult;
    }

}
