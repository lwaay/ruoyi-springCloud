package com.sinonc.base.controller.api;

import com.sinonc.base.domain.Menu;
import com.sinonc.base.service.IMenuService;
import com.sinonc.base.vo.MenuTree;
import com.sinonc.base.vo.MenuTreeVo;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hhao
 */
@Api(tags = "大屏菜单")
@RestController
@RequestMapping("api/menu")
public class ApiMenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation("加载树形菜单")
    @GetMapping("/list")
    public AjaxResult menuList() {
        List<Menu> menus = menuService.selectMenuList(new Menu(1));
        List<MenuTreeVo> menuTreeVoList = menus.stream().map(e -> {
            MenuTreeVo menuTreeVo = new MenuTreeVo();
            BeanUtils.copyProperties(e, menuTreeVo);
            return menuTreeVo;
        }).sorted(Comparator.comparing(MenuTreeVo::getSort)).collect(Collectors.toList());
        MenuTree menuTree = new MenuTree(menuTreeVoList);
        return AjaxResult.success(menuTree.builTree());
    }

    @ApiOperation("获取溯源视频")
    @GetMapping("/getOriginsVideo")
    public AjaxResult getOriginsVideo(){
        return AjaxResult.success(menuService.selectMenuById(Constants.ORIGINS));
    }

}
