package com.hsap.huisianpu.bean;

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

    public DataBean getData() {
        return data;
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

        public List<ListBean> getList() {
            return list;
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

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public String getFinishWork() {
                return finishWork;
            }

            public String getCoordinateWork() {
                return coordinateWork;
            }

            public String getSummary() {
                return summary;
            }

            public String getWorkPlay() {
                return workPlay;
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


                public int getYear() {
                    return year;
                }

                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public int getMonthValue() {
                    return monthValue;
                }

            }
        }
    }
}
