package com.zyx.dto;

/**
 * Created by HL on 2016/11/8.
 */
public class VenueDto {

    private Integer id;

    private  String name;

    private  Integer type;

    private  String description;

    private  String mark;

    private  String city;

    private  String phone;

    private  String address;

    private  String level;

    private  String imgUrls;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getMark() { return mark; }

    public void setMark(String mark) { this.mark = mark; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getImgUrls() { return imgUrls; }

    public void setImgUrls(String imgUrls) { this.imgUrls = imgUrls; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }
}
