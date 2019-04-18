package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageConfig extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 页面链接
     */
    private String pageUrl;

    /**
     * 手机页面链接
     */
    private String mobileUrl;

    /**
     * app页面链接
     */
    private String appUrl;

    /**
     * 是否关联轮播图 y/n
     */
    private String isRelCarousel;

    /**
     * 是否关联资讯分类 y/n
     */
    private String isRelPressCat;

    /**
     * 是否关联功能分类 y/n
     */
    private String isRelActionCat;

    /**
     * 是否关联广告 y/n
     */
    private String isRelAdvert;

    /**
     * 是否关联图片 y/n
     */
    private String isRelImg;

    /**
     * 是否关联友情链接 y/n
     */
    private String isRelFriend;

    /**
     * 是否关联图文 y/n
     */
    private String isRelGraphics;
}