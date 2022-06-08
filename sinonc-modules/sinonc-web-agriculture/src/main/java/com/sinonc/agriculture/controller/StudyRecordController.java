package com.sinonc.agriculture.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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
import com.sinonc.agriculture.domain.StudyRecord;
import com.sinonc.agriculture.service.IStudyRecordService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 学习记录Controller
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@RestController
@RequestMapping("/agriculture/record")
public class StudyRecordController extends BaseController {
    @Autowired
    private IStudyRecordService studyRecordService;

    /**
     * 查询学习记录列表
     */
    @PreAuthorize(hasPermi = "agriculture:record:list")
    @GetMapping("/list")
    public TableDataInfo list(StudyRecord studyRecord) {
        startPage();
        List<StudyRecord> list = studyRecordService.selectStudyRecordList(studyRecord);
        return getDataTable(list);
    }

    /**
     * 导出学习记录列表
     */
    @PreAuthorize(hasPermi = "agriculture:record:export")
    @Log(title = "学习记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudyRecord studyRecord) throws IOException {
        List<StudyRecord> list = studyRecordService.selectStudyRecordList(studyRecord);
        ExcelUtil<StudyRecord> util = new ExcelUtil<StudyRecord>(StudyRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取学习记录详细信息
     */
    @PreAuthorize(hasPermi = "agriculture:record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId) {
        return AjaxResult.success(studyRecordService.selectStudyRecordById(recordId));
    }

    /**
     * 新增学习记录
     */
    @PreAuthorize(hasPermi = "agriculture:record:add")
    @Log(title = "学习记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudyRecord studyRecord) {
        return toAjax(studyRecordService.insertStudyRecord(studyRecord));
    }

    /**
     * 修改学习记录
     */
    @PreAuthorize(hasPermi = "agriculture:record:edit")
    @Log(title = "学习记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudyRecord studyRecord) {
        return toAjax(studyRecordService.updateStudyRecord(studyRecord));
    }

    /**
     * 删除学习记录
     */
    @PreAuthorize(hasPermi = "agriculture:record:remove")
    @Log(title = "学习记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds) {
        return toAjax(studyRecordService.deleteStudyRecordByIds(recordIds));
    }
}
