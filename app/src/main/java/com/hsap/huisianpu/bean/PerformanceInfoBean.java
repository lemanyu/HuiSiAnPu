package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/20.
 */

public class PerformanceInfoBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"m2Score":[{"type":2,"value":6}],"myScore":[{"type":0,"value":3},{"type":0,"value":4},{"type":0,"value":2},{"type":0,"value":1},{"type":0,"value":8},{"type":0,"value":9},{"type":0,"value":1},{"type":0,"value":2}],"managerScore":[{"type":1,"value":3}]}
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
        private List<ScoreBean> m2Score;
        private List<ScoreBean> myScore;
        private List<ScoreBean> managerScore;

        public List<ScoreBean> getM2Score() {
            return m2Score;
        }

        public void setM2Score(List<ScoreBean> m2Score) {
            this.m2Score = m2Score;
        }

        public List<ScoreBean> getMyScore() {
            return myScore;
        }

        public void setMyScore(List<ScoreBean> myScore) {
            this.myScore = myScore;
        }

        public List<ScoreBean> getManagerScore() {
            return managerScore;
        }

        public void setManagerScore(List<ScoreBean> managerScore) {
            this.managerScore = managerScore;
        }

        public static class ScoreBean {
            /**
             * type : 2
             * value : 6
             */

            private int type;
            private int value;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }


    }
}
