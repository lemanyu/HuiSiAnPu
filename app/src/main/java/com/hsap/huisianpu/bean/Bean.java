package com.hsap.huisianpu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhao on 2017/11/17.
 */

public class Bean implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.pic);
        dest.writeString(this.number);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
    }

    protected Bean(Parcel in) {
        this.name = in.readString();
        this.pic = in.readInt();
        this.number = in.readString();
        this.select = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Bean> CREATOR = new Parcelable.Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel source) {
            return new Bean(source);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };
}
