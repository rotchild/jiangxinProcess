package com.project.cx.processcontroljx.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.cx.processcontroljx.R;
import com.project.cx.processcontroljx.adapters.DCKAdapter;
import com.project.cx.processcontroljx.adapters.DCKAdapterR;
import com.project.cx.processcontroljx.adapters.DDSAdapter;
import com.project.cx.processcontroljx.adapters.DSZAdapter;
import com.project.cx.processcontroljx.adapters.HPAdapter;
import com.project.cx.processcontroljx.adapters.RSHISAdapter;
import com.project.cx.processcontroljx.adapters.RSWORKAdapter;
import com.project.cx.processcontroljx.adapters.RiskAdapter;
import com.project.cx.processcontroljx.adapters.YCKAdapter;
import com.project.cx.processcontroljx.adapters.YDSAdapter;
import com.project.cx.processcontroljx.beans.AreaObj;
import com.project.cx.processcontroljx.beans.DSArea;
import com.project.cx.processcontroljx.beans.DetailIntentType;
import com.project.cx.processcontroljx.beans.LoadType;
import com.project.cx.processcontroljx.beans.ParamType;
import com.project.cx.processcontroljx.beans.SelectedTask;
import com.project.cx.processcontroljx.beans.TaskCK;
import com.project.cx.processcontroljx.beans.TaskDS;
import com.project.cx.processcontroljx.beans.TaskRole;
import com.project.cx.processcontroljx.beans.Taskhurt;
import com.project.cx.processcontroljx.processmain.DSappointment;
import com.project.cx.processcontroljx.processmain.FxsbDetailActivity;
import com.project.cx.processcontroljx.processmain.ProcessMain;
import com.project.cx.processcontroljx.taskdetail.DetailDCK;
import com.project.cx.processcontroljx.taskdetail.DetailDDS;
import com.project.cx.processcontroljx.taskdetail.DetailDSZ;
import com.project.cx.processcontroljx.taskdetail.DetailHP;
import com.project.cx.processcontroljx.taskdetail.DetailRSHIS;
import com.project.cx.processcontroljx.taskdetail.DetailRSWORK;
import com.project.cx.processcontroljx.taskdetail.DetailYCK;
import com.project.cx.processcontroljx.taskdetail.DetailYDS;
import com.project.cx.processcontroljx.theme.MBaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/29 0029.
 */

public class MViewManager {
    private static MViewManager mViewManager;
    DCKAdapter dckAdapter;
    DCKAdapterR dckAdapterR;
    YCKAdapter yckAdapter;
    DDSAdapter ddsAdapter;
    DSZAdapter dszAdapter;
    YDSAdapter ydsAdapter;
    HPAdapter hpAdapter;
    RSWORKAdapter rsworkAdapter;
    RSHISAdapter rshisAdapter;
    private MViewManager(){}
    public static MViewManager getInstance(){
        if(mViewManager==null){
            mViewManager=new MViewManager();
        }
        return mViewManager;
    }

    /**
     * 初始化待查勘list
     * @param isVisible
     */
    public void initDCK(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_dck=inflater.inflate(R.layout.list_dck,null);
            //pm.xrv_dck= (XRefreshView) pm.v_dck.findViewById(R.id.xrv_dck);
/*            pm.lvr_dck.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DCK,"","","","",pm.loadStart_dck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DCK,pm));
                }
            });*/
            pm.lv_dck= (ListView) pm.v_dck.findViewById(R.id.lv_dck);
            pm.lvrf_dck= (RefreshLayout) pm.v_dck.findViewById(R.id.lvrf_dck);
            pm.lvrf_dck.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_dck.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DCK,"","","","",pm.loadStart_dck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DCK,pm));
                }
            });


//            pm.v_dck=inflater.inflate(R.layout.list_dck_recycer,null);
//            //pm.xrv_dck= (XRefreshView) pm.v_dck.findViewById(R.id.xrv_dck);
//            pm.lv_dckR= (RecyclerView) pm.v_dck.findViewById(R.id.lv_dck);
//            pm.lv_dckR.setLayoutManager(new LinearLayoutManager(context));

