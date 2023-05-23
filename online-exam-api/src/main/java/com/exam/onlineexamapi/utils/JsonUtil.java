package com.exam.onlineexamapi.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

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

    /**
     * 转json对象
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toJsonObject(String json, Class<T> valueType) {
        try {
            return MAPPER.<T>readValue(json, valueType);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toJsonListObject(String json, Class<T> valueType) {
        try {
            JavaType getCollectionType = MAPPER.getTypeFactory().constructParametricType(List.class, valueType);
            List<T> list = MAPPER.readValue(json, getCollectionType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
