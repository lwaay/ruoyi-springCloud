package com.sinonc.base.mapper;

import java.util.List;

import com.sinonc.base.domain.ForchardInfo;

/**
 * 果园信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-21
 */
public interface ForchardInfoMapper {
    /**
     * 查询果园信息
     *
     * @param orchId 果园信息ID
     * @return 果园信息
     */
    public ForchardInfo selectForchardInfoById(Long orchId);

    /**
     * 查询果园信息列表
     *
     * @param forchardInfo 果园信息
     * @return 果园信息集合
     */
    public List<ForchardInfo> selectForchardInfoList(ForchardInfo forchardInfo);

    /**
     * 新增果园信息
     *
     * @param forchardInfo 果园信息
     * @return 结果
     */
    public int insertForchardInfo(ForchardInfo forchardInfo);

    /**
     * 修改果园信息
     *
     * @param forchardInfo 果园信息
     * @return 结果
     */
    public int updateForchardInfo(ForchardInfo forchardInfo);

    /**
     * 删除果园信息
     *
     * @param orchId 果园信息ID
     * @return 结果
     */
    public int deleteForchardInfoById(Long orchId);

    /**
     * 批量删除果园信息
     *
     * @param orchIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteForchardInfoByIds(Long[] orchIds);
}
