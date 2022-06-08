package com.sinonc.openapi.service.impl;

import com.sinonc.common.security.service.TokenService;
import com.sinonc.openapi.domain.DataApiItem;
import com.sinonc.openapi.mapper.DataApiItemMapper;
import com.sinonc.openapi.service.IDataApiItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统接口明细Service业务层处理
 *
 * @author huanghao
 * @date 2020-11-05
 */
@Service
public class DataApiItemServiceImpl implements IDataApiItemService {
    @Autowired
    private DataApiItemMapper dataApiItemMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询系统接口明细
     *
     * @param id 系统接口明细ID
     * @return 系统接口明细
     */
    @Override
    public DataApiItem selectDataApiItemById(Long id) {
        return dataApiItemMapper.selectDataApiItemById(id);
    }

    /**
     * 查询系统接口明细列表
     *
     * @param dataApiItem 系统接口明细
     * @return 系统接口明细
     */
    @Override
    public List<DataApiItem> selectDataApiItemList(DataApiItem dataApiItem) {
        return dataApiItemMapper.selectDataApiItemList(dataApiItem);
    }

    /**
     * 新增系统接口明细
     *
     * @param dataApiItem 系统接口明细
     * @return 结果
     */
    @Override
    public int insertDataApiItem(DataApiItem dataApiItem) {
        dataApiItem.setCreateBy(tokenService.getLoginUser().getUsername());
        return dataApiItemMapper.insertDataApiItem(dataApiItem);
    }

    /**
     * 修改系统接口明细
     *
     * @param dataApiItem 系统接口明细
     * @return 结果
     */
    @Override
    public int updateDataApiItem(DataApiItem dataApiItem) {
        return dataApiItemMapper.updateDataApiItem(dataApiItem);
    }

    /**
     * 批量删除系统接口明细
     *
     * @param ids 需要删除的系统接口明细ID
     * @return 结果
     */
    @Override
    public int deleteDataApiItemByIds(Long[] ids) {
        return dataApiItemMapper.deleteDataApiItemByIds(ids);
    }

    /**
     * 删除系统接口明细信息
     *
     * @param id 系统接口明细ID
     * @return 结果
     */
    @Override
    public int deleteDataApiItemById(Long id) {
        return dataApiItemMapper.deleteDataApiItemById(id);
    }

}
