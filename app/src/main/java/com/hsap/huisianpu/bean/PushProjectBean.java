package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/28.
 */

public class PushProjectBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"personList":[{"name":"王二"},{"name":"王四"},{"name":"王五"}],"filePath":[{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0}],"info":{"id":27,"projectNumber":"66","projectName":"77","projectWorkers":1,"esStartTime":{"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"esEndTime":null,"esCycle":30,"projectState":0,"progreddBar":1,"createDate":{"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":11,"minute":38,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"maxBar":8,"body":"788","type":null}}
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
         * personList : [{"name":"王二"},{"name":"王四"},{"name":"王五"}]
         * filePath : [{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0},{"bar":0}]
         * info : {"id":27,"projectNumber":"66","projectName":"77","projectWorkers":1,"esStartTime":{"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"esEndTime":null,"esCycle":30,"projectState":0,"progreddBar":1,"createDate":{"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":11,"minute":38,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"maxBar":8,"body":"788","type":null}
         */
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private InfoBean info;
        private List<PersonListBean> personList;
        private List<FilePathBean> filePath;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<PersonListBean> getPersonList() {
            return personList;
        }

        public void setPersonList(List<PersonListBean> personList) {
            this.personList = personList;
        }

        public List<FilePathBean> getFilePath() {
            return filePath;
        }

        public void setFilePath(List<FilePathBean> filePath) {
            this.filePath = filePath;
        }

        public static class InfoBean {
            /**
             * id : 27
             * projectNumber : 66
             * projectName : 77
             * projectWorkers : 1
             * esStartTime : {"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":0,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * esEndTime : null
             * esCycle : 30.0
             * projectState : 0
             * progreddBar : 1
             * createDate : {"dayOfMonth":2,"dayOfWeek":"TUESDAY","month":"JANUARY","year":2018,"dayOfYear":2,"monthValue":1,"hour":11,"minute":38,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * maxBar : 8
             * body : 788
             * type : null
             */

            private int id;
            private String projectNumber;
            private String projectName;
            private String projectWorkers;
            private EsStartTimeBean esStartTime;
            private Object esEndTime;
            private double esCycle;
            private int projectState;
            private int progreddBar;
            private CreateDateBean createDate;
            private int maxBar;
            private String body;
            private Object type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProjectNumber() {
                return projectNumber;
            }

            public void setProjectNumber(String projectNumber) {
                this.projectNumber = projectNumber;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectWorkers() {
                return projectWorkers;
            }

            public void setProjectWorkers(String projectWorkers) {
                this.projectWorkers = projectWorkers;
            }

            public EsStartTimeBean getEsStartTime() {
                return esStartTime;
            }

            public void setEsStartTime(EsStartTimeBean esStartTime) {
                this.esStartTime = esStartTime;
            }

            public Object getEsEndTime() {
                return esEndTime;
            }

            public void setEsEndTime(Object esEndTime) {
                this.esEndTime = esEndTime;
            }

            public double getEsCycle() {
                return esCycle;
            }

            public void setEsCycle(double esCycle) {
                this.esCycle = esCycle;
            }

            public int getProjectState() {
                return projectState;
            }

            public void setProjectState(int projectState) {
                this.projectState = projectState;
            }

            public int getProgreddBar() {
                return progreddBar;
            }

            public void setProgreddBar(int progreddBar) {
                this.progreddBar = progreddBar;
            }

            public CreateDateBean getCreateDate() {
                return createDate;
            }

            public void setCreateDate(CreateDateBean createDate) {
                this.createDate = createDate;
            }

            public int getMaxBar() {
                return maxBar;
            }

            public void setMaxBar(int maxBar) {
                this.maxBar = maxBar;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public static class EsStartTimeBean {
                /**
                 * dayOfMonth : 2
                 * dayOfWeek : TUESDAY
                 * month : JANUARY
                 * year : 2018
                 * dayOfYear : 2
                 * monthValue : 1
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

            public static class CreateDateBean {
                /**
                 * dayOfMonth : 2
                 * dayOfWeek : TUESDAY
                 * month : JANUARY
                 * year : 2018
                 * dayOfYear : 2
                 * monthValue : 1
                 * hour : 11
                 * minute : 38
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
        }

        public static class PersonListBean {
            /**
             * name : 王二
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class FilePathBean {
            /**
             * bar : 0
             */

            private int bar;

            public int getBar() {
                return bar;
            }

            public void setBar(int bar) {
                this.bar = bar;
            }
        }
    }
}
