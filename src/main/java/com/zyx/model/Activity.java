package com.zyx.model;

import javax.persistence.*;

@Table(name = "activity")
public class Activity{
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
     * 删除（0，正常  1，删除）
     */
    private Integer del;

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
     * app类型 1为趣攀岩
     */
    @Column(name = "app_type")
    private Integer appType;
    /**
     * 活动状态 0-正在报名 1-结束
     */
    @Transient
    private Integer status;
    @Transient
    private  Long currentTime;
    /**
     * 活动地址纬度
     */
    private double latitude;
    /**
     * 活动地址经度
     */
    private double longitude;


    public Integer getAppType() { return appType; }

    public void setAppType(Integer appType) { this.appType = appType; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getImgUrls() { return imgUrls; }

    public void setImgUrls(String imgUrls) { this.imgUrls = imgUrls; }

    public String getDescContent() { return descContent; }

    public void setDescContent(String descContent) { this.descContent = descContent; }

    public Integer getActivityType() { return activityType; }

    public void setActivityType(Integer activityType) { this.activityType = activityType; }

    public Integer getActivityModule() { return activityModule; }

    public void setActivityModule(Integer activityModule) { this.activityModule = activityModule; }

    public Long getStartTime() { return startTime; }

    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public Long getEndTime() { return endTime; }

    public void setEndTime(Long endTime) { this.endTime = endTime; }

    public Long getLastTime() { return lastTime; }

    public void setLastTime(Long lastTime) { this.lastTime = lastTime; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Integer getDel() { return del; }

    public void setDel(Integer del) { this.del = del; }

    public Integer getMaxPeople() { return maxPeople; }

    public void setMaxPeople(Integer maxPeople) { this.maxPeople = maxPeople; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public String getTargetUrl() { return targetUrl; }

    public void setTargetUrl(String targetUrl) { this.targetUrl = targetUrl; }

    public Integer getPaymentType() { return paymentType; }

    public void setPaymentType(Integer paymentType) { this.paymentType = paymentType; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Integer getMask() { return mask; }

    public void setMask(Integer mask) { this.mask = mask; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Long getCurrentTime() { return currentTime; }

    public void setCurrentTime(Long currentTime) { this.currentTime = currentTime; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }
}