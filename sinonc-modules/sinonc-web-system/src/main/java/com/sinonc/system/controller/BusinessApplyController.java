package com.sinonc.system.controller;

import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.domain.*;
import com.sinonc.system.dto.BusinessApplyDto;
import com.sinonc.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主体类型申请Controller
 *
 * @author ruoyi
 * @date 2022-03-01
 */
@RestController
@RequestMapping("/dbdkalbdtwo")
public class BusinessApplyController extends BaseController {
    @Autowired
    private IBusinessApplyService businessApplyService;
    @Autowired
    private IBusinessEntityService businessEntityService;


    /**
     * 查询主体类型申请列表
     */
    @PreAuthorize(hasPermi = "system:bussapply:list")
    @GetMapping("/list")
    public TableDataInfo list(BusinessApply businessApply) {
        startPage();
        List<BusinessApply> list = businessApplyService.selectBusinessApplyList(businessApply);
        TableDataInfo tableDataInfo = getDataTable(list);
        List<BusinessApply> businessApplyDtoList =new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BusinessApply tempBusinessApply=list.get(i);
            BusinessApplyDto businessApplyDto=new BusinessApplyDto();
            BeanUtils.copyProperties(tempBusinessApply,businessApplyDto);
            businessApplyDtoList.add(businessApplyDto);
            businessApplyService.iniBusinessApply(businessApplyDto);
//            BusinessEntity businessEntity=getBusinessEntityById(tempBusinessApply.getEntityId());
//            businessApplyDto.setEntityName(businessEntity.getEntityName());
        }
        tableDataInfo.setRows(businessApplyDtoList);
        return tableDataInfo;
    }

    private BusinessEntity getBusinessEntityById(Long businessEntityId){
        BusinessEntity businessEntity=businessEntityService.selectBusinessEntityById(businessEntityId);
        return businessEntity;
    }



    /**
     * 导出主体类型申请列表
     */
    @PreAuthorize(hasPermi = "system:bussapply:export")
    @Log(title = "主体类型申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusinessApply businessApply) throws IOException {
        List<BusinessApply> list = businessApplyService.selectBusinessApplyList(businessApply);
        ExcelUtil<BusinessApply> util = new ExcelUtil<BusinessApply>(BusinessApply.class);
        util.exportExcel(response, list, "bussapply");
    }

    /**
     * 获取主体类型申请详细信息
     */
    @PreAuthorize(hasPermi = "system:bussapply:query")
    @GetMapping(value = "/{applyId}")
    public AjaxResult getInfo(@PathVariable("applyId") Long applyId) {
        BusinessApply businessApply=businessApplyService.selectBusinessApplyById(applyId);
        BusinessApplyDto businessApplyDto=new BusinessApplyDto();
        BeanUtils.copyProperties(businessApply,businessApplyDto);
        businessApplyService.iniBusinessApply(businessApplyDto);
        return AjaxResult.success(businessApplyDto);
    }

    /**
     * 新增主体类型申请
     */
    @PreAuthorize(hasPermi = "system:bussapply:add")
    @Log(title = "主体类型申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusinessApply businessApply) {
        return toAjax(businessApplyService.insertBusinessApply(businessApply));
    }

    /**
     * 审核
     */
    @PreAuthorize(hasPermi = "system:bussapply:edit")
    @Log(title = "主体类型申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusinessApply businessApply) {
        int rs = businessApplyService.auditBusinessApply(businessApply);
        return toAjax(rs);
    }

    /**
     * 删除主体类型申请
     */
    @PreAuthorize(hasPermi = "system:bussapply:remove")
    @Log(title = "主体类型申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{applyIds}")
    public AjaxResult remove(@PathVariable Long[] applyIds) {
        return toAjax(businessApplyService.deleteBusinessApplyByIds(applyIds));
    }
}
