package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/7.
 */

public class WorkLeaveBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"type":0,"typeId":4,"workersId":2,"type2":0,"createTime":{"hour":9,"minute":3,"nano":0,"second":12,"month":"DECEMBER","year":2017,"monthValue":12,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"chronology":{"id":"ISO","calendarType":"iso8601"}}}]
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
         * type : 0
         * typeId : 4
         * workersId : 2
         * type2 : 0
         * createTime : {"hour":9,"minute":3,"nano":0,"second":12,"month":"DECEMBER","year":2017,"monthValue":12,"dayOfMonth":7,"dayOfWeek":"THURSDAY","dayOfYear":341,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         */

        private int type;
        private int typeId;
        private int workersId;
        private int type2;
        private CreateTimeBean createTime;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getWorkersId() {
            return workersId;
        }

        public void setWorkersId(int workersId) {
            this.workersId = workersId;
        }

        public int getType2() {
            return type2;
        }

        public void setType2(int type2) {
            this.type2 = type2;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public static class CreateTimeBean {
            /**
             * hour : 9
             * minute : 3
             * nano : 0
             * second : 12
             * month : DECEMBER
             * year : 2017
             * monthValue : 12
             * dayOfMonth : 7
             * dayOfWeek : THURSDAY
             * dayOfYear : 341
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private int hour;
            private int minute;
            private int nano;
            private int second;
            private String month;
            private int year;
            private int monthValue;
            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
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

            public int getMonthValue() {
                return monthValue;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
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
