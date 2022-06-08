package com.sinonc.base.vo;

import java.util.ArrayList;
import java.util.List;

public class MenuTree {
    private final List<MenuTreeVo> menuList;

    public MenuTree(List<MenuTreeVo> menuList) {
        this.menuList = menuList;
    }

    //建立树形结构
    public List<MenuTreeVo> builTree() {
        List<MenuTreeVo> treeMenus = new ArrayList<>();
        for (MenuTreeVo menuNode : getRootNode()) {
            buildChilTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    //递归，建立子树形结构
    private MenuTreeVo buildChilTree(MenuTreeVo pNode) {
        List<MenuTreeVo> chilMenus = new ArrayList<>();
        for (MenuTreeVo menuNode : menuList) {
            if (menuNode.getParentId().equals(pNode.getId())) {
                chilMenus.add(buildChilTree(menuNode));
            }
        }
        pNode.setChildMenuList(chilMenus);
        return pNode;
    }

    //获取根节点
    private List<MenuTreeVo> getRootNode() {
        List<MenuTreeVo> rootMenuLists = new ArrayList<>();
        for (MenuTreeVo menuNode : menuList) {
            if (0L == menuNode.getParentId()) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}