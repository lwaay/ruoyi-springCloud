package com.sinonc.system.mapper;

import com.sinonc.consume.api.domain.WxUserConsume;

import java.util.List;


/**
 * 消费版用户Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-28
 */
public interface WxUserConsumeMapper {

    /**
     * 查询消费版用户
     *
     * @param openId 消费版用户唯一标识
     * @return 消费版用户
     */
    public WxUserConsume selectWxUserConsumeByOpenId(String openId);

    /**
     * 查询消费版用户
     *
     * @param unionId 消费版用户唯一标识
     * @return 消费版用户
     */
    public WxUserConsume selectWxUserConsumeByUnionId(String unionId);
    /**
     * 查询消费版用户
     *
     * @param phone 消费版用户电话
     * @return 消费版用户
     */
    public WxUserConsume selectWxUserConsumeByPhone(String phone);

    /**
     * 查询消费版用户
     *
     * @param id 消费版用户ID
     * @return 消费版用户
     */
    public WxUserConsume selectWxUserConsumeById(Long id);

    /**
     * 查询消费版用户列表
     *
     * @param wxUserConsume 消费版用户
     * @return 消费版用户集合
     */
    public List<WxUserConsume> selectWxUserConsumeList(WxUserConsume wxUserConsume);

    /**
     * 新增消费版用户
     *
     * @param wxUserConsume 消费版用户
     * @return 结果
     */
    public int insertWxUserConsume(WxUserConsume wxUserConsume);

    /**
     * 修改消费版用户
     *
     * @param wxUserConsume 消费版用户
     * @return 结果
     */
    public int updateWxUserConsume(WxUserConsume wxUserConsume);

    /**
     * 删除消费版用户
     *
     * @param id 消费版用户ID
     * @return 结果
     */
    public int deleteWxUserConsumeById(Long id);

    /**
     * 批量删除消费版用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxUserConsumeByIds(Long[] ids);
}
