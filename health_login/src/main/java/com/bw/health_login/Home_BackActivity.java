package com.bw.health_login;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;



import androidx.appcompat.app.AppCompatActivity;

public class Home_BackActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ib_applyf_black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__back);
        initView();
    }

    private void initView() {
        ib_applyf_black = (ImageButton) findViewById(R.id.ib_applyf_black);
        ib_applyf_black.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Home_BackActivity.this, HomeActivity.class));
        finish();
    }
}

