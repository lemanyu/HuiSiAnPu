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
    private boolean state;
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
