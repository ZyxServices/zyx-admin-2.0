package com.zyx.parm.appUser;

import com.zyx.parm.QueryParam;

/**
 * Created by zjx on 2016/12/5.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class OpinionParam extends QueryParam {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * app类型：1-趣攀岩
     */
    private Integer appType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
