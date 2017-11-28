package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/28.
 */

public class WorkNameBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":1,"name":"管理员","idCard":"1301241","phone":"123","birthDate":{"month":"JULY","year":2004,"dayOfMonth":9,"dayOfWeek":"FRIDAY","dayOfYear":191,"monthValue":7,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"age":"女","departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"monthValue":11,"hour":10,"minute":58,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":1,"state":"在职","createDate":{"month":"OCTOBER","year":2017,"dayOfMonth":30,"dayOfWeek":"MONDAY","dayOfYear":303,"monthValue":10,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":"111@qq.com","token":"899aeb2e128eedbe413a9518fd394c72e53429ac"},{"id":2,"name":"经理","idCard":"111","phone":"11","birthDate":{"month":"JANUARY","year":2011,"dayOfMonth":1,"dayOfWeek":"SATURDAY","dayOfYear":1,"monthValue":1,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"age":"男","departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":8,"nano":0,"second":36,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":2,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":8,"nano":0,"second":36,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":3,"name":"总监","idCard":"33","phone":"33","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":3,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":4,"name":"总经理","idCard":"4","phone":"4","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":41,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":4,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":41,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":5,"name":"员工","idCard":"5","phone":"5","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":47,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":5,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":47,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":6,"name":"实习员工","idCard":"6","phone":"101","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":53,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":6,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"hour":13,"minute":9,"nano":0,"second":53,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null}]
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
         * phone : 123
         * birthDate : {"month":"JULY","year":2004,"dayOfMonth":9,"dayOfWeek":"FRIDAY","dayOfYear":191,"monthValue":7,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * age : 女
         * departmentId : 1
         * createTime : {"month":"NOVEMBER","year":2017,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"monthValue":11,"hour":10,"minute":58,"nano":0,"second":17,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * jurisdictionId : 1
         * state : 在职
         * createDate : {"month":"OCTOBER","year":2017,"dayOfMonth":30,"dayOfWeek":"MONDAY","dayOfYear":303,"monthValue":10,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * createId : 0
         * email : 111@qq.com
         * token : 899aeb2e128eedbe413a9518fd394c72e53429ac
         */

        private int id;
        private String name;
        private String idCard;
        private String phone;
        private BirthDateBean birthDate;
        private String age;
        private int departmentId;
        private CreateTimeBean createTime;
        private int jurisdictionId;
        private String state;
        private CreateDateBean createDate;
        private int createId;
        private String email;
        private String token;

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

        public BirthDateBean getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(BirthDateBean birthDate) {
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

        public CreateDateBean getCreateDate() {
            return createDate;
        }

        public void setCreateDate(CreateDateBean createDate) {
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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class BirthDateBean {
            /**
             * month : JULY
             * year : 2004
             * dayOfMonth : 9
             * dayOfWeek : FRIDAY
             * dayOfYear : 191
             * monthValue : 7
             * hour : 0
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

        public static class CreateTimeBean {
            /**
             * month : NOVEMBER
             * year : 2017
             * dayOfMonth : 20
             * dayOfWeek : MONDAY
             * dayOfYear : 324
             * monthValue : 11
             * hour : 10
             * minute : 58
             * nano : 0
             * second : 17
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

        public static class CreateDateBean {
            /**
             * month : OCTOBER
             * year : 2017
             * dayOfMonth : 30
             * dayOfWeek : MONDAY
             * dayOfYear : 303
             * monthValue : 10
             * hour : 0
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
