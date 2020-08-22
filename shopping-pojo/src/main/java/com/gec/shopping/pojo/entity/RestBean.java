package com.gec.shopping.pojo.entity;

/**
 * 状态包装类
 */
public class RestBean {
    private boolean success;//是否成功

    private String message;//返回信息


    public RestBean() {
    }

    public RestBean(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
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
}
