package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/11/22.
 */

public class SelectApproverBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":1,"name":"管理员","idCard":"1301241","phone":"123","birthDate":{"month":"JULY","year":2004,"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":9,"dayOfWeek":"FRIDAY","dayOfYear":191,"monthValue":7,"chronology":{"id":"ISO","calendarType":"iso8601"}},"age":"女","departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"hour":10,"minute":58,"nano":0,"second":17,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":1,"state":"在职","createDate":{"month":"OCTOBER","year":2017,"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":30,"dayOfWeek":"MONDAY","dayOfYear":303,"monthValue":10,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":"111@qq.com","token":"899aeb2e128eedbe413a9518fd394c72e53429ac"},{"id":2,"name":"经理","idCard":"111","phone":"11","birthDate":{"month":"JANUARY","year":2011,"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":1,"dayOfWeek":"SATURDAY","dayOfYear":1,"monthValue":1,"chronology":{"id":"ISO","calendarType":"iso8601"}},"age":"男","departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"hour":13,"minute":8,"nano":0,"second":36,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":2,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"hour":13,"minute":8,"nano":0,"second":36,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":3,"name":"总监","idCard":"33","phone":"33","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"hour":13,"minute":9,"nano":0,"second":17,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":3,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"hour":13,"minute":9,"nano":0,"second":17,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null},{"id":4,"name":"总经理","idCard":"4","phone":"4","birthDate":null,"age":null,"departmentId":1,"createTime":{"month":"NOVEMBER","year":2017,"hour":13,"minute":9,"nano":0,"second":41,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"jurisdictionId":4,"state":"在职","createDate":{"month":"NOVEMBER","year":2017,"hour":13,"minute":9,"nano":0,"second":41,"dayOfMonth":22,"dayOfWeek":"WEDNESDAY","dayOfYear":326,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}},"createId":0,"email":null,"token":null}]
     */

    private int code;
    private Object msg;
    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 管理员
         * idCard : 1301241
         * phone : 123
         * birthDate : {"month":"JULY","year":2004,"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":9,"dayOfWeek":"FRIDAY","dayOfYear":191,"monthValue":7,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * age : 女
         * departmentId : 1
         * createTime : {"month":"NOVEMBER","year":2017,"hour":10,"minute":58,"nano":0,"second":17,"dayOfMonth":20,"dayOfWeek":"MONDAY","dayOfYear":324,"monthValue":11,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * jurisdictionId : 1
         * state : 在职
         * createDate : {"month":"OCTOBER","year":2017,"hour":0,"minute":0,"nano":0,"second":0,"dayOfMonth":30,"dayOfWeek":"MONDAY","dayOfYear":303,"monthValue":10,"chronology":{"id":"ISO","calendarType":"iso8601"}}
         * createId : 0
         * email : 111@qq.com
         * token : 899aeb2e128eedbe413a9518fd394c72e53429ac
         */

        private int id;
        private String name;
        private String idCard;
        private String phone;
        private String age;
        private int departmentId;
        private DataBean createTime;
        private int jurisdictionId;
        private String state;
        private DateBean createDate;
        private int createId;
        private String email;
        private String token;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }
}
