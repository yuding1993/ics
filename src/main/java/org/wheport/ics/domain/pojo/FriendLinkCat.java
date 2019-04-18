package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class FriendLinkCat extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 父分类id
     */
    private Integer parendCatId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人
     */
    private String addOperator;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateOperator;
}