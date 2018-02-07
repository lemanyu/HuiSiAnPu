package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/27.
 */

public class MineSummaryBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * date : 2017-12-27
         * id : 9
         */

        private String date;
        private int id;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
