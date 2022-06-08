package com.sinonc.origins.service.impl;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.CreateQrCode;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.domain.ProMonitorsRelation;
import com.sinonc.origins.dto.ProProductInfoDto;
import com.sinonc.origins.mapper.ProMonitorsRelationMapper;
import com.sinonc.origins.mapper.ProProductInfoMapper;
import com.sinonc.origins.service.IPmBusinessService;
import com.sinonc.origins.service.IProOriginsInfoService;
import com.sinonc.origins.service.IProProductInfoService;
import com.sinonc.origins.vo.ProProductInfoVo;
import com.sinonc.system.api.RemoteEntityService;
import com.sinonc.system.api.domain.BusinessEntity;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 产品信息Service业务层处理
 *
 * @author zhangxl
 * @date 2020-10-21
 */
@Service
public class ProProductInfoServiceImpl implements IProProductInfoService {

    @Autowired
    private ProProductInfoMapper proProductInfoMapper;

    @Autowired
    private ProMonitorsRelationMapper proMonitorsRelationMapper;

    @Autowired
    private IPmBusinessService pmBusinessService;

    @Autowired
    private IProOriginsInfoService originsInfoService;

    @Autowired
    private RemoteEntityService remoteEntityService;

    /**
     * 查询朔源码线上链接
     */
    @Value("${qrcodeUrl}")
    private String url;


    @Override
    public List<ProProductInfo> getProductInfoBycode(Long pAreaCode) {
        List<ProProductInfo> productInfoList=new ArrayList<>();

        ProProductInfo productInfoPara=new ProProductInfo();
        productInfoPara.setBaseArea(pAreaCode);

        List<ProProductInfo> productInfoListbyArea = proProductInfoMapper.selectProProductInfoList(productInfoPara);
        if (productInfoListbyArea!=null&&productInfoListbyArea.size()>0){
            for (ProProductInfo productInfo : productInfoListbyArea) {
                try {
                    //c朔源码转成二维码base64位
                    String qrCode = CreateQrCode.getQrCode(url, productInfo.getProductCode());
                    productInfo.setQrUrl(qrCode);
                } catch (IOException e) {
                    e.printStackTrace();
                    productInfo.setProductCode(null);
                }
                productInfo.setImgs(productInfo.getMainImages().split(","));
                productInfoList.add(productInfo);
            }
        }
        return productInfoList;
    }

    @Override
    public long getCount(String baseArea) {
        return proProductInfoMapper.selectCount(baseArea);
    }

    /**
     * 查询产品信息
     *
     * @param productId 产品信息ID
     * @return 产品信息
     */
    @Override
    public ProProductInfoDto selectProProductInfoById(Long productId) {
        ProProductInfo proProductInfo = proProductInfoMapper.selectProProductInfoById(productId);

        ProProductInfoDto proProductInfoDto = new ProProductInfoDto();
        BeanUtils.copyProperties(proProductInfo, proProductInfoDto);

        ProMonitorsRelation proMonitorsRelation = new ProMonitorsRelation();
        proMonitorsRelation.setProductIdP(proProductInfo.getProductId());
        List<ProMonitorsRelation> proMonitorsRelationList = proMonitorsRelationMapper.selectProMonitorsRelationList(proMonitorsRelation);

        List<Long> myList = new ArrayList<>();

        for (ProMonitorsRelation monitorsRelation : proMonitorsRelationList) {
            if (!myList.contains(monitorsRelation.getMonitorsIdP())) {
                myList.add(monitorsRelation.getMonitorsIdP());
            }
        }

        Long[] monitors = myList.toArray(new Long[myList.size()]);

        proProductInfoDto.setMonitors(monitors);
        return proProductInfoDto;
    }

