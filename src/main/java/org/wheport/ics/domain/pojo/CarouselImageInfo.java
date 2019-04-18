package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CarouselImageInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播图名称
     */
    private String carouselName;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 图片注释
     */
    private String imgDesc;

    /**
     * 分类id
     */
    private Integer carouselCatId;

    /**
     * 轮播顺序
     */
    private Integer playSort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;
}