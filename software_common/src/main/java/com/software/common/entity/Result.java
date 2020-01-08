package com.software.common.entity;

/**
 * 统一返回结果
 */
public class Result {

    //是否成功
    private boolean success;

    //返回信息
    private String message;

    //状态码
    private Integer code;

    //返回结果集
    private Object data;

    public Result() {}

    public Result(boolean success, String message, Integer code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Result(boolean success, String message, Integer code, Object data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return data;
    }

    public void setResult(Object data) {
        this.data = data;
    }


}
