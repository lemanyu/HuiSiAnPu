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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

        public void setType(int type) {
            this.type = type;
        }

        public LocalTimeBean getLocalTime() {
            return localTime;
        }

        public void setLocalTime(LocalTimeBean localTime) {
            this.localTime = localTime;
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

            public void setHour(int hour) {
                this.hour = hour;
            }

            public int getMinute() {
                return minute;
            }

            public void setMinute(int minute) {
                this.minute = minute;
            }

            public int getSecond() {
                return second;
            }

            public void setSecond(int second) {
                this.second = second;
            }

            public int getNano() {
                return nano;
            }

            public void setNano(int nano) {
                this.nano = nano;
            }
        }
    }
}
