package com.zyx.model;

import javax.persistence.*;

/**
 * Created by HL on 2016/11/17.
 */

/**
 * 运营端操作日志
 */
@Table(name = "SYS_OPERATION_LOG")
public class Log{
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作描述
     */
    @Column(name = "operation_desc")
    private String operationDesc;

    /**
     * 操作时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 操作者id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 删除标记
     */
    private int del;

    /**
     * 操作成功状态 0-成功 1-失败
     */
    private Integer result;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取操作描述
     *
     * @return operation_desc - 操作描述
     */
    public String getOperationDesc() {
        return operationDesc;
    }

    /**
     * 设置操作描述
     *
     * @param operationDesc 操作描述
     */
    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }

    /**
     * 获取操作时间
     *
     * @return create_time - 操作时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作者id
     *
     * @return user_id - 操作者id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作者id
     *
     * @param userId 操作者id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取删除标记
     *
     * @return del - 删除标记
     */


    public int getDel() {
        return del;
    }
    /**
     * 设置删除标记
     *
     * @param del 删除标记
     */
    public void setDel(int del) {
        this.del = del;
    }

    public Integer getResult() { return result; }

    public void setResult(Integer result) { this.result = result; }
}
