package com.zucc.circle.summerwork.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zucc.circle.summerwork.Activity.AddPersonPlanActivity;
import com.zucc.circle.summerwork.Activity.FinancialActivity;
import com.zucc.circle.summerwork.Activity.InvestActivity;
import com.zucc.circle.summerwork.R;

public class PersonFinacialFragment extends Fragment implements View.OnClickListener{

    private ImageView iv_financial,iv_invest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_person_financial, container, false);
        iv_financial = (ImageView) view.findViewById(R.id.action_financial);
        iv_invest = (ImageView) view.findViewById(R.id.action_invest);
        iv_financial.setOnClickListener(this);
        iv_invest.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.action_financial:
                intent.setClass(view.getContext(), FinancialActivity.class);
                view.getContext().startActivity(intent);
                break;
            case R.id.action_invest:
                intent = new Intent();
                intent.setClass(view.getContext(), InvestActivity.class);
                view.getContext().startActivity(intent);
        }
    }
}
