package com.zucc.circle.summerwork;

import android.app.Application;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.zucc.circle.summerwork.Entity.PersonEntity;

/**
 * Created by 圆圈 on 2017-07-03.
 */

public class MyApplication extends Application {
    //请求队列
    private static RequestQueue mRequestQueue;
    public static RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
    public static IWXAPI mWxApi;
    public PersonEntity user;

    public PersonEntity getUser() {
        return user;
    }

    public void setUser(PersonEntity user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this); // NoHttp默认初始化。
//        Logger.setDebug(true); // 开启NoHttp调试模式。
//        Logger.setTag("NoHttpSample"); // 设置NoHttp打印Log的TAG。
        mRequestQueue = NoHttp.newRequestQueue();
        registToWX();
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this,"wx4a6dd69bc75de4fe" , false);
        // 将该app注册到微信
        mWxApi.registerApp("wx4a6dd69bc75de4fe");
    }
}