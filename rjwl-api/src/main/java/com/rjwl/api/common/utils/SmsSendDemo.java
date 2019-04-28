package com.rjwl.api.common.utils;

import com.alibaba.fastjson.JSON;
import com.rjwl.api.constants.ManageConstant;

import java.io.UnsupportedEncodingException;

/**
 * @author tianyh
 * @Description:普通短信发送
 */
public class SmsSendDemo {
    public static final String charset = "utf-8";

    public static boolean sendSms(String phone, String msg, Integer userType) throws UnsupportedEncodingException {
        //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
        String smsSingleRequestServerUrl = "https://smssh1.253.com/msg/send/json";
        // 短信内容
        //String msg = "【253云通讯】你好,你的验证码是123456";
//        String msg ="【小赢花花】验证码：1234，请勿告知他人，谨防上当受骗";
        //状态报告
        String report = "true";
        SmsSendRequest smsSingleRequest = null;
        if (null != userType && userType == 2) {
            smsSingleRequest = new SmsSendRequest(ManageConstant.ACCOUNT_TWO, ManageConstant.PASSWORD_TWO, msg, phone, report);
        } else if (null != userType && userType == 3) {
            smsSingleRequest = new SmsSendRequest(ManageConstant.HK_ACCOUNT, ManageConstant.HK_PASSWORD, msg, phone, report);
            smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
        } else {
            smsSingleRequest = new SmsSendRequest(ManageConstant.ACCOUNT_ONE, ManageConstant.PASSWORD_ONE, msg, phone, report);
        }


        String requestJson = JSON.toJSONString(smsSingleRequest);

        System.out.println("before request string is: " + requestJson);

        String response = ChuangLanSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);

        System.out.println("response after request result is :" + response);

        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);

        System.out.println("response  toString is :" + smsSingleResponse);


        if (null == smsSingleResponse) {
            return false;
        }
        if ("0".equals(smsSingleResponse.getCode())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            sendSms("13342131381", "【喜羊羊】尊敬的刘纯俭，您的借款已超过还款日1天，共计应还1590.0元，请及时处理。已还忽略。", 3);
        } catch (Exception e) {

        }

    }
}