/*            pm.xrv_dck.setPullRefreshEnable(false);
            pm.xrv_dck.setPullLoadEnable(true);

            pm.xrv_dck.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {
                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.e(context.getClass().getSimpleName(),"dck onLoadMore enter");
                    pm.xrv_dck.stopLoadMore();
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DCK,"","","","",pm.loadStart_dck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DCK,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化已查勘list
     * @param isVisible
     */
    public void initYCK(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        Log.i(context.getClass().getSimpleName(),"initYCK enter");
        if(isVisible){
            pm.v_yck=inflater.inflate(R.layout.list_yck,null);
            pm.lvrf_yck= (RefreshLayout) pm.v_yck.findViewById(R.id.lvrf_yck);
            pm.lv_yck= (ListView) pm.v_yck.findViewById(R.id.lv_yck);
            pm.lvrf_yck.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_yck.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YCK,"","","","",pm.loadStart_yck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YCK,pm));
                }
            });
            //pm.xrv_yck= (XRefreshView) pm.v_yck.findViewById(R.id.xrv_yck);
            //pm.srf_yck= (SwipeRefreshLayout) pm.v_yck.findViewById(R.id.srf_yck);
            //pm.lv_yck=(ListView) pm.v_yck.findViewById(R.id.lv_yck);
/*            pm.lv_yck.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    Log.i(context.getClass().getSimpleName(),"onScrollStateChanged enter");
                    if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                        Log.i(context.getClass().getSimpleName(),"SCROLL_STATE_IDLE enter");
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });*/

/*            pm.lvr_yck=(LoadMoreListView) pm.v_yck.findViewById(R.id.lv_yck);
            pm.lvr_yck.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YCK,"","","","",pm.loadStart_yck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YCK,pm));
                }
            });*/
/*            pm.xrv_yck.setPullRefreshEnable(false);
            pm.xrv_yck.setPullLoadEnable(true);
            pm.xrv_yck.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YCK,"","","","",pm.loadStart_yck,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YCK,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化待定损list
     * @param isVisible
     */
    public void initDDS(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_dds=inflater.inflate(R.layout.list_dds,null);
            //pm.xrv_dds= (XRefreshView) pm.v_dds.findViewById(R.id.xrv_dds);
            //pm.lv_dds=(ListView)pm.v_dds.findViewById(R.id.lv_dds);
/*            pm.lvr_dds=(LoadMoreListView) pm.v_dds.findViewById(R.id.lv_dds);
            pm.lvr_dds.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DDS,"",pm.loadStart_dds,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DDS,pm));
                }
            });*/
            pm.lvrf_dds=(RefreshLayout) pm.v_dds.findViewById(R.id.lvrf_dds);
            pm.lv_dds= (ListView) pm.v_dds.findViewById(R.id.lv_dds);
            pm.lvrf_dds.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_dds.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DDS,"",pm.loadStart_dds,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DDS,pm));
                }
            });
//            pm.xrv_dds.setPullRefreshEnable(false);
//            pm.xrv_dds.setPullLoadEnable(true);
//            pm.xrv_dds.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//                @Override
//                public void onRefresh() {
//
//                }
//
//                @Override
//                public void onRefresh(boolean isPullDown) {
//
//                }
//
//                @Override
//                public void onLoadMore(boolean isSilence) {
//                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
//                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DDS,"",pm.loadStart_dds,pm.loadLimit,
//                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DDS,pm));
//                }
//
//                @Override
//                public void onRelease(float direction) {
//
//                }
//
//                @Override
//                public void onHeaderMove(double headerMovePercent, int offsetY) {
//
//                }
//            });
        }
    }

    /**
     * 初始化ALLlist
     * @param isVisible
     */
    public void initALL(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
/*            pm.v_all=inflater.inflate(R.layout.list_all,null);
            pm.xrv_all= (XRefreshView) pm.v_all.findViewById(R.id.xrv_all);
            pm.lv_all=(ListView)pm.v_all.findViewById(R.id.lv_all);

            pm.xrv_all.setPullRefreshEnable(false);
            pm.xrv_all.setPullLoadEnable(true);
            pm.xrv_all.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    //all没有调用接口
                   *//* pm.getTaskCKData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.,"0","10",
                            OkCallbackManager.getInstance().getCallback(context,ParamType.DDS,pm));*//*
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化DSZlist
     * @param isVisible
     */
    public void initDSZ(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_dsz=inflater.inflate(R.layout.list_dsz,null);
            //pm.xrv_dsz= (XRefreshView) pm.v_dsz.findViewById(R.id.xrv_dsz);
            //pm.lv_dsz=(ListView)pm.v_dsz.findViewById(R.id.lv_dsz);
/*            pm.lvr_dsz=(LoadMoreListView) pm.v_dsz.findViewById(R.id.lv_dsz);
            pm.lvr_dsz.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DSZ,"",pm.loadStart_dsz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DSZ,pm));
                }
            });*/
            pm.lvrf_dsz= (RefreshLayout) pm.v_dsz.findViewById(R.id.lvrf_dsz);
            pm.lv_dsz= (ListView) pm.v_dsz.findViewById(R.id.lv_dsz);
            pm.lvrf_dsz.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_dsz.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DSZ,"",pm.loadStart_dsz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DSZ,pm));
                }
            });
