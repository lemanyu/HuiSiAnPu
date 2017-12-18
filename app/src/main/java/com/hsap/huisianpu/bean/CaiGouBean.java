package com.hsap.huisianpu.bean;

/**
 *
 */

public class CaiGouBean {
    private  String name;
    private String guige;
    private int shuliang;
    private double danjia;
    private double jine;
    private String piaoju;
    private String yongtu;
    public CaiGouBean(){

    }
    public CaiGouBean(String name,String guige,int shuliang,double danjia,double jine,String piaoju,String yongtu){
         this.name=name;
         this.guige=guige;
         this.shuliang=shuliang;
         this.danjia=danjia;
         this.jine=jine;
         this.piaoju=piaoju;
         this.yongtu=yongtu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public int getShuliang() {
        return shuliang;
    }

    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }

    public double getDanjia() {
        return danjia;
    }

    public void setDanjia(double danjia) {
        this.danjia = danjia;
    }

    public double getJine() {
        return jine;
    }

    public void setJine(double jine) {
        this.jine = jine;
    }

    public String getPiaoju() {
        return piaoju;
    }

    public void setPiaoju(String piaoju) {
        this.piaoju = piaoju;
    }

    public String getYongtu() {
        return yongtu;
    }

    public void setYongtu(String yongtu) {
        this.yongtu = yongtu;
    }
}
