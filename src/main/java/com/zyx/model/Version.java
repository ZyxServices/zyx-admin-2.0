package com.zyx.model;

/**
 * Created by HL on 2016/11/15.
 */

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 版本控制
 */
@Table(name = "version")
public class Version{
    private  Integer id;
    /**
     * 版本号
     */
    private  String number;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;
    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Long publishTime;
    /**
     * 下载地址
     */
    @Column(name = "download_url")
    private String downloadUrl;

    /**
     * 注释
     */
    @Column(name = "notes")
    private String notes;

    /**
     * 系统类型 0-安卓 1- ios
     */
    @Column(name = "system_type")
    private Integer systemType;

    /**
     * 删除（0，正常  1，删除）
     */
    private Integer del;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Long getPublishTime() { return publishTime; }

    public void setPublishTime(Long publishTime) { this.publishTime = publishTime; }

    public String getDownloadUrl() { return downloadUrl; }

    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public Integer getSystemType() { return systemType; }

    public void setSystemType(Integer systemType) { this.systemType = systemType; }

    public Integer getDel() { return del; }

    public void setDel(Integer del) { this.del = del; }
}
