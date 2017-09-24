package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    final static String BOT_NAME = "야꿍봇";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public interface PS_RE {
        @FormUrlEncoded
        @POST("/")
        Call<ResponseBody> test(@Field("content") String content);

        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://prometasv.com:6974")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public void start(View v) {
        String notifiPermission = Settings.Secure.getString(this.getContentResolver(), "enabled_notification_listeners");
        if ((notifiPermission != null && !notifiPermission.contains("com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot")) || notifiPermission == null) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            Toast.makeText(this, "알림읽기 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "시작됨", Toast.LENGTH_SHORT).show();
    }

    public void server(View v) {
        MainActivity.PS_RE apiRequest = MainActivity.PS_RE.retrofit.create(MainActivity.PS_RE.class);
        Call<ResponseBody> call = apiRequest.test("플멭qt");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseStr = "";
                JSONObject json;
                try {
                    responseStr = response.body().string();
                    Log.i("TAG",responseStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    json = new JSONObject(responseStr);
                    if (json.getInt("result") == 1) {
                        json.getString("content");
                    } else {
                        //아무일도 없음(요청보냈는데 딱히 응답할내용없음)
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
