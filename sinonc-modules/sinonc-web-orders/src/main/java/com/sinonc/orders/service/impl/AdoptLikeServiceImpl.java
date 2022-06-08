package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.AdoptLike;
import com.sinonc.orders.mapper.AdoptLikeMapper;
import com.sinonc.orders.service.AdoptLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认养点赞 服务层实现
 * 
 * @author sinonc
 * @date 2019-10-06
 */
@Service
public class AdoptLikeServiceImpl implements AdoptLikeService {
	@Autowired
	private AdoptLikeMapper adoptLikeMapper;

	/**
     * 查询认养点赞信息
     * 
     * @param likeId 认养点赞ID
     * @return 认养点赞信息
     */
    @Override
	public AdoptLike getAdoptLikeById(Long likeId) {
	    return adoptLikeMapper.selectAdoptLikeById(likeId);
	}
	
	/**
     * 查询认养点赞列表
     * 
     * @param adoptLike 认养点赞信息
     * @return 认养点赞集合
     */
	@Override
	public List<AdoptLike> listAdoptLike(AdoptLike adoptLike) {
	    return adoptLikeMapper.listAdoptLike(adoptLike);
	}
	
    /**
     * 新增认养点赞
     * 
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	@Override
	public int addAdoptLike(AdoptLike adoptLike) {

		List<AdoptLike> list=adoptLikeMapper.selectAdoptLikeList1(adoptLike);
		if(list!=null&&list.size()>0){
			return -1;//新增失败，一个人对一个认养圈只能点赞一次
		}

	    return adoptLikeMapper.insertAdoptLike(adoptLike);
	}
	
	/**
     * 修改认养点赞
     * 
     * @param adoptLike 认养点赞信息
     * @return 结果
     */
	@Override
	public int updateAdoptLike(AdoptLike adoptLike) {
	    return adoptLikeMapper.updateAdoptLike(adoptLike);
	}

	/**
     * 删除认养点赞对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdoptLikeByIds(String ids) {
		return adoptLikeMapper.deleteAdoptLikeByIds(Convert.toStrArray(ids));
	}


	/**
	 * 删除认养点赞
	 *
	 * @param likeId 认养点赞ID
	 * @return 结果
	 */
	@Override
	public int deleteAdoptLikeById(Long likeId) {
		return adoptLikeMapper.deleteAdoptLikeById(likeId);
	}


	@Override
	public int deleteAdoptLike(AdoptLike adoptLike) {
		return adoptLikeMapper.deleteAdoptLike(adoptLike);
	}


}
