package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/30.
 */

public class OneReportFromBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"id":168,"workersId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":30,"dayOfWeek":"THURSDAY","dayOfYear":334,"monthValue":11,"hour":11,"minute":19,"nano":0,"second":55,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":1,"finishWork":"rr","unWork":null,"coordinateWork":null,"remarks":null,"summary":"rr","workPlay":"rr"}
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
         * id : 168
         * workersId : 1
         * createTime : {"month":"NOVEMBER","year":2017,"dayOfMonth":30,"dayOfWeek":"THURSDAY","dayOfYear":334,"monthValue":11,"hour":11,"minute":19,"nano":0,"second":55,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * type : 1
         * finishWork : rr
         * unWork : null
         * coordinateWork : null
         * remarks : null
         * summary : rr
         * workPlay : rr
         */

        private int id;
        private int workersId;
        private CreateTimeBean createTime;
        private int type;
        private String finishWork;
        private Object unWork;
        private Object coordinateWork;
        private Object remarks;
        private String summary;
        private String workPlay;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFinishWork() {
            return finishWork;
        }

        public void setFinishWork(String finishWork) {
            this.finishWork = finishWork;
        }

        public Object getUnWork() {
            return unWork;
        }

        public void setUnWork(Object unWork) {
            this.unWork = unWork;
        }

        public Object getCoordinateWork() {
            return coordinateWork;
        }

        public void setCoordinateWork(Object coordinateWork) {
            this.coordinateWork = coordinateWork;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getWorkPlay() {
            return workPlay;
        }

        public void setWorkPlay(String workPlay) {
            this.workPlay = workPlay;
        }

        public static class CreateTimeBean {
            /**
             * month : NOVEMBER
             * year : 2017
             * dayOfMonth : 30
             * dayOfWeek : THURSDAY
             * dayOfYear : 334
             * monthValue : 11
             * hour : 11
             * minute : 19
             * nano : 0
             * second : 55
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
    }
}
