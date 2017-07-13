package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

public class PersonPlanActivity extends Activity {
    TextView person_plan_title;
    TextView person_plan_start;
    TextView person_plan_end;
    TextView person_plan_type;
    TextView person_plan_remark;
    Button btn_plan_log;

    MyApplication application;
    PersonPlanEntity personPlanEntity;
    int selectid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_plan);
        application = (MyApplication) getApplication();
        selectid = MyApplication.selectPlan;
        personPlanEntity = application.getUser().getPersonPlanEntities().get(selectid);
        initview();
    }
    public void initview(){
        person_plan_title = (TextView) findViewById(R.id.person_plan_title);
        person_plan_start = (TextView) findViewById(R.id.person_plan_start);
        person_plan_end = (TextView) findViewById(R.id.person_plan_end);
        person_plan_type = (TextView) findViewById(R.id.person_plan_type);
        person_plan_remark = (TextView) findViewById(R.id.person_plan_remark);
        btn_plan_log = (Button) findViewById(R.id.action_plan_log);
        person_plan_title.setText(personPlanEntity.getSchedulecontent());
        person_plan_type.setText(personPlanEntity.getScheduletype());
        person_plan_start.setText(personPlanEntity.getSchedulestart());
        person_plan_end.setText(personPlanEntity.getScheduleend());
        person_plan_remark.setText(personPlanEntity.getScheduleremark());
        btn_plan_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(PersonPlanActivity.this,PersonPlanLogActivity.class);
                startActivity(intent);
            }
        });
    }
    public void fan(View v) {
        this.finish();
    }
}
