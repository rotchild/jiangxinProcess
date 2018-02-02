package com.project.cx.processcontroljx.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.project.cx.processcontroljx.R;
import com.project.cx.processcontroljx.beans.ParamType;

/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener{
    private View mFootView;
    private int mTotalItemCount;//item总数
    private OnLoadMoreListener mLoadMoreListener;
    private boolean mIsLoading=false;//是否正在加载
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String mtype="";

    public LoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.MyTag);
        mtype=ta.getString(R.styleable.MyTag_tag);
        init(context);
    }

    @Override
    public void onScrollStateChanged(AbsListView listview, int scrollState) {
        int lastVisibleIndex=listview.getLastVisiblePosition();
        if(!mIsLoading && scrollState==OnScrollListener.SCROLL_STATE_IDLE && lastVisibleIndex==mTotalItemCount-1){
            mIsLoading=true;
            addFooterView(mFootView);
            if(mLoadMoreListener!=null){
                mLoadMoreListener.onloadMore();
            }
        }
        if(scrollState==OnScrollListener.SCROLL_STATE_IDLE){
            int index=listview.getFirstVisiblePosition();
            View v=listview.getChildAt(0);
            int top=(v==null)?0:v.getTop();
            Log.e("loadMoreListView","mtype:"+mtype);
            if(mtype.equals(ParamType.DCK)){
                editor.putInt("dckindex",index);
                editor.putInt("dcktop",top);
            }else
            if(mtype.equals(ParamType.YCK)){
                editor.putInt("yckindex",index);
                editor.putInt("ycktop",top);
            }else
            if(mtype.equals(ParamType.DDS)){
                editor.putInt("ddsindex",index);
                editor.putInt("ddstop",top);
            }else
            if(mtype.equals(ParamType.DSZ)){
                editor.putInt("dszindex",index);
                editor.putInt("dsztop",top);
            }else
            if(mtype.equals(ParamType.HP)){
                editor.putInt("hpindex",index);
                editor.putInt("hptop",top);
            }else
            if(mtype.equals(ParamType.YDS)){
                editor.putInt("ydsindex",index);
                editor.putInt("ydstop",top);
            }else
            if(mtype.equals(ParamType.GZ)){
                editor.putInt("gzindex",index);
                editor.putInt("gztop",top);
            }else
            if(mtype.equals(ParamType.LS)){
                editor.putInt("lsindex",index);
                editor.putInt("lstop",top);
            }

            editor.commit();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount=totalItemCount;
    }

    private void init(Context context){
        sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
        editor=sp.edit();
        mFootView= LayoutInflater.from(context).inflate(R.layout.foot_view,null);
        setOnScrollListener(this);
    }

    public void setLoadCompleted(){
        mIsLoading=false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeFooterView(mFootView);
            }
        },3000);
    }

    public void setOnloadMoreListener(OnLoadMoreListener listener){
        mLoadMoreListener=listener;
    }

    public interface OnLoadMoreListener{
        void onloadMore();
    }

    public int[] getStorePosition(){
        int[] indexs=new int[2];
        for(int i=0;i<indexs.length;i++){
            indexs[0]=sp.getInt("index",0);
            indexs[1]=sp.getInt("top",0);
        }
        return indexs;
    }

}
