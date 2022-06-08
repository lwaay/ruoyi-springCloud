package com.sinonc.ser.mapper;


import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.ser.dto.GoodMemberDto;

import java.util.List;

/**
 * 商品信息（主）Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface BizGoodInfoMapper {
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
     * 删除商品信息（主）
     *
     * @param infoId 商品信息（主）ID
     * @return 结果
     */
    int deleteBizGoodInfoById(Long infoId);

    /**
     * 批量删除商品信息（主）
     *
     * @param infoIds 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodInfoByIds(String[] infoIds);

    /**
     * 查询会员列表
     *
     * @return
     */
    List<GoodMemberDto> selectMemberList();
}
