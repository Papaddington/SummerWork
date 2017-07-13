package com.zucc.circle.summerwork.Activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.rey.material.widget.Button;
import com.rey.material.widget.Switch;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.Util.AlarmUtils;

import java.util.Calendar;

public class AddPersonPlanActivity extends AppCompatActivity implements View.OnClickListener{
    private Switch switch_person_time;
    private LinearLayout ll_person_time_set;
    private RelativeLayout rl_person_plan_start_date,rl_calendarview,rl_person_plan_start_time,rl_timeview,rl_person_plan_end_date,rl_person_plan_end_time;
    private MaterialCalendarView calendarView;
    private TimePicker timePicker;
    private TextView tv_person_plan_start_date,tv_person_plan_start_time,btn_check_person_plan;
    private TextView tv_person_plan_end_date, tv_person_plan_end_time;
    private EditText et_person_plan_title, et_note;
    private Button btn_check_date,btn_check_time;
    private int start_month,start_day,start_hour,start_minute;
    private int end_month, end_day, end_hour, end_minute;
    private int FLAG = 0;
    private MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_plan);
        myApplication = (MyApplication)this.getApplication();
        switch_person_time = (Switch) findViewById(R.id.switch_person_time);
        ll_person_time_set = (LinearLayout) findViewById(R.id.ll_person_time_set);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        rl_person_plan_start_date = (RelativeLayout) findViewById(R.id.rl_person_plan_start_date);
        rl_calendarview = (RelativeLayout) findViewById(R.id.rl_calendarview);
        rl_person_plan_start_time = (RelativeLayout) findViewById(R.id.rl_person_plan_start_time);
        rl_timeview = (RelativeLayout) findViewById(R.id.rl_timeview);
        rl_person_plan_end_date = (RelativeLayout) findViewById(R.id.rl_person_plan_end_date);
        rl_person_plan_end_time = (RelativeLayout) findViewById(R.id.rl_person_plan_end_time);
        tv_person_plan_start_date = (TextView) findViewById(R.id.tv_person_plan_start_date);
        tv_person_plan_start_time = (TextView) findViewById(R.id.tv_person_plan_start_time);
        tv_person_plan_end_date = (TextView) findViewById(R.id.tv_person_plan_end_date);
        tv_person_plan_end_time = (TextView) findViewById(R.id.tv_person_plan_end_time);
        btn_check_date = (Button) findViewById(R.id.btn_check_date);
        btn_check_time = (Button) findViewById(R.id.btn_check_time);
        btn_check_person_plan = (TextView) findViewById(R.id.btn_check_person_plan);
        et_note = (EditText) findViewById(R.id.et_note);
        et_person_plan_title = (EditText) findViewById(R.id.et_person_plan_title);
        switch_person_time.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {
                if(checked) {
                    ll_person_time_set.setVisibility(View.VISIBLE);
                }
                else {
                    ll_person_time_set.setVisibility(View.GONE);
                }
            }
        });
        rl_person_plan_start_date.setOnClickListener(this);
        rl_person_plan_start_time.setOnClickListener(this);
        rl_person_plan_end_date.setOnClickListener(this);
        rl_person_plan_end_time.setOnClickListener(this);
        btn_check_date.setOnClickListener(this);
        btn_check_time.setOnClickListener(this);
        btn_check_person_plan.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_person_plan_start_date:
                rl_calendarview.setVisibility(View.VISIBLE);
                FLAG = 0;
                break;
            case R.id.rl_person_plan_start_time:
                rl_timeview.setVisibility(View.VISIBLE);
                FLAG = 0;
                break;
            case R.id.rl_person_plan_end_date:
                rl_calendarview.setVisibility(View.VISIBLE);
                FLAG = 1;
                break;
            case R.id.rl_person_plan_end_time:
                rl_timeview.setVisibility(View.VISIBLE);
                FLAG = 1;
                break;
            case R.id.btn_check_date:
                CalendarDay cd = calendarView.getSelectedDate();
                String current_date = cd.getYear() + "/" + (cd.getMonth()+1) + "/" + cd.getDay();
                if(FLAG == 0) {
                    tv_person_plan_start_date.setText(current_date);
                    rl_calendarview.setVisibility(View.GONE);
                    start_month = cd.getMonth();
                    start_day = cd.getDay();
                }
                else {
                    tv_person_plan_end_date.setText(current_date);
                    rl_calendarview.setVisibility(View.GONE);
                    end_month = cd.getMonth();
                    end_day = cd.getDay();
                }
                break;
            case R.id.btn_check_time:
                String current_time = timePicker.getHour() + "  时 " + timePicker.getMinute() + " 分";
                if(FLAG == 0) {
                    tv_person_plan_start_time.setText(current_time);
                    rl_timeview.setVisibility(View.GONE);
                    start_hour = timePicker.getHour();
                    start_minute = timePicker.getMinute();
                }
                else {
                    tv_person_plan_end_time.setText(current_time);
                    rl_timeview.setVisibility(View.GONE);
                    end_hour = timePicker.getHour();
                    end_minute = timePicker.getMinute();
                }
                break;
            case R.id.btn_check_person_plan:
                UserAddPersonPlan();
                break;
            default:
                break;
        }

    }
    public void UserAddPersonPlan() {
        String content = et_person_plan_title.getText().toString();
        String start_time = "2017-" + start_month + "-" + start_day + " " + start_hour + ":" + start_minute + ":00";
        String end_time = "2017-" + end_month + "-" + end_day + " " + end_hour + ":" + end_minute + ":00";
        String remark = et_note.getText().toString();
        AddPersonPlan(content, start_time, end_time, remark);
        AlarmUtils.setAlarm(AddPersonPlanActivity.this, start_month, start_day, start_hour, start_minute, 001, "任务开始",0);
        AlarmUtils.setAlarm(AddPersonPlanActivity.this, start_month, start_day, start_hour, start_minute, 002, "任务结束",0);
    }
    public void AddPersonPlan(String content, String start, String end, String remark) {
        Request<String> request = NoHttp.createStringRequest(ContantUri.ADD_PERSON_PLAN_URL, RequestMethod.POST);
        request.add("schedulecontent", content);
        request.add("startTime", start);
        request.add("endTime", end);
        request.add("scheduleuser", myApplication.getUser().getUserphone());
        request.add("scheduleremark",remark);
        RequestQueue queue = MyApplication.getmRequestQueue();
        OnResponseListener<String> callBack = new OnResponseListener<String>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Toast.makeText(AddPersonPlanActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        };
        queue.add(0, request, callBack);
    }
    public void fan(View v) {
        this.finish();
    }
}
