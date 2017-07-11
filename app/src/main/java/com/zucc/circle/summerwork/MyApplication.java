package com.zucc.circle.summerwork;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.RequestQueue;

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
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this); // NoHttp默认初始化。
//        Logger.setDebug(true); // 开启NoHttp调试模式。
//        Logger.setTag("NoHttpSample"); // 设置NoHttp打印Log的TAG。
        mRequestQueue = NoHttp.newRequestQueue();
        registToWX();
        SDKInitializer.initialize(this);
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this,"wx4a6dd69bc75de4fe" , false);
        // 将该app注册到微信
        mWxApi.registerApp("wx4a6dd69bc75de4fe");
    }
}