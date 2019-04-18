package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class RelActionUsertype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能id
     */
    private Integer actionId;

    /**
     * 角色类型id
     */
    private String usertypeId;
}