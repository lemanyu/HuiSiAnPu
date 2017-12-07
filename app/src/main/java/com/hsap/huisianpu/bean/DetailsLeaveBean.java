package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/12/6.
 */

public class DetailsLeaveBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"id":2,"workersId":2,"leaveType":"事假","startTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":0.5,"createTime":{"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":14,"minute":47,"nano":0,"second":23,"chronology":{"id":"ISO","calendarType":"iso8601"}},"editTiem":null,"state":null,"reason":"66"}
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
         * id : 2
         * workersId : 2
         * leaveType : 事假
         * startTime : {"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * endTime : {"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * totalTime : 0.5
         * createTime : {"dayOfMonth":6,"dayOfWeek":"WEDNESDAY","month":"DECEMBER","year":2017,"dayOfYear":340,"monthValue":12,"hour":14,"minute":47,"nano":0,"second":23,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * editTiem : null
         * state : null
         * reason : 66
         */

        private int id;
        private int workersId;
        private String leaveType;
        private StartTimeBean startTime;
        private EndTimeBean endTime;
        private double totalTime;
        private CreateTimeBean createTime;
        private Object editTiem;
        private Object state;
        private String reason;

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

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
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

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public Object getEditTiem() {
            return editTiem;
        }

        public void setEditTiem(Object editTiem) {
            this.editTiem = editTiem;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public static class StartTimeBean {
            /**
             * dayOfMonth : 6
             * dayOfWeek : WEDNESDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 340
             * monthValue : 12
             * hour : 0
             * minute : 0
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

        public static class EndTimeBean {
            /**
             * dayOfMonth : 6
             * dayOfWeek : WEDNESDAY
             * month : DECEMBER
             * year : 2017
             * dayOfYear : 340
             * monthValue : 12
             * hour : 0
             * minute : 0
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
             * hour : 14
             * minute : 47
             * nano : 0
             * second : 23
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
