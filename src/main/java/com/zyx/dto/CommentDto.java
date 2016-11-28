package com.zyx.dto;

/**
 * Created by HL on 2016/11/25.
 */
public class CommentDto {
    private Integer id;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论图片
     */
    private String commentImgPath;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 等级名称
     */
    private String level;
    /**
     * 留言时间
     */
    private Long createTime;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCommentContent() { return commentContent; }

    public void setCommentContent(String commentContent) { this.commentContent = commentContent; }

    public String getCommentImgPath() { return commentImgPath; }

    public void setCommentImgPath(String commentImgPath) { this.commentImgPath = commentImgPath; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getAvatar() { return avatar; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }
}
