package com.sinonc.ser.mapper;

import com.sinonc.service.domain.BizGoodSell;
import com.sinonc.ser.dto.BizGoodSellBackDto;
import com.sinonc.ser.dto.BizGoodSellDto;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 供应Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface BizGoodSellMapper {
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
     * 删除供应
     *
     * @param sellId 供应ID
     * @return 结果
     */
    int deleteBizGoodSellById(Long sellId);

    /**
     * 批量删除供应
     *
     * @param sellIds 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodSellByIds(Long[] sellIds);

    /**
     * 查询我要卖
     *
     * @param breedsArray
     * @param shipAddressArray
     * @param memberIdP        会员ID
     * @param breedsArray      品类
     * @param shipAddressArray 源地址
     * @param goodSpecsArray   商品规格
     * @param passion          热卖(1热卖 2普通)
     * @param saleAble         上下架 (0上架 1下架)
     * @return see BizGoodSellDto
     */
    List<BizGoodSellDto> selectBizGoodSellForPage(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice,
                                                  @Param("infoName") String infoName, @Param("passion") String passion, @Param("saleAble") String saleAble,
                                                  @Param("memberIdP") Long memberIdP, @Param("breedsArray") String[] breedsArray,
                                                  @Param("shipAddressArray") String[] shipAddressArray, @Param("goodSpecsArray") String[] goodSpecsArray);

    /**
     * 查询商品详情
     *
     * @param infoId
     * @return
     * @param infoId  商品ID
     * @return 供应商品信息
     */
    BizGoodSellDto selectBizGoodSellByInfoId(Long infoId);

}
