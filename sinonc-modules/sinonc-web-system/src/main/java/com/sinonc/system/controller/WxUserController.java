package com.sinonc.system.controller;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.service.IWxUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 认养用户基础Controller
 *
 * @author ruoyi
 * @date 2022-02-12
 */
@RestController
@RequestMapping("/wxUser")
@Slf4j
public class WxUserController extends BaseController {
    @Autowired
    private IWxUserService wxUserService;


    /**
     * 查询认养用户基础列表
     */
    @PreAuthorize(hasPermi = "system:wxUser:list")
    @GetMapping("/list")
    public TableDataInfo list(WxUser wxUser) {
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出认养用户基础列表
     */
    @PreAuthorize(hasPermi = "system:wxUser:export")
    @Log(title = "认养用户基础", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUser wxUser) throws IOException {
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser. class);
        util.exportExcel(response, list, "wxUser");
    }

    /**
     * 获取认养用户基础详细信息
     */
    @PreAuthorize(hasPermi = "system:wxUser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wxUserService.selectWxUserById(id));
    }

    /**
     * 新增认养用户基础
     */
    @PreAuthorize(hasPermi = "system:wxUser:add")
    @Log(title = "认养用户基础", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUser wxUser) {
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改认养用户基础
     */
    @PreAuthorize(hasPermi = "system:wxUser:edit")
    @Log(title = "认养用户基础", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUser wxUser) {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 修改认养用户基础
     */
    @Log(title = "认养用户基础", businessType = BusinessType.UPDATE)
    @PostMapping("/updateUserBy")
    public AjaxResult updateUserBy(@RequestBody WxUser wxUser) {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除认养用户基础
     */
    @PreAuthorize(hasPermi = "system:wxUser:remove")
    @Log(title = "认养用户基础", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wxUserService.deleteWxUserByIds(ids));
    }


    @GetMapping("/getWxUserByMemberId/{memberId}")
    public R<WxUser> getWxUserByMemberId(@PathVariable("memberId") Long memberId) {
        WxUser user = wxUserService.selectWxUserById(memberId);
        return R.ok(user);
    }

    /**
     * 查询会员信息
     * @param id 会员ID
     * @return 结果
     */
    @GetMapping("/getUserById/{id}")
    public R<WxUser> getUserById(@PathVariable("id") Long id){
        WxUser user = wxUserService.selectWxUserById(id);
        return R.ok(user);
    }

    @GetMapping(value = "/info/selectUserInfoByIds")
    public R<List<WxUser>> infos(Long[] ids){
        return R.ok(wxUserService.selectWxUserByIds(ids));
    }

    @GetMapping(value = "/info/getAllUsers")
    public R<List<WxUser>> allUsers(){
        return R.ok(wxUserService.selectWxUserList(new WxUser()));
    }
}
