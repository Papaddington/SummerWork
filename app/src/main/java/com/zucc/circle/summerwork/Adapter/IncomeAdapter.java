package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zucc.circle.summerwork.Entity.IncomeEntity;
import com.zucc.circle.summerwork.Entity.PersonPlanEntity;
import com.zucc.circle.summerwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 圆圈 on 2017-07-07.
 */

public class IncomeAdapter extends ArrayAdapter<IncomeEntity>{
    private int resource;
    public IncomeAdapter(Context context, int resource, List<IncomeEntity> incomes) {
        super(context, resource,incomes);
        this.resource = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        IncomeEntity income = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView income_name = (TextView) view.findViewById(R.id.item_income_name);
        TextView income_type = (TextView) view.findViewById(R.id.item_income_type);
        TextView income_money = (TextView) view.findViewById(R.id.item_income_money);
        TextView income_time = (TextView) view.findViewById(R.id.item_income_time);
        income_name.setText(income.getName());
        income_type.setText(income.getType());
        income_money.setText(income.getMoney());
        income_time.setText(income.getStart_time());
        return view;
    }
}
