package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/29.
 */

public class SeeProjectBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * max : 8
         * now : 1
         * name : 王四
         * startTime : {"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":29,"dayOfWeek":"WEDNESDAY","month":"NOVEMBER","year":2017,"dayOfYear":333,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * id : 26
         * endTime : null
         * projectName : 得
         */

        private int max;
        private int now;
        private String name;
        private StartTimeBean startTime;
        private int id;
        private Object endTime;
        private String projectName;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNow() {
            return now;
        }

        public void setNow(int now) {
            this.now = now;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public StartTimeBean getStartTime() {
            return startTime;
        }

        public void setStartTime(StartTimeBean startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public static class StartTimeBean {
            /**
             * hour : 0
             * minute : 0
             * nano : 0
             * second : 0
             * dayOfMonth : 29
             * dayOfWeek : WEDNESDAY
             * month : NOVEMBER
             * year : 2017
             * dayOfYear : 333
             * monthValue : 11
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private int hour;
            private int minute;
            private int nano;
            private int second;
            private int dayOfMonth;
            private String dayOfWeek;
            private String month;
            private int year;
            private int dayOfYear;
            private int monthValue;
            private ChronologyBean chronology;

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
