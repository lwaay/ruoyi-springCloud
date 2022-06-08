package com.sinonc.agriculture.service.impl;

import java.util.List;
import java.util.Optional;

import com.sinonc.common.core.utils.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.GrowtechLikeMapper;
import com.sinonc.agriculture.domain.GrowtechLike;
import com.sinonc.agriculture.service.GrowtechLikeService;
import com.sinonc.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 种养殖技术点赞Service业务层处理
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
@Service
public class GrowtechLikeServiceImpl implements GrowtechLikeService
{
    @Autowired
    private GrowtechLikeMapper growtechLikeMapper;

    /**
     * 查询种养殖技术点赞
     *
     * @param likeId 种养殖技术点赞ID
     * @return 种养殖技术点赞
     */
    @Override
    public GrowtechLike selectGrowtechLikeById(Long likeId)
    {
        return growtechLikeMapper.selectGrowtechLikeById(likeId);
    }

    /**
     * 查询种养殖技术点赞列表
     *
     * @param growtechLike 种养殖技术点赞
     * @return 种养殖技术点赞
     */
    @Override
    public List<GrowtechLike> selectGrowtechLikeList(GrowtechLike growtechLike)
    {
        return growtechLikeMapper.selectGrowtechLikeList(growtechLike);
    }

    /**
     * 新增种养殖技术点赞
     *
     * @param growtechLike 种养殖技术点赞
     * @return 结果
     */
    @Override
    public int insertGrowtechLike(GrowtechLike growtechLike)
    {
        growtechLike.setCreateTime(DateUtils.getNowDate());
        return growtechLikeMapper.insertGrowtechLike(growtechLike);
    }

    /**
     * 修改种养殖技术点赞
     *
     * @param growtechLike 种养殖技术点赞
     * @return 结果
     */
    @Override
    public int updateGrowtechLike(GrowtechLike growtechLike)
    {
        growtechLike.setUpdateTime(DateUtils.getNowDate());
        return growtechLikeMapper.updateGrowtechLike(growtechLike);
    }

    /**
     * 删除种养殖技术点赞对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGrowtechLikeByIds(String ids)
    {
        return growtechLikeMapper.deleteGrowtechLikeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除种养殖技术点赞信息
     *
     * @param likeId 种养殖技术点赞ID
     * @return 结果
     */
    @Override
    public int deleteGrowtechLikeById(Long likeId)
    {
        return growtechLikeMapper.deleteGrowtechLikeById(likeId);
    }

    @Override
    @Transactional
    public int addGrowtechLike(Long growtechIdP, Long memberIdP) {

        GrowtechLike growtechLike=new GrowtechLike();
        growtechLike.setGrowtechIdP(growtechIdP);
        growtechLike.setMemberIdP(memberIdP);
        List<GrowtechLike> list=growtechLikeMapper.selectGrowtechLikeList(growtechLike);

        if(!CollectionUtils.isEmpty(list)){
            //新增失败，一个人对同一个种植技术只能点赞一次
            return -1;
        }else {
            return growtechLikeMapper.insertGrowtechLike(growtechLike);
        }

    }

    @Override
    @Transactional
    public int deleteGrowtechLike(Long growId, Long memberId) {
        GrowtechLike growtechLike=new GrowtechLike();
        growtechLike.setGrowtechIdP(growId);
        growtechLike.setMemberIdP(memberId);

        return  growtechLikeMapper.deleteGrowtechLike(growtechLike);
    }
}
