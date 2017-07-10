package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zucc.circle.summerwork.Entity.ExpendEntity;
import com.zucc.circle.summerwork.Entity.IncomeEntity;
import com.zucc.circle.summerwork.R;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-09.
 */

public class ExpendAdapter extends ArrayAdapter<ExpendEntity>{
    private int resource;
    public ExpendAdapter(Context context, int resource, List<ExpendEntity> expends) {
        super(context, resource,expends);
        this.resource = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ExpendEntity expend = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView expend_name = (TextView) view.findViewById(R.id.item_expend_name);
        TextView expend_type = (TextView) view.findViewById(R.id.item_expend_type);
        TextView expend_money = (TextView) view.findViewById(R.id.item_expend_money);
        TextView expend_time = (TextView) view.findViewById(R.id.item_expend_time);
        expend_name.setText(expend.getName());
        expend_type.setText(expend.getType());
        expend_money.setText(expend.getMoney());
        expend_time.setText(expend.getStart_time());
        return view;
    }
}
