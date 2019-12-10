package com.wd.common.core.constan;


import com.wd.common.bean.BannerBean;
import com.wd.common.bean.LoginBean;
import com.wd.common.bean.ShengBean;
import com.wd.common.bean.YanzhengBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Constant {

    //banner接口
    @GET("share/v1/bannersShow")
    Observable<BannerBean> BANNER_BEAN();
    //登录
    @FormUrlEncoded
    @POST("doctor/v1/login")
    Observable<LoginBean> getLogin(@FieldMap Map<String, String> map);
    //发送验证码
    @FormUrlEncoded
    @POST("doctor/v1/sendEmailCode")
    Observable<YanzhengBean> getyan(@Field("email") String email);
    @FormUrlEncoded
    @POST("doctor/v1/applyJoin")
    Observable<ShengBean> getsheng(@Field("email") String email, @Field("code") String code,
                                   @Field("pwd1") String pwd1, @Field("pwd2") String pwd2,
                                   @Field("name") String name, @Field("inauguralHospital") String inauguralHospital,
                                   @Field("departmentName") String departmentName,
                                   @Field("jobTitle") String jobTitle,
                                   @Field("personalProfile") String personalProfile,
                                   @Field("goodField") String goodField);
}
