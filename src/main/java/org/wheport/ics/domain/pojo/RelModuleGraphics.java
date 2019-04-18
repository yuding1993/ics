package org.wheport.ics.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "rel_module_graphics")
public class RelModuleGraphics implements Serializable {
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
     * 图文分类id
     */
    @Column(name = "graphics_cat_id")
    private Integer graphicsCatId;

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
     * 获取图文分类id
     *
     * @return graphics_cat_id - 图文分类id
     */
    public Integer getGraphicsCatId() {
        return graphicsCatId;
    }

    /**
     * 设置图文分类id
     *
     * @param graphicsCatId 图文分类id
     */
    public void setGraphicsCatId(Integer graphicsCatId) {
        this.graphicsCatId = graphicsCatId;
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