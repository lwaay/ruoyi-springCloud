package com.sinonc.system.controller;

import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.constant.FileSuffixConstants;
import com.sinonc.common.core.constant.UserConstants;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.oss.service.UploadUtil;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private UploadUtil uploadUtil;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(username));
        ajax.put("postGroup", userService.selectUserPostGroup(username));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        if (userService.updateUserProfile(user) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        String password = user.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if(newPassword.length() < UserConstants.PASSWORD_MIN_LENGTH
                || newPassword.length() > UserConstants.PASSWORD_MAX_LENGTH){
            return AjaxResult.error("密码必须是8位以上20位以下");
        }
        if(!user.getPassword().matches(Constants.PW_PATTERN)){
            return AjaxResult.error("密码必须包含大小写字母、数字和特殊字符串");
        }
        if (userService.resetUserPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 个人信息
     */
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        if (file == null || file.isEmpty()){
            return AjaxResult.error("获取上传图片失败");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(!(FileSuffixConstants.IMG_TYPE_DMG.equals(suffix.toUpperCase()) ||
                FileSuffixConstants.IMG_TYPE_GIF.equals(suffix.toUpperCase()) ||
                FileSuffixConstants.IMG_TYPE_JPEG.equals(suffix.toUpperCase()) ||
                FileSuffixConstants.IMG_TYPE_JPG.equals(suffix.toUpperCase()) ||
                FileSuffixConstants.IMG_TYPE_PNG.equals(suffix.toUpperCase()) ||
                FileSuffixConstants.IMG_TYPE_SVG.equals(suffix.toUpperCase()))) {
            return AjaxResult.error("文件后缀错误，无法上传");
        }
        String url = uploadUtil.upload(file);
        if (StringUtils.isEmpty(url)){
            return AjaxResult.error("上传图片失败");
        }
        user.setAvatar(url);
        int result = userService.updateUser(user);
        return result>0?AjaxResult.success("上传成功",url):AjaxResult.error();
    }
}
