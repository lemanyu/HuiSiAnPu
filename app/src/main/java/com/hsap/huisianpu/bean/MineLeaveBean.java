package com.hsap.huisianpu.bean;

import java.util.ArrayList;

/**
 * Created by zhao on 2017/12/7.
 */

public class MineLeaveBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":43,"startTime":"2017-12-08 08:30:00.0"},{"id":42,"startTime":"2017-12-08 08:30:00.0"},{"id":24,"startTime":"2017-12-07 08:30:00.0"},{"id":21,"startTime":"2017-12-07 08:30:00.0"},{"id":20,"startTime":"2017-12-07 08:30:00.0"},{"id":19,"startTime":"2017-12-07 08:30:00.0"},{"id":18,"startTime":"2017-12-07 08:30:00.0"},{"id":17,"startTime":"2017-12-07 08:30:00.0"},{"id":16,"startTime":"2017-12-07 08:30:00.0"},{"id":15,"startTime":"2017-12-07 08:30:00.0"},{"id":14,"startTime":"2017-12-07 08:30:00.0"},{"id":13,"startTime":"2017-12-07 08:30:00.0"},{"id":12,"startTime":"2017-12-07 08:30:00.0"},{"id":11,"startTime":"2017-12-07 00:00:00.0"},{"id":10,"startTime":"2017-12-07 00:00:00.0"},{"id":9,"startTime":"2017-12-07 00:00:00.0"},{"id":8,"startTime":"2017-12-07 00:00:00.0"},{"id":7,"startTime":"2017-12-07 00:00:00.0"}]
     */

    private int code;
    private Object msg;
    private boolean success;
    private ArrayList<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * id : 43
         * startTime : 2017-12-08 08:30:00.0
         */

        private int id;
        private String startTime;

        public int getId() {
            return id;
        }

        public String getStartTime() {
            return startTime;
        }

    }
}
