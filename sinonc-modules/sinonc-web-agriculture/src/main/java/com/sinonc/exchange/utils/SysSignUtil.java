package com.sinonc.exchange.utils;

import com.sinonc.common.core.utils.Md5Utils;
import com.sinonc.common.core.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 系统签名工具
 */
public class SysSignUtil {

    public static final String key = "zxyn888";

    public static String getSign(Object o) {
        try {

            Map<String, String> describe = BeanUtils.describe(o);
            describe.remove("class");
            describe.remove("sign");

            Set<String> keys = describe.keySet();
            TreeSet<String> params = new TreeSet<>(keys);
            StringBuilder sb = new StringBuilder();

            params.forEach((key) -> {

                String value = describe.get(key);

                if (StringUtils.isNotEmpty(value)) {
                    sb.append(key).append("=").append(value).append("&");
                }
            });
            sb.append("key=").append(key);

            return Md5Utils.hash(sb.toString());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
