package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class PressCat extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 父分类
     */
    private Integer parentCatId;

    /**
     * 所属模块id
     */
    private Integer belongModuleId;

    /**
     * 列表小图标
     */
    private String logoUrl;
}