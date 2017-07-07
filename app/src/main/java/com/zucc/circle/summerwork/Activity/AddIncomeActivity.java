package com.zucc.circle.summerwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rey.material.widget.Spinner;
import com.zucc.circle.summerwork.R;

public class AddIncomeActivity extends AppCompatActivity {
    MaterialSpinner sp_income_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        sp_income_type.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
    }
    public void fan(View v) {
        this.finish();
    }
}
