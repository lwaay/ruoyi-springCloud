package com.sinonc.common.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

public class AES256Util {


    /**
     *  使用需要导入 bcprov-jdk15on 依赖包
     * <dependency>
     *    <groupId>org.bouncycastle</groupId>
     *    <artifactId>bcprov-jdk15on</artifactId>
     *    <version>1.60</version>
     * </dependency>
     */

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptData(byte[] data, byte[] key) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(data), StandardCharsets.UTF_8);
    }

    /**
     * AES解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptData(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING, "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return new String(cipher.doFinal(data));
    }


}

