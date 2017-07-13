package com.zucc.circle.summerwork.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Adapter.FinancialViewPagerAdapter;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Fragment.ExpendFragment;
import com.zucc.circle.summerwork.Fragment.IncomeFragment;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FinancialActivity extends AppCompatActivity {
    FinancialViewPagerAdapter vpAdapter;
    ViewPager vp;
    private MyApplication myApplication;
    TextView tv_profit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        myApplication = (MyApplication)this.getApplication();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IncomeFragment());
        fragments.add(new ExpendFragment());
        List<String> name = new ArrayList<>();
        name.add("收入");
        name.add("支出");
        vpAdapter = new FinancialViewPagerAdapter(getSupportFragmentManager(), fragments, name);
        vp = (ViewPager) findViewById(R.id.viewPager);
        tv_profit = (TextView) findViewById(R.id.tv_profit);
        vp.setAdapter(vpAdapter);
        getEarning();
    }


    //从服务端获取收入记录
    public int getProfit(List<String> incomes, List<String> expends) {
        int result = 0;
        return result;
    }
    public void fan(View v) {
        this.finish();
    }

    public void getEarning() {
        final Request<String> request = NoHttp.createStringRequest(ContantUri.GET_EARNING_URL, RequestMethod.POST);
        request.add("incomeuserid", myApplication.getUser().getUserphone());
        RequestQueue queue = MyApplication.getmRequestQueue();
        OnResponseListener<String> callBack = new OnResponseListener<String>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getString("result");
                    tv_profit.setText(result + "元");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        };
        queue.add(1, request, callBack);
    }
}
