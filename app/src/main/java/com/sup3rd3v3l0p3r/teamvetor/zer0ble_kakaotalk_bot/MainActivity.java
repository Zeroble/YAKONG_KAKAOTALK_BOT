package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String BOT_NAME = "야꿍봇";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v){
        String notifiPermission = Settings.Secure.getString(this.getContentResolver(), "enabled_notification_listeners");
        if((notifiPermission != null && !notifiPermission.contains("com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot")) || notifiPermission == null) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            Toast.makeText(this, "알림읽기 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),"시작됨",Toast.LENGTH_SHORT).show();
    }
}
