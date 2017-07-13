package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonEntity;
import com.zucc.circle.summerwork.MainActivity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.Util.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddIncomeActivity extends Activity implements View.OnClickListener{
    MaterialSpinner sp_income_type;
    EditText et_income_title, et_income_money;
    TextView btn_check_income;
    List<String> incomeTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        LoadIncomeType();
        sp_income_type = (MaterialSpinner) findViewById(R.id.sp_income_type);
        et_income_title = (EditText) findViewById(R.id.et_income_title);
        et_income_money = (EditText) findViewById(R.id.et_income_money);
        btn_check_income = (TextView) findViewById(R.id.btn_check_income);
        sp_income_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_income:
                userAddIncome();
                break;
            default:
                break;
        }
    }

    public void fan(View v) {
        this.finish();
    }

    public void LoadIncomeType() {
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOAD_PERSON_INCOME_TYPE_URL, RequestMethod.POST);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
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
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    incomeTypes = new ArrayList<>();
                    String incometypes = jsonObject.getString("incometypes");
                    String ss = incometypes.substring(1,incometypes.length()-1);
                    String str[] = ss.split(",");
                    for(int i = 0; i < str.length; i++) {
                        String s = str[i].substring(1, str[i].length()-1);
                        incomeTypes.add(s);
                    }
                    sp_income_type.setItems(incomeTypes);
                } catch (JSONException e) {
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

    //判断客户端逻辑
    public void userAddIncome() {
        String incomeTitle = et_income_title.getText().toString();
        String incomeMoney = et_income_money.getText().toString();
        if(incomeTitle == null || StringUtils.isEmpty(incomeTitle)) {
            return;
        }
        if(incomeMoney == null || StringUtils.isEmpty(incomeMoney)) {
            return;
        }

    }
    public void addIncome(String icomeTitle, String incomeMoney) {
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOGIN_URL, RequestMethod.POST);
        request.add("incomeTitle", icomeTitle);
        request.add("incomeMoney", incomeMoney);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
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
                //tv_test.setText(json.toString());

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
