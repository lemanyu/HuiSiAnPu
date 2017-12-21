package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/21.
 */

public class MinePerformanceBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"info":[{"m2Score":-1,"createTime":"2017-12-21","myScore":34,"managerScore":47,"id":9},{"m2Score":-1,"createTime":"2017-12-21","myScore":29,"managerScore":-1,"id":10},{"m2Score":-1,"createTime":"2017-12-21","myScore":36,"managerScore":-1,"id":11}]}
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
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * m2Score : -1
             * createTime : 2017-12-21
             * myScore : 34
             * managerScore : 47
             * id : 9
             */

            private int m2Score;
            private String createTime;
            private int myScore;
            private int managerScore;
            private int id;

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

            public int getManagerScore() {
                return managerScore;
            }

            public void setManagerScore(int managerScore) {
                this.managerScore = managerScore;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
