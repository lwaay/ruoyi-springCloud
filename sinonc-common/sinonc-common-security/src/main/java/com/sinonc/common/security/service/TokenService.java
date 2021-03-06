package com.sinonc.common.security.service;

import com.alibaba.fastjson.JSON;
import com.sinonc.common.core.constant.CacheConstants;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.utils.IdUtils;
import com.sinonc.common.core.utils.ServletUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.order.api.RemoteShopService;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.system.api.RemoteEntityService;
import com.sinonc.system.api.model.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class TokenService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RemoteShopService remoteShopService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 14;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginUser loginUser) {
        // 生成token
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        loginUser.setUserid(loginUser.getSysUser().getUserId());
        loginUser.setUsername(loginUser.getSysUser().getUserName());
        loginUser.setEntityId(loginUser.getSysUser().getEntityId());
        refreshToken(loginUser);
        //用户密码是否过期
        boolean hasKey = redisService.hasKey(UserConstants.USER + loginUser.getSysUser().getUserId());
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("is_expire", hasKey);
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.MINUTES);
        return map;
    }

    /**
     * 创建令牌
     */
    public Map<String, Object> createWxUserToken(LoginUser loginUser) {
        // 生成token
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        loginUser.setUserid(loginUser.getWxUser().getId());
        loginUser.setUsername(loginUser.getWxUser().getName());
        String entityId = loginUser.getWxUser().getEntityId();
        if(!StringUtils.isEmpty(entityId)){
            loginUser.setEntityId(Long.valueOf(entityId));
        }
        refreshToken(loginUser);
        //用户密码是否过期
        boolean hasKey = redisService.hasKey(UserConstants.WX_USER + loginUser.getWxUser().getId());
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("is_expire", hasKey);
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        map.put("wxUser", loginUser.getWxUser());

        //判断用户是否有店铺
        if(StringUtils.isNotEmpty(entityId)){
            List<Shop> shopList = remoteShopService.getShopListByEntityId(Long.valueOf(entityId)).getData();
            map.put("hasShop", Optional.ofNullable(shopList).isPresent());
        }
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.MINUTES);
        return map;
    }

     /**
     * 创建令牌
     */
    public Map<String, Object> createWxUserConsumeToken(LoginUser loginUser) {
        // 生成token
        String token = IdUtils.fastUUID();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(JSON.toJSONString(loginUser));
        loginUser.setToken(token);
        loginUser.setUserid(loginUser.getWxUserConsume().getId());
        loginUser.setUsername(loginUser.getWxUserConsume().getName());
        loginUser.setEntityId(loginUser.getWxUserConsume().getEntityId());
        refreshToken(loginUser);
        //用户密码是否过期
        boolean hasKey = redisService.hasKey(UserConstants.WX_USER_CONSUME + loginUser.getWxUserConsume().getId());
        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("is_expire", hasKey);
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        map.put("wxUserConsume", loginUser.getWxUserConsume());
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUser, EXPIRE_TIME, TimeUnit.MINUTES);
        return map;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser() {
        return getLoginUser(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            LoginUser user = redisService.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public Long refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisService.setCacheObject(userKey, loginUser, EXPIRE_TIME, TimeUnit.SECONDS);
        return EXPIRE_TIME;
    }

    private String getTokenKey(String token) {
        return ACCESS_TOKEN + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
