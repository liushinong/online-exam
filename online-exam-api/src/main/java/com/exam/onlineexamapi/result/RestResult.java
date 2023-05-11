package com.exam.onlineexamapi.result;

/**
 *  Created by D丶Cheng on 2018/7/4.
 * @param <T>
 */
public class RestResult<T> {

    private Integer code;
    private String msg;
    private T data;

    private RestResult(){}

    public RestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public RestResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RestResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RestResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
