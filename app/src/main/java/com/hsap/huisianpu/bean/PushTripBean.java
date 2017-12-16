package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/5.
 */

public class PushTripBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"nameList":[],"waIntegration":{"id":40,"startTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":22,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":null,"type":4,"type2":"15127027086","createTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":59,"chronology":{"id":"ISO","calendarType":"iso8601"}}},"map":{"leixing":"商务用车","didian":"5.0","shixiang":"1.0"}}
     */

    private int code;
    private Object msg;
    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        /**
         * nameList : []
         * waIntegration : {"id":40,"startTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":22,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":null,"type":4,"type2":"15127027086","createTime":{"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":59,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
         * map : {"leixing":"商务用车","didian":"5.0","shixiang":"1.0"}
         */

        private WaIntegrationBean waIntegration;
        private MapBean map;
        private List<String> nameList;

        public List<Integer> getNameId() {
            return nameId;
        }

        public void setNameId(List<Integer> nameId) {
            this.nameId = nameId;
        }

        private List<Integer> nameId;

        public WaIntegrationBean getWaIntegration() {
            return waIntegration;
        }

        public MapBean getMap() {
            return map;
        }

        public List<String> getNameList() {
            return nameList;
        }

        public static class WaIntegrationBean {
            /**
             * id : 40
             * startTime : {"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * endTime : {"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":22,"minute":26,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * totalTime : null
             * state : 0
             * reason : null
             * type : 4
             * type2 : 15127027086
             * createTime : {"dayOfMonth":8,"dayOfWeek":"FRIDAY","month":"DECEMBER","year":2017,"dayOfYear":342,"monthValue":12,"hour":10,"minute":26,"nano":0,"second":59,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             */

            private int id;
            private StartTimeBean startTime;
            private EndTimeBean endTime;
            private Double totalTime;
            private int state;
            private String reason;
            private int type;
            private String type2;


            public StartTimeBean getStartTime() {
                return startTime;
            }

            public EndTimeBean getEndTime() {
                return endTime;
            }

            public Double getTotalTime() {
                return totalTime;
            }

            public int getState() {
                return state;
            }

            public String getReason() {
                return reason;
            }

            public String getType2() {
                return type2;
            }

            public static class StartTimeBean {
                /**
                 * dayOfMonth : 8
                 * dayOfWeek : FRIDAY
                 * month : DECEMBER
                 * year : 2017
                 * dayOfYear : 342
                 * monthValue : 12
                 * hour : 10
                 * minute : 26
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


                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public int getYear() {
                    return year;
                }

                public int getMonthValue() {
                    return monthValue;
                }

                public int getHour() {
                    return hour;
                }

                public int getMinute() {
                    return minute;
                }

            }

            public static class EndTimeBean {
                /**
                 * dayOfMonth : 8
                 * dayOfWeek : FRIDAY
                 * month : DECEMBER
                 * year : 2017
                 * dayOfYear : 342
                 * monthValue : 12
                 * hour : 22
                 * minute : 26
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


                public int getDayOfMonth() {
                    return dayOfMonth;
                }

                public int getYear() {
                    return year;
                }

                public int getMonthValue() {
                    return monthValue;
                }

                public int getHour() {
                    return hour;
                }

                public int getMinute() {
                    return minute;
                }

            }

        }

        public static class MapBean {
            /**
             * leixing : 商务用车
             * didian : 5.0
             * shixiang : 1.0
             */

            private String leixing;
            private String didian;
            private String shixiang;

            public String getLeixing() {
                return leixing;
            }

            public String getDidian() {
                return didian;
            }

            public String getShixiang() {
                return shixiang;
            }

        }
    }
}
