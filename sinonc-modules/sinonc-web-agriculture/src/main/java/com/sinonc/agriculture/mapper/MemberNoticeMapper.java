package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.MemberNotice;

import java.util.List;

public interface MemberNoticeMapper {

    int deleteByPrimaryKey(Long noticeId);

    int insert(MemberNotice record);

    int batchInsert(List<MemberNotice> memberNoticeList);

    int insertSelective(MemberNotice record);

    MemberNotice selectByPrimaryKey(Long noticeId);

    int updateByPrimaryKeySelective(MemberNotice record);

    int updateByPrimaryKey(MemberNotice record);

    List<MemberNotice> selectNoticeList(MemberNotice memberNotice);
}