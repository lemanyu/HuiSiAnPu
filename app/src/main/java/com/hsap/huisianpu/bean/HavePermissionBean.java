package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/2.
 */

public class HavePermissionBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : true
     */

    private int code;
    private Object msg;
    private boolean success;
    private boolean data;

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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
