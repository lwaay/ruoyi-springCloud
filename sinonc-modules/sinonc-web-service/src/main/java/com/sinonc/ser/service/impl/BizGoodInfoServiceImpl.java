package com.sinonc.ser.service.impl;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.service.domain.BizGoodSpecs;
import com.sinonc.ser.dto.BizGoodBuyDto;
import com.sinonc.ser.dto.BizGoodSellDto;
import com.sinonc.ser.dto.GoodMemberDto;
import com.sinonc.ser.mapper.BizGoodBuyMapper;
import com.sinonc.ser.mapper.BizGoodInfoMapper;
import com.sinonc.ser.mapper.BizGoodSellMapper;
import com.sinonc.ser.mapper.BizGoodSpecsMapper;
import com.sinonc.ser.service.IBizGoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品信息（主）Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Service
public class BizGoodInfoServiceImpl implements IBizGoodInfoService {
    @Autowired
    private BizGoodInfoMapper bizGoodInfoMapper;

    @Autowired
    private BizGoodBuyMapper bizGoodBuyMapper;

    @Autowired
    private BizGoodSellMapper bizGoodSellMapper;

    @Autowired
    private BizGoodSpecsMapper bizGoodSpecsMapper;

    /**
     * 查询商品信息（主）
     *
     * @param infoId 商品信息（主）ID
     * @return 商品信息（主）
     */
    @Override
    public BizGoodInfo selectBizGoodInfoById(Long infoId) {
        return bizGoodInfoMapper.selectBizGoodInfoById(infoId);
    }

    /**
     * 查询商品信息（主）列表
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 商品信息（主）
     */
    @Override
    public List<BizGoodInfo> selectBizGoodInfoList(BizGoodInfo bizGoodInfo) {
        return bizGoodInfoMapper.selectBizGoodInfoList(bizGoodInfo);
    }

    /**
     * 新增商品信息（主）
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 结果
     */
    @Override
    public int insertBizGoodInfo(BizGoodInfo bizGoodInfo) {
        bizGoodInfo.setCreateTime(DateUtils.getNowDate());
        return bizGoodInfoMapper.insertBizGoodInfo(bizGoodInfo);
    }

    /**
     * 修改商品信息（主）
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 结果
     */
    @Override
    public int updateBizGoodInfo(BizGoodInfo bizGoodInfo) {
        return bizGoodInfoMapper.updateBizGoodInfo(bizGoodInfo);
    }

    /**
     * 删除商品信息（主）对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBizGoodInfoByIds(String ids) {
        BizGoodBuyDto bizGoodBuyDto = bizGoodBuyMapper.selectBizGoodBuyByInfoId(Long.valueOf(ids));
        if (bizGoodBuyDto != null) {
            bizGoodBuyMapper.deleteBizGoodBuyById(bizGoodBuyDto.getBuyId());
        }

        BizGoodSellDto bizGoodSellDto = bizGoodSellMapper.selectBizGoodSellByInfoId(Long.valueOf(ids));
        if (bizGoodSellDto != null) {
            bizGoodSellMapper.deleteBizGoodSellById(bizGoodSellDto.getSellId());
        }

        BizGoodSpecs bizGoodSpecs = new BizGoodSpecs();
        bizGoodSpecs.setInfoIdP(Long.valueOf(ids));
        List<BizGoodSpecs> bizGoodSpecsList = bizGoodSpecsMapper.selectBizGoodSpecsList(bizGoodSpecs);
        if (bizGoodSpecsList != null && bizGoodSpecsList.size() > 0) {
            for (BizGoodSpecs goodSpecs : bizGoodSpecsList) {
                bizGoodSpecsMapper.deleteBizGoodSpecsById(goodSpecs.getSpecsId());
            }
        }

        return bizGoodInfoMapper.deleteBizGoodInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品信息（主）信息
     *
     * @param infoId 商品信息（主）ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodInfoById(Long infoId) {
        return bizGoodInfoMapper.deleteBizGoodInfoById(infoId);
    }

    @Override
    public List<GoodMemberDto> selectMemberList() {
        List<GoodMemberDto> memberList = bizGoodInfoMapper.selectMemberList();
        return memberList;
    }

    /**
     * 根据用户id获取用户商品信息 b
     * @Param memberId
     * @Return
     */
    @Override
    public List<BizGoodInfo> listSupplyGoodsInfoByMember(Long memberId){
        if (memberId == null || memberId<1L) return null;
        BizGoodInfo bizGoodInfoparam = new BizGoodInfo();
        bizGoodInfoparam.setMemberIdP(memberId);
        bizGoodInfoparam.setInfoType("2");
        bizGoodInfoparam.setSaleAble("0");
        PageHelper.orderBy("top_flag");
        return bizGoodInfoMapper.selectBizGoodInfoList(bizGoodInfoparam);
    }
}
