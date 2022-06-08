package com.sinonc.system.api.factory;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 用户服务降级处理
 *
 * @author ruoyi
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public R<LoginUser> getUserInfo(String username) {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<List<SysUser>> listUser(SysUser user) {
                return R.fail("获取用户失败"+ throwable.getMessage());
            }

            @Override
            public R<String> getConfigKey(String configKey) {
                return R.fail("获取参数失败:" + throwable.getMessage());
            }

            @Override
            public R<List<WxUser>> infos(Long[] ids) {
                return R.fail("获取用户失败"+ throwable.getMessage());
            }

            @Override
            public R<WxUser> getWxUserById(@PathVariable("id") Long id) {
                return R.fail("获取用户失败"+ throwable.getMessage());
            }

            @Override
            public R<List<WxUser>> allUsers() {
                return R.fail("获取用户失败"+ throwable.getMessage());
            }

            @Override
            public R<List<BusinessEntity>> getAllBusinessEntity() {
                return R.fail("获取数据失败"+ throwable.getMessage());
            }
        };
    }
}