/*            pm.xrv_dsz.setPullRefreshEnable(false);
            pm.xrv_dsz.setPullLoadEnable(true);
            pm.xrv_dsz.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.DSZ,"",pm.loadStart_dsz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.DSZ,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化YDSlist
     * @param isVisible
     */
    public void initYDS(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_yds=inflater.inflate(R.layout.list_yds,null);
            //pm.xrv_yds= (XRefreshView) pm.v_yds.findViewById(R.id.xrv_yds);
            //pm.lv_yds=(ListView)pm.v_yds.findViewById(R.id.lv_yds);
/*            pm.lvr_yds=(LoadMoreListView) pm.v_yds.findViewById(R.id.lv_yds);
            pm.lvr_yds.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YDS,"",pm.loadStart_yds,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YDS,pm));
                }
            });*/
            pm.lvrf_yds= (RefreshLayout) pm.v_yds.findViewById(R.id.lvrf_yds);
            pm.lv_yds= (ListView) pm.v_yds.findViewById(R.id.lv_yds);
            pm.lvrf_yds.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_yds.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YDS,"",pm.loadStart_yds,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YDS,pm));
                }
            });
/*            pm.xrv_yds.setPullRefreshEnable(false);
            pm.xrv_yds.setPullLoadEnable(true);
            pm.xrv_yds.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.YDS,"",pm.loadStart_yds,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.YDS,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化HPlist
     * @param isVisible
     */
    public void initHP(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_hp=inflater.inflate(R.layout.list_hp,null);
            //pm.xrv_hp= (XRefreshView) pm.v_hp.findViewById(R.id.xrv_hp);
            //pm.lv_hp=(ListView)pm.v_hp.findViewById(R.id.lv_hp);
/*            pm.lvr_hp=(LoadMoreListView) pm.v_hp.findViewById(R.id.lv_hp);
            pm.lvr_hp.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.HP,"",pm.loadStart_hp,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.HP,pm));
                }
            });*/
            pm.lvrf_hp= (RefreshLayout) pm.v_hp.findViewById(R.id.lvrf_hp);
            pm.lv_hp= (ListView) pm.v_hp.findViewById(R.id.lv_hp);
            pm.lvrf_hp.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_hp.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.HP,"",pm.loadStart_hp,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.HP,pm));
                }
            });
/*            pm.xrv_hp.setPullRefreshEnable(false);
            pm.xrv_hp.setPullLoadEnable(true);
            pm.xrv_hp.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskDSData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.HP,"",pm.loadStart_hp,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.HP,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化RSWORKlist
     * @param isVisible
     */
    public void initRSWORK(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_rswork=inflater.inflate(R.layout.list_rswork,null);
            //pm.xrv_gz= (XRefreshView) pm.v_rswork.findViewById(R.id.xrv_rswork);
            //pm.lv_rswork=(ListView)pm.v_rswork.findViewById(R.id.lv_rswork);
/*            pm.lvr_rswork=(LoadMoreListView) pm.v_rswork.findViewById(R.id.lv_rswork);
            pm.lvr_rswork.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.GZ,"",pm.loadStart_gz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.GZ,pm));
                }
            });*/
            pm.lvrf_gz= (RefreshLayout) pm.v_rswork.findViewById(R.id.lvrf_gz);
            pm.lv_gz= (ListView) pm.v_rswork.findViewById(R.id.lv_gz);
            pm.lvrf_gz.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_gz.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.GZ,"",pm.loadStart_gz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.GZ,pm));
                }
            });
/*            pm.xrv_gz.setPullRefreshEnable(false);
            pm.xrv_gz.setPullLoadEnable(true);
            pm.xrv_gz.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.GZ,"",pm.loadStart_gz,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.GZ,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    /**
     * 初始化RSHISTROYlist
     * @param isVisible
     */
    public void initRSHISTROY(final Context context, boolean isVisible, final ProcessMain pm){
        LayoutInflater inflater=LayoutInflater.from(context);
        if(isVisible){
            pm.v_rshistroy=inflater.inflate(R.layout.list_rshistroy,null);
            pm.lvrf_ls= (RefreshLayout) pm.v_rshistroy.findViewById(R.id.lvrf_ls);
            pm.lv_ls= (ListView) pm.v_rshistroy.findViewById(R.id.lv_ls);
            pm.lvrf_ls.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pm.lvrf_ls.finishLoadmore(2000/*,false*/);//传入false表示加载失败
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.LS,"",pm.loadStart_ls,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.LS,pm));
                }
            });
            //pm.xrv_ls= (XRefreshView) pm.v_rshistroy.findViewById(R.id.xrv_rshistroy);
            //pm.lv_rshistroy=(ListView)pm.v_rshistroy.findViewById(R.id.lv_rshistroy);
