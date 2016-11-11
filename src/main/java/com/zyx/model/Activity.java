package com.zyx.model;

import javax.persistence.*;

@Table(name = "activity")
public class Activity extends  BaseModel{
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建者ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动图片
     */
    @Column(name = "img_urls")
    private String imgUrls;
    /**
     * 活动介绍
     */
    @Column(name = "desc_content")
    private  String descContent;
    /**
     * 活动类型 1-求约 2-求带
     */
    @Column(name = "activity_type")
    private Integer activityType;
    /**
     * 活动模块 1-攀岩 2-跑步
     */
    @Column(name = "activity_module")
    private Integer activityModule;
    /**
     * 活动开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 活动结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

    /**
     * 报名截止时间
     */
    @Column(name = "last_time")
    private Long lastTime;
    /**
     * 活动地址
     */
    private String address;
    /**
     * 城市
     */
    private String city;

    /**
     * 活动人数上限
     */
    @Column(name = "max_people")
    private Integer maxPeople;
    /**
     * 活动价格
     */
    private Double price;
    /**
     * 活动分类（0  官方，  1 用户）
     */
    private Integer type;
    /**
     * 线上活动跳转地址
     */
    @Column(name = "target_url")
    private String targetUrl;
    /**
     * 付费类型 0奖励 1免费 2 AA
     */
    @Column(name="payment_type")
    private Integer paymentType;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    private Integer mask;

    /**
     * 活动状态 0-正在报名 1-结束
     */
    @Transient
    private Integer status;
    @Transient
    private  Long currentTime;

    /**
     * 获取表id
     *
     * @return id - 表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置表id
     *
     * @param id 表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建者ID
     *
     * @return user_id - 创建者ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置创建者ID
     *
     * @param userId 创建者ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取活动标题
     *
     * @return title - 活动标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置活动标题
     *
     * @param title 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取活动图片
     *
     * @return img_urls - 活动图片
     */
    public String getImgUrls() {
        return imgUrls;
    }

    /**
     * 设置活动图片
     *
     * @param imgUrls 活动图片
     */
    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    /**
     * 获取活动开始时间
     *
     * @return start_time - 活动开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置活动开始时间
     *
     * @param startTime 活动开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取活动结束时间
     *
     * @return end_time - 活动结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置活动结束时间
     *
     * @param endTime 活动结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取报名截止时间
     *
     * @return last_time - 报名截止时间
     */
    public Long getLastTime() {
        return lastTime;
    }

    /**
     * 设置报名截止时间
     *
     * @param lastTime 报名截止时间
     */
    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取活动人数上线
     *
     * @return max_people - 活动人数上线
     */
    public Integer getMaxPeople() {
        return maxPeople;
    }

    /**
     * 设置活动人数上线
     *
     * @param maxPeople 活动人数上线
     */
    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }
    /**
     * 获取活动价格
     *
     * @return price - 活动价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置活动价格
     *
     * @param price 活动价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取活动分类（0  线上活动，  1线下活动）
     *
     * @return type - 活动分类（0  线上活动，  1线下活动）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置活动分类（0  线上活动，  1线下活动）
     *
     * @param type 活动分类（0  线上活动，  1线下活动）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取线上活动跳转地址
     *
     * @return target_url - 线上活动跳转地址
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * 设置线上活动跳转地址
     *
     * @param targetUrl 线上活动跳转地址
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * 获取活动地址
     *
     * @return address - 活动地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置活动地址
     *
     * @param address 活动地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * 获取活动描述
     *
     * @return desc_content - 活动描述
     */
    public String getDescContent() {
        return descContent;
    }

    /**
     * 设置活动描述
     *
     * @param descContent 活动描述
     */
    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }

    public Integer getActivityType() { return activityType; }

    public void setActivityType(Integer activityType) { this.activityType = activityType; }

    public Integer getActivityModule() { return activityModule; }

    public void setActivityModule(Integer activityModule) { this.activityModule = activityModule; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Integer getPaymentType() { return paymentType; }

    public void setPaymentType(Integer paymentType) { this.paymentType = paymentType; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Long getCurrentTime() { return currentTime; }

    public void setCurrentTime(Long currentTime) { this.currentTime = currentTime; }
}