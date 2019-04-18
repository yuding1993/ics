package org.wheport.ics.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class VideoInfo extends BaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频图片
     */
    private String imgUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 排列顺序
     */
    private Integer sort;

    /**
     * 是否启用 y/n
     */
    private String workStatus;
}