package com.zyx.model;

import javax.persistence.*;

/**
 * 等级
 * Created by HL on 2016/11/11.
 */
@Table(name = "level")
public class Level extends  BaseModel{
    /**
     *主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *等级名称
     */
    private  String name;
    /**
     *阶级
    */
    private  String step;
    /**
     * 升级总共需要积分
     */
    private  Integer score;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getStep() { return step; }

    public void setStep(String step) { this.step = step; }

    public Long getCreateTime() { return createTime; }

    public void setCreateTime(Long createTime) { this.createTime = createTime; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }
}
