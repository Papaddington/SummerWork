package com.zucc.circle.summerwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zucc.circle.summerwork.R;

public class InvestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);

    }
    public void fan(View v) {
        this.finish();
    }
}
