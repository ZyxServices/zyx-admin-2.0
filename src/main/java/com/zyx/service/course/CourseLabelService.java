package com.zyx.service.course;

import com.zyx.model.CourseLabel;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          教程标签的业务层接口
 */
public interface CourseLabelService extends BaseService<CourseLabel>{
    /**
     * 添加标签
     * @param courseLabel
     * @return
     */
    Map<String,Object> insertCourseLabel(CourseLabel courseLabel);

    /**
     * 删除标签
     * @param courseLabelId 标签id
     * @return
     */
    Map<String,Object> delCourseLabel(Integer courseLabelId);

    /**
     * 查询所有标签
     * @return
     */
    Map<String,Object> queryCourseLabel(CourseLabel courseLabel);

    /**
     * 设置标签启用或者禁用
     * @param courseLabel
     * @return
     */
    Map<String,Object> updateState(CourseLabel courseLabel);

    /**
     * 根据状态查询标签集合
     * @param
     * @return
     */
    Map<String,Object>queryByState(CourseLabel courseLabel);
}
