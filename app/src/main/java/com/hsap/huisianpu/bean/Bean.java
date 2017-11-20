package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/17.
 */

public class Bean {
    private  String name;
    private  int pic;
    private  String number;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    private boolean select;
    public Bean(String name, int pic){
        this.name=name;
        this.pic=pic;
    }
    public Bean(){

    }
    public Bean(String name,String number,boolean select){
        this.name=name;
        this.number=number;
        this.select=select;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
