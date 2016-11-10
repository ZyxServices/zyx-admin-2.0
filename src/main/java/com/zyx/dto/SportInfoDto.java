package com.zyx.dto;

/**
 * Created by HL on 2016/11/9.
 */
public class SportInfoDto {


    private  Integer id;

    private  Integer venueId;

    private  String path;

    private  String url;

    private  String level;

    private  Integer score;

    private  String pathType;

    private  String developer;

    private  Long developTime;

    private  Integer pathLength;
    /**
     * 多少人去过
     */
    private Integer visitNum;
    /**
     * 评论数量
     */
    private Integer commentNum;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getVenueId() { return venueId; }

    public void setVenueId(Integer venueId) { this.venueId = venueId; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public String getDeveloper() { return developer; }

    public void setDeveloper(String developer) { this.developer = developer; }

    public Long getDevelopTime() { return developTime; }

    public void setDevelopTime(Long developTime) { this.developTime = developTime; }

    public Integer getVisitNum() { return visitNum; }

    public void setVisitNum(Integer visitNum) { this.visitNum = visitNum; }

    public Integer getCommentNum() { return commentNum; }

    public void setCommentNum(Integer commentNum) { this.commentNum = commentNum; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public String getPathType() { return pathType; }

    public void setPathType(String pathType) { this.pathType = pathType; }

    public Integer getPathLength() { return pathLength; }

    public void setPathLength(Integer pathLength) { this.pathLength = pathLength; }
}
