package com.sinonc.common.core.constant;

import java.nio.charset.StandardCharsets;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {

    public static final Integer WHITE_CODE = 520;

    public static final String BIG_SCREEN = "1";

    public static final String PRODUCT = "PRODUCT:";

    /**
     * 检测密码是否包含大小写，数字，特殊字符
     */
    public static final String PW_PATTERN = "^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAIL = 500;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌有效期（分钟）
     */
    public final static long TOKEN_EXPIRE = 720;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    /**
     * 启动
     */
    public static final Integer ON = 1;

    /**
     * 按 销售额 排序
     */
    public static final Integer SALE_PRICE = 0;
    /**
     * 按 销售量 排序
     */
    public static final int SALE_COUNT = 1;
    /**
     * 按 单价 排序
     */
    public static final int PRICE = 2;
    /**
     * 打开
     */
    public static final String IS_OPEN = "1";
    /**
     * 关闭
     */
    public static final String IS_CLOSE = "0";

    public static final Long[] typeIds = new Long[]{50008056L, 50008618L, 124294001L};

    public static final Long ORIGINS = 8L;

    public static final String FROM = "本平台";
    public static final String STATUS = "正常";

}
