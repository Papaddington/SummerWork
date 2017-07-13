package com.zucc.circle.summerwork.presenter.view;

/**
 * Created by yuritian on 2017/4/18.
 */

public interface IBaseView {
    void showLoading(String msg);
    void hideLoading();
    void showError(String errorMsg);
}
