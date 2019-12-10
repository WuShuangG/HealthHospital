package com.bw.health_login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.health_login.R;
import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.base.BasePresenter;
import com.wd.common.core.constarn.IBaseView;


public class ShenqingActivity extends BaseActivity implements IBaseView,View.OnClickListener {

    private TextView wanshanxinxi;
    private TextView shenhetontguo;
    private TextView tianxiezhengque;
    private TextView nindezhenshixingming;
    private EditText et_apply_name;
    private TextView ninsuozaideyiyuan;
    private EditText et_apply_hospital;
    private TextView ninsuozaikeshi;
    private EditText et_apply_department;
    private ImageView home_shengqing_ke_drop;
    private TextView nindezhicheng;
    private EditText et_apply_title;
    private ImageView home_shengqing_cheng_drop;
    private Button btn_applyo_next;
    private ListPopupWindow listPopupWindow;

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter getpresenter() {
        return new BasePresenter();
    }

    public void initView() {
        wanshanxinxi = (TextView) findViewById(R.id.wanshanxinxi);
        shenhetontguo = (TextView) findViewById(R.id.shenhetontguo);
        tianxiezhengque = (TextView) findViewById(R.id.tianxiezhengque);
        nindezhenshixingming = (TextView) findViewById(R.id.nindezhenshixingming);
        et_apply_name = (EditText) findViewById(R.id.et_apply_name);
        ninsuozaideyiyuan = (TextView) findViewById(R.id.ninsuozaideyiyuan);
        et_apply_hospital = (EditText) findViewById(R.id.et_apply_hospital);
        ninsuozaikeshi = (TextView) findViewById(R.id.ninsuozaikeshi);
        et_apply_department = (EditText) findViewById(R.id.et_apply_department);
        home_shengqing_ke_drop = (ImageView) findViewById(R.id.home_shengqing_ke_drop);
        nindezhicheng = (TextView) findViewById(R.id.nindezhicheng);
        et_apply_title = (EditText) findViewById(R.id.et_apply_title);
        home_shengqing_cheng_drop = (ImageView) findViewById(R.id.home_shengqing_cheng_drop);
        btn_applyo_next = (Button) findViewById(R.id.btn_applyo_next);

        btn_applyo_next.setOnClickListener(this);
        et_apply_department.setOnClickListener(this);

        et_apply_department.setCursorVisible(false);
        et_apply_department.setFocusable(false);
        et_apply_department.setFocusableInTouchMode(false);
        et_apply_title.setCursorVisible(false);
        et_apply_title.setFocusable(false);
        et_apply_title.setFocusableInTouchMode(false);

        btn_applyo_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = et_apply_name.getText().toString();
                String depaetment = et_apply_department.getText().toString();
                String hospital = et_apply_hospital.getText().toString();
                String title = et_apply_title.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(depaetment)||TextUtils.isEmpty(hospital)||TextUtils.isEmpty(title)){
                    Toast.makeText(ShenqingActivity.this, "请填写全部资料", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(ShenqingActivity.this, HomeShengActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("depaetment",depaetment);
                    intent.putExtra("hospital",hospital);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }
            }
        });
        et_apply_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String [] list={"内科", "眼科", "骨科", "儿科", "传染病科", "皮肤病科", "耳鼻喉科", "精神病科"};
                final ListPopupWindow listPopupWindow;
                listPopupWindow = new ListPopupWindow(ShenqingActivity.this);
                listPopupWindow.setAdapter(new ArrayAdapter<String>(ShenqingActivity.this,android.R.layout.simple_list_item_1,list));
                listPopupWindow.setAnchorView(et_apply_department);
                listPopupWindow.setModal(true);
                et_apply_department.setSelection(et_apply_department.getText().length());
                listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      et_apply_department.setText(list[position]);
                      listPopupWindow.dismiss();
                    }
                });
                listPopupWindow.show();
                et_apply_department.setCursorVisible(false);
            }
        });
        et_apply_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] list1 = {"主任医师", "主治医师", "副主任医师"};
                final ListPopupWindow listPopupWindow1;
                listPopupWindow1 = new ListPopupWindow(ShenqingActivity.this);
                listPopupWindow1.setAdapter(new ArrayAdapter<String>(ShenqingActivity.this, android.R.layout.simple_list_item_1, list1));
                listPopupWindow1.setAnchorView(et_apply_title);
                listPopupWindow1.setModal(true);
                et_apply_title.setSelection(et_apply_title.getText().length());
                listPopupWindow1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        et_apply_title.setText(list1[i]);
                        listPopupWindow1.dismiss();
                    }
                });
                listPopupWindow1.show();
                et_apply_title.setCursorVisible(false);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_shenqing;
    }


    @Override
    public void onClick(View v) {

    }
}
