package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RelModulePress extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块id
     */
    private Integer moduleId;

    /**
     * 资讯分类id
     */
    private Integer pressCatId;

    /**
     * 关联图片
     */
    private String relImgUrl;

    /**
     * 关联排序
     */
    private Integer sort;
}