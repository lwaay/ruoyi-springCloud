package com.sinonc.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.YxShippingTemplatesFreeMapper;
import com.sinonc.orders.domain.YxShippingTemplatesFree;
import com.sinonc.orders.service.IYxShippingTemplatesFreeService;

/**
 * 模板运费表Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class YxShippingTemplatesFreeServiceImpl implements IYxShippingTemplatesFreeService {
    @Autowired
    private YxShippingTemplatesFreeMapper yxShippingTemplatesFreeMapper;

    /**
     * 查询模板运费表
     *
     * @param id 模板运费表ID
     * @return 模板运费表
     */
    @Override
    public YxShippingTemplatesFree selectYxShippingTemplatesFreeById(Long id) {
        return yxShippingTemplatesFreeMapper.selectYxShippingTemplatesFreeById(id);
    }

    /**
     * 查询模板运费表列表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 模板运费表
     */
    @Override
    public List<YxShippingTemplatesFree> selectYxShippingTemplatesFreeList(YxShippingTemplatesFree yxShippingTemplatesFree) {
        return yxShippingTemplatesFreeMapper.selectYxShippingTemplatesFreeList(yxShippingTemplatesFree);
    }

    /**
     * 新增模板运费表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 结果
     */
    @Override
    public int insertYxShippingTemplatesFree(YxShippingTemplatesFree yxShippingTemplatesFree) {
        return yxShippingTemplatesFreeMapper.insertYxShippingTemplatesFree(yxShippingTemplatesFree);
    }

    /**
     * 修改模板运费表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 结果
     */
    @Override
    public int updateYxShippingTemplatesFree(YxShippingTemplatesFree yxShippingTemplatesFree) {
        return yxShippingTemplatesFreeMapper.updateYxShippingTemplatesFree(yxShippingTemplatesFree);
    }

    /**
     * 批量删除模板运费表
     *
     * @param ids 需要删除的模板运费表ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesFreeByIds(Long[] ids) {
        return yxShippingTemplatesFreeMapper.deleteYxShippingTemplatesFreeByIds(ids);
    }

    /**
     * 删除模板运费表信息
     *
     * @param id 模板运费表ID
     * @return 结果
     */
    @Override
    public int deleteYxShippingTemplatesFreeById(Long id) {
        return yxShippingTemplatesFreeMapper.deleteYxShippingTemplatesFreeById(id);
    }
}
