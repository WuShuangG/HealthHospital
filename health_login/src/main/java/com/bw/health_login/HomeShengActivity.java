package com.bw.health_login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.health_login.R;

import com.bw.health_login.presenter.YanPresenter;
import com.wd.common.bean.YanzhengBean;
import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.constarn.Constraint;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeShengActivity extends BaseActivity<YanPresenter> implements Constraint.IYan, View.OnClickListener {

    private TextView home_wanshan;
    private TextView home_shenhetontguo;
    private TextView home_you;
    private EditText home_et_apply_name;
    private Button home_but_verificationcode;
    private TextView home_ninsuozaideyiyuan;
    private EditText home_et_apply_hospital;
    private TextView home_ninsuozaikeshi;
    private EditText home_et_apply_department;
    private TextView home_nindezhicheng;
    private EditText home_et_apply_title;
    private Button hone_btn_applyo_next;
    private TimeCount time;
    private int flag = 0;
    private ImageView home_sheng_mm_eye;
    private ImageView home_sheng_zaici_eye;
    private long verificationCode=0; //生成的验证码
    private String email; //邮箱
    public static Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配


    @Override
    protected void initData() {
        time = new TimeCount(30000, 1000);

    }

    @Override
    protected YanPresenter getpresenter() {
        return new YanPresenter();
    }

    public void initView() {
        home_sheng_mm_eye = findViewById(R.id.home_sheng_mm_eye);
        home_sheng_zaici_eye = findViewById(R.id.home_sheng_zaici_eye);
        home_wanshan = (TextView) findViewById(R.id.home_wanshan);
        home_shenhetontguo = (TextView) findViewById(R.id.home_shenhetontguo);
        home_you = (TextView) findViewById(R.id.home_you);
        home_et_apply_name = (EditText) findViewById(R.id.home_et_apply_name);
        home_but_verificationcode = (Button) findViewById(R.id.home_but_verificationcode);
        home_ninsuozaideyiyuan = (TextView) findViewById(R.id.home_ninsuozaideyiyuan);
        home_et_apply_hospital = (EditText) findViewById(R.id.home_et_apply_hospital);
        home_ninsuozaikeshi = (TextView) findViewById(R.id.home_ninsuozaikeshi);
        home_et_apply_department = (EditText) findViewById(R.id.home_et_apply_department);
        home_nindezhicheng = (TextView) findViewById(R.id.home_nindezhicheng);
        home_et_apply_title = (EditText) findViewById(R.id.home_et_apply_title);
        hone_btn_applyo_next = (Button) findViewById(R.id.hone_btn_applyo_next);

        home_but_verificationcode.setOnClickListener(this);
        hone_btn_applyo_next.setOnClickListener(this);
        hone_btn_applyo_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = home_et_apply_name.getText().toString();
                String trim1 = home_et_apply_department.getText().toString();
                String trim2 = home_et_apply_title.getText().toString();
                String trim3 = home_et_apply_hospital.getText().toString();
                if (TextUtils.isEmpty(trim)||TextUtils.isEmpty(trim1)||TextUtils.isEmpty(trim2)||TextUtils.isEmpty(trim3)){
                    Toast.makeText(HomeShengActivity.this, "请填写全部资料", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(HomeShengActivity.this, Home_jianjie_Activity.class);
                    intent.putExtra("email",trim);
                    intent.putExtra("code",trim1);
                    intent.putExtra("pwd1",trim2);
                    intent.putExtra("pwd2",trim3);
                    startActivity(intent);
                }
            }
        });
        home_but_verificationcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = home_et_apply_name.getText().toString();
                presenter.setYan(trim);
                boolean email = isEmail(home_et_apply_name.getText().toString());
                if (email==false){
                    Toast.makeText(HomeShengActivity.this, "邮箱格式错误", Toast.LENGTH_SHORT).show();
                }
                time.start();

            }
        });
        home_sheng_mm_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag <= 0) {
                    home_sheng_mm_eye.setActivated(false);
                    home_et_apply_department.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = 1;
                } else if (flag >= 0) {
                    home_sheng_mm_eye.setActivated(true);
                    home_et_apply_department.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = 0;
                }
            }
        });
        home_sheng_zaici_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag <= 0) {
                    home_sheng_zaici_eye.setActivated(false);
                    home_et_apply_title.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = 1;
                } else if (flag >= 0) {
                    home_sheng_zaici_eye.setActivated(true);
                    home_et_apply_title.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = 0;
                }
            }
        });


    }


    class TimeCount extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            home_but_verificationcode.setClickable(false);
            home_but_verificationcode.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            home_but_verificationcode.setText("重新获取验证码");
            home_but_verificationcode.setClickable(true);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home_sheng;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(YanzhengBean yanzhengBean) {
        if (yanzhengBean.equals("0000") && yanzhengBean != null) {
            Toast.makeText(this, "发送失败，请重新发送", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)){ return false;}
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        //Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
