package com.zyx.mapper;

import com.zyx.model.Course;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/11/9.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *
 *          教程攻略的持久层接口
 */
@Repository("courseMapper")
public interface CourseMapper extends Mapper<Course> {

    /**
     * 根据标签和类型查询教程攻略
     * @param course
     * @return 教程攻略集合
     */
    List<Course> queryCourse(Course course);

    /**
     * 根据标题进行模糊查询
     * @param course 教程对象
     * @return 教程集合
     */
    List<Course> queryByTitle(Course course);

    /**
     * 编辑教程攻略
     * @param course 教程攻略对象
     * @return 修改状态
     */
    int updateCourse(Course course);

    /**
     * 记录查询数量
     * @return
     */
    int selectCountCourse();



}
