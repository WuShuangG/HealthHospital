package com.bw.health_login.presenter;


import com.wd.common.bean.YanzhengBean;
import com.wd.common.core.base.BasePresenter;
import com.wd.common.core.constarn.Constraint;
import com.wd.common.core.http.HttpUtils;

public class YanPresenter extends BasePresenter<Constraint.IYan> {
    public void setYan(String email){
        HttpUtils.getInstance().getYan(email, new HttpUtils.ICallBack<YanzhengBean>() {
            @Override
            public void success(YanzhengBean yanzhengBean) {
                getView().success(yanzhengBean);
            }
        });
    }
}
