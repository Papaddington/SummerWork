package com.zucc.circle.summerwork.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zucc.circle.summerwork.Activity.GroupActivity;
import com.zucc.circle.summerwork.Adapter.GroupAdapter;
import com.zucc.circle.summerwork.Entity.GroupEntity;
import com.zucc.circle.summerwork.Entity.PersonEntity;
import com.zucc.circle.summerwork.R;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class GroupPlanFragment extends Fragment {
    ListView lv_group;
    List<GroupEntity> groupEntities;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        groupEntities  = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_group_plan, container, false);
        lv_group = (ListView) view.findViewById(R.id.lv_group);
        loadGroup();
        GroupAdapter groupAdapter = new GroupAdapter(view.getContext(), R.layout.item_group, groupEntities);
        lv_group.setAdapter(groupAdapter);
        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), GroupActivity.class);
                //通过传小组id到下一个Activity，Activity然后向服务器获取小组信息
                intent.putExtra("groupId","");
                startActivity(intent);
            }
        });
        return view;
    }
    //加载列表
    void loadGroup() {
        groupEntities.add(new GroupEntity("小组1", new PersonEntity("小火车"),null));
        groupEntities.add(new GroupEntity("小组2", new PersonEntity("小火车"),null));
        groupEntities.add(new GroupEntity("小组3", new PersonEntity("小火车"),null));
        groupEntities.add(new GroupEntity("小组4", new PersonEntity("小火车"),null));
    }

}
