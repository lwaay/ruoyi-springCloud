package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.vo.MemberInfoVo;

import java.util.List;

/**
 * 会员信息Service接口
 *
 * @author ruoyi
 * @date 2020-03-05
 */
public interface MemberInfoService
{
    /**
     * 查询会员信息
     *
     * @param memberId 会员信息ID
     * @return 会员信息
     */
    public MemberInfo getMemberInfoById(Long memberId);
}
