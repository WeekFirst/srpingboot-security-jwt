package com.mca.api.util;

import java.io.Serializable;

/**
 * @Author an Stark
 * @Description: service result
 * @Date 2021/6/17 12:39
 * @Version 1.0
 */
public class ServiceResult<T> implements Serializable {

    private boolean isSuccess;

    private String msg;

    private T value;

    private long code;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public ServiceResult(boolean isSuccess, T value, String msg, long code) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.value = value;
        this.code = code;
    }

    public static <T> ServiceResult<T> createSuccess(T v , RCode code) {
        return new ServiceResult<T>(true, v, code.getMessage(), code.getCode());
    }

    public static <T> ServiceResult<T> createFail(String msg, int rc) {
        return new ServiceResult<T>(false, null, msg, rc);
    }

    public static <T> ServiceResult<T> createFail(RCode serviceResultCode) {
        return new ServiceResult<T>(false, null, serviceResultCode.getMessage(), serviceResultCode.getCode());
    }

    public static <T> ServiceResult<T> createFail() {
        return new ServiceResult<T>(false, null, RCode.FAIL.getMessage(), RCode.FAIL.getCode());
    }

    public static <T> ServiceResult<T> createError(RCode serviceResultCode) {
        return new ServiceResult<T>(false, null, serviceResultCode.getMessage(), serviceResultCode.getCode());
    }

    public static <T> ServiceResult<T> createError() {
        return new ServiceResult<T>(false, null, RCode.ERROR.getMessage(), RCode.ERROR.getCode());
    }



}
