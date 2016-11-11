package com.zyx.model;

import javax.persistence.*;

/**
 * Created by zjx on 2016/11/9.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          教程攻略实体类
 */
@Table(name = "t_course")
public class Course extends BaseModel{
    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 教程标题
     */
    private String title;

    /**
     * 教程内容
     */
    private String content;

    /**
     * 教程类型：图文、视频
     */
    @Column(name="course_type")
    private String courseType;

    /**
     * 标签id
     */
    @Column(name="label_id")
    private Integer labelId;


    /**
     * 教程图片
     */
    @Column(name="img_urls")
    private String imgUrl;

    /**
     * 发布时间
     */
    @Column(name="create_time")
    private Long createTime;

    /**
     * 是否删除：0正常；1删除
     */
//    private Integer del;

    /**
     * 是否屏蔽：0正常；1屏蔽
     */
    private Integer mask;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private Integer userId;

    /***********************   get/set方法  **********************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

//    public int getDel() {
//        return del;
//    }

//    public void setDel(Integer del) {

//        this.del = del;
//    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**********************   toString  ***********************/


}
