package com.hsap.huisianpu.pager.mine;

import java.util.List;

/**
 * Created by zhao on 2018/1/2.
 */

public class MineProjectBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * maxBar : 8
         * ProjectNumber : 66
         * ProgreddBar : 1
         * startTime : 2018-01-02
         * id : 27
         * projectName : 77
         */

        private int maxBar;
        private String ProjectNumber;
        private int ProgreddBar;
        private String startTime;
        private int id;
        private String projectName;

        public int getMaxBar() {
            return maxBar;
        }

        public void setMaxBar(int maxBar) {
            this.maxBar = maxBar;
        }

        public String getProjectNumber() {
            return ProjectNumber;
        }

        public void setProjectNumber(String ProjectNumber) {
            this.ProjectNumber = ProjectNumber;
        }

        public int getProgreddBar() {
            return ProgreddBar;
        }

        public void setProgreddBar(int ProgreddBar) {
            this.ProgreddBar = ProgreddBar;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
