package com.zyx.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 运动信息(场馆路线)
 * Created by HL on 2016/11/7.
 */
@Table(name = "t_sport_info")
public class SportInfo{
    /**
     * 主键id
     */
    private  Integer id;
    /**
     * 运动类型 1-攀岩
     */
    private Integer type;
    /**
     * 场馆id
     */
    @Column(name="venue_id")
    private  Integer venueId;
    /**
     * 路线名称
     */
    private  String path;
    /**
     * 线路图（上传图片的url路径）
     */
    private  String url;
    /**
     * 难度等级
     */
    private  String level;
    /**
     * 难度对应得分
     */
    private  Integer score;
    /**
     * 线路类型
     */
    private  String pathType;
    /**
     * 开线者
     */
    private  String developer;
    /**
     * 开线时间
     */
    @Column(name = "develop_time")
    private  Long developTime;
    /**
     * 创建数据时间
     */
    @Column(name = "create_time")
    private  Long createTime;
    /**
     * 线路长度
     */
    private  Integer pathLength;

    /**
     * 删除（0，正常  1，删除）
     */
    private Integer del;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getVenueId() { return venueId; }

    public void setVenueId(Integer venueId) { this.venueId = venueId; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public String getDeveloper() { return developer; }

    public void setDeveloper(String developer) { this.developer = developer; }

    public Long getDevelopTime() { return developTime; }

    public void setDevelopTime(Long developTime) { this.developTime = developTime; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public String getPathType() { return pathType; }

    public void setPathType(String pathType) { this.pathType = pathType; }

    public Integer getPathLength() { return pathLength; }

    public void setPathLength(Integer pathLength) { this.pathLength = pathLength; }

    public Integer getDel() { return del; }

    public void setDel(Integer del) { this.del = del; }
}
