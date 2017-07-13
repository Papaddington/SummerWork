package com.zucc.circle.summerwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddExpendActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialSpinner sp_expend_type;
    EditText et_expend_title, et_expend_money;
    TextView btn_check_expend;
    List<String> expendTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expend);
        loadExpendType();
        et_expend_title = (EditText) findViewById(R.id.et_expend_title);
        et_expend_money = (EditText) findViewById(R.id.et_expend_money);
        btn_check_expend = (TextView) findViewById(R.id.btn_check_expend);
        sp_expend_type = (MaterialSpinner) findViewById(R.id.sp_expend_type);
        btn_check_expend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_check_expend:
                break;
            default:
                break;
        }
    }


    public void fan(View v) {
        this.finish();
    }

    public List<String> loadExpendType() {
        final List<String> result = new ArrayList<>();
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOAD_PERSON_EXPEND_TYPE_URL, RequestMethod.POST);
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
                    expendTypes = new ArrayList<>();
                    String expendtypes = jsonObject.getString("expendtypes");
                    String ss = expendtypes.substring(1,expendtypes.length()-1);
                    String str[] = ss.split(",");
                    for(int i = 0; i < str.length; i++) {
                        String s = str[i].substring(1, str[i].length()-1);
                        expendTypes.add(s);
                    }
                    sp_expend_type.setItems(expendTypes);
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
        return result;
    }

}
