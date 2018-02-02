package com.project.cx.processcontroljx.beans;

import android.content.ContentValues;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class ListDataCache {
    public static final String TAG="ListDataCache";
    static ListDataCache INSATNCE;
    private List<ContentValues> mDataList;
    public static ListDataCache getINSATNCE(){
        if(INSATNCE==null){
            INSATNCE=new ListDataCache();
        }
        return INSATNCE;
    }
    public void setCache(List<ContentValues> dataList){
        mDataList=dataList;
    }
    public ContentValues getItem(int position){//ContentValues 考虑泛型
        int length=getCount();
        if(position<length){
            return mDataList.get(position);
        }else{
            Log.e(TAG,"error infor:"+"position:"+position+"/length:"+length);
            return null;
        }
    }

    public void setRiskValue(int postion,String riskStateStr){
        ContentValues value=getItem(postion);
        if(value!=null){
            value.put(TaskCK.riskstate,riskStateStr);
        }else{
            Log.e(TAG,"value is null");
        }

    }

    public int getCount(){
        if(mDataList!=null){
            return mDataList.size();
        }
        return 0;
    }

    public void clearCache(){
        if(mDataList!=null){
            mDataList=null;
        }
    }
}
