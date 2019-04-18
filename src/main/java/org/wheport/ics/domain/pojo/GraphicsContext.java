package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class GraphicsContext extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图文名称
     */
    private String name;

    /**
     * 图文链接
     */
    private String linkUrl;

    /**
     * 图文图片
     */
    private String imgUrl;

    /**
     * 图文分类
     */
    private Integer catId;

    /**
     * 图文排序
     */
    private Integer sort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;

    /**
     * 图文内容
     */
    private String content;
}