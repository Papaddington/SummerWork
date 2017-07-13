package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Adapter.PlanLogAdapeter;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.Entity.PersonPlanLogEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.View.DivItemDecoration;
import com.zucc.circle.summerwork.presenter.LogPresenter;
import com.zucc.circle.summerwork.presenter.view.ICircleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonPlanLogActivity extends Activity implements ICircleView{
    SuperRecyclerView personPlanLog;
    ImageView add_log;
    LinearLayoutManager layoutManager;
    PlanLogAdapeter adapter;
    MyApplication application;
    ArrayList datasPlaLogs;
    LogPresenter logPresenter;

    PersonPlanEntity personPlanEntity;
    int selectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_plan_log);
        logPresenter = new LogPresenter();
        logPresenter.attachView(this);
        application = (MyApplication)getApplication();
        selectid = MyApplication.selectPlan;
        personPlanEntity = application.getUser().getPersonPlanEntities().get(selectid);
        initView();
    }
    public void initView(){
        datasPlaLogs = new ArrayList();
        personPlanLog = (SuperRecyclerView) findViewById(R.id.person_plan_log_View);
        layoutManager = new LinearLayoutManager(this);
        personPlanLog.setLayoutManager(layoutManager);
        personPlanLog.addItemDecoration(new DivItemDecoration(2, true));
        personPlanLog.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        personPlanLog.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datasPlaLogs.clear();
                        loadPlanLog();
                        adapter.notifyDataSetChanged();
                        personPlanLog.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        personPlanLog.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        }, 1);
        personPlanLog.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(PersonPlanLogActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Glide.with(PersonPlanLogActivity.this).resumeRequests();
            }
        });
        adapter = new PlanLogAdapeter(this,this);
        personPlanLog.setAdapter(adapter);
        loadPlanLog();
        adapter.setLogPresenter(logPresenter);
        adapter.setDatas(datasPlaLogs);
        adapter.notifyDataSetChanged();
        add_log=(ImageView) findViewById(R.id.plan_log_add);
        add_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(PersonPlanLogActivity.this,AddPersonPlanLogActivity.class);
                startActivity(intent);
            }
        });
    }
    public void loadPlanLog(){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOADLOG_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("logscheduleid",personPlanEntity.getSchedulenumber());
        //请求回调
        OnResponseListener<String> callBack = new OnResponseListener<String>() {
            //这些方法都运行在主线程中，可以直接更新界面，同时也意味着不能做耗时操作
            @Override
            public void onStart(int what) {
                //发出请求时，开始执行的方法
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                //请求成功时执行的方法
                String json = response.get();
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray planLogs = jsonObject.getJSONArray("logs");
                    for (int i = 0;i<planLogs.length();i++){
                        JSONObject planLog = planLogs.getJSONObject(i);
                        PersonPlanLogEntity personPlanLogEntity = new PersonPlanLogEntity();
                        personPlanLogEntity.setLogid(planLog.getInt("logid"));
                        personPlanLogEntity.setLogdata(StringTime(planLog.getString("logdata")));
                        personPlanLogEntity.setLogwrite(planLog.getString("logwrite"));
                        personPlanLogEntity.setLogwriter(planLog.getString("logwriter"));
                        datasPlaLogs.add(personPlanLogEntity);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                //请求失败时执行的方法
            }

            @Override
            public void onFinish(int what) {
                //请求结束时执行的方法
            }
        };
        //将网络请求添加到请求队列中；第一个参数：请求的标识，标记是哪个请求；第二个参数：请求对象；第三个参数：回调对象
        queue.add(0, request, callBack);
    }
    public String StringTime(String time){
        time = time.replace("T","   ");
        time = time.substring(0,time.length() - 3);
        return time;
    }
    public void fan(View v) {
        this.finish();
    }


    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void deleLog(int logid) {
        deletePlanLog(logid);
        for (int i=0;i<datasPlaLogs.size();i++){
            PersonPlanLogEntity deletePlanlog = (PersonPlanLogEntity)datasPlaLogs.get(i);
            if (deletePlanlog.getLogid()==logid){
                datasPlaLogs.remove(i);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void deletePlanLog(int logid){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.DELETELOG_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("logid",logid);
        //请求回调
        OnResponseListener<String> callBack = new OnResponseListener<String>() {
            //这些方法都运行在主线程中，可以直接更新界面，同时也意味着不能做耗时操作
            @Override
            public void onStart(int what) {
                //发出请求时，开始执行的方法
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                //请求成功时执行的方法
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                //请求失败时执行的方法
            }

            @Override
            public void onFinish(int what) {
                //请求结束时执行的方法
            }
        };
        //将网络请求添加到请求队列中；第一个参数：请求的标识，标记是哪个请求；第二个参数：请求对象；第三个参数：回调对象
        queue.add(0, request, callBack);
    }
}
