package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2018/1/10.
 */

public class JiXiaoBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"list":[{"workerId":2,"m2Score":-1,"createTime":"2018-01-09","myScore":28,"name":"李向东","managerScore":-1,"id":4},{"workerId":5,"m2Score":-1,"createTime":"2018-01-10","myScore":31,"name":"赵贵","managerScore":-1,"id":5}]}
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
             * workerId : 2
             * m2Score : -1
             * createTime : 2018-01-09
             * myScore : 28
             * name : 李向东
             * managerScore : -1
             * id : 4
             */
            private int size;

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
