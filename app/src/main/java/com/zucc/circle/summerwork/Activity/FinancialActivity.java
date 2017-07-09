package com.zucc.circle.summerwork.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zucc.circle.summerwork.Adapter.FinancialViewPagerAdapter;
import com.zucc.circle.summerwork.Fragment.ExpendFragment;
import com.zucc.circle.summerwork.Fragment.IncomeFragment;
import com.zucc.circle.summerwork.R;

import java.util.ArrayList;
import java.util.List;

public class FinancialActivity extends AppCompatActivity {
    FinancialViewPagerAdapter vpAdapter;
    ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IncomeFragment());
        fragments.add(new ExpendFragment());
        List<String> name = new ArrayList<>();
        name.add("收入");
        name.add("支出");
        vpAdapter = new FinancialViewPagerAdapter(getSupportFragmentManager(), fragments, name);
        vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(vpAdapter);
    }

    //从服务端获取收入记录
    public int getProfit(List<String> incomes, List<String> expends) {
        int result = 0;
        return result;
    }
    public void fan(View v) {
        this.finish();
    }
}
