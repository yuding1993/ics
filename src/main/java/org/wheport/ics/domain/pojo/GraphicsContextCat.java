package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class GraphicsContextCat extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 父分类id
     */
    private Integer parentCatId;
}