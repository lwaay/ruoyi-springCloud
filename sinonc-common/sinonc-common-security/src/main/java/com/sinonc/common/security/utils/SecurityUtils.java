package com.sinonc.common.security.utils;

import com.sinonc.common.core.constant.CacheConstants;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.ServletUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 权限获取工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    /**
     * 获取用户
     */
    public static String getUsername() {
        return ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USERNAME);
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return Convert.toLong(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USER_ID));
    }

    /**
     * 获取主体id
     */
    public static Long getEntity(){
        return Convert.toLong(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_ENTITY));
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin() {
        return Convert.toBool(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_ISADMIN));
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
