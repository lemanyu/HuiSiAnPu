package com.hsap.huisianpu.bean;

import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhao on 2017/11/17.
 */

public class Bean implements Parcelable {
    private  String name;
    private  int pic;
    private  String number;

    private boolean select;
    /**
     * code : 200
     * msg : null
     * success : true
     * data : 39
     */

    private int code;
    private Object msg;
    private boolean success;
    private int data;

    public Bean(String name, int pic){
        this.name=name;
        this.pic=pic;
    }
    public Bean(){

    }

    public static final Creator<Bean> CREATOR = new Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel in) {
            return new Bean(in);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
