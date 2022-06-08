package com.sinonc.ser.service.impl;

import java.util.List;
import java.util.Optional;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.origins.api.RemotePmBusinessService;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.ser.service.ISerServiceAttrService;
import com.sinonc.service.domain.SerServiceAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.ser.mapper.SerServiceMapper;
import com.sinonc.service.domain.SerService;
import com.sinonc.ser.service.ISerServiceService;

/**
 * 社会化服务Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class SerServiceServiceImpl implements ISerServiceService {
    @Autowired
    private SerServiceMapper serServiceMapper;
    @Autowired
    private ISerServiceAttrService attrService;
    @Autowired
    private RemotePmBusinessService businessService;

    /**
     * 查询社会化服务
     *
     * @param serviceId 社会化服务ID
     * @return 社会化服务
     */
    @Override
    public SerService selectSerServiceById(Long serviceId) {
        SerService serService = serServiceMapper.selectSerServiceById(serviceId);
        if (!Optional.ofNullable(serService).isPresent()){
            return serService;
        }
        serService.setAttrs(attrService.allServiceAttrByServiceId(serviceId));
        R<PmBusiness> res = businessService.getInfo(serService.getBusiId());
        if (res.getCode()==200){
            serService.setBusiness(res.getData());
        }
        return serService;
    }

    /**
     * 查询社会化服务列表
     *
     * @param serService 社会化服务
     * @return 社会化服务
     */
    @Override
    public List<SerService> selectSerServiceList(SerService serService) {
        return serServiceMapper.selectSerServiceList(serService);
    }

    /**
     * 新增社会化服务
     *
     * @param serService 社会化服务
     * @return 结果
     */
    @Override
    public int insertSerService(SerService serService) {
        int i = serServiceMapper.insertSerService(serService);
        if(i == 1){
            serService.setCreateTime(DateUtils.getNowDate());
            serService.getAttrs().forEach(x->{
                x.setServiceIdP(serService.getServiceId());
                attrService.insertSerServiceAttr(x);
            });
        }

        return i;
    }

    /**
     * 修改社会化服务
     *
     * @param serService 社会化服务
     * @return 结果
     */
    @Override
    public int updateSerService(SerService serService) {
        int i = serServiceMapper.updateSerService(serService);
        if(i == 1){
            attrService.deleteSerServiceAttrByServiceId(serService.getServiceId());
            serService.getAttrs().forEach(x->{
                x.setServiceIdP(serService.getServiceId());
                attrService.insertSerServiceAttr(x);
            });
        }
        return i;
    }

    /**
     * 批量删除社会化服务
     *
     * @param serviceIds 需要删除的社会化服务ID
     * @return 结果
     */
    @Override
    public int deleteSerServiceByIds(Long[] serviceIds) {
        return serServiceMapper.deleteSerServiceByIds(serviceIds);
    }

    /**
     * 删除社会化服务信息
     *
     * @param serviceId 社会化服务ID
     * @return 结果
     */
    @Override
    public int deleteSerServiceById(Long serviceId) {
        return serServiceMapper.deleteSerServiceById(serviceId);
    }
}
