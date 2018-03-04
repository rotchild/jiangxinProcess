package com.project.cx.processcontroljx.beans;

import android.content.ContentValues;
import android.view.View;
import android.widget.TextView;

import com.project.cx.processcontroljx.R;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
//管理选中的task
public class SelectedTask {
    private static ContentValues mtaskDCK,mtaskYCK,mtaskDDS,mtaskDSZ,mtaskYDS,mtaskHP,mtaskRSWORK,mtaskRSHIS,mtaskRisk;

    private static View selectedView;

    public static void storeView(View view){
        selectedView=view;
    }

    public static View getView(){
        return selectedView;
    }

    public static void storeTaskDCK(ContentValues taskDCK){
        mtaskDCK=taskDCK;
    }
    public static void storeTaskYCK(ContentValues taskYCK){
        mtaskYCK=taskYCK;
    }
    public static void storeTaskDDS(ContentValues taskDDS){
        mtaskDDS=taskDDS;
    }
    public static void storeTaskDSZ(ContentValues taskDSZ){
        mtaskDSZ=taskDSZ;
    }
    public static void storeTaskYDS(ContentValues taskYDS){
        mtaskYDS=taskYDS;
    }
    public static void storeTaskHP(ContentValues taskHP){
        mtaskHP=taskHP;
    }
    public static void storeTaskRSWORK(ContentValues taskRSWORK){
        mtaskRSWORK=taskRSWORK;
    }
    public static void storeTaskRSHIS(ContentValues taskRSHIS){
        mtaskRSHIS=taskRSHIS;
    }
    public static void storeTaskRisk(ContentValues taskRisk){
        mtaskRisk=taskRisk;
    }

    public static ContentValues getTaskDCK(){
        return mtaskDCK;
    }
    public static ContentValues getTaskYCK(){
        return mtaskYCK;
    }
    public static ContentValues getTaskDDS(){
        return mtaskDDS;
    }
    public static ContentValues getTaskDSZ(){
        return mtaskDSZ;
    }
    public static ContentValues getTaskYDS(){
        return mtaskYDS;
    }
    public static ContentValues getTaskHP(){
        return mtaskHP;
    }
    public static ContentValues getTaskRSWORK(){
        return mtaskRSWORK;
    }
    public static ContentValues getTaskRSHIS(){
        return mtaskRSHIS;
    }
    public static ContentValues getTaskRisk(){
        return mtaskRisk;
    }

    //更改tasklist的任务状态
    public static void changeViewStateSelected() {
        TextView viewSelected=null;
        if (Type_Selected.typeSelected.equals("dck")) {
            viewSelected = (TextView) selectedView.findViewById(R.id.dck_riskstate);
        }
        if (Type_Selected.typeSelected.equals("yck")) {
            viewSelected = (TextView) selectedView.findViewById(R.id.yck_riskstate);
        }
        if (Type_Selected.typeSelected.equals("dds")) {
            viewSelected = (TextView) selectedView.findViewById(R.id.dds_riskstate);
        }
        if (Type_Selected.typeSelected.equals("dsz")) {
            viewSelected = (TextView) selectedView.findViewById(R.id.dsz_riskstate);
        }
        if (Type_Selected.typeSelected.equals("yds")) {
            viewSelected = (TextView) selectedView.findViewById(R.id.yds_riskstate);
        }

        if (viewSelected != null) {
            viewSelected.setText("已上报");
        }
    }
}
