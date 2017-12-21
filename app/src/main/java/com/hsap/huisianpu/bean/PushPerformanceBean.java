package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/21.
 */

public class PushPerformanceBean {

    /**
     * workerId : 2
     * typeName : 月度考核
     * name : 王二
     * id : 5
     * state : true
     * type : 9
     */

    private int workerId;
    private String typeName;
    private String name;
    private int id;
    private boolean state;
    private int type;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

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
