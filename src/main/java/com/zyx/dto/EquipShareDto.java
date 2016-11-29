package com.zyx.dto;

import com.zyx.model.vo.UserVo;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zjx on 2016/11/21.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class EquipShareDto implements Serializable{

    /**
     * 装备控id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    @Column(name="create_time")
    private Long createTime;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帖子类型。0用户帖子；1官方帖子
     */
    @Column(name = "equip_type")
    private Integer equipType;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getAvatar() { return avatar; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Integer getEquipType() { return equipType; }

    public void setEquipType(Integer equipType) { this.equipType = equipType; }
}
