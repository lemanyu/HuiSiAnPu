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

    public Object getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "code=" + code +
                ", msg=" + msg +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
