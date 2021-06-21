package com.mca.api.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @Author an Stark
 * @Description: Json Result
 * @Date 2021/6/17 10:06
 * @Version 1.0
 */
public class Result implements Serializable {

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 响应业务状态
     */
    private long status;

    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应中的数据
     */
    private Object data;

    public static Result build(Long status, String msg, Object data) {
        return new Result(status, msg, data);
    }

    public static Result ok() {
        return new Result(null);
    }
    public static Result ok(Object data) {
        return new Result(data);
    }


    public static Result unAuth() {
        return new Result(RCode.UNAUTHORIZED.getCode(), RCode.UNAUTHORIZED.getMessage(), "");
    }

    public static Result error() {
        return new Result(RCode.FAILED.getCode(), RCode.FAILED.getMessage(), null);
    }

    public static Result error(Object msg) {
        return new Result(RCode.FAILED.getCode(), RCode.FAILED.getMessage(), msg);
    }

    public Result() {
    }

    public Result(Long status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data) {
        this.status = RCode.SUCCESS.getCode();
        this.msg = RCode.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsonData json数据 传的是Result的对象的Json字符串
     * @param clazz    Result中的object类型
     * @return
     */
    public static Result formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Result.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build((long) jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static Result format(String json) {
        try {
            return MAPPER.readValue(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData 传的是Result的对象的Json字符串
     *                 json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build((long) jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }



}
