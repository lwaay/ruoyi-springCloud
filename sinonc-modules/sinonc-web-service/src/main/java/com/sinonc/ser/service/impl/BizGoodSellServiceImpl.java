package com.sinonc.ser.service.impl;

import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.ser.constant.GoodConstants;
import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.service.domain.BizGoodSell;
import com.sinonc.service.domain.BizGoodSpecs;
import com.sinonc.ser.dto.BizGoodSellBackDto;
import com.sinonc.ser.dto.BizGoodSellDto;
import com.sinonc.ser.mapper.BizGoodInfoMapper;
import com.sinonc.ser.mapper.BizGoodSellMapper;
import com.sinonc.ser.mapper.BizGoodSpecsMapper;
import com.sinonc.ser.service.IBizGoodSellService;
import com.sinonc.ser.vo.BizGoodSellParaVo;
import com.sinonc.ser.vo.BizGoodSellVo;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
@Slf4j
public class BizGoodSellServiceImpl implements IBizGoodSellService {
    @Autowired
    private BizGoodSellMapper bizGoodSellMapper;

    @Autowired
    private BizGoodInfoMapper bizGoodInfoMapper;

    @Autowired
    private BizGoodSpecsMapper bizGoodSpecsMapper;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    @Autowired
    private RemoteWxUserService wxUserService;

    /**
     * 查询供应
     *
     * @param sellId 供应ID
     * @return 供应
     */
    @Override
    public BizGoodSell selectBizGoodSellById(Long sellId) {
        return bizGoodSellMapper.selectBizGoodSellById(sellId);
    }

    /**
     * 查询供应列表
     *
     * @param bizGoodSell 供应
     * @return 供应
     */
    @Override
    public List<BizGoodSellBackDto> selectBizGoodSellList(BizGoodSell bizGoodSell) {
        return bizGoodSellMapper.selectBizGoodSellList(bizGoodSell);
    }

    /**
     * 新增供应
     *
     * @param bizGoodSell 供应
     * @return 结果
     */
    @Override
    public int insertBizGoodSell(BizGoodSell bizGoodSell) {
        bizGoodSell.setCreateTime(DateUtils.getNowDate());
        return bizGoodSellMapper.insertBizGoodSell(bizGoodSell);
    }

    /**
     * 修改供应
     *
     * @param bizGoodSell 供应
     * @return 结果
     */
    @Override
    public int updateBizGoodSell(BizGoodSell bizGoodSell) {
        bizGoodSell.setUpdateTime(DateUtils.getNowDate());
        return bizGoodSellMapper.updateBizGoodSell(bizGoodSell);
    }

