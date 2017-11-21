package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/21.
 */

public class InviteBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"phone":"0571-88157808","managerId":1,"state":0,"createTime":{"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}},"departmentId":1},{"phone":"057156215888","managerId":1,"state":0,"createTime":{"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}},"departmentId":1},{"phone":"1065752551629604916","managerId":1,"state":0,"createTime":{"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}},"departmentId":1},{"phone":"106575258192144","managerId":1,"state":0,"createTime":{"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}},"departmentId":1},{"phone":"106590256203144","managerId":1,"state":0,"createTime":{"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}},"departmentId":1}]
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
         * phone : 0571-88157808
         * managerId : 1
         * state : 0
         * createTime : {"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"month":"NOVEMBER","year":2017,"monthValue":11,"hour":14,"minute":11,"nano":0,"second":35,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * departmentId : 1
         */

        private String phone;
        private int managerId;
        private int state;
        private CreateTimeBean createTime;
        private int departmentId;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getManagerId() {
            return managerId;
        }

        public void setManagerId(int managerId) {
            this.managerId = managerId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public static class CreateTimeBean {
            /**
             * dayOfMonth : 20
             * dayOfWeek : MONDAY
             * dayOfYear : 324
             * month : NOVEMBER
             * year : 2017
             * monthValue : 11
             * hour : 14
             * minute : 11
             * nano : 0
             * second : 35
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
            private String month;
            private int year;
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

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
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
