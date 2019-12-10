package com.bw.health_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.health_login.R;


import com.bw.health_login.presenter.LoginPresenter;
import com.bw.health_login.presenter.RsaCoder;
import com.wd.common.bean.LoginBean;
import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.constarn.Constraint;


import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends BaseActivity<LoginPresenter> implements Constraint.ILoginView, View.OnClickListener {



    private ImageView iv_login_loginbg;
    private ImageView iv_login_mail;
    private EditText et_login_email;
    private LinearLayout root;
    private ImageView iv_login_lock;
    private EditText et_login_pwd;
    private ImageButton ib_login_eye;
    private LinearLayout root2;
    private View view;
    private TextView tv_login_forget;
    private TextView tv_login_apply;
    private Button btn_login_sign;
    private LinearLayout bg;
    private int flag = 0;

    @Override
    protected void initData() {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        ViewGroup rootView = getWindow().getDecorView().findViewById(R.id.bg);
        rootView.setPadding(0, getStatusBarHeight(), 0, 0);
         initView();
        readAccount();
    }


    @Override
    protected LoginPresenter getpresenter() {
        return new LoginPresenter();
    }


    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }
    public void initView() {
        iv_login_loginbg = (ImageView) findViewById(R.id.iv_login_loginbg);
        iv_login_mail = (ImageView) findViewById(R.id.iv_login_mail);
        et_login_email = (EditText) findViewById(R.id.et_login_email);
        root = (LinearLayout) findViewById(R.id.root);
        iv_login_lock = (ImageView) findViewById(R.id.iv_login_lock);
        et_login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        ib_login_eye = (ImageButton) findViewById(R.id.ib_login_eye);
        root2 = (LinearLayout) findViewById(R.id.root2);
        view = (View) findViewById(R.id.view);
        tv_login_forget = (TextView) findViewById(R.id.tv_login_forget);
        tv_login_apply = (TextView) findViewById(R.id.tv_login_apply);
        btn_login_sign = (Button) findViewById(R.id.btn_login_sign);
        bg = (LinearLayout) findViewById(R.id.bg);

        ib_login_eye.setOnClickListener(this);
        btn_login_sign.setOnClickListener(this);

        ib_login_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag<=0){
                    ib_login_eye.setActivated(false);
                    et_login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag=1;
                }else if (flag>=0){
                    ib_login_eye.setActivated(true);
                    et_login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag=0;
                }
            }
        });
        btn_login_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_login_email.getText().toString().trim();
                String pwd = et_login_pwd.getText().toString().trim();
                String encrypt = RsaCoder.encryptByPublicKey(pwd);
                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(encrypt)){
                    Toast.makeText(HomeActivity.this, "你的输入为空", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String,String>map=new HashMap<>();
                    map.put("email", email);
                    map.put("pwd", encrypt);
                    presenter.setlogin(map);
                }
                SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("email", email);
                edit.putString("pwd", pwd);
                edit.commit();
            }
        });
        tv_login_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,ShenqingActivity.class));
            }
        });
        tv_login_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,HomeWangMainActivity.class));
            }
        });
    }

    @Override
    public void success(LoginBean loginBean) {
        if (loginBean.getStatus().equals("0000")) {
            Intent intent = new Intent(HomeActivity.this, HomePageActivity.class);

            String name = loginBean.getResult().getName();
            String inauguralHospital = loginBean.getResult().getInauguralHospital();
            String jobTitle = loginBean.getResult().getJobTitle();
            String departmentName = loginBean.getResult().getDepartmentName();

            intent.putExtra("name",name);
            intent.putExtra("inauguralHospital",inauguralHospital);
            intent.putExtra("jobTitle",jobTitle);
            intent.putExtra("departmentName",departmentName);
            startActivity(intent);
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            String trim = et_login_pwd.getText().toString().trim();
            RsaCoder.encryptByPublicKey(trim);
            HomeActivity.this.finish();
            if (loginBean.getResult().getWhetherHaveImagePic() == 2) {
                Toast.makeText(this, "您还没有设置形象照哦", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(String msg) {

    }



    private void readAccount() {
        SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
        String email = info.getString("email", "");
        String pwd = info.getString("pwd", "");
        et_login_email.setText(email);
        et_login_pwd.setText(pwd);
    }

    @Override
    public void onClick(View v) {

    }



}
