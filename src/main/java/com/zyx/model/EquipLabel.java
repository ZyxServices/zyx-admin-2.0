package com.zyx.model;

import javax.persistence.*;

/**
 * Created by zjx on 2016/11/11.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          装备标签实体类
 */
@Table(name="t_equip_label")
public class EquipLabel {

    /**
     * 表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名字
     */
    @Column(name="label_name")
    private String labelName;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private Integer userId;

    /**************************** get /set 方法   ***************************************/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
