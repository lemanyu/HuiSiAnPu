package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/20.
 */

public class PerformanceBean {


    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"list":[{"m2Score":-1,"createTime":"2017-12-20","myScore":59,"name":"王二","managerScore":-1}]}
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
             * m2Score : -1
             * createTime : 2017-12-20
             * myScore : 59
             * name : 王二
             * managerScore : -1
             */
            private int workerId;
            private int m2Score;
            private String createTime;
            private int myScore;
            private String name;
            private int managerScore;
            private int id;

            public int getWorkerId() {
                return workerId;
            }

            public void setWorkerId(int workerId) {
                this.workerId = workerId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getM2Score() {
                return m2Score;
            }

            public void setM2Score(int m2Score) {
                this.m2Score = m2Score;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getMyScore() {
                return myScore;
            }

            public void setMyScore(int myScore) {
                this.myScore = myScore;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getManagerScore() {
                return managerScore;
            }

            public void setManagerScore(int managerScore) {
                this.managerScore = managerScore;
            }
        }
    }
}
