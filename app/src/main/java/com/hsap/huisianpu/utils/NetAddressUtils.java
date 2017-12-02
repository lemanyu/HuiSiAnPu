package com.hsap.huisianpu.utils;

/**
 * Created by zhao on 2017/11/18.
 */

public class NetAddressUtils {
    public static String url="http://192.168.9.200:8082/phone/";
    public static String attend=url+"Attend/";
    public static String login=url+"login";//登录
    public static String registration=url+"registration";//注册
    public static String invitation=url+"invitation";//工作邀请
    public static String invitationList=url+"invitationList";//我的邀请
    public static String isSignPush=url+"isSignPush";//打卡状态
    public static String signTopPush=url+"SignTopPush";//上班打卡
    public static String signDownPush=url+"SignDownPush";//下班打卡
    public static String getDaySignPush=url+"getDaySignPush";//下班打卡
    public static String getWorkerAdmin=url+"getWorkerAdmin";//获取所有员工所在部门的管理员
    public static String leave=url+"leave";//请假接口
    public static String setToken=url+"setToken";//推送token
    public static String setReportForm=url+"setReportForm";//发送日周月报
    public static String getWorkersList=url+"getWorkersList";//获取所在部门所有员工及经理
    public static String getMyReportForms=url+"getMyReportForms";//获取员工的日周月报
    public static String getNowReportFormState=url+"getNowReportFormState";//获取当日周月是否已经提交过报
    public static String getOneReportForm=url+"getOneReportForm";//推送的周月报
    public static String BusinessTrip=attend+"BusinessTrip";//出差审核

}
