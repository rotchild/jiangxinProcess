package com.project.cx.processcontroljx.beans;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

/**
 * 未读消息数量
 */
public class UnReadCounts {
    public static int DCKCount=0;
    public static int YCKCount=0;
    public static int DDSCount=0;
    public static int DSZCount=0;
    public static int YDSCount=0;
    public static int HPCount=0;
    public static int GZCount=0;
    public static int LSCount=0;

    public static int getCount(String tasktype){
        int count=-1;
        switch (tasktype){
            case ParamType.DCK:
                count=DCKCount;
                break;

            case ParamType.YCK:
                count=YCKCount;
                break;

            case ParamType.DDS:
                count=DDSCount;
                break;

            case ParamType.DSZ:
                count=DSZCount;
                break;

            case ParamType.YDS:
                count=YDSCount;
                break;

            case ParamType.HP:
                count=HPCount;
                break;

            case ParamType.ALL:
                break;

            case ParamType.GZ:
                count=GZCount;
                break;

            case ParamType.LS:
                count=LSCount;
                break;

            default:
                break;
        }
        return count;
    }

    public static void setCount(String tasktype,int count){
        switch (tasktype){
            case ParamType.DCK:
                DCKCount=count;
                break;

            case ParamType.YCK:
                YCKCount=count;
                break;

            case ParamType.DDS:
                DDSCount=count;
                break;

            case ParamType.DSZ:
                DSZCount=count;
                break;

            case ParamType.YDS:
                YDSCount=count;
                break;

            case ParamType.HP:
                HPCount=count;
                break;

            case ParamType.ALL:
                break;

            case ParamType.GZ:
                GZCount=count;
                break;

            case ParamType.LS:
                LSCount=count;
                break;

            default:
                break;
        }

    }

}
