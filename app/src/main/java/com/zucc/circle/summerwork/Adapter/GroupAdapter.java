package com.zucc.circle.summerwork.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zucc.circle.summerwork.Entity.GroupEntity;
import com.zucc.circle.summerwork.R;

import java.util.List;

/**
 * Created by 圆圈 on 2017-07-10.
 */

public class GroupAdapter extends ArrayAdapter<GroupEntity> {
    int resource;
    public GroupAdapter(Context context, int resource, List<GroupEntity> groups) {
        super(context, resource,groups);
        this.resource = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupEntity group = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource,null);
        TextView groupName = (TextView) view.findViewById(R.id.item_group_name);
        TextView groupLeader = (TextView) view.findViewById(R.id.item_group_leader);
        groupName.setText(group.getGroupName());
        groupLeader.setText(group.getGroupLeader().getName());
        return view;
    }

}
