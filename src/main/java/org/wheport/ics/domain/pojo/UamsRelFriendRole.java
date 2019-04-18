package org.wheport.ics.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "uams_rel_friend_role")
public class UamsRelFriendRole implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 友情链接id
     */
    @Column(name = "link_id")
    private Integer linkId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * 获取友情链接id
     *
     * @return link_id - 友情链接id
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * 设置友情链接id
     *
     * @param linkId 友情链接id
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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