package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.system.api.vo.WxUserInfoVo;
import com.sinonc.system.api.vo.WxUserVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteWxUserFallbackFactory implements FallbackFactory<RemoteWxUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteWxUserFallbackFactory.class);

    @Override
    public RemoteWxUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteWxUserService() {
            @Override
            public R<LoginUser> wxRegister(@RequestBody WxUserInfoVo wxUserInfoVo) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> wxLogin(@RequestBody WxRegisterVo wxRegisterVo) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<WxUser> getUserByPhone(String phone) {
                return R.fail("根据手机号获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> phoneLogin(String phone) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> registerUser(@RequestBody WxUserVo wxUserVo) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> loginWxUser(@RequestBody WxUserVo wxUserVo) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<WxUser> getWxUserByMemberId(Long memberId) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<WxUser> getUserById(Long id) {
                return R.fail("获取微信用户失败:" + throwable.getMessage());
            }

            @Override
            public AjaxResult updateUserBy( WxUser wxUser) {
                return AjaxResult.error("修改微信用户失败:" + throwable.getMessage());
            }

            @Override
            public R<WxUserConsume> consumerWxLogin(WxRegisterVo wxRegisterVo) {
                return R.fail("登录微信用户失败:" + throwable.getMessage());
            }
        };
    }
}
