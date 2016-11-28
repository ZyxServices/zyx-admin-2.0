package com.zyx.dto;

/**
 * Created by HL on 2016/11/25.
 */
public class SportRecordDto {
    private Long createTime;
    private Integer spendTime;
    private String level;
    private String venueName;
    private String imgUrls;

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Integer getSpendTime() { return spendTime; }

    public void setSpendTime(Integer spendTime) { this.spendTime = spendTime; }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getVenueName() { return venueName; }

    public void setVenueName(String venueName) { this.venueName = venueName; }

    public String getImgUrls() { return imgUrls; }

    public void setImgUrls(String imgUrls) { this.imgUrls = imgUrls; }
}
