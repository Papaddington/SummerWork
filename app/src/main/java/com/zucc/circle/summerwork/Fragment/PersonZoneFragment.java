package com.zucc.circle.summerwork.Fragment;


import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zucc.circle.summerwork.Activity.TestActivity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonZoneFragment extends Fragment implements View.OnClickListener{
    LinearLayout ll_daohang;
    TextView userName,tips;
    MyApplication myApplication;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_person_zone, container, false);
        myApplication = (MyApplication) getActivity().getApplication();
        ll_daohang = (LinearLayout) view.findViewById(R.id.ll_daohang);
        userName = (TextView) view.findViewById(R.id.userName);
        tips = (TextView) view.findViewById(R.id.tip);
        userName.setText(myApplication.getUser().getUsername());
        tips.setText("欢迎您登录");
        ll_daohang.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_daohang:
                Intent intent = new Intent(view.getContext(), TestActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
