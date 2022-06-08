package com.sinonc.common.core.constant;

/**
 * 缓存的key 常量
 *
 * @author ruoyi
 */
public class CacheConstants {
    /**
     * 令牌自定义标识
     */
    public static final String HEADER = "Authorization";
    /**
     * 积木报表请求令牌
     */
    public static final String JI_MU_TOKEN = "X-Access-Token";
    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 用户ID字段
     */
    public static final String DETAILS_USER_ID = "user_id";

    /**
     * 用户名字段
     */
    public static final String DETAILS_USERNAME = "username";

    /**
     * 用户名字段
     */
    public static final String DETAILS_ENTITY = "entity";

    /**
     * 用户名字段
     */
    public static final String DETAILS_ISADMIN = "is_admin";
}
