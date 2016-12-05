package com.zyx.model;

import javax.persistence.*;

/**
 * Created by zjx on 2016/11/15.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          城市管理实体类
 */
@Table(name="city")
public class City {
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 城市名字
     */
    @Column(name="city_name")
    private String cityName;

    /**
     * 创建日期
     */
    @Column(name="create_time")
    private Long createTime;

    /**
     * 启用状态：0启用、1禁用
     */
    private int state;

    /**
     * app类型 1为趣攀岩
     */
    @Column(name = "app_type")
    private Integer appType;

    /**********************************  get/set方法   *********************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
