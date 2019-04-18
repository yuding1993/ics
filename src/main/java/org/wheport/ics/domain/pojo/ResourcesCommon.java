package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class ResourcesCommon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * logo图片链接
     */
    private String logoImgLinkUrl;

    /**
     * ios图片
     */
    private String iosImgUrl;

    /**
     * android图片
     */
    private String androidImgUrl;

    /**
     * 底部文字
     */
    private String bottomFont;

    /**
     * 注册地址
     */
    private String regUrl;

    /**
     * 操作指南名称
     */
    private String userguideName;

    /**
     * 操作指南链接
     */
    private String userguideUrl;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateOperator;
}