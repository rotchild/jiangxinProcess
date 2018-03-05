package com.project.cx.processcontroljx.beans;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

import android.content.ContentValues;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * GetRisks_warn 中data json数组中json的结构
 * 管理content的array数组
 * 风险类型管理
 */
public class RiskWarm {
    public static final String RISKNAME="riskname";
    public static final String CONTENT="content";
    public static ArrayList<ContentValues> riskwarmArray=new ArrayList<ContentValues>();
    public static ArrayList<ContentValues> risksyswarmArray=new ArrayList<ContentValues>();

    //将getTaskRisk中中的risktype,others转为arrayList<ContentValue>
    public ArrayList<ContentValues> format2AList(String risktypeStr,String othersStr){
        ArrayList<ContentValues> resultList=new ArrayList<>();
        if(!TextUtils.isEmpty(risktypeStr)){
            String[] risktypeListStr=risktypeStr.split(",");
            ArrayList<ContentValues> riskList=getRisktypeList(risktypeListStr);
            resultList.addAll(riskList);
        }
        if(!TextUtils.isEmpty(othersStr)){
            ContentValues cv=new ContentValues();
            cv.put("riskname","其他类型:");
            cv.put("content",othersStr);
            resultList.add(cv);
        }
        return resultList;
    }
    private ArrayList<ContentValues> getRisktypeList(String[] risktypeListStr){
        ArrayList<ContentValues> risktypeList=new ArrayList<>();
        for(int i=0;i<risktypeListStr.length;i++){
           ContentValues cv=getRiskContentValues(risktypeListStr[i]);
            risktypeList.add(cv);
        }
        return risktypeList;
    }

/*    private ContentValues getContentValue(String key,String value){
        ContentValues cv=new ContentValues();
        cv.put(key,value);
        return cv;
    }*/

    //根据risktype的值确定risktype的名字
    private ContentValues getRiskContentValues(String value){
        ContentValues risktypeCV=new ContentValues();
        if(value.equals("1")){
            //risktypeName="风险类型1";
            risktypeCV.put("riskname","风险类型1");
            risktypeCV.put("content","");
        }
        if(value.equals("2")){
            risktypeCV.put("riskname","风险类型2");
            risktypeCV.put("content","");
        }
        if(value.equals("3")){
            risktypeCV.put("riskname","风险类型3");
            risktypeCV.put("content","");
        }
        if(value.equals("4")){
            risktypeCV.put("riskname","风险类型4");
            risktypeCV.put("content","");
        }
        if(value.equals("5")){
            risktypeCV.put("riskname","风险类型5");
            risktypeCV.put("content","");
        }
        if(value.equals("6")){
            risktypeCV.put("riskname","风险类型6");
            risktypeCV.put("content","");
        }
        if(value.equals("7")){
            risktypeCV.put("riskname","风险类型7");
            risktypeCV.put("content","");
        }
        if(value.equals("8")){
            risktypeCV.put("riskname","风险类型8");
            risktypeCV.put("content","");
        }
        if(value.equals("9")){
            risktypeCV.put("riskname","风险类型9");
            risktypeCV.put("content","");
        }
        return risktypeCV;
    }
}
