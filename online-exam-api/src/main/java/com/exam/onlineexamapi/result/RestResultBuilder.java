package com.exam.onlineexamapi.result;


import java.util.HashMap;

/**
 * Created by D丶Cheng on 2018/7/4.
 *
 * @param <T>
 */
public class RestResultBuilder<T> {
    private Integer code = 0;

    private String msg = "返回成功";

    private T data;

    public RestResultBuilder<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public RestResultBuilder<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RestResultBuilder<T> setData(T data) {
        this.data = data;
        return this;
    }

    public RestResult<T> build() {
        return new RestResult<T>(code, msg, data != null ? data : (T) new HashMap());
    }

    public RestResult<T> error(String msg) {
        return new RestResult<T>(-1, msg, (T) new HashMap());
    }

    public RestResult<T> success(T data) {
        return new RestResult<T>(0, msg, data);
    }

    public RestResult<T> success() {
        return new RestResult<T>(0, "操作成功", (T) new HashMap());
    }

    public RestResult<T> success(String msg) {
        return new RestResult<T>(0, msg, (T) new HashMap());
    }

    public RestResult<T> success(String msg, T data) {
        return new RestResult<T>(0, msg,
                data != null ? data : (T) new HashMap());
    }

    /**
     * 失败返回
     * @param resultCode
     * @return
     */
    public RestResult<T> failure(ResultCode resultCode){
        return new RestResult<T>(resultCode.getCode(),resultCode.getMsg(),(T) new HashMap());
    }
}
