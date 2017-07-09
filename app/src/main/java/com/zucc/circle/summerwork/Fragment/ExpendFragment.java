package com.zucc.circle.summerwork.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zucc.circle.summerwork.Adapter.ExpendAdapter;
import com.zucc.circle.summerwork.Entity.ExpendEntity;
import com.zucc.circle.summerwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpendFragment extends Fragment {
    ListView lv_expend;
    ExpendAdapter expendAdapter;
    List<ExpendEntity> expendEntities;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expend, container, false);
        lv_expend = (ListView) view.findViewById(R.id.lv_expend);
        expendEntities = new ArrayList<>();
        expendEntities.add(new ExpendEntity("肯德基", "100", "吃饭", "无"));
        expendEntities.add(new ExpendEntity("肯德基", "100", "吃饭", "无"));
        expendEntities.add(new ExpendEntity("肯德基", "100", "吃饭", "无"));
        expendEntities.add(new ExpendEntity("肯德基", "100", "吃饭", "无"));
        expendAdapter = new ExpendAdapter(view.getContext(), R.layout.item_expend, expendEntities);
        lv_expend.setAdapter(expendAdapter);
        return view;
    }

}
