package com.zucc.circle.summerwork.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        fragments.add(new ExpendFragment());
        fragments.add(new IncomeFragment());
        List<String> name = new ArrayList<>();
        name.add("收入");
        name.add("支出");
        vpAdapter = new FinancialViewPagerAdapter(getSupportFragmentManager(),fragments,name);
        vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(vpAdapter);
    }
}
