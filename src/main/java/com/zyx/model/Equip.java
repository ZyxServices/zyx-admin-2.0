package com.zyx.model;

import com.zyx.model.vo.UserVo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zjx on 2016/11/11.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          装备贴实体类
 */
@Table(name="equip")
public class Equip extends BaseModel{

    /**
     * 装备控id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 账户id
     */
    @Column(name="account_id")
    private Integer accountId;

    /**
     * 发布人
     */
    @Transient
    private UserVo account;

    /**
     * 标签id
     */
    @Column(name="label_id")
    private Integer labelId;

    /**
     * 标签名字
     */
    @Transient
    private String equipLabelName;

    /**
     * 是否屏蔽：0正常 1屏蔽
     */
    private int mask;

    /**
     * 评论数
     */
    @Transient
    private int commentCount;

    /**
     * 点赞数
     */
    @Transient
    private int zanCount;

    /**
     * 帖子类型。0用户帖子；1官方帖子
     */
    private int equipType;
    /**
     * 评论对象集合
     */
    @Transient
    private List<Comment> commentList;



    /***********************  get/set方法   *********************************/

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getZanCount() {
        return zanCount;
    }

    public void setZanCount(int zanCount) {
        this.zanCount = zanCount;
    }

    public int getEquipType() {
        return equipType;
    }

    public void setEquipType(int equipType) {
        this.equipType = equipType;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public UserVo getAccount() {
        return account;
    }

    public void setAccount(UserVo account) {
        this.account = account;
    }

    public String getEquipLabelName() {
        return equipLabelName;
    }

    public void setEquipLabelName(String equipLabelName) {
        this.equipLabelName = equipLabelName;
    }
}
