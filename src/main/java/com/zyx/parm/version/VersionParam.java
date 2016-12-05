package com.zyx.parm.version;

import com.zyx.parm.QueryParam;

/**
 * Created by HL on 2016/11/18.
 */
public class VersionParam extends QueryParam {
    private Integer platform;
    private Integer appType;

    public Integer getPlatform() { return platform; }

    public void setPlatform(Integer platform) { this.platform = platform; }

    public Integer getAppType() { return appType; }

    public void setAppType(Integer appType) { this.appType = appType; }
}
