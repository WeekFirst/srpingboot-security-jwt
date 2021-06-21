package com.mca.api.util;

/**
 * @Author an Stark
 * @Description: TODO
 * @Date 2021/6/19 12:15
 * @Version 1.0
 */
public enum RCode {
    //controller 业务编码
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    //service 业务编码
    SUCCESSFULLY(0 , "操作成功"),
    FAIL(1 , "操作失败"),
    NO_DATA(100 , "无数据"),
    DATA_EXIT(101 , "数据存在"),
    ERROR(-1 , "系统异常");


    private long code;

    private String message;

    RCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
