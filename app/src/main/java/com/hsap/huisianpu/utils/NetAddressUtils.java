package com.hsap.huisianpu.utils;

/**
 *
 * @author zhao
 * @date 2017/11/18
 */

public class NetAddressUtils {
    public static String url="http://192.168.9.200:8082/phone/";
    public static String attend=url+"Attend/";
    public static String sign=url+"Sign/";
    public static String login=url+"login";//登录
    public static String registration=url+"registration";//注册
    public static String invitation=url+"invitation";//工作邀请
    public static String invitationList=url+"invitationList";//我的邀请
    public static String isSignPush=sign+"isSignPush";//打卡状态
    public static String signTopPush=sign+"SignPush";//上班打卡
    public static String getDaySignPush=sign+"getDaySignPush";//获取某天打卡状态
    public static String getWorkerAdmin=url+"getWorkerAdmin";//获取所有员工所在部门的管理员
    public static String leave=url+"/Leave/leave";//请假接口
    public static String setToken=url+"setToken";//推送token
    public static String setReportForm=url+"setReportForm";//发送日周月报
    public static String getWorkersList=url+"getWorkersList";//获取所在部门所有员工及经理
    public static String getMyReportForms=url+"getMyReportForms";//获取员工的日周月报
    public static String getNowReportFormState=url+"getNowReportFormState";//获取当日周月是否已经提交过报
    public static String getOneReportForm=url+"getOneReportForm";//推送的周月报
    public static String BusinessTrip=attend+"BusinessTrip";//出差审核
    public static String getOneBusinessTrip=attend+"getOneBusinessTrip";//获取单条出差记录
    public static String getMyBusinessTrips=attend+"getMyBusinessTrips";//获取自己出差信息
    public static String getJurisdiction=url+"getJurisdiction";//获取权限信息
    public static String getAuditList=url+"getAuditList";//获取获取所有审核
    public  static String getMyAttendance=url+"getMyAttendance";//我的考勤
    public static String getOneLeave=url+"/Leave/getOneLeave";//获取一条请假信息
    public static String insertIntegration=url+"insertIntegration";
    public static  String selectOneIntergration=url+"selectOneIntergration";
    public static String selectIntegration=url+"selectIntegration";
    public static String addNotice=url+"Notice/addNotice";
    public static String selectAllNotice=url+"Notice/selectAllNotice";
    public static String deleteNotice=url+"Notice/deleteNotice";
    public static String getPhoto=url+"images/";
}
