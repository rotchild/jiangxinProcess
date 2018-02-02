package com.project.cx.processcontroljx.utils;

import com.squareup.otto.Bus;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class BusUtil {
    static Bus INSTANCE;
    public static Bus getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE=new Bus();
        }
        return INSTANCE;
    }
}
