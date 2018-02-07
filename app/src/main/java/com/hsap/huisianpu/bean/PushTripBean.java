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
    private String msg;
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

            /**
             * guige0 : 看看
             * shuliang0 : 55.0
             * danjia0 : 55.0
             * yongtu0 : 看看
             * jine0 : 0.0
             * name0 : 看看
             * piaoju0 : 看看
             */

            private String guige0;
            private String shuliang0;
            private String danjia0;
            private String yongtu0;
            private String jine0;
            private String name0;
            private String piaoju0;
            /**
             * didian0 : 吧
             * shixiang0 : 把
             * leixing0 : 商务用车
             */

            private String didian0;
            private String shixiang0;
            private String leixing0;


            public String getGuige0() {
                return guige0;
            }

            public void setGuige0(String guige0) {
                this.guige0 = guige0;
            }

            public String getShuliang0() {
                return shuliang0;
            }

            public void setShuliang0(String shuliang0) {
                this.shuliang0 = shuliang0;
            }

            public String getDanjia0() {
                return danjia0;
            }

            public void setDanjia0(String danjia0) {
                this.danjia0 = danjia0;
            }

            public String getYongtu0() {
                return yongtu0;
            }

            public void setYongtu0(String yongtu0) {
                this.yongtu0 = yongtu0;
            }

            public String getJine0() {
                return jine0;
            }

            public void setJine0(String jine0) {
                this.jine0 = jine0;
            }

            public String getName0() {
                return name0;
            }

            public void setName0(String name0) {
                this.name0 = name0;
            }

            public String getPiaoju0() {
                return piaoju0;
            }

            public void setPiaoju0(String piaoju0) {
                this.piaoju0 = piaoju0;
            }

            public String getDidian0() {
                return didian0;
            }

            public void setDidian0(String didian0) {
                this.didian0 = didian0;
            }

            public String getShixiang0() {
                return shixiang0;
            }

            public void setShixiang0(String shixiang0) {
                this.shixiang0 = shixiang0;
            }

            public String getLeixing0() {
                return leixing0;
            }

            public void setLeixing0(String leixing0) {
                this.leixing0 = leixing0;
            }
        }
    }
}
