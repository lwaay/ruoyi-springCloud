package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.MemberDict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 会员字典Mapper接口
 *
 * @author ruoyi
 * @date 2020-03-12
 */
public interface MemberDictMapper {
    /**
     * 查询会员字典
     *
     * @param dictId 会员字典ID
     * @return 会员字典
     */
    public MemberDict selectMemberDictById(Long dictId);

    /**
     * 查询会员字典列表
     *
     * @param memberDict 会员字典
     * @return 会员字典集合
     */
    public List<MemberDict> selectMemberDictList(MemberDict memberDict);

    /**
     * 新增会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    public int insertMemberDict(MemberDict memberDict);

    /**
     * 修改会员字典
     *
     * @param memberDict 会员字典
     * @return 结果
     */
    public int updateMemberDict(MemberDict memberDict);

    /**
     * 删除会员字典
     *
     * @param dictId 会员字典ID
     * @return 结果
     */
    public int deleteMemberDictById(Long dictId);

    /**
     * 批量删除会员字典
     *
     * @param dictIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberDictByIds(String[] dictIds);

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
    MemberDict selectDictByMemberIdAndType(Long memberId, String type);

    /**
     * 根据会员id和字典类型查询
     *
     * @param memberId
     * @param types
     * @return
     */
    List<MemberDict> selectDictByMemberIdAndTypes(Long memberId, ArrayList<String> types);

    /**
     * 批量添加
     *
     * @param linkedList
     * @return
     */
    int batchInsert(List<MemberDict> linkedList);

    /**
     * 按回复率查询专家会员id列表
     * @return
     */
    List<Map> selectMemberDictGroupByMemberId();

    /**
     * 只查单个人的回复率
     * @param memberId
     * @return
     */
    Map selectMemberDictGroupByMemberIdSingle(Long memberId);
}
