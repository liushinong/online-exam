package com.exam.onlineexamapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json转换工具类
 */
public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * json转str
     * @param o
     * @param <T>
     * @return
     */
    public static <T> String toJsonStr(T o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
