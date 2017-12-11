package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * 所有公告bean
 */

public class AllGongGaoBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":1,"level":1,"noticeDepartment":1,"noticeType":1,"createTime":{"dayOfMonth":9,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":2017,"dayOfYear":343,"monthValue":12,"hour":15,"minute":15,"nano":0,"second":42,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":1,"state":1,"noticeBody":"1"}]
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
         * id : 1
         * level : 1
         * noticeDepartment : 1
         * noticeType : 1
         * createTime : {"dayOfMonth":9,"dayOfWeek":"SATURDAY","month":"DECEMBER","year":2017,"dayOfYear":343,"monthValue":12,"hour":15,"minute":15,"nano":0,"second":42,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * workersId : 1
         * state : 1
         * noticeBody : 1
         */

        private int id;
        private int level;
        private int noticeDepartment;
        private int noticeType;
        private CreateTimeBean createTime;
        private int workersId;
        private int state;
        private String noticeBody;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getNoticeDepartment() {
            return noticeDepartment;
        }

        public void setNoticeDepartment(int noticeDepartment) {
            this.noticeDepartment = noticeDepartment;
        }

        public int getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(int noticeType) {
            this.noticeType = noticeType;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getWorkersId() {
            return workersId;
        }

        public void setWorkersId(int workersId) {
            this.workersId = workersId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getNoticeBody() {
            return noticeBody;
        }

        public void setNoticeBody(String noticeBody) {
            this.noticeBody = noticeBody;
        }

        public static class CreateTimeBean {
            /**
             * dayOfMonth : 9
             * dayOfWeek : SATURDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 343
             * monthValue : 12
             * hour : 15
             * minute : 15
             * nano : 0
             * second : 42
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private int dayOfMonth;
            private String dayOfWeek;
            private String month;
            private int year;
            private int dayOfYear;
            private int monthValue;
            private int hour;
            private int minute;
            private int nano;
            private int second;
            private ChronologyBean chronology;

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

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
            }

            public int getMonthValue() {
                return monthValue;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
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
