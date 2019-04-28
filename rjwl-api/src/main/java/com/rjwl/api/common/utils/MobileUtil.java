package com.rjwl.api.common.utils;

import cn.hutool.core.util.ReUtil;

/**
 * @author aniu
 */
public class MobileUtil {
    public static boolean isPhoneBase(String phone) {
        return ReUtil.isMatch("^(13[0-9]|14[57]|15[0-35-9]|17[0-8]|18[0-9])[0-9]{8}$", phone);
    }
}
