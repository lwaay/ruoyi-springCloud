package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.MemberDict;

import java.util.List;
import java.util.Map;

/**
 * 会员字典Service接口
 *
 * @author ruoyi
 * @date 2020-03-12
 */
public interface MemberDictService {
    /**
     * 查询会员字典
     *
     * @param dictId 会员字典ID
     * @return 会员字典
     */
    public MemberDict getMemberDictById(Long dictId);

    /**
     * 查询会员字典列表
     *
     * @param memberDict 会员字典
     * @return 会员字典集合
     */
    public List<MemberDict> getMemberDictList(MemberDict memberDict);

    /**
     * 新增会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    public int addMemberDict(MemberDict memberDict);

    /**
     * 修改会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    public int updateMemberDict(MemberDict memberDict);

    /**
     * 批量删除会员字典
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberDictByIds(String ids);

    /**
     * 删除会员字典信息
     *
     * @param dictId 会员字典ID
     * @return 结果
     */
    public int deleteMemberDictById(Long dictId);

    /**
     * 根据会员id查询会员的点赞和回答数量
     *
     * @param memberId
     * @return
     */
    List<MemberDict> getMemberDictByMemberId(Long memberId);

    /**
     * 根据会员ID和字典类型查询字典值
     *
     * @param memberId
     * @param type
     * @return
     */
    public MemberDict getDictByMemberIdAndType(Long memberId, String type);


    int batchAddMemberDict(List<MemberDict> linkedList);

    /**
     * 按回复率排名查询出专家对应的会员ID
     * @return
     */
    List<Map> selectMemberDictGroupByMemberId();

    /**
     * 只查一个会员的数据
     * @param memberId
     * @return
     */
    Map selectMemberDictGroupByMemberIdSingle(Long memberId);
}
