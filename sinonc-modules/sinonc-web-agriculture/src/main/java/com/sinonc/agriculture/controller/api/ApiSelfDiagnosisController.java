package com.sinonc.agriculture.controller.api;

import com.sinonc.agriculture.domain.DiagnosisIllness;
import com.sinonc.agriculture.domain.DiagnosisIllnessResult;
import com.sinonc.agriculture.domain.DiagnosisSymptom;
import com.sinonc.agriculture.mapper.SelfDiagnosisMapper;
import com.sinonc.agriculture.service.ISelfDiagnosisService;
import com.sinonc.agriculture.vo.SelfDiagnosisVo;
import com.sinonc.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * 自助诊断
 */
@Api(tags = "自助诊断")
@RestController
@RequestMapping("/api/agriculture/diagnosis")
public class ApiSelfDiagnosisController {

    @Autowired
    private ISelfDiagnosisService selfDiagnosisService;
    @Autowired
    private SelfDiagnosisMapper selfDiagnosisMapper;
    /**
     * 获取诊断信息
     *
     * @param cropId 根据crop_id
     * @return AjaxResult
     */
    @ApiOperation("获取诊断信息")
    @GetMapping("/getDiagnosis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cropId", value = "鱼 crop_id", type = "Long", required = true, paramType = "query")
    })
    public AjaxResult getDiagnosisBody(Long cropId) {
        if (!ObjectUtils.allNotNull(cropId)) {
            return AjaxResult.error("cropId 参数为空");
        }
        List<SelfDiagnosisVo> list = selfDiagnosisService.getDiagnosisBodyAndSymptom(cropId);
        return AjaxResult.success(list);
    }

    /**
     * 获取症状
     *
     * @param diaId
     * @return AjaxResult
     */
    @ApiOperation("获取症状信息")
    @GetMapping("/getSymptom")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "diaId", value = "症状 id", type = "Long", required = true, paramType = "query")
    })

    public AjaxResult getSymptom(long diaId) {
        if (!ObjectUtils.allNotNull(diaId)) {
            return AjaxResult.error("diaId 参数为空");
        }
        List<DiagnosisSymptom> symptomList = selfDiagnosisMapper.selectSymptomByDiaId(diaId);
        return AjaxResult.success(symptomList);
    }

    @ApiOperation("获取诊断疾病")
    @GetMapping("/getDiagnosisIllness")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "symptomIds", value = "症状id ，多个则用英文逗号分隔", type = "String", required = true, paramType = "query")
    })
    public AjaxResult getDiagnosisIllness(String symptomIds) {
        if (!ObjectUtils.allNotNull(symptomIds)) {
            return AjaxResult.error("symptomIds 参数为空");
        }
        if (symptomIds.contains("，"))return AjaxResult.error("请使用英文逗号分隔");
        List<DiagnosisIllness> resultVoList = selfDiagnosisService.getDiagnosisIllness(symptomIds);
        return AjaxResult.success(resultVoList);
    }

    @ApiOperation("获取诊断疾病结果")
    @GetMapping("/getDiagnosisIllnessResult")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "illnessId", value = "症状id ，多个则用英文逗号分隔", type = "String", required = true, paramType = "query")
    })
    public AjaxResult getDiagnosisResult(String illnessId) {
        if (!ObjectUtils.allNotNull(illnessId)) {
            return AjaxResult.error("illnessId 参数为空");
        }
        List<DiagnosisIllnessResult> resultVoList = selfDiagnosisService.getDiagnosisIllnessResult(illnessId);
        return AjaxResult.success(resultVoList);
    }
}
