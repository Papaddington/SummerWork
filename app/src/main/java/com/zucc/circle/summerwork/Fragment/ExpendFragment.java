package com.zucc.circle.summerwork.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Activity.AddExpendActivity;
import com.zucc.circle.summerwork.Activity.AddIncomeActivity;
import com.zucc.circle.summerwork.Adapter.ExpendAdapter;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.ExpendEntity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpendFragment extends Fragment implements View.OnClickListener{
    ListView lv_expend;
    ExpendAdapter expendAdapter;
    List<ExpendEntity> expendEntities;
    private MyApplication myApplication;
    Button btn_add_expend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expend, container, false);
        myApplication = (MyApplication) getActivity().getApplication();
        lv_expend = (ListView) view.findViewById(R.id.lv_expend);
        btn_add_expend = (Button) view.findViewById(R.id.btn_add_expend);
        expendEntities = new ArrayList<>();
        LoadExpendList();
        expendAdapter = new ExpendAdapter(view.getContext(), R.layout.item_expend, expendEntities);
        lv_expend.setAdapter(expendAdapter);
        btn_add_expend.setOnClickListener(this);
        return view;
    }


    public void LoadExpendList() {
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOAD_PERSON_EXPEND_URL, RequestMethod.POST);
        request.add("expenduserid",myApplication.getUser().getUserphone());
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
                    JSONArray expends = jsonObject.getJSONArray("expends");
                    for(int i = 0; i < expends.length(); i++) {
                        JSONObject expend = expends.getJSONObject(i);
                        ExpendEntity expendEntity = new ExpendEntity();
                        expendEntity.setName(expend.getString("expendname"));
                        expendEntity.setType(expend.getString("expendtype"));
                        expendEntity.setMoney(expend.getString("expendmoney"));
                        expendEntity.setStart_time(StringTime(expend.getString("expenddate")));
                        expendEntities.add(expendEntity);
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
            case R.id.btn_add_expend:
                Intent intent = new Intent();
                intent.setClass(view.getContext(), AddExpendActivity.class);
                view.getContext().startActivity(intent);
        }
    }

    public String StringTime(String time){
        time = time.replace("T","   ");
        time = time.substring(0,time.length() - 3);
        return time;
    }
}
