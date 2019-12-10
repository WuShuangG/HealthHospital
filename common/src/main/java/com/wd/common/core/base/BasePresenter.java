package com.wd.common.core.base;


import com.wd.common.core.constarn.IBaseView;

public class BasePresenter<V extends IBaseView> {

    public V mV;

    public void attachView(V v) {
        mV = v;
    }

    public void detachView() {
        mV = null;
    }

    public V getView() {
        return mV;
    }

}
