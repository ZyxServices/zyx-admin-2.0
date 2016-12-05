package com.zyx.parm.Equip;

import com.zyx.parm.QueryParam;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class QueryEquipLabelParam extends QueryParam{

    private Integer appType;

    private int state;

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
