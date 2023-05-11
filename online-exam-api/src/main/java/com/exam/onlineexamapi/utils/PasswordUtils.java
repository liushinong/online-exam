package com.exam.onlineexamapi.utils;

/**
 * 密码工具类
 */
public class PasswordUtils {
    /**
     * 匹配密码
     * @param salt
     * @param rawPass
     * @param encPass
     * @return
     */
    public static Boolean matches(String salt, String rawPass, String encPass) {
        return new PasswordEncoder(salt).matches(encPass, rawPass);
    }
}
