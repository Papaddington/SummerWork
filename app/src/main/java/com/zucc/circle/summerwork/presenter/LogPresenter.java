package com.zucc.circle.summerwork.presenter;

import android.view.View;

import com.zucc.circle.summerwork.presenter.view.ICircleView;

/**
 * Created by yuritian on 2017/4/18.
 */

public class LogPresenter extends BasePresenter<ICircleView> {
    public void deleLog(int logid){
        getView().deleLog(logid);
    }

}
