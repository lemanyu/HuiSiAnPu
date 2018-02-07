package com.hsap.huisianpu.bean;

/**
 * Created by zhao on 2017/11/21.
 */

public class DateBean {
    private int monthValue;
    private String month;
    private int year;
    private int dayOfMonth;
    private String dayOfWeek;
    private int dayOfYear;
    private int hour;
    private int minute;
    private int nano;
    private int second;
    private ChronologyBeanX chronology;

    public int getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(int monthValue) {
        this.monthValue = monthValue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getNano() {
        return nano;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public ChronologyBeanX getChronology() {
        return chronology;
    }

    public void setChronology(ChronologyBeanX chronology) {
        this.chronology = chronology;
    }

    public static class ChronologyBeanX {
        /**
         * id : ISO
         * calendarType : iso8601
         */

        private String id;
        private String calendarType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCalendarType() {
            return calendarType;
        }

        public void setCalendarType(String calendarType) {
            this.calendarType = calendarType;
        }
    }
}
