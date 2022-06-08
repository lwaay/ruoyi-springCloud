package com.sinonc.system.controller;

import java.util.List;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.domain.SysUserExamine;
import com.sinonc.system.service.ISysUserExamineService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 用户注册审核Controller
 *
 * @author ruoyi
 * @date 2021-10-30
 */
@RestController
@RequestMapping("/examine")
public class SysUserExamineController extends BaseController {
    @Autowired
    private ISysUserExamineService sysUserExamineService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ISysUserService userService;
    /**
     * 查询用户注册审核列表
     */
    @PreAuthorize(hasPermi = "system:examine:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUserExamine sysUserExamine) {
        startPage();
        List<SysUserExamine> list = sysUserExamineService.selectSysUserExamineList(sysUserExamine);
        return getDataTable(list);
    }

    /**
     * 导出用户注册审核列表
     */
    @PreAuthorize(hasPermi = "system:examine:export")
    @Log(title = "用户注册审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserExamine sysUserExamine) throws IOException {
        List<SysUserExamine> list = sysUserExamineService.selectSysUserExamineList(sysUserExamine);
        ExcelUtil<SysUserExamine> util = new ExcelUtil<SysUserExamine>(SysUserExamine.class);
        util.exportExcel(response, list, "examine");
    }

    /**
     * 获取用户注册审核详细信息
     */
    @PreAuthorize(hasPermi = "system:examine:query")
    @GetMapping(value = "/{examineId}")
    public AjaxResult getInfo(@PathVariable("examineId") Long examineId) {
        return AjaxResult.success(sysUserExamineService.selectSysUserExamineById(examineId));
    }

    /**
     * 新增用户注册审核
     */
//    @PreAuthorize(hasPermi = "system:examine:add")
    @Log(title = "用户注册审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserExamine sysUserExamine) {
        SysUser sysUser = new SysUser();
        sysUser.setPhonenumber(sysUserExamine.getPhonenumber());
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(sysUserExamine.getUserName()))) {
            return AjaxResult.error("新增用户'" + sysUserExamine.getUserName() + "用户账号已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser))) {
            return AjaxResult.error("新增用户'" + sysUserExamine.getUserName() + "手机号码已存在");
        }
        SysUserExamine oldUserExamine = sysUserExamineService.selectSysUserExamineByPhone(sysUserExamine.getPhonenumber());
        if(Optional.ofNullable(oldUserExamine).isPresent()){
            return AjaxResult.error("该手机号已被使用过");
        }
        //验证码
        String key = UserConstants.USER + sysUserExamine.getPhonenumber();
        if(!redisService.hasKey(key)){
            return AjaxResult.error("验证码过期，请重新发送");
        }
        if(!sysUserExamine.getCode().equals(redisService.getCacheObject(key))){
            return AjaxResult.error("验证码错误！");
        }
        if(sysUserExamine.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || sysUserExamine.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH){
            return AjaxResult.error("密码必须是8位以上20位以下");
        }
        if(!sysUserExamine.getPassword().matches(Constants.PW_PATTERN)){
            return AjaxResult.error("密码必须包含大小写字母、数字和特殊字符串");
        }
        return toAjax(sysUserExamineService.insertSysUserExamine(sysUserExamine));
    }

    /**
     * 修改用户注册审核
     */
    @PreAuthorize(hasPermi = "system:examine:edit")
    @Log(title = "用户注册审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserExamine sysUserExamine) {
        return toAjax(sysUserExamineService.updateSysUserExamine(sysUserExamine));
    }

    /**
     * 删除用户注册审核
     */
    @PreAuthorize(hasPermi = "system:examine:remove")
    @Log(title = "用户注册审核", businessType = BusinessType.DELETE)
    @DeleteMapping("/{examineIds}")
    public AjaxResult remove(@PathVariable Long[] examineIds) {
        return toAjax(sysUserExamineService.deleteSysUserExamineByIds(examineIds));
    }

    /**
     * 审核用户
     */
    @PreAuthorize(hasPermi = "system:examine:examine")
    @Log(title = "审核用户", businessType = BusinessType.UPDATE)
    @PostMapping("/examine")
    public AjaxResult examine(@RequestBody SysUserExamine sysUserExamine) {
        return toAjax(sysUserExamineService.examine(sysUserExamine));
    }
}
