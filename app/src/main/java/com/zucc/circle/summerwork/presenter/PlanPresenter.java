package com.zucc.circle.summerwork.presenter;

import com.zucc.circle.summerwork.presenter.view.IPersonPlan;

/**
 * Created by yuritian on 2017/7/13.
 */

public class PlanPresenter extends BasePresenter<IPersonPlan>{
    public void finishPlan(int planid){
        getView().finishPlan(planid);
    }
    public void deletePlan(int planid){
        getView().deletePlan(planid);
    }
}