    /**
     * 删除供应对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodSellByIds(Long[] ids) {
        return bizGoodSellMapper.deleteBizGoodSellByIds(ids);
    }

    /**
     * 删除供应信息
     *
     * @param sellId 供应ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodSellById(Long sellId) {
        return bizGoodSellMapper.deleteBizGoodSellById(sellId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addBizGoodSell(BizGoodSellVo bizGoodSellVo) {
        int rs = 0;
        //插入主表数据
        BizGoodInfo bizGoodInfo = new BizGoodInfo();
        bizGoodInfo.setInfoName(bizGoodSellVo.getInfoName());
        bizGoodInfo.setBreed(bizGoodSellVo.getBreed());
        bizGoodInfo.setImageUrl(bizGoodSellVo.getImageUrl());
        bizGoodInfo.setContent(bizGoodSellVo.getContent());
        bizGoodInfo.setDetailImages(bizGoodSellVo.getDetailImages());
        bizGoodInfo.setMemberIdP(bizGoodSellVo.getMemberIdP());
        bizGoodInfo.setSubtitle(bizGoodSellVo.getSubtitle());
        //商品类型
        bizGoodInfo.setInfoType(GoodConstants.INFO_TYPE_SELLER);
        //上架
        bizGoodInfo.setSaleAble(GoodConstants.SALE_ABLE_UP);
        //置顶标记
        bizGoodInfo.setTopFlag(GoodConstants.TOP_FLAG_NO);
        bizGoodInfo.setCreateTime(new Date());
        bizGoodInfo.setDelFlag(GoodConstants.DEL_FLAG_NO);
        rs = rs + bizGoodInfoMapper.insertBizGoodInfo(bizGoodInfo);

        //插入供应商品表
        BizGoodSell bizGoodSell = new BizGoodSell();
        bizGoodSell.setInfoIdP(bizGoodInfo.getInfoId());
        bizGoodSell.setShipAddress(bizGoodSellVo.getShipAddress());
        bizGoodSell.setShipDetail(bizGoodSellVo.getShipDetail());
        //默认为普通
        bizGoodSell.setPassion(GoodConstants.PASSION_FLAG_GEN);
        bizGoodSell.setPhone(bizGoodSellVo.getPhone());
        bizGoodSell.setCreateTime(new Date());
        bizGoodSell.setDelFlag(GoodConstants.DEL_FLAG_NO);
        bizGoodSell.setIssueTime(new Date());
        rs = rs + bizGoodSellMapper.insertBizGoodSell(bizGoodSell);

        //插入商品规格
        List<BizGoodSpecs> specsList = bizGoodSellVo.getBizGoodSpecsList();
        if (specsList != null && specsList.size() > 0) {
            for (BizGoodSpecs bizGoodSpecs : specsList) {
                bizGoodSpecs.setCreateTime(new Date());
                bizGoodSpecs.setDelFlag(GoodConstants.DEL_FLAG_NO);
                bizGoodSpecs.setInfoIdP(bizGoodInfo.getInfoId());
                rs = rs + bizGoodSpecsMapper.insertBizGoodSpecs(bizGoodSpecs);
            }
        }

        return rs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BizGoodSellDto> selectBizGoodSellForPage(BizGoodSellParaVo bizGoodSellParaVo) {

        //品类
        String breeds = bizGoodSellParaVo.getBreeds();
        String[] breedsArray = null;
        if (breeds != null && !"".equals(breeds) && !GoodConstants.ALL_FLAG_ONE.equals(breeds) && !GoodConstants.ALL_FLAG_ZERO.equals(breeds)) {
            breedsArray = breeds.split("[,，]");
        }

        //货源地址
        String shipAddress = bizGoodSellParaVo.getShipAddress();
        String[] shipAddressArray = null;
        if (shipAddress != null && !"".equals(shipAddress) && !GoodConstants.ALL_FLAG_ONE.equals(shipAddress) && !GoodConstants.ALL_FLAG_ZERO.equals(shipAddress)) {
            shipAddressArray = shipAddress.split("[,，]");
        }

        //商品规格
        String goodSpecs = bizGoodSellParaVo.getGoodSpecs();
        String[] goodSpecsArray = null;
        if (goodSpecs != null && !"".equals(goodSpecs) && !GoodConstants.ALL_FLAG_ONE.equals(goodSpecs) && !GoodConstants.ALL_FLAG_ZERO.equals(goodSpecs)) {
            goodSpecsArray = goodSpecs.split("[,，]");
        }

        Long memberIdP = bizGoodSellParaVo.getMemberIdP();
        String saleAble = null;
        if (memberIdP == null) {
            //查询所有已经上架的
            saleAble = GoodConstants.SALE_ABLE_UP;
        }

        String passion = bizGoodSellParaVo.getPassion();
        String infoName = bizGoodSellParaVo.getInfoName();

        BigDecimal minPrice = bizGoodSellParaVo.getMinPrice();
        BigDecimal maxPrice = bizGoodSellParaVo.getMaxPrice();

        List<BizGoodSellDto> sellDtoList = bizGoodSellMapper.selectBizGoodSellForPage(minPrice, maxPrice, infoName, passion, saleAble, memberIdP,
                breedsArray, shipAddressArray, goodSpecsArray);
        sellDtoList.forEach(x->{
            WxUser user = wxUserService.getWxUserByMemberId(x.getMemberId()).getData();
            if(user != null && StringUtils.isNotEmpty(user.getName())){
                x.setRealName(user.getName());
            }
        });
        return sellDtoList;
    }

    @Override
    public BizGoodSellDto selectBizGoodSellByInfoId(Long infoId) {
        BizGoodSellDto bizGoodSellDto = bizGoodSellMapper.selectBizGoodSellByInfoId(infoId);
        WxUser user = wxUserService.getWxUserByMemberId(bizGoodSellDto.getMemberId()).getData();
        if(user != null && StringUtils.isNotEmpty(user.getName())){
            bizGoodSellDto.setRealName(user.getName());
//            bizGoodSellDto.setPhone(user.getPhone());
            bizGoodSellDto.setAvatar(user.getHeadimg());
        }
        if (bizGoodSellDto == null) {
            log.error("查找不到BizGoodSellDto，infoId：" + infoId);
            return new BizGoodSellDto();
        }
        iniShipAddressName(bizGoodSellDto);

        BigDecimal minPrice = bizGoodSpecsMapper.selectBizGoodSpecsMinPriceByInfoId(bizGoodSellDto.getInfoId());
        bizGoodSellDto.setMinPrice(minPrice);
        BigDecimal maxPrice = bizGoodSpecsMapper.selectBizGoodSpecsMaxPriceByInfoId(bizGoodSellDto.getInfoId());
        bizGoodSellDto.setMaxPrice(maxPrice);

        BizGoodSpecs bizGoodSpecs = new BizGoodSpecs();
        bizGoodSpecs.setInfoIdP(bizGoodSellDto.getInfoId());
        List<BizGoodSpecs> bizGoodSpecsList = bizGoodSpecsMapper.selectBizGoodSpecsList(bizGoodSpecs);
        bizGoodSellDto.setGoodSpecsList(bizGoodSpecsList);
        return bizGoodSellDto;
    }

    private void iniShipAddressName(BizGoodSellDto bizGoodSellDto) {
        String shipAddress = bizGoodSellDto.getShipAddress();

        String addressName = areaCodeService.changeAddressName(shipAddress).getData();
        bizGoodSellDto.setShipAddressName(addressName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyBizGoodSell(BizGoodSellVo bizGoodSellVo) {
        int rs = 0;
        //更新主表数据
        BizGoodInfo bizGoodInfo = new BizGoodInfo();
        bizGoodInfo.setInfoId(bizGoodSellVo.getParaInfoId());
        bizGoodInfo.setInfoName(bizGoodSellVo.getInfoName());
        bizGoodInfo.setBreed(bizGoodSellVo.getBreed());
        bizGoodInfo.setImageUrl(bizGoodSellVo.getImageUrl());
        bizGoodInfo.setContent(bizGoodSellVo.getContent());
        bizGoodInfo.setDetailImages(bizGoodSellVo.getDetailImages());
        //bizGoodInfo.setMemberIdP(bizGoodSellVo.getMemberIdP());
        //上下架
        bizGoodInfo.setSaleAble(bizGoodSellVo.getSaleAble());
        bizGoodInfo.setUpdateTime(new Date());
        rs = rs + bizGoodInfoMapper.updateBizGoodInfo(bizGoodInfo);

        //更新供应商品表
        BizGoodSell bizGoodSell = new BizGoodSell();
        bizGoodSell.setSellId(bizGoodSellVo.getParaSellId());
        //bizGoodSell.setInfoIdP(bizGoodSellVo.getParaInfoId());
        bizGoodSell.setShipAddress(bizGoodSellVo.getShipAddress());
        bizGoodSell.setShipDetail(bizGoodSellVo.getShipDetail());
        bizGoodSell.setUpdateTime(new Date());
        bizGoodSell.setIssueTime(new Date());
        rs = rs + bizGoodSellMapper.updateBizGoodSell(bizGoodSell);

        BizGoodSpecs bizGoodSpecs = new BizGoodSpecs();
        bizGoodSpecs.setInfoIdP(bizGoodSellVo.getParaInfoId());
        List<BizGoodSpecs> specsTempList = bizGoodSpecsMapper.selectBizGoodSpecsList(bizGoodSpecs);
        for (int i = 0; i < specsTempList.size(); i++) {
            BizGoodSpecs tempBizGoodSpecs = specsTempList.get(i);
            Long specsId = tempBizGoodSpecs.getSpecsId();
            bizGoodSpecsMapper.deleteBizGoodSpecsById(specsId);
        }

        //更新商品规格
        List<BizGoodSpecs> specsList = bizGoodSellVo.getBizGoodSpecsList();
        if (specsList != null && specsList.size() > 0) {
            for (int i = 0; i < specsList.size(); i++) {
                BizGoodSpecs tempBizGoodSpecs = specsList.get(i);
//                Long specsId = bizGoodSpecs.getSpecsId();
//                if (specsId != null) {
//                    String delFlag=bizGoodSpecs.getDelFlag();
//                    if(delFlag!=null&&"0".equals(delFlag)){
//                        //标记为删除
//                        bizGoodSpecsMapper.deleteBizGoodSpecsById(specsId);
//                    }
//                    bizGoodSpecs.setUpdateTime(new Date());
//                    bizGoodSpecs.setInfoIdP(bizGoodSellVo.getInfoIdP());
//                    rs = rs + bizGoodSpecsMapper.updateBizGoodSpecs(bizGoodSpecs);
//                } else {
                tempBizGoodSpecs.setInfoIdP(bizGoodSellVo.getParaInfoId());
                tempBizGoodSpecs.setCreateTime(new Date());
                rs = rs + bizGoodSpecsMapper.insertBizGoodSpecs(tempBizGoodSpecs);
//                }
            }
        }

        return rs;
    }

    @Override
    public List<BizGoodSellDto> changeBizGoodSellDtoList(List<BizGoodSellDto> adoptionCircleDtoList, BizGoodSellParaVo bizGoodSellParaVo) {
        //初始化最大、最小价格
        for (BizGoodSellDto bizGoodSellDto : adoptionCircleDtoList) {
            iniShipAddressName(bizGoodSellDto);
            BigDecimal minPrice = bizGoodSpecsMapper.selectBizGoodSpecsMinPriceByInfoId(bizGoodSellDto.getInfoId());
            bizGoodSellDto.setMinPrice(minPrice);
            BigDecimal maxPrice = bizGoodSpecsMapper.selectBizGoodSpecsMaxPriceByInfoId(bizGoodSellDto.getInfoId());
            bizGoodSellDto.setMaxPrice(maxPrice);
        }
        return adoptionCircleDtoList;
    }
}
