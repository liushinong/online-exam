package com.exam.onlineexamapi.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExamUtil {
    /**
     * 字符串转数组
     * @param contentArray
     * @return
     */
    public static List<String> contentToArray(String contentArray) {
        return Arrays.asList(contentArray.split(","));
    }

    /**
     * 数组转字符串
     * @param contentArray
     * @return
     */
    public static String contentToString(List<String> contentArray) {
        return contentArray.stream().sorted().collect(Collectors.joining(","));
    }

    public static Integer scoreFromVM(String score) {
        if (score == null) {
            return null;
        } else {
            return (int) (Float.parseFloat(score));
        }
    }

    public static String secondToVM(Integer second) {
        String dateTimes;
        long days = second / (60 * 60 * 24);
        long hours = (second % (60 * 60 * 24)) / (60 * 60);
        long minutes = (second % (60 * 60)) / 60;
        long seconds = second % 60;
        if (days > 0) {
            dateTimes = days + "天 " + hours + "时 " + minutes + "分 " + seconds + "秒";
        } else if (hours > 0) {
            dateTimes = hours + "时 " + minutes + "分 " + seconds + "秒";
        } else if (minutes > 0) {
            dateTimes = minutes + "分 " + seconds + "秒";
        } else {
            dateTimes = seconds + " 秒";
        }
        return dateTimes;
    }
}
