package com.zyx.dto;

import io.swagger.models.auth.In;

/**
 * Created by HL on 2016/11/11.
 */
public class LevelDto {
    private  Integer id;
    private  String name;
    private  String step;
    private  Integer score;
    private Integer minScore;
    private Integer maxScore;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getStep() { return step; }

    public void setStep(String step) { this.step = step; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

    public Integer getMinScore() { return minScore; }

    public void setMinScore(Integer minScore) { this.minScore = minScore; }

    public Integer getMaxScore() { return maxScore; }

    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}
