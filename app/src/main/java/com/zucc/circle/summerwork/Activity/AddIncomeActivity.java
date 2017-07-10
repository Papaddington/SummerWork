package com.zucc.circle.summerwork.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rey.material.widget.Button;
import com.rey.material.widget.Switch;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.Util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddIncomeActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialSpinner sp_income_type;
    EditText et_income_title, et_income_money;
    TextView btn_check_income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        sp_income_type = (MaterialSpinner) findViewById(R.id.sp_income_type);
        et_income_title = (EditText) findViewById(R.id.et_income_title);
        et_income_money = (EditText) findViewById(R.id.et_income_money);
        btn_check_income = (TextView) findViewById(R.id.btn_check_income);
        sp_income_type.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat");
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
