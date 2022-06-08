package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.FarmProduceSale;
import com.sinonc.orders.ec.dto.ProduceSaleDto;
import com.sinonc.orders.ec.dto.ProduceSalePieDto;
import com.sinonc.orders.ec.mapper.FarmProduceSaleMapper;
import com.sinonc.orders.ec.service.IFarmProduceSaleService;
import com.sinonc.orders.ec.vo.FarmProduceSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 农产品销售信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-08-11
 */
@Service
public class FarmProduceSaleServiceImpl implements IFarmProduceSaleService {
    @Autowired
    private FarmProduceSaleMapper farmProduceSaleMapper;

    /**
     * 查询农产品销售信息
     *
     * @param saleId 农产品销售信息ID
     * @return 农产品销售信息
     */
    @Override
    public FarmProduceSale selectFarmProduceSaleById(Long saleId) {
        return farmProduceSaleMapper.selectFarmProduceSaleById(saleId);
    }

    /**
     * 查询农产品销售信息列表
     *
     * @param farmProduceSale 农产品销售信息
     * @return 农产品销售信息
     */
    @Override
    public List<FarmProduceSale> selectFarmProduceSaleList(FarmProduceSale farmProduceSale) {
        return farmProduceSaleMapper.selectFarmProduceSaleList(farmProduceSale);
    }

    /**
     * 新增农产品销售信息
     *
     * @param farmProduceSale 农产品销售信息
     * @return 结果
     */
    @Override
    public int insertFarmProduceSale(FarmProduceSale farmProduceSale) {
        farmProduceSale.setCreateTime(DateUtils.getNowDate());
        return farmProduceSaleMapper.insertFarmProduceSale(farmProduceSale);
    }

    /**
     * 修改农产品销售信息
     *
     * @param farmProduceSale 农产品销售信息
     * @return 结果
     */
    @Override
    public int updateFarmProduceSale(FarmProduceSale farmProduceSale) {
        farmProduceSale.setUpdateTime(DateUtils.getNowDate());
        return farmProduceSaleMapper.updateFarmProduceSale(farmProduceSale);
    }

    /**
     * 批量删除农产品销售信息
     *
     * @param saleIds 需要删除的农产品销售信息ID
     * @return 结果
     */
    @Override
    public int deleteFarmProduceSaleByIds(Long[] saleIds) {
        return farmProduceSaleMapper.deleteFarmProduceSaleByIds(saleIds);
    }

    /**
     * 删除农产品销售信息信息
     *
     * @param saleId 农产品销售信息ID
     * @return 结果
     */
    @Override
    public int deleteFarmProduceSaleById(Long saleId) {
        return farmProduceSaleMapper.deleteFarmProduceSaleById(saleId);
    }

    @Override
    public List<ProduceSaleDto> statisticFarmProduceSaleByUnitPrice(FarmProduceSaleVo farmProduceSaleVo) {
        return farmProduceSaleMapper.statisticFarmProduceSaleByUnitPrice(farmProduceSaleVo);
    }

    @Override
    public List<ProduceSaleDto> statisticFarmProduceSaleBySaleVol(FarmProduceSaleVo farmProduceSaleVo) {
        return farmProduceSaleMapper.statisticFarmProduceSaleBySaleVol(farmProduceSaleVo);
    }

    @Override
    public List<ProduceSaleDto> statisticFarmProduceSaleByIncome(FarmProduceSaleVo farmProduceSaleVo) {
        return farmProduceSaleMapper.statisticFarmProduceSaleByIncome(farmProduceSaleVo);
    }

    @Override
    public List<ProduceSalePieDto> statisticPieFarmProduceSaleByIncome() {
        return farmProduceSaleMapper.statisticPieFarmProduceSaleByIncome();
    }

    @Override
    public List<ProduceSalePieDto> statisticPieFarmProduceSaleBySaleVol() {
        return farmProduceSaleMapper.statisticPieFarmProduceSaleBySaleVol();
    }

    @Override
    public List<String> getProduceTypeList() {
        return farmProduceSaleMapper.getProduceTypeList();
    }
}
