package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zucc.circle.summerwork.Entity.PersonPlanLogEntity;
import com.zucc.circle.summerwork.R;

/**
 * Created by yuritian on 2017/7/12.
 */

public class PlanLogAdapeter extends BaseRecycleViewAdapter {
    private Context context;
    public PlanLogAdapeter(Context context) {
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_plan_log, parent, false);
        viewHolder = new PlanlogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PlanlogViewHolder holder = (PlanlogViewHolder) viewHolder;
        PersonPlanLogEntity data = (PersonPlanLogEntity) datas.get(position);
        holder.userName.setText(data.getLogwriter());
        holder.logContent.setText(data.getLogwrite());
        holder.logTime.setText(data.getLogdata());
    }

    @Override
    public int getItemCount() {
        return datas.size()+1;
    }
    public class PlanlogViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView logContent;
        public TextView logTime;
        public TextView deleteLog;
        public PlanlogViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.username);
            logContent = (TextView) itemView.findViewById(R.id.content);
            logTime = (TextView) itemView.findViewById(R.id.createTime);
            deleteLog = (TextView) itemView.findViewById(R.id.deleteBtn);
        }
    }
}
