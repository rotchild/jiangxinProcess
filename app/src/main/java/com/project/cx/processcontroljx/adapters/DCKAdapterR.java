package com.project.cx.processcontroljx.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.cx.processcontroljx.R;
import com.project.cx.processcontroljx.beans.TaskCK;
import com.project.cx.processcontroljx.utils.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/1/26 0026.
 */

public class DCKAdapterR extends RecyclerView.Adapter<DCKAdapterR.MViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<ContentValues> mData;
    public interface MOnItemClickListener{
        void onClick(int i);
    }

    private DCKAdapterR.MOnItemClickListener mOnItemClicklistener;

    public DCKAdapterR(Context mContext, List<ContentValues> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setMOnItemClickListener(DCKAdapterR.MOnItemClickListener mOnItemClickListener){
        this.mOnItemClicklistener=mOnItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClicklistener!=null){
            mOnItemClicklistener.onClick((int)v.getTag());
        }
    }

    @Override
    public DCKAdapterR.MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dck_r,parent,false);
        view.setOnClickListener(this);
        MViewHolder vh=new MViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(DCKAdapterR.MViewHolder holder_dck, int position) {
        holder_dck.itemView.setTag(position);
                    String isRead=mData.get(position).getAsString(TaskCK.isRead);
            if(isRead.equals("0")){//是否显示isNewTag
                holder_dck.newTaskTag.setVisibility(View.VISIBLE);
            }else{
                holder_dck.newTaskTag.setVisibility(View.GONE);
            }
//                    holder_dck.item_ck.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClicklistener.onClick(position);
//                }
//            });
            long reparations_long=Long.valueOf(mData.get(position).getAsString(TaskCK.reparations));
            holder_dck.caseNo.setText(mData.get(position).getAsString(TaskCK.caseNo));
            Log.i("dckadapterText","position:"+position+"caseNo:"+mData.get(position).getAsString(TaskCK.caseNo));
            holder_dck.licenseno.setText(mData.get(position).getAsString(TaskCK.licenseno));

            Long Ctime_long=Long.valueOf(mData.get(position).getAsString(TaskCK.caseTime))*1000;
            String Ctime_str=String.valueOf(Ctime_long);
            String caseTime_str= TimeUtil.stampToDate(Ctime_str);
            holder_dck.caseTime.setText(caseTime_str);
            //holder_dck.caseState.setText(mData.get(position).getAsString(TaskCK.case_state));


            holder_dck.outNumber.setText(mData.get(position).getAsString(TaskCK.outNumber)+"次");

            String hurtstate_str="--";
            Long hurtstate_long=Long.valueOf(mData.get(position).getAsString(TaskCK.hurt_state));
            if(hurtstate_long==0){
                hurtstate_str="";
            }else if(hurtstate_long==1){
                holder_dck.image_hurt.setImageDrawable(mContext.getResources().getDrawable(R.drawable.red_medical));
                holder_dck.image_hurt.setVisibility(View.VISIBLE);
                hurtstate_str="未完成";
                holder_dck.hurt_state.setVisibility(View.VISIBLE);
            }else if(hurtstate_long==2){
                holder_dck.image_hurt.setImageDrawable(mContext.getResources().getDrawable(R.drawable.green_medical));
                holder_dck.image_hurt.setVisibility(View.VISIBLE);
                hurtstate_str="已完成";
                holder_dck.hurt_state.setTextColor(mContext.getResources().getColor(R.color.typegreen));
                holder_dck.hurt_state.setVisibility(View.VISIBLE);
            }
            holder_dck.hurt_state.setText(hurtstate_str);

            String risklevel_str="--";
            Long risklevel_long=Long.valueOf(mData.get(position).getAsString(TaskCK.risklevel));
            if(risklevel_long==0){
                risklevel_str="无风险";
                holder_dck.risklevel.setTextColor(mContext.getResources().getColor(R.color.typegreen));
            }else if(risklevel_long>0) {
                risklevel_str = "风险案件";
                holder_dck.risklevel.setTextColor(mContext.getResources().getColor(R.color.typered));
            }
            holder_dck.risklevel.setText(risklevel_str);

        String riskstate_str="--";
        Long riskstate_long=Long.valueOf(mData.get(position).getAsString(TaskCK.riskstate));
        if(riskstate_long==0){
            riskstate_str="未上报";
        }else if(riskstate_long==1){
            riskstate_str="已上报";
        }
        holder_dck.riskstate.setText(riskstate_str);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ContentValues getItem(int position) {
        return  mData.get(position);
    }

    public static class MViewHolder extends RecyclerView.ViewHolder{
        TextView caseNo,licenseno,caseTime,outNumber,risklevel,riskstate,hurt_state;
        RelativeLayout head;
        LinearLayout item_ck;
        ImageView newTaskTag;
        ImageView image_hurt;
        public MViewHolder(View itemView) {
            super(itemView);
            item_ck= (LinearLayout)itemView.findViewById(R.id.item_dck);
            caseNo=(TextView)itemView.findViewById(R.id.dck_caseNo);
            licenseno=(TextView)itemView.findViewById(R.id.dck_licenseno);
            caseTime=(TextView)itemView.findViewById(R.id.dck_caseTime);
            outNumber=(TextView)itemView.findViewById(R.id.dck_outNumber);
            risklevel=(TextView)itemView.findViewById(R.id.dck_risklevel);
            riskstate=(TextView)itemView.findViewById(R.id.dck_riskstate);
            hurt_state=(TextView)itemView.findViewById(R.id.dck_hurt_state);
            newTaskTag=(ImageView)itemView.findViewById(R.id.dck_new_tag);


            head= (RelativeLayout)itemView.findViewById(R.id.dck_head_change);
            image_hurt = (ImageView)itemView.findViewById(R.id.dck_hurt_state_icon);

//            }
        }
    }
}
