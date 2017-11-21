package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/21.
 */

public class IsSignPushBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : 未打卡
     */

    private int code;
    private Object msg;
    private boolean success;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
