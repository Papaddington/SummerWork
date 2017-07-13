package com.zucc.circle.summerwork.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

public class PersonDataActivity extends AppCompatActivity {
    private MyApplication myApplication;
    private TextView name, phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_data);
        myApplication = (MyApplication) getApplication();
        name = (TextView) findViewById(R.id.name);
        phone = (TextView) findViewById(R.id.phone);
        email = (TextView) findViewById(R.id.mail);
        name.setText(myApplication.getUser().getUsername());
        phone.setText(myApplication.getUser().getUserphone());
        email.setText(myApplication.getUser().getUsermailbox());
    }
    public void fan(View v) {
        this.finish();
    }
}
