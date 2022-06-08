package com.sinonc.openapi.aspect;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.api.client.util.Maps;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.IpUtils;
import com.sinonc.common.core.utils.ServletUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.openapi.domain.ApiLog;
import com.sinonc.openapi.domain.DataApi;
import com.sinonc.openapi.domain.DataApiConfig;
import com.sinonc.openapi.domain.DataApiItem;
import com.sinonc.openapi.dto.DataApiConfigDto;
import com.sinonc.openapi.mapper.ApiLogMapper;
import com.sinonc.openapi.mapper.DataApiConfigMapper;
import com.sinonc.openapi.mapper.DataApiItemMapper;
import com.sinonc.openapi.mapper.DataApiMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huanghao
 * @apiNote 日志
 * @date 2020/11/6 10:43
 */
@Aspect
@Component
@Slf4j
public class AuthAndLogAspect {

    @Autowired
    private ApiLogMapper apiLogMapper;
    @Autowired
    private DataApiItemMapper itemMapper;
    @Autowired
    private DataApiMapper apiMapper;
    @Autowired
    private DataApiConfigMapper configMapper;

    /**
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.sinonc.openapi.controller.api.*.*(..)) && !execution(public * com.sinonc.openapi.controller.api.*.putData(..)) ")
    public void authAndLogAspect() {
    }

    @Before("authAndLogAspect()")
    @SuppressWarnings("all")
    public void doBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String appLabel = request.getHeader("appLabel");
        String appKey = request.getHeader("appKey");
        if (StringUtils.isEmpty(appLabel) || StringUtils.isEmpty(appKey)) {
            throwBusinessException("请求失败：[appLabel]或[appKey] 不存在，请确保添加在请求头上");
        }
        DataApi dataApi = apiMapper.selectOne(new QueryWrapper<DataApi>().eq("system_label", appLabel).eq("status", 1));
        if (dataApi == null || !DateUtils.isEffectiveDate(new Date(), dataApi.getStartTime(), dataApi.getEndTime())) {
            throwBusinessException("请求失败：[appLabel] 请确保正确，以及该系统接口在有效期之内");
        }
        checkIp(dataApi, request);
        List<DataApiItem> apiItems = itemMapper.selectList(new QueryWrapper<DataApiItem>().in("id", dataApi.getOpenApi().split("[，,]"))
                .eq("status", Constants.ON));
        // 判断是否 url 匹配
        apiItems = apiItems.stream().filter(e -> e.getApiUrl().equals(request.getRequestURI())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(apiItems) || !dataApi.getPrivateKey().equals(appKey)) {
            throwBusinessException("请求失败：当前请求接口错误或 [appKey] 错误");
        }
        recordLog(request);
    }

    /**
     * 处理返回值
     *
     * @param o 返回值
     */
    @AfterReturning(returning = "o", pointcut = "authAndLogAspect()")
    @SuppressWarnings("all")
    public void doAfter(Object o) throws IllegalAccessException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String requestURI = request.getRequestURI();
        DataApiItem apiItem = itemMapper.selectOne(new QueryWrapper<DataApiItem>().eq("api_url", requestURI));
        if (apiItem == null) {
            throwBusinessException("不存在该接口：" + requestURI);
        }
        DataApiConfig apiConfig = configMapper.selectOne(new QueryWrapper<DataApiConfig>().eq("app_label", request.getHeader("appLabel")).eq("data_api_item_id", apiItem.getId()));
        if (apiConfig == null) {
            return;
        }
        List<DataApiConfigDto> dataApiConfigDtos = JSONArray.parseArray(apiConfig.getConfig(), DataApiConfigDto.class);
        if (o instanceof Map) {
            return;
        } else if (o instanceof R) {
            R r = (R) o;
            Map<String, Object> resultMap = beanToMap(r.getData());
            dataApiConfigDtos.stream().filter(e -> e.getCheck() == 0).forEach(e -> {
                resultMap.remove(e.getKey());
            });
            r.setData(resultMap);
        } else if (o instanceof TableDataInfo) {
            TableDataInfo target = (TableDataInfo) o;
            List<Map> rows = (List<Map>) target.getRows();
            rows.forEach(e -> {
                dataApiConfigDtos.stream().filter(e1 -> e1.getCheck() == 0).forEach(e1 -> {
                    e.remove(e1.getKey());
                });
            });
            target.setRows(rows);
        }
    }

    /**
     * 检查访问 ip 是否在白名单/黑名单
     *
     * @param dataApi 接口
     * @param request 请求
     */
    private void checkIp(DataApi dataApi, HttpServletRequest request) {
        String blackList = dataApi.getBlackList();
        String whiteList = dataApi.getWhiteList();
        String ipAddr = IpUtils.getIpAddr(request);
        log.info("当前请求 IP 地址 {}", ipAddr);
        String[] ipList = ipAddr.split(",", -1);
        if (StringUtils.isNotEmpty(blackList)) {
            boolean isBlack = false;
            for (String s : ipList) {
                isBlack = Arrays.asList(blackList.split("[,，]")).contains(s);
                if (isBlack) {
                    break;
                }
            }
            if (isBlack) {
                // 存在黑名单
                throwBusinessException("该请求 IP 已被加入黑名单");
            }
        }
        if (StringUtils.isNotEmpty(whiteList)) {
            boolean isWhite = false;
            for (String s : ipList) {
                isWhite = Arrays.asList(whiteList.split("[,，]")).contains(s);
                if(isWhite){
                    break;
                }
            }
            if (!isWhite) {
                // 不存在白名单
                throwBusinessException("该请求 IP 不在白名单");
            }
        }
    }

    /**
     * 统一抛出异常
     */
    private void throwBusinessException(String msg) {
        throw new BusinessException(msg);
    }

    /**
     * 记录日志
     */
    private void recordLog(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        log.info("recordLog：{}", ip);
        apiLogMapper.insert(ApiLog.builder()
                .createTime(new Date())
                .requestArg(Arrays.asList(request.getHeader("appLabel"), request.getHeader("appKey")).toString())
                .requestUrl(request.getRequestURL().toString())
                .ipAddress(ip)
                .build());
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
//    public static void main(String[] args) throws IllegalAccessException {
//        ApiLog apiLog = new ApiLog(1L, "1", "1", "1", new Date());
//        Map<String, Object> stringObjectMap = objectToMap(apiLog);
//        System.out.println(stringObjectMap);
//    }
}
