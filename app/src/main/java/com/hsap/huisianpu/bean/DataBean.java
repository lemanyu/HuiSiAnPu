package com.hsap.huisianpu.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * 保存在数据库中的bean
 */

public class DataBean extends DataSupport{
    private int state;//状态  0可以处理 1不能处理
    private int type;//类型
    private int numberId;//条目id 删除用
    private String title;//标题
    private String content;//内容
    private int workId;//访问数据的id
    private Date date;
    private boolean check;//是否显示小红点

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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

    public int getNumberId() {
        return numberId;
    }

    public void setNumberId(int numberId) {
        this.numberId = numberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
