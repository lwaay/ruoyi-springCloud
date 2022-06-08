package com.sinonc.system.service.impl;

import com.sinonc.agriculture.RemoteExpertInfoService;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.domain.*;
import com.sinonc.system.dto.BusinessEntityDto;
import com.sinonc.system.mapper.*;
import com.sinonc.system.service.IBudistCompanyService;
import com.sinonc.system.service.IBuplantCompanyService;
import com.sinonc.system.service.IBusinessEntityService;
import com.sinonc.system.utils.BuAuditStatusContents;
import com.sinonc.system.utils.BuEntityContents;
import com.sinonc.system.vo.BusinessEntityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 农业经营主体基础信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class BusinessEntityServiceImpl implements IBusinessEntityService {
    @Autowired
    private BusinessEntityMapper businessEntityMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    @Autowired
    private BudistCompanyMapper budistCompanyMapper;

    @Autowired
    private BuplantCompanyMapper buplantCompanyMapper;

    @Autowired
    private BuprocessCompanyMapper buprocessCompanyMapper;

    @Autowired
    private BuserviceCompanyMapper buserviceCompanyMapper;

    @Autowired
    private BusinessCertMapper businessCertMapper;

    @Autowired
    private RemoteExpertInfoService expertInfoService;


    /**
     * 查询农业经营主体基础信息
     *
     * @param entityId 农业经营主体基础信息ID
     * @return 农业经营主体基础信息
     */
    @Override
    public BusinessEntity selectBusinessEntityById(Long entityId) {
        return businessEntityMapper.selectBusinessEntityById(entityId);
    }

    /**
     * 查询农业经营主体基础信息列表
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 农业经营主体基础信息
     */
    @Override
    public List<BusinessEntity> selectBusinessEntityList(BusinessEntity businessEntity) {
        return businessEntityMapper.selectBusinessEntityList(businessEntity);
    }

    /**
     * 新增农业经营主体基础信息
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 结果
     */
    @Override
    public int insertBusinessEntity(BusinessEntity businessEntity) {
        return businessEntityMapper.insertBusinessEntity(businessEntity);
    }

    /**
     * 修改农业经营主体基础信息
     *
     * @param businessEntity 农业经营主体基础信息
     * @return 结果
     */
    @Override
    public int updateBusinessEntity(BusinessEntity businessEntity) {
        return businessEntityMapper.updateBusinessEntity(businessEntity);
    }

    /**
     * 批量删除农业经营主体基础信息
     *
     * @param entityIds 需要删除的农业经营主体基础信息ID
     * @return 结果
     */
    @Override
    public int deleteBusinessEntityByIds(Long[] entityIds) {
        return businessEntityMapper.deleteBusinessEntityByIds(entityIds);
    }

    /**
     * 删除农业经营主体基础信息信息
     *
     * @param entityId 农业经营主体基础信息ID
     * @return 结果
     */
    @Override
    public int deleteBusinessEntityById(Long entityId) {
        return businessEntityMapper.deleteBusinessEntityById(entityId);
    }

    @Override
    public int addBusinessEntityVo(BusinessEntityVo businessEntityVo) {
        Long memberId = businessEntityVo.getMemberId();
        WxUser wxUser = wxUserMapper.selectWxUserById(memberId);
        String entityIdString = wxUser.getEntityId();

        if (entityIdString == null) {
            BusinessEntity businessEntity = new BusinessEntity();
            BeanUtils.copyProperties(businessEntityVo, businessEntity);
            String businessMaintype=businessEntityVo.getApplyType();
            if(StringUtils.compare("1",businessMaintype)==0){
                //种植户以名称为公司名称
                //businessEntity.setEntityName(businessEntityVo.getLegalPerson());
                businessEntity.setEntityName(businessEntityVo.getCompanyName());
            }else {
                businessEntity.setEntityName(businessEntityVo.getCompanyName());
            }

            businessEntity.setPrincipalMan(businessEntityVo.getLegalPerson());
            //businessEntity.setPrincipalPhone(businessEntityVo.getLegalIdcard());
            businessEntity.setPrincipalPhone(businessEntityVo.getPrincipalPhone());
            businessEntity.setContactAddress(businessEntityVo.getContactAddress());
            businessEntity.setRegisterTime(new Date());
            businessEntity.setRemark(businessEntityVo.getRemark());

            businessEntityMapper.insertBusinessEntity(businessEntity);
            Long entityId = businessEntity.getEntityId();
            wxUser.setEntityId(String.valueOf(entityId));
            businessEntityVo.setEntityId(entityId);
            wxUserMapper.updateWxUser(wxUser);
            Long companyId = genCompany(businessEntityVo);
            genBusinessApply(businessEntityVo, entityId, companyId);
        } else {
            businessEntityVo.setEntityId(Long.valueOf(entityIdString));
            Long companyId = genCompany(businessEntityVo);
            genBusinessApply(businessEntityVo, Long.valueOf(entityIdString), companyId);
            BusinessEntity businessEntity=new BusinessEntity();
            businessEntity.setEntityId(Long.valueOf(entityIdString));
            businessEntity.setRemark(businessEntityVo.getRemark());
            businessEntity.setPrincipalPhone(businessEntityVo.getPrincipalPhone());
            businessEntity.setContactAddress(businessEntityVo.getContactAddress());
            businessEntityMapper.updateBusinessEntity(businessEntity);
        }
        return 1;
    }


    private void genBusinessApply(BusinessEntityVo businessEntityVo, Long entityId, Long companyId) {


        BusinessCert businessCert = new BusinessCert();
        businessCert.setCertName(businessEntityVo.getCertName());
        businessCert.setCertPicurl(businessEntityVo.getCertPic());
        businessCert.setEntityId(entityId);
        businessCert.setUploadMemberid(businessEntityVo.getMemberId());
        businessCert.setCreateTime(new Date());
        businessCert.setUploadTime(new Date());
        businessCertMapper.insertBusinessCert(businessCert);

        BusinessApply businessApplyPara = new BusinessApply();
        String businessMaintype = businessEntityVo.getApplyType();
        businessApplyPara.setEntityId(entityId);
        businessApplyPara.setBusinessMaintype(String.valueOf(businessMaintype));
        List<BusinessApply> businessApplyList = businessApplyMapper.selectBusinessApplyList(businessApplyPara);

        BusinessApply businessApply = new BusinessApply();
        businessApply.setEntityId(entityId);
        businessApply.setBusinessMaintype(businessEntityVo.getApplyType());
        businessApply.setAuditStatus(BuAuditStatusContents.APPLY_STATUS_SHZ);
        businessApply.setCertIds(String.valueOf(businessCert.getCertId()));
        businessApply.setCompanyId(companyId);
        businessApply.setUpdateTime(new Date());

        if (businessApplyList != null && businessApplyList.size() > 0) {
            BusinessApply businessApplyOne = businessApplyList.get(0);
            businessApply.setApplyId(businessApplyOne.getApplyId());
            businessApplyMapper.updateBusinessApply(businessApply);
        } else {
            businessApply.setCreateTime(new Date());
            businessApplyMapper.insertBusinessApply(businessApply);
        }
    }

    private Long genCompany(BusinessEntityVo businessEntityVo) {
        BusinessApply businessApply = new BusinessApply();
        String businessMaintype = businessEntityVo.getApplyType();
        Long entityId = businessEntityVo.getEntityId();
        businessApply.setEntityId(entityId);
        businessApply.setBusinessMaintype(String.valueOf(businessMaintype));
        List<BusinessApply> businessApplyList = businessApplyMapper.selectBusinessApplyList(businessApply);
        BusinessApply businessApplyPz = null;
        Boolean isHave = false;
        if (businessApplyList.size() > 0) {
            businessApplyPz = businessApplyList.get(0);
            isHave = true;
        }

        if (BuEntityContents.APPLY_TYPE_ZZ.compareTo(businessMaintype) == 0) {
            BuplantCompany buplantCompany = new BuplantCompany();
            BeanUtils.copyProperties(businessEntityVo, buplantCompany);
            buplantCompany.setBulicensePic(businessEntityVo.getCertPic());
            buplantCompany.setPlantArea(businessEntityVo.getPlantArea());
            if (isHave) {
                //已经存在
                buplantCompany.setPlantId(businessApplyPz.getCompanyId());
                buplantCompanyMapper.updateBuplantCompany(buplantCompany);
            } else {
                buplantCompany.setCreateTime(new Date());
                buplantCompany.setUpdateTime(new Date());
                buplantCompanyMapper.insertBuplantCompany(buplantCompany);
            }
            return buplantCompany.getPlantId();
        }
        if (BuEntityContents.APPLY_TYPE_JG.compareTo(businessMaintype) == 0) {
            BuprocessCompany buprocessCompany = new BuprocessCompany();
            BeanUtils.copyProperties(businessEntityVo, buprocessCompany);
            buprocessCompany.setBulicensePic(businessEntityVo.getCertPic());
            if (isHave) {
                //已经存在
                buprocessCompany.setProceId(businessApplyPz.getCompanyId());
                buprocessCompanyMapper.updateBuprocessCompany(buprocessCompany);
            } else {
                buprocessCompanyMapper.insertBuprocessCompany(buprocessCompany);
            }

            return buprocessCompany.getProceId();
        }
        if (BuEntityContents.APPLY_TYPE_JX.compareTo(businessMaintype) == 0) {
            BudistCompany budistCompany = new BudistCompany();
            BeanUtils.copyProperties(businessEntityVo, budistCompany);
            budistCompany.setBulicensePic(businessEntityVo.getCertPic());
            if (isHave) {
                //已经存在
                budistCompany.setDistId(businessApplyPz.getCompanyId());
                budistCompanyMapper.updateBudistCompany(budistCompany);
            } else {
                budistCompanyMapper.insertBudistCompany(budistCompany);
            }

            return budistCompany.getDistId();
        }
        if (BuEntityContents.APPLY_TYPE_FW.compareTo(businessMaintype) == 0) {
            BuserviceCompany buserviceCompany = new BuserviceCompany();
            BeanUtils.copyProperties(businessEntityVo, buserviceCompany);
            buserviceCompany.setBulicensePic(businessEntityVo.getCertPic());
            if (isHave) {
                //已经存在
                buserviceCompany.setServiceId(businessApplyPz.getCompanyId());
                buserviceCompanyMapper.updateBuserviceCompany(buserviceCompany);
            } else {
                buserviceCompanyMapper.insertBuserviceCompany(buserviceCompany);
            }

            return buserviceCompany.getServiceId();
        }
        return 0L;
    }

    @Override
    public BusinessEntityDto getBusinessEntityDtoById(Long memberId, String applyType) {
        BusinessEntityDto businessEntityDto = new BusinessEntityDto();

        WxUser wxUser = wxUserMapper.selectWxUserById(memberId);
        if (wxUser == null || wxUser.getEntityId() == null) {
            return businessEntityDto;
        }

        Long entityId = Long.valueOf(wxUser.getEntityId());
        BusinessEntity businessEntity = businessEntityMapper.selectBusinessEntityById(entityId);
        if (businessEntity != null) {
            BeanUtils.copyProperties(businessEntity, businessEntityDto);
            businessEntityDto.setRemark(businessEntity.getRemark());
        }

        BusinessApply businessApply = new BusinessApply();
        businessApply.setEntityId(entityId);
        businessApply.setBusinessMaintype(applyType);
        List<BusinessApply> businessApplyList = businessApplyMapper.selectBusinessApplyList(businessApply);

        if (businessApplyList != null && businessApplyList.size() > 0) {
            BusinessApply businessApplyRs = businessApplyList.get(0);
            copyCompanyData(businessEntityDto, businessApplyRs);
            businessEntityDto.setAuditStatus(businessApplyRs.getAuditStatus());
            businessEntityDto.setBusinessMaintype(applyType);

            String certId = businessApplyRs.getCertIds();
            BusinessCert businessCert = businessCertMapper.selectBusinessCertById(Long.valueOf(certId));
            businessEntityDto.setCertName(businessCert.getCertName());
            businessEntityDto.setCertPic(businessCert.getCertPicurl());
        } else {
            businessEntityDto.setAuditStatus(BuAuditStatusContents.APPLY_STATUS_MRZ);
        }
        if (businessEntity != null) {
            businessEntityDto.setRemark(businessEntity.getRemark());
        }
        return businessEntityDto;
    }

    @Override
    public List<BusinessEntityDto> getAllBusinessEntityByMemberId(Long memberId) {
        List<BusinessEntityDto> businessEntityDtoList=new ArrayList<>();

        Map<String, Object> expertStatus = expertInfoService.expertStatus(memberId).getData();

        BusinessEntityDto zjBusinessEntityDto = new BusinessEntityDto();
        zjBusinessEntityDto.setApplyType("0");
        zjBusinessEntityDto.setAuditStatus(expertStatus.get("status").toString());
        businessEntityDtoList.add(zjBusinessEntityDto);

        BusinessEntityDto zzBusinessEntityDto=genBusinessEntityDto(BuEntityContents.APPLY_TYPE_ZZ,memberId);
        businessEntityDtoList.add(zzBusinessEntityDto);

        BusinessEntityDto jgBusinessEntityDto=genBusinessEntityDto(BuEntityContents.APPLY_TYPE_JG,memberId);
        businessEntityDtoList.add(jgBusinessEntityDto);

        BusinessEntityDto jxBusinessEntityDto = genBusinessEntityDto(BuEntityContents.APPLY_TYPE_JX, memberId);
        businessEntityDtoList.add(jxBusinessEntityDto);

        BusinessEntityDto fwBusinessEntityDto = genBusinessEntityDto(BuEntityContents.APPLY_TYPE_FW, memberId);
        businessEntityDtoList.add(fwBusinessEntityDto);

        return businessEntityDtoList;
    }

    @Override
    public BusinessEntityDto getBusinessEntityDtoBymemberId(Long memberId) {
        BusinessEntityDto businessEntityDto = new BusinessEntityDto();

        WxUser wxUser = wxUserMapper.selectWxUserById(memberId);
        if (wxUser == null || wxUser.getEntityId() == null) {
            return businessEntityDto;
        }

        Long entityId = Long.valueOf(wxUser.getEntityId());
        BusinessEntity businessEntity = businessEntityMapper.selectBusinessEntityById(entityId);
        if (businessEntity != null) {
            BeanUtils.copyProperties(businessEntity, businessEntityDto);
            String mainTypes = businessEntity.getBusinessMaintype();
            if (mainTypes == null) {
                return businessEntityDto;
            }
            String[] mainTypeArray = mainTypes.split(",");
            for (int i = 0; i < mainTypeArray.length; i++) {
                if (BuEntityContents.APPLY_TYPE_ZZ.compareTo(mainTypeArray[i]) == 0) {
                    businessEntityDto.setPlant(true);
                }
                if (BuEntityContents.APPLY_TYPE_JG.compareTo(mainTypeArray[i]) == 0) {
                    businessEntityDto.setProcess(true);
                }
                if (BuEntityContents.APPLY_TYPE_JX.compareTo(mainTypeArray[i]) == 0) {
                    businessEntityDto.setDist(true);
                }
                if (BuEntityContents.APPLY_TYPE_FW.compareTo(mainTypeArray[i]) == 0) {
                    businessEntityDto.setService(true);
                }
            }
        }
        return businessEntityDto;
    }

    private BusinessEntityDto genBusinessEntityDto(String applyType, Long memberId) {
        BusinessEntityDto businessEntityDto = new BusinessEntityDto();
        businessEntityDto.setApplyType(applyType);
        businessEntityDto.setAuditStatus(BuAuditStatusContents.APPLY_STATUS_MRZ);
        WxUser wxUser = wxUserMapper.selectWxUserById(memberId);
        if (wxUser == null) {
            return businessEntityDto;
        }
        String entityIdString = wxUser.getEntityId();
        if (entityIdString == null) {
            return businessEntityDto;
        }
        Long entityId = Long.valueOf(entityIdString);
        if (entityId == null || entityId == 0L) {
            return businessEntityDto;
        }
        BusinessEntity businessEntity = businessEntityMapper.selectBusinessEntityById(entityId);
        if (businessEntity == null) {
            return businessEntityDto;
        }

        BusinessApply businessApply = new BusinessApply();
        businessApply.setEntityId(entityId);
        businessApply.setBusinessMaintype(applyType);
        List<BusinessApply> businessApplyList = businessApplyMapper.selectBusinessApplyList(businessApply);
        if (businessApplyList == null || businessApplyList.size() <= 0) {
            return businessEntityDto;
        }

        BusinessApply businessApplyRs = businessApplyList.get(0);

        businessEntityDto.setAuditStatus(businessApplyRs.getAuditStatus());
        businessEntityDto.setEntityId(businessEntity.getEntityId());

        return businessEntityDto;
    }

    /**
     * 将不同的拷贝到DTO
     * @param businessEntityDto
     * @param businessApplyRs
     */
    private void copyCompanyData(BusinessEntityDto businessEntityDto,BusinessApply businessApplyRs){
        Long companyId=businessApplyRs.getCompanyId();
        String applyType=businessApplyRs.getBusinessMaintype();
        businessEntityDto.setApplyType(applyType);
        if(BuEntityContents.APPLY_TYPE_ZZ.equals(applyType)){
            BuplantCompany buplantCompany=buplantCompanyMapper.selectBuplantCompanyById(companyId);
            if(buplantCompany!=null){
                BeanUtils.copyProperties(buplantCompany, businessEntityDto);
                businessEntityDto.setPrincipalMan(buplantCompany.getLegalPerson());
                businessEntityDto.setPlantArea(buplantCompany.getPlantArea());
            }
        }
        if(BuEntityContents.APPLY_TYPE_JG.equals(applyType)){
            BuprocessCompany buprocessCompany=buprocessCompanyMapper.selectBuprocessCompanyById(companyId);
            if(buprocessCompany!=null){
                BeanUtils.copyProperties(buprocessCompany, businessEntityDto);
                businessEntityDto.setPrincipalMan(buprocessCompany.getLegalPerson());
            }
        }
        if(BuEntityContents.APPLY_TYPE_JX.equals(applyType)){
            BudistCompany budistCompany=budistCompanyMapper.selectBudistCompanyById(companyId);
            if(budistCompany!=null){
                BeanUtils.copyProperties(budistCompany, businessEntityDto);
                businessEntityDto.setPrincipalMan(budistCompany.getLegalPerson());
            }
        }
        if(BuEntityContents.APPLY_TYPE_FW.equals(applyType)){
            BuserviceCompany buserviceCompany=buserviceCompanyMapper.selectBuserviceCompanyById(companyId);
            if(buserviceCompany!=null){
                BeanUtils.copyProperties(buserviceCompany, businessEntityDto);
                businessEntityDto.setPrincipalMan(buserviceCompany.getLegalPerson());
            }
        }

    }

}
