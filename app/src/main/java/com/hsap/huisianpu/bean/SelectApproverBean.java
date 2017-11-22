package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/22.
 */

public class SelectApproverBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":1,"name":"管理员","idCard":"1301241","phone":"111","birthDate":1293811200000,"age":"女","departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"monthValue":11,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"hour":10,"minute":58,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":1,"state":"在职","createDate":1511146697000,"createId":0,"email":"111@qq.com","token":null}]
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
         * id : 1
         * name : 管理员
         * idCard : 1301241
         * phone : 111
         * birthDate : 1293811200000
         * age : 女
         * departmentId : 1
         * createTime : {"month":"NOVEMBER","year":2017,"monthValue":11,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"hour":10,"minute":58,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * jurisdictionId : 1
         * state : 在职
         * createDate : 1511146697000
         * createId : 0
         * email : 111@qq.com
         * token : null
         */

        private int id;
        private String name;
        private String idCard;
        private String phone;
        private long birthDate;
        private String age;
        private int departmentId;
        private CreateTimeBean createTime;
        private int jurisdictionId;
        private String state;
        private long createDate;
        private int createId;
        private String email;
        private Object token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(long birthDate) {
            this.birthDate = birthDate;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getJurisdictionId() {
            return jurisdictionId;
        }

        public void setJurisdictionId(int jurisdictionId) {
            this.jurisdictionId = jurisdictionId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getCreateId() {
            return createId;
        }

        public void setCreateId(int createId) {
            this.createId = createId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public static class CreateTimeBean {
            /**
             * month : NOVEMBER
             * year : 2017
             * monthValue : 11
             * dayOfMonth : 20
             * dayOfWeek : MONDAY
             * dayOfYear : 324
             * hour : 10
             * minute : 58
             * nano : 0
             * second : 17
             * chronology : {"id":"ISO","calendarType":"iso8601"}
             */

            private String month;
            private int year;
            private int monthValue;
            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
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
