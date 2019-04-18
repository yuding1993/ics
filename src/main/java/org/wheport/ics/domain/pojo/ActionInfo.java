package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActionInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能名称
     */
    private String actionName;

    /**
     * 功能链接
     */
    private String actionLinkUrl;

    /**
     * 功能分类id
     */
    private Integer actionCatId;

    /**
     * 功能排序
     */
    private Integer actionSort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;

    /**
     * 登陆限制 无限制no，普通登陆pt，ic卡登陆ic
     */
    private String loginType;

    /**
     * ic卡限制是否跳转qp
     */
    private String doesTurnQp;

    /**
     * 是否有后缀 y/n
     */
    private String hasSuffix;

    /**
     * 后缀参数名
     */
    private String suffixParamName;

    /**
     * 功能图标
     */
    private String imgUrl;
}