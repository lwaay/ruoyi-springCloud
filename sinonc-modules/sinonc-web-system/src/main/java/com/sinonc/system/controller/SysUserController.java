package com.sinonc.system.controller;

import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.common.sms.SmsService;
import com.sinonc.system.api.domain.SysRole;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.service.*;
import com.sinonc.system.vo.ResetVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysPermissionService permissionService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsService smsService;


    /**
     * 获取用户列表
     */
    @PreAuthorize(hasPermi = "system:user:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 修改新密码
     *
     * @param resetVo
     * @return
     */
    @PostMapping("/changePassword")
    public R<Boolean> newPassword(@RequestBody ResetVo resetVo) {
        if (StringUtils.isEmpty(resetVo.getUsername()) || StringUtils.isEmpty(resetVo.getPhone()) || StringUtils.isEmpty(resetVo.getPassword())) {
            return R.fail("参数错误！");
        }
        SysUser sysUser = userService.selectUserByPhone(resetVo.getPhone());
        if(!Optional.ofNullable(sysUser).isPresent()){
            return R.fail("该号码未注册！");
        }
        if(resetVo.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || resetVo.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH){
            return R.fail("密码必须是8位以上20位以下");
        }
        if(!resetVo.getPassword().matches(Constants.PW_PATTERN)){
            return R.fail("密码必须包含大小写字母、数字和特殊字符串");
        }
        int res = userService.resetUserPwd(resetVo.getUsername(),SecurityUtils.encryptPassword(resetVo.getPassword()));
        if(res > 0){
            return R.ok(true);
        }else {
            return R.fail("修改密码错误，请联系管理员");
        }
    }

    /**
     * 验证验证码
     * @param resetVo
     * @return
     */
    @PostMapping("/checkSmsCode")
    public R<Map<String,String>> checkCode(@RequestBody ResetVo resetVo){
        if(StringUtils.isEmpty(resetVo.getPhone()) || StringUtils.isEmpty(resetVo.getCode())){
            return R.fail("参数错误！");
        }
        String key = UserConstants.USER + resetVo.getPhone();
        if(!redisService.hasKey(key)){
            return R.fail("验证码过期，请重新发送");
        }
        if(resetVo.getCode().equals(redisService.getCacheObject(key))){
            redisService.del(key);
            SysUser sysUser = userService.selectUserByPhone(resetVo.getPhone());
            Map<String,String> result = new HashMap<>();
            result.put("smsCode",resetVo.getCode());
            result.put("username",sysUser.getUserName());
            return R.ok(result);
        }else {
            return R.fail("验证码错误");
        }
    }

    /**
     * 发送验证码
     * @param
     * @return
     */
    @GetMapping("/sendCode/{phone}")
    public R<String> sendCode(@PathVariable("phone")String phone){
        if(redisService.hasKey(UserConstants.USER + phone)){
            return R.fail("您已经发送过了，请五分钟之后再试");
        }
        StringBuilder random= new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int max=9,min=0;
            int ran2 = (int) (Math.random()*(max-min)+min);
            System.out.println(ran2);
            random.append(ran2);
        }
        String code = random.toString();
        log.info("code:***************************"+code);
        redisService.setCacheObject(UserConstants.USER + phone,code,5L, TimeUnit.MINUTES);
        try {
            smsService.sendSmsCode(phone,"您的验证码是" + code + "，5分钟内有效，请勿泄漏！");
            return R.ok("已向手机尾号为" + phone.substring(7,11) + "的用户发送验证码！");
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
    }

    /**
     * 根据用户发送验证码
     * @param
     * @return
     */
    @PostMapping("/sendSmsCode/{phone}")
    public R<String> sendSmsCode(@PathVariable("phone")String phone){
        if(redisService.hasKey(UserConstants.USER + phone)){
            return R.fail("您已经发送过了，请五分钟之后再试");
        }
        SysUser sysUser = new SysUser();
        sysUser.setPhonenumber(phone);
        List<SysUser> sysUserList = userService.selectUserList(sysUser);
        if (!Optional.ofNullable(sysUserList).isPresent() || sysUserList.size() <= 0) {
            return R.fail("该用户不存在，或者身份已过期");
        }
        StringBuilder random= new StringBuilder();
        for (int i = 0; i < 6; i++) {
             int max=9,min=0;
             int ran2 = (int) (Math.random()*(max-min)+min);
             System.out.println(ran2);
            random.append(ran2);
         }
        String code = random.toString();
        redisService.setCacheObject(UserConstants.USER + phone,code,5L, TimeUnit.MINUTES);
        try {
            smsService.sendSmsCode(phone,"您的验证码是" + code + "，5分钟内有效，请勿泄漏！");
            return R.ok("已向手机尾号为" + phone.substring(7,11) + "的用户发送验证码！");
        }catch (Exception e){
            return R.fail(e.getMessage());
        }
    }


    @PostMapping("/listUser")
    public R<List<SysUser>> listUser(@RequestBody SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        return CollectionUtils.isEmpty(list)?R.fail():R.ok(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:user:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUser user) throws IOException {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.exportExcel(response, list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:user:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        util.importTemplateExcel(response, "用户数据");
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info/{username}")
    public R<LoginUser> info(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("用户名或密码错误");
        }
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(sysUser.getUserId());
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(sysUser.getUserId());
        LoginUser sysUserVo = new LoginUser();
        sysUserVo.setSysUser(sysUser);
        sysUserVo.setEntityId(sysUser.getEntityId());
        sysUserVo.setRoles(roles);
        sysUserVo.setPermissions(permissions);
        return R.ok(sysUserVo);
    }


    /**
     * 获取配置参数
     *
     * @param configKey
     * @return
     */
    @GetMapping("/configKey/{configKey}")
    public R<String> getConfigKey(@PathVariable("configKey") String configKey) {
        String configPara = configService.selectConfigByKey(configKey);
        return R.ok(configPara);
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        Long userId = SecurityUtils.getUserId();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(userId);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", userService.selectUserById(userId));
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:user:query")
    @GetMapping(value = {"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize(hasPermi = "system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (user.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || user.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH) {
            return AjaxResult.error("密码必须是8位以上20位以下");
        }
        if (!user.getPassword().matches(Constants.PW_PATTERN)) {
            return AjaxResult.error("密码必须包含大小写字母、数字和特殊字符串");
        }


        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }


    /**
     * 修改用户
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize(hasPermi = "system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if(user.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || user.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH){
            return AjaxResult.error("密码必须是8位以上20位以下");
        }
        if(!user.getPassword().matches(Constants.PW_PATTERN)){
            return AjaxResult.error("密码必须包含大小写字母、数字和特殊字符串");
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize(hasPermi = "system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

//    /**
//     * 验证密码是否过期
//     */
//    @GetMapping("/isExpire")
//    public AjaxResult isExpire(String userName) {
//        return AjaxResult.success(userService.isExpire(userName));
//    }

//    /**
//     * 密码过期后修改密码
//     */
//    @PutMapping("/changePwd")
//    public AjaxResult changePwd(@RequestBody SysUser user) {
//        SysUser oldUser = userService.selectUserByUserName(user.getUserName());
//        if (SecurityUtils.matchesPassword(user.getPassword(), oldUser.getPassword())) {
//            return AjaxResult.error("新密码不能与旧密码相同");
//        }
//        if(user.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
//                || user.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH){
//            return AjaxResult.error("密码必须是8位以上20位以下");
//        }
//        if(!user.getPassword().matches(Constants.PW_PATTERN)){
//            return AjaxResult.error("密码必须包含大小写字母、数字和特殊字符串");
//        }
//        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
//        user.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(userService.changePwd(user));
//    }
}
