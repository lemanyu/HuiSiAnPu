package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/28.
 */

public class ReportFormStateBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : null
     */

    private int code;
    private Object msg;
    private boolean success;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
