package com.zyx.parm.course;

import com.zyx.parm.QueryParam;

/**
 * Created by zjx on 2016/11/18.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class QueryCourseParam extends QueryParam{
    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图文类型
     */
    private String courseType;

    private Integer appType;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
