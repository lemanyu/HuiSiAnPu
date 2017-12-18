package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/18.
 */

public class PurchaseBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"createName":"王二","nameList":[],"waIntegration":{"id":81,"startTime":null,"endTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":"看看","type":12,"type2":"办公用品","createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}},"object":{"list":[{"shuliang":"55","piaoju":"看看","name":"看看","yongtu":"看看","guige":"看看","danjia":"55.0"}]},"nameId":[]}
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
         * createName : 王二
         * nameList : []
         * waIntegration : {"id":81,"startTime":null,"endTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":"看看","type":12,"type2":"办公用品","createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
         * object : {"list":[{"shuliang":"55","piaoju":"看看","name":"看看","yongtu":"看看","guige":"看看","danjia":"55.0"}]}
         * nameId : []
         */

        private String createName;
        private WaIntegrationBean waIntegration;
        private ObjectBean object;
        private List<?> nameList;
        private List<?> nameId;

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

        public List<?> getNameList() {
            return nameList;
        }

        public void setNameList(List<?> nameList) {
            this.nameList = nameList;
        }

        public List<?> getNameId() {
            return nameId;
        }

        public void setNameId(List<?> nameId) {
            this.nameId = nameId;
        }

        public static class WaIntegrationBean {
            /**
             * id : 81
             * startTime : null
             * endTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * totalTime : null
             * state : 0
             * reason : 看看
             * type : 12
             * type2 : 办公用品
             * createTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             */

            private int id;
            private Object startTime;
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

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
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

            public static class EndTimeBean {
                /**
                 * monthValue : 12
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 18
                 * dayOfWeek : MONDAY
                 * dayOfYear : 352
                 * hour : 17
                 * minute : 0
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

            public static class CreateTimeBean {
                /**
                 * monthValue : 12
                 * month : DECEMBER
                 * year : 2017
                 * dayOfMonth : 18
                 * dayOfWeek : MONDAY
                 * dayOfYear : 352
                 * hour : 14
                 * minute : 56
                 * nano : 0
                 * second : 11
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
        }

        public static class ObjectBean {
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * shuliang : 55
                 * piaoju : 看看
                 * name : 看看
                 * yongtu : 看看
                 * guige : 看看
                 * danjia : 55.0
                 */

                private String shuliang;
                private String piaoju;
                private String name;
                private String yongtu;
                private String guige;
                private String danjia;

                public String getShuliang() {
                    return shuliang;
                }

                public void setShuliang(String shuliang) {
                    this.shuliang = shuliang;
                }

                public String getPiaoju() {
                    return piaoju;
                }

                public void setPiaoju(String piaoju) {
                    this.piaoju = piaoju;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getYongtu() {
                    return yongtu;
                }

                public void setYongtu(String yongtu) {
                    this.yongtu = yongtu;
                }

                public String getGuige() {
                    return guige;
                }

                public void setGuige(String guige) {
                    this.guige = guige;
                }

                public String getDanjia() {
                    return danjia;
                }

                public void setDanjia(String danjia) {
                    this.danjia = danjia;
                }
            }
        }
    }
}
