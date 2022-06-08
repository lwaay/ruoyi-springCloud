package com.sinonc.common.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.parsing.XPathParser;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;


/**
 * @author huanghao
 * 微信api 调用工具
 */
public class WechatUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WechatUtil.class);

    public static final String AES = "AES";
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS7Padding";
    private static String APPID = "wxdb2d052900247c04";
    private static String SECRET = "f970041b70a06ca435cdb6cb0e913d36";

    private static String CONSUMEAPPID = "wx22a7f108aeda43df";
    private static String CONSUMESECRET = "fdc1ff3828fce55cb7874016694bffda";


    /**
     * 获取小程序codeid换取openid
     *
     * @param code
     * @return
     */
    public static JSONObject getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" +
                SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        PrintWriter out = null;
        BufferedReader in = null;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性 设置请求格式
            //设置返回类型
            conn.setRequestProperty("contentType", "text/plain");
            //设置请求类型
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setDoOutput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
            return jsonObject;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

//    public static Map<String, Object> getPhoneNumber(BuyerUserVo vo) {
//        Map<String,Object> map=new HashMap<>();
//        String openid= vo.getWechatOpenId();
//        String session_key = vo.getSessionKey();
//        if (!EmptyUtils.isEmpty(openid)) {
//
//            if(EmptyUtils.isEmpty(session_key)){
//                return null;
//            }
//            map.put("openid",openid);
//            // 被加密的数据
//            byte[] dataByte = Base64.decode(vo.getEncryptedData());
//            // 加密秘钥
//            byte[] keyByte = Base64.decode(session_key);
//            // 偏移量
//            byte[] ivByte = Base64.decode(vo.getIv());
//            try {
//                // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
//                int base = 16;
//                if (keyByte.length % base != 0) {
//                    int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
//                    byte[] temp = new byte[groups * base];
//                    Arrays.fill(temp, (byte) 0);
//                    System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
//                    keyByte = temp;
//                }
//                // 初始化
//                Security.addProvider(new BouncyCastleProvider());
//                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//                SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
//                AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
//                IvParameterSpec ivParameterSpec=new IvParameterSpec(ivByte);
//                parameters.init(ivParameterSpec);
//                cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
//                byte[] resultByte = cipher.doFinal(dataByte);
//                if (null != resultByte && resultByte.length > 0) {
//                    String result = new String(resultByte, "UTF-8");
//                    JSONObject jsonObject = JSONObject.parseObject(result);
//                    map.put("param",jsonObject);
//                    return map;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    /**
     * 获取小程序token和openid
     *
     * @param code
     * @return
     */
    public static JSONObject getAccessTokenOrOpenid(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
        PrintWriter out = null;
        BufferedReader in = null;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性 设置请求格式
            //设置返回类型
            conn.setRequestProperty("contentType", "text/plain");
            //设置请求类型
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setDoOutput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
            return jsonObject;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取小程序unionId
     *
     * @return
     */
    public static String getUnionId(String accessToken, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid;
        PrintWriter out = null;
        BufferedReader in = null;
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性 设置请求格式
            //设置返回类型
            conn.setRequestProperty("contentType", "text/plain");
            //设置请求类型
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setDoOutput(true);
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }
            JSONObject jsonObject = JSONObject.parseObject(stringBuffer.toString());
            return jsonObject.get("unionId").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @param code 调用微信登陆返回的Code
     * @return
     */
    public static JSONObject getSessionKeyOrOpenid(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", APPID);
        requestUrlParam.put("secret", SECRET);
        requestUrlParam.put("js_code", code);
        requestUrlParam.put("grant_type", "authorization_code");
        return JSON.parseObject(sendPost(requestUrl, requestUrlParam));
    }

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @param code 调用微信登陆返回的Code
     * @return
     */
    public static JSONObject getConsumeSessionKeyOrOpenid(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("appid", CONSUMEAPPID);
        requestUrlParam.put("secret", CONSUMESECRET);
        requestUrlParam.put("js_code", code);
        requestUrlParam.put("grant_type", "authorization_code");
        return JSON.parseObject(sendPost(requestUrl, requestUrlParam));
    }

//    /**
//     * 获取微信小程序 session_key 和 openid
//     *
//     * @param code 调用微信登陆返回的Code
//     * @param accessToken token
//     * @return
//     */
//    public static JSONObject getPhoneNumber(String accessToken, String code) {
//        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
//        Map<String, String> requestUrlParam = new HashMap<>();
//        requestUrlParam.put("code", code);
//        return JSON.parseObject(sendPost(requestUrl, requestUrlParam));
//    }

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @param code 调用微信登陆返回的Code
     * @param accessToken token
     * @return
     */
    public static JSONObject getPhoneNumber(String accessToken, String code) {
        String result = "";
        String requestUrl = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken;
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        json.put("code",code);
        String r = String.valueOf(json);
        RequestBody requestBody = RequestBody.create(mediaType,String.valueOf(json));
        // 1 创建okhttp客户端对象
        OkHttpClient client  = new OkHttpClient();
        // 2 request 默认是get请求
        Request request = new  Request.Builder().url(requestUrl).post(requestBody).build();
        // 3 进行请求操作
        try {
            Response response = client.newCall(request).execute();
            // 4 判断是否请求成功
            if(response.isSuccessful()){
                // 得到响应体中的身体,将其转成  string
                String string = response.body().string();
                JSONObject jsonObject = (JSONObject) JSONObject.parse(string);
                return jsonObject;
            }else {
                result = "获取token失败！";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return JSONObject.parseObject(result);
    }

    /**
     *
     * 获取access_token
     *
     * @return
     */
    public static String getAccessToken() {
        String result = "";
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + CONSUMEAPPID +"&secret=" + CONSUMESECRET;
        // 1 创建okhttp客户端对象
        OkHttpClient client  = new OkHttpClient();
        // 2 request 默认是get请求
        Request request = new  Request.Builder().url(requestUrl).build();
        // 3 进行请求操作
        try {
            Response response = client.newCall(request).execute();
            // 4 判断是否请求成功
            if(response.isSuccessful()){
                // 得到响应体中的身体,将其转成  string
                String string = response.body().string();
                JSONObject jsonObject = (JSONObject) JSONObject.parse(string);
                result = jsonObject.getString("access_token");
                System.out.println(result);
            }else {
                result = "获取token失败！";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * * 微信 数据解密<br/>
     * * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充<br/>
     * * 对称解密的目标密文:encrypted=Base64_Decode(encryptData)<br/>
     * * 对称解密秘钥:key = Base64_Decode(session_key),aeskey是16字节<br/>
     * * 对称解密算法初始向量:iv = Base64_Decode(iv),同样是16字节<br/>
     * *
     * * @param encrypted 目标密文
     * * @param session_key 会话ID
     * * @param iv 加密算法的初始向量
     */
    public static String wxDecrypt(String encrypted, String sessionKey, String iv) {
        String result = null;
        byte[] encrypted64 = Base64.decodeBase64(encrypted);
        byte[] key64 = Base64.decodeBase64(sessionKey);
        byte[] iv64 = Base64.decodeBase64(iv);
        try {
            init();
            result = new String(decrypt(encrypted64, key64, generateIV(iv64)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * * 初始化密钥
     */

    public static void init() throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator.getInstance(AES).init(128);
    }

    /**
     * * 生成iv
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        // iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        // Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * * 生成解密
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv)
            throws Exception {
        Key key = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        // 设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(encryptedData);
    }
}
