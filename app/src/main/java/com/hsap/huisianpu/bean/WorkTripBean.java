package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/2.
 */

public class WorkTripBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"verifier":["王一","王二","王四"],"accompanying":["王一","王六"],"waTravelBusiness":{"id":6,"place":"看看","departureTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"returnTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":15,"minute":17,"nano":0,"second":41,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":1,"state":null,"total":0.5,"reason":"看看","comment":"同学"}}]
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
         * verifier : ["王一","王二","王四"]
         * accompanying : ["王一","王六"]
         * waTravelBusiness : {"id":6,"place":"看看","departureTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"returnTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":15,"minute":17,"nano":0,"second":41,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":1,"state":null,"total":0.5,"reason":"看看","comment":"同学"}
         */

        private WaTravelBusinessBean waTravelBusiness;
        private List<String> verifier;
        private List<String> accompanying;

        public WaTravelBusinessBean getWaTravelBusiness() {
            return waTravelBusiness;
        }

        public void setWaTravelBusiness(WaTravelBusinessBean waTravelBusiness) {
            this.waTravelBusiness = waTravelBusiness;
        }

        public List<String> getVerifier() {
            return verifier;
        }

        public void setVerifier(List<String> verifier) {
            this.verifier = verifier;
        }

        public List<String> getAccompanying() {
            return accompanying;
        }

        public void setAccompanying(List<String> accompanying) {
            this.accompanying = accompanying;
        }

        public static class WaTravelBusinessBean {
            /**
             * id : 6
             * place : 看看
             * departureTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * returnTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * createTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":2,"dayOfWeek":"SATURDAY","dayOfYear":336,"hour":15,"minute":17,"nano":0,"second":41,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * workersId : 1
             * state : null
             * total : 0.5
             * reason : 看看
             * comment : 同学
             */

            private int id;
            private String place;
            private DepartureTimeBean departureTime;
            private ReturnTimeBean returnTime;
            private CreateTimeBean createTime;
            private int workersId;
            private Object state;
            private double total;
            private String reason;
            private String comment;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPlace() {
                return place;
            }

            public void setPlace(String place) {
                this.place = place;
            }

            public DepartureTimeBean getDepartureTime() {
                return departureTime;
            }

            public void setDepartureTime(DepartureTimeBean departureTime) {
                this.departureTime = departureTime;
            }

            public ReturnTimeBean getReturnTime() {
                return returnTime;
            }

            public void setReturnTime(ReturnTimeBean returnTime) {
                this.returnTime = returnTime;
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

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public double getTotal() {
                return total;
            }

            public void setTotal(double total) {
                this.total = total;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public static class DepartureTimeBean {
                /**
                 * monthValue : 12
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 2
                 * dayOfWeek : SATURDAY
                 * dayOfYear : 336
                 * hour : 8
                 * minute : 30
                 * nano : 0
                 * second : 0
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private int monthValue;
                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBean chronology;

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
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

            public static class ReturnTimeBean {
                /**
                 * monthValue : 12
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 2
                 * dayOfWeek : SATURDAY
                 * dayOfYear : 336
                 * hour : 8
                 * minute : 30
                 * nano : 0
                 * second : 0
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private int monthValue;
                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanX chronology;

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
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
                 * monthValue : 12
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 2
                 * dayOfWeek : SATURDAY
                 * dayOfYear : 336
                 * hour : 15
                 * minute : 17
                 * nano : 0
                 * second : 41
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private int monthValue;
                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private ChronologyBeanXX chronology;

                public int getMonthValue() {
                    return monthValue;
                }

                public void setMonthValue(int monthValue) {
                    this.monthValue = monthValue;
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
