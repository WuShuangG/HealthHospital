package com.wd.common.core.http;

import com.wd.common.bean.LoginBean;
import com.wd.common.bean.ShengBean;
import com.wd.common.bean.YanzhengBean;
import com.wd.common.core.constan.Constant;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtils {
    //单例
    private static final HttpUtils ourInstance = new HttpUtils();
    private Constant constant;

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
        //拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit build1 = new Retrofit.Builder()
                .client(build)
                .baseUrl("http://172.17.8.100/health/")//内网接口
//                .baseUrl("http://mobile.bwstudent.com/health/")//外网接口
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        constant = build1.create(Constant.class);
    }



    //登录
    public void getlogin(Map<String, String> map, final ICallBack callback){
      constant.getLogin(map)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<LoginBean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(LoginBean loginBean) {
                    callback.success(loginBean);
                  }

                  @Override
                  public void onError(Throwable e) {

                  }

                  @Override
                  public void onComplete() {

                  }
              });
    }
    //发送验证码
    public void getYan(String email, final ICallBack callBack){
        constant.getyan(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YanzhengBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YanzhengBean yanzhengBean) {
                     callBack.success(yanzhengBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //申请入证
    public void getSheng(String email, String code,
                         String pwd1, String pwd2, String name, String inauguralHospital,
                         String departmentName, String jobTitle, String personalProfile,
                         String goodField, final ICallBack callBack){
        constant.getsheng(email,code,pwd1,pwd2,name,inauguralHospital,departmentName,jobTitle,personalProfile,goodField)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShengBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShengBean shengBean) {
                     callBack.success(shengBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public interface ICallBack<B>{
        void success(B b);
    }


}
