package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.UserRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 查询用户关联的部门、基地、店铺
 */
@Mapper
public interface UserRelationMapper {

    /**
     * 根据部门ID查询店铺ID
     * @param deptId 部门ID
     * @return 结果
     */
    UserRelation selectByDeptId(Long deptId);

    UserRelation selectByShopId(Long shopId);
}
