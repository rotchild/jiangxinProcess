package com.project.cx.processcontroljx.taskdetail;

import com.project.cx.processcontroljx.beans.Type_Selected;
import com.project.cx.processcontroljx.theme.MBaseActivity;

/**
 * 作为所有detail的基类.
 */

public abstract class DetailTask extends MBaseActivity {
    protected void setTaskTypeSelected(String typeSelected){
        Type_Selected.typeSelected=typeSelected;
    }

}
