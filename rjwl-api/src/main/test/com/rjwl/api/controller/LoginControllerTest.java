package com.rjwl.api.controller;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLogin() {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "123456";//密码原值
        Object salt = "admin";//盐值
        int hashIterations = 3;//加密3次
        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        System.out.println(result);
    }

    //测试向上取整
    @Test
    public void testFloat(){
        Float yuqiMoney = 5.3f;
        double a=Math.ceil(yuqiMoney);
        int b=(int)a;
        System.out.println(yuqiMoney);
        System.out.println(a);
    }
}