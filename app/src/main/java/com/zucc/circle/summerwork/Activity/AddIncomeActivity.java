package com.zucc.circle.summerwork.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.zucc.circle.summerwork.R;

public class AddIncomeActivity extends AppCompatActivity {
    MaterialSpinner sp_income_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        sp_income_type = (MaterialSpinner) findViewById(R.id.sp_income_type);
        sp_income_type.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat");
        sp_income_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }
    public void fan(View v) {
        this.finish();
    }
}
