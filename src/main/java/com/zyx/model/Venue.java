package com.zyx.model;

import javax.persistence.*;


/**
 * 场馆
 * Created by HL on 2016/11/7.
 */
@Table(name="t_venue")
public class Venue extends  BaseModel{

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 场馆名称
     */
    private  String name;

    /**
     * 经度
     */
    private  Double longitude;
    /**
     * 纬度
     */
    private  Double  latitude;
    /**
     * 冗余标注所属城市便于展示
     */
    private  String city;
    /**
     * 场馆类型 1-室内 2-室外
      */
    private  Integer type;
    /**
     * 描述标签
    */
    private  String mark;
    /**
     * 场馆介绍
     */
    private  String description;
    /**
     * 场馆地址
     */
    private  String address;
    /**
     * 场馆封面图片，可以多张图片，每个url之间用”,“隔开
     */
    private  String imgUrls;
    /**
     * 联系电话
     */
    private  String phone;
    /**
     * 场馆综合难度等级
     */
    private  String level;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private  Long createTime;

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getLatitude() { return latitude; }

    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public String getMark() { return mark; }

    public void setMark(String mark) { this.mark = mark; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getImgUrls() { return imgUrls; }

    public void setImgUrls(String imgUrls) { this.imgUrls = imgUrls; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }
}
