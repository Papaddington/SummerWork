package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Activity.LoginActivity;
import com.zucc.circle.summerwork.Activity.PersonPlanActivity;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.presenter.PlanPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 圆圈 on 2017-07-05.
 */

public class PersonPlanAdapter extends ArrayAdapter<PersonPlanEntity> {
    private int resource;
    private Context context;
    private List<PersonPlanEntity> personplans;
    private String userid;
    private String finishType;

    public String getFinishType() {
        return finishType;
    }

    public void setFinishType(String finishType) {
        this.finishType = finishType;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<PersonPlanEntity> getPersonplans() {
        return personplans;
    }

    public void setPersonplans(List<PersonPlanEntity> personplans) {
        this.personplans = personplans;
    }
    public PersonPlanAdapter(Context context, int resource, List<PersonPlanEntity> personplans) {
        super(context, resource,personplans);
        this.context = context;
        this.resource = resource;
        this.personplans = personplans;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PersonPlanEntity personplan = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView person_plan_title = (TextView)view.findViewById(R.id.person_plan_title);
        TextView person_plan_time_start = (TextView)view.findViewById(R.id.person_plan_time_start);
        TextView person_plan_time_end = (TextView)view.findViewById(R.id.person_plan_time_end);
        TextView person_plan_type = (TextView)view.findViewById(R.id.person_plan_type);
        Button btn_Into = (Button)view.findViewById(R.id.btn_Into);
        Button btn_Delete = (Button)view.findViewById(R.id.btn_Delete);
        Button btn_Nao = (Button)view.findViewById(R.id.btn_Nao);
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePersonPlan(personplan.getSchedulenumber());
                personplans.remove(position);
                notifyDataSetChanged();
            }
        });
        if (!personplan.getScheduletype().equals("未完成")){
            btn_Nao.setEnabled(false);
        }
        btn_Nao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishPersonPlan(personplan.getSchedulenumber(),position);
            }
        });
        btn_Into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, PersonPlanActivity.class);
                MyApplication.selectPlan = position;
                context.startActivity(intent);
            }
        });
        person_plan_type.setText(personplan.getScheduletype());
        person_plan_title.setText(personplan.getSchedulecontent());
        person_plan_time_start.setText(personplan.getSchedulestart());
        person_plan_time_end.setText(personplan.getScheduleend());
        return view;
    }
    public void deletePersonPlan(int planid){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.DELETEPERSONPLAN_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("schedulenumber", planid);
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

    public void finishPersonPlan(int planid, final int position){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.FINISHPERSONPLAN_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("schedulenumber", planid);
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
                    String result = jsonObject.getString("result");
                    if (result.equals("001")){
                        personplans.get(position).setScheduletype("按时完成");
                    }else {
                        personplans.get(position).setScheduletype("超时完成");
                    }
                    notifyDataSetChanged();
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

}
