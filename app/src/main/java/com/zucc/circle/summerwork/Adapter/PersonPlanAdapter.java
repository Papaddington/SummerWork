package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Activity.LoginActivity;
import com.zucc.circle.summerwork.Activity.PersonPlanActivity;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.presenter.PlanPresenter;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-05.
 */

public class PersonPlanAdapter extends ArrayAdapter<PersonPlanEntity> {
    private int resource;
    private Context context;
    List<PersonPlanEntity> personplans;
    private PlanPresenter planPresenter;

    public void setPlanPresenter(PlanPresenter planPresenter) {
        this.planPresenter = planPresenter;
    }
    public void setPersonplans(List<PersonPlanEntity> personplans) {
        this.personplans = personplans;
    }
    public PersonPlanAdapter(Context context, int resource, List<PersonPlanEntity> personplans) {
        super(context, resource,personplans);
        this.context = context;
        this.resource = resource;
        this.personplans = personplans;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        PersonPlanEntity personplan = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView person_plan_title = (TextView)view.findViewById(R.id.person_plan_title);
        TextView person_plan_time_start = (TextView)view.findViewById(R.id.person_plan_time_start);
        TextView person_plan_time_end = (TextView)view.findViewById(R.id.person_plan_time_end);
        TextView person_plan_type = (TextView)view.findViewById(R.id.person_plan_type);
        Button btn_Into = (Button)view.findViewById(R.id.btn_Into);
        Button btn_Delete = (Button)view.findViewById(R.id.btn_Delete);
        Button btn_Nao = (Button)view.findViewById(R.id.btn_Nao);
        if (!personplan.getScheduletype().equals("未完成")){
            btn_Nao.setEnabled(false);
        }
        btn_Nao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_Into.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, PersonPlanActivity.class);
                MyApplication.selectPlan = position;
                context.startActivity(intent);
            }
        });
        person_plan_type.setText(personplan.getScheduletype());
        person_plan_title.setText(personplan.getSchedulecontent());
        person_plan_time_start.setText(personplan.getSchedulestart());
        person_plan_time_end.setText(personplan.getScheduleend());
        return view;
    }
}
