package com.sinonc.ser.service;

import com.sinonc.service.domain.BizGoodSell;
import com.sinonc.ser.dto.BizGoodSellBackDto;
import com.sinonc.ser.dto.BizGoodSellDto;
import com.sinonc.ser.vo.BizGoodSellParaVo;
import com.sinonc.ser.vo.BizGoodSellVo;

import java.util.List;


/**
 * 供应Service接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface IBizGoodSellService {
    /**
     * 查询供应
     *
     * @param sellId 供应ID
     * @return 供应
     */
    BizGoodSell selectBizGoodSellById(Long sellId);

    /**
     * 查询供应列表
     *
     * @param bizGoodSell 供应
     * @return 供应集合
     */
    List<BizGoodSellBackDto> selectBizGoodSellList(BizGoodSell bizGoodSell);


    /**
     * 新增供应
     *
     * @param bizGoodSell 供应
     * @return 结果
     */
    int insertBizGoodSell(BizGoodSell bizGoodSell);

    /**
     * 修改供应
     *
     * @param bizGoodSell 供应
     * @return 结果
     */
    int updateBizGoodSell(BizGoodSell bizGoodSell);

    /**
     * 批量删除供应
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodSellByIds(Long[] ids);

    /**
     * 删除供应信息
     *
     * @param sellId 供应ID
     * @return 结果
     */
    int deleteBizGoodSellById(Long sellId);

    /**
     * 发布供应商品
     * @param bizGoodSellVo
     * @return
     */
    int addBizGoodSell(BizGoodSellVo bizGoodSellVo);

    /**
     * 查询我要卖列表
     * @param bizGoodSellParaVo
     * @return
     */
    List<BizGoodSellDto> selectBizGoodSellForPage(BizGoodSellParaVo bizGoodSellParaVo);

    /**
     * 查询商品详情
     *
     * @param infoId
     * @return
     */
    BizGoodSellDto selectBizGoodSellByInfoId(Long infoId);

    /**
     * 修改供应商品信息
     *
     * @param bizGoodSellVo
     * @return
     */
    int modifyBizGoodSell(BizGoodSellVo bizGoodSellVo);

    /**
     * 转换相关数据
     *
     * @param adoptionCircleDtoList 需转换的数据列表
     * @return
     */
    List<BizGoodSellDto> changeBizGoodSellDtoList(List<BizGoodSellDto> adoptionCircleDtoList, BizGoodSellParaVo bizGoodSellParaVo);

}