/*            pm.lvr_rshistroy=(LoadMoreListView) pm.v_rshistroy.findViewById(R.id.lv_rshistroy);
            pm.lvr_rshistroy.setOnloadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
                @Override
                public void onloadMore() {
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.LS,"",pm.loadStart_ls,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.LS,pm));
                }
            });*/
/*            pm.xrv_ls.setPullRefreshEnable(false);
            pm.xrv_ls.setPullLoadEnable(true);
            pm.xrv_ls.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onRefresh(boolean isPullDown) {

                }

                @Override
                public void onLoadMore(boolean isSilence) {
                    Log.i(context.getClass().getSimpleName(),"onLoadMore enter");
                    pm.getTaskhurtData(pm.userManager.getUserToken(),pm.userManager.getFrontRole(), ParamType.LS,"",pm.loadStart_ls,pm.loadLimit,
                            OkCallbackManager.getInstance().getCallback(LoadType.LOADMORE,context,ParamType.LS,pm));
                }

                @Override
                public void onRelease(float direction) {

                }

                @Override
                public void onHeaderMove(double headerMovePercent, int offsetY) {

                }
            });*/
        }
    }

    public void setDCKLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            if(dckAdapter!=null){//第一次进入刷新的时候如果失败不会进入setDCKLayout,dckAdapter没有创建,调用loadmore时要判空
                dckAdapter.notifyDataSetChanged();
            }
            //dckAdapterR.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
//            dckAdapter=new DCKAdapter(context,listData);
//            pm.lvr_dck.setAdapter(dckAdapter);
            if(dckAdapter==null){
                dckAdapter=new DCKAdapter(context,listData);
                dckAdapter.setMOnItemClickListener(new DCKAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) dckAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskDCK(selectTask);
                        }
                        String isRead=selectTask.getAsString(TaskCK.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口,!需要注意刷新的时机
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                                    TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(context,DetailDCK.class,pm,ParamType.DCK));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailDCK.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                dckAdapter.setDataList(listData);
                Log.e("MViewManager","setDataList Enter listDataLength:"+listData.size());
            }
            //pm.lv_yck.setAdapter(yckAdapter);
            // pm.lvr_yck.setAdapter(yckAdapter);
            pm.lv_dck.setAdapter(dckAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int dckindex=sp.getInt("dckindex",0);
            int dcktop=sp.getInt("dcktop",0);
            Log.e("MViewManager","dckindex"+dckindex+"/dcktop"+dcktop);
            pm.lv_dck.setSelectionFromTop(dckindex,dcktop);
/*            int[] indexs=pm.lvr_yck.getStorePosition();
            pm.lvr_yck.setSelectionFromTop(indexs[0],indexs[1]);*/

//            dckAdapterR=new DCKAdapterR(context,listData);
//            pm.lv_dckR.setAdapter(dckAdapterR);

        }
        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.DCK);
        }else{
            pm.setNoDataView(false,ParamType.DCK);
        }
        /*dckAdapter=new DCKAdapter(context,listData);
        pm.lv_dck.setAdapter(dckAdapter);*/
       /* if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.DCK);
        }else{
            pm.setNoDataView(false,ParamType.DCK);
        }*/

       /* if(dckAdapter==null){
            dckAdapter=new DCKAdapter(context,listData);
            pm.lv_dck.setAdapter(dckAdapter);
            Log.e("MViewManager","dckAdapter is null");
        }else{
            dckAdapter.notifyDataSetChanged();
            Log.e("MViewManager","dckAdapter notifyDataSetChanged");
        }*/



/*        dckAdapter.setMOnItemClickListener(new DCKAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) dckAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDCK(selectTask);
                }
                String isRead=selectTask.getAsString(TaskCK.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口,!需要注意刷新的时机
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                            TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(context,DetailDCK.class,pm,ParamType.DCK));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailDCK.class,DetailIntentType.READ);
                }
            }
        });*/
