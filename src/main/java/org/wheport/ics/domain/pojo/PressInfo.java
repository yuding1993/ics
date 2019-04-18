package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class PressInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资讯名称
     */
    private String pressName;

    /**
     * 所属分类
     */
    private Integer pressCatId;

    /**
     * 资讯图片
     */
    private String pressImgUrl;

    /**
     * 是否启用 y/n
     */
    private String workStatus;

    /**
     * 是否在app展示 y/n
     */
    private String isShowApp;

    /**
     * 在app展示为 新闻xw 公告gg
     */
    private String appShowType;

    /**
     * 摘要
     */
    private String pressDesc;

    /**
     * 资讯内容
     */
    private String pressContent;
}