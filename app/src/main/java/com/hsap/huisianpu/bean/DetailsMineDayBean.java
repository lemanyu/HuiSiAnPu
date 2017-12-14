package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/13.
 */

public class DetailsMineDayBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : {"reportForm":{"id":193,"workersId":2,"fileSize":4,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":12,"dayOfWeek":"TUESDAY","dayOfYear":346,"hour":15,"minute":8,"nano":0,"second":1,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"把","unWork":"吧","coordinateWork":"吧","remarks":null,"summary":null,"workPlay":null},"list":[{"fileName":"201712121930.png","type":8},{"fileName":"201712121931.png","type":8},{"fileName":"201712121932.jpg","type":8},{"fileName":"201712121933.png","type":8}]}
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
         * reportForm : {"id":193,"workersId":2,"fileSize":4,"createTime":{"month":"DECEMBER","year":2017,"dayOfMonth":12,"dayOfWeek":"TUESDAY","dayOfYear":346,"hour":15,"minute":8,"nano":0,"second":1,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}},"type":0,"finishWork":"把","unWork":"吧","coordinateWork":"吧","remarks":null,"summary":null,"workPlay":null}
         * list : [{"fileName":"201712121930.png","type":8},{"fileName":"201712121931.png","type":8},{"fileName":"201712121932.jpg","type":8},{"fileName":"201712121933.png","type":8}]
         */

        private ReportFormBean reportForm;
        private List<ListBean> list;

        public ReportFormBean getReportForm() {
            return reportForm;
        }

        public void setReportForm(ReportFormBean reportForm) {
            this.reportForm = reportForm;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ReportFormBean {
            /**
             * id : 193
             * workersId : 2
             * fileSize : 4
             * createTime : {"month":"DECEMBER","year":2017,"dayOfMonth":12,"dayOfWeek":"TUESDAY","dayOfYear":346,"hour":15,"minute":8,"nano":0,"second":1,"monthValue":12,"chronology":{"id":"ISO","calendarType":"iso8601"}}
             * type : 0
             * finishWork : 把
             * unWork : 吧
             * coordinateWork : 吧
             * remarks : null
             * summary : null
             * workPlay : null
             */

            private int id;
            private int workersId;
            private int fileSize;
            private int type;
            private String finishWork;
            private String unWork;
            private String coordinateWork;
            private Object remarks;
            private String summary;
            private String workPlay;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWorkersId() {
                return workersId;
            }

            public void setWorkersId(int workersId) {
                this.workersId = workersId;
            }

            public int getFileSize() {
                return fileSize;
            }

            public void setFileSize(int fileSize) {
                this.fileSize = fileSize;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getFinishWork() {
                return finishWork;
            }

            public void setFinishWork(String finishWork) {
                this.finishWork = finishWork;
            }

            public String getUnWork() {
                return unWork;
            }

            public void setUnWork(String unWork) {
                this.unWork = unWork;
            }

            public String getCoordinateWork() {
                return coordinateWork;
            }

            public void setCoordinateWork(String coordinateWork) {
                this.coordinateWork = coordinateWork;
            }

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getWorkPlay() {
                return workPlay;
            }

            public void setWorkPlay(String workPlay) {
                this.workPlay = workPlay;
            }

        }

        public static class ListBean {
            /**
             * fileName : 201712121930.png
             * type : 8
             */

            private String fileName;
            private int type;

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
