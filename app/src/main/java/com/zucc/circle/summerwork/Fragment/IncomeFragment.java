package com.zucc.circle.summerwork.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zucc.circle.summerwork.Adapter.IncomeAdapter;
import com.zucc.circle.summerwork.Entity.IncomeEntity;
import com.zucc.circle.summerwork.R;

import java.util.ArrayList;
import java.util.List;


public class IncomeFragment extends Fragment {
    ListView lv_income;
    IncomeAdapter incomeAdapter;
    List<IncomeEntity> incomeEntities;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        lv_income = (ListView) view.findViewById(R.id.lv_income);
        incomeEntities = new ArrayList<>();
        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
        incomeAdapter = new IncomeAdapter(view.getContext(), R.layout.item_income, incomeEntities);
        lv_income.setAdapter(incomeAdapter);
        return view;
    }

}
