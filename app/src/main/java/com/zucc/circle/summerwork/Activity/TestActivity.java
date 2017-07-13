package com.zucc.circle.summerwork.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.bikenavi.BikeNavigateHelper;
import com.baidu.mapapi.bikenavi.adapter.IBEngineInitListener;
import com.baidu.mapapi.bikenavi.adapter.IBRoutePlanListener;
import com.baidu.mapapi.bikenavi.model.BikeRoutePlanError;
import com.baidu.mapapi.bikenavi.params.BikeNaviLauchParam;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudManager;
import com.baidu.mapapi.cloud.CloudPoiInfo;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.cloud.LocalSearchInfo;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.zucc.circle.summerwork.R;
import java.util.ArrayList;

public class TestActivity extends Activity implements CloudListener{
    private BikeNavigateHelper  mNaviHelper;
    private BikeNaviLauchParam param;
    private static boolean isPermissionRequested = false;
    private LocationClient mLocationClient = null;
    public BDLocationListener myListener = null;
    private EditText et_search_address;
    double lati, longa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        /*-----------------初始化组件------------------*/
        SDKInitializer.initialize(this.getApplicationContext());
        requestPermission();
        CloudManager.getInstance().init(TestActivity.this);
        mLocationClient = new LocationClient(getApplicationContext());
        initLocation();
        myListener = new MyLocationListener();
        mNaviHelper = BikeNavigateHelper.getInstance();
        setContentView(R.layout.activity_test);
        Button button = (Button) findViewById(R.id.button);
        et_search_address = (EditText) findViewById(R.id.et_search_address);
        et_search_address.setText("体育");
       /*-----------------初始化组件完毕------------------*/

       /*-----------------创建本地位置监听----------------*/
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();

        /*---------------点击按钮开始导航----------------*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInfo(et_search_address.getText().toString());
                LatLng startPt = new LatLng(30.326678, 120.164154);
                LatLng endPt = new LatLng(lati, longa);

                param = new BikeNaviLauchParam().stPt(startPt).endPt(endPt);
                startBikeNavi();
            }
        });

    }
    /*---------------LBS搜索结果监听---------------------*/
    @Override
    public void onGetSearchResult(CloudSearchResult cloudSearchResult, int i) {
        for (CloudPoiInfo info : cloudSearchResult.poiList) {
            Log.e("当前位置为",info.address);

            lati = info.latitude;
            longa = info.longitude;
            Log.e("当前纬度为","" + lati);
            Log.e("当前经度为","" + longa);
        }
    }

    @Override
    public void onGetDetailSearchResult(DetailSearchResult detailSearchResult, int i) {

    }

    @Override
    public void onGetCloudRgcResult(CloudRgcResult cloudRgcResult, int i) {

    }
    /*-------------初始化获取当前位置--------------*/
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //就是这个方法设置为true，才能获取当前的位置信息
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("WGS84");//可选，默认gcj02，设置返回的定位结果坐标系
        //int span = 1000;
        //option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClient.setLocOption(option);
    }

    private void startBikeNavi() {
        Log.d("View", "startBikeNavi");
        mNaviHelper.initNaviEngine(this, new IBEngineInitListener() {
            @Override
            public void engineInitSuccess() {
                Log.d("View", "engineInitSuccess");
                routePlanWithParam();
            }

            @Override
            public void engineInitFail() {
                Log.d("View", "engineInitFail");
            }
        });
    }

    private void routePlanWithParam() {
        mNaviHelper.routePlanWithParams(param, new IBRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                Log.d("View", "onRoutePlanStart");
            }

            @Override
            public void onRoutePlanSuccess() {
                Log.d("View", "onRoutePlanSuccess");
                Intent intent = new Intent();
                intent.setClass(TestActivity.this, BNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(BikeRoutePlanError error) {
                Log.d("View", "onRoutePlanFail");
            }

        });
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !isPermissionRequested) {

            isPermissionRequested = true;

            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (permissions.size() == 0) {
                return;
            } else {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 0);
            }
        }
    }

    private void searchInfo(String myaddress) {
        LocalSearchInfo info = new LocalSearchInfo();
        info.ak = "qO2ldNNCFjGvcxICVP0Ull3KagUDd1Cm";
        info.geoTableId = 171769;
        info.tags = "";
        info.q = myaddress;
        info.region = "杭州市";
        CloudManager.getInstance().localSearch(info);
    }

    /* -----------------自定义位置监听函数-------------*/
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            //经纬度
            //lati = location.getLatitude();
            //longa = location.getLongitude();
            //打印出当前位置
            Toast.makeText(TestActivity.this,"经纬"+ lati + "    " + longa,Toast.LENGTH_SHORT).show();
            //Toast.makeText(TestActivity.this,"location.getAddrStr()="+ location.getAddrStr(),Toast.LENGTH_SHORT).show();
            //Log.i("TAG", "location.getAddrStr()=" + location.getAddrStr());
            //打印出当前城市
            Log.i("TAG", "location.getCity()=" + location.getCity());
            //返回码
            int i = location.getLocType();
            Log.i("TAG", "错误码" + i);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }


}
