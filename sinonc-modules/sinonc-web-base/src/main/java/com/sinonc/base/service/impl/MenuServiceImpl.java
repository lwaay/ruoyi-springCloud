package com.sinonc.base.service.impl;

import com.sinonc.base.domain.Menu;
import com.sinonc.base.mapper.MenuMapper;
import com.sinonc.base.service.IMenuService;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大屏菜单Service业务层处理
 *
 * @author hhao
 * @date 2020-11-26
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询大屏菜单
     *
     * @param id 大屏菜单ID
     * @return 大屏菜单
     */
    @Override
    public Menu selectMenuById(Long id) {
        return menuMapper.selectMenuById(id);
    }

    /**
     * 查询大屏菜单列表
     *
     * @param menu 大屏菜单
     * @return 大屏菜单
     */
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        List<Menu> menus = menuMapper.selectMenuList(menu);
        menus.forEach(entity -> {
            if (entity.getParentId() == 0L) {
                entity.setParentName("无");
            }
            Menu parentMenu = menuMapper.selectMenuById(entity.getParentId());
            if (null != parentMenu) {
                entity.setParentName(parentMenu.getMenuName());
            }
        });
        return menus;


    }

    /**
     * 新增大屏菜单
     *
     * @param menu 大屏菜单
     * @return 结果
     */
    @Override
    public int insertMenu(Menu menu) {
        menu.setCreateTime(DateUtils.getNowDate());
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改大屏菜单
     *
     * @param menu 大屏菜单
     * @return 结果
     */
    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 批量删除大屏菜单
     *
     * @param ids 需要删除的大屏菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuByIds(Long[] ids) {
        return menuMapper.deleteMenuByIds(ids);
    }

    /**
     * 删除大屏菜单信息
     *
     * @param id 大屏菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long id) {
        return menuMapper.deleteMenuById(id);
    }
}
