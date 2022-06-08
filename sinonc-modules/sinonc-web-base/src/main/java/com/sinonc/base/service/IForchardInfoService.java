package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.ForchardInfo;

/**
 * 果园信息Service接口
 *
 * @author ruoyi
 * @date 2022-02-21
 */
public interface IForchardInfoService {
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
     * 批量删除果园信息
     *
     * @param orchIds 需要删除的果园信息ID
     * @return 结果
     */
    public int deleteForchardInfoByIds(Long[] orchIds);

    /**
     * 删除果园信息信息
     *
     * @param orchId 果园信息ID
     * @return 结果
     */
    public int deleteForchardInfoById(Long orchId);
}
