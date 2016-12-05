package com.zyx.dto;

import java.io.Serializable;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          意见反馈的展示实体类
 */
public class OpinionListDto implements Serializable {

    private Integer id;
    /**
     * 意见内容
     */
    private String content;

    /**
     * 发表日期
     */
    private Long createTime;

    /**
     * 用户对象
     */
    private AppUserListDto user;

    private Integer appType;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public AppUserListDto getUser() {
        return user;
    }

    public void setUser(AppUserListDto user) {
        this.user = user;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
