package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/21.
 */

public class DaySignPushBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"id":1,"topSign":{"month":"NOVEMBER","year":2017,"dayOfMonth":21,"dayOfWeek":"TUESDAY","dayOfYear":325,"monthValue":11,"hour":14,"minute":3,"nano":0,"second":21,"chronology":{"id":"ISO","calendarType":"iso8601"}},"downSign":null,"workersId":1,"state":"异常","topPosition":"河北省秦皇岛市海港区龙海道77号","createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":21,"dayOfWeek":"TUESDAY","dayOfYear":325,"monthValue":11,"hour":13,"minute":42,"nano":0,"second":8,"chronology":{"id":"ISO","calendarType":"iso8601"}},"downPosition":null}
     */

    private int code;
    private String msg;
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
         * id : 1
         * topSign : {"month":"NOVEMBER","year":2017,"dayOfMonth":21,"dayOfWeek":"TUESDAY","dayOfYear":325,"monthValue":11,"hour":14,"minute":3,"nano":0,"second":21,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * downSign : null
         * workersId : 1
         * state : 异常
         * topPosition : 河北省秦皇岛市海港区龙海道77号
         * createTime : {"month":"NOVEMBER","year":2017,"dayOfMonth":21,"dayOfWeek":"TUESDAY","dayOfYear":325,"monthValue":11,"hour":13,"minute":42,"nano":0,"second":8,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * downPosition : null
         */

        private int id;
        private DateBean topSign;
        private DateBean downSign;
        private int workersId;
        private String state;
        private String topPosition;
        private DateBean createTime;
        private String downPosition;

        public DateBean getTopSign() {
            return topSign;
        }

        public DateBean getDownSign() {
            return downSign;
        }

        public String getTopPosition() {
            return topPosition;
        }

        public Object getDownPosition() {
            return downPosition;
        }

    }
}
