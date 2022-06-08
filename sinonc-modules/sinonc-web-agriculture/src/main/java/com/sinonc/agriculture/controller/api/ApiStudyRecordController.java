package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.domain.StudyRecord;
import com.sinonc.agriculture.service.IStudyRecordService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lw
 * @date: 2022/2/24 15:27
 * @description:
 */
@Api(tags = "学习记录")
@RequestMapping("/agriculture/api/record")
@RestController
public class ApiStudyRecordController extends BaseController {

    @Autowired
    private IStudyRecordService studyRecordService;

    @ApiOperation("添加学习记录")
    @PostMapping("/addStudyRecord")
    public AjaxResult addStudyRecord(@RequestBody StudyRecord studyRecord){
        return toAjax(studyRecordService.insertStudyRecord(studyRecord));
    }

    @ApiOperation("查看学习记录")
    @GetMapping("/listStudyRecord")
    public AjaxResult listStudyRecord(StudyRecord studyRecord){
        startPage();
        studyRecord.setUserId(SecurityUtils.getUserId());
        return AjaxResult.success(studyRecordService.selectStudyRecordList(studyRecord));
    }
}
