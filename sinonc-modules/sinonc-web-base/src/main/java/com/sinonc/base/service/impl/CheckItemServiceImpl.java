package com.sinonc.base.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.CheckItemMapper;
import com.sinonc.base.domain.CheckItem;
import com.sinonc.base.service.ICheckItemService;

/**
 * 检验项目Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-24
 */
@Service
public class CheckItemServiceImpl implements ICheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    /**
     * 查询检验项目
     *
     * @param checkItemId 检验项目ID
     * @return 检验项目
     */
    @Override
    public CheckItem selectCheckItemById(Long checkItemId) {
        return checkItemMapper.selectCheckItemById(checkItemId);
    }

    /**
     * 查询检验项目列表
     *
     * @param checkItem 检验项目
     * @return 检验项目
     */
    @Override
    public List<CheckItem> selectCheckItemList(CheckItem checkItem) {
        return checkItemMapper.selectCheckItemList(checkItem);
    }

    /**
     * 新增检验项目
     *
     * @param checkItem 检验项目
     * @return 结果
     */
    @Override
    public int insertCheckItem(CheckItem checkItem) {
        checkItem.setCreateTime(DateUtils.getNowDate());
        return checkItemMapper.insertCheckItem(checkItem);
    }

    /**
     * 修改检验项目
     *
     * @param checkItem 检验项目
     * @return 结果
     */
    @Override
    public int updateCheckItem(CheckItem checkItem) {
        checkItem.setUpdateTime(DateUtils.getNowDate());
        return checkItemMapper.updateCheckItem(checkItem);
    }

    /**
     * 批量删除检验项目
     *
     * @param checkItemIds 需要删除的检验项目ID
     * @return 结果
     */
    @Override
    public int deleteCheckItemByIds(Long[] checkItemIds) {
        return checkItemMapper.deleteCheckItemByIds(checkItemIds);
    }

    /**
     * 删除检验项目信息
     *
     * @param checkItemId 检验项目ID
     * @return 结果
     */
    @Override
    public int deleteCheckItemById(Long checkItemId) {
        return checkItemMapper.deleteCheckItemById(checkItemId);
    }
}
