package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/20.
 */

public class PerformanceBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * workerId : 5
         * m2Score : -1
         * size : 2
         * createTime : 2018-01-10
         * myScore : 54
         * name : 赵贵
         * managerScore : 66
         * id : 6
         */

        private int workerId;
        private int m2Score;
        private int size;
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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
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
