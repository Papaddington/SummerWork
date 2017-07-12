package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.zucc.circle.summerwork.Contants.ContantUri;
import com.zucc.circle.summerwork.Entity.PersonEntity;
import com.zucc.circle.summerwork.MainActivity;
import com.zucc.circle.summerwork.MyApplication;
import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.Util.StringUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity implements View.OnClickListener {
    private TextView btn_login, btn_regist;
    private LinearLayout ll_login, ll_regist;
    private Button btn_login_app, btn_regist_app;
    private EditText et_login_phonenumber, et_login_password;
    private EditText et_regist_phonenumber, et_regist_username, et_regist_usermail, et_regist_password, et_regist_check_password;
    private TextView tv_is_phone, tv_is_mail, tv_is_check_password;
    private TextView tv_test;
    private ImageView iv_wechat;
    private MyApplication myApplication;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myApplication = (MyApplication)this.getApplication();
        btn_login = (TextView) findViewById(R.id.btn_login);
        btn_regist = (TextView) findViewById(R.id.btn_regist);
        btn_login_app = (Button) findViewById(R.id.btn_login_app);
        btn_regist_app = (Button) findViewById(R.id.btn_regist_app);
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        ll_regist = (LinearLayout) findViewById(R.id.ll_regist);
        et_login_phonenumber = (EditText) findViewById(R.id.et_login_phonenumber);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_regist_check_password = (EditText) findViewById(R.id.et_regist_check_password);
        et_regist_password = (EditText) findViewById(R.id.et_regist_password);
        et_regist_phonenumber = (EditText) findViewById(R.id.et_regist_phonenumber);
        et_regist_username = (EditText) findViewById(R.id.et_regist_username);
        et_regist_usermail = (EditText) findViewById(R.id.et_regist_usermail);
        tv_is_phone = (TextView) findViewById(R.id.is_phone);
        tv_is_mail = (TextView) findViewById(R.id.is_user_mail);
        tv_is_check_password = (TextView) findViewById(R.id.is_check_password);
        tv_test = (TextView) findViewById(R.id.test);
        iv_wechat = (ImageView) findViewById(R.id.iv_wechat);
        btn_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
        btn_login_app.setOnClickListener(this);
        btn_regist_app.setOnClickListener(this);
        iv_wechat.setOnClickListener(this);
//        sharedPreferences= getSharedPreferences("test",
//                Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        //用putString的方法保存数据
//        editor.putBoolean("USER_ISLOGIN",true);
//        editor.putString("USER_ID", "11111111");
//        editor.putString("USER_PWD", "222222");
//        //提交当前数据
//        editor.commit();

        sharedPreferences= getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String userId=sharedPreferences.getString("USER_ID","");
        String userPwd=sharedPreferences.getString("USER_PWD","");
        boolean isLogin=sharedPreferences.getBoolean("USER_ISLOGIN",false);
        if(!isLogin) {
            //判断用户是否登录
            Log.e("","userId---->"+userId);
            Log.e("","userPwd---->"+userPwd);
            Log.e("","isLogin---->"+isLogin);
        }
        et_regist_phonenumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str="^(0(10|2\\d|[3-9]\\d\\d)[- ]{0,3}\\d{7,8}|0?1[3584]\\d{9})$";
                Pattern p = Pattern.compile(str);
                Matcher m = p.matcher(editable.toString());
                if(!m.matches()) {
                    tv_is_phone.setVisibility(View.VISIBLE);
                }
                else {
                    tv_is_phone.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_regist_usermail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str="^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
                Pattern p = Pattern.compile(str);
                Matcher m = p.matcher(editable.toString());
                if(!m.matches()) {
                    tv_is_mail.setVisibility(View.VISIBLE);
                }
                else {
                    tv_is_mail.setVisibility(View.INVISIBLE);
                }
            }
        });
        et_regist_check_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!(et_regist_check_password.getText().toString().equals(et_regist_password.getText().toString()))) {
                    tv_is_check_password.setVisibility(View.VISIBLE);
                }
                else {
                    tv_is_check_password.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                switchLoginMenu();
                break;
            case R.id.btn_regist:
                switchRegistMenu();
                break;
            case R.id.btn_login_app:
                UserLogin();
                break;
            case R.id.btn_regist_app:
                UserRegist();
                break;
            case R.id.iv_wechat:
                wxLogin();
                break;
            default:
                break;
        }
    }
    //切换登录菜单
    private void switchLoginMenu(){
        btn_login.setBackgroundResource(R.drawable.textview_bg);
        btn_login.setTextColor(Color.parseColor("#ffffff"));
        btn_regist.setBackgroundResource(R.drawable.textview_white_bg);
        btn_regist.setTextColor(Color.parseColor("#0f68c5"));
        ll_regist.setVisibility(View.GONE);
        ll_login.setVisibility(View.VISIBLE);
    }

    //切换注册菜单
    private void switchRegistMenu(){
        btn_regist.setBackgroundResource(R.drawable.textview_bg);
        btn_regist.setTextColor(Color.parseColor("#ffffff"));
        btn_login.setBackgroundResource(R.drawable.textview_white_bg);
        btn_login.setTextColor(Color.parseColor("#0f68c5"));
        ll_regist.setVisibility(View.VISIBLE);
        ll_login.setVisibility(View.GONE);
    }

    //判断登录逻辑
    public void UserLogin() {
        String phonenumber = et_login_phonenumber.getText().toString().trim();
        String password = et_login_password.getText().toString().trim();
        if (null == phonenumber || StringUtils.isEmpty(phonenumber)){
            //手机号为空
            Toast.makeText(LoginActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == password || StringUtils.isEmpty(password)){
            //密码为空
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        Login(phonenumber, password);
    }
    //判断注册逻辑
    public void UserRegist() {
        String phonenumber = et_regist_phonenumber.getText().toString().trim();
        String usermail = et_regist_usermail.getText().toString().trim();
        String password = et_regist_password.getText().toString().trim();
        String check_password = et_regist_check_password.getText().toString().trim();
        String username = et_regist_username.getText().toString().trim();
        if (null == phonenumber || StringUtils.isEmpty(phonenumber)){
            Toast.makeText(LoginActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == username || StringUtils.isEmpty(username)){
            //邮箱为空
            Toast.makeText(LoginActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == password || StringUtils.isEmpty(password)){
            //密码为空
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == check_password || StringUtils.isEmpty(check_password)){
            //再次密码为空
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == username || StringUtils.isEmpty(username)){
            //用户名为空
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(check_password)) {
            //两次密码输入不一样
            Toast.makeText(LoginActivity.this,"两次密码输入不相同",Toast.LENGTH_SHORT).show();
            return;
        }
        Regist(phonenumber, username, password, usermail);
    }
    //向服务器发起登录请求
    public void Login(String phonenumber, String password) {
        //创建String请求；第一个参数是地址，第二个参数指定请求方法
        Request<String> request = NoHttp.createStringRequest(ContantUri.LOGIN_URL, RequestMethod.POST);
        request.add("userphone", phonenumber);
        request.add("userpassword", password);
        //创建请求队列
        RequestQueue queue = MyApplication.getmRequestQueue();
        //请求回调
        OnResponseListener<String> callBack = new OnResponseListener<String>() {
            //这些方法都运行在主线程中，可以直接更新界面，同时也意味着不能做耗时操作
            @Override
            public void onStart(int what) {
                //发出请求时，开始执行的方法
            }
            @Override
            public void onSucceed(int what, Response<String> response) {
                //请求成功时执行的方法
                String json = response.get();
                try{
                    JSONObject jsonObject = new JSONObject(json);
                    String result = jsonObject.getString("result");
                    if (result.equals("001")){
                        JSONObject user = jsonObject.getJSONObject("user");
                        PersonEntity appuser;
                        appuser = new PersonEntity(user.getString("username"));
                        appuser.setUsermailbox(user.getString("usermailbox"));
                        appuser.setUserwxname(user.getString("userwxname"));
                        myApplication.setUser(appuser);
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else if (result.equals("002")){
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"账号不存在",Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                //请求失败时执行的方法
            }

            @Override
            public void onFinish(int what) {
                //请求结束时执行的方法
            }
        };
        //将网络请求添加到请求队列中；第一个参数：请求的标识，标记是哪个请求；第二个参数：请求对象；第三个参数：回调对象
        queue.add(0, request, callBack);
    }
    //向服务器发起注册请求
    public void Regist(String phonenumber, String username, String password, String usermail) {
        Request<String> request = NoHttp.createStringRequest(ContantUri.REGIST_URL, RequestMethod.POST);
        request.add("","");
        RequestQueue queue = MyApplication.getmRequestQueue();
        OnResponseListener<String> callBack = new OnResponseListener<String>() {

            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                switchLoginMenu();
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

    public void wxLogin() {
        if (!MyApplication.mWxApi.isWXAppInstalled()) {
            Log.d("","微信不存在");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        MyApplication.mWxApi.sendReq(req);
    }
}
