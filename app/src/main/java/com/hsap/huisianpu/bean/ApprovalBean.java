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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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
        private int opinion;
        private Object date;
        private CreateTimeBean createTime;
        private String name;
        private String typeName;

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getManagerId() {
            return managerId;
        }

        public void setManagerId(Object managerId) {
            this.managerId = managerId;
        }

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public static class CreateTimeBean {
            /**
             * month : DECEMBER
             * year : 2017
             * dayOfMonth : 6
             * dayOfWeek : WEDNESDAY
             * dayOfYear : 340
             * hour : 11
             * minute : 57
             * nano : 0
             * second : 35
             * monthValue : 12
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private String month;
            private int year;
            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
            private int hour;
            private int minute;
            private int nano;
            private int second;
            private int monthValue;
            private ChronologyBean chronology;

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public int getDayOfMonth() {
                return dayOfMonth;
            }

            public void setDayOfMonth(int dayOfMonth) {
                this.dayOfMonth = dayOfMonth;
            }

            public String getDayOfWeek() {
                return dayOfWeek;
            }

            public void setDayOfWeek(String dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
            }

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
            }

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

            public int getNano() {
                return nano;
            }

            public void setNano(int nano) {
                this.nano = nano;
            }

            public int getSecond() {
                return second;
            }

            public void setSecond(int second) {
                this.second = second;
            }

            public int getMonthValue() {
                return monthValue;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
            }

            public ChronologyBean getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBean chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBean {
                /**
                 * id : ISO
                 * calendarType : iso8601
                 */

                private String id;
                private String calendarType;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCalendarType() {
                    return calendarType;
                }

                public void setCalendarType(String calendarType) {
                    this.calendarType = calendarType;
                }
            }
        }
    }
}
