package com.nowcoder.community.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author xindong
 * @create 2022-05-11 15:10
 */
public class CommunityUtils {

    //利用UUID生成随机字符串，同时去除里面的“-”，保留数字和字符
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5加密，将输入值利用commons-lang3判断是否为空值，通过spring的加密工具加密
    //一般我们在需要加密的内容后面拼接随机字符串后加密，增加安全性(黑客手里都有简单密码和对应加密库)
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
