package com.hsap.huisianpu.bean;

/**
 * 解析推送的bean
 */

public class PushBean {

    /**
     * name : 王一
     * id : 39
     * state : true
     * type : 2
     */

    private String name;
    private int id;
    private int state;
    private int type;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

}