    /**
     * 查询产品信息列表
     *
     * @param proProductInfo 产品信息
     * @return 产品信息
     */
    @Override
    public List<ProProductInfo> selectProProductInfoList(ProProductInfo proProductInfo) {
        List<ProProductInfo> list = proProductInfoMapper.selectProProductInfoList(proProductInfo);
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        list.forEach(item->{
            item.setOriginsSize(originsInfoService.countOriginsByProductId(item.getProductId()));
        });

        for (ProProductInfo info : list) {
            try {
                String qrCode = CreateQrCode.getQrCode(url, info.getProductCode());
                info.setQrUrl(qrCode);
                if(!StringUtils.isEmpty(info.getMainImages())){
                    info.setImgs( info.getMainImages().split(","));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return list;
    }

    @Override
    public List<ProProductInfo> selectProductByLike(ProProductInfo productInfo) {
        List<ProProductInfo> productInfoList = proProductInfoMapper.selectProductInfoByLike(productInfo);
        for (ProProductInfo info : productInfoList) {
            try {
                R<BusinessEntity> businessEntityR = remoteEntityService.getEntityById(info.getTillmainIdP());
                if (businessEntityR.getCode() == 200){
                    BusinessEntity businessEntity = businessEntityR.getData();
                    if (Optional.ofNullable(businessEntity).isPresent()){
                        info.setProduceName(businessEntity.getEntityName());
                    }
                }
                String qrCode = CreateQrCode.getQrCode(url, String.valueOf(info.getProductId()));
                info.setQrUrl(qrCode);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return productInfoList ;
    }

    /**
     * 根据部门id获取部门关联的溯源产品
     */
    @Override
    public List<ProProductInfo> listProProductByDeptId(Long deptId){
        ProProductInfo query = new ProProductInfo();
        if (deptId == null){
           return proProductInfoMapper.selectProProductInfoList(query);
        }
        PmBusiness pmBusiness = pmBusinessService.selectPmBusinessByDeptId(deptId);
        if (Optional.ofNullable(pmBusiness).isPresent() && pmBusiness.getBusiId()!=null){
            query.setManuIdP(pmBusiness.getBusiId());
        }
        List<ProProductInfo> list = proProductInfoMapper.selectProProductInfoList(query);
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        list.forEach(item->{
            item.setOriginsSize(originsInfoService.countOriginsByProductId(item.getProductId()));
        });
        return list;
    }



    /**
     * 查询产品信息列表
     *
     * @param proProductInfoDto 产品信息
     * @return 产品信息
     */
    @Override
    public List<ProProductInfoDto> listProProductInfo(ProProductInfoDto proProductInfoDto) {
        return proProductInfoMapper.listProProductInfoDto(proProductInfoDto);
    }

    /**
     * 查询所有产品信息
     *
     * @return 产品信息集合
     */
    @Override
    public List<ProProductInfo> selectProductInfos(){
        return proProductInfoMapper.selectProductInfos();
    }

    /**
     * 新增产品信息
     *
     * @param proProductInfoVo 产品信息
     * @return 结果
     */
    @Override
    public int insertProProductInfo(ProProductInfoVo proProductInfoVo) {
        proProductInfoVo.setCreateTime(DateUtils.getNowDate());
        int rs = proProductInfoMapper.insertProProductInfo(proProductInfoVo);

        //更新产品编码
        String productInfoCode = createProductCode(proProductInfoVo);
        proProductInfoVo.setProductCode(productInfoCode);
        proProductInfoMapper.updateProProductInfo(proProductInfoVo);

        Long[] monitors = proProductInfoVo.getMonitors();

        List<Long> list = new ArrayList<>();

        if (monitors != null) {
            for (Long monitor : monitors) {
                if (!list.contains(monitor)) {
                    ProMonitorsRelation proMonitorsRelation = new ProMonitorsRelation();
                    proMonitorsRelation.setMonitorsIdP(monitor);
                    proMonitorsRelation.setProductIdP(proProductInfoVo.getProductId());
                    proMonitorsRelationMapper.insertProMonitorsRelation(proMonitorsRelation);
                    list.add(monitor);
                }
            }
        }

        return rs;
    }

    /**
     * 生成产品编码
     *
     * @param productInfo
     * @return
     */
    private String createProductCode(ProProductInfoVo productInfo) {

        Date date = productInfo.getProductionDate();
        String dateString = DateFormatUtils.format(date, "yyyyMMdd");
        StringBuffer sb = new StringBuffer();

        //format 方法 进行格式化
        sb.append("(01)690").append(String.format("%04d", productInfo.getProductId()))
                .append(getCheckCode(productInfo.getProductId()))
                .append("(10)")
                .append(dateString)
                .append(String.format("%03d", Integer.valueOf(productInfo.getShelfLife())))
                .append(productInfo.getShelfLifeUnit());
        return sb.toString();
    }

    /**
     * @return int
     * @Description:计算校验码
     * @Title: getCheckCode
     */
    private int getCheckCode(Long productId) {
        StringBuffer sb = new StringBuffer("690");
        sb.append(String.format("%04d", productId)).append("0");
        String s = sb.reverse().toString();
        List<Integer> listJi = new ArrayList<>();//奇数位第二位起
        List<Integer> listOu = new ArrayList<>();//偶数位第三位起
        for (int i = 1; i < s.length(); i++) {
            if (i % 2 == 0) {
                listOu.add(Integer.parseInt(s.charAt(i) + ""));
            } else {
                if (i == 1) {
                    continue;
                }
                listJi.add(Integer.parseInt(s.charAt(i) + ""));
            }
        }
        int oushuHe = 0;//1偶数合
        for (int i = 0; i < listOu.size(); i++) {
            oushuHe = oushuHe + listOu.get(i);
        }
        int oushuhe3 = oushuHe * 3;//2偶数合乘以3
        int jishuhe = 0;//3奇数合
        for (int i = 0; i < listJi.size(); i++) {
            jishuhe = jishuhe + listJi.get(i);
        }
        int three = oushuhe3 + jishuhe;//4奇数偶数之和
        int x = 0;
        for (int i = three; i >= three; i++) {
            if (i % 10 == 0) {
                x = i;
                break;
            }
        }
        return x - three;
    }

    /**
     * 修改产品信息
     *
     * @param proProductInfoVo 产品信息
     * @return 结果
     */
    @Override
    public int updateProProductInfo(ProProductInfoVo proProductInfoVo) {
        proProductInfoVo.setUpdateTime(DateUtils.getNowDate());
        int rs = proProductInfoMapper.updateProProductInfo(proProductInfoVo);

        proMonitorsRelationMapper.deleteProMonitorsRelationByProductId(proProductInfoVo.getProductId());
        List<Long> list = new ArrayList<>();

        Long[] monitors = proProductInfoVo.getMonitors();
        if (monitors != null && monitors.length > 0) {
            for (Long monitor : monitors) {
                if (!list.contains(monitor)) {
                    ProMonitorsRelation proMonitorsRelation = new ProMonitorsRelation();
                    proMonitorsRelation.setMonitorsIdP(monitor);
                    proMonitorsRelation.setProductIdP(proProductInfoVo.getProductId());
                    proMonitorsRelationMapper.insertProMonitorsRelation(proMonitorsRelation);
                    list.add(monitor);
                }
            }
        }

        return rs;
    }

    /**
     * 批量删除产品信息
     *
     * @param productIds 需要删除的产品信息ID
     * @return 结果
     */
    @Override
    public int deleteProProductInfoByIds(Long[] productIds) {
        return proProductInfoMapper.deleteProProductInfoByIds(productIds);
    }

    @Override
    public ProProductInfo selectProductByCode(String code) {
        return proProductInfoMapper.selectProductByCode(code);
    }

    /**
     * 删除产品信息信息
     *
     * @param productId 产品信息ID
     * @return 结果
     */
    @Override
    public int deleteProProductInfoById(Long productId) {
        return proProductInfoMapper.deleteProProductInfoById(productId);
    }
}
