package com.sinonc.ser.service.impl;

import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.ser.constant.GoodConstants;
import com.sinonc.service.domain.BizGoodBuy;
import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.ser.dto.BizGoodBuyBackDto;
import com.sinonc.ser.dto.BizGoodBuyDto;
import com.sinonc.ser.mapper.BizGoodBuyMapper;
import com.sinonc.ser.mapper.BizGoodInfoMapper;
import com.sinonc.ser.service.IBizGoodBuyService;
import com.sinonc.ser.vo.BizGoodBuyParaVo;
import com.sinonc.ser.vo.BizGoodBuyVo;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Service
public class BizGoodBuyServiceImpl implements IBizGoodBuyService {
    @Autowired
    private BizGoodBuyMapper bizGoodBuyMapper;

    @Autowired
    private BizGoodInfoMapper bizGoodInfoMapper;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    @Autowired
    private RemoteWxUserService wxUserService;

    /**
     * 查询采购
     *
     * @param buyId 采购ID
     * @return 采购
     */
    @Override
    public BizGoodBuy selectBizGoodBuyById(Long buyId) {
        return bizGoodBuyMapper.selectBizGoodBuyById(buyId);
    }

    /**
     * 查询采购列表
     *
     * @param bizGoodBuy 采购
     * @return 采购
     */
    @Override
    public List<BizGoodBuyBackDto> selectBizGoodBuyList(BizGoodBuy bizGoodBuy) {
        return bizGoodBuyMapper.selectBizGoodBuyList(bizGoodBuy);
    }