/*        dckAdapterR.setMOnItemClickListener(new DCKAdapterR.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) dckAdapterR.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDCK(selectTask);
                }
                String isRead=selectTask.getAsString(TaskCK.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口,!需要注意刷新的时机
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                            TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(context,DetailDCK.class,pm,ParamType.DCK));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailDCK.class,DetailIntentType.READ);
                }
            }
        });*/
    }

    public void setYCKLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            Log.i("MViewManager","loadmore enter");
            yckAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
            if(yckAdapter==null){
                yckAdapter=new YCKAdapter(context,listData);
                yckAdapter.setMOnItemClickListener(new YCKAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) yckAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskYCK(selectTask);
                        }

                        String isRead=selectTask.getAsString(TaskCK.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                                    TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(context,DetailYCK.class,pm,ParamType.YCK));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailYCK.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                yckAdapter.setDataList(listData);
                Log.e("MViewManager","setDataList Enter listDataLength:"+listData.size());
            }
            //pm.lv_yck.setAdapter(yckAdapter);
           // pm.lvr_yck.setAdapter(yckAdapter);
           // pm.lvr_yck.setAdapter(yckAdapter);
            pm.lv_yck.setAdapter(yckAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int yckindex=sp.getInt("yckindex",0);
            int ycktop=sp.getInt("ycktop",0);
            Log.e("MViewManager","yckindex"+yckindex+"/ycktop"+ycktop);
            pm.lv_yck.setSelectionFromTop(yckindex,ycktop);
/*            int[] indexs=pm.lvr_yck.getStorePosition();
            pm.lvr_yck.setSelectionFromTop(indexs[0],indexs[1]);*/
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.YCK);
        }else{
            pm.setNoDataView(false,ParamType.YCK);
        }

      /*  if(yckAdapter==null){
            yckAdapter=new YCKAdapter(context,listData);
            pm.lv_yck.setAdapter(yckAdapter);
        }else{
            yckAdapter.notifyDataSetChanged();
        }*/
        //yckAdapter=new YCKAdapter(context,listData);
        //pm.lv_yck.setAdapter(yckAdapter);


    }

    public void setDDSLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            ddsAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
           /* ddsAdapter=new DDSAdapter(context,listData);
            pm.lv_dds.setAdapter(ddsAdapter);*/
            Log.e("MViewManager","DDSLayout Enter"+listData.size());
            if(ddsAdapter==null){
                ddsAdapter=new DDSAdapter(context,listData);
                ddsAdapter.setMOnItemClickListener(new DDSAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        //Detail对应修改
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) ddsAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskDDS(selectTask);
                        }

                        String isRead=selectTask.getAsString(TaskDS.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            //需要判断该待定损任务是否属于自己
                            if(!selectTask.getAsString(TaskDS.assessorNo).equals(pm.userManager.getJobNo())){
                                //不是自己的任务不调用setTaskRead
                                pm.startActivity(DetailDDS.class,DetailIntentType.UNREAD);
                            }else{
                                pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                        TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailDDS.class,pm,ParamType.DDS));
                            }
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailDDS.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                ddsAdapter.setDataList(listData);
                Log.e("MViewManager","DDSsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_dds.setAdapter(ddsAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int ddsindex=sp.getInt("ddsindex",0);
            int ddstop=sp.getInt("ddstop",0);
            Log.e("MViewManager","ddsindex"+ddsindex+"/ddstop"+ddstop);
            pm.lv_dds.setSelectionFromTop(ddsindex,ddstop);

            /*int[] indexs=pm.lvr_dds.getStorePosition();
            pm.lvr_dds.setSelectionFromTop(indexs[0],indexs[1]);*/
        }


        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.DDS);
        }else{
            pm.setNoDataView(false,ParamType.DDS);
        }

        /*if(ddsAdapter==null){
            ddsAdapter=new DDSAdapter(context,listData);
            pm.lv_dds.setAdapter(ddsAdapter);
        }else{
            ddsAdapter.notifyDataSetChanged();
        }*/


/*        ddsAdapter.setMOnItemClickListener(new DDSAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                //Detail对应修改
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) ddsAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDDS(selectTask);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    //需要判断该待定损任务是否属于自己
                    if(!selectTask.getAsString(TaskDS.assessorNo).equals(pm.userManager.getJobNo())){
                        //不是自己的任务不调用setTaskRead
                        pm.startActivity(DetailDDS.class,DetailIntentType.UNREAD);
                    }else{
                        pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailDDS.class,pm,ParamType.DDS));
                    }
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailDDS.class,DetailIntentType.READ);
                }
            }
        });*/
    }

    public void setDSZLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            dszAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
