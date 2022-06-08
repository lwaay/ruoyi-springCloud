package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.GrowExper;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.mapper.GrowExperMapper;
import com.sinonc.agriculture.mapper.MemberDictMapper;
import com.sinonc.agriculture.mapper.MemberInfoMapper;
import com.sinonc.agriculture.service.MemberInfoService;
import com.sinonc.agriculture.vo.MemberInfoVo;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-05
 */
@Service("memberInfo")
public class MemberInfoServiceImpl implements MemberInfoService {

    @Autowired
    private RemoteUserService userService;

    /**
     * 查询会员信息
     *
     * @param memberId 会员信息ID
     * @return 会员信息
     */
    @Override
    public MemberInfo getMemberInfoById(Long memberId) {
        return transfer(userService.getWxUserById(memberId).getData());
    }

    private MemberInfo transfer(WxUser user){
        MemberInfo memberInfo = new MemberInfo();
        if(null!=user){
            memberInfo.setMemberId(user.getId());
            memberInfo.setMobilePhone(user.getPhone());
            memberInfo.setNikeName(user.getName());
            memberInfo.setHeadImg(user.getHeadimg());
            memberInfo.setEntityId(user.getEntityId());
        }
        return memberInfo;
    }
}
