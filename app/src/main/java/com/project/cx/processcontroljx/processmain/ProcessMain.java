package com.project.cx.processcontroljx.processmain;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.cx.processcontroljx.R;
import com.project.cx.processcontroljx.adapters.DCKAdapter;
import com.project.cx.processcontroljx.adapters.DDSAdapter;
import com.project.cx.processcontroljx.adapters.DSZAdapter;
import com.project.cx.processcontroljx.adapters.HPAdapter;
import com.project.cx.processcontroljx.adapters.MainPageAdapter;
import com.project.cx.processcontroljx.adapters.RSHISAdapter;
import com.project.cx.processcontroljx.adapters.RSWORKAdapter;
import com.project.cx.processcontroljx.adapters.YCKAdapter;
import com.project.cx.processcontroljx.adapters.YDSAdapter;
import com.project.cx.processcontroljx.beans.BottomIndex;
import com.project.cx.processcontroljx.beans.BusRefreshAct;
import com.project.cx.processcontroljx.beans.CurrentBottom;
import com.project.cx.processcontroljx.beans.DetailIntentType;
import com.project.cx.processcontroljx.beans.FilterState;
import com.project.cx.processcontroljx.beans.FrontRole;
import com.project.cx.processcontroljx.beans.LoadType;
import com.project.cx.processcontroljx.beans.ParamType;
import com.project.cx.processcontroljx.beans.SelectedTask;
import com.project.cx.processcontroljx.beans.TaskCK;
import com.project.cx.processcontroljx.beans.TaskDS;
import com.project.cx.processcontroljx.beans.TaskRole;
import com.project.cx.processcontroljx.beans.Taskhurt;
import com.project.cx.processcontroljx.beans.UnReadCounts;
import com.project.cx.processcontroljx.net.MHttpParams;
import com.project.cx.processcontroljx.net.MSocketHelper;
import com.project.cx.processcontroljx.net.OkhttpDataHandler;
import com.project.cx.processcontroljx.settings.SystemSetActivity;
import com.project.cx.processcontroljx.taskdetail.DetailDCK;
import com.project.cx.processcontroljx.taskdetail.DetailDDS;
import com.project.cx.processcontroljx.taskdetail.DetailDSZ;
import com.project.cx.processcontroljx.taskdetail.DetailHP;
import com.project.cx.processcontroljx.taskdetail.DetailRSHIS;
import com.project.cx.processcontroljx.taskdetail.DetailRSWORK;
import com.project.cx.processcontroljx.taskdetail.DetailYCK;
import com.project.cx.processcontroljx.taskdetail.DetailYDS;
import com.project.cx.processcontroljx.theme.MBaseActivity;
import com.project.cx.processcontroljx.ui.IconCenterEditText;
import com.project.cx.processcontroljx.ui.LoadMoreListView;
import com.project.cx.processcontroljx.ui.PopWin_Left;
import com.project.cx.processcontroljx.utils.AppManager;
import com.project.cx.processcontroljx.utils.BusUtil;
import com.project.cx.processcontroljx.utils.MViewManager;
import com.project.cx.processcontroljx.utils.MetricUtil;
import com.project.cx.processcontroljx.utils.OkCallbackManager;
import com.project.cx.processcontroljx.utils.SearchHelper;
import com.project.cx.processcontroljx.utils.UserManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.squareup.otto.Subscribe;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.emitter.Emitter;
import okhttp3.Callback;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class ProcessMain extends MBaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {
    public ViewPager mViewPager;
    private ArrayList<View> mArrayList;
    private Context mContext;
    public UserManager userManager;
    public String frontRole;

    public View v_dck,v_yck,v_dds,v_all;
    public View v_dsz,v_yds,v_hp;
    public View v_rswork,v_rshistroy;

    //public ListView lv_dck,lv_yck,lv_dds,lv_all;
    //public ListView lv_dck,lv_dds,lv_all;
    public LoadMoreListView lvr_yck,lvr_dds;
    public RefreshLayout lvrf_dck,lvrf_yck,lvrf_dds,lvrf_dsz,lvrf_yds,lvrf_hp,lvrf_gz,lvrf_ls;
    public ListView lv_dck,lv_yck,lv_dds,lv_dsz,lv_yds,lv_hp,lv_gz,lv_ls;

//    public ListView lv_dsz,lv_yds,lv_hp;
//    public ListView lv_rswork,lv_rshistroy;
    public LoadMoreListView lvr_dsz,lvr_yds,lvr_hp;
    public LoadMoreListView lvr_rswork,lvr_rshistroy;

    //public RecyclerView lv_dckR;

    //public XRefreshView xrv_dck,xrv_yck,xrv_dds,xrv_all;
    //public XRefreshView xrv_dck,xrv_dds,xrv_all;
    //public SwipeRefreshLayout srf_yck;

    //public XRefreshView xrv_dsz,xrv_yds,xrv_hp;
    //public XRefreshView xrv_gz,xrv_ls;

    public int refreshStart=0;
    //public int refreshLimit=10;
    public int refreshLimit_search=10;
    public int refreshLimit_dck=10;
    public int refreshLimit_yck=10;
    public int refreshLimit_dds=10;
    public int refreshLimit_dsz=10;
    public int refreshLimit_yds=10;
    public int refreshLimit_hp=10;
    public int refreshLimit_gz=10;
    public int refreshLimit_ls=10;


    public int loadLimit=10;

    public int loadStart_dck=0;
    public int loadStart_yck=0;
    public int loadStart_dds=0;
    public int loadStart_dsz=0;
    public int loadStart_yds=0;
    public int loadStart_hp=0;
    public int loadStart_gz=0;
    public int loadStart_ls=0;

    public ArrayList<ContentValues> mArray_=new ArrayList<ContentValues>();

    //底部组件
    private RelativeLayout  touch_dck,touch_yck,touch_dds,
                            touch_dsz,touch_yds,touch_hp,touch_all,
                            touch_rswork,touch_rshistroy;

    public TextView    text_dck,text_yck,text_dds,
                        text_dsz,text_yds,text_hp,text_all,
                        text_rswork,text_rshistroy;
    public TextView     bar_num1,bar_num2,bar_num3,bar_num4,bar_num5,
                        bar_num6,bar_num7,bar_num8,bar_num9;

    private ImageView   img_dck,img_yck,img_dds,
                        img_dsz,img_yds,img_hp,img_all,
                        img_rswork,img_rshistroy;

    private String currentBottom;//当前底部
    private boolean hasGetCount=false;//是否已经调用getTaskCount,在processmain没有被销毁的情况只调用一次

    //顶部组件
    private TextView process_title;
    private Button filter_btn;
    private PopWin_Left popWin_left;
    private Button process_systemsetting;
    public IconCenterEditText cur_search_et2;

    private final String TAG=getClass().getSimpleName();

    //保存获取的数据
    public ArrayList<ContentValues> arrayList_dck,arrayList_yck,arrayList_dds,arrayList_all,
                                     arrayList_dsz,arrayList_yds,arrayList_hp,arrayList_rswork,arrayList_rshis;

    //RelativeLayout noReportRL;//无数据界面

    io.socket.client.Socket mSocket=null;

    //存储权限
    private final int STORAGE_PERMISSION=22;


    //selectionPosition
   // public int scrolledX=0,scrolledY=0;
//    public int mindex=0,mtop=0;
    public int dckIndex=0,dckTop=0;
    public int yckIndex=0,yckTop=0;
    public int ddsIndex=0,ddsTop=0;
    public int dszIndex=0,dszTop=0;
    public int ydsIndex=0,ydsTop=0;
    public int hpIndex=0,hpTop=0;
    public int rsworkIndex=0,rsworkTop=0;
    public int rshisIndex=0,rshisTop=0;

    public DCKAdapter mdckAdapter;
    public YCKAdapter myckAdapter;
    public DDSAdapter mddsAdapter;
    public DSZAdapter mdszAdapter;
    public YDSAdapter mydsAdapter;
    public HPAdapter  mhpAdapter;
    public RSWORKAdapter mrsworkAdapter;
    public RSHISAdapter mrshisAdapter;
    public ArrayList<ContentValues> initialList=new ArrayList<>();

    Emitter.Listener msgListener=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("ProcessMain","server is new_msg");
                    /*try{
                        JSONObject dataGet=new JSONObject(args[0].toString());
                    }catch(){

                    }*/
            String toUser="";
            String eventtype="";
            if(args[0]!=null){//jsonobject转换
                Log.e("ProcessMain","args[0]"+args[0].toString());
                try{
                    JSONObject dataGet=new JSONObject(args[0].toString());
                    toUser=dataGet.getString("toUser");
                    eventtype=dataGet.getString("eventype");
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }else{
                Log.e("ProcessMain","args[0]=null");
            }
            if(toUser!="" && toUser.equals(userManager.getUserName())){
                final String finalEventtype = eventtype;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String taskTypeName=MSocketHelper.getTaskTypeName(finalEventtype);
                        Toast.makeText(mContext,"您有新的"+taskTypeName+"消息",Toast.LENGTH_SHORT).show();//未读数量+1
//                        bar_num3.setTextColor(Color.parseColor("#00FF00"));
//                        bar_num3.setText("3");
                                if(finalEventtype !=null && finalEventtype.length()>0){
                                    String taskType=MSocketHelper.getTaskType(finalEventtype);
                                    int counts= UnReadCounts.getCount(taskType);
                                    UnReadCounts.setCount(taskType,counts+1);
                                    Log.e("processmain","counts+1:"+UnReadCounts.getCount(taskType));



                                    Log.e("processmain","counts+2:"+String.valueOf(3));
                                    Log.e("processmain","counts+3:"+bar_num3.getText().toString());

                                    updateCountView();

                                    if(currentBottom.equals(taskType)){
                                        if(taskType.equals(ParamType.DCK) ||taskType.equals(ParamType.YCK) ){
                                            getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(),taskType,"","","","",loadStart_dck,loadLimit,
                                                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,taskType,ProcessMain.this));
                                        }else if(taskType.equals(ParamType.DDS) ||taskType.equals(ParamType.DSZ) || taskType.equals(ParamType.YDS) ||taskType.equals(ParamType.HP)){
                                            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,"",loadStart_dds,loadLimit,
                                                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));
                                        }
                                    }
                                }else{
                                    if(finalEventtype==null){
                                        Log.e("ProcessMain","finalEventType is null");
                                    }
                                }
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Main","onCreate enter");
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_processmain);
        mContext=this;
        initData();//must before initView
        initView();

        MHttpParams mHttpParams= MHttpParams.getInstance();
        mHttpParams.init(mContext);
        String socketUrl="http://"+mHttpParams.getIP()+":1017/";
        Log.e("pm","socketUrl:"+socketUrl);
        MSocketHelper.getInstance().init(socketUrl,userManager.getUserToken());
        mSocket=MSocketHelper.getInstance().getMSocket();
        if(mSocket.hasListeners("new_msg")){
            mSocket.off();
            InitSocketListener();
        }else{
            InitSocketListener();//on事件监听
        }
    }

    private void toRequestPermission() {
        toRequestStorage();
    }

    public void  toRequestStorage(){
        MPermissions.requestPermissions(ProcessMain.this,STORAGE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(STORAGE_PERMISSION)
    public void requestSuccess(){
       //
    }

    @PermissionDenied(STORAGE_PERMISSION)
    public void requestFail(){
        //
        Toast.makeText(mContext,"请开启存储权限",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        Log.e("Main","onPause enter");
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {//刷新当前的任务列表?
        Log.e("Main","onResume enter");
        super.onResume();
        toRequestPermission();
        if(userManager==null){
            return;
        }
       updateCountView();
        //refreshCurrentPage();
        if(!hasGetCount){
            getTaskCountHttpData(userManager.getUserToken(),userManager.getFrontRole(),
                    OkCallbackManager.getInstance().getTaskCountCallback(mContext,userManager.getFrontRole(),ProcessMain.this));
            hasGetCount=true;
        }
        Log.i(TAG,"onResume");
    }
    @Override
    protected void onStart() {
        Log.e("Main","onStart enter");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e("Main","onStop enter");
        super.onStop();
    }

    private void initData() {
        //获取用户信息
        userManager=UserManager.getInstance();
        userManager.init(mContext);
        frontRole=userManager.getFrontRole();
        BusUtil.getINSTANCE().register(this);
    }

    private void initView() {
        mViewPager=(ViewPager)findViewById(R.id.mViewPager);
        //noReportRL= (RelativeLayout) findViewById(R.id.noReportRL);//无数据界面
        initAdapter();
        initTitle();
        initBottom();//must before setpage
        setPage();
        getFirstData();

    }

    private void initAdapter() {
        mdckAdapter=new DCKAdapter(this,initialList);
        mdckAdapter.setMOnItemClickListener(new DCKAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id,View v) {
                Log.i("MViewManager","selece position:"+id);
                //点击时改变new_tag
                ImageView tag= (ImageView) v.findViewById(R.id.dck_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mdckAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDCK(selectTask);
                    SelectedTask.storeView(v);//存储被选中的view;
                }
                String isRead=selectTask.getAsString(TaskCK.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口,!需要注意刷新的时机
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                            TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailDCK.class,ProcessMain.this,ParamType.DCK));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailDCK.class,DetailIntentType.READ);
                }
            }
        });

        myckAdapter=new YCKAdapter(this,initialList);
        myckAdapter.setMOnItemClickListener(new YCKAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id,View v) {
                Log.i("MViewManager","selece position:"+id);
                //点击时改变new_tag
                ImageView tag= (ImageView) v.findViewById(R.id.yck_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) myckAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskYCK(selectTask);
                    SelectedTask.storeView(v);
                }

                String isRead=selectTask.getAsString(TaskCK.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskCK.id),
                            TaskRole.ck,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailYCK.class,ProcessMain.this,ParamType.YCK));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailYCK.class,DetailIntentType.READ);
                }
            }
        });

        mddsAdapter=new DDSAdapter(this,initialList);
        mddsAdapter.setMOnItemClickListener(new DDSAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id,View v) {
                //Detail对应修改
                ContentValues values=(ContentValues) mddsAdapter.getItem(id);
                String assessorNo=values.getAsString(TaskDS.assessorNo);
                if(UserManager.getInstance().getJobNo().equals(assessorNo)){//newTag标签的显示隐藏
                    ImageView tag= (ImageView) v.findViewById(R.id.dds_new_tag);
                    tag.setVisibility(View.GONE);
                }
                Log.i("MViewManager","selece position:"+id);
                ContentValues selectTask= (ContentValues) mddsAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDDS(selectTask);
                    SelectedTask.storeView(v);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    //需要判断该待定损任务是否属于自己
                    if(!selectTask.getAsString(TaskDS.assessorNo).equals(userManager.getJobNo())){
                        //不是自己的任务不调用setTaskRead
                        startActivity(DetailDDS.class, DetailIntentType.UNREAD);
                    }else{
                        setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                                TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailDDS.class,ProcessMain.this,ParamType.DDS));
                    }
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailDDS.class,DetailIntentType.READ);
                }
            }
        });

        mdszAdapter=new DSZAdapter(this,initialList);
        mdszAdapter.setMOnItemClickListener(new DSZAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id,View v) {
                Log.i("MViewManager","selece position:"+id);
                ImageView tag= (ImageView) v.findViewById(R.id.dsz_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mdszAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskDSZ(selectTask);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailDSZ.class,ProcessMain.this,ParamType.DSZ));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailDSZ.class,DetailIntentType.READ);
                }
            }
        });

        mydsAdapter=new YDSAdapter(this,initialList);
        mydsAdapter.setMOnItemClickListener(new YDSAdapter.MOnItemClickListener() {
            @Override
            public void onClick(int id,View v) {
                Log.i("MViewManager","selece position:"+id);
                ImageView tag= (ImageView) v.findViewById(R.id.yds_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mydsAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskYDS(selectTask);
                    SelectedTask.storeView(v);
                }

                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailYDS.class,ProcessMain.this,ParamType.YDS));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailYDS.class,DetailIntentType.READ);
                }
            }
        });
        mhpAdapter=new HPAdapter(this,initialList);
        mhpAdapter.setMOnItemClickListener(new HPAdapter.MOnItemClickListener() {
            @Override
            public void onClick(View v,int id) {
                Log.i("MViewManager","selece position:"+id);
                ImageView tag= (ImageView) v.findViewById(R.id.hp_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mhpAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskHP(selectTask);
                }
                String isRead=selectTask.getAsString(TaskDS.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.ds,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailHP.class,ProcessMain.this,ParamType.HP));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailHP.class,DetailIntentType.READ);
                }
            }
        });

        mrsworkAdapter=new RSWORKAdapter(this,initialList);
        mrsworkAdapter.setMOnItemClickListener(new RSWORKAdapter.MOnItemClickListener() {
            @Override
            public void onClick(View v,int id) {
                Log.i("MViewManager","selece position:"+id);
                ImageView tag= (ImageView) v.findViewById(R.id.rswork_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mrsworkAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskRSWORK(selectTask);
                }
                //人伤有isread
                String isRead=selectTask.getAsString(Taskhurt.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailRSWORK.class,ProcessMain.this,ParamType.GZ));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailRSWORK.class,DetailIntentType.READ);
                }
            }
        });

        mrshisAdapter=new RSHISAdapter(this,initialList);
        mrshisAdapter.setMOnItemClickListener(new RSHISAdapter.MOnItemClickListener() {
            @Override
            public void onClick(View v,int id) {
                Log.i("MViewManager","selece position:"+id);
                ImageView tag= (ImageView) v.findViewById(R.id.rshis_new_tag);
                tag.setVisibility(View.GONE);
                ContentValues selectTask= (ContentValues) mrshisAdapter.getItem(id);
                if(selectTask!=null){
                    SelectedTask.storeTaskRSHIS(selectTask);
                }
                String isRead=selectTask.getAsString(Taskhurt.isRead);
                if(isRead.equals("0")){//未读,调用设置已读接口
                    setTaskReadHttp(userManager.getUserToken(),userManager.getFrontRole(),selectTask.getAsString(TaskDS.id),
                            TaskRole.rs,OkCallbackManager.getInstance().getReadCallback(ProcessMain.this,DetailRSHIS.class,ProcessMain.this,ParamType.GZ));
                }else if(isRead.equals("1")){//已读
                    startActivity(DetailRSHIS.class,DetailIntentType.READ);
                }
                //pm.startActivity(DetailRSHIS.class,DetailIntentType.READ);
            }
        });
    }

    private void getFirstData(){//进入后，默认页面的数据来源,selectpage没有触发
        if(frontRole.equals(String.valueOf(FrontRole.CK))){
            getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DCK,"","",
                    "","",refreshStart,refreshLimit_dck,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DCK,ProcessMain.this));
        }else if(frontRole.equals(String.valueOf(FrontRole.DS))){
            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,"",refreshStart,refreshLimit_dds,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));
        }else if(frontRole.equals(String.valueOf(FrontRole.CK_DS))){
            getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DCK,"","","",
                    "",refreshStart,refreshLimit_dck,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DCK,ProcessMain.this));
        }else if(frontRole.equals(String.valueOf(FrontRole.RS))){
            getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.GZ,"",refreshStart,refreshLimit_gz,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.GZ,ProcessMain.this));
        }
    }

    private void initTitle(){
        cur_search_et2= (IconCenterEditText) findViewById(R.id.cur_search_et2);
        cur_search_et2.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                String s=cur_search_et2.getText().toString().trim();
                Log.i(TAG,"currentBottom:"+currentBottom);
                ContentValues type= SearchHelper.getType(currentBottom);
                if(type.containsKey("CK")){
                    String paramType=type.getAsString("CK");
                    getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(),paramType,"","",
                            "",s,refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                }else if(type.containsKey("DS")){
                    String paramType=type.getAsString("DS");
                    getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(),paramType,s,refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                }else if(type.containsKey("RS")){//人伤接口也需要keyword
                    String paramType=type.getAsString("RS");
                    getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(),paramType,s,refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                }
            }
        });

        cur_search_et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length=cur_search_et2.getText().toString().length();
                if(length==0){//重置keyword
                    ContentValues type= SearchHelper.getType(currentBottom);
                    if(type.containsKey("CK")){
                        String paramType=type.getAsString("CK");
                        getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(),paramType,"","",
                                "","",refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                    }else if(type.containsKey("DS")){
                        String paramType=type.getAsString("DS");
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(),paramType,"",refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                    }else if(type.containsKey("RS")){//人伤接口也需要keyword
                        String paramType=type.getAsString("RS");
                        getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(),paramType,"",refreshStart,refreshLimit_search,OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,paramType,ProcessMain.this));
                    }
                }
            }
        });

        process_systemsetting= (Button) findViewById(R.id.process_systemsetting);
        process_systemsetting.setOnClickListener(this);
    }

    //初始化底部样式
    private void initBottom(){
        touch_dck= (RelativeLayout)findViewById(R.id.touch_dck);
        touch_yck= (RelativeLayout)findViewById(R.id.touch_yck);
        touch_dds= (RelativeLayout)findViewById(R.id.touch_dds);
        touch_dsz= (RelativeLayout)findViewById(R.id.touch_dsz);
        touch_yds= (RelativeLayout)findViewById(R.id.touch_yds);
        touch_hp= (RelativeLayout)findViewById(R.id.touch_hp);
        touch_all= (RelativeLayout)findViewById(R.id.touch_all);
        touch_rswork= (RelativeLayout)findViewById(R.id.touch_rswork);
        touch_rshistroy= (RelativeLayout)findViewById(R.id.touch_rshistroy);

        //设置底部点击事件
        touch_dck.setOnClickListener(ProcessMain.this);
        touch_yck.setOnClickListener(ProcessMain.this);
        touch_dds.setOnClickListener(ProcessMain.this);
        touch_dsz.setOnClickListener(ProcessMain.this);
        touch_yds.setOnClickListener(ProcessMain.this);
        touch_hp.setOnClickListener(ProcessMain.this);
        touch_all.setOnClickListener(ProcessMain.this);
        touch_rswork.setOnClickListener(ProcessMain.this);
        touch_rshistroy.setOnClickListener(ProcessMain.this);

        text_dck= (TextView) findViewById(R.id.text_dck);
        text_yck= (TextView)findViewById(R.id.text_yck);
        text_dds= (TextView)findViewById(R.id.text_dds);
        text_dsz= (TextView)findViewById(R.id.text_dsz);
        text_yds= (TextView)findViewById(R.id.text_yds);
        text_hp= (TextView)findViewById(R.id.text_hp);
        text_all= (TextView)findViewById(R.id.text_all);
        text_rswork= (TextView)findViewById(R.id.text_rswork_re);
        text_rshistroy= (TextView)findViewById(R.id.text_rshistroy);

        img_dck= (ImageView) findViewById(R.id.img_dck);
        img_yck= (ImageView)findViewById(R.id.img_yck);
        img_dds= (ImageView)findViewById(R.id.img_dds);
        img_dsz= (ImageView)findViewById(R.id.img_dsz);
        img_yds= (ImageView)findViewById(R.id.img_yds);
        img_hp= (ImageView)findViewById(R.id.img_hp);
        img_all= (ImageView)findViewById(R.id.img_all);
        img_rswork= (ImageView)findViewById(R.id.img_rswork);
        img_rshistroy= (ImageView)findViewById(R.id.img_rshistroy);

        //设置未读数量
        bar_num1= (TextView) findViewById(R.id.bar_num1);
        bar_num2= (TextView) findViewById(R.id.bar_num2);
        bar_num3= (TextView) findViewById(R.id.bar_num3);
        bar_num4= (TextView) findViewById(R.id.bar_num4);
        bar_num5= (TextView) findViewById(R.id.bar_num5);
        bar_num6= (TextView) findViewById(R.id.bar_num6);
        bar_num7= (TextView) findViewById(R.id.bar_num7);
        bar_num8= (TextView) findViewById(R.id.bar_num8);
        bar_num9= (TextView) findViewById(R.id.bar_num9);

    }

    // set the view and listview accroding to the usertype
    private void setPage(){
        MViewManager mViewManager=MViewManager.getInstance();
        mArrayList=new ArrayList<View>();
        //top init
        process_title= (TextView) findViewById(R.id.process_title);
        filter_btn=(Button) findViewById(R.id.type_select);
        filter_btn.setOnClickListener(this);

         switch(userManager.getUserType()){
             case FrontRole.CK:

                 mViewManager.initDCK(mContext,true,ProcessMain.this);
                 mViewManager.initYCK(mContext,true,ProcessMain.this);
                 mViewManager.initDDS(mContext,true,ProcessMain.this);
                // mViewManager.initALL(mContext,true,ProcessMain.this);

                 mArrayList.add(v_dck);
                 mArrayList.add(v_yck);
                 mArrayList.add(v_dds);
                // mArrayList.add(v_all);

                 touch_dck.setVisibility(View.VISIBLE);
                 touch_yck.setVisibility(View.VISIBLE);
                 touch_dds.setVisibility(View.VISIBLE);
                // touch_all.setVisibility(View.VISIBLE);

                 //初始化selectstyle
                 currentBottom= CurrentBottom.CK_DCK;
                 setFilerVisiblity(currentBottom);

                 process_title.setText("待查勘");
                 text_dck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                 img_dck.setImageResource(R.drawable.dck_active);

                 //for test
/*                 ArrayList<ContentValues> listData=new ArrayList<ContentValues>();
                 ContentValues value=new ContentValues();
                 value.put(TaskCK.caseTime,"timestamp");
                 value.put(TaskCK.case_state,"case_state");
                 value.put(TaskCK.outNumber,"outnumber");
                 value.put(TaskCK.risklevel,"risklevel");
                 value.put(TaskCK.riskstate,"riskstate");
                 listData.add(value);

                 mViewManager.setDCKLayout(mContext,listData,ProcessMain.this);*/
                 break;
             case FrontRole.DS:
                 mViewManager.initDDS(mContext,true,ProcessMain.this);
                 mViewManager.initDSZ(mContext,true,ProcessMain.this);
                 mViewManager.initYDS(mContext,true,ProcessMain.this);
                 mViewManager.initHP(mContext,true,ProcessMain.this);

                 mArrayList.add(v_dds);
                 mArrayList.add(v_dsz);
                 mArrayList.add(v_yds);
                 mArrayList.add(v_hp);

                 touch_dds.setVisibility(View.VISIBLE);
                 touch_dsz.setVisibility(View.VISIBLE);
                 touch_yds.setVisibility(View.VISIBLE);
                 touch_hp.setVisibility(View.VISIBLE);

                 //初始化selectstyle
                 currentBottom= CurrentBottom.DS_DDS;
                 setFilerVisiblity(currentBottom);

                 process_title.setText("待定损");
                 text_dds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                 img_dds.setImageResource(R.drawable.dds_active);
                 break;
             case FrontRole.RS:
                 mViewManager.initRSWORK(mContext,true,ProcessMain.this);
                 mViewManager.initRSHISTROY(mContext,true,ProcessMain.this);

                 mArrayList.add(v_rswork);
                 mArrayList.add(v_rshistroy);

                 touch_rswork.setVisibility(View.VISIBLE);
                 touch_rshistroy.setVisibility(View.VISIBLE);

                 //初始化selectstyle
                 currentBottom= CurrentBottom.RS_WORK;
                 setFilerVisiblity(currentBottom);

                 process_title.setText("首次跟踪");
                 text_rswork.setTextColor(mContext.getResources().getColor(R.color.orangered));
                 img_rswork.setImageResource(R.drawable.gzlb_active);
                 break;
             case FrontRole.CK_DS:
                 mViewManager.initDCK(mContext,true,ProcessMain.this);
                 mViewManager.initYCK(mContext,true,ProcessMain.this);
                 mViewManager.initDDS(mContext,true,ProcessMain.this);
                 mViewManager.initDSZ(mContext,true,ProcessMain.this);
                 mViewManager.initYDS(mContext,true,ProcessMain.this);
                 mViewManager.initHP(mContext,true,ProcessMain.this);

                 mArrayList.add(v_dck);
                 mArrayList.add(v_yck);
                 mArrayList.add(v_dds);
                 mArrayList.add(v_dsz);
                 mArrayList.add(v_yds);
                 mArrayList.add(v_hp);

                 touch_dck.setVisibility(View.VISIBLE);
                 touch_yck.setVisibility(View.VISIBLE);
                 touch_dds.setVisibility(View.VISIBLE);
                 touch_dsz.setVisibility(View.VISIBLE);
                 touch_yds.setVisibility(View.VISIBLE);
                 touch_hp.setVisibility(View.VISIBLE);

                 //初始化selectstyle
                 currentBottom= CurrentBottom.CK_DCK;
                 setFilerVisiblity(currentBottom);

                 process_title.setText("待查勘");
                 text_dck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                 img_dck.setImageResource(R.drawable.dck_active);
                 break;

             default:
                 break;
         }

        MainPageAdapter mPageAdapter=new MainPageAdapter(mArrayList);
        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //reset BottomStyle
        resetBottom(userManager.getUserType());
        String keyword="";
        switch(userManager.getUserType()){
            case FrontRole.CK:
                switch(position){
                    case BottomIndex.CK_DCK:
                        currentBottom= CurrentBottom.CK_DCK;
                        setFilerVisiblity(currentBottom);
                        resetLoadStart();
                        getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DCK,"","","",
                                keyword,refreshStart,refreshLimit_dck,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DCK,ProcessMain.this));

                        process_title.setText("待查勘");
                        text_dck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dck.setImageResource(R.drawable.dck_active);//selectimg
                        break;
                    case BottomIndex.CK_YCK:
                        currentBottom= CurrentBottom.CK_YCK;
                        setFilerVisiblity(currentBottom);
                        resetLoadStart();
                        getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YCK,"","","",
                                keyword,refreshStart,refreshLimit_yck,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YCK,ProcessMain.this));

                        process_title.setText("已查勘");
                        text_yck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_yck.setImageResource(R.drawable.yck_active);//selectimg
                        break;
                    case BottomIndex.CK_DDS:
                        currentBottom= CurrentBottom.CK_DDS;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,keyword,refreshStart,refreshLimit_dds,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));

                        process_title.setText("待定损");
                        text_dds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dds.setImageResource(R.drawable.dds_active);//selectimg
                        break;
                    case BottomIndex.CK_ALL:
                        resetLoadStart();
                        text_all.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_all.setImageResource(R.drawable.all_active);//selectimg
                        break;
                }
                break;
            case FrontRole.DS:
                switch(position){
                    case BottomIndex.DS_DDS:
                        currentBottom= CurrentBottom.DS_DDS;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,"",refreshStart,refreshLimit_dds,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));

                        process_title.setText("待定损");
                        text_dds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dds.setImageResource(R.drawable.dds_active);//selectimg
                        break;
                    case BottomIndex.DS_DSZ:
                        currentBottom= CurrentBottom.DS_DSZ;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DSZ,"",refreshStart,refreshLimit_dsz,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DSZ,ProcessMain.this));

                        process_title.setText("定损中");
                        text_dsz.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dsz.setImageResource(R.drawable.dsz_active);//selectimg
                        break;
                    case BottomIndex.DS_YDS:
                        currentBottom= CurrentBottom.DS_YDS;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YDS,"",refreshStart,refreshLimit_yds,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YDS,ProcessMain.this));

                        process_title.setText("已定损");
                        text_yds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_yds.setImageResource(R.drawable.yds_active);//selectimg
                        break;
                    case BottomIndex.DS_HP:
                        currentBottom=CurrentBottom.DS_HP;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.HP,"",refreshStart,refreshLimit_hp,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.HP,ProcessMain.this));

                        process_title.setText("获票");
                        text_hp.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_hp.setImageResource(R.drawable.hp_active);//selectimg
                        break;
                }
                break;
            case FrontRole.RS:
                switch(position){
                    case BottomIndex.RS_WORK:
                        currentBottom=CurrentBottom.RS_WORK;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.GZ,"",refreshStart,refreshLimit_gz,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.GZ,ProcessMain.this));
                        process_title.setText("首次跟踪");
                        text_rswork.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_rswork.setImageResource(R.drawable.gzlb_active);//selectimg
                        break;
                    case BottomIndex.RS_HISTROY:
                        currentBottom=CurrentBottom.RS_HISTROY;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.LS,"",refreshStart,refreshLimit_ls,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.LS,ProcessMain.this));
                        process_title.setText("历史列表");
                        text_rshistroy.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_rshistroy.setImageResource(R.drawable.lslb_active);//selectimg
                        break;
                }
                break;
            case FrontRole.CK_DS:
                switch(position){
                    case BottomIndex.CD_DCK:
                        currentBottom=CurrentBottom.CD_DCK;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DCK,"","","","",refreshStart,refreshLimit_dck,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DCK,ProcessMain.this));

                        process_title.setText("待查勘");
                        text_dck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dck.setImageResource(R.drawable.dck_active);//selectimg
                        break;
                    case BottomIndex.CD_YCK:
                        currentBottom=CurrentBottom.CD_YCK;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YCK,"","","","",refreshStart,refreshLimit_yck,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YCK,ProcessMain.this));

                        process_title.setText("已查勘");
                        text_yck.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_yck.setImageResource(R.drawable.yck_active);//selectimg
                        break;
                    case BottomIndex.CD_DDS:
                        currentBottom=CurrentBottom.CD_DDS;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,"",refreshStart,refreshLimit_dds,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));

                        process_title.setText("待定损");
                        text_dds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dds.setImageResource(R.drawable.dds_active);//selectimg
                        break;
                    case BottomIndex.CD_DSZ:
                        currentBottom=CurrentBottom.CD_DSZ;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DSZ,"",refreshStart,refreshLimit_dsz,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DSZ,ProcessMain.this));

                        process_title.setText("定损中");
                        text_dsz.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_dsz.setImageResource(R.drawable.dsz_active);//selectimg
                        break;
                    case BottomIndex.CD_YDS:
                        currentBottom=CurrentBottom.CD_YDS;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YDS,"",refreshStart,refreshLimit_yds,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YDS,ProcessMain.this));

                        process_title.setText("已定损");
                        text_yds.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_yds.setImageResource(R.drawable.yds_active);//selectimg
                        break;
                    case BottomIndex.CD_HP:
                        currentBottom=CurrentBottom.CD_HP;
                        setFilerVisiblity(currentBottom);

                        resetLoadStart();
                        getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.HP,"",refreshStart,refreshLimit_hp,
                                OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.HP,ProcessMain.this));

                        process_title.setText("获票");
                        text_hp.setTextColor(mContext.getResources().getColor(R.color.orangered));
                        img_hp.setImageResource(R.drawable.hp_active);//selectimg
                        break;
                }


                break;
            default:
                break;
        }
    }



    /**
     * reset bottom style
     * @param userType
     */
    public void resetBottom(int userType){
        switch(userType){
            case FrontRole.CK:
                text_dck.setTextColor(Color.BLACK);
                text_yck.setTextColor(Color.BLACK);
                text_dds.setTextColor(Color.BLACK);
                text_all.setTextColor(Color.BLACK);

                img_dck.setImageResource(R.drawable.dck);
                img_yck.setImageResource(R.drawable.yck);
                img_dds.setImageResource(R.drawable.dds);
                img_all.setImageResource(R.drawable.all);
                break;
            case FrontRole.DS:
                text_dds.setTextColor(Color.BLACK);
                text_dsz.setTextColor(Color.BLACK);
                text_yds.setTextColor(Color.BLACK);
                text_hp.setTextColor(Color.BLACK);

                img_dds.setImageResource(R.drawable.dds);
                img_dsz.setImageResource(R.drawable.dsz);
                img_yds.setImageResource(R.drawable.yds);
                img_hp.setImageResource(R.drawable.hp);
                break;
            case FrontRole.RS:
                text_rswork.setTextColor(Color.BLACK);
                text_rshistroy.setTextColor(Color.BLACK);

                img_rswork.setImageResource(R.drawable.gzlb);
                img_rshistroy.setImageResource(R.drawable.lslb);
                break;
            case FrontRole.CK_DS:
                text_dck.setTextColor(Color.BLACK);
                text_yck.setTextColor(Color.BLACK);
                text_dds.setTextColor(Color.BLACK);
                text_dsz.setTextColor(Color.BLACK);
                text_yds.setTextColor(Color.BLACK);
                text_hp.setTextColor(Color.BLACK);

                img_dck.setImageResource(R.drawable.dck);
                img_yck.setImageResource(R.drawable.yck);
                img_dds.setImageResource(R.drawable.dds);
                img_dsz.setImageResource(R.drawable.dsz);
                img_yds.setImageResource(R.drawable.yds);
                img_hp.setImageResource(R.drawable.hp);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //获取查勘数据
    public void getTaskCKData(String token,String frontrole,String type,String lian_state,String risk_state,
                              String risk_level,String keyword,int start,int limit,
                              Callback getCKCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
        okhandler.setmIsShowProgressDialog(true);
        okhandler.getTaskCKHttp(token,frontrole,type,lian_state,risk_state,risk_level,keyword,start,limit,getCKCall);
    }

    //获取定损数据
    public void getTaskDSData(String token,String frontrole,String type,String keyword,int start,int limit,
                              Callback getDSCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
        okhandler.setmIsShowProgressDialog(true);
        okhandler.getTaskDSHttp(token,frontrole,type,keyword,start,limit,getDSCall);
    }

    //获取人伤数据
    public void getTaskhurtData(String token,String frontrole,String type,String keyword,int start,int limit,
                              Callback gethurtCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
        okhandler.setmIsShowProgressDialog(true);
        okhandler.getTaskhurtHttp(token,frontrole,type,keyword,start,limit,gethurtCall);
    }

    //设置任务已读
    public void setTaskReadHttp(String token,String frontrole,String taskid,String task_role,
                                 Callback getReadCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
/*        if(!isFinishing()){//判断activity是否被销毁
            okhandler.setmIsShowProgressDialog(true);
        }*/
        okhandler.setmIsShowProgressDialog(true);
        okhandler.setTaskReadHttp(token,frontrole,taskid,task_role,getReadCall);
    }

    //获取模块任务数量数据
    public void getTaskCountHttpData(String token,String frontrole,
                                Callback getReadCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
        okhandler.setmIsShowProgressDialog(true);
        okhandler.getTaskCountHttp(token,frontrole,getReadCall);
    }

    /*    //风险预警,在详情页调用
    public void getRisksWarnData(String token,String frontrole,String taskid, String task_role,Callback Callback,
                                 Callback getRisksCall){
        OkhttpDataHandler okhandler=new OkhttpDataHandler(mContext);
        okhandler.setmIsShowProgressDialog(true);
        okhandler.getRisksWarnHttp(token,frontrole,taskid,task_role,getRisksCall);
    }*/

    public void startActivity(Class activity,int intentType){
        Intent intent=new Intent(mContext,activity);
        intent.putExtra("intentType",intentType);//intentType 0:未读 1:已读
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.type_select:
                showFilterWindow(mContext);
                break;
            case R.id.touch_dck:
                if(mViewPager!=null){
                    mViewPager.setCurrentItem(BottomIndex.CD_DCK);
                }
                break;
            case R.id.touch_yck:
                if(mViewPager!=null){
                    mViewPager.setCurrentItem(BottomIndex.CD_YCK);
                }
                break;
            case R.id.touch_dds:
                if(mViewPager!=null){
                   if(frontRole.equals(String.valueOf(FrontRole.DS))){//dds=0
                       mViewPager.setCurrentItem(BottomIndex.DS_DDS);
                   }else if(frontRole.equals(String.valueOf(FrontRole.CK)) || frontRole.equals(String.valueOf(FrontRole.CK_DS))){
                       mViewPager.setCurrentItem(BottomIndex.CK_DDS);//CK和CK_DS一样
                   }
                }
                break;
            case R.id.touch_dsz:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.DS))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.DS_DSZ);
                    }else if(frontRole.equals(String.valueOf(FrontRole.CK_DS))){
                        mViewPager.setCurrentItem(BottomIndex.CD_DSZ);//CK和CK_DS一样
                    }
                }
                break;
            case R.id.touch_yds:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.DS))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.DS_YDS);
                    }else if(frontRole.equals(String.valueOf(FrontRole.CK_DS))){
                        mViewPager.setCurrentItem(BottomIndex.CD_YDS);//CK和CK_DS一样
                    }
                }
                break;
            case R.id.touch_hp:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.DS))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.DS_HP);
                    }else if(frontRole.equals(String.valueOf(FrontRole.CK_DS))){
                        mViewPager.setCurrentItem(BottomIndex.CD_HP);//CK和CK_DS一样
                    }
                }
                break;
            case R.id.touch_all:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.CK))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.CK_ALL);
                    }
                }
                break;
            case R.id.touch_rswork:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.RS))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.RS_WORK);
                    }
                }
                break;
            case R.id.touch_rshistroy:
                if(mViewPager!=null){
                    if(frontRole.equals(String.valueOf(FrontRole.RS))){//dds=0
                        mViewPager.setCurrentItem(BottomIndex.RS_HISTROY);
                    }
                }
                break;
            case R.id.process_systemsetting:
                Intent toSystemset=new Intent(ProcessMain.this, SystemSetActivity.class);
                startActivity(toSystemset);
                break;
            default:

                break;
        }
    }
    //重置loadStart,切换时刷新,应当重置loadStart
    public void resetLoadStart(){
        loadStart_dck=0;
        loadStart_yck=0;
        loadStart_dds=0;
        loadStart_dsz=0;
        loadStart_yds=0;
        loadStart_hp=0;
        loadStart_gz=0;
        loadStart_ls=0;
    }

    private void showFilterWindow(Context ctx) {
        int scrW= MetricUtil.getScreenWith(ctx);
        //选中状态
        int lian_state_yes=0,lian_state_no=0,shangbo_yes=0,shangbo_no=0,fengxian_yes=0,fengxian_no=0;
        popWin_left=new PopWin_Left.Builder(ctx).setView(R.layout.dialog_typetip).
                setWidthAndHeight(scrW*2/3,
                        ViewGroup.LayoutParams.MATCH_PARENT) .setAnimationStyle(R.style.AnimHorizontal) .setBackGroundLevel(0.5f)
                .setViewOnclickListener(new PopWin_Left.ViewInterface(){
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        final View viewfinal=view;
                        final TextView typetip_lian_yes,typetip_lian_no,typetip_shangbao_yes,typetip_shangbao_no,
                                 typetip_fengxian_yes,typetip_fengxian_no;
                        final Button type_yes= (Button) view.findViewById(R.id.type_yes);
                        final Button type_reset= (Button) view.findViewById(R.id.type_reset);

                        typetip_lian_yes= (TextView) view.findViewById(R.id.typetip_lian_yes);
                        typetip_lian_no= (TextView) view.findViewById(R.id.typetip_lian_no);
                        typetip_shangbao_yes= (TextView) view.findViewById(R.id.typetip_shangbao_yes);
                        typetip_shangbao_no= (TextView) view.findViewById(R.id.typetip_shangbao_no);
                        typetip_fengxian_yes= (TextView) view.findViewById(R.id.typetip_fengxian_yes);
                        typetip_fengxian_no= (TextView) view.findViewById(R.id.typetip_fengxian_no);
                        //初始化选中状态
                        FilterState.initState(mContext,view,R.id.typetip_lian_yes);
                        FilterState.initState(mContext,view,R.id.typetip_lian_no);
                        FilterState.initState(mContext,view,R.id.typetip_shangbao_yes);
                        FilterState.initState(mContext,view,R.id.typetip_shangbao_no);
                        FilterState.initState(mContext,view,R.id.typetip_fengxian_yes);
                        FilterState.initState(mContext,view,R.id.typetip_fengxian_no);

                        typetip_lian_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_lian_yes.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_lian_yes.setTextColor(Color.RED);
                                    FilterState.lian_yes=1;
                                }else{
                                    typetip_lian_yes.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.lian_yes=0;
                                }

                            }
                        });

                        typetip_lian_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_lian_no.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_lian_no.setTextColor(Color.RED);
                                    FilterState.lian_no=1;
                                }else{
                                    typetip_lian_no.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.lian_no=0;
                                }
                            }
                        });

                        typetip_shangbao_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_shangbao_yes.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_shangbao_yes.setTextColor(Color.RED);
                                    FilterState.shangbao_yes=1;
                                }else{
                                    typetip_shangbao_yes.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.shangbao_yes=0;
                                }
                            }
                        });

                        typetip_shangbao_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_shangbao_no.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_shangbao_no.setTextColor(Color.RED);
                                    FilterState.shangbao_no=1;
                                }else{
                                    typetip_shangbao_no.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.shangbao_no=0;
                                }
                            }
                        });

                        typetip_fengxian_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_fengxian_yes.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_fengxian_yes.setTextColor(Color.RED);
                                    FilterState.fengxian_yes=1;
                                }else{
                                    typetip_fengxian_yes.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.fengxian_yes=0;
                                }
                            }
                        });

                        typetip_fengxian_no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(typetip_fengxian_no.getCurrentTextColor()==mContext.getResources().getColor(R.color.maintext)){
                                    typetip_fengxian_no.setTextColor(Color.RED);
                                    FilterState.fengxian_no=1;
                                }else{
                                    typetip_fengxian_no.setTextColor(mContext.getResources().getColor(R.color.maintext));
                                    FilterState.fengxian_no=0;
                                }
                            }
                        });

                        type_yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(popWin_left!=null && popWin_left.isShowing()){
                                    popWin_left.dismiss();
                                    popWin_left=null;
                                }
                                Log.i(TAG,"lian_yes"+FilterState.lian_yes+"/lian_no"+FilterState.lian_no
                                +"shangbao_yes"+FilterState.shangbao_yes+"/shangbao_no"+FilterState.shangbao_no+
                                "/fengxian_yes"+FilterState.fengxian_yes+"/fengxian_no"+FilterState.fengxian_no);
                                String lian_state=FilterState.stateconvert(FilterState.lian_yes,FilterState.lian_no);
                                String riskstate=FilterState.stateconvert(FilterState.shangbao_yes,FilterState.shangbao_no);
                                String risklevel=FilterState.stateconvert(FilterState.fengxian_yes,FilterState.fengxian_no);
                                //String keywords="";
                                String keyword=cur_search_et2.getText().toString().trim();
                                getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YCK,lian_state,riskstate,risklevel,keyword,refreshStart,refreshLimit_search,
                                        OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YCK,ProcessMain.this));

                            }
                        });
                        type_reset.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                FilterState.reset(mContext,viewfinal);
                            }
                        });
                    }
                }).setOutsideTouchable(true).create();
        popWin_left.showAtLocation(getLayoutInflater().inflate(R.layout.activity_detail_yck, null), Gravity.RIGHT, 0, 500);
    }
    //设置筛选按钮是否课件
    private void setFilerVisiblity(String mCurrentBottom){
        if(mCurrentBottom.equals(CurrentBottom.CK_YCK) || mCurrentBottom.equals(CurrentBottom.CD_YCK)){
            filter_btn.setVisibility(View.VISIBLE);
        }else{
            filter_btn.setVisibility(View.GONE);
        }
    }

    private void InitSocketListener() {
            mSocket.on("disconnect",new Emitter.Listener(){//服务器断开连接测试

                @Override
                public void call(Object... args) {
                    Log.e(getClass().getSimpleName(),"server is disconnect");
                    //重新连接
                }
            });
            mSocket.on("connect",new Emitter.Listener(){//服务器断开连接测试

                @Override
                public void call(Object... args) {
                    Log.e("ProcessMain","server is connect");
                }
            });
            mSocket.on("new_msg", msgListener);
            mSocket.on("logout", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    MSocketHelper.getInstance().logout();
                    //显示退出alet框
                    if(AppManager.getAppManager().getCurrentActivity()!=null){
                        OkCallbackManager.getInstance().showCloseAlert(ProcessMain.this);
                    }else{
                        Log.e("ProcessMain","current Visible activity is null");
                    }
                }
            });
    }

    public void setCount(TextView textView,int count){
        if(count>0){
            textView.setVisibility(View.VISIBLE);
        }else{
            textView.setVisibility(View.GONE);
        }
        textView.setText(""+count);
        Log.e("processmain","setCount:"+count);
    }

    public void updateCountView(){
        setCount(bar_num1,UnReadCounts.getCount(ParamType.DCK));
        setCount(bar_num2,UnReadCounts.getCount(ParamType.YCK));
        setCount(bar_num3,UnReadCounts.getCount(ParamType.DDS));
        setCount(bar_num4,UnReadCounts.getCount(ParamType.DSZ));
        setCount(bar_num5,UnReadCounts.getCount(ParamType.YDS));
        setCount(bar_num6,UnReadCounts.getCount(ParamType.HP));
        setCount(bar_num8,UnReadCounts.getCount(ParamType.GZ));
        setCount(bar_num9,UnReadCounts.getCount(ParamType.LS));
    }

    //无数据页面控制
    public void setNoDataView(boolean nodata,String paramType){
            switch (paramType){
                case ParamType.DCK:
                    RelativeLayout noReportRL1= (RelativeLayout) findViewById(R.id.dck_noReportRL);
                    if(nodata){
                        //xrv_dck.setVisibility(View.GONE);
                        noReportRL1.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_dck.setVisibility(View.VISIBLE);
                        noReportRL1.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.YCK:
                    RelativeLayout noReportRL2= (RelativeLayout) findViewById(R.id.yck_noReportRL);
                    if(nodata){
                        //xrv_yck.setVisibility(View.GONE);
                        noReportRL2.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_yck.setVisibility(View.VISIBLE);
                        noReportRL2.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.DDS:
                    RelativeLayout noReportRL3= (RelativeLayout) findViewById(R.id.dds_noReportRL);
                    if(nodata){
                        //xrv_dds.setVisibility(View.GONE);
                        noReportRL3.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_dds.setVisibility(View.VISIBLE);
                        noReportRL3.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.DSZ:
                    RelativeLayout noReportRL4= (RelativeLayout) findViewById(R.id.dsz_noReportRL);
                    if(nodata){
                        //xrv_dsz.setVisibility(View.GONE);
                        noReportRL4.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_dsz.setVisibility(View.VISIBLE);
                        noReportRL4.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.YDS:
                    RelativeLayout noReportRL5= (RelativeLayout) findViewById(R.id.yds_noReportRL);
                    if(nodata){
                        //xrv_yds.setVisibility(View.GONE);
                        noReportRL5.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_yds.setVisibility(View.VISIBLE);
                        noReportRL5.setVisibility(View.GONE);
                    }

                    break;
                case ParamType.HP:
                    RelativeLayout noReportRL6= (RelativeLayout) findViewById(R.id.hp_noReportRL);
                    if(nodata){
                        //xrv_hp.setVisibility(View.GONE);
                        noReportRL6.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_hp.setVisibility(View.VISIBLE);
                        noReportRL6.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.GZ:
                    RelativeLayout noReportRL7= (RelativeLayout) findViewById(R.id.rswork_noReportRL);
                    if(nodata){
                        //xrv_gz.setVisibility(View.GONE);
                        noReportRL7.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_gz.setVisibility(View.VISIBLE);
                        noReportRL7.setVisibility(View.GONE);
                    }
                    break;
                case ParamType.LS:
                    RelativeLayout noReportRL8= (RelativeLayout) findViewById(R.id.rshistroy_noReportRL);
                    if(nodata){
                        //xrv_ls.setVisibility(View.GONE);
                        noReportRL8.setVisibility(View.VISIBLE);
                    }else {
                        //xrv_ls.setVisibility(View.VISIBLE);
                        noReportRL8.setVisibility(View.GONE);
                    }
                    break;
                default:
                    break;
            }
    }

    @Override
    protected void onDestroy() {
        //MViewManager.getInstance().finishAllAdapter();//销毁所有adapter
        Log.e("Main","onDestroy enter");
        //MSocketHelper.getInstance().logout();
        super.onDestroy();
    }
    //刷新当前页面
    public void refreshCurrentPage(){
        Log.e("ProcessMain","currentBottom:"+currentBottom);
        String keyword="";
        if(cur_search_et2!=null){
            keyword=cur_search_et2.getText().toString().trim();
        }
              if(currentBottom.equals(CurrentBottom.RS_WORK)){
            getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.GZ,keyword,refreshStart,refreshLimit_gz,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.GZ,ProcessMain.this));
        }else if(currentBottom.equals(CurrentBottom.RS_HISTROY)){
            getTaskhurtData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.LS,keyword,refreshStart,refreshLimit_ls,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.LS,ProcessMain.this));
        }
        else if(currentBottom.equals(CurrentBottom.CK_DDS) || currentBottom.equals(CurrentBottom.DS_DDS) || currentBottom.equals(CurrentBottom.CD_DDS)){
            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DDS,keyword,refreshStart,refreshLimit_dds,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DDS,ProcessMain.this));
        }else if(currentBottom.equals(CurrentBottom.DS_DSZ) || currentBottom.equals(CurrentBottom.CD_DSZ)){
            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DSZ,keyword,refreshStart,refreshLimit_dsz,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DSZ,ProcessMain.this));
        }else if(currentBottom.equals(CurrentBottom.DS_YDS) || currentBottom.equals(CurrentBottom.CD_YDS)){
            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YDS,keyword,refreshStart,refreshLimit_yds,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YDS,ProcessMain.this));
        }
        else if(currentBottom.equals(CurrentBottom.DS_HP) || currentBottom.equals(CurrentBottom.CD_HP)){
            getTaskDSData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.HP,keyword,refreshStart,refreshLimit_hp,

                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.HP,ProcessMain.this));
        }

        else if(currentBottom.equals(CurrentBottom.CK_DCK) || currentBottom.equals(CurrentBottom.CD_DCK)){
            getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.DCK,"","","",keyword,refreshStart,refreshLimit_dck,

                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.DCK,ProcessMain.this));
        }
        else if(currentBottom.equals(CurrentBottom.CK_YCK) || currentBottom.equals(CurrentBottom.CD_YCK)){
            getTaskCKData(userManager.getUserToken(),userManager.getFrontRole(), ParamType.YCK,"","","",keyword,refreshStart,refreshLimit_yck,
                    OkCallbackManager.getInstance().getCallback(LoadType.REFRESH,mContext,ParamType.YCK,ProcessMain.this));
        }
    }

    @Subscribe
    public void refreshAct(BusRefreshAct busdata){
        if(busdata.getType().equals("refresh")){
            refreshCurrentPage();
        }
    }
}
