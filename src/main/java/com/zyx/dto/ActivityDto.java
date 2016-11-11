package com.zyx.dto;

public class ActivityDto {

    private Integer id;

    private Integer userId;

    private String title;

    private String imgUrls;

    private  String descContent;

    private Integer activityType;

    private Integer activityModule;

    private Long startTime;

    private Long endTime;

    private Long lastTime;

    private String address;

    private String city;

    private Integer maxPeople;

    private Double price;

    private Integer type;

    private String targetUrl;

    private Integer paymentType;

    private Long createTime;

    private Integer mask;

    private  String userName;

    private  boolean isDeva;

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

    public boolean getIsDeva() {
        return isDeva;
    }

    public void setIsDeva(boolean isDeva) {
        this.isDeva = isDeva;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }
}