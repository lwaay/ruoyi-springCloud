package com.sinonc.common.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.utils.StringUtils;
import com.sinonc.common.sms.config.properties.AliSmsProperties;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "sms", name = "access-key-id")
public class SmsService {


    @Autowired
    private IAcsClient client;

    @Autowired
    private AliSmsProperties properties;

    /**
     * 验证码短信模版ID
     */
    @Value("${sms.sms-code}")
    private String templateCode;

    /**
     * 物联网语句短信模板
     */
    @Value("${sms.warm-code}")
    private String warmCode;

    /**
     * 发送短信
     *
     * @param templateCode 短信模版ID
     * @param phone        电话号码
     * @param content      短信内容 JSON字符串
     */
    public boolean sendSms(String templateCode, String phone, String content) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", properties.getRegionId());
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", properties.getSignName());
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", content);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObject = new JSONObject(data);
            boolean code = "OK".equals(jsonObject.getString("Code"));
            if (!code) {
//                logger.error(jsonObject.getString("Message"));
            }
            return code;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone 电话号码
     * @param code  验证码
     * @return 发送结果
     */
    public boolean sendSmsCode(String phone, String code) {
        String smsCode = templateCode.split("[,，]")[0];
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("code", code);
        return this.sendSms(smsCode, phone, jsonObject.toJSONString());
    }

    /**
     * 审核提醒发送消息
     *
     * @param phone    电话号码
     * @param name     平台管理员名称
     * @param coopName 合作社名称
     * @return 发挥结果
     */
    public boolean sendSmsAudit(String phone, String name, String coopName) {
        String smsCode = templateCode.split("[,，]")[1];
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("coopName", coopName);
        return this.sendSms(smsCode, phone, jsonObject.toJSONString());
    }

    /**
     * 审核通过发送消息
     *
     * @param phone    电话号码
     * @param name     平台管理员名称
     * @param coopName 合作社名称
     * @param iphone   登录用户名 默认手机号
     * @param password 登录初始密码
     * @return 发挥结果
     */
    public boolean sendSmsAuditPass(String phone, String name, String coopName, String iphone, String password) {
        String smsCode = templateCode.split("[,，]")[2];
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("coopName", coopName);
        jsonObject.put("iphone", iphone);
        jsonObject.put("password", password);
        return this.sendSms(smsCode, phone, jsonObject.toJSONString());
    }

    /**
     * 审核失败发送消息
     *
     * @param phone    电话号码
     * @param name     平台管理员名称
     * @param coopName 合作社名称
     * @return 发挥结果
     */
    public boolean sendSmsAuditFails(String phone, String name, String coopName) {
        String smsCode = templateCode.split("[,，]")[3];
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("coopName", coopName);
        return this.sendSms(smsCode, phone, jsonObject.toJSONString());
    }


    /**
     * 发送设备状态信息
     * @param phone 电话号码
     * @param message 消息内容
     */
    public boolean sendWarmSms(String phone, String deviceName,String message){
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(message) || StringUtils.isEmpty(deviceName)){
            return false;
        }
        com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
        jsonObject.put("device_name", deviceName);
        jsonObject.put("warn_info", message);
        return this.sendSms(warmCode,phone,jsonObject.toJSONString());
    }
}
