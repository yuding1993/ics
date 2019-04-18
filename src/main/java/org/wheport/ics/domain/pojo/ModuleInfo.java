package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModuleInfo extends  BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 排列顺序
     */
    private Integer moduleSort;

    /**
     * 关联页面id
     */
    private Integer pageId;

    /**
     * 是否启用 y/n
     */
    private String workStatus;

    /**
     * 分页查询数
     */
    private Integer pageCount;
}