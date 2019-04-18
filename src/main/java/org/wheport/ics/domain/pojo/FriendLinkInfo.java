package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class FriendLinkInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 友情链接分类id
     */
    private Integer catId;

    /**
     * 友情链接名称
     */
    private String friendLinkName;

    /**
     * 友情链接地址
     */
    private String friendLinkUrl;

    /**
     * 友情链接图片
     */
    private String friendLinkImgUrl;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;
}