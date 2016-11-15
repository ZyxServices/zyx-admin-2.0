package com.zyx.dto;

/**
 * Created by HL on 2016/11/14.
 */
public class SysMessageDto {

    private Integer id;
    private Integer type;
    private Long pushTime;
    private String content;
    private Integer done;

    public Integer getDone() { return done; }

    public void setDone(Integer done) { this.done = done; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public Long getPushTime() { return pushTime; }

    public void setPushTime(Long pushTime) { this.pushTime = pushTime; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
