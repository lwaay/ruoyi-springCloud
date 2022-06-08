package com.sinonc.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 放行白名单配置
 *
 * @author ruoyi
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {
    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    private List<String> whites = new ArrayList<>();
    /**
     * 大屏前端项目使用的接口
     */
    private static final List<String> VIEW_LOGIN_USE_API = new ArrayList<>(Arrays.asList(
            "/api/statistic/**",
            "/api/item/**",
            "/api/app/**",
            "/api/notice/**",
            "/api/hlb/**",
            "/api/bigScreen/**",
            "/api/group/**",
            "/api/base/device/**",
            "/agri/agriculture/api/selectGrowTechList",
            "/agri/agriculture/api/selectGrowTechById/**",
            "/agri/agriculture/api/summGrowTechShareCount**",
            "/api/guide/**",
            "/avue/**",
            "/api/base/menu/**",
            "/v1/**",
            "/jmreport/desreport/**",
            "/api/base/api/menu/list",
            "/system/user/sendSmsCode",
            "/system/user/sendCode",
            "/system/api/wxUser/sendCode/**",
            "/system/user/checkSmsCode",
            "/system/user/changePassword",
            "/auth/ipWhite",
            "/auth/wxLogin",
            "/auth/wxAppRegister",
            "/auth/phoneLogin",
            "/auth/registerUser",
            "/auth/wxUserLogin",
            "/system/examine",
            "/auth/api/login",
            "/auth/**",
            "/base/api/seller/**",
            "/system/api/dict/type/**",
            "/system/api/consume/sendCode/**",
            "/system/api/consume/consumerWxLogin",
            "/origins/api/price/**",
            "/orders/api/orders/notices/countNotRead",
            "/orders/api/odgood/getOdGoodList**",
            "/orders/api/advertisement/list**",
            "/orders/api/shop/getShopList**",
            "/orders/api/goods/selectgoodsbytype**",
            "/origins/api/index/analysis/breed**"
    ));

    public List<String> getWhites() {
        return whites;
    }

    public void setWhites(List<String> whites) {
        this.whites = whites;
    }

    /**
     * 大屏登陆接口
     *
     * @return 登陆接口集合
     */
    public List<String> getViewLoginUseApi() {
        return VIEW_LOGIN_USE_API;
    }

    /**
     * 大屏登陆接口+白名单
     *
     * @return list
     */
    public List<String> getWhitesWithView() {
        List<String> l = new ArrayList<>();
        l.addAll(getWhites());
        l.addAll(VIEW_LOGIN_USE_API);
        return l;
    }
}
