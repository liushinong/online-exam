package com.exam.onlineexamapi.utils;

import java.util.Arrays;
import java.util.List;

public class ExamUtil {
    /**
     * 字符串转数组
     * @param contentArray
     * @return
     */
    public static List<String> contentToArray(String contentArray) {
        return Arrays.asList(contentArray.split(","));
    }

    public static Integer scoreFromVM(String score) {
        if (score == null) {
            return null;
        } else {
            return (int) (Float.parseFloat(score));
        }
    }
}
