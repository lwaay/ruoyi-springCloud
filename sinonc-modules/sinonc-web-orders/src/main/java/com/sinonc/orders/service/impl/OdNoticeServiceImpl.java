package com.sinonc.orders.service.impl;

import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.service.IOdNoticeService;
import com.sinonc.orders.domain.OdNotice;
import com.sinonc.orders.mapper.OdNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 公告 服务层实现
 *
 * @author ruoyi
 */
@Service
public class OdNoticeServiceImpl implements IOdNoticeService {

    private final static String NOTICE_PACKAGE_KEY = "baise:notice:";

    @Autowired
    private OdNoticeMapper noticeMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public OdNotice selectNoticeById(Long noticeId) {
        OdNotice notice = noticeMapper.selectNoticeById(noticeId);
        return notice;
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<OdNotice> selectNoticeList(OdNotice notice) {
        List<OdNotice> list = noticeMapper.selectNoticeList(notice);
        if (CollectionUtils.isEmpty(list) || SecurityUtils.getUserId() == null || SecurityUtils.getUserId() <1L){
            return list;
        }
        list.forEach(item->{
            Set<String> personSet  = redisService.getCacheSet(NOTICE_PACKAGE_KEY+item.getNoticeId());
            boolean b = personSet.contains(SecurityUtils.getUserId()+"");
            if (b){
                item.setIsRead(1);
            }else {
                item.setIsRead(0);
            }
        });
        return list;
    }

    /**
     * 查询未读公告数
     *
     * @param userId 用户id
     * @return 公告集合
     */
    @Override
    public Integer selectNoticeByNotRead(Long userId){
        List<OdNotice> list = noticeMapper.selectNoticeList(new OdNotice());
        AtomicInteger count = new AtomicInteger();
        list.forEach(item->{
            Set<String> personSet  = redisService.getCacheSet(NOTICE_PACKAGE_KEY+item.getNoticeId());
            boolean b = personSet.contains(userId+"");
            if (!b){
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(OdNotice notice) {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(OdNotice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }


}
