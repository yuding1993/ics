package org.wheport.ics.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "rel_carousel_press")
public class RelCarouselPress implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 轮播图id
     */
    @Column(name = "carousel_id")
    private Integer carouselId;

    /**
     * 资讯id
     */
    @Column(name = "press_id")
    private Integer pressId;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 添加人
     */
    @Column(name = "add_operator")
    private String addOperator;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @Column(name = "update_operator")
    private String updateOperator;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取轮播图id
     *
     * @return carousel_id - 轮播图id
     */
    public Integer getCarouselId() {
        return carouselId;
    }

    /**
     * 设置轮播图id
     *
     * @param carouselId 轮播图id
     */
    public void setCarouselId(Integer carouselId) {
        this.carouselId = carouselId;
    }

    /**
     * 获取资讯id
     *
     * @return press_id - 资讯id
     */
    public Integer getPressId() {
        return pressId;
    }

    /**
     * 设置资讯id
     *
     * @param pressId 资讯id
     */
    public void setPressId(Integer pressId) {
        this.pressId = pressId;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取添加人
     *
     * @return add_operator - 添加人
     */
    public String getAddOperator() {
        return addOperator;
    }

    /**
     * 设置添加人
     *
     * @param addOperator 添加人
     */
    public void setAddOperator(String addOperator) {
        this.addOperator = addOperator;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     *
     * @return update_operator - 修改人
     */
    public String getUpdateOperator() {
        return updateOperator;
    }

    /**
     * 设置修改人
     *
     * @param updateOperator 修改人
     */
    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }
}