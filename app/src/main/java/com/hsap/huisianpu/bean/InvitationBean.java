package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/20.
 */

public class InvitationBean {

    /**
     * code : 200
     * msg : 邀请成功
     * success : true
     * data : null
     */

    private int code;
    private String msg;
    private boolean success;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
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
