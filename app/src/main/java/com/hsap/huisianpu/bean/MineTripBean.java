package com.hsap.huisianpu.bean;

import java.util.List;

/**
 *
 * @author zhao
 */

public class MineTripBean {

    /**
     * code : 200
     * msg :
     * success : true
     * data : {"verifier":["王三"],"accompanying":["王二","王六"],"waTravelBusiness":{"id":53,"place":"gh","departureTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"returnTime":{"dayOfMonth":22,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":356,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":11,"minute":52,"nano":0,"second":26,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":5,"state":0,"total":16.5,"reason":"gg","comment":"bb"}}
     */

    private int code;
    private String msg;
    private boolean success;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
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
         * verifier : ["王三"]
         * accompanying : ["王二","王六"]
         * waTravelBusiness : {"id":53,"place":"gh","departureTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"returnTime":{"dayOfMonth":22,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":356,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":11,"minute":52,"nano":0,"second":26,"chronology":{"id":"ISO","calendarType":"iso8601"}},"workersId":5,"state":0,"total":16.5,"reason":"gg","comment":"bb"}
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
             * id : 53
             * place : gh
             * departureTime : {"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * returnTime : {"dayOfMonth":22,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":356,"monthValue":12,"hour":8,"minute":30,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * createTime : {"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":11,"minute":52,"nano":0,"second":26,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * workersId : 5
             * state : 0
             * total : 16.5
             * reason : gg
             * comment : bb
             */

            private int id;
            private String place;
            private DepartureTimeBean departureTime;
            private ReturnTimeBean returnTime;
            private CreateTimeBean createTime;
            private int workersId;
            private int state;
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

            public int getState() {
                return state;
            }

            public void setState(int state) {
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
                 * dayOfMonth : 6
                 * dayOfWeek : WEDNESDAY
                 * month : DECEMBER
                 * year : 2017
                 * dayOfYear : 340
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

            public static class ReturnTimeBean {
                /**
                 * dayOfMonth : 22
                 * dayOfWeek : FRIDAY
                 * month : DECEMBER
                 * year : 2017
                 * dayOfYear : 356
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

            public static class CreateTimeBean {
                /**
                 * dayOfMonth : 6
                 * dayOfWeek : WEDNESDAY
                 * month : DECEMBER
                 * year : 2017
                 * dayOfYear : 340
                 * monthValue : 12
                 * hour : 11
                 * minute : 52
                 * nano : 0
                 * second : 26
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
        }
    }
}
