package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * 查看汇报的日周月
 */

public class WorkNameBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"list":[{"reportForm":{"id":188,"workersId":2,"fileSize":3,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":54,"nano":0,"second":43,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":187,"workersId":2,"fileSize":3,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":53,"nano":0,"second":39,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":186,"workersId":2,"fileSize":3,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":51,"nano":0,"second":29,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":185,"workersId":2,"fileSize":3,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":49,"nano":0,"second":52,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":184,"workersId":2,"fileSize":1,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":49,"nano":0,"second":24,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":183,"workersId":2,"fileSize":2,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":43,"nano":0,"second":27,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"看看","unWork":"看看","coordinateWork":"图","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":182,"workersId":2,"fileSize":0,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":35,"nano":0,"second":37,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"'图","unWork":"看看","coordinateWork":"推荐","remarks":null,"summary":null,"workPlay":null},"name":"王二"},{"reportForm":{"id":181,"workersId":2,"fileSize":0,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":33,"nano":0,"second":25,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"哈哈","unWork":"起","coordinateWork":"里","remarks":null,"summary":null,"workPlay":null},"name":"王二"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * reportForm : {"id":188,"workersId":2,"fileSize":3,"createTime":{"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":54,"nano":0,"second":43,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"吧","unWork":"把","coordinateWork":"的","remarks":null,"summary":null,"workPlay":null}
             * name : 王二
             */

            private ReportFormBean reportForm;
            private String name;

            public ReportFormBean getReportForm() {
                return reportForm;
            }

            public void setReportForm(ReportFormBean reportForm) {
                this.reportForm = reportForm;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public static class ReportFormBean {
                /**
                 * id : 188
                 * workersId : 2
                 * fileSize : 3
                 * createTime : {"dayOfMonth":12,"dayOfWeek":"TUESDAY","month":"DECEMBER","year":2017,"dayOfYear":346,"monthValue":12,"hour":13,"minute":54,"nano":0,"second":43,"chronology":{"id":"ISO","calendarType":"iso8601"}}
                 * type : 0
                 * finishWork : 吧
                 * unWork : 把
                 * coordinateWork : 的
                 * remarks : null
                 * summary : null
                 * workPlay : null
                 */

                private int id;
                private int workersId;
                private int fileSize;
                private CreateTimeBean createTime;
                private int type;
                private String finishWork;
                private String unWork;
                private String coordinateWork;
                private Object remarks;
                private String summary;
                private Object workPlay;

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

                public int getFileSize() {
                    return fileSize;
                }

                public void setFileSize(int fileSize) {
                    this.fileSize = fileSize;
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

                public String getUnWork() {
                    return unWork;
                }

                public void setUnWork(String unWork) {
                    this.unWork = unWork;
                }

                public String getCoordinateWork() {
                    return coordinateWork;
                }

                public void setCoordinateWork(String coordinateWork) {
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

                public Object getWorkPlay() {
                    return workPlay;
                }

                public void setWorkPlay(Object workPlay) {
                    this.workPlay = workPlay;
                }

                public static class CreateTimeBean {
                    /**
                     * dayOfMonth : 12
                     * dayOfWeek : TUESDAY
                     * month : DECEMBER
                     * year : 2017
                     * dayOfYear : 346
                     * monthValue : 12
                     * hour : 13
                     * minute : 54
                     * nano : 0
                     * second : 43
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
            }
        }
    }
}
