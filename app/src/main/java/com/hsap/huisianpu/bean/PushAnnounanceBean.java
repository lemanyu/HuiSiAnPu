package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/27.
 */

public class PushAnnounanceBean {

    /**
     * urlList : ["http://192.168.9.216:8082/phone/images/20171227100.png?type=7&filename20171227100.png"]
     * notice : {"id":10,"level":0,"noticeDepartment":1,"noticeType":null,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":27,"dayOfWeek":"WEDNESDAY","dayOfYear":361,"hour":16,"minute":5,"nano":0,"second":22,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":2,"state":0,"noticeBody":""}
     */

    private NoticeBean notice;
    private List<String> urlList;

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public static class NoticeBean {
        /**
         * id : 10
         * level : 0
         * noticeDepartment : 1
         * noticeType : null
         * createTime : {"month":"DECEMBER","year":2017,"dayOfMonth":27,"dayOfWeek":"WEDNESDAY","dayOfYear":361,"hour":16,"minute":5,"nano":0,"second":22,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * workersId : 2
         * state : 0
         * noticeBody :
         */

        private int id;
        private int level;
        private int noticeDepartment;
        private Object noticeType;
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

        public Object getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(Object noticeType) {
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
             * month : DECEMBER
             * year : 2017
             * dayOfMonth : 27
             * dayOfWeek : WEDNESDAY
             * dayOfYear : 361
             * hour : 16
             * minute : 5
             * nano : 0
             * second : 22
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
