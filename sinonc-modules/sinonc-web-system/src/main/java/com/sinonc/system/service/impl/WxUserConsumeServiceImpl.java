package com.sinonc.system.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.vo.WxRegisterVo;
import com.sinonc.common.core.utils.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.WxUserConsumeMapper;
import com.sinonc.system.service.IWxUserConsumeService;

/**
 * 消费版用户Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Slf4j
@Service
public class WxUserConsumeServiceImpl implements IWxUserConsumeService {
    @Autowired
    private WxUserConsumeMapper wxUserConsumeMapper;

    /**
     * 查询消费版用户
     *
     * @param unionId 消费版用户唯一标识
     * @return 消费版用户
     */
    @Override
    public WxUserConsume selectWxUserConsumeByUnionId(String unionId){
        return wxUserConsumeMapper.selectWxUserConsumeByUnionId(unionId);
    }

    /**
     * 查询消费版用户
     *
     * @param phone 消费版用户电话
     * @return 消费版用户
     */
    @Override
    public WxUserConsume selectWxUserConsumeByPhone(String phone){
        return wxUserConsumeMapper.selectWxUserConsumeByPhone(phone);
    }

    /**
     * 查询消费版用户
     *
     * @param id 消费版用户ID
     * @return 消费版用户
     */
    @Override
    public WxUserConsume selectWxUserConsumeById(Long id) {
        return wxUserConsumeMapper.selectWxUserConsumeById(id);
    }

    /**
     * 查询消费版用户列表
     *
     * @param wxUserConsume 消费版用户
     * @return 消费版用户
     */
    @Override
    public List<WxUserConsume> selectWxUserConsumeList(WxUserConsume wxUserConsume) {
        return wxUserConsumeMapper.selectWxUserConsumeList(wxUserConsume);
    }

    /**
     * 新增消费版用户
     *
     * @param wxUserConsume 消费版用户
     * @return 结果
     */
    @Override
    public int insertWxUserConsume(WxUserConsume wxUserConsume) {
        wxUserConsume.setCreateTime(DateUtils.getNowDate());
        return wxUserConsumeMapper.insertWxUserConsume(wxUserConsume);
    }

    /**
     * 修改消费版用户
     *
     * @param wxUserConsume 消费版用户
     * @return 结果
     */
    @Override
    public int updateWxUserConsume(WxUserConsume wxUserConsume) {
        wxUserConsume.setUpdateTime(DateUtils.getNowDate());
        return wxUserConsumeMapper.updateWxUserConsume(wxUserConsume);
    }

    /**
     * 批量删除消费版用户
     *
     * @param ids 需要删除的消费版用户ID
     * @return 结果
     */
    @Override
    public int deleteWxUserConsumeByIds(Long[] ids) {
        return wxUserConsumeMapper.deleteWxUserConsumeByIds(ids);
    }

    /**
     * 删除消费版用户信息
     *
     * @param id 消费版用户ID
     * @return 结果
     */
    @Override
    public int deleteWxUserConsumeById(Long id) {
        return wxUserConsumeMapper.deleteWxUserConsumeById(id);
    }

    @Override
    public WxUserConsume wxRegister(WxRegisterVo wxRegisterVo) {
        JSONObject jsonObject = WechatUtil.getConsumeSessionKeyOrOpenid(wxRegisterVo.getLoginCode());
        Integer resultCode = jsonObject.getInteger("errcode");
        if (resultCode != null && resultCode != 0) {
            throw new BusinessException("获取微信openId异常，wxRegisterDto: {}" + jsonObject.toJSONString());
        }
        String openid = jsonObject.getString("openid");

        //获取唯一标识(只有绑定微信开发平台才有)
        String unionId = jsonObject.getString("unionid");

        WxUserConsume user;
        if (StringUtils.isEmpty(unionId)){
            user = wxUserConsumeMapper.selectWxUserConsumeByUnionId(unionId);
        }else {
            user = wxUserConsumeMapper.selectWxUserConsumeByOpenId(openid);
        }

        if (user == null) {
            // 注册
            String accessToken = WechatUtil.getAccessToken();
            JSONObject jsonObjectPhone = WechatUtil.getPhoneNumber(accessToken, wxRegisterVo.getCode());
            String phoneInfo = jsonObjectPhone.getString("phone_info");

            JSONObject json = JSONObject.parseObject(phoneInfo);
            String phone = json.getString("purePhoneNumber");
//            String sessionKey = jsonObject.getString("session_key");
//            String result = WechatUtil.wxDecrypt(wxRegisterVo.getEncryptedData(), sessionKey, wxRegisterVo.getIv());
//            JSONObject json = JSONObject.parseObject(result);
//            String phone = json.getString("purePhoneNumber");
            WxUserConsume userByPhone = wxUserConsumeMapper.selectWxUserConsumeByPhone(phone);
            try {
                if (userByPhone == null) {
                    user = new WxUserConsume("用户" + phone, phone, "用户" + phone, "", "888888", openid, null, unionId);
                    user.setType("H5");
                    this.insertWxUserConsume(user);
                } else {
                    userByPhone.setOpenid(openid);
                    userByPhone.setUnionid(unionId);
                    wxUserConsumeMapper.updateWxUserConsume(userByPhone);
                    user = userByPhone;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }
        return user;
    }

}
