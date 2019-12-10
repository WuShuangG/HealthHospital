package com.bw.health_login.presenter;


import com.wd.common.bean.LoginBean;

import com.wd.common.core.base.BasePresenter;
import com.wd.common.core.constarn.Constraint;
import com.wd.common.core.http.HttpUtils;


import java.util.Map;

public class LoginPresenter extends BasePresenter<Constraint.ILoginView> {
    public void setlogin(Map<String,String>map){
        HttpUtils.getInstance().getlogin(map, new HttpUtils.ICallBack<LoginBean>() {
            @Override
            public void success(LoginBean loginBean) {
                getView().success(loginBean);
            }
        });
    }
}
