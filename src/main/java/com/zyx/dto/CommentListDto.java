package com.zyx.dto;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by zjx on 2016/11/21.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 */
public class CommentListDto implements Serializable{

    private Integer id;

    /**
     * 回复类型，0：圈子,1:帖子，2：活动，3：动态，4：直播,5:装备贴
     */
    @Column(name = "comment_type")
    private Integer commentType;

    /**
     * 主体id，根据comment_type而决定是哪个主体id
     */
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "comment_account")
    private Integer commentAccount;

    @Column(name = "create_time")
    private Long createTime;

    /**
     * 显示方式，0：公开回复
     */
    @Column(name = "comment_state")
    private Integer commentState;

    @Column(name = "comment_content")
    private String commentContent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentAccount() {
        return commentAccount;
    }

    public void setCommentAccount(Integer commentAccount) {
        this.commentAccount = commentAccount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
