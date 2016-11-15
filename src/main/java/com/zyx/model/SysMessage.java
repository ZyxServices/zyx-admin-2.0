package com.zyx.model;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 推送的系统消息
 * Created by HL on 2016/11/14.
 */
@Table(name = "SYS_MSG")
public class SysMessage extends  BaseModel{

    private Integer id;
    /**
     * 推送内容
     */
    private String content;
    /**
     * 消息类型  0-系统通知 1-日常推送
     */
    private Integer type;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;
    /**
     * 定时推送时间
     */
    @Column(name = "push_time")
    private Long pushTime;
    /**
     * 推送类型 0-立即推送  1-定时推送
     */
    @Column(name = "push_type")
    private Integer pushType;

    /**
     * 定时任务是否完成
     */
    private Integer done;

    /**
     * 撤销状态 0-正常 1-撤销
     */
    private Integer mask;

    public Integer getMask() { return mask; }

    public void setMask(Integer mask) { this.mask = mask; }

    public Integer getDone() { return done; }

    public void setDone(Integer done) { this.done = done; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Long getPushTime() { return pushTime; }

    public void setPushTime(Long pushTime) { this.pushTime = pushTime; }

    public Integer getPushType() { return pushType; }

    public void setPushType(Integer pushType) { this.pushType = pushType; }
}
