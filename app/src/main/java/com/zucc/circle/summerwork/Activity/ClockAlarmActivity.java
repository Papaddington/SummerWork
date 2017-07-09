package com.zucc.circle.summerwork.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zucc.circle.summerwork.R;
import com.zucc.circle.summerwork.View.SimpleDialog;

import static android.R.id.message;

public class ClockAlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_alarm);
        String message = this.getIntent().getStringExtra("msg");
        showDialogInBroadcastReceiver(message);
    }
    private void showDialogInBroadcastReceiver(String message) {
        final SimpleDialog dialog = new SimpleDialog(this, R.style.Theme_dialog);
        dialog.show();
        dialog.setTitle("闹钟提醒");
        dialog.setMessage(message);
        dialog.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.bt_confirm == v || dialog.bt_cancel == v) {
                    dialog.dismiss();
                    finish();
                }
            }
        });
    }
}
