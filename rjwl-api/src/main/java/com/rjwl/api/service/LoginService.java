package com.rjwl.api.service;

public interface LoginService {
    /**
     * 发送验证码
     *
     * @param phone          手机号
     * @param verifyCodeSize 验证码长度
     * @return
     */
    boolean verifyCode(String phone, Integer verifyCodeSize);

    /**
     * 发送验证码
     *
     * @param phone         手机号
     * @return
     */
    boolean verifyCode(String phone);
}
