package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/21.
 */

public class IsSignPushBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"type":0,"localTime":{"hour":9,"minute":34,"second":54,"nano":226000000}}
     */

    private int code;
    private Object msg;
    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        /**
         * type : 0
         * localTime : {"hour":9,"minute":34,"second":54,"nano":226000000}
         */

        private int type;
        private LocalTimeBean localTime;

        public int getType() {
            return type;
        }

        public LocalTimeBean getLocalTime() {
            return localTime;
        }

        public static class LocalTimeBean {
            /**
             * hour : 9
             * minute : 34
             * second : 54
             * nano : 226000000
             */

            private int hour;
            private int minute;
            private int second;
            private int nano;

            public int getHour() {
                return hour;
            }

        }
    }
}
