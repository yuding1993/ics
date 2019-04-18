package org.wheport.ics.domain.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ComplaintInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 附件
     */
    private String fileUrl;

    /**
     * 内容
     */
    private String content;
}