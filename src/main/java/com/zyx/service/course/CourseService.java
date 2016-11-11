package com.zyx.service.course;

import com.zyx.model.Course;
import com.zyx.service.BaseService;

import java.util.Map;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          教程攻略的业务层接口
 */
public interface CourseService extends BaseService<Course>{

    /**
     * 发布教程攻略
     * @param course 教程攻略对象
     * @return 发布结果的map集合
     */
    Map<String,Object> insertCourse(Course course);

    /**
     * 根据标签和类型分页查询教程攻略集合
     * @param label 标签
     * @param courseType 教程攻略类型
     * @param page
     * @param pageNumber
     * @return
     */
    Map<String,Object> queryCourse(Integer label,String courseType,int page, int pageNumber);

    /**
     * 根据标题完成查询结果
     * @param title 标题
     * @return 查询结果的map集合
     */
    Map<String,Object> queryByTitle(String title);

    /**
     * 编辑教程攻略
     * @param course 教程攻略对象
     * @return
     */
    Map<String,Object> updateCourse(Course course);

    /**
     * 删除教程攻略（逻辑删除）
     * @param idStr id字符串，可以进行批量删除
     * @param delType 删除类型
     * @return
     */
    Map<String,Object> delCourse(String idStr,int delType);

    /**
     * 屏蔽教程攻略
     * @param id 教程id
     * @param maskType 屏蔽类型
     * @return
     */
    Map<String,Object> maskCourse(int id,int maskType);

}
