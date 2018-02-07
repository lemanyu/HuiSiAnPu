package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/18.
 */

public class PurchaseBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"createName":"王二","nameList":[],"waIntegration":{"id":81,"startTime":null,"endTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":"看看","type":12,"type2":"办公用品","createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}},"object":{"list":[{"shuliang":"55","piaoju":"看看","name":"看看","yongtu":"看看","guige":"看看","danjia":"55.0"}]},"nameId":[]}
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
         * createName : 王二
         * nameList : []
         * waIntegration : {"id":81,"startTime":null,"endTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":0,"reason":"看看","type":12,"type2":"办公用品","createTime":{"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
         * object : {"list":[{"shuliang":"55","piaoju":"看看","name":"看看","yongtu":"看看","guige":"看看","danjia":"55.0"}]}
         * nameId : []
         */

        private String createName;
        private WaIntegrationBean waIntegration;
        private ObjectBean object;
        private List<String> nameList;
        private List<Integer> nameId;

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public WaIntegrationBean getWaIntegration() {
            return waIntegration;
        }

        public void setWaIntegration(WaIntegrationBean waIntegration) {
            this.waIntegration = waIntegration;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public List<String> getNameList() {
            return nameList;
        }

        public void setNameList(List<String> nameList) {
            this.nameList = nameList;
        }

        public List<?> getNameId() {
            return nameId;
        }

        public void setNameId(List<Integer> nameId) {
            this.nameId = nameId;
        }

        public static class WaIntegrationBean {
            /**
             * id : 81
             * startTime : null
             * endTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":17,"minute":0,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * totalTime : null
             * state : 0
             * reason : 看看
             * type : 12
             * type2 : 办公用品
             * createTime : {"monthValue":12,"month":"DECEMBER","year":2017,"dayOfMonth":18,"dayOfWeek":"MONDAY","dayOfYear":352,"hour":14,"minute":56,"nano":0,"second":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             */

            private int id;
            private DateBean startTime;
            private DateBean endTime;
            private Float totalTime;
            private int state;
            private String reason;
            private int type;
            private String type2;
            private DateBean createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public DateBean getStartTime() {
                return startTime;
            }

            public void setStartTime(DateBean startTime) {
                this.startTime = startTime;
            }

            public DateBean getEndTime() {
                return endTime;
            }

            public void setEndTime(DateBean endTime) {
                this.endTime = endTime;
            }

            public Float getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(Float totalTime) {
                this.totalTime = totalTime;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getType2() {
                return type2;
            }

            public void setType2(String type2) {
                this.type2 = type2;
            }

            public DateBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(DateBean createTime) {
                this.createTime = createTime;
            }
        }

        public static class ObjectBean {
            private List<ListBean> list;
            private String fileUrl;

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * shuliang : 55
                 * piaoju : 看看
                 * name : 看看
                 * yongtu : 看看
                 * guige : 看看
                 * danjia : 55.0
                 *
                 */

                private String shuliang;
                private String piaoju;
                private String name;
                private String yongtu;
                private String guige;
                private String danjia;
                private String fengzhuang;
                private String pinpai;
                private String fenlei;
                private String lianxiren;
                private String dianhua;
                private String beizhu;

                public String getBeizhu() {
                    return beizhu;
                }

                public void setBeizhu(String beizhu) {
                    this.beizhu = beizhu;
                }

                public String getFengzhuang() {
                    return fengzhuang;
                }

                public void setFengzhuang(String fengzhuang) {
                    this.fengzhuang = fengzhuang;
                }

                public String getPinpai() {
                    return pinpai;
                }

                public void setPinpai(String pinpai) {
                    this.pinpai = pinpai;
                }

                public String getFenlei() {
                    return fenlei;
                }

                public void setFenlei(String fenlei) {
                    this.fenlei = fenlei;
                }

                public String getLianxiren() {
                    return lianxiren;
                }

                public void setLianxiren(String lianxiren) {
                    this.lianxiren = lianxiren;
                }

                public String getDianhua() {
                    return dianhua;
                }

                public void setDianhua(String dianhua) {
                    this.dianhua = dianhua;
                }

                public String getShuliang() {
                    return shuliang;
                }

                public void setShuliang(String shuliang) {
                    this.shuliang = shuliang;
                }

                public String getPiaoju() {
                    return piaoju;
                }

                public void setPiaoju(String piaoju) {
                    this.piaoju = piaoju;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getYongtu() {
                    return yongtu;
                }

                public void setYongtu(String yongtu) {
                    this.yongtu = yongtu;
                }

                public String getGuige() {
                    return guige;
                }

                public void setGuige(String guige) {
                    this.guige = guige;
                }

                public String getDanjia() {
                    return danjia;
                }

                public void setDanjia(String danjia) {
                    this.danjia = danjia;
                }


                private String leixing;
                private String didian;
                private String shixiang;

                public String getLeixing() {
                    return leixing;
                }

                public void setLeixing(String leixing) {
                    this.leixing = leixing;
                }

                public String getDidian() {
                    return didian;
                }

                public void setDidian(String didian) {
                    this.didian = didian;
                }

                public String getShixiang() {
                    return shixiang;
                }

                public void setShixiang(String shixiang) {
                    this.shixiang = shixiang;
                }
            }
        }
    }
}
