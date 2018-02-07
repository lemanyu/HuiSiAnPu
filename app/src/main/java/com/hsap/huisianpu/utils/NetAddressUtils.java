package com.hsap.huisianpu.utils;

/**
 *
 * @author zhao
 * @date 2017/11/18
 */

public class NetAddressUtils {
    public static String url="http://101.200.229.18:8082/phone/";
    public static String attend=url+"Attend/";
    public static String sign=url+"Sign/";
    public static String login=url+"login";//登录
    public static String kaohe=url+"MonthAssessment/";
    public static String project=url+"Project/";//项目一级
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
    public static String setAudit=url+"setAudit";//审批
    public static String insertOneMonth=kaohe+"insertOneMonth";//提交绩效
    public static String selectMySumScoreMonthInfo=kaohe+"selectMySumScoreMonthInfo";
    public static String selectByMyDepartmentSumScoreMonth=kaohe+"selectByMyDepartmentSumScoreMonth";
    public static String isNowMonth=kaohe+"isNowMonth";//是否当月提交过
    public static String selectMySumScoreMonth=kaohe+"selectMySumScoreMonth";//获取自己的
    public static String successIntergration=url+"successIntergration";//完成
    public static String deleteIntergration=url+"deleteIntergration";//撤销
    public static String insertTTBSummary=attend+"insertTTBSummary";//提交出差总结
    public static String queryTTBSummar=attend+"queryTTBSummar";//获取一条出差总结
    public static String queryOneTTBSummar=attend+"queryOneTTBSummar";//获取一条出差总结
    public static String queryTTBSummarAll=attend+"queryTTBSummarAll";//查询所有出差
    public static String selectOneNotice=url+"Notice/selectOneNotice";
    public static String insertProjectBase=project+"insertProjectBase";//提交一个项目
    public static String getMyProjectInfo=project+"getMyProjectInfo";//获取详细信息
    public static String getDepartmentAllProject=project+"getDepartmentAllProject";//获取部门项目
    public static String getMyAllProject=project+"getMyAllProject";//获取自己项目
    public static String setProjectBar=project+"setProjectBar";//设置进度
    public static String reportForward=url+"reportForward";
    //获取个人信息
    public static String getBaseInformation = url + "getBaseInformation";
    public static String setBaseInformation = url + "setBaseInformation";
    public static String SelectDepartment = url + "SelectDepartment";
    public static String phoneImages = url + "images/";

    public static String Department = url + "Department/";
    public static String SelectDepartmentAll = Department + "SelectDepartmentAll";
    public static String selectByMyPutMonth=kaohe+"selectByMyPutMonth";
}
