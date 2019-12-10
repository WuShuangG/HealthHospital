package com.bw.health_login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.health_login.R;
import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.base.BasePresenter;


public class HomePageActivity extends BaseActivity implements View.OnClickListener {


    private ImageView imageone;
    private TextView iv_home_xin;
    private ImageView home_doctor;
    private ImageView imagetwo;
    private ImageView imagethree;
    private ImageView doctorimage;
    private TextView zeng;
    private TextView doctoraddress;
    private TextView doctorzhi;
    private TextView doctorsubject;
    private Button home_page_but_guan;


    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter getpresenter() {
        return new BasePresenter();
    }


    public void initView() {
        imageone = (ImageView) findViewById(R.id.imageone);
        iv_home_xin = (TextView) findViewById(R.id.iv_home_xin);
        home_doctor = (ImageView) findViewById(R.id.home_doctor);
        imagetwo = (ImageView) findViewById(R.id.imagetwo);
        imagethree = (ImageView) findViewById(R.id.imagethree);
        doctorimage = (ImageView) findViewById(R.id.doctorimage);
        zeng = (TextView) findViewById(R.id.zeng);
        doctoraddress = (TextView) findViewById(R.id.doctoraddress);
        doctorzhi = (TextView) findViewById(R.id.doctorzhi);
        doctorsubject = (TextView) findViewById(R.id.doctorsubject);
        home_page_but_guan = findViewById(R.id.home_page_but_guan);

        home_page_but_guan.setOnClickListener(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String inauguralHospital = intent.getStringExtra("inauguralHospital");
        String jobTitle = intent.getStringExtra("jobTitle");
        String departmentName = intent.getStringExtra("departmentName");
        zeng.setText(name);
        doctoraddress.setText(inauguralHospital);
        doctorzhi.setText(jobTitle);
        doctorsubject.setText(departmentName);

//        RoundedCorners roundedCorners = new RoundedCorners(10);
//        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners);
//        Glide.with(HomePageActivity.this)
//                .load(result.getImagePic())
//                .apply(options)
//                .into(mIvHomePic);
        home_page_but_guan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(HomePageActivity.this,Home_wodeActivity.class));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    public void onClick(View v) {

    }
}
