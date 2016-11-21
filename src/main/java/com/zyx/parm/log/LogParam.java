package com.zyx.parm.log;

import com.zyx.parm.QueryParam;

/**
 * Created by HL on 2016/11/18.
 */
public class LogParam extends QueryParam{
    private Integer systemType;

    private Integer userId;


    public Integer getSystemType() { return systemType; }

    public void setSystemType(Integer systemType) { this.systemType = systemType; }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
