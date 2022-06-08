package com.sinonc.ser.service;

import com.sinonc.service.domain.BizGoodBuy;
import com.sinonc.ser.dto.BizGoodBuyBackDto;
import com.sinonc.ser.dto.BizGoodBuyDto;
import com.sinonc.ser.vo.BizGoodBuyParaVo;
import com.sinonc.ser.vo.BizGoodBuyVo;

import java.util.List;


/**
 * 采购Service接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface IBizGoodBuyService {
    /**
     * 查询采购
     *
     * @param buyId 采购ID
     * @return 采购
     */
    BizGoodBuy selectBizGoodBuyById(Long buyId);

    /**
     * 查询采购列表
     *
     * @param bizGoodBuy 采购
     * @return 采购集合
     */
    List<BizGoodBuyBackDto> selectBizGoodBuyList(BizGoodBuy bizGoodBuy);

    /**
     * 新增采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    int insertBizGoodBuy(BizGoodBuy bizGoodBuy);

    /**
     * 修改采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    int updateBizGoodBuy(BizGoodBuy bizGoodBuy);

    /**
     * 批量删除采购
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodBuyByIds(Long[] ids);

    /**
     * 删除采购信息
     *
     * @param buyId 采购ID
     * @return 结果
     */
    int deleteBizGoodBuyById(Long buyId);

    /**
     * 分页查询我要买
     *
     * @param bizGoodBuyParaVo
     * @return
     */
    List<BizGoodBuyDto> selectBizGoodBuyForPage(BizGoodBuyParaVo bizGoodBuyParaVo);

    /**
     * 新增采购商品
     *
     * @param bizGoodBuyVo
     * @return
     */
    int addBizGoodBuy(BizGoodBuyVo bizGoodBuyVo);

    /**
     * 查询 采购商品详情
     *
     * @param infoId
     * @return
     */
    BizGoodBuyDto selectBizGoodBuyByInfoId(Long infoId);

    /**
     * 修改采购商品信息
     *
     * @param bizGoodBuyVo
     * @return
     */
    int modifyBizGoodBuy(BizGoodBuyVo bizGoodBuyVo);

    /**
     * 转换数据(手动置顶)
     *
     * @param bizGoodBuyDtoList
     * @return
     */
    List<BizGoodBuyDto> changeBizGoodBuyDtoList(List<BizGoodBuyDto> bizGoodBuyDtoList);

}
