package org.wheport.ics.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "rel_module_action_cat")
public class RelModuleActionCat implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 模块id
     */
    @Column(name = "module_id")
    private Integer moduleId;

    /**
     * 功能分类id
     */
    @Column(name = "action_cat_id")
    private Integer actionCatId;

    /**
     * 图片路径
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 图片路径2
     */
    @Column(name = "img_url2")
    private String imgUrl2;

    /**
     * 英文名
     */
    @Column(name = "english_name")
    private String englishName;

    /**
     * 是否启用 y/n
     */
    private String status;

    /**
     * 关联排序
     */
    private Integer sort;

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
     * 获取模块id
     *
     * @return module_id - 模块id
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块id
     *
     * @param moduleId 模块id
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 获取功能分类id
     *
     * @return action_cat_id - 功能分类id
     */
    public Integer getActionCatId() {
        return actionCatId;
    }

    /**
     * 设置功能分类id
     *
     * @param actionCatId 功能分类id
     */
    public void setActionCatId(Integer actionCatId) {
        this.actionCatId = actionCatId;
    }

    /**
     * 获取图片路径
     *
     * @return img_url - 图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片路径
     *
     * @param imgUrl 图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取图片路径2
     *
     * @return img_url2 - 图片路径2
     */
    public String getImgUrl2() {
        return imgUrl2;
    }

    /**
     * 设置图片路径2
     *
     * @param imgUrl2 图片路径2
     */
    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    /**
     * 获取英文名
     *
     * @return english_name - 英文名
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 设置英文名
     *
     * @param englishName 英文名
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * 获取是否启用 y/n
     *
     * @return status - 是否启用 y/n
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置是否启用 y/n
     *
     * @param status 是否启用 y/n
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取关联排序
     *
     * @return sort - 关联排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置关联排序
     *
     * @param sort 关联排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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