package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/11.
 */

public class PushAnnouncenBean {

    /**
     * name : 王二
     * id : 16
     * state : false
     * type : 1
     * content :吧
     */

    private String name;
    private int id;
    private int state;
    private int type;
    private String content;
    private int admin;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