//            dszAdapter=new DSZAdapter(context,listData);
//            pm.lvr_dsz.setAdapter(dszAdapter);
                       /* ddsAdapter=new DDSAdapter(context,listData);
            pm.lv_dds.setAdapter(ddsAdapter);*/
            Log.e("MViewManager","DSZLayout Enter"+listData.size());
            if(dszAdapter==null){
                dszAdapter=new DSZAdapter(context,listData);
                dszAdapter.setMOnItemClickListener(new DSZAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) dszAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskDSZ(selectTask);
                        }

                        String isRead=selectTask.getAsString(TaskDS.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                    TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailDSZ.class,pm,ParamType.DSZ));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailDSZ.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                dszAdapter.setDataList(listData);
                Log.e("MViewManager","DSZsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_dsz.setAdapter(dszAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int dszindex=sp.getInt("dszindex",0);
            int dsztop=sp.getInt("dsztop",0);
            pm.lv_dsz.setSelectionFromTop(dszindex,dsztop);

            /*int[] indexs=pm.lvr_dds.getStorePosition();
            pm.lvr_dds.setSelectionFromTop(indexs[0],indexs[1]);*/
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.DSZ);
        }else{
            pm.setNoDataView(false,ParamType.DSZ);
        }

        /*if(dszAdapter==null){
            dszAdapter=new DSZAdapter(context,listData);
            pm.lv_dsz.setAdapter(dszAdapter);
        }else{
            dszAdapter.notifyDataSetChanged();
        }*/


/*        dszAdapter.setMOnItemClickListener(new DSZAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) dszAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDSZ(selectTask);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailDSZ.class,pm,ParamType.DSZ));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailDSZ.class,DetailIntentType.READ);
                }
            }
        });*/
    }

    public void setYDSLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            ydsAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
//            ydsAdapter=new YDSAdapter(context,listData);
//            pm.lvr_yds.setAdapter(ydsAdapter);
            if(ydsAdapter==null){
                ydsAdapter=new YDSAdapter(context,listData);
                ydsAdapter.setMOnItemClickListener(new YDSAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) ydsAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskYDS(selectTask);
                        }

                        String isRead=selectTask.getAsString(TaskDS.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                    TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailYDS.class,pm,ParamType.YDS));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailYDS.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                ydsAdapter.setDataList(listData);
                Log.e("MViewManager","YDSsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_yds.setAdapter(ydsAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int ydsindex=sp.getInt("ydsindex",0);
            int ydstop=sp.getInt("ydstop",0);
            pm.lv_yds.setSelectionFromTop(ydsindex,ydstop);

            /*int[] indexs=pm.lvr_dds.getStorePosition();
            pm.lvr_dds.setSelectionFromTop(indexs[0],indexs[1]);*/
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.YDS);
        }else{
            pm.setNoDataView(false,ParamType.YDS);
        }

        /*if(ydsAdapter==null){
            ydsAdapter=new YDSAdapter(context,listData);
            pm.lv_yds.setAdapter(ydsAdapter);
        }else{
            ydsAdapter.notifyDataSetChanged();
        }*/


/*        ydsAdapter.setMOnItemClickListener(new YDSAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) ydsAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskYDS(selectTask);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailYDS.class,pm,ParamType.YDS));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailYDS.class,DetailIntentType.READ);
                }
            }
        });*/
    }
    public void setHPLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            hpAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
          /*  hpAdapter=new HPAdapter(context,listData);
            pm.lvr_hp.setAdapter(hpAdapter);*/
            if(hpAdapter==null){
                hpAdapter=new HPAdapter(context,listData);
                hpAdapter.setMOnItemClickListener(new HPAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) hpAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskHP(selectTask);
                        }
                        String isRead=selectTask.getAsString(TaskDS.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                    TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailHP.class,pm,ParamType.HP));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailHP.class,DetailIntentType.READ);
                        }
                    }
                });
            }else{
                hpAdapter.setDataList(listData);
                Log.e("MViewManager","YDSsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_hp.setAdapter(hpAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int hpindex=sp.getInt("hpindex",0);
            int hptop=sp.getInt("hptop",0);
            pm.lv_hp.setSelectionFromTop(hpindex,hptop);
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.HP);
        }else{
            pm.setNoDataView(false,ParamType.HP);
        }

        /*if(hpAdapter==null){
            hpAdapter=new HPAdapter(context,listData);
            pm.lv_hp.setAdapter(hpAdapter);
        }else{
            hpAdapter.notifyDataSetChanged();
        }*/



        hpAdapter.setMOnItemClickListener(new HPAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) hpAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskHP(selectTask);
                }
                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(context,DetailHP.class,pm,ParamType.HP));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailHP.class,DetailIntentType.READ);
                }
            }
        });
    }
    public void setRSWORKLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            rsworkAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
