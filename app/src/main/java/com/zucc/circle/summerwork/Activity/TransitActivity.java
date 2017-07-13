package com.zucc.circle.summerwork.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.zucc.circle.summerwork.R;

public class TransitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_transit, null);
        setContentView(view);
        /* -----------------------创建动画，界面从透明变为不透明，设置动画持续时间-------------------------- */
        AlphaAnimation start = new AlphaAnimation(0.3f,1.0f);
        start.setDuration(2000);

        /* -----------------------开始动画，设置监听函数，当动画结束时，开启登录界面-------------------------- */
        view.startAnimation(start);
        start.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try {
                    Intent intent = new Intent();
                    intent.setClass(TransitActivity.this,LoginActivity.class);
                    TransitActivity.this.startActivity(intent);
                    TransitActivity.this.finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
