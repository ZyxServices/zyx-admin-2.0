package com.zyx.dto;

/**
 * Created by HL on 2016/11/25.
 */
public class RankDto {
    /**
     * 排名
     */
    private Integer number;
    private Integer userId;
    /**
     * 荣誉值
     */
    private Integer totalScore;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

    private String levelName;

    public Integer getNumber() { return number; }

    public void setNumber(Integer number) { this.number = number; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTotalScore() { return totalScore; }

    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }

    public String getAvatar() { return avatar; }

    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getLevelName() { return levelName; }

    public void setLevelName(String levelName) { this.levelName = levelName; }
}