/*            rsworkAdapter=new RSWORKAdapter(context,listData);
            pm.lvr_rswork.setAdapter(rsworkAdapter);*/
            if(rsworkAdapter==null){
                rsworkAdapter=new RSWORKAdapter(context,listData);
                rsworkAdapter.setMOnItemClickListener(new RSWORKAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) rsworkAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskRSWORK(selectTask);
                        }
                        //人伤有isread
                        String isRead=selectTask.getAsString(Taskhurt.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                    TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(context,DetailRSWORK.class,pm,ParamType.GZ));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailRSWORK.class,DetailIntentType.READ);
                        }
                        //pm.startActivity(DetailRSWORK.class,DetailIntentType.READ);
                    }
                });
            }else{
                rsworkAdapter.setDataList(listData);
                Log.e("MViewManager","YDSsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_gz.setAdapter(rsworkAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int gzindex=sp.getInt("gzindex",0);
            int gztop=sp.getInt("gztop",0);
            pm.lv_gz.setSelectionFromTop(gzindex,gztop);
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.GZ);
        }else{
            pm.setNoDataView(false,ParamType.GZ);
        }

        /*if(rsworkAdapter==null){
            rsworkAdapter=new RSWORKAdapter(context,listData);
            pm.lv_rswork.setAdapter(rsworkAdapter);
        }else{
            rsworkAdapter.notifyDataSetChanged();
        }*/


/*        rsworkAdapter.setMOnItemClickListener(new RSWORKAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) rsworkAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskRSWORK(selectTask);
                }
                //人伤有isread
                String isRead=selectTask.getAsString(Taskhurt.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(context,DetailRSWORK.class,pm,ParamType.GZ));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailRSWORK.class,DetailIntentType.READ);
                }
                //pm.startActivity(DetailRSWORK.class,DetailIntentType.READ);
            }
        });*/
    }
    public void setRSHISLayout(final Context context, ArrayList<ContentValues> listData,int loadtype, final ProcessMain pm){
        if(loadtype==LoadType.LOADMORE){
            rshisAdapter.notifyDataSetChanged();
        }else if(loadtype==LoadType.REFRESH){
/*            rshisAdapter=new RSHISAdapter(context,listData);
            pm.lvr_rshistroy.setAdapter(rshisAdapter);*/
            if(rshisAdapter==null){
                rshisAdapter=new RSHISAdapter(context,listData);
                rshisAdapter.setMOnItemClickListener(new RSHISAdapter.MOnItemClickListener() {
                    @Override
                    public void onClick(int id) {
                        Log.i("MViewManager","selece position:"+id);
                        ContentValues selectTask= (ContentValues) rshisAdapter.getItem(id);
                        if(selectTask!=null){
                            SelectedTask.storeTaskRSHIS(selectTask);
                        }
                        String isRead=selectTask.getAsString(Taskhurt.isRead);
                        if(isRead.equals("0")){//未读,调用设置已读接口
                            pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                    TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(context,DetailRSHIS.class,pm,ParamType.GZ));
                        }else if(isRead.equals("1")){//已读
                            pm.startActivity(DetailRSHIS.class,DetailIntentType.READ);
                        }
                        //pm.startActivity(DetailRSHIS.class,DetailIntentType.READ);
                    }
                });
            }else{
                rshisAdapter.setDataList(listData);
                Log.e("MViewManager","RSHISsetDataList Enter listDataLength:"+listData.size());
            }
            pm.lv_ls.setAdapter(rshisAdapter);
            SharedPreferences sp=context.getSharedPreferences("loadMore",Context.MODE_PRIVATE);
            int lsindex=sp.getInt("lsindex",0);
            int lstop=sp.getInt("lstop",0);
            pm.lv_ls.setSelectionFromTop(lsindex,lstop);
        }

        if(listData.size()==0){//是否显示无数据页面
            pm.setNoDataView(true,ParamType.LS);
        }else{
            pm.setNoDataView(false,ParamType.LS);
        }

       /* if(rshisAdapter==null){
            rshisAdapter=new RSHISAdapter(context,listData);
            pm.lv_rshistroy.setAdapter(rshisAdapter);
        }else{
            rshisAdapter.notifyDataSetChanged();
        }*/


/*        rshisAdapter.setMOnItemClickListener(new RSHISAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id) {
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) rshisAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskRSHIS(selectTask);
                }
                String isRead=selectTask.getAsString(Taskhurt.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    pm.setTaskReadHttp(pm.userManager.getUserToken(),pm.userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(context,DetailRSHIS.class,pm,ParamType.GZ));
                }else if(isRead.equals("1")){//已读
                    pm.startActivity(DetailRSHIS.class,DetailIntentType.READ);
                }
                //pm.startActivity(DetailRSHIS.class,DetailIntentType.READ);
            }
        });*/
    }
