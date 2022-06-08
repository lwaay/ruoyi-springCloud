package com.sinonc.orders.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 收藏店铺持久层
 * @anthor wang
 */
@Mapper
public interface AttentionMapper {
    /**
     * 查询店铺关注人数
     * @param shopId
     * @return
     */
    public Integer queryAttention(String shopId);

    /**
     * 查询列表
     * @param map
     * @return
     */
    public List<String> selectAttention(Map map);

    /**
     * 插入店铺关注数据
     * @param map
     * @return
     */
    public int insertAttention(Map map);

    /**
     * 更新店铺关注
     * @param map
     * @return
     */
    public int updateAttention(Map map);

    /**
     * 取消店铺关注
     * @param map
     * @return
     */
    public int removeAttention(Map map);

    /**
     * 获取关注的店铺
     * @param memberById 关注人
     * @return
     */
    List<Long> selectLikeListByMemberId(@Param("memberId") long memberById);
}
