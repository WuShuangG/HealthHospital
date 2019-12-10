package com.bw.health_login;

import android.content.Intent;
import android.os.Bundle;


import com.bw.health_login.HomeActivity;
import com.bw.health_login.R;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(StartActivity.this, HomeActivity.class));
                        StartActivity.this.finish();
                    }
                });
            }
        }).start();

    }
}
