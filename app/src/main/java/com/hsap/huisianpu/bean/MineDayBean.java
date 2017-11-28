package com.hsap.huisianpu.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

public class MineDayBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"pageNum":1,"pageSize":1,"size":1,"startRow":1,"endRow":1,"total":61,"pages":61,"list":[{"id":62,"workersId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":28,"dayOfWeek":"TUESDAY","dayOfYear":332,"hour":10,"minute":36,"nano":0,"second":11,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"11","unWork":"1","coordinateWork":"1","remarks":"1","summary":"1","workPlay":"1"}],"prePage":0,"nextPage":2,"isFirstPage":true,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"navigateFirstPage":1,"navigateLastPage":8,"firstPage":1,"lastPage":8}
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
         * pageNum : 1
         * pageSize : 1
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 61
         * pages : 61
         * list : [{"id":62,"workersId":1,"createTime":{"month":"NOVEMBER","year":2017,"dayOfMonth":28,"dayOfWeek":"TUESDAY","dayOfYear":332,"hour":10,"minute":36,"nano":0,"second":11,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"11","unWork":"1","coordinateWork":"1","remarks":"1","summary":"1","workPlay":"1"}]
         * prePage : 0
         * nextPage : 2
         * isFirstPage : true
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2,3,4,5,6,7,8]
         * navigateFirstPage : 1
         * navigateLastPage : 8
         * firstPage : 1
         * lastPage : 8
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * id : 62
             * workersId : 1
             * createTime : {"month":"NOVEMBER","year":2017,"dayOfMonth":28,"dayOfWeek":"TUESDAY","dayOfYear":332,"hour":10,"minute":36,"nano":0,"second":11,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * type : 0
             * finishWork : 11
             * unWork : 1
             * coordinateWork : 1
             * remarks : 1
             * summary : 1
             * workPlay : 1
             */

            private int id;
            private int workersId;
            private CreateTimeBean createTime;
            private int type;
            private String finishWork;
            private String unWork;
            private String coordinateWork;
            private String remarks;
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

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
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
                 * dayOfMonth : 28
                 * dayOfWeek : TUESDAY
                 * dayOfYear : 332
                 * hour : 10
                 * minute : 36
                 * nano : 0
                 * second : 11
                 * monthValue : 11
                 * chronology : {"id":"ISO","calendarType":"iso8601"}
                 */

                private String month;
                private int year;
                private int dayOfMonth;
                private String dayOfWeek;
                private int dayOfYear;
                private int hour;
                private int minute;
                private int nano;
                private int second;
                private int monthValue;
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
        }
    }
}
