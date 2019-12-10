package com.wd.common.bean;

import java.util.List;

/**
 * <p>文件描述：bannerBean <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class BannerBean {

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {

        public String imageUrl;
        public String jumpUrl;
        public int rank;
        public String title;
    }
}
