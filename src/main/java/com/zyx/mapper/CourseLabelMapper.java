package com.zyx.mapper;

import com.zyx.model.CourseLabel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zjx on 2016/11/10.
 *
 * @author zhaojiaxing
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 *          教程标签的持久层接口
 */
@Repository("courseLabelMapper")
public interface CourseLabelMapper extends Mapper<CourseLabel>{

    /**
     * 删除标签
     * @param labelId 标签id
     * @return
     */
//    int deleteByPrimaryKey(Integer labelId);

    /**
     * 查询所有标签
     * @return
     */
//    List<CourseLabel> queryCourseLabel();

    /**
     * 以上两种方法通过继承mapper自动实现
     */

    /**
     * 设置标签启用或者禁用
     * @param courseLabel
     * @return
     */
    int updateState(CourseLabel courseLabel);

    /**
     * 查询启用状态的标签集合
     * @return
     */
    List<CourseLabel> queryByState(CourseLabel courseLabel);
}
