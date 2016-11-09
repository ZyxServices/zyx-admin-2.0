package com.zyx.model;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * 实体基础类
 * 包含分页的pageNumber和page
 * Created by HL on 2016/11/7.
 */
public class BaseModel {
    /**
     * 每页数量
     */
    @Transient//使用此注解改字段不会映射为表字段
    private  Integer pageNumber=0;
    /**
     * 页码 默认一页取10条数据
     */
    @Transient
    private  Integer page=10;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private  Long createTime;

    public Integer getPageNumber() { return pageNumber; }

    public void setPageNumber(Integer pageNumber) { this.pageNumber = pageNumber; }

    public Integer getPage() { return page; }

    public void setPage(Integer page) { this.page = page; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }
}
