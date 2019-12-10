package com.wd.health_main.seach;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.health_main.R;
import com.wd.health_main.utils.DimensionUtils;

/**
 * Time:  2019-12-09
 * Author:  杨世博
 * Description:
 */
public class KylinSearchView_Out extends FrameLayout implements SearchExtendImpl {
    protected Context context;
    protected View seachView;
    protected ImageView ivSearch;
    protected TextView edtSearch;
    protected ViewGroup rlSearch;
    protected LinearLayout llSearch;
    /**
     *  属性定义
     */
    protected int seaBackgroup;
    protected int imgRid;
    protected float imgSize;
    protected String edtHint;
    protected int edtHintColor;
    protected float seaPadding;
    protected int showType;
    protected float edt_size;
    /**
     *  事件
     */
    protected OnSearchListener onSearchListener;
    protected OnSearchFocusListener onSearchFocusListener;


    public KylinSearchView_Out(Context context) {
        super(context);
        init();
    }

    public KylinSearchView_Out(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public KylinSearchView_Out(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    /**
     * 设置属性
     */
    private void initAttrs(Context context, AttributeSet attrs)  {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.kylin_search_style);

        imgRid = typedArray.getInteger(R.styleable.kylin_search_style_img_src,R.drawable.serch);
        // 默认的float是px，所以要转成dp
        imgSize = typedArray.getDimension(R.styleable.kylin_search_style_img_size,  DimensionUtils.dip2px(context,24));
        edtHint = typedArray.getString(R.styleable.kylin_search_style_edt_hint);
        edtHintColor = typedArray.getColor(R.styleable.kylin_search_style_edt_hint, getResources().getColor(R.color.get_gray_code));
        seaBackgroup = typedArray.getInteger(R.styleable.kylin_search_style_search_backgroup,R.drawable.bg_search_default);
        seaPadding = typedArray.getDimension(R.styleable.kylin_search_style_img_size, DimensionUtils.dip2px(context,8));
        showType = typedArray.getInteger(R.styleable.kylin_search_style_show_location,0);
        edt_size = typedArray.getDimension(R.styleable.kylin_search_style_edt_size, 18);

        typedArray.recycle();

    }

    /**
     *  初始化操作
     */
    private void init(){
        // 提供自定义样式的钩子
        int layoutId = getLayoutId();
        if (layoutId == -1){
            seachView = LayoutInflater.from(getContext()).inflate(R.layout.layout_base_seach_out,this,false);
            this.addView(seachView);
            ivSearch = (ImageView) seachView.findViewById(R.id.iv_search);
            edtSearch = (TextView) seachView.findViewById(R.id.edt_search);
            rlSearch = (RelativeLayout) seachView.findViewById(R.id.rl_search);
            llSearch = (LinearLayout) seachView.findViewById(R.id.ll_search);
            initView();
        }else {
            seachView = LayoutInflater.from(getContext()).inflate(layoutId,this,false);
            initChildView();
            this.addView(seachView);
            ivSearch = getImageView();
            edtSearch = getEditText();
            rlSearch = getSearchFrame();
        }
        // 初始化事件监听
        initAllListener();
    }

    @Override
    public int getLayoutId(){
        return -1;
    }

    @Override
    public ImageView getImageView(){
        return null;
    }

    @Override
    public EditText getEditText(){
        return null;
    }

    @Override
    public ViewGroup getSearchFrame(){
        return null;
    }

    @Override
    public void initChildView() {}


    protected void initView() {
        // 初始化搜索边框
        rlSearch.setBackgroundResource(seaBackgroup);
        rlSearch.setPadding((int) seaPadding, (int) seaPadding, (int) seaPadding, (int) seaPadding);

        ViewGroup.LayoutParams llLp = llSearch.getLayoutParams();
        if (showType == 0){
            ((RelativeLayout.LayoutParams) llLp).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        }else if (showType == 1) {
            ((RelativeLayout.LayoutParams) llLp).addRule(RelativeLayout.CENTER_IN_PARENT);
        }else if (showType == 2){
            ((RelativeLayout.LayoutParams) llLp).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        llSearch.setLayoutParams(llLp);

        // 初始化图片
        ViewGroup.LayoutParams lp = ivSearch.getLayoutParams();
        lp.width = (int) imgSize;
        lp.height = (int) imgSize;
        ivSearch.setLayoutParams(lp);
        ivSearch.setImageResource(imgRid);

        // 初始化输入框
        edtHint = (edtHint == null || edtHint == "" || edtHint.equals(null) || edtHint.equals(""))
                ? "请输入搜索内容" : edtHint;
        edtSearch.setHint(edtHint);
        edtSearch.setHintTextColor(edtHintColor);
        edtSearch.setTextSize(edt_size);


    }

    protected void initAllListener(){
        final InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);

    }

    /**
     *  获取搜索框的内容
     */
    public String getSearchContent(){
        if (edtSearch == null){
            return null;
        }
        return edtSearch.getText().toString();
    }

    /**
     *  清空搜索框
     */
    public void clearSearch(){
        edtSearch.setText("");
    }

    /**
     *  让EditText失去焦点
     */
    public void lostRocus(){
        if(edtSearch != null) {
            edtSearch.setFocusable(false);
        }
    }

    /**
     *  搜索
     */
    public void search(){
        lostRocus();
        if (onSearchListener != null){
            onSearchListener.search(getSearchContent());
        }
    }

    public void setOnSearchListener(OnSearchListener onSearchListener) {
        this.onSearchListener = onSearchListener;
    }

    public void setOnSearchFocusListener(OnSearchFocusListener onSearchFocusListener) {
        this.onSearchFocusListener = onSearchFocusListener;
    }

    public TextView getSearchEditText() {
        return edtSearch;
    }

    public ImageView getSearchImageView() {
        return ivSearch;
    }

    public ViewGroup getSearchFrameView() {
        return rlSearch;
    }

    public void setImgSize(float size){
        ViewGroup.LayoutParams lp = ivSearch.getLayoutParams();
        lp.width = (int) size;
        lp.height = (int) size;
        ivSearch.setLayoutParams(lp);
    }

    public void setTextSize(float size){
        edtSearch.setTextSize(size);
    }
}
