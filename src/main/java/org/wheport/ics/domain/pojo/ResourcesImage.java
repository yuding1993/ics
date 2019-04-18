package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class ResourcesImage extends BaseInfo implements Serializable {

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 资源路径
     */
    private String resourceUrl;
}