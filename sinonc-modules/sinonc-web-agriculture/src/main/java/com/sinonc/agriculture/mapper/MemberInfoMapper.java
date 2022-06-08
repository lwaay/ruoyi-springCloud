package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.MemberInfo;

import java.util.List;

/**
 * 会员信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-05
 */
public interface MemberInfoMapper 
{
    /**
     * 查询会员信息
     * 
     * @param memberId 会员信息ID
     * @return 会员信息
     */
    public MemberInfo selectMemberInfoById(Long memberId);

    /**
     * 查询会员信息列表
     * 
     * @param memberInfo 会员信息
     * @return 会员信息集合
     */
    public List<MemberInfo> selectMemberInfoList(MemberInfo memberInfo);

    /**
     * 新增会员信息
     * 
     * @param memberInfo 会员信息
     * @return 结果
     */
    public int insertMemberInfo(MemberInfo memberInfo);

    /**
     * 修改会员信息
     * 
     * @param memberInfo 会员信息
     * @return 结果
     */
    public int updateMemberInfo(MemberInfo memberInfo);

    /**
     * 删除会员信息
     * 
     * @param memberId 会员信息ID
     * @return 结果
     */
    public int deleteMemberInfoById(Long memberId);

    /**
     * 批量删除会员信息
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberInfoByIds(String[] memberIds);

    /**
     * 根据微信UnionId获取会员信息
     *
     * @param unionId unionId
     * @return 会员信息
     */
    MemberInfo selectMemberInfoByUnionId(String unionId);

    MemberInfo selectMemberInfoByPhone(String phone);

    List<MemberInfo> selectMemberInfoByIds(Long[] memberIds);

    /**
     * 根据手机号查询用户数
     *
     * @param phone 手机号
     * @return 数量
     */
    int selectCountByPhone(String phone);

    MemberInfo selectMemberInfoBySysUUID(String sysUUID);
}
