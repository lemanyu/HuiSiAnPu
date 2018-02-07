package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/21.
 */

public class PushPerformanceBean {


    /**
     * next : 1
     * workerId : 5
     * isMy : true
     * typeName : 月度考核
     * name : 赵贵
     * id : 19
     * state : 0
     * type : 9
     */

    private int next;
    private int workerId;
    private boolean isMy;
    private String typeName;
    private String name;
    private int id;
    private int state;
    private int type;

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public boolean isIsMy() {
        return isMy;
    }

    public void setIsMy(boolean isMy) {
        this.isMy = isMy;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
