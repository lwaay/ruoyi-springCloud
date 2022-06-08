package com.sinonc.orders.service;

import com.sinonc.orders.domain.OdNotice;
import java.util.List;

/**
 * 公告 服务层
 *
 * @author ruoyi
 */
public interface IOdNoticeService {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public OdNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<OdNotice> selectNoticeList(OdNotice notice);

    /**
     * 查询未读公告数
     *
     * @param userId 用户id
     * @return 公告集合
     */
    public Integer selectNoticeByNotRead(Long userId);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(OdNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(OdNotice notice);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}
