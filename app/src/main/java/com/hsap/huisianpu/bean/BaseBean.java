package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/23.
 */

public class BaseBean<T> {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : []
     */

    private int code;
    private String msg;
    private boolean success;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
