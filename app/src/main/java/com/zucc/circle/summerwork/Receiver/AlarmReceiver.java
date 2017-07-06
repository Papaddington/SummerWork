package com.zucc.circle.summerwork.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 圆圈 on 2017-07-05.
 */

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MainActivity", "接收自定义动态注册广播消息");
        Toast.makeText(context, "short alarm", Toast.LENGTH_LONG).show();
    }
}
