package com.wd.common.core.constarn;


import com.wd.common.bean.BannerBean;
import com.wd.common.bean.LoginBean;
import com.wd.common.bean.ShengBean;
import com.wd.common.bean.YanzhengBean;

public interface Constraint {

    interface IHomeView extends IBaseView{
        //banner
        void IHomeBannerSuccess(BannerBean bannerBean);
        void IHomeBannerError(String s);
    }
    interface ILoginView extends IBaseView{
        void success(LoginBean loginBean);
        void error(String msg);
    }
    interface IYan extends IBaseView{
        void success(YanzhengBean yanzhengBean);
    }
    interface ISheng extends IBaseView{
        void success(ShengBean shengBean);
    }

}
