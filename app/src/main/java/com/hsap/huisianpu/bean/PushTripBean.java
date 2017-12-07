package com.hsap.huisianpu.bean;

import java.util.ArrayList;

/**
 * Created by zhao on 2017/12/5.
 */

public class PushTripBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"nameList":[],"waIntegration":{"id":19,"startTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":1,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":1,"state":0,"reason":null,"type":0,"type2":"事假","createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":14,"minute":59,"nano":0,"second":39,"chronology":{"id":"ISO","calendarType":"iso8601"}}}}
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
         * nameList : []
         * waIntegration : {"id":19,"startTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":1,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":1,"state":0,"reason":null,"type":0,"type2":"事假","createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":14,"minute":59,"nano":0,"second":39,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
         */

        private WaIntegrationBean waIntegration;
        private ArrayList<String> nameList;

        public WaIntegrationBean getWaIntegration() {
            return waIntegration;
        }

        public void setWaIntegration(WaIntegrationBean waIntegration) {
            this.waIntegration = waIntegration;
        }

        public ArrayList<String> getNameList() {
            return nameList;
        }

        public void setNameList(ArrayList<String> nameList) {
            this.nameList = nameList;
        }

        public static class WaIntegrationBean {
            /**
             * id : 19
             * startTime : {"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * endTime : {"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":1,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * totalTime : 1.0
             * state : 0
             * reason : null
             * type : 0
             * type2 : 事假
             * createTime : {"month":"DECEMBER","year":2017,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"monthValue":12,"hour":14,"minute":59,"nano":0,"second":39,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             */

            private int id;
            private StartTimeBean startTime;
            private EndTimeBean endTime;
            private double totalTime;
            private int state;
            private String reason;
            private int type;
            private String type2;
            private CreateTimeBean createTime;

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

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public static class StartTimeBean {
                /**
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 7
                 * dayOfWeek : THURSDAY
                 * dayOfYear : 341
                 * monthValue : 12
                 * hour : 8
                 * minute : 30
                 * nano : 0
                 * second : 0
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
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

            public static class EndTimeBean {
                /**
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 7
                 * dayOfWeek : THURSDAY
                 * dayOfYear : 341
                 * monthValue : 12
                 * hour : 1
                 * minute : 0
                 * nano : 0
                 * second : 0
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanX chronology;

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

            public static class CreateTimeBean {
                /**
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 7
                 * dayOfWeek : THURSDAY
                 * dayOfYear : 341
                 * monthValue : 12
                 * hour : 14
                 * minute : 59
                 * nano : 0
                 * second : 39
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int monthValue;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanXX chronology;

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
        }
    }
}
