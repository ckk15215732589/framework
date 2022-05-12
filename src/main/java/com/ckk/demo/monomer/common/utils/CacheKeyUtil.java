package com.ckk.demo.monomer.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheKeyUtil {

    public static String testKey(Integer id) {
        return String.format("large:monitor:test:%d", id);
    }

    public static String captchaKey(String uuid) {
        return String.format("monomer:captcha:%s", uuid);
    }

}
