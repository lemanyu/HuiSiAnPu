package com.hsap.huisianpu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2017/11/16.
 */

public class ConstantUtils {
    public static String Login="login";
    public static String UserId="userid";
    public static String Username="username";
    public static String Approve="approve";
    private  Map<String, List<String>> dataset = new HashMap<>();
    public static String[] groupStrings = {"研发过程的规范性（20）", "产品研发周期控制（20）",
            "工作内容饱和度（20）","工作积极主动性（10）","与其他部门沟通配合（10）",
            "解决问题（10）","工作日志（10）","工作失误","其他人员投诉","违反公司纪律"};
    public  ArrayList<String> oneList = new ArrayList<>();
    public   ArrayList<String> twoList = new ArrayList<>();
    public  ArrayList<String> threeList = new ArrayList<>();
    public   ArrayList<String> fourList=new ArrayList<>();
    public  ArrayList<String> fiveList = new ArrayList<>();
    public  ArrayList<String> sixList = new ArrayList<>();
    public  ArrayList<String> sevenList = new ArrayList<>();
    public  ArrayList<String> eightList = new ArrayList<>();
    public  ArrayList<String> nineList = new ArrayList<>();
    public  ArrayList<String> tenList = new ArrayList<>();


    public  ArrayList<String> getOneList() {
        oneList.add("新产品研发过程标准、规范，工作纪律性强、无违规现象(20分)");
        oneList.add("新产品研发过程标准、规范，工作纪律性强、有个别违规现象（15~19分）");
        oneList.add("新产品研发过程比较规范，有违规现象，但不影响研发质量（8~14分）");
        oneList.add("新产品研发过程混乱、违规现象较多，影响研发质量（0~7分）");
        return oneList;
    }

    public Map<String, List<String>> getDataset() {
        dataset.put(groupStrings[0],oneList);
        dataset.put(groupStrings[1],twoList);
        dataset.put(groupStrings[2],threeList);
        dataset.put(groupStrings[3],fourList);
        dataset.put(groupStrings[4],fiveList);
        dataset.put(groupStrings[5],sixList);
        dataset.put(groupStrings[6],sevenList);
        dataset.put(groupStrings[7],eightList);
        dataset.put(groupStrings[8],nineList);
        dataset.put(groupStrings[9],tenList);
        return dataset;
    }

    public  void setDataset(Map<String, List<String>> dataset) {
       this.dataset = dataset;
    }

    public static String[] getGroupStrings() {
        return groupStrings;
    }

    public static void setGroupStrings(String[] groupStrings) {
        ConstantUtils.groupStrings = groupStrings;
    }

    public  void setOneList(ArrayList<String> oneList) {

        this.oneList = oneList;
    }

    public   ArrayList<String> getTwoList() {
        twoList.add("研发过程加班加点，每次均能提前或按时完成任务(20分)");
        twoList.add("研发过程每个任务延期5天内，每延期1天扣除1分,延期5~10天，每延期1天扣2分（0~19分）");
        return twoList;
    }

    public  void setTwoList(ArrayList<String> twoList) {
        this.twoList = twoList;
    }

    public  ArrayList<String> getThreeList() {
        threeList.add("工作内容十分饱满，工作安排有条理、经常加班（16~20分）");
        threeList.add("工作内容饱满，工作安排有条理，偶尔加班（11~15分）");
        threeList.add("工作内容基本饱满，工作安排合理（6~10分）");
        threeList.add("工作内容不饱满，工作安排不合理（0~5分）");
        return threeList;
    }

    public  void setThreeList(ArrayList<String> threeList) {
        this.threeList = threeList;
    }

    public  ArrayList<String> getFourList() {
        fourList.add("工作积极性高，有预见性，能够主动及时进行工作(7~10分)");
        fourList.add("工作有一定积极性，工作需领导进行安排(4~6分)");
        fourList.add("工作无积极性，行为懒散，不能主动工作(0~3分)");
        return fourList;
    }

    public  void setFourList(ArrayList<String> fourList) {
        this.fourList = fourList;
    }

    public  ArrayList<String> getFiveList() {
        fiveList.add("与其他部门沟通顺畅、高效(7~10分)");
        fiveList.add("与其他部门人员沟通偶尔会存在问题，造成沟通不畅(4~6分)");
        fiveList.add("与其他部门人员沟通经常出现问题，不能正常沟通(0~3)分");
        return fiveList;
    }

    public  void setFiveList(ArrayList<String> fiveList) {
        this.fiveList = fiveList;
    }

    public  ArrayList<String> getSixList() {
        sixList.add("独立解决问题的能力较强，通过钻研，都能够给出最合理的问题解决方案(7~10分)");
        sixList.add("具有独立解决问题能力，偶尔需其他人员进行指导(4~6分)");
        sixList.add("独立解决问题的能力较差，基本需其他人进行指导（0~3分）");
        return sixList;
    }

    public  void setSixList(ArrayList<String> sixList) {
        this.sixList = sixList;
    }

    public  ArrayList<String> getSevenList() {
        sevenList.add("工作日志缺少每1天扣1分");
        return sevenList;
    }

    public  void setSevenList(ArrayList<String> sevenList) {
        this.sevenList = sevenList;
    }

    public  ArrayList<String> getEightList() {
        eightList.add("每次工作失误扣1~5分");
        return eightList;
    }

    public  void setEightList(ArrayList<String> eightList) {
        this.eightList = eightList;
    }

    public  ArrayList<String> getNineList() {
        nineList.add("每次遭其他人员投诉扣1~5分");
        return nineList;
    }

    public  void setNineList(ArrayList<String> nineList) {
        this.nineList = nineList;
    }

    public  ArrayList<String> getTenList() {
        tenList.add("违反公司纪律每次扣10分");
        return tenList;
    }

    public  void setTenList(ArrayList<String> tenList) {
        this.tenList = tenList;
    }










}
