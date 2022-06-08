package com.sinonc.consume.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.consume.api.factory.RemoteWxUserConsumeFallbackFactory;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.factory.RemoteWxUserFallbackFactory;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 消费版用户服务
 *
 * @author ruoyi
 */
@FeignClient(contextId = "RemoteWxUserConsumeService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteWxUserConsumeFallbackFactory.class)
public interface RemoteWxUserConsumeService {

    /**
     * 查询会员信息
     *
     * @param unionId 会员唯一标识
     * @return 结果
     */
    @GetMapping(value = "/consume/getUserByUnionId/{unionId}")
    public R<WxUserConsume> getUserByUnionId(@PathVariable("unionId") String unionId);

    /**
     * 查询会员信息
     *
     * @param id 会员ID
     * @return 结果
     */
    @GetMapping(value = "/consume/getInfo/{id}")
    public R<WxUserConsume> getUserById(@PathVariable("id") Long id);

    /**
     * 查询会员信息
     *
     * @param phone 会员电话
     * @return 结果
     */
    @GetMapping(value = "/consume/getUserByPhone/{phone}")
    public R<WxUserConsume> getUserByPhone(@PathVariable("phone") String phone);

    /**
     * 添加会员信息
     *
     * @param wxUserConsume 消费版会员信息
     * @return 结果
     */
    @PostMapping(value = "/consume/addConsume")
    public R<Integer> addConsume(@RequestBody WxUserConsume wxUserConsume);

    /**
     * 修改会员信息
     *
     * @param wxUserConsume 消费版会员信息
     * @return 结果
     */
    @PostMapping(value = "/consume/updateConsume")
    public R<Integer> updateConsume(@RequestBody WxUserConsume wxUserConsume);
}
