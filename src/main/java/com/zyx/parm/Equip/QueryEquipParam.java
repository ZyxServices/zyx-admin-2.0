package com.zyx.parm.Equip;

import com.zyx.parm.QueryParam;

/**
 * Created by zjx on 2016/11/21.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *         装备帖的查询参数
 */
public class QueryEquipParam extends QueryParam{
    /**
     * 标题
     */
    private String title;

    /**
     * 标签id
     */
    private Integer labelId;

    private Integer equipType;

    private Integer appType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getEquipType() {
        return equipType;
    }

    public void setEquipType(Integer equipType) {
        this.equipType = equipType;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
