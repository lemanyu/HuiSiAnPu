package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/26.
 */

public class SummayBean {

    /**
     * summary : {"type":"7","cname":"7","cmanager":"8","body1":"1","body2":"2","body3":"3","body4":"4","id":8,"travelBsinessId":170,"workersId":2,"createTime":{"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":16,"minute":47,"nano":0,"second":48,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
     * waIntegration : {"id":170,"startTime":{"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"dayOfMonth":29,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":363,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":3.5,"state":1,"reason":"饿","type":2,"type2":"啊啊啊","createTime":{"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":14,"minute":28,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
     * nameList : [{"name":"王二"},{"name":"王三"},{"name":"王四"}]
     */

    private SummaryBean summary;
    private WaIntegrationBean waIntegration;
    private List<NameListBean> nameList;

    public SummaryBean getSummary() {
        return summary;
    }

    public void setSummary(SummaryBean summary) {
        this.summary = summary;
    }

    public WaIntegrationBean getWaIntegration() {
        return waIntegration;
    }

    public void setWaIntegration(WaIntegrationBean waIntegration) {
        this.waIntegration = waIntegration;
    }

    public List<NameListBean> getNameList() {
        return nameList;
    }

    public void setNameList(List<NameListBean> nameList) {
        this.nameList = nameList;
    }

    public static class SummaryBean {
        /**
         * type : 7
         * cname : 7
         * cmanager : 8
         * body1 : 1
         * body2 : 2
         * body3 : 3
         * body4 : 4
         * id : 8
         * travelBsinessId : 170
         * workersId : 2
         * createTime : {"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":16,"minute":47,"nano":0,"second":48,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         */

        private String type;
        private String cname;
        private String cmanager;
        private String body1;
        private String body2;
        private String body3;
        private String body4;
        private int id;
        private int travelBsinessId;
        private int workersId;
        private CreateTimeBean createTime;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getCmanager() {
            return cmanager;
        }

        public void setCmanager(String cmanager) {
            this.cmanager = cmanager;
        }

        public String getBody1() {
            return body1;
        }

        public void setBody1(String body1) {
            this.body1 = body1;
        }

        public String getBody2() {
            return body2;
        }

        public void setBody2(String body2) {
            this.body2 = body2;
        }

        public String getBody3() {
            return body3;
        }

        public void setBody3(String body3) {
            this.body3 = body3;
        }

        public String getBody4() {
            return body4;
        }

        public void setBody4(String body4) {
            this.body4 = body4;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTravelBsinessId() {
            return travelBsinessId;
        }

        public void setTravelBsinessId(int travelBsinessId) {
            this.travelBsinessId = travelBsinessId;
        }

        public int getWorkersId() {
            return workersId;
        }

        public void setWorkersId(int workersId) {
            this.workersId = workersId;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public static class CreateTimeBean {
            /**
             * dayOfMonth : 26
             * dayOfWeek : TUESDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 360
             * monthValue : 12
             * hour : 16
             * minute : 47
             * nano : 0
             * second : 48
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

    public static class WaIntegrationBean {
        /**
         * id : 170
         * startTime : {"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * endTime : {"dayOfMonth":29,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":363,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * totalTime : 3.5
         * state : 1
         * reason : 饿
         * type : 2
         * type2 : 啊啊啊
         * createTime : {"dayOfMonth":26,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":360,"monthValue":12,"hour":14,"minute":28,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         */

        private int id;
        private StartTimeBean startTime;
        private EndTimeBean endTime;
        private double totalTime;
        private int state;
        private String reason;
        private int type;
        private String type2;
        private CreateTimeBeanX createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public StartTimeBean getStartTime() {
            return startTime;
        }

        public void setStartTime(StartTimeBean startTime) {
            this.startTime = startTime;
        }

        public EndTimeBean getEndTime() {
            return endTime;
        }

        public void setEndTime(EndTimeBean endTime) {
            this.endTime = endTime;
        }

        public double getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(double totalTime) {
            this.totalTime = totalTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public CreateTimeBeanX getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBeanX createTime) {
            this.createTime = createTime;
        }

        public static class StartTimeBean {
            /**
             * dayOfMonth : 26
             * dayOfWeek : TUESDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 360
             * monthValue : 12
             * hour : 8
             * minute : 30
             * nano : 0
             * second : 0
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
            private ChronologyBeanX chronology;

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

            public ChronologyBeanX getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBeanX chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBeanX {
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

        public static class EndTimeBean {
            /**
             * dayOfMonth : 29
             * dayOfWeek : FRIDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 363
             * monthValue : 12
             * hour : 8
             * minute : 30
             * nano : 0
             * second : 0
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
            private ChronologyBeanXX chronology;

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

            public ChronologyBeanXX getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBeanXX chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBeanXX {
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

        public static class CreateTimeBeanX {
            /**
             * dayOfMonth : 26
             * dayOfWeek : TUESDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 360
             * monthValue : 12
             * hour : 14
             * minute : 28
             * nano : 0
             * second : 35
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
            private ChronologyBeanXXX chronology;

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

            public ChronologyBeanXXX getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBeanXXX chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBeanXXX {
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

    public static class NameListBean {
        /**
         * name : 王二
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
