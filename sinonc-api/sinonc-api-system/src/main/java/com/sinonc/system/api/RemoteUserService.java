package com.sinonc.system.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.factory.RemoteUserFallbackFactory;
import com.sinonc.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);

    /**
     * 分页获取用户信息
     */
    @PostMapping(value = "/user/listUser")
    public R<List<SysUser>> listUser(@RequestBody SysUser user);

    /**
     * 获取配置参数
     * @param configKey
     * @return
     */
    @GetMapping(value = "/user/configKey/{configKey}")
    public R<String> getConfigKey(@PathVariable("configKey") String configKey);

    @GetMapping(value = "/wxUser/info/selectUserInfoByIds")
    public R<List<WxUser>> infos(@RequestParam("ids") Long[] ids);

    @GetMapping(value = "/api/wxUser/getWxUserById/{id}")
    public R<WxUser> getWxUserById(@PathVariable("id") Long id);

    @GetMapping(value = "/info/getAllUsers")
    public R<List<WxUser>> allUsers();

    @GetMapping("/api/buss/getAllBusinessEntity")
    public R<List<BusinessEntity>> getAllBusinessEntity();
}
