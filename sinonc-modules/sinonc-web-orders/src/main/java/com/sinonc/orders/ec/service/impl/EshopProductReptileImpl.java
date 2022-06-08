package com.sinonc.orders.ec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinonc.orders.ec.domain.EshopProductReptile;
import com.sinonc.orders.ec.mapper.EshopProductReptileMapper;
import com.sinonc.orders.ec.service.IEshopProductReptileService;
import org.springframework.stereotype.Service;

/**
 * @author huanghao
 * @apiNote 商品实时爬取配置
 * @date 2021/3/22 15:29
 */
@Service
public class EshopProductReptileImpl extends ServiceImpl<EshopProductReptileMapper, EshopProductReptile> implements IEshopProductReptileService {
}
