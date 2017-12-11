package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/30.
 */

public class OneReportFromBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"id":168,"workersId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":30,"dayOfWeek":"THURSDAY","dayOfYear":334,"monthValue":11,"hour":11,"minute":19,"nano":0,"second":55,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":1,"finishWork":"rr","unWork":null,"coordinateWork":null,"remarks":null,"summary":"rr","workPlay":"rr"}
     */

    private int code;
    private Object msg;
    private boolean success;
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        /**
         * id : 168
         * workersId : 1
         * createTime : {"month":"NOVEMBER","year":2017,"dayOfMonth":30,"dayOfWeek":"THURSDAY","dayOfYear":334,"monthValue":11,"hour":11,"minute":19,"nano":0,"second":55,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * type : 1
         * finishWork : rr
         * unWork : null
         * coordinateWork : null
         * remarks : null
         * summary : rr
         * workPlay : rr
         */

        private int id;
        private int workersId;
        private int type;
        private String finishWork;
        private Object unWork;
        private Object coordinateWork;
        private Object remarks;
        private String summary;
        private String workPlay;

        public String getFinishWork() {
            return finishWork;
        }

        public String getSummary() {
            return summary;
        }

        public String getWorkPlay() {
            return workPlay;
        }

    }
}
