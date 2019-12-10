package com.bw.health_login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home_wodeActivity extends AppCompatActivity  implements View.OnClickListener{

    private ImageView my_image;
    private ImageView my_back;
    private ImageView my_message;
    private TextView my_query;
    private ImageView fang;
    private ImageView my_history;
    private TextView text_history;
    private RelativeLayout re_home_wode_li;
    private ImageView my_wallet;
    private TextView text_wallet;
    private RelativeLayout re_home_wode_qian;
    private ImageView my_suggest;
    private TextView text_suggest;
    private RelativeLayout re_home_wode_jian;
    private ImageView my_reply;
    private TextView text_reply;
    private RelativeLayout re_home_wode_zidong;
    private PopupWindow popupBigPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wode);
        initView();
    }

    private void initView() {
        my_image = (ImageView) findViewById(R.id.my_image);
        my_back = (ImageView) findViewById(R.id.my_back);
        my_message = (ImageView) findViewById(R.id.my_message);
        my_history = (ImageView) findViewById(R.id.my_history);
        my_query = (TextView) findViewById(R.id.my_query);
        fang = (ImageView) findViewById(R.id.fang);
        text_history = (TextView) findViewById(R.id.text_history);
        re_home_wode_li = (RelativeLayout) findViewById(R.id.re_home_wode_li);
        my_wallet = (ImageView) findViewById(R.id.my_wallet);
        text_wallet = (TextView) findViewById(R.id.text_wallet);
        re_home_wode_qian = (RelativeLayout) findViewById(R.id.re_home_wode_qian);
        my_suggest = (ImageView) findViewById(R.id.my_suggest);
        text_suggest = (TextView) findViewById(R.id.text_suggest);
        re_home_wode_jian = (RelativeLayout) findViewById(R.id.re_home_wode_jian);
        my_reply = (ImageView) findViewById(R.id.my_reply);
        text_reply = (TextView) findViewById(R.id.text_reply);
        re_home_wode_zidong = (RelativeLayout) findViewById(R.id.re_home_wode_zidong);

        my_image.setOnClickListener(this);
        my_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow();
            }
        });
    }
    private void popupWindow() {
        View inflate = getLayoutInflater().inflate(R.layout.activity_home_photo, null);
        if (popupBigPhoto == null) {
            popupBigPhoto = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popupBigPhoto.setOutsideTouchable(true);
            popupBigPhoto.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
        }
        if (popupBigPhoto.isShowing()) {
            popupBigPhoto.dismiss();
        } else {
            popupBigPhoto.showAtLocation(inflate, Gravity.TOP, 0, 0);
        }
        popupBigPhoto.setOnDismissListener(new PopupWindow.OnDismissListener(){
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=1.0f;
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHND);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
        // 设置PopupWindow的背景
        popupBigPhoto.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupBigPhoto.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupBigPhoto.setTouchable(true);

        popupBigPhoto.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        Button buttongenghuan = inflate.findViewById(R.id.button_genghuan);
        Button buttonqvxiao = inflate.findViewById(R.id.button_qvxiao);
        buttongenghuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_wodeActivity.this, Home_paiActivity.class);
                startActivity(intent);
                popupBigPhoto.dismiss();
            }
        });
        buttonqvxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupBigPhoto.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
