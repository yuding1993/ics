package org.wheport.ics.domain.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "system_log")
public class SystemLog implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 方法描述
     */
    @Column(name = "method_desc")
    private String methodDesc;

    /**
     * 结果 成功s失败f
     */
    @Column(name = "method_result")
    private String methodResult;

    /**
     * 操作人
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
     * 获取方法名
     *
     * @return method_name - 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名
     *
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取方法描述
     *
     * @return method_desc - 方法描述
     */
    public String getMethodDesc() {
        return methodDesc;
    }

    /**
     * 设置方法描述
     *
     * @param methodDesc 方法描述
     */
    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    /**
     * 获取结果 成功s失败f
     *
     * @return method_result - 结果 成功s失败f
     */
    public String getMethodResult() {
        return methodResult;
    }

    /**
     * 设置结果 成功s失败f
     *
     * @param methodResult 结果 成功s失败f
     */
    public void setMethodResult(String methodResult) {
        this.methodResult = methodResult;
    }

    /**
     * 获取操作人
     *
     * @return user_id - 操作人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作人
     *
     * @param userId 操作人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作人姓名
     *
     * @return user_name - 操作人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作人姓名
     *
     * @param userName 操作人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}