package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2018/1/4.
 */

public class CarcarBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"createName":"王四","nameList":["ll","王二","王三","王五","王六"],"waIntegration":{"id":220,"startTime":{"hour":15,"minute":54,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"hour":15,"minute":55,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":3,"reason":"0","type":4,"type2":"15903360181","createTime":{"hour":16,"minute":3,"nano":0,"second":8,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}}},"object":{"fileUrl":"http://192.168.9.217:8082/phone/file/getAttendFile/201801042204.docx?integrationId=220","list":[{"leixing":"商务用车","didian":"11","shixiang":"11"}]},"nameId":[1,2,3,5,6]}
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
         * createName : 王四
         * nameList : ["ll","王二","王三","王五","王六"]
         * waIntegration : {"id":220,"startTime":{"hour":15,"minute":54,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"hour":15,"minute":55,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":3,"reason":"0","type":4,"type2":"15903360181","createTime":{"hour":16,"minute":3,"nano":0,"second":8,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
         * object : {"fileUrl":"http://192.168.9.217:8082/phone/file/getAttendFile/201801042204.docx?integrationId=220","list":[{"leixing":"商务用车","didian":"11","shixiang":"11"}]}
         * nameId : [1,2,3,5,6]
         */

        private String createName;
        private WaIntegrationBean waIntegration;
        private ObjectBean object;
        private List<String> nameList;
        private List<Integer> nameId;

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public WaIntegrationBean getWaIntegration() {
            return waIntegration;
        }

        public void setWaIntegration(WaIntegrationBean waIntegration) {
            this.waIntegration = waIntegration;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public List<String> getNameList() {
            return nameList;
        }

        public void setNameList(List<String> nameList) {
            this.nameList = nameList;
        }

        public List<Integer> getNameId() {
            return nameId;
        }

        public void setNameId(List<Integer> nameId) {
            this.nameId = nameId;
        }

        public static class WaIntegrationBean {
            /**
             * id : 220
             * startTime : {"hour":15,"minute":54,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * endTime : {"hour":15,"minute":55,"nano":0,"second":0,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * totalTime : null
             * state : 3
             * reason : 0
             * type : 4
             * type2 : 15903360181
             * createTime : {"hour":16,"minute":3,"nano":0,"second":8,"dayOfMonth":4,"dayOfWeek":"THURSDAY","month":"JANUARY","year":2018,"dayOfYear":4,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             */

            private int id;
            private StartTimeBean startTime;
            private EndTimeBean endTime;
            private Object totalTime;
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

            public Object getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(Object totalTime) {
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
                 * hour : 15
                 * minute : 54
                 * nano : 0
                 * second : 0
                 * dayOfMonth : 4
                 * dayOfWeek : THURSDAY
                 * month : JANUARY
                 * year : 2018
                 * dayOfYear : 4
                 * monthValue : 1
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

            public static class EndTimeBean {
                /**
                 * hour : 15
                 * minute : 55
                 * nano : 0
                 * second : 0
                 * dayOfMonth : 4
                 * dayOfWeek : THURSDAY
                 * month : JANUARY
                 * year : 2018
                 * dayOfYear : 4
                 * monthValue : 1
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
                private ChronologyBeanX chronology;

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
                 * hour : 16
                 * minute : 3
                 * nano : 0
                 * second : 8
                 * dayOfMonth : 4
                 * dayOfWeek : THURSDAY
                 * month : JANUARY
                 * year : 2018
                 * dayOfYear : 4
                 * monthValue : 1
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
                private ChronologyBeanXX chronology;

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

        public static class ObjectBean {
            /**
             * fileUrl : http://192.168.9.217:8082/phone/file/getAttendFile/201801042204.docx?integrationId=220
             * list : [{"leixing":"商务用车","didian":"11","shixiang":"11"}]
             */

            private String fileUrl;
            private List<ListBean> list;

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * leixing : 商务用车
                 * didian : 11
                 * shixiang : 11
                 */

                private String leixing;
                private String didian;
                private String shixiang;

                public String getLeixing() {
                    return leixing;
                }

                public void setLeixing(String leixing) {
                    this.leixing = leixing;
                }

                public String getDidian() {
                    return didian;
                }

                public void setDidian(String didian) {
                    this.didian = didian;
                }

                public String getShixiang() {
                    return shixiang;
                }

                public void setShixiang(String shixiang) {
                    this.shixiang = shixiang;
                }
            }
        }
    }
}
