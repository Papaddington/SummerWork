package com.zucc.circle.summerwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zucc.circle.summerwork.R;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import tech.linjiang.suitlines.SuitLines;
import tech.linjiang.suitlines.Unit;

public class InvestActivity extends AppCompatActivity {
    SuitLines suitLines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        suitLines = (SuitLines) findViewById(R.id.suitlines);
        List<Unit> lines = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            lines.add(new Unit(new SecureRandom().nextInt(48), i + ""));
        }
        suitLines.feedWithAnim(lines);
    }
    public void fan(View v) {
        this.finish();
    }
}
