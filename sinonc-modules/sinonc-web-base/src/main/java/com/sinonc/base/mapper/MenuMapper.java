package com.sinonc.base.mapper;

import com.sinonc.base.domain.Menu;

import java.util.List;

/**
 * 大屏菜单Mapper接口
 *
 * @author hhao
 * @date 2020-11-26
 */
public interface MenuMapper {
    /**
     * 查询大屏菜单
     *
     * @param id 大屏菜单ID
     * @return 大屏菜单
     */
    public Menu selectMenuById(Long id);

    /**
     * 查询大屏菜单列表
     *
     * @param menu 大屏菜单
     * @return 大屏菜单集合
     */
    public List<Menu> selectMenuList(Menu menu);

    /**
     * 新增大屏菜单
     *
     * @param menu 大屏菜单
     * @return 结果
     */
    public int insertMenu(Menu menu);

    /**
     * 修改大屏菜单
     *
     * @param menu 大屏菜单
     * @return 结果
     */
    public int updateMenu(Menu menu);

    /**
     * 删除大屏菜单
     *
     * @param id 大屏菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long id);

    /**
     * 批量删除大屏菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMenuByIds(Long[] ids);
}
