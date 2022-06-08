package com.sinonc.orders.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.AdoptionCircleLikeMapper;
import com.sinonc.orders.domain.AdoptionCircleLike;
import com.sinonc.orders.service.IAdoptionCircleLikeService;

/**
 * 朋友圈点赞Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@Service
public class AdoptionCircleLikeServiceImpl implements IAdoptionCircleLikeService {
    @Autowired
    private AdoptionCircleLikeMapper adoptionCircleLikeMapper;

    /**
     * 查询朋友圈点赞
     *
     * @param id 朋友圈点赞ID
     * @return 朋友圈点赞
     */
    @Override
    public AdoptionCircleLike selectAdoptionCircleLikeById(Long id) {
        return adoptionCircleLikeMapper.selectAdoptionCircleLikeById(id);
    }

    /**
     * 查询朋友圈点赞列表
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 朋友圈点赞
     */
    @Override
    public List<AdoptionCircleLike> selectAdoptionCircleLikeList(AdoptionCircleLike adoptionCircleLike) {
        return adoptionCircleLikeMapper.selectAdoptionCircleLikeList(adoptionCircleLike);
    }

    /**
     * 新增朋友圈点赞
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 结果
     */
    @Override
    public int insertAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike) {
        List<AdoptionCircleLike> circleLikeList = adoptionCircleLikeMapper.selectAdoptionCircleLikeList(adoptionCircleLike);
        //存在历史点赞
        if(circleLikeList.size()>0){
            AdoptionCircleLike exist = circleLikeList.get(0);
            if("1".equals(exist.getDelFlg())){
                exist.setDelFlg("0");
                return adoptionCircleLikeMapper.updateAdoptionCircleLike(exist);
            }
            return 1;
        }
        adoptionCircleLike.setCreateTime(DateUtils.getNowDate());
        return adoptionCircleLikeMapper.insertAdoptionCircleLike(adoptionCircleLike);
    }

    /**
     * 修改朋友圈点赞
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 结果
     */
    @Override
    public int updateAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike) {
        return adoptionCircleLikeMapper.updateAdoptionCircleLike(adoptionCircleLike);
    }

    /**
     * 批量删除朋友圈点赞
     *
     * @param ids 需要删除的朋友圈点赞ID
     * @return 结果
     */
    @Override
    public int deleteAdoptionCircleLikeByIds(Long[] ids) {
        return adoptionCircleLikeMapper.deleteAdoptionCircleLikeByIds(ids);
    }

    /**
     * 删除朋友圈点赞信息
     *
     * @param id 朋友圈点赞ID
     * @return 结果
     */
    @Override
    public int deleteAdoptionCircleLikeById(Long id) {
        return adoptionCircleLikeMapper.deleteAdoptionCircleLikeById(id);
    }

    @Override
    public int removeAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike) {
        List<AdoptionCircleLike> circleLikeList = adoptionCircleLikeMapper.selectAdoptionCircleLikeList(adoptionCircleLike);
        //存在历史点赞
        if(circleLikeList.size()>0){
            AdoptionCircleLike exist = circleLikeList.get(0);
            if("0".equals(exist.getDelFlg())){
                exist.setDelFlg("1");
                return adoptionCircleLikeMapper.updateAdoptionCircleLike(exist);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int selectAdoptionCircleLikeCount(AdoptionCircleLike adoptionCircleLike) {
        return adoptionCircleLikeMapper.selectAdoptionCircleLikeCount(adoptionCircleLike);
    }
}
