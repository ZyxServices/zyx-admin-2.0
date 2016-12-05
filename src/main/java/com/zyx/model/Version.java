package com.zyx.model;

/**
 * Created by HL on 2016/11/15.
 */

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 版本控制
 */
@Table(name = "t_version")
public class Version{
    private  Integer id;
    /**
     * 版本号
     */
    private  String version;
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
    private Integer platform;

    /**
     * 删除（0，正常  1，删除）
     */
    private Integer del;

    /**
     * app类型 1为趣攀岩
     */
    @Column(name = "app_type")
    private Integer appType;
    /**
     * 1正式版 2内测版 3公测版
     */
    private Integer type;

    /**
     * 1 最新版本 2过期版本 -1停用版本
     */
    private Integer state;

    public Integer getAppType() { return appType; }

    public void setAppType(Integer appType) { this.appType = appType; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Long getPublishTime() { return publishTime; }

    public void setPublishTime(Long publishTime) { this.publishTime = publishTime; }

    public String getDownloadUrl() { return downloadUrl; }

    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public Integer getDel() { return del; }

    public void setDel(Integer del) { this.del = del; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public Integer getPlatform() { return platform; }

    public void setPlatform(Integer platform) { this.platform = platform; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public Integer getState() { return state; }

    public void setState(Integer state) { this.state = state; }
}
