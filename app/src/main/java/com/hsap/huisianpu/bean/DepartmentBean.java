package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by Dongsh on ${DATA}.
 */
public class DepartmentBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 研发中心
         * Id : 1
         * state : 0
         */

        private String name;
        private int Id;
        private String state;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "ListBean{" + "name='" + name + '\'' + ", Id=" + Id + ", state='" + state + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "DepartmentBean{" + "list=" + list + '}';
    }
}
