package com.alfred.parkingalfred.utils;

import org.springframework.util.DigestUtils;

public class EncodingUtil {

    public static String encodingByMd5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
