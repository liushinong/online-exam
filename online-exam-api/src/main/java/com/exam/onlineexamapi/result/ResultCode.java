package com.exam.onlineexamapi.result;
/**
 * Created by D丶Cheng on 2018/7/4.
 */
public enum ResultCode {

    SYSTEM_BUSY(-1, "system too busy"),
    SUCCESS(0, "success"),

    TOKEN_EXPIRE(421, "token expire"),

    FAIL(400, "错误"),
    INVALID_PARAM(402, "invalid param"),
    NULL_PARAM(403, "null param"),
    NULL_POINT(405, "Null Point Exception"),
    JSON_EXCEPTION(406, "JSON转换异常"),
    ACCESS_DENIED(501, "access denied"),
    INVALID_SMS_CODE(503, "invalid sms code"),
    INVALID_OPERATING(504, "invalid operating"),

    UNKNOWN_ERROR(100, "unknown error"),
    METHOD_NOT_ALLOW(102, "method not allow"),
    MESSAGE_NOT_READABLE(103, "message not readable"),
    UNSUPPORTED_MEDIA_TYPE(104, "unsupported media type"),
    PAGE_NOT_FOUND(105, "page not found"),

    USER_IS_DELETE(201, "用户已被删除 请联系管理人员"),
    USER_PASSWORD_NOT_MATCH(202, "account and password mismatch "),
    ACCOUNT_EXIST(203, "account already exist"),
    USER_IS_NOT_BIND(206, "用户已被解绑 请联系管理人员"),
    USER_IS_BIND(205, "用户已被绑定其他用户 请联系管理人员"),
    USER_TOKEN_NOT_FOUND(204,"用户token不存在 请重新登录");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