//为DCK和YCK设置流程追踪
    public void setRiskListView(final Context context, ArrayList<ContentValues> listData, final MBaseActivity detailClass){
        final RiskAdapter riskAdapter=new RiskAdapter(context,listData);
        if(detailClass instanceof DetailDCK){
            final DetailDCK detailDCK=(DetailDCK) detailClass;
            detailDCK.dck_detail_risklist.setAdapter(riskAdapter);
            riskAdapter.setMOnItemClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);
                    }
                }
            });

            riskAdapter.setMOnItemdetailClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);


                    }
                    //to:风险上报详情
                    detailDCK.startActivity(FxsbDetailActivity.class,DetailIntentType.READ);
                }
            });
        }else if(detailClass instanceof DetailYCK){
            final DetailYCK detailYCK=(DetailYCK) detailClass;
            detailYCK.yck_detail_risklist.setAdapter(riskAdapter);
            riskAdapter.setMOnItemClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);
                    }
                }
            });

            riskAdapter.setMOnItemdetailClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);


                    }
                    //to:风险上报详情
                    detailYCK.startActivity(FxsbDetailActivity.class,DetailIntentType.READ);
                }
            });
        }else if(detailClass instanceof DetailYDS){
            final DetailYDS detailYDS=(DetailYDS) detailClass;
            detailYDS.yds_detail_risklist.setAdapter(riskAdapter);
            riskAdapter.setMOnItemClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);
                    }
                }
            });

            riskAdapter.setMOnItemdetailClickListener(new RiskAdapter.MOnItemClickListener() {//似乎没有必要点击
                @Override
                public void onClick(int id) {
                    Log.i("MViewManager","selece position:"+id);
                    ContentValues selectTask= (ContentValues) riskAdapter.getItem(id);
                    if(selectTask!=null){
                        SelectedTask.storeTaskRisk(selectTask);


                    }
                    //to:风险上报详情
                    detailYDS.startActivity(FxsbDetailActivity.class,DetailIntentType.READ);
                }
            });
        }

    }

    public void setSpinnerAdapter(final Context context, ArrayList<ContentValues> listData, final DSappointment asappointment){
        asappointment.areanameArrayList=new ArrayList<String>();
        asappointment.areanameArrayList=AreaUtil.getAreaNames(listData);
        DSArea.dsAreaObjArray=AreaUtil.formatAreaData(listData);
        //asappointment.setSpinnerAdapter(context,asappointment.dsyy_edit_areaname_spinner,asappointment.areaname_spinnerAdapter,asappointment.areanameArrayList);
        //设置areanameAdapter
        asappointment.areaname_spinnerAdapter=new ArrayAdapter<String>(context,R.layout.hc_simple_list_item,asappointment.areanameArrayList);
        asappointment.dsyy_edit_areaname_spinner.setAdapter(asappointment.areaname_spinnerAdapter);

        asappointment.dsyy_edit_areaname_spinner.setSelection(asappointment.areaname_Select_int, true);
        asappointment.dsyy_edit_areaname_spinner.setOnItemSelectedListener(asappointment.areanameOnItemSelectedListener);
        //进入页面不会调用onItemSelected
        String area_name=asappointment.areanameArrayList.get(asappointment.areaname_Select_int);
        asappointment.assess_nameArrayList=new ArrayList<String>();
        for(int i=0;i< DSArea.dsAreaObjArray.size();i++){
            AreaObj areaObj=DSArea.dsAreaObjArray.get(i);
            if(areaObj.getName().equals(area_name)){
                asappointment.assess_nameArrayList=areaObj.getAssess_arraylist();
                break;
            }
        }
        //设置spinner当前选值
        asappointment.areaname_selected=asappointment.areanameArrayList.get(asappointment.areaname_Select_int);
        asappointment.assess_selected=asappointment.assess_nameArrayList.get(asappointment.assess_name_Select_int);
        asappointment.case_from_seleced="1";
        asappointment.setSpinnerAdapter(context,asappointment.dsyy_edit_assess_name_spinner,asappointment.assess_name_spinnerAdapter,asappointment.assess_nameArrayList);
        //asappointment.dsyy_edit_assess_name_spinner.setOnItemSelectedListener(asappointment.assess_nameOnItemSelectedListener);

    }

    public void finishAllAdapter(){//销毁所有adapter
        if(dckAdapter!=null){
            dckAdapter=null;
        }
        if(yckAdapter!=null){
            yckAdapter=null;
        }
        if(ddsAdapter!=null){
            ddsAdapter=null;
        }
        if(dszAdapter!=null){
            dszAdapter=null;
        }
        if(ydsAdapter!=null){
            ydsAdapter=null;
        }
        if(hpAdapter!=null){
            hpAdapter=null;
        }
        if(rsworkAdapter!=null){
            rsworkAdapter=null;
        }
        if(rshisAdapter!=null){
            rshisAdapter=null;
        }

    }



}
