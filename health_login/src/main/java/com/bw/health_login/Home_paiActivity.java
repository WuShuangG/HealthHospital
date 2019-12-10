package com.bw.health_login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wd.common.core.base.BaseActivity;
import com.wd.common.core.base.BasePresenter;
import com.wd.common.core.encryption.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Home_paiActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_zheng_pai;
    private ImageView iv_home_pai;
    private TextView tv_home_zi;
    private TextView tv_home_du;
    private ImageView iv_home_xing;
    private Button home_xing_wan;
    private PopupWindow popupBigPhoto;
    private static int REQUEST_CAMERA=1;
    private String mFilePath;
    private Bitmap bitmap;
    private ImageView home_pai_iv_ben;


    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter getpresenter() {
        return new BasePresenter();
    }

    public void initView() {
        tv_zheng_pai = (TextView) findViewById(R.id.tv_zheng_pai);
        iv_home_pai = (ImageView) findViewById(R.id.iv_home_pai);
        tv_home_zi = (TextView) findViewById(R.id.tv_home_zi);
        tv_home_du = (TextView) findViewById(R.id.tv_home_du);
        iv_home_xing = (ImageView) findViewById(R.id.iv_home_xing);
        home_xing_wan = (Button) findViewById(R.id.home_xing_wan);
        home_pai_iv_ben = findViewById(R.id.home_pai_iv_ben);

        mFilePath = Environment.getExternalStorageDirectory().getPath();// 获取SD卡路径

        mFilePath = mFilePath + "/" + "temp.png";// 指定路径

        home_xing_wan.setOnClickListener(this);
        iv_home_pai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow();
            }
        });
        iv_home_xing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Home_paiActivity.this,Home_xingxiangActivity.class));
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_home_pai;
    }

    @Override
    public void onClick(View v) {

    }
    private void popupWindow() {
        View inflate = getLayoutInflater().inflate(R.layout.activity_home_pai, null);
        if (popupBigPhoto == null) {
            popupBigPhoto = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popupBigPhoto.setOutsideTouchable(true);
            popupBigPhoto.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
        }
        if (popupBigPhoto.isShowing()) {
            popupBigPhoto.dismiss();
        } else {
            popupBigPhoto.showAtLocation(inflate, Gravity.TOP, 0, 0);
        }
        popupBigPhoto.setOnDismissListener(new PopupWindow.OnDismissListener(){
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=1.0f;
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHND);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
        // 设置PopupWindow的背景
        popupBigPhoto.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupBigPhoto.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupBigPhoto.setTouchable(true);

        popupBigPhoto.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.alpha=0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        Button but_pai_ben = inflate.findViewById(R.id.home_pai_iv_ben);
        Button but_xiang = inflate.findViewById(R.id.iv_home_xing);
       // Button but_qu = inflate.findViewById(R.id.but_qu);
        but_pai_ben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机
                Uri photoUri = Uri.fromFile(new File(mFilePath)); // 传递路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
                startActivityForResult(intent, REQUEST_CAMERA);
                popupBigPhoto.dismiss();
            }
        });
        but_xiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType( "image/*" );
                startActivityForResult( intent,100 );
                popupBigPhoto.dismiss();
            }
        });
//        but_qu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupBigPhoto.dismiss();
//            }
//        });
    }
    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode == RESULT_OK) { // 如果返回数据
//                if (requestCode == REQUEST_CAMERA) {
//                    FileInputStream fis = null;
//                    try {
//                        fis = new FileInputStream(mFilePath); // 根据路径获取数据
//                        bitmap = BitmapFactory.decodeStream(fis);    //获取图片
//                        new Thread(new Runnable() {
//                            @Override
//                             public void run() {
//                             intentBitmap();
//                            }
//                        }).start();
//                        Intent intent=new Intent(Home_paiActivity.this,Home_wodeActivity.class);
//                        startActivity(intent);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }finally{
//                        try{
//                            fis.close();// 关闭流
//                        }catch(IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        Uri data1 = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap( getContentResolver(), data1 );
            home_pai_iv_ben.setImageBitmap( bitmap );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //传递bitmap
    private void intentBitmap() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        String imageBase64 = new String(Base64.encode(baos.toByteArray()));        //把字符串存到SharedPreferences里面
        SharedPreferences prePicture = getSharedPreferences("Picture", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prePicture.edit();
        editor.putString("cameraImage", imageBase64);
        editor.commit();
    }
}
