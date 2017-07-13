package com.zucc.circle.summerwork.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Activity.AddIncomeActivity;
import com.zucc.circle.summerwork.Activity.LoginActivity;
import com.zucc.circle.summerwork.Adapter.IncomeAdapter;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.IncomeEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class IncomeFragment extends Fragment implements View.OnClickListener{
    ListView lv_income;
    IncomeAdapter incomeAdapter;
    List<IncomeEntity> incomeEntities;
    Button btn_add_income;
    private MyApplication myApplication;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        myApplication = (MyApplication) getActivity().getApplication();
        lv_income = (ListView) view.findViewById(R.id.lv_income);
        btn_add_income = (Button) view.findViewById(R.id.btn_add_income);
        incomeEntities = new ArrayList<>();
//        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
//        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
//        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
//        incomeEntities.add(new IncomeEntity("第一桶金", "100", "投资", "无"));
        incomeAdapter = new IncomeAdapter(view.getContext(), R.layout.item_income, incomeEntities);
        lv_income.setAdapter(incomeAdapter);
        btn_add_income.setOnClickListener(this);
        return view;
    }
    public void LoadIncomeList() {
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOAD_PERSON_INCOME_URL, RequestMethod.POST);
        request.add("incomeuserid",myApplication.getUser().getUserphone());
        RequestQueue queue = MyApplication.getmRequestQueue();
        OnResponseListener<String> callBack = new OnResponseListener<String>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String json = response.get();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray incomes = jsonObject.getJSONArray("incomes");
                    for(int i = 0; i < incomes.length(); i++) {
                        JSONObject income = incomes.getJSONObject(i);
                        IncomeEntity incomeEntity = new IncomeEntity();
                        incomeEntity.setName(income.getString("incomename"));
                        incomeEntity.setType(income.getString("incometype"));
                        incomeEntity.setMoney(income.getString("incomemoney"));
                        incomeEntity.setStart_time(income.getString("incomedate"));
                        incomeEntities.add(incomeEntity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        };
        queue.add(0, request, callBack);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_income:
                Intent intent = new Intent();
                intent.setClass(view.getContext(), AddIncomeActivity.class);
                view.getContext().startActivity(intent);
        }
    }
}
