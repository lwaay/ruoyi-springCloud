package com.sinonc.orders.ec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinonc.orders.ec.domain.EshopProductRealtime;
import com.sinonc.orders.ec.mapper.EshopProductRealtimeMapper;
import com.sinonc.orders.ec.service.IEshopProductRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实时爬取电商数据Service业务层处理
 *
 * @author hhao
 * @date 2021-03-24
 */
@Service
public class EshopProductRealtimeServiceImpl extends ServiceImpl<EshopProductRealtimeMapper, EshopProductRealtime> implements IEshopProductRealtimeService {
    @Autowired
    private EshopProductRealtimeMapper eshopProductRealtimeMapper;

    /**
     * 查询实时爬取电商数据
     *
     * @param eshopId 实时爬取电商数据ID
     * @return 实时爬取电商数据
     */
    @Override
    public EshopProductRealtime selectEshopProductRealtimeById(Long eshopId) {
        return eshopProductRealtimeMapper.selectEshopProductRealtimeById(eshopId);
    }

    /**
     * 查询实时爬取电商数据列表
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 实时爬取电商数据
     */
    @Override
    public List<EshopProductRealtime> selectEshopProductRealtimeList(EshopProductRealtime eshopProductRealtime) {
        return eshopProductRealtimeMapper.selectEshopProductRealtimeList(eshopProductRealtime);
    }

    /**
     * 新增实时爬取电商数据
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 结果
     */
    @Override
    public int insertEshopProductRealtime(EshopProductRealtime eshopProductRealtime) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        return eshopProductRealtimeMapper.insertEshopProductRealtime(eshopProductRealtime);
    }

    /**
     * 修改实时爬取电商数据
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 结果
     */
    @Override
    public int updateEshopProductRealtime(EshopProductRealtime eshopProductRealtime) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                        return eshopProductRealtimeMapper.updateEshopProductRealtime(eshopProductRealtime);
    }

    /**
     * 批量删除实时爬取电商数据
     *
     * @param eshopIds 需要删除的实时爬取电商数据ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductRealtimeByIds(Long[] eshopIds) {
        return eshopProductRealtimeMapper.deleteEshopProductRealtimeByIds(eshopIds);
    }

    /**
     * 删除实时爬取电商数据信息
     *
     * @param eshopId 实时爬取电商数据ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductRealtimeById(Long eshopId) {
        return eshopProductRealtimeMapper.deleteEshopProductRealtimeById(eshopId);
    }

    /**
     * 获取开启状态的realTime定时任务
     * @return
     */
    @Override
    public List<EshopProductRealtime> listEshopProductRealtimeTask(){
        EshopProductRealtime query = new EshopProductRealtime();
        query.setReptileStatus(1);
        return eshopProductRealtimeMapper.selectEshopProductRealtimeList(query);
    }
}
