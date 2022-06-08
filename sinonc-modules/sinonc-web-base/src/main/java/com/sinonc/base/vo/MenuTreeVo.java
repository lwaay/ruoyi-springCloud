package com.sinonc.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author nuc
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuTreeVo {
    private Long id;
    private Long parentId;
    private String menuName;
    private String uri;
    private Integer sort;
    private List<MenuTreeVo> childMenuList;
    private Integer type;
    private String height;
    private String videoUrl;
}
