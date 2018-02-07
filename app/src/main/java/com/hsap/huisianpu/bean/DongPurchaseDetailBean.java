package com.hsap.huisianpu.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2017/12/23.
 */

public class DongPurchaseDetailBean {

    /**
     * createName : ll
     * nameList : ["ll","王三","王四","王五","王六"]
     * waIntegration : {"id":158,"startTime":{"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":14,"minute":44,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"endTime":{"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":15,"minute":44,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}},"totalTime":null,"state":1,"reason":null,"type":4,"type2":"15903360181","createTime":{"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":14,"minute":45,"nano":0,"second":14,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
     * object : {"fileUrl":"http://192.168.9.216:8082/phone/file/getAttendFile/201712231584.docx?integrationId=158","list":[{"leixing":"商务用车","didian":"看看","shixiang":"看看"}]}
     * nameId : [1,3,4,5,6]
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

    public List<Integer> getNameId() {
        return nameId;
    }

    public void setNameId(List<Integer> nameId) {
        this.nameId = nameId;
    }

    public static class WaIntegrationBean {
        /**
         * id : 158
         * startTime : {"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":14,"minute":44,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * endTime : {"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":15,"minute":44,"nano":0,"second":0,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * totalTime : null
         * state : 1
         * reason : null
         * type : 4
         * type2 : 15903360181
         * createTime : {"dayOfMonth":23,"dayOfWeek":"SATURDAY","dayOfYear":357,"month":"DECEMBER","year":2017,"monthValue":12,"hour":14,"minute":45,"nano":0,"second":14,"chronology":{"id":"ISO","calendarType":"iso8601"}}
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
        /**
         * fileUrl : http://192.168.9.216:8082/phone/file/getAttendFile/201712231584.docx?integrationId=158
         * list : [{"leixing":"商务用车","didian":"看看","shixiang":"看看"}]
         */

        private String fileUrl;
        private Map<String,Object> list;

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public Map<String,Object> getList() {
            return list;
        }

        public void setList(Map<String,Object> list) {
            this.list = list;
        }
    }
}
