package com.sinonc.orders.mapper;

import com.sinonc.orders.vo.SpecsDetailVo;

/**
 * 查询规格详情
 */
public interface SpecsDetailVoMapper {

    SpecsDetailVo selectById(Long specsId);

}
