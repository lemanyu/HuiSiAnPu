package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/11.
 */

public class PushAnnouncenBean {

    /**
     * name : 王二
     * id : 16
     * state : false
     * type : 7
     * content : 吧
     */

    private String name;
    private int id;
    private boolean state;
    private int type;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
