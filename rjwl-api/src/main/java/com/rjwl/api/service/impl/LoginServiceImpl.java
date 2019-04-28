package com.rjwl.api.service.impl;

import cn.hutool.core.util.StrUtil;
import com.rjwl.api.common.utils.RedisUtil;
import com.rjwl.api.common.utils.SmsSendDemo;
import com.rjwl.api.common.utils.VerifyCodeUtils;
import com.rjwl.api.constants.ManageConstant;
import com.rjwl.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author aniu
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * 验证码默认长度
     */
    private static final Integer VERIFY_CODE_SIZE = 6;
    /**
     * redis中 验证码过期时间
     */
    private static final Integer EXPIRE_TIME = 60;
    /**
     * 验证码生成范围
     */
    private static final String VERIFY_SOURCE = "123456789";
    /**
     * 验证码短信模板
     */
    private static final String CONTENT_TEMPLATE = "【" + ManageConstant.SMS_NAME + "】验证码：[??]，请勿告知他人，谨防上当受骗";
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean verifyCode(String phone, Integer verifyCodeSize) {
        String verifyCode = redisUtil.get(phone);
        if (StrUtil.isNotBlank(verifyCode)) {
            //验证码已存在
            return false;
        }
        //获取自动生成的验证码
        verifyCode = generateVerifyCode(verifyCodeSize);
        //调用发送短信
        boolean status = sendSms(phone, CONTENT_TEMPLATE.replace("[??]", verifyCode));
        if (status) {
            //发送成功 将验证码缓存到redis key：phone; value：verifyCode;expire:60s
            redisUtil.setEx(phone, verifyCode, EXPIRE_TIME, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    /**
     * 发送短信
     *
     * @param phone      手机号
     * @param verifyCode 短信内容
     * @return
     */
    private boolean sendSms(String phone, String verifyCode) {
        // 发送短信
        try {
            boolean sendSMS = SmsSendDemo.sendSms(phone, verifyCode, 1);
            if (!sendSMS) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取验证码 1-9 任意数字
     *
     * @param verifyCodeSize
     * @return
     */
    private String generateVerifyCode(Integer verifyCodeSize) {
        return VerifyCodeUtils.generateVerifyCode(verifyCodeSize, VERIFY_SOURCE);
    }

    @Override
    public boolean verifyCode(String phone) {
        return this.verifyCode(phone, VERIFY_CODE_SIZE);
    }
}
