package com.sinonc.system.controller.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.domain.*;
import com.sinonc.system.dto.BusinessApplyDto;
import com.sinonc.system.dto.BusinessEntityDto;
import com.sinonc.system.mapper.BudistCompanyMapper;
import com.sinonc.system.mapper.BuplantCompanyMapper;
import com.sinonc.system.mapper.BuprocessCompanyMapper;
import com.sinonc.system.mapper.BuserviceCompanyMapper;
import com.sinonc.system.service.IBusinessApplyService;
import com.sinonc.system.service.IBusinessCertService;
import com.sinonc.system.service.IBusinessEntityService;
import com.sinonc.system.utils.BuEntityContents;
import com.sinonc.system.vo.BusinessEntityVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@RestController
@RequestMapping("api/buss")
@Slf4j
public class ApiBussController {

    @Autowired
    private IBusinessEntityService businessEntityService;

    @Autowired
    private IBusinessApplyService businessApplyService;

    @Autowired
    private IBusinessCertService businessCertService;

    @Autowired
    private BudistCompanyMapper budistCompanyMapper;

    @Autowired
    private BuplantCompanyMapper buplantCompanyMapper;

    @Autowired
    private BuprocessCompanyMapper buprocessCompanyMapper;

    @Autowired
    private BuserviceCompanyMapper buserviceCompanyMapper;

    @GetMapping("getBusinessEntityDtoBymemberId")
    public AjaxResult getBusinessEntityDtoBymemberId(Long memberId) {
        BusinessEntityDto businessEntityDto = businessEntityService.getBusinessEntityDtoBymemberId(memberId);
        return AjaxResult.success("success", businessEntityDto);
    }

    @GetMapping("getBusinessEntityDtoById")
    public AjaxResult getBusinessEntityDtoById(Long memberId, String applyType) {
        BusinessEntityDto businessEntityDto = businessEntityService.getBusinessEntityDtoById(memberId, applyType);
        return AjaxResult.success("success", businessEntityDto);
    }

    @GetMapping("getAllBusinessEntityByMemberId")
    public AjaxResult getAllBusinessEntityByMemberId(Long memberId) {
        List<BusinessEntityDto> businessEntityDtoList = businessEntityService.getAllBusinessEntityByMemberId(memberId);
        return AjaxResult.success(businessEntityDtoList);
    }

    @PostMapping("addBusinessEntityVo")
    public AjaxResult addBusinessEntityVo(@RequestBody BusinessEntityVo businessEntityVo) {
        int result = businessEntityService.addBusinessEntityVo(businessEntityVo);
        return AjaxResult.success("success", result);
    }

    @ApiOperation("获取百色芒果新型生产经营主体")
    @GetMapping("getNewTypeBusinessEntity")
    public AjaxResult selectBusinessEntityList(String type) {
        BusinessEntity query = new BusinessEntity();
        query.setStatus("1");
        query.setBusinessMaintype(type);
        return AjaxResult.success(businessEntityService.selectBusinessEntityList(query));
    }

    @ApiOperation("查询主体认证审核列表")
    @PostMapping("getBusinessApplyList")
    public AjaxResult getBusinessApplyList(@RequestBody BusinessApply businessApply) {
        PageHelper.startPage(businessApply.getPageNum(), businessApply.getPageSize());
        PageInfo pageInfo = new PageInfo<>(businessApplyService.selectBusinessApplyList(businessApply));

        List<BusinessApply> businessApplyList = pageInfo.getList();
        List<BusinessApplyDto> businessApplyDtoList = new ArrayList<>();
        for (int i = 0; i < businessApplyList.size(); i++) {
            BusinessApply tempBusinessApply = businessApplyList.get(i);
            BusinessApplyDto tempBusinessApplyDto = new BusinessApplyDto();
            businessApplyDtoList.add(tempBusinessApplyDto);

            BeanUtils.copyProperties(tempBusinessApply, tempBusinessApplyDto);
            BusinessCert businessCert = businessCertService.selectBusinessCertById(Long.valueOf(tempBusinessApply.getCertIds()));
            tempBusinessApplyDto.setBusinessCert(businessCert);

            BusinessEntity businessEntity = businessEntityService.selectBusinessEntityById(tempBusinessApply.getEntityId());
            tempBusinessApplyDto.setBusinessEntity(businessEntity);
        }

        AjaxResult success = AjaxResult.success();
        success.put("data", businessApplyDtoList);
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    @ApiOperation("查询主体认证审核详情")
    @GetMapping("getBusinessApplyDto")
    public AjaxResult getBusinessApplyDto(Long applyId) {
        BusinessApply businessApply = businessApplyService.selectBusinessApplyById(applyId);
        BusinessApplyDto tempBusinessApplyDto = new BusinessApplyDto();

        BeanUtils.copyProperties(businessApply, tempBusinessApplyDto);
        BusinessCert businessCert = businessCertService.selectBusinessCertById(Long.valueOf(businessApply.getCertIds()));
        tempBusinessApplyDto.setBusinessCert(businessCert);

        BusinessEntity businessEntity = businessEntityService.selectBusinessEntityById(businessApply.getEntityId());
        tempBusinessApplyDto.setBusinessEntity(businessEntity);

        String applyType=businessApply.getBusinessMaintype();
        Long companyId=businessApply.getCompanyId();

        if(BuEntityContents.APPLY_TYPE_ZZ.equals(applyType)){
            BuplantCompany buplantCompany=buplantCompanyMapper.selectBuplantCompanyById(companyId);
            tempBusinessApplyDto.setBuplantCompany(buplantCompany);
        }
        if(BuEntityContents.APPLY_TYPE_JG.equals(applyType)){
            BuprocessCompany buprocessCompany=buprocessCompanyMapper.selectBuprocessCompanyById(companyId);
            tempBusinessApplyDto.setBuprocessCompany(buprocessCompany);
        }
        if(BuEntityContents.APPLY_TYPE_JX.equals(applyType)){
            BudistCompany budistCompany=budistCompanyMapper.selectBudistCompanyById(companyId);
            tempBusinessApplyDto.setBudistCompany(budistCompany);
        }
        if(BuEntityContents.APPLY_TYPE_FW.equals(applyType)){
            BuserviceCompany buserviceCompany=buserviceCompanyMapper.selectBuserviceCompanyById(companyId);
            tempBusinessApplyDto.setBuserviceCompany(buserviceCompany);
        }

        AjaxResult success = AjaxResult.success();
        success.put("data", tempBusinessApplyDto);
        return success;
    }



    @ApiOperation("更新主体审核状态")
    @PostMapping("updateBusinessApply")
    public AjaxResult updateBusinessApply(@RequestBody BusinessApply businessApply) {
        businessApplyService.updateBusinessApply(businessApply);
        AjaxResult success = AjaxResult.success();
        return success;
    }

}
