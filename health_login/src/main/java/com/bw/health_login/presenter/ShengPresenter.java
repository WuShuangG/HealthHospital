package com.bw.health_login.presenter;


import com.wd.common.bean.ShengBean;
import com.wd.common.core.base.BasePresenter;
import com.wd.common.core.constarn.Constraint;
import com.wd.common.core.http.HttpUtils;


public class ShengPresenter extends BasePresenter<Constraint.ISheng> {
    public void  setSheng(String email, String code,
                          String pwd1, String pwd2, String name, String inauguralHospital,
                          String departmentName, String jobTitle, String personalProfile,
                          String goodField){
        HttpUtils.getInstance().getSheng(email, code, pwd1, pwd2, name, inauguralHospital, departmentName, jobTitle, personalProfile, goodField, new HttpUtils.ICallBack<ShengBean>() {
            @Override
            public void success(ShengBean shengBean) {
            getView().success(shengBean);
            }

        });
    }
}
