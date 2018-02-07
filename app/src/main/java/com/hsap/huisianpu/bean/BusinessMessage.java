package com.hsap.huisianpu.bean;

public class BusinessMessage<T> {

    private Integer code;
    private String msg;
    private Boolean success = false;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BusinessMessage{" + "code=" + code + ", msg='" + msg + '\'' + ", success=" + success + ", data=" + data + '}';
    }
}
