package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * 工作审批bean
 */

public class ApprovalBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"projectId":54,"type":2,"managerId":null,"opinion":0,"date":null,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","dayOfYear":340,"hour":11,"minute":57,"nano":0,"second":35,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"name":"王五","typeName":"出差"},{"projectId":2,"type":0,"managerId":null,"opinion":0,"date":null,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","dayOfYear":340,"hour":11,"minute":39,"nano":0,"second":30,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"name":"王二","typeName":"请假"},{"projectId":3,"type":0,"managerId":null,"opinion":0,"date":null,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","dayOfYear":340,"hour":11,"minute":39,"nano":0,"second":30,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"name":"王二","typeName":"请假"}]
     */

    private int code;
    private Object msg;
    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * projectId : 54
         * type : 2
         * managerId : null
         * opinion : 0
         * date : null
         * createTime : {"month":"DECEMBER","year":2017,"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","dayOfYear":340,"hour":11,"minute":57,"nano":0,"second":35,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * name : 王五
         * typeName : 出差
         */

        private int projectId;
        private int type;
        private Object managerId;

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        private int opinion;
        private Object date;
        private DateBean createTime;
        private String name;
        private String typeName;

        public int getProjectId() {
            return projectId;
        }

        public int getType() {
            return type;
        }

        public DateBean getCreateTime() {
            return createTime;
        }

        public String getName() {
            return name;
        }

        public String getTypeName() {
            return typeName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "projectId=" + projectId +
                    ", type=" + type +
                    ", managerId=" + managerId +
                    ", opinion=" + opinion +
                    ", date=" + date +
                    ", createTime=" + createTime +
                    ", name='" + name + '\'' +
                    ", typeName='" + typeName + '\'' +
                    '}';
        }
    }
}
