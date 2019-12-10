package com.bw.health_login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.health_login.presenter.RsaCoder;
import com.bw.health_login.presenter.ShengPresenter;
import com.wd.common.bean.ShengBean;
import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.constarn.Constraint;


public class Home_jianjie_Activity extends BaseActivity<ShengPresenter> implements Constraint.ISheng,View.OnClickListener {

    private ImageButton ib_applyr_black;
    private TextView jianjie;
    private EditText et_applyr_introduction;
    private TextView lingyu;
    private EditText et_applyr_good;
    private Button btn_applyo_next;


    @Override
    protected void initData() {

    }

    @Override
    protected ShengPresenter getpresenter() {
        return new ShengPresenter();
    }

    public void initView() {
        ib_applyr_black = (ImageButton) findViewById(R.id.ib_applyr_black);
        jianjie = (TextView) findViewById(R.id.jianjie);
        et_applyr_introduction = (EditText) findViewById(R.id.et_applyr_introduction);
        lingyu = (TextView) findViewById(R.id.lingyu);
        et_applyr_good = (EditText) findViewById(R.id.et_applyr_good);
        btn_applyo_next = (Button) findViewById(R.id.btn_applyo_next);

        ib_applyr_black.setOnClickListener(this);
        btn_applyo_next.setOnClickListener(this);
        ib_applyr_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home_jianjie_Activity.this.finish();
            }
        });
        btn_applyo_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intro = et_applyr_introduction.getText().toString();
                String good = et_applyr_good.getText().toString();
                if (TextUtils.isEmpty(intro)||TextUtils.isEmpty(good)){
                    Toast.makeText(Home_jianjie_Activity.this, "你的输入不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = getIntent();
                    String name = intent.getStringExtra("name");
                    String depaetment = intent.getStringExtra("depaetment");
                    String hospital = intent.getStringExtra("hospital");
                    String title = intent.getStringExtra("title");
                    String email = intent.getStringExtra("email");
                    String code = intent.getStringExtra("code");
                    String pwd1 = intent.getStringExtra("pwd1");
                    String encrypt = RsaCoder.encryptByPublicKey(pwd1);
                    String pwd2 = intent.getStringExtra("pwd2");
                    String encrypt1 = RsaCoder.encryptByPublicKey(pwd2);
                    presenter.setSheng(email, code, encrypt, encrypt1, name, hospital, depaetment, title,intro,good);
                    Intent intent1 = new Intent(Home_jianjie_Activity.this, Home_BackActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home_jianjie_;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(ShengBean shengBean) {
        if (shengBean.getStatus().equals("0000")){
            Toast.makeText(this, "请输入正确的信息", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "申请成功", Toast.LENGTH_SHORT).show();
    }
}