    /**
     * 新增采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    @Override
    public int insertBizGoodBuy(BizGoodBuy bizGoodBuy) {
        bizGoodBuy.setCreateTime(DateUtils.getNowDate());
        return bizGoodBuyMapper.insertBizGoodBuy(bizGoodBuy);
    }

    /**
     * 修改采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    @Override
    public int updateBizGoodBuy(BizGoodBuy bizGoodBuy) {
        bizGoodBuy.setUpdateTime(DateUtils.getNowDate());
        return bizGoodBuyMapper.updateBizGoodBuy(bizGoodBuy);
    }

    /**
     * 删除采购对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodBuyByIds(Long[] ids) {
        return bizGoodBuyMapper.deleteBizGoodBuyByIds(ids);
    }

    /**
     * 删除采购信息
     *
     * @param buyId 采购ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodBuyById(Long buyId) {
        return bizGoodBuyMapper.deleteBizGoodBuyById(buyId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBizGoodBuy(BizGoodBuyVo bizGoodBuyVo) {

        int rs = 0;
        //新增商品信息
        BizGoodInfo bizGoodInfo = new BizGoodInfo();
        bizGoodInfo.setInfoName(bizGoodBuyVo.getInfoName());
        bizGoodInfo.setBreed(bizGoodBuyVo.getBreed());
        bizGoodInfo.setMemberIdP(bizGoodBuyVo.getMemberIdP());

        bizGoodInfo.setInfoType(GoodConstants.INFO_TYPE_BUY);
        bizGoodInfo.setSaleAble(GoodConstants.SALE_ABLE_UP);
        bizGoodInfo.setCreateTime(new Date());
        bizGoodInfo.setDelFlag(GoodConstants.DEL_FLAG_NO);

        rs = rs + bizGoodInfoMapper.insertBizGoodInfo(bizGoodInfo);

        //新增采购商品
        BizGoodBuy bizGoodBuy = new BizGoodBuy();
        //获取商品信息ID
        bizGoodBuy.setInfoIdP(bizGoodInfo.getInfoId());
        bizGoodBuy.setPreference(GoodConstants.PREFERENCE_FLAG_GEN);
        bizGoodBuy.setShipAddress(bizGoodBuyVo.getShipAddress());
        bizGoodBuy.setReceiveAddress(bizGoodBuyVo.getReceiveAddress());
        bizGoodBuy.setBuySpecinfo(bizGoodBuyVo.getBuySpecinfo());
        bizGoodBuy.setPhone(bizGoodBuyVo.getPhone());
        bizGoodBuy.setShipDetail(bizGoodBuyVo.getShipDetail());
        bizGoodBuy.setReceiveDetail(bizGoodBuyVo.getReceiveDetail());
        bizGoodBuy.setMemberIdP(bizGoodBuyVo.getMemberIdP());
        bizGoodBuy.setBuyWeight(bizGoodBuyVo.getBuyWeight());
        bizGoodBuy.setIssueTime(new Date());
        bizGoodBuy.setDeliveryTime(bizGoodBuyVo.getDeliveryTime());
        bizGoodBuy.setQualityDescribe(bizGoodBuyVo.getQualityDescribe());
        bizGoodBuy.setCreateTime(new Date());
        bizGoodBuy.setDelFlag(GoodConstants.DEL_FLAG_NO);

        rs = rs + bizGoodBuyMapper.insertBizGoodBuy(bizGoodBuy);
        return rs;
    }

    @Override
    public List<BizGoodBuyDto> selectBizGoodBuyForPage(BizGoodBuyParaVo bizGoodBuyParaVo) {
        //品类
        String breeds = bizGoodBuyParaVo.getBreeds();
        String[] breedsArray = null;
        if (breeds != null && !"".equals(breeds) && !GoodConstants.ALL_FLAG_ONE.equals(breeds) && !GoodConstants.ALL_FLAG_ZERO.equals(breeds)) {
            breedsArray = breeds.split("[,，]");
        }

        //货源地址
        String shipAddress = bizGoodBuyParaVo.getShipAddress();
        String[] shipAddressArray = null;
        if (shipAddress != null && !"".equals(shipAddress) && !GoodConstants.ALL_FLAG_ONE.equals(shipAddress) && !GoodConstants.ALL_FLAG_ZERO.equals(shipAddress)) {
            shipAddressArray = shipAddress.split("[,，]");
        }

        //重量
        Long memberIdP = bizGoodBuyParaVo.getMemberIdP();
        Integer maxBuyWeight = bizGoodBuyParaVo.getMaxBuyWeight();
        Integer minBuyWeight = bizGoodBuyParaVo.getMinBuyWeight();

        String saleAble = null;
        if (memberIdP == null) {
            //查询所有已经上架的
            saleAble = GoodConstants.SALE_ABLE_UP;
        }
        //优选
        String preference = bizGoodBuyParaVo.getPreference();
        //商品信息
        String infoName = bizGoodBuyParaVo.getInfoName();

        List<BizGoodBuyDto> bizGoodBuyDtoList = bizGoodBuyMapper.selectBizGoodBuyForPage(infoName, preference,
                saleAble, memberIdP, maxBuyWeight,
                minBuyWeight, breedsArray, shipAddressArray);
//        List<BusinessEntity>  allEntity= userService.getAllBusinessEntity().getData();
        for(BizGoodBuyDto tmp : bizGoodBuyDtoList){
//            List<BusinessEntity> result = allEntity.stream().filter(t -> tmp.getMemberId().equals(t.getModMemberid())).collect(Collectors.toList());
//            if(result.size() != 0){
//                tmp.setCompanyName(result.get(0).getEntityName());
//            }
            tmp.setCompanyName("大排档");
        }

        return bizGoodBuyDtoList;
    }

    private void iniShipAddressName(BizGoodBuyDto bizGoodBuyDto) {
        String shipAddress = bizGoodBuyDto.getShipAddress();

        //转换货源地址
        if (shipAddress != null && !"".equals(shipAddress)) {
            String addressName = areaCodeService.changeAddressName(shipAddress).getData();
            bizGoodBuyDto.setShipAddressName(addressName);
        }

        //转换收货地址
        String receiveAddress = bizGoodBuyDto.getReceiveAddress();
        if (receiveAddress != null & !"".equals(receiveAddress)) {
            String addressName = areaCodeService.changeAddressName(receiveAddress).getData();
            bizGoodBuyDto.setReceiveAddressName(addressName);
        }

    }

    @Override
    public BizGoodBuyDto selectBizGoodBuyByInfoId(Long infoId) {
        BizGoodBuyDto bizGoodBuyDto = bizGoodBuyMapper.selectBizGoodBuyByInfoId(infoId);
        if (bizGoodBuyDto == null) {
            return null;
        }
        //在新增求购时已查询手机
//        WxUser user = wxUserService.getWxUserByMemberId(bizGoodBuyDto.getMemberId()).getData();
//        if(!ObjectUtils.isEmpty(user)){
//            bizGoodBuyDto.setPhone(user.getPhone());
//        }
        iniShipAddressName(bizGoodBuyDto);
        return bizGoodBuyDto;
    }

    @Override
    public int modifyBizGoodBuy(BizGoodBuyVo bizGoodBuyVo) {
        int rs = 0;
        //更新商品信息
        BizGoodInfo bizGoodInfo = new BizGoodInfo();
        bizGoodInfo.setInfoId(bizGoodBuyVo.getParaInfoId());
        bizGoodInfo.setInfoName(bizGoodBuyVo.getInfoName());
        bizGoodInfo.setBreed(bizGoodBuyVo.getBreed());
        bizGoodInfo.setSaleAble(bizGoodBuyVo.getSaleAble());
        bizGoodInfo.setUpdateTime(new Date());
        rs = rs + bizGoodInfoMapper.updateBizGoodInfo(bizGoodInfo);

        //更新采购信息
        BizGoodBuy bizGoodBuy = new BizGoodBuy();
        bizGoodBuy.setBuyId(bizGoodBuyVo.getParaBuyId());
        bizGoodBuy.setInfoIdP(bizGoodBuyVo.getInfoIdP());
        bizGoodBuy.setShipAddress(bizGoodBuyVo.getShipAddress());
        bizGoodBuy.setReceiveAddress(bizGoodBuyVo.getReceiveAddress());

        bizGoodBuy.setShipDetail(bizGoodBuyVo.getShipDetail());
        bizGoodBuy.setReceiveDetail(bizGoodBuyVo.getReceiveDetail());
        bizGoodBuy.setBuySpecinfo(bizGoodBuyVo.getBuySpecinfo());

        bizGoodBuy.setBuyWeight(bizGoodBuyVo.getBuyWeight());
        bizGoodBuy.setIssueTime(bizGoodBuyVo.getIssueTime());
        bizGoodBuy.setDeliveryTime(bizGoodBuyVo.getDeliveryTime());
        bizGoodBuy.setQualityDescribe(bizGoodBuyVo.getQualityDescribe());
        bizGoodBuy.setUpdateTime(new Date());
        rs = rs + bizGoodBuyMapper.updateBizGoodBuy(bizGoodBuy);

        return rs;
    }

    @Override
    public List<BizGoodBuyDto> changeBizGoodBuyDtoList(List<BizGoodBuyDto> bizGoodBuyDtoList) {
        List<BizGoodBuyDto> topYesList = new ArrayList<BizGoodBuyDto>();
        List<BizGoodBuyDto> topNoList = new ArrayList<BizGoodBuyDto>();

        for (BizGoodBuyDto tempBizGoodBuyDto : bizGoodBuyDtoList) {
            iniShipAddressName(tempBizGoodBuyDto);

            if (tempBizGoodBuyDto.getTopFlag().equals(GoodConstants.TOP_FLAG_YES)) {
                topYesList.add(tempBizGoodBuyDto);
            } else {
                topNoList.add(tempBizGoodBuyDto);
            }

        }

        topYesList.addAll(topNoList);

        return topYesList;
    }

}
