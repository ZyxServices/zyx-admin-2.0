package com.zyx.parm.activity;

import com.zyx.parm.QueryParam;

import java.io.Serializable;

/**
 * Created by Rainbow on 16-6-13.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title com.zyx.entity.activity.parm
 */
public class QueryActivityParm extends QueryParam{

    private Integer createId;
    private Integer id;
    private String groupName;
    private Long startTime;
    private Long endTime;
    private Integer type;
    private  String title;
    private Integer activityType;
    private Integer paymentType;
    private Integer appType;
    private Integer status;
    private Long currentTime;


    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getActivityType() { return activityType; }

    public void setActivityType(Integer activityType) { this.activityType = activityType; }

    public Integer getPaymentType() { return paymentType; }

    public void setPaymentType(Integer paymentType) { this.paymentType = paymentType; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Integer getAppType() { return appType; }

    public void setAppType(Integer appType) { this.appType = appType; }

    public Long getCurrentTime() { return currentTime; }

    public void setCurrentTime(Long currentTime) { this.currentTime = currentTime; }
}
