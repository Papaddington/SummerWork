package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.Entity.PersonPlanLogEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddPersonPlanLogActivity extends Activity {
    EditText plan_log_text;
    Button add_plan_log;
    MyApplication application;
    PersonPlanEntity personPlanEntity;
    int selectid;
    String logText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_plan_log);
        application = (MyApplication)getApplication();
        selectid = MyApplication.selectPlan;
        personPlanEntity = application.getUser().getPersonPlanEntities().get(selectid);
        initView();
    }
    public void initView(){
        plan_log_text = (EditText)findViewById(R.id.plan_log_text);
        add_plan_log = (Button)findViewById(R.id.add_plan_log);
        add_plan_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logText = plan_log_text.getText().toString();
                if (TextUtils.isEmpty(logText)){
                    Toast.makeText(AddPersonPlanLogActivity.this,logText,Toast.LENGTH_LONG).show();
                }else {
                    addPlanLog(logText);
                    finish();
                }
            }
        });

    }

    public void addPlanLog(String logText){
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.ADDLOG_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        request.add("logwrite",logText);
        request.add("logwriter",application.getUser().getUserphone());
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
    public void fan(View v) {
        this.finish();
    }
}
