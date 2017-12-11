package com.hsap.huisianpu.bean;

import java.util.List;

/**
 * Created by zhao on 2017/12/9.
 */

public class OngoingBean {

    /**
     * code : 200
     * msg : null
     * success : true
     * data : [{"id":48,"startTime":"2017-12-08 13:46:00.0","type":3},{"id":47,"startTime":"2017-12-08 13:45:00.0","type":3},{"id":46,"startTime":"2017-12-08 08:30:00.0","type":2},{"id":45,"startTime":"2017-12-08 08:30:00.0","type":2},{"id":44,"startTime":"2017-12-08 13:29:00.0","type":1},{"id":43,"startTime":"2017-12-08 08:30:00.0","type":0},{"id":42,"startTime":"2017-12-08 08:30:00.0","type":0},{"id":41,"startTime":"2017-12-08 10:44:00.0","type":4},{"id":40,"startTime":"2017-12-08 10:26:00.0","type":4},{"id":39,"startTime":"2017-12-08 10:20:00.0","type":4},{"id":34,"startTime":"2017-12-08 08:33:00.0","type":3},{"id":33,"startTime":"2017-12-07 16:58:00.0","type":3},{"id":32,"startTime":"2017-12-07 08:30:00.0","type":2},{"id":31,"startTime":"2017-12-07 08:30:00.0","type":2},{"id":28,"startTime":"2017-12-07 16:26:00.0","type":1},{"id":27,"startTime":"2017-12-07 16:20:00.0","type":1},{"id":26,"startTime":"2017-12-07 16:17:00.0","type":1},{"id":25,"startTime":"2017-12-07 16:14:00.0","type":1},{"id":24,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":23,"startTime":"2017-12-07 16:09:00.0","type":1},{"id":22,"startTime":"2017-12-07 16:03:00.0","type":1},{"id":21,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":20,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":19,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":18,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":17,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":16,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":15,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":14,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":13,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":12,"startTime":"2017-12-07 08:30:00.0","type":0},{"id":11,"startTime":"2017-12-07 00:00:00.0","type":0},{"id":10,"startTime":"2017-12-07 00:00:00.0","type":0},{"id":9,"startTime":"2017-12-07 00:00:00.0","type":0},{"id":8,"startTime":"2017-12-07 00:00:00.0","type":0},{"id":7,"startTime":"2017-12-07 00:00:00.0","type":0}]
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
         * id : 48
         * startTime : 2017-12-08 13:46:00.0
         * type : 3
         */

        private int id;
        private String startTime;
        private int type;

        public String getStartTime() {
            return startTime;
        }

        public int getType() {
            return type;
        }

    }
}
