package com.sinonc.ser.service;

import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.ser.dto.GoodMemberDto;

import java.util.List;


/**
 * 商品信息（主）Service接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface IBizGoodInfoService {
    /**
     * 查询商品信息（主）
     *
     * @param infoId 商品信息（主）ID
     * @return 商品信息（主）
     */
    BizGoodInfo selectBizGoodInfoById(Long infoId);

    /**
     * 查询商品信息（主）列表
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 商品信息（主）集合
     */
    List<BizGoodInfo> selectBizGoodInfoList(BizGoodInfo bizGoodInfo);

    /**
     * 新增商品信息（主）
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 结果
     */
    int insertBizGoodInfo(BizGoodInfo bizGoodInfo);

    /**
     * 修改商品信息（主）
     *
     * @param bizGoodInfo 商品信息（主）
     * @return 结果
     */
    int updateBizGoodInfo(BizGoodInfo bizGoodInfo);

    /**
     * 批量删除商品信息（主）
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodInfoByIds(String ids);

    /**
     * 删除商品信息（主）信息
     *
     * @param infoId 商品信息（主）ID
     * @return 结果
     */
    int deleteBizGoodInfoById(Long infoId);

    /**
     * 查询会员列表
     * @return
     */
    List<GoodMemberDto> selectMemberList();

    /**
     * 根据用户id获取用户商品信息
     * @Param memberId
     * @Return
     */
    List<BizGoodInfo> listSupplyGoodsInfoByMember(Long memberId);
}
