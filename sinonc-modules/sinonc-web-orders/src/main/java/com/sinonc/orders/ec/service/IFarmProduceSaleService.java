package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.FarmProduceSale;
import com.sinonc.orders.ec.dto.ProduceSaleDto;
import com.sinonc.orders.ec.dto.ProduceSalePieDto;
import com.sinonc.orders.ec.vo.FarmProduceSaleVo;

import java.util.List;

/**
 * 农产品销售信息Service接口
 *
 * @author ruoyi
 * @date 2021-08-11
 */
public interface IFarmProduceSaleService {
    /**
     * 查询农产品销售信息
     *
     * @param saleId 农产品销售信息ID
     * @return 农产品销售信息
     */
    public FarmProduceSale selectFarmProduceSaleById(Long saleId);

    /**
     * 查询农产品销售信息列表
     *
     * @param farmProduceSale 农产品销售信息
     * @return 农产品销售信息集合
     */
    public List<FarmProduceSale> selectFarmProduceSaleList(FarmProduceSale farmProduceSale);

    /**
     * 新增农产品销售信息
     *
     * @param farmProduceSale 农产品销售信息
     * @return 结果
     */
    public int insertFarmProduceSale(FarmProduceSale farmProduceSale);

    /**
     * 修改农产品销售信息
     *
     * @param farmProduceSale 农产品销售信息
     * @return 结果
     */
    public int updateFarmProduceSale(FarmProduceSale farmProduceSale);

    /**
     * 批量删除农产品销售信息
     *
     * @param saleIds 需要删除的农产品销售信息ID
     * @return 结果
     */
    public int deleteFarmProduceSaleByIds(Long[] saleIds);

    /**
     * 删除农产品销售信息信息
     *
     * @param saleId 农产品销售信息ID
     * @return 结果
     */
    public int deleteFarmProduceSaleById(Long saleId);

    /**
     * 按单价统计
     *
     * @param farmProduceSaleVo
     * @return
     */
    List<ProduceSaleDto> statisticFarmProduceSaleByUnitPrice(FarmProduceSaleVo farmProduceSaleVo);

    /**
     * 按销量统计
     *
     * @param farmProduceSaleVo
     * @return
     */
    List<ProduceSaleDto> statisticFarmProduceSaleBySaleVol(FarmProduceSaleVo farmProduceSaleVo);

    /**
     * 按收入统计
     *
     * @param farmProduceSaleVo
     * @return
     */
    List<ProduceSaleDto> statisticFarmProduceSaleByIncome(FarmProduceSaleVo farmProduceSaleVo);

    /**
     * 统计收入饼图
     *
     * @return
     */
    List<ProduceSalePieDto> statisticPieFarmProduceSaleByIncome();

    /**
     * 统计销量饼图
     *
     * @return
     */
    List<ProduceSalePieDto> statisticPieFarmProduceSaleBySaleVol();

    /**
     * 获取农产品销售品种
     *
     * @return
     */
    List<String> getProduceTypeList();
}
