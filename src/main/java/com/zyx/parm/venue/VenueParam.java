package com.zyx.parm.venue;

import com.zyx.parm.QueryParam;

/**
 * Created by HL on 2016/11/18.
 */
public class VenueParam extends QueryParam{
    private Integer type;
    private String city;
    private String mark;
    private String name;

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getMark() { return mark; }

    public void setMark(String mark) { this.mark = mark; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
