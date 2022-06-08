package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.mapper.MemberDictMapper;
import com.sinonc.agriculture.service.MemberDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 会员字典Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-12
 */
@Service
public class MemberDictServiceImpl implements MemberDictService
{
    @Autowired
    private MemberDictMapper memberDictMapper;

    /**
     * 查询会员字典
     *
     * @param dictId 会员字典ID
     * @return 会员字典
     */
    @Override
    public MemberDict getMemberDictById(Long dictId)
    {
        return memberDictMapper.selectMemberDictById(dictId);
    }

    /**
     * 查询会员字典列表
     *
     * @param memberDict 会员字典
     * @return 会员字典
     */
    @Override
    public List<MemberDict> getMemberDictList(MemberDict memberDict)
    {
        return memberDictMapper.selectMemberDictList(memberDict);
    }

    /**
     * 新增会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    @Override
    public int addMemberDict(MemberDict memberDict)
    {
        memberDict.setCreateTime(DateUtils.getNowDate());
        return memberDictMapper.insertMemberDict(memberDict);
    }

    /**
     * 修改会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    @Override
    public int updateMemberDict(MemberDict memberDict)
    {
        memberDict.setUpdateTime(DateUtils.getNowDate());
        return memberDictMapper.updateMemberDict(memberDict);
    }

    /**
     * 删除会员字典对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberDictByIds(String ids)
    {
        return memberDictMapper.deleteMemberDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员字典信息
     *
     * @param dictId 会员字典ID
     * @return 结果
     */
    @Override
    public int deleteMemberDictById(Long dictId)
    {
        return memberDictMapper.deleteMemberDictById(dictId);
    }

    /**
     * 根据会员id查询会员的点赞和回答数量
     *
     * @param memberId
     * @return
     */
    @Override
    public List<MemberDict> getMemberDictByMemberId(Long memberId) {
        return memberDictMapper.getMemberDictByMemberId(memberId);
    }


    /**
     * 根据会员ID和字典类型查询字典值
     *
     * @param memberId
     * @param type
     * @return
     */
    @Override
    public MemberDict getDictByMemberIdAndType(Long memberId, String type) {
        return memberDictMapper.selectDictByMemberIdAndType(memberId, type);
    }

    /**
     * 批量添加
     *
     * @param linkedList
     * @return
     */
    @Override
    public int batchAddMemberDict(List<MemberDict> linkedList) {
        return memberDictMapper.batchInsert(linkedList);
    }

    @Override
    public List<Map> selectMemberDictGroupByMemberId() {
        return memberDictMapper.selectMemberDictGroupByMemberId();
    }

    @Override
    public Map selectMemberDictGroupByMemberIdSingle(Long memberId) {
        return memberDictMapper.selectMemberDictGroupByMemberIdSingle(memberId);
    }

}
