package com.zucc.circle.summerwork.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Activity.AddPersonPlanActivity;
import com.zucc.circle.summerwork.Activity.LoginActivity;
import com.zucc.circle.summerwork.Adapter.PersonPlanAdapter;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonEntity;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.MainActivity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.View.ScrollListviewDelete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTabHost;

public class PersonPlanFragment extends Fragment implements View.OnClickListener{
    FloatingActionButton fab_add_person_plan;
    ScrollListviewDelete lv_person_plan;
    PersonPlanAdapter adapter;
    List<PersonPlanEntity> personPlans;
    MyApplication myApplication;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_person_plan, container, false);
        fab_add_person_plan = (FloatingActionButton) view.findViewById(R.id.action_add_person_plan);
        fab_add_person_plan.setOnClickListener(this);
        myApplication = (MyApplication)getActivity().getApplication();
        lv_person_plan = (ScrollListviewDelete) view.findViewById(R.id.lv_person_plan);
        loadPersonPlan();
//        personPlans = new ArrayList<>();
//        personPlans.add(new PersonPlanEntity("我要吃饭", "2016/7/29/16:00", "2016/7/29/16:00"));
//        personPlans.add(new PersonPlanEntity("我要吃饭", "2016/7/29/16:00", "2016/7/29/16:00"));
//        personPlans.add(new PersonPlanEntity("我要吃饭", "2016/7/29/16:00", "2016/7/29/16:00"));
//        personPlans.add(new PersonPlanEntity("我要吃饭", "2016/7/29/16:00", "2016/7/29/16:00"));
//        personPlans.add(new PersonPlanEntity("我要吃饭", "2016/7/29/16:00", "2016/7/29/16:00"));
        adapter = new PersonPlanAdapter(view.getContext(),R.layout.item_person_plan,myApplication.getUser().getPersonPlanEntities());
        lv_person_plan.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_add_person_plan:
                Intent intent = new Intent();
                intent.setClass(view.getContext(), AddPersonPlanActivity.class);
                view.getContext().startActivity(intent);

        }
    }
    public void loadPersonPlan(){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOADPERSONPLAN_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("scheduleuser", myApplication.getUser().getUsername());
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
                Toast.makeText(getContext(),json,Toast.LENGTH_LONG).show();
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray personPlans = jsonObject.getJSONArray("personschedules");
                    PersonPlanEntity personPlanEntity;
                    for (int i=0;i<personPlans.length();i++){
                        JSONObject personPlan = personPlans.getJSONObject(i);
                        personPlanEntity = new PersonPlanEntity();
                        personPlanEntity.setSchedulecontent(personPlan.getString("schedulecontent"));
                        personPlanEntity.setSchedulestart(personPlan.getString("schedulestart"));
                        personPlanEntity.setScheduleend(personPlan.getString("scheduleend"));
                        myApplication.getUser().getPersonPlanEntities().add(personPlanEntity);
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
        return "123";
    }
}
