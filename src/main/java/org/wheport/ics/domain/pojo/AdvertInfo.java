package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdvertInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告名称
     */
    private String advertName;

    /**
     * 广告图片
     */
    private String imgUrl;

    /**
     * 广告链接
     */
    private String linkUrl;
}