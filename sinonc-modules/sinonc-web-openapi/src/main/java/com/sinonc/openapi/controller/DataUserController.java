package com.sinonc.openapi.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.openapi.domain.DataApi;
import com.sinonc.openapi.domain.DataUser;
import com.sinonc.openapi.service.IDataApiService;
import com.sinonc.openapi.service.IDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 第三方接口用户Controller
 *
 * @author hhao
 * @date 2020-12-23
 */
@RestController
@RequestMapping("/dataUser")
public class DataUserController extends BaseController {
    @Autowired
    private IDataUserService dataUserService;
    @Autowired
    private IDataApiService dataApiService;

    /**
     * 查询第三方接口用户列表
     */
    @PreAuthorize(hasPermi = "data:dataUser:list")
    @GetMapping("/list")
    public TableDataInfo list(DataUser dataUser) {
        startPage();
        List<DataUser> list = dataUserService.selectDataUserList(dataUser);
        List<DataApi> dataApis = dataApiService.selectDataApiList(new DataApi());
        dataUser.setDataApiList(dataApis);
        return getDataTable(list);
    }

    /**
     * 导出第三方接口用户列表
     */
    @PreAuthorize(hasPermi = "data:dataUser:export")
    @Log(title = "第三方接口用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DataUser dataUser) throws IOException {
        List<DataUser> list = dataUserService.selectDataUserList(dataUser);
        ExcelUtil<DataUser> util = new ExcelUtil<DataUser>(DataUser.class);
        util.exportExcel(response, list, "dataUser");
    }

    /**
     * 获取第三方接口用户详细信息
     */
    @PreAuthorize(hasPermi = "data:dataUser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(dataUserService.selectDataUserById(id));
    }

    /**
     * 新增第三方接口用户
     */
    @PreAuthorize(hasPermi = "data:dataUser:add")
    @Log(title = "第三方接口用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataUser dataUser) {
        return toAjax(dataUserService.insertDataUser(dataUser));
    }

    /**
     * 修改第三方接口用户
     */
    @PreAuthorize(hasPermi = "data:dataUser:edit")
    @Log(title = "第三方接口用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataUser dataUser) {
        return toAjax(dataUserService.updateDataUser(dataUser));
    }

    /**
     * 删除第三方接口用户
     */
    @PreAuthorize(hasPermi = "data:dataUser:remove")
    @Log(title = "第三方接口用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataUserService.deleteDataUserByIds(ids));
    }
}
