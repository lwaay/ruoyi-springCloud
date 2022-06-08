package com.sinonc.system.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.domain.*;
import com.sinonc.system.dto.BusinessApplyDto;
import com.sinonc.system.mapper.BusinessApplyMapper;
import com.sinonc.system.mapper.BusinessEntityMapper;
import com.sinonc.system.service.*;
import com.sinonc.system.utils.BuAuditStatusContents;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主体类型申请Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-01
 */
@Service
public class BusinessApplyServiceImpl implements IBusinessApplyService {
    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    @Autowired
    private BusinessEntityMapper businessEntityMapper;

    @Autowired
    private IBuplantCompanyService buplantCompanyService;
    @Autowired
    private IBuprocessCompanyService buprocessCompanyService;
    @Autowired
    private IBudistCompanyService budistCompanyService;
    @Autowired
    private IBuserviceCompanyService buserviceCompanyService;

    /**
     * 查询主体类型申请
     *
     * @param applyId 主体类型申请ID
     * @return 主体类型申请
     */
    @Override
    public BusinessApply selectBusinessApplyById(Long applyId) {
        return businessApplyMapper.selectBusinessApplyById(applyId);
    }

    /**
     * 查询主体类型申请列表
     *
     * @param businessApply 主体类型申请
     * @return 主体类型申请
     */
    @Override
    public List<BusinessApply> selectBusinessApplyList(BusinessApply businessApply) {
        return businessApplyMapper.selectBusinessApplyList(businessApply);
    }

    /**
     * 新增主体类型申请
     *
     * @param businessApply 主体类型申请
     * @return 结果
     */
    @Override
    public int insertBusinessApply(BusinessApply businessApply) {
        businessApply.setCreateTime(DateUtils.getNowDate());
        return businessApplyMapper.insertBusinessApply(businessApply);
    }

    /**
     * 修改主体类型申请
     *
     * @param businessApply 主体类型申请
     * @return 结果
     */
    @Override
    public int updateBusinessApply(BusinessApply businessApply) {
        businessApply.setUpdateTime(DateUtils.getNowDate());
        return businessApplyMapper.updateBusinessApply(businessApply);
    }

    /**
     * 批量删除主体类型申请
     *
     * @param applyIds 需要删除的主体类型申请ID
     * @return 结果
     */
    @Override
    public int deleteBusinessApplyByIds(Long[] applyIds) {
        return businessApplyMapper.deleteBusinessApplyByIds(applyIds);
    }

    /**
     * 删除主体类型申请信息
     *
     * @param applyId 主体类型申请ID
     * @return 结果
     */
    @Override
    public int deleteBusinessApplyById(Long applyId) {
        return businessApplyMapper.deleteBusinessApplyById(applyId);
    }

    @Override
    public int auditBusinessApply(BusinessApply businessApply) {
        String auditStatus = businessApply.getAuditStatus();
        if (BuAuditStatusContents.APPLY_STATUS_TG.compareTo(auditStatus) == 0) {
            BusinessEntity businessEntity = businessEntityMapper.selectBusinessEntityById(businessApply.getEntityId());
            String businessMaintype = businessEntity.getBusinessMaintype();
            if (businessMaintype == null) {
                businessMaintype = businessApply.getBusinessMaintype();
            } else {
                businessMaintype = businessMaintype + "," + businessApply.getBusinessMaintype();
            }
            BusinessApplyDto businessApplyDto=new BusinessApplyDto();
            BeanUtils.copyProperties(businessApply,businessApplyDto);
            iniBusinessApply(businessApplyDto);
            //更新主体名称
            businessEntity.setEntityName(businessApplyDto.getEntityName());

            businessEntity.setBusinessMaintype(businessMaintype);
            //更新经营主体类型
            businessEntityMapper.updateBusinessEntity(businessEntity);
        }
        businessApply.setUpdateTime(DateUtils.getNowDate());
        businessApply.setAuditTime(DateUtils.getNowDate());
        int rs = businessApplyMapper.updateBusinessApply(businessApply);
        return rs;
    }

    @Override
    public void iniBusinessApply(BusinessApplyDto businessApplyDto){
        String businessMaintype=businessApplyDto.getBusinessMaintype();
        Long companyId=businessApplyDto.getCompanyId();
        if(StringUtils.compare(businessMaintype,"1")==0){
            BuplantCompany buplantCompany=buplantCompanyService.selectBuplantCompanyById(companyId);
            businessApplyDto.setEntityName(buplantCompany.getLegalPerson());
        }

        if(StringUtils.compare(businessMaintype,"2")==0){
            BuprocessCompany buprocessCompany=buprocessCompanyService.selectBuprocessCompanyById(companyId);
            businessApplyDto.setEntityName(buprocessCompany.getCompanyName());
        }

        if(StringUtils.compare(businessMaintype,"3")==0){
            BudistCompany budistCompany=budistCompanyService.selectBudistCompanyById(companyId);
            businessApplyDto.setEntityName(budistCompany.getCompanyName());
        }
        if(StringUtils.compare(businessMaintype,"4")==0){
            BuserviceCompany buserviceCompany=buserviceCompanyService.selectBuserviceCompanyById(companyId);
            businessApplyDto.setEntityName(buserviceCompany.getCompanyName());
        }
    }
}
