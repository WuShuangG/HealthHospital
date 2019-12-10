package com.wd.common.core.base;

import android.os.Bundle;


import com.wd.common.core.constarn.IBaseView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        presenter = getpresenter();
        presenter.attachView(this);
        initData();
        initView();
        initListener();

    }

    protected abstract void initData();

    protected abstract P getpresenter();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
