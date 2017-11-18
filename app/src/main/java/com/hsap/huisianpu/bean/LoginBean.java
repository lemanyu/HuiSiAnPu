package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/18.
 */

public class LoginBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : 2
     */

    private int code;
    private Object msg;
    private boolean success;
    private int data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
